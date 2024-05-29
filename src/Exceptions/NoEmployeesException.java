/**
 *
 */
package Exceptions;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class NoEmployeesException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NoEmployeesException() {
		super("No Employees in the department, You can add an employee in the 'Add Emplyee' menu item");
	}


}
