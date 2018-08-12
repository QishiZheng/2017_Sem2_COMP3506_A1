package au.edu.uq.itee.comp3506.assn1.adts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.edu.uq.itee.comp3506.assn1.gameworld.GameObject;

/* Implement your additional JUnit tests for RectangularGrid in this test class. */
public class MyRectangularGridTest {

	private RectangularGrid<GameObject> grid;

	/**
	 * Create a small RectangularGrid to be used for testing. The grid is 6
	 * cells in length and 9 cells in width.
	 */
	@Before
	public void setupRectangularGrid() {
		grid = new RectangularGrid<GameObject>(6, 9);
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidConstruction() {
		new RectangularGrid<GameObject>(-1, -1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void invalidGridPlacement1() {
		GameObject item = new GameObject("Item");
		grid.place(item, -1, -1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void invalidGridPlacement2() {
		GameObject item = new GameObject("Item");
		grid.place(item, 6, 9);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void invalidGridAccess1() {
		grid.get(-1, -1);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void invalidGridAccess2() {
		grid.get(6, 9);
	}

	@Test
	public void validGridAccess() {
		GameObject item1 = new GameObject("Item1");
		GameObject item2 = new GameObject("Item2");
		GameObject item1Retrieved;
		GameObject item2Retrieved;
		grid.place(item1, 4, 4);
		grid.place(item2, 5, 8);
		item1Retrieved = grid.get(4, 4);
		item2Retrieved = grid.get(5, 8);

		Assert.assertEquals("Item1 retrieved does not match item1 inserted at the same position", item1,
				item1Retrieved);
		Assert.assertEquals("Item2 retrieved does not match item1 inserted at the same position", item2,
				item2Retrieved);

	}

}
