/**
 * CS 240: Introduction to Data Structures
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #5
 *
 * <create Hash Tables using two different hash functions and create a 
 * simple student registry program using one of these hash tables>
 *
 * @author Eric Schenck
 * last modified: 6/4/17
 */
package edu.cppcs.cs240.hash_project_hw5;


import java.util.Scanner;

import java.io.*;


public class StudentRegistry {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		int userChoice;
		
		String fileName = "//WINDOWS-PC//Users//Eric//"
				+ "workspace//studentRegistry.txt";
		
		// creating hashTable object
		@SuppressWarnings("rawtypes")
		OpenAddressHashTable myTable = new 
OpenAddressHashTable();
		
		File file = new File(fileName);	// openning file to read 
in data
		Scanner inputFile = new Scanner(file);
		
		while(inputFile.hasNext()) {
			String temp = inputFile.nextLine();
			String tempKey = temp.substring(0,5);
			String tempValue = 
temp.substring(6,(temp.length()));
			
			myTable.add(tempKey, tempValue);
			
		}
		
		// closing read in file 
		inputFile.close();
		
		// used to test input from file
		//System.out.print(myTable.toString());
		
		
		
		Scanner keyboard = new Scanner(System.in);
		Scanner keyboard2 = new Scanner(System.in);
		
		A : while(true) {
		
			
System.out.println("-----------------------------------------");
			System.out.println("---- Welcome to the Student 
Registry ----");
			System.out.println();
			System.out.println("Create a new Entry: Enter 
1");
			System.out.println("Lookup a student:   Enter 
2");
			System.out.println("Remove a student:   Enter 
3");
			System.out.println("Display registry:   Enter 
4");
			System.out.println("Save and Close:     Enter 
5");
			
			System.out.println(myTable.toString());
			userChoice = keyboard.nextInt();
		
			switch(userChoice) {
		
			case 1:
				// create a new entry
				String userKey;
				String userName;
				String userGrade;
				String tempString;
				
				System.out.println("Please enter an ID 
number 5-digits long...");
				userKey = keyboard2.nextLine();
				System.out.println("Now enter the 
students name...");
				userName = keyboard2.nextLine();
				System.out.println("Now enter the 
students grade...");
				userGrade = keyboard2.nextLine();
				tempString = userName + " - " + 
userGrade;
				myTable.add(userKey, tempString);
				break;
			case 2:
				// lookup student
				System.out.println("Enter the ID of 
student to lookup...");
				userKey = keyboard2.nextLine();
				userName = (String) 
myTable.lookup(userKey);
				System.out.println(userName);
				break;
			case 3:
				// remove student
				System.out.println("Enter the ID of 
student to remove...");
				userKey = keyboard2.nextLine();
				userName = (String) 
myTable.remove(userKey);
				System.out.println("Removing " + 
userName);
				break;
			case 4:
				// display registry
				myTable.printSorted();
				break;
			case 5:
				// saves and closes program
				PrintWriter outFile = new 
PrintWriter(fileName);
				String[] temp1 = new 
String[myTable.size()];
					
				temp1 = myTable.toStringArray();	
				
				for(int i = 0; i < temp1.length; ++i) {
					if(temp1[i] != null){
						
outFile.println(temp1[i]);
					}
				}
				outFile.close();
				System.out.println("Goodbye...");
				break A;
			}
		}
		keyboard.close();
		keyboard2.close();
	}
	
	
}

