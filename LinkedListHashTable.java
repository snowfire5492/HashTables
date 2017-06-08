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


public class LinkedListHashTable<K extends Comparable<K>,V> implements 
HashTable<K,V> {
	
	
	Object[] myTable;// = new Object[10];
	
	// # of used buckets / # total buckets
	protected double loadFactor;
	
	// if a chain gets too big, i.e. too many collisions, resize 
table
	protected double longestChain;
	
	// # of mappings / # total buckets
	protected double densityFactor;
	
	// average length of any chain in the table
	protected double chaniningFactor;
	
	// to hold total number of array buckets, init at 10
	protected int totalBuckets = 10;
	
	// to hold total number of elements stored, init at 0
	protected int totalMappings = 0;
	
	// used to hold number of empty indices or buckets 
	protected int openBuckets;
	
	// used to hold number of used or closed buckets init at 0
	protected int closedBuckets = 0;

	// used to hold current size of array list
	protected int hashSize;
	
	//private HashNode head = null;
	
	public LinkedListHashTable() {
		
		//hashArray = new ArrayList<>();
		myTable = new Object[totalBuckets];
		
		openBuckets = totalBuckets;
		hashSize = 0;
		
		for(int i = 0; i < totalBuckets; i++) {
			 //HashNode head = null;
		}
		
	}
	

	  
	public Object[] getValuesList(){
	  return null;
	}

	public void printReport(){
		  
	}
	
	public V get(int index) {
		V temp; 
		
		
		
		return V;
	}
	  
	@Override
	public void add(K key, V value) {
		
		int hashIndex;
		
		ensureCapacity();
		
		hashIndex = getHashIndex(key);
		
		//HashNode head = myTable[hashIndex].get(hashIndex);
		
		if(myTable[hashIndex] != null) {
			//while()
			//HashNode node = myTable[hashIndex];
			// Find head of chain for given key
	        //int bucketIndex = getBucketIndex(key);
	        ///HashNode<K, V> head = bucketArray.get(bucketIndex);
			// This means there is a node here
			// so i need to have next point to next node
		}else {
			myTable[hashIndex] = new HashNode(key,value);
			// make first head node for this spot
		}
		/*
		if (head == null) { 	// List is empty
			HashNode tempNode = new HashNode(key,value);
			head = tempNode;
		} else {
			HashNode node = head;
			while( node.next != null) {
				node = node.next;
			}
			node.next = new HashNode(key,value);
		}
		*/
		//myTable[hashIndex].next = new HashNode(key,value);
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V lookup(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getSortedList(Object[] list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
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
		return 0;

	}
	
	
	/**
	 * creates node objects that refrence the next node object.
	 * 
	 * @author Eric
	 *
	 * @param <String>
	 * @param <V>
	 */
	public class HashNode{
		
		// Hold data 
		protected K key; 
		protected V value; 
		
		// refrence to the next node
		protected HashNode next; 
		
		// constructor
		public HashNode(K k, V v){
			key = k;
			value = v;
			next = null;
		}
	}
	
	public class Bucket{
		protected HashNode head = null;
	}

	/*
	 * // # of used buckets / # total buckets
	protected double loadFactor;
	
	// if a chain gets too big, i.e. too many collisions, resize 
table
	protected double longestChain;
	
	// # of mappings (total # elements stored) / # total buckets
	protected double densityFactor;
	 */
	
	/**
	 * total number elements stored in table / # of array buckets
	 */
	private void calculateDensity() {
		
		densityFactor = totalMappings / totalBuckets;
	}
	
	/**
	 * number of closed buckets / number of total buckets
	 */
	private void calculateLoad() {
		
		loadFactor = closedBuckets / totalBuckets;
	}
	
	@Override
	public void ensureCapacity() {
		
		calculateDensity();
		calculateLoad();
		
		// resize and rehash table is 80% full or too many 
collisions
		if((loadFactor > .7) ||					
				(densityFactor > 1.8 && loadFactor < .7) 
) { 	
			
			// create tempTable to increase size of table
			Object[] tempTable = new Object[hashSize *= 2];
			
			// go thru old table and rehash keys to new 
table
			for(int i = 0; i<hashSize/2; ++i) {
				while(myTable[i] != null) {
					
				}
			}
			/*	
				// go thru old table and rehash keys to 
new table
				for(int i = 0; i<hashSize/2; ++i) {
							
				int tempIndex = 
getHashIndex(((Entry)myTable[i]).key,hashSize);
							
				tempTable[tempIndex] = myTable[i];
				//tempTable[i] = myTable[i];
				}
						
			// myTable becomes the new array at double size
			myTable = tempTable;
			*/
			// adjust number of buckets 
			// ( totalBuckets, openBuckets, closedBuckets )
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
	private int getHashIndex(K key) {
		// getting hashIndex using additive hash function
		return add_hash(key, hashSize);
	}
	
	
	
}


