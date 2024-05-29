/**
 *
 */
package model;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class DepartmentAlredyExsist extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DepartmentAlredyExsist(Department dep) {
		super("The Department number " + dep.getDepartmentID() + " Already exists");
	}

}
