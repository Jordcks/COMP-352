
import java.util.Set;
import java.util.TreeMap;
//-----------------------------------------------
//Assignment 4
//Programming Question IntelligentSIDC Class
//Written by Jordan Chan Kum Sang- 40125997
//-----------------------------------------------
/**
* Name: Jordan Chan Kum Sang - 40125997
* Section: COMP 352 Section FF
* Assignment 4
* Due Date: December 4, 2020
*/
public class IntelligentSIDC {

	//private attributes
	private int threshold;
	//private int keyLength=8;
	private int size = 0;
	private int max;
	// We stored values in a sequence at first
	boolean IsSequence = false;

	// ADT: entries>=threshold
	private TreeMap<String, Student> records_2 = new TreeMap<>();

	// Sequence: entries<threshold>
	private DoubleLinkList records_1 = new DoubleLinkList();



	/**
	 * Default constructor
	 */
	public IntelligentSIDC() {

	}

	/**
	 * Constructor
	 * 
	 * @param maxx Size of IntelligentSIDC
	 */
	public IntelligentSIDC(int maxx) {
		this.max = maxx;
	}

	/**
	 * 
	 * @param size  number of entries
	 * @return True, if we should use the ADT
	 */
	public boolean GreatherThanThreshold(int size) {
		if (size >= threshold)
			return true;
		else
			return false;
	}

	/**
	 * Sets the threshold of the IntelligentSIDC
	 * 
	 * @param threshold_pass The threshold to be set
	 */
	public void setThreshold(int threshold_pass) {

		// Must be between 100 and 500K
		if (threshold_pass < 100 || threshold_pass > 500000) {
			System.out.println("Error, threshold must be between 100 and 500,000");
			System.exit(0);
		}

		this.threshold = threshold_pass;
	}

	/**
	 * 
	 * @return The threshold
	 */
	public int getThreshold() {
		return this.threshold;
	}


	/**
	 * 
	 * @param n How many keys to be generated
	 */
	public void generate(int n) {

		// If the number of entries plus the number of generated keys is more than size,
		// we cannot
		if (size + n > max) {
			System.out.println("Sorry, the maximum size of " + max + " has been reached");
			return;
		}

		// Use ADT
		if (GreatherThanThreshold(n + size)) {
			if (IsSequence == true) {
				changeADT();
			}
			for (int i = 0; i < n; i++) {
				Student temp = new Student();
				//String temp_SIDC = generateSIDC(keyLength); to
				String temp_SIDC = generateSIDC();
				if (!records_2.containsKey(temp_SIDC)) {
					records_2.put(temp_SIDC, temp);
					size++;
					// Duplicate key
				} else if (records_2.containsKey(temp_SIDC)) {
					i--;
					continue;
				}
			}

		}
		// Use sequence
		else if (!GreatherThanThreshold(n + size)) {
			IsSequence = true;
			for (int i = 0; i < n; i++) {
				Student temp = new Student();
				String temp_SIDC = generateSIDC();
				
				if (size == 0) {
					records_1.addFirst(temp_SIDC, temp);
					size++;
					// Duplicate key
				} else if (!records_1.contains(temp_SIDC)) {

					records_1.addFirst(temp_SIDC, temp);
					size++;

				} else if (records_1.contains(temp_SIDC)) {
					i--;
					continue;
				}
			}
		}
		System.out.println("Finished generating new keys");
		// Must sort the keys in the end
		sort();

	}

	
	public static String generateSIDC() {
		String id;
		id="";
		int num=(int) (Math.random() *9) +1;
		
		id+=Integer.toString(num);
		
		for (int i=0; i<7; i++) {
			num=(int) (Math.random() * 10);
			id+=Integer.toString(num);
		}
		
		return id;
	}

	/**
	 * 
	 * @return LinkedList with all keys sorted
	 */
	public LinkedList<String> allKeys() {

		LinkedList<String> sorted = new LinkedList<String>();

		// ADT
		if (GreatherThanThreshold(size)) {
			Set<String> keys = records_2.keySet();
			sorted.addAll(keys);
		//Sequence
		} else {
			//Calls method for linked list allkeys(), see the class DoublyLinkedList
			sorted = records_1.allKeys();
		}

		return sorted;
	}

