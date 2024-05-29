/**
 *
 */
package model;

import java.util.Comparator;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class DepManagerCompare implements Comparator<DepartmentManager>{

	@Override
	public int compare(DepartmentManager depM1, DepartmentManager depM2) {

		if(depM1.getSalary().equals(depM2.getSalary())) {
			if(depM1.getDateOfStartWork().compareTo(depM2.getDateOfStartWork())==0)
				return depM1.compareTo(depM2);
			 return depM1.getDateOfStartWork().compareTo(depM2.getDateOfStartWork());
		}



		return depM1.getSalary().compareTo(depM2.getSalary());
	}


	/**
	 *
	 * @param HashMap<String, Employee> map - HashMap of all Employees
	 * @return ArrayList<DepartmentManager> sortedDepartmentManagersArrayList - Sorted ArrayList of Employees
	 */
	/*
	 static ArrayList<DepartmentManager> buildArrayList(HashMap<String, Employee> map){
		ArrayList<DepartmentManager> sortedDepartmentManagersArrayList = new ArrayList<DepartmentManager>();
		for(HashMap.Entry<String, Employee> emp: map.entrySet()) {
			Employee val = emp.getValue();
			if(val instanceof DepartmentManager) {
				sortedDepartmentManagersArrayList.add((DepartmentManager) val);
			}
		}

		Collections.sort(sortedDepartmentManagersArrayList,new DepManagerCompare());
		return sortedDepartmentManagersArrayList;
	}*/
}
