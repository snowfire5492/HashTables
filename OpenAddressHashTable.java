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


/**
 * @author Eric Schenck
 * 
 * @param <K> key
 * @param <V> value
 */
public class OpenAddressHashTable<K extends Comparable<K>,V> implements 
HashTable<K,V> {
	
	// ratio used of used to total number of buckets (# used buckets 
/ # buckets )
	protected int loadFactor;
	
	protected Object[] myTable;
	
	protected int openBuckets;
	
	
	// initially set to size 10, will increase if array is full
	protected int hashSize = 10;
	
	protected int closedBuckets = (hashSize - openBuckets);
	
	/**
	 *  Constructor for OpenAddressHashTable
	 */
	public OpenAddressHashTable() {
		
		// creating array size 10, filled with null values
		myTable = new Object[hashSize];
		openBuckets = hashSize;
	}
	
	
	@Override
	public void add(K key, V value) {
		
		// checking if array is full, if so this doubles size of 
array. 
		ensureCapacity();
		
		// getting hashIndex using additive hash function
		int hashIndex = getHashIndex(key,hashSize);
		
		// checks hashIndex location to see if it is null or 
not. 
		// if myTable[hashIndex] == null then incriment until 
null
		while(myTable[hashIndex] != null) {
			int temp = (hashIndex + 1) % hashSize;
			hashIndex = temp;
		}
		myTable[hashIndex] = new Entry(key, value);
		--openBuckets;
		
	}
	
	/**
	 * creates a printable list of entries in hashtable 
	 * used for debugging purposes
	 * 
	 * @return String used for list printout
	 */
	@SuppressWarnings("unchecked")
	public String toString() {
		String temp = ""; 
		
		
		for(int i = 0; i < myTable.length; i++) {
			
			if(myTable[i] != null) {
				temp += (i + " (" + 
((Entry)myTable[i]).key + ", " 
						+ 
((Entry)myTable[i]).value + ") \n ");
			}else
				temp += (i + " null \n");
		}
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	public String[] toStringArray() {
		
		String[] temp = new String[myTable.length];
		int tempCounter = 0;
		for(int i = 0; i < myTable.length; i++) {
			
			if(myTable[i] != null) {
				temp[tempCounter++] = 
((Entry)myTable[i]).key + " " 
						+ 
((Entry)myTable[i]).value ;
			}
		}
		return temp;
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void ensureCapacity() {
		// check if array is full. if so then create a new array 
double
		// the size and copy elements to it
		if(openBuckets == 0) {
			
			// create tempTable to increase size of table
			Object[] tempTable = new Object[hashSize *= 2];
			
			// go thru old table and rehash keys to new 
table
			for(int i = 0; i<hashSize/2; ++i) {
				
				int tempIndex = 
getHashIndex(((Entry)myTable[i]).key,hashSize);
				
				tempTable[tempIndex] = myTable[i];
				//tempTable[i] = myTable[i];
			}
			
			// myTable becomes the new array at double size
			myTable = tempTable;
			
			// update openbuckets values for new array 
			openBuckets =  (hashSize / 2);
			
			// update closedBuckets for new array
			closedBuckets = hashSize - openBuckets; // 
always hashSize/2 
			
			
		}
		
	}
	
	/**
	 * runs hash functions,
	 * using this so i can easily switch between hash functions
	 * 
	 * @param key
	 * @param hashSize
	 * @return index for storage
	 */
	private int getHashIndex(K key, int hashSize) {
		// getting hashIndex using additive hash function
		return add_hash(key,hashSize);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public V remove(K key){
		
		V valueAtKey = null; 
		
		// getting hash index for key
		int hashIndex = getHashIndex(key,hashSize);
		
		while(myTable[hashIndex] != null) {
			// checking for similar keys starting at hashed 
index
			if 
((((Entry)myTable[hashIndex]).key.compareTo(key)) != 0) {
				int tempIndex = (hashIndex + 1) % 
hashSize ;
				hashIndex = tempIndex; 
			}else {
				// saving value at entry being removed
				valueAtKey = 
((Entry)myTable[hashIndex]).value;
				
				// removing entry by setting to null
				myTable[hashIndex] = null;
				
				//updating closedBuckets and openBuckets
				++openBuckets;
				--closedBuckets;
			}
		}	
		
	  return valueAtKey;
	}
	 
	@Override
	@SuppressWarnings("unchecked")
	public V lookup(K key){
		
		V valueAtKey = null; 
		
		// getting hash index for key
		int hashIndex = getHashIndex(key,hashSize);
		
		A: while(myTable[hashIndex] != null) {
			// checking for similar keys starting at hashed 
index
			if 
((((Entry)myTable[hashIndex]).key.compareTo(key)) != 0) {
				int tempIndex = (hashIndex + 1) % 
hashSize ;
				hashIndex = tempIndex; 
			}else {
				// saving value at entry being removed
				valueAtKey = 
((Entry)myTable[hashIndex]).value;
				break A;
			}
		}
	  return valueAtKey;
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public Object[] getValuesList(){
	
		int listCounter = 0;
		//creates object array of size of closed or filled 
buckets
		Object[] list = new Object[closedBuckets];
		
		// fills list with non null values
		for(int i = 0; i < hashSize; ++i ) {
			if(myTable[i] != null) {
				list[listCounter] = 
((Entry)myTable[i]).value;
				++listCounter;
			}
		}
		return list;
	}
	
	@Override
	public Object[] getSortedList(Object[] list) {
		return bubbleSort(list);
	}
	
	@SuppressWarnings("unchecked")
	public Object[] bubbleSort(Object[] list) {
		
		Object[] tempList = new Object[closedBuckets];
		
		tempList = list;
		
		
		
		boolean swapped = false;
		
		do {
			swapped = false;
			
			for (int i = 0; i < (closedBuckets - 1); ++i) {
				if(tempList[i] != null && tempList[i+1] 
!= null) {
				
if((((Entry)tempList[i]).key.compareTo(((Entry)tempList[i+1]).key)) != 
0) {
					System.out.println("TESTING" + 
i);
					Object temp = tempList[i];
					tempList[i] = tempList[i+1];
					tempList[i+1] = temp;
					swapped = true;
				}
				}
			}
		} while (swapped);
		
		return tempList;
	}
	
	
	
	/**
	 * using to print sorted list. uses getSortedList() 
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public void printSorted() {
		
		String toPrint = "";
		
		Object[] tempList = new Object[hashSize];
		tempList = getSortedList(myTable);
		
		for(int i = 0; i < tempList.length; ++i) {
			if(tempList[i] != null) {
				toPrint += (((Entry)myTable[i]).key + " 
" 
						+ 
((Entry)myTable[i]).value + "\n");
			}
			
		}
		System.out.print(toPrint);
	}
	
	
	
	
	@Override
	public void printReport(){
		
		System.out.println("OpenAddressHashTable: load factor = 
" + calculateLoad());
		
	}
	
	
	@Override
	public int add_hash(K key, int value) {
		  
	  int hash = 0;
	  String temp = (String) key;
	 
	  for(int i = 0; i < temp.length() ; i++) {
		  hash += temp.charAt(i); 
	  }
	  
	  hash %= value;	  
	  return hash;
	}
	
	
	@Override
	public int xor_hash(K key, int value) {
		
		int hash = 0;
		String temp = (String) key;
		String scrambledKey = "";
		int counter = 0;
		int subArrayCounter = 0;
		
		// stack 
		char[] stack = new char[temp.length()];
		//int stackCounter = 0;
		
		// queue 
		String[] subArray = new String[temp.length()];
		int firstOut = 0;
		
		for(int i = 0; i < temp.length(); ++i ) {
			
			if ( counter == 2) {
				String subTemp = temp.substring((i -2), 
i);
				subArray[subArrayCounter++] = subTemp;
				counter = 0;
			}else if ( (i+1) == temp.length() ) {
				String subTemp = temp.substring((i-1), 
i);
				subArray[subArrayCounter++] = subTemp;
			}
			++counter;	 
		}
		
		for(int i =0; i < subArrayCounter; ++i) {
			for(int j = 0; j < subArray[i].length(); ++j ) {
				stack[j] = subArray[i].charAt(j);
				++firstOut;
			}
			for(int j = 0; j < subArray[i].length(); ++j ) {
				scrambledKey += stack[firstOut--];
			}
	
		}
		for(int i = 0; i < scrambledKey.length() ; i++) {
			  
			hash += scrambledKey.charAt(i); 
		  }
		  
		hash %= value;
		  
		
		return hash;

	}
	
	
	// ratio used of used to total number of buckets (# used buckets 
/ # buckets )
	private double calculateLoad() {
		return (closedBuckets / hashSize);
	}
	
	public int size() {
		return hashSize;
	}
	
	
	private class Entry {
		
		protected K key;
		protected V value;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}

