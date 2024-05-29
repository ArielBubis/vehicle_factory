/**
 *
 */
package viewUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import Exceptions.HandleExceptions;
import model.Factory;

/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class SerializableMethods {
	public static Factory factory;

	public static void createFactoryLib() throws UnsupportedAudioFileException, LineUnavailableException {
		try {

			Factory fac = deserialize();
			if (fac == null) {
				fac = Factory.create();
			}
			factory = fac;
			// in case the file is not found
		} catch (FileNotFoundException e) {
			HandleExceptions.showException(e);
		}
		// in case there was a problem loading data from file
		catch (IOException eio) {
			JOptionPane.showMessageDialog(null, "Sound Problems");
		}
	}

	public static void serialize() throws IOException {
		String filename = "factory.ser";
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		// trying to save the data
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(model.Factory.getInstance());
			out.close();
			Factory.getInstance().sound.playSound("cheering");
			JOptionPane.showMessageDialog(null, "Data Saved successfully", "Data Saved",
					JOptionPane.INFORMATION_MESSAGE);
		}

		// general exceptions
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Saving data failed ", "Save Error", JOptionPane.ERROR_MESSAGE);
		}
	}// end of save method

	@SuppressWarnings({ "resource" })
	public static Factory deserialize() throws IOException {
		//Factory factory = null;
		String filename = "Factory.ser";
		FileInputStream fis = null;
		ObjectInputStream in = null;

		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			factory = (Factory) in.readObject();
			Factory.getInstance().sound.playSound("System_reload2");
			JOptionPane.showMessageDialog(null, "File was deserialized succesfuly", "Factory",
					JOptionPane.INFORMATION_MESSAGE);

			return factory;
		}

		// in case the file is not found
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File wasn't found, creating new factory", "Missing file",
					JOptionPane.ERROR_MESSAGE);

			return factory;

		}

		// in case there was a problem loading data from file
		catch (IOException eio) {
			JOptionPane.showMessageDialog(null, "system wasn't able to read from file.. creating new factory",
					"Read File Error", JOptionPane.INFORMATION_MESSAGE);
			return factory;
		}

		// genreal exceptions
		catch (Exception e) {
			return factory;
		}

	}
}
