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

public interface HashTable<K extends Comparable<K>,V> {
	
	/**
	 * adds key and value at hash index. either on linked list or 
open address 
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void add(K key, V value);
	  
	/**
	 * removes key and value at given key
	 * 
	 * @param key 
	 * @return value at key removed
	 */
	public abstract V remove(K key);
	

	/**
	 * lookup a value based on a given key
	 * 
	 * @param key 
	 * @return value 
	 */
	public abstract V lookup(K key);
	
	
	/**
	 * returns a list of only valid non null values
	 * 
	 * @return
	 */
	public abstract Object[] getValuesList();
	
	
	/**
	 * print to console load factor, longest chain, density factor, 
	 * and chaining factor
	 */
	public abstract void printReport();
	
	/**
	 * xor-shift(Rotational) hashing function 
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract int xor_hash(K key, int value);
	
	/**
	 * additive hash function uses ascii values to create hash
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract int add_hash(K key, int value);
	
	/**
	 *  sort elements in list
	 * 
	 * @param list
	 * @return sorted array
	 */
	public abstract Object[] getSortedList(Object[] list);
	
	/**
	 *  method checks if all openBuckets have been used
	 *  if they have then creates a larger array
	 *  
	 */
	public abstract void ensureCapacity();
	
	
}

