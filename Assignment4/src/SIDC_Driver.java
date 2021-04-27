import java.util.Scanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
//-----------------------------------------------
//Assignment 4
//Programming Question SIDC Driver
//Written by Jordan Chan Kum Sang- 40125997
//-----------------------------------------------
/**
* Name: Jordan Chan Kum Sang - 40125997
* Section: COMP 352 Section FF
* Assignment 4
* Due Date: December 4, 2020
*/

public class SIDC_Driver {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int choice;
		IntelligentSIDC SIDC = null;

		
		/*
		 * Prompt user to choose either default values or enter their own
		 */
		System.out.print("\nChoice Selection: "
				+ "\nPress 1 to use default values: size=2000, threshold= 1000 with generation of 100 keys."
				+ "\nPress 2 to use user values."
				+ "\nPress 3 to read files."
				+ "\nEnter Choice: ");
				
		choice=keyboard.nextInt();
		
		if (choice <=0  || choice>3) {
			System.out.println("Wrong choice!!");
			System.exit(0);
		}
		
		switch(choice) {
		
			case 1:{
				SIDC = new IntelligentSIDC(2000);
				SIDC.setThreshold(1000);
				SIDC.generate(100);
				break;
			}
			case 2:{
				System.out.println("What is the size (total number of Keys?");
				int size = keyboard.nextInt();
				SIDC = new IntelligentSIDC(size);

				System.out.println("What is the threshold that will determine if we will use Data Structure or Sequence?");
				int threshold = keyboard.nextInt();
				SIDC.setThreshold(threshold);

				System.out.println("generate, how many keys");
				int gen = keyboard.nextInt();
				SIDC.generate(gen);
				break;
			}
			case 3:{
				String[] filename= {"CSTA_test_file1.txt", "CSTA_test_file2.txt", "CSTA_test_file3.txt"};
				Scanner[] sc= new Scanner[3];
				int count;
				String id;

				sc[0]=null;
				sc[1]=null;
				sc[2]=null;
				
				IntelligentSIDC[] dataset=new IntelligentSIDC[3];
				dataset[0]=new IntelligentSIDC(500000);
				dataset[1]=new IntelligentSIDC(500000);
				dataset[2]=new IntelligentSIDC(1000000);
				
				
				for (int i=0; i< 3; i++) {
					count=0;
					try {
						sc[i]=new Scanner(new FileInputStream(filename[i]));
						
						while (sc[i].hasNextLine()) {
							id=sc[i].nextLine();
							//count++;
							dataset[i].add(id, "Name_"+id);
							
						}
						LinkedList<String> ffile=dataset[i].allKeys();
						
						ffile = dataset[i].allKeys();
						
						for (String element : ffile) { //
							Student Student = dataset[i].getValue(element);
							System.out.println(element + " " + Student.toString());
							count++;
						}
						System.out.println(count);
						sc[i].close();
					} catch (FileNotFoundException e) {
						System.out.println("File cannot be opened");
					}
				}
				System.exit(0);
				break;
				
			}
		}


		LinkedList<String> list = SIDC.allKeys();

		// allkeys
		System.out.println("Test method: allkeys(), but also print values");
		for (String element : list) {
			Student Student = SIDC.getValue(element);
			System.out.println(element + " " + Student.toString());

		}
		;
		System.out.println();
		// add(key,value)
		System.out.println("Test method add(key,value)- 3 times");
		System.out.println(
				"key=22345678, Add_1");
		System.out.println(
				"key=42345678, Add_2");
		System.out.println(
				"key=52345678, Add_3");
		System.out.println();

		SIDC.add("22345678", "Add_1");
		SIDC.add("42345678", "Add_2");
		SIDC.add("52345678", "Add_3");
		list = SIDC.allKeys();
		for (String element : list) { //
			Student Student = SIDC.getValue(element);
			System.out.println(element + " " + Student.toString());

		}

		System.out.println();

		// remove(key)
		System.out.println("Test method: remove(key)-2 times\nkey= 123456783\nLast key");
		System.out.println();

		SIDC.remove("123456783");
		SIDC.remove(list.getLast());

		list = SIDC.allKeys();
		for (String element : list) {
			Student Student = SIDC.getValue(element);
			System.out.println(element + " " + Student.toString());

		}

		System.out.println();

		System.out.print("First value " + list.getFirst() + ": ");

		System.out.println(SIDC.getValue(list.getFirst()));
		System.out.print("Last value  " + list.getLast() + ": ");
		System.out.println(SIDC.getValue(list.getLast()));

		System.out.println();
		//nextKey()
		System.out.println("Test method: nextkey(): first key");
		System.out.println(SIDC.nextKey(list.getFirst()));

		System.out.println();
		//prevKey()
		System.out.println("Test method: prevkey(): last key");
		System.out.println(SIDC.prevKey(list.getLast()));

		System.out.println();
		//rangeKey()
		System.out.println("Test method: rangeKey(key1,key2)");
		System.out.println();
		SIDC.rangeKey(list, list.getFirst(), list.getLast());
		
		keyboard.close();
	}

}

