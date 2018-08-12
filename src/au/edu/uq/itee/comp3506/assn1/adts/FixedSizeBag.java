package au.edu.uq.itee.comp3506.assn1.adts;


/**
 * 
 * Implementation of FixedSizeBag using array.
 * 
 * Memory Usage Efficiency: O(n), it takes n space on memory to construct a
 * size n array for FixedSIzeBag.
 * 
 * Justification: The FixedSizeBag is implemented by using array. Using array to 
 * implement the ADT makes advantage of index of array, which makes it easier to implement the methods
 * specified. Besides, iteration is more run-time efficient with indices in the array. 
 * 
 * 
 * @author Qishi Zheng<Student No. 43759453>
 *
 * @param <T>
 */
public class FixedSizeBag<T> implements RemovableBag<T> {
	private T[] bagArray;
	private int cursor;
	private int numberOfItem;

	/**
	 * Create a FixedSizeBag with contents set to null.
	 * 
	 * Run-time Efficiency: O(c), where c is constant as the max number of 
	 * operations executed is 3.
	 * 
	 * 
	 * @param size
	 *            Maximum number of items that can be contained in the bag.
	 */
	public FixedSizeBag(int size) {
		/*
		 * Due to type erasure
		 * (https://docs.oracle.com/javase/tutorial/java/generics/erasure.html)
		 * the dynamically allocated array has to be created as an array of
		 * Object references. For type safety this array of Object references is
		 * then cast to be an array of references to the generic type <T> of the
		 * actual elements to be held in bagArray.
		 */
		if (size < 0 ) {
			throw new IllegalArgumentException("Size of array cannot be negative!");
		}
		bagArray = (T[]) new Object[size];
		numberOfItem = 0;
	}

	/**
	 * Add an item to the bag.
	 * 
	 * Run-time Efficiency: O(n), this method iterates over bagArray to add items
	 * 
	 * 
	 * @param item
	 *            The item to be added.
	 * @return true if item is added to the bag; false if can't be added.
	 */
	@Override
	public boolean add(T item) {
		// the value indicates if the item is added
		boolean added = false;

		// add the item to bagArray
		for (int i = 0; i < bagArray.length; i++) {
			if (bagArray[i] == null) {
				bagArray[i] = item;
				added = true;
				this.numberOfItem++;
				break;
			}
		}
		return added;

	}

	
	/**
	 * Remove the item from the bag. Searches from the current cursor position
	 * and removes the first occurrence of {@code item} found in the bag.
	 * 
	 * Run-time Efficiency: O(n), this method iterates over the bagArray to remove an item.
	 * 
	 * 
	 * @param item
	 *            The item to be removed.
	 * @return true if item is removed from the bag; false if item was not in
	 *         bag.
	 */
	@Override
	public boolean remove(T item) {
		boolean removed = false;
		for (int i = cursor; i < bagArray.length; i++) {
			if (bagArray[i] == item) {
				bagArray[i] = null;
				removed = true;
				this.numberOfItem--;
				break;
			}
		}
		return removed;
	}

	/**
	 * Set the internal cursor to refer to the first item in the bag.
	 * 
	 * Run-time Efficiency: O(n), this method iterates over the bagArray to find
	 * the first item stored>
	 * 
	 * 
	 * @return The first item or null if bag is empty.
	 */
	@Override
	public T firstItem() {
		if (size() == 0) {
			return null;
		} else {
			for (int i = 0; i < bagArray.length; i++) {
				if (bagArray[i] != null) {
					cursor = i;
					break;
				}
			}
		}
		return (T)bagArray[cursor];
	}

	/**
	 * Move the internal cursor to the next item in the bag. If the internal
	 * cursor refers to the last item, do not move the cursor and return
	 * {@code null}.
	 * 
	 * Run-time Efficiency: O(n), this method iterates over the bagArray to find
	 * the next item from the cursor.
	 * 
	 * @return The next item or null if there is no next item.
	 */
	@Override
	public T nextItem() {
		if (isLast() || size() == 0) {
			return null;
		} 
		
		for (int i = cursor + 1; i< bagArray.length; i++) {
			if (bagArray[i] != null) {
				cursor = i;
				break;
			}
		}
		return bagArray[cursor];
	}

	/**
	 * Indicate if the cursor is at the last item in the bag.
	 * 
	 * Run-time Efficiency: O(n), this method iterates over the bagArray to find
	 * if the cursor is at the last item in the bagArray.
	 * 
	 * @return true if the internal cursor is at the last item in the bag; false
	 *         otherwise.
	 */
	@Override
	public boolean isLast() {
		boolean result = false;
		if (cursor == bagArray.length - 1) {
			result = true;
		} else {
			for (int i = cursor+1; i < bagArray.length;i++) {
				if (bagArray[i] != null) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Indicate the number of items in the bag
	 *
	 * Run-time Efficiency: O(c), where c is constant as the max number of 
	 * operations executed is 1
	 * 
	 * @return The number of items in the bag.
	 */
	@Override
	public int size() {
		return this.numberOfItem;
	}
}