	/**
	 * Sorts the keys in the IntelligentSIDC
	 */
	public void sort() {
		if (!GreatherThanThreshold(size)) {
			records_1 = records_1.sortList();
		}
	}
	
	/**
	 * 
	 * @param key The SIDC
	 * @param name String
	 * Adds a Student with these values
	 */

	public void add(String key, String name) {

		//no space
		if (size == max) {
			System.out.println("Sorry, the maximum size of " + max + " has been reached");
			return;
		}

		Student Student_pass = new Student(name);
		//ADT
		if (GreatherThanThreshold(size)) {

			//Duplicate
			if (records_2.containsKey(key)) {
				System.out.println("Could not add the key " + key + " because it is already there");
			}

			else {
				//Must change ADT if we were previously using sequence
				if (IsSequence == true) {
					changeADT();
				}

				records_2.put(key, Student_pass);
				//Increment number of entries by 1
				size++;
			}
		}

		//sequence 
		else if (!GreatherThanThreshold(size)) {
			IsSequence = true;
			//deuplicate
			if (records_1.contains(key)) {
				System.out.println("Could not add the key " + key + " because it is already there");
			}

			
			//CHECK
			
			records_1.addHere(key, Student_pass);
			//increment size by 1
			size++;
		}
		
	}

	/**
	 * 
	 * @param key_pass The key whose value will be removed
	 */
	public void remove(String key_pass) {
		//ADT
		if (GreatherThanThreshold(size)) {
			//Decrement size and remove key
			if (records_2.remove(key_pass) != null)
				size--;
			else {
				System.out.println("Sorry could not delete that key");
			}
		//Sequence
		} else if (!GreatherThanThreshold(size)) {
			//Decrement size and remove key
			if (records_1.deleteKey(key_pass))
				size--;
			else {
				System.out.println("Sorry could not delete that key");
			}
		}
		
	}

	/**
	 * Method to get value of key
	 * @param key The key whose value will be found
	 * @return Student name
	 */
	public Student getValue(String key) {
		if (GreatherThanThreshold(size)) {
			return records_2.get(key);
		}

		else
			return records_1.getValueFromKey(key);
	}

	/**
	 * Method to return next key
	 * @param key String 
	 * @return key String
	 */
	public String nextKey(String key) {
		if (GreatherThanThreshold(size)) {
			if (records_2.higherEntry(key) == null)
				return null;
			return records_2.higherEntry(key).getKey();
		} else {
			return records_1.nextKey(key);
		}
	}

	/**
	 * Return previous key
	 * @param key String
	 * @return String key
	 */
	public String prevKey(String key) {
		if (GreatherThanThreshold(size)) {
			if (records_2.lowerEntry(key) == null)
				return null;
			return records_2.lowerEntry(key).getKey();
		} else {
			return records_1.prevKey(key);
		}
	}

	/**
	 * Method to change ADt when size is too bug
	 */
	public void changeADT() {
		while (!records_1.isEmpty()) {
			String key_temp = records_1.firstKey();
			Student Student_temp = records_1.firstValue();
			records_1.removeFirst();
			records_2.put(key_temp, Student_temp);
		}
		IsSequence = false;
	}
	
	/**
	 * Return all keys with range
	 * @param list LinkedList
	 * @param key1 String
	 * @param key2	String
	 */
	public void rangeKey(LinkedList<String> list, String key1,String key2) {
		//LinkedList<String> ranged_list = new LinkedList<String>();
		if ((list.contains(key1)) && (list.contains(key2))){
			
			for(int i=Integer.parseInt(key1); i<=Integer.parseInt(key2); i++) {
				 if (this.getValue(Integer.toString(i)) != null) { 
					 System.out.println((Integer.toString(i)) + " " + this.getValue(Integer.toString(i)));
				 }
			}
				
		}

		
	}
}