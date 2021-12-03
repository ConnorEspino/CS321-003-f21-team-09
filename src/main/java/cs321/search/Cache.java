/**
 * Project 1
 * 
 * Cache interface
 * 
 * @author Connor Espino
 * @version Fall 2021
 */
public interface Cache<T> {

	/**
	 * Returns the next object in the list to the application and moves the object
	 * to the head of the list.
	 *
	 * @return the next object in the list.
	 * @param object - The object to search the cache for and return.
	 */
	public T getObject(T object);

	/**
	 * Adds the given object to the head of the cache.
	 * 
	 * @param object - The object to add to the cache.
	 */
	public void addObject(T object);

	/**
	 * Removes the object from the cache.
	 * 
	 * @param object - The object to remove from the cache.
	 */
	public void removeObject(T object);

	/**
	 * Removes all items from the cache.
	 */
	public void clearCache();
}
