/**
 *
 */
package viewUtils;

import java.awt.HeadlessException;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import Exceptions.HandleExceptions;
import Exceptions.InputDecimalIsWrong;
import Exceptions.InputNotANumber;
import Exceptions.InputSizeException;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class TextFormatterOnlyNumbers extends DocumentFilter {

	private int maxLength = 0;
	private boolean isDoubleFlag = false;
	private boolean isPrecentFlag = false;
	private String str = "";// write down full string in this

	/**
	 * Set the max length of the string if not set there will be no limit
	 *
	 * @param maxLength
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * If we want the output to be of a double format we set this to true
	 */
	public void isDouble() {
		isPrecentFlag = false;
		isDoubleFlag = true;
	}

	public void isPrecent() {
		isPrecentFlag = true;
		isDoubleFlag = false;
	}

	/**
	 * These methods will make sure you can only input Alphabet in the field
	 */
	@Override
	public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
		Document doc = fb.getDocument();
		// iterate on the string in case user input was not a single character
		for (int n = string.length(); n > 0; n--) {
			char c = string.charAt(n - 1);// get a single character of the string
			try {
				if (checkChar(c, fb, i, i1, string, as) && checkMax(maxLength, string.length(), doc)) {
					super.replace(fb, i, i1, String.valueOf(c), as);// allow update to take place for the given
				}
			} catch (HeadlessException | InputSizeException | BadLocationException | InputDecimalIsWrong
					| InputNotANumber e) {
				// TODO Auto-generated catch block
				HandleExceptions.showException(e);
			}
		}
	}

	@Override
	public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
		str = str.substring(0, i);
		super.remove(fb, i, i1);
	}

	@Override
	public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {
		super.insertString(fb, i, string, as);

	}

	/*****************************************************/
	/**
	 *
	 * @param max
	 * @param strLength
	 * @param doc
	 * @return True if the input is shorter then the maxLength and an Exception if
	 *         longer
	 * @throws InputSizeException
	 */
	private boolean checkMax(int max, int strLength, Document doc) throws InputSizeException {
		if (maxLength == 0 || (doc.getLength() + strLength) <= maxLength) {
			return true;
		} else {
			throw new InputSizeException(max);
		}
	}

	/**
	 * This methods will check if the character is right according to the parameters
	 * we need
	 *
	 * @param c
	 * @param fb
	 * @param i
	 * @param i1
	 * @param string
	 * @param as
	 * @return true if the input is correct false if not
	 * @throws InputDecimalIsWrong
	 * @throws InputNotANumber
	 * @throws BadLocationException
	 */
	private boolean checkChar(Character c, FilterBypass fb, int i, int i1, String string, AttributeSet as)
			throws InputDecimalIsWrong, InputNotANumber, BadLocationException {
		Document doc = fb.getDocument();
		int index = doc.getLength();
		// Check input is a number
		if (c >= '0' && c <= '9') {
			if (isPrecentFlag && index == 0) {
				super.replace(fb, 0, 0, "0." + c, as);
				return false;
			}
			str = str + c;
			return true;
		} else {
			if (isDoubleFlag) {
				// A decimal point is allowed on the double format but it cannot be the first
				// character
				if (c == '.' && !str.contains(".")) {// Check if the string already contains a point
					if (index == 0) {
						throw new InputDecimalIsWrong();
					} else {
						str = str + c;
						return true;
					}
				} else {
					throw new InputNotANumber();
				}
			} else {
				throw new InputNotANumber();
			}
		}

	}
}
