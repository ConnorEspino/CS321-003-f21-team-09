package cs321.search;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import cs321.btree.BTreeNode;

/**
 * Project 1
 * 
 * Implementation of Cache Interface with methods for Cache management
 * 
 * @author Connor Espino
 * @version Fall 2021
 */
public class LinkedListCache implements Cache<BTreeNode> {
	//Linked list object
	public LinkedList<BTreeNode> list;
	//Size of the cache
	int cacheSize;
	//Object to return if in list
	BTreeNode returnObject;

	/**
	 * Constructor for LinkedListCache
	 * @param cacheSize - Size of the cache
	 */
	public LinkedListCache(int cacheSize) {
		list = new LinkedList<BTreeNode>();
		this.cacheSize = cacheSize;
	}

	@Override
	public BTreeNode getObject(BTreeNode object) {
		// Remove object
		try {
			list.remove(object);
		}catch(NoSuchElementException e) {
			System.out.println("Object not in cache");
			return null;
		}
		
		//Add object to front of the cache
		addObject(object);

		return (BTreeNode) list.get(list.indexOf(object));
	}

	@Override
	public void clearCache() {
		// Clears the linked list
		list.clear();
	}

	@Override
	public void addObject(BTreeNode object) {
		// Checks if the cache is full. If it is then remove the last item.
		if (list.size() == cacheSize) {
			list.removeLast();
		}

		// Adds the object to the front of the list
		list.addFirst(object);
	}

	@Override
	public void removeObject(BTreeNode object) {
		try {
			list.remove(object);
		}catch(NoSuchElementException e) {
			System.out.println("Object not in cache");
		}
	}
}
