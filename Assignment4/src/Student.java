//-----------------------------------------------
//Assignment 4
//Programming Question Student Class
//Written by Jordan Chan Kum Sang- 40125997
//-----------------------------------------------
/**
* Name: Jordan Chan Kum Sang - 40125997
* Section: COMP 352 Section FF
* Assignment 4
* Due Date: December 4, 2020
*/

/**
 * Student Class
 * @author jordan
 *
 */
public class Student {
	//private attributes
	private String name; 
	private static int count=0;
	/**
	 * Default constructor
	 */
	public Student() {
		count++;
		name="Name_"+Integer.toString(count);
		
	}
	
	/**
	 * Parametrised Constructor
	 * @param name Student
	 */
	public Student(String name) {
		this.name=name;
	}
	
	/**
	 * Copy Constructor
	 * @param stu Student
	 */
	public Student(Student stu) {
		this.name=stu.name;
	}
	
	//Mutator method
	/**
	 * Method to set name
	 * @param name String name
	 */
	public void setname(String name) {
		this.name=name;
	}
	
	//Accessor method
	/**
	 * Method to return name
	 * @return name 
	 */
	public String getname() {
		return name;
	}
	
	/**
	 * Method to return message
	 */
	public String toString() {
		return name;
	}
}
