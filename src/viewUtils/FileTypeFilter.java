/**
 *
 */
package viewUtils;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 */
public class FileTypeFilter extends FileFilter {
	/**
	 *
	 * This Class will create filter for the JFileChooser
	 *
	 */
	private String extension;
	private String description;

	public FileTypeFilter(String extension, String description) {
		this.extension = extension;
		this.description = description;
	}

	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		}
		return file.getName().endsWith(extension);
	}

	@Override
	public String getDescription() {
		return description + String.format(" (*%s)", extension);
	}
}