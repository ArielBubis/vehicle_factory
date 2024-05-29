/**
 *
 */
package viewUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.MaskFormatter;

import Exceptions.IncorrectDateFormat;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class TextFormattersClass{
	private static SimpleDateFormat dateFormat;
	private static MaskFormatter tempFormatter;



	/**
	 * This method will return a Formatter that will force a condition on a
	 * JFormattedTextField to only accept numbers that a Date format
	 *
	 * @return dateFormat SimpleDateFormat class
	 */
	public static SimpleDateFormat MyDateFormmater() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		return dateFormat;
	}


	/**
	 * Format phone number
	 * @return
	 */
	public static MaskFormatter getPhoneFormatter() {
		try {
			tempFormatter = new MaskFormatter("###'-####'-###");
			tempFormatter.setOverwriteMode(true);
			tempFormatter.setAllowsInvalid(false);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempFormatter;
	}


	/**
	 * Get the formatted field of date using SimpleDateFormat
	 * @return formatted SimpleDate
	 * @throws ParseException
	 * @throws IncorrectDateFormat
	 */
	public static Date getDate(String str) throws ParseException, IncorrectDateFormat {
		Date temp = new SimpleDateFormat("dd/MM/yyyy").parse(str);
		if (temp.after(new Date()) || temp.before(new Date(01 / 01 / 1900)))
			throw new IncorrectDateFormat();
		return temp;
	}
}
