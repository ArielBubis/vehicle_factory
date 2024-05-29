/**
 *
 */
package Exceptions;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class AllDepartmentsCrated extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public AllDepartmentsCrated() {
		super("You can't add any more Departments");
	}

}
