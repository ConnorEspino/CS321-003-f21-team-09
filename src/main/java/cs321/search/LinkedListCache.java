package cs321.search;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Project 1
 * 
 * Implementation of Cache Interface with methods for Cache management
 * 
 * @author Connor Espino
 * @version Fall 2021
 */
public class LinkedListCache<T> implements Cache<T> {
	//Linked list object
	public LinkedList<T> list;
	//Size of the cache
	int cacheSize;
	//Object to return if in list
	T returnObject;

	/**
	 * Constructor for LinkedListCache
	 * @param cacheSize - Size of the cache
	 */
	public LinkedListCache(int cacheSize) {
		list = new LinkedList<T>();
		this.cacheSize = cacheSize;
	}

	@Override
	public T getObject(T object) {
		// Return element if it is in the cache.
		returnObject = (T) list.get(list.indexOf(object));
		return returnObject;
	}

	@Override
	public void addObject(T object) {
		// Checks if the cache is full. If it is then remove the last item.
		if (list.size() == cacheSize) {
			list.removeLast();
		}

		// Adds the object to the front of the list
		list.addFirst(object);
	}

	@Override
	public void removeObject(T object) {
		try {
			list.remove(object);
		}catch(NoSuchElementException e) {
			System.out.println("Object not in cache");
		}
	}

	@Override
	public void clearCache() {
		// Clears the linked list
		list.clear();
	}

}
