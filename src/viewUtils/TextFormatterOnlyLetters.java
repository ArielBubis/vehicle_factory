/**
 *
 */
package viewUtils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import Exceptions.HandleExceptions;
import Exceptions.InputNotALetter;
import Exceptions.InputSizeException;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class TextFormatterOnlyLetters extends DocumentFilter {

	private int maxLength = 0;

	/**
	 * Set the max length of the string
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
	 * These methods will make sure you can only input Alphabet in the field
	 */
	@Override
	public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
		Document doc = fb.getDocument();

		// iterate on the string in case user input was not a single character
		for (int n = string.length(); n > 0; n--) {
			char c = string.charAt(n - 1);// get a single character of the string
			try {
				if (checkChar(c) && checkMax(maxLength, string.length(), doc)) {
					super.replace(fb, i, i1, String.valueOf(c), as);// allow update to take place for the given
																	// character
				} else {// it was not an alphabetic character or white space
				}
			} catch (InputSizeException | BadLocationException | InputNotALetter e) {
				// TODO Auto-generated catch block
				HandleExceptions.showException(e);
			}
		}
	}

	@Override
	public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
		super.remove(fb, i, i1);
	}

	@Override
	public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {
		super.insertString(fb, i, string, as);

	}

	/*****************************************************/
	private boolean checkMax(int max, int strLength, Document doc) throws InputSizeException {
		if (maxLength == 0 || (doc.getLength() + strLength) <= maxLength) {
			return true;
		} else {
			throw new InputSizeException(max);
		}
	}

	private boolean checkChar(Character c) throws InputNotALetter {
		if (Character.isAlphabetic(c) || c == ' ' || c == '-') {
			return true;
		}else {
			throw new InputNotALetter();
		}
	}
}
