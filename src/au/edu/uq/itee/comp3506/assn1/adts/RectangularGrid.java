package au.edu.uq.itee.comp3506.assn1.adts;


/**
 * Create a two-dimensional n*n grid to store game objects.
 *
 * Memory Usage Efficiency: O(n^2), since one array with size n uses O(n) memory,
 * two-dimensional n*n grid uses O(n^2) memory.
 * 
 * Justification:
 * Using a array of arrays to construct a grid data structure is simple and easy
 * to implement. Memory usage will be large if we need to create a large grid as
 * O(n^2) memory space will be used, but placing and accessing items in the grid
 * is fast if indices are specified.
 * 
 * Limitations:
 * When dealing with very large size grid that only contains a small number of items,
 * most of cells in the grid are empty, which means a large amount of memory space is 
 * wasted as the grid uses O(n^2) memory. Besides, iterating over the grid will be 
 * time-consuming since it has n^2 cells.   
 * 
 * @author Qishi Zheng<Student No. 43759453>
 *
 * @param <T>
 */
public class RectangularGrid<T> implements Grid<T> {
	private int xBound;
	private int yBound;
	private Object[][] grid;

	/**
	 * constructor for RectangularGrid
	 * 
	 * Run-time Efficiency: O(c), c is the max number of operations executed is 8 . 
	 * 
	 * @param x
	 *            the length of X bound
	 * @param y
	 *            the height of Y bound
	 * @throws IllegalArgumentException
	 *             If x or y coordinates are 0.
	 */
	public RectangularGrid(int x, int y) {
		if (x <= 0 || y <= 0) {
			throw new IllegalArgumentException("Invalid length and height! Please enter valid lenght and height!");
		}
		xBound = x;
		yBound = y;
		grid = new Object[x][y];
	}

	/**
	 * Place an item at a fixed position in the grid. Overwrites whatever was at
	 * the position. 
	 * Run-time efficiency: O(c), c is the max number of operations executed is 11.
	 * 
	 * @param Object
	 *            Object to be placed in the grid.
	 * @param x
	 *            X Coordinate of the position of the item.
	 * @param y
	 *            Y Coordinate of the position of the item.
	 * @throws IndexOutOfBoundsException
	 *             If x or y coordinates are out of bounds.
	 */
	public void place(T t, int x, int y) {
		if (x < 0 || x > xBound - 1 || y < 0 || y > yBound - 1) {
			throw new IndexOutOfBoundsException("Failed to place the object! The x or y coordinates are out of bounds");
		}
		grid[x][y] = t;

	}

	/**
	 * Return the object at the indicated position. 
	 * Run-time efficiency: O(c), c is the max number of operations executed is 11.
	 * 
	 * @param x
	 *            X Coordinate of the position of the item.
	 * @param y
	 *            Y Coordinate of the position of the item.
	 * @return Object at this position or null.
	 * @throws ArrayIndexOutOfBoundsException
	 *             If x or y coordinates are out of bounds.
	 */
	public T get(int x, int y) {
		if (x < 0 || x > xBound - 1 || y < 0 || y > yBound - 1) {
			/*
			 * Grid Interface shows throw IndexOutOfBoundsException but in the
			 * RectangularGridTest it is ArrayIndexOutOfBoundsException.In order
			 * to pass all test, changed from IndexOutOfBoundException to
			 * ArrayIndexOutOfBoundsException
			 */
			// throw new IndexOutOfBoundsException(
			// "Failed to get the object! The x or y coordinates are out of
			// bounds");
			throw new ArrayIndexOutOfBoundsException(
					"Failed to get the object! The x or y coordinates are out of bounds");
		}
		return (T) grid[x][y];
	}

}
