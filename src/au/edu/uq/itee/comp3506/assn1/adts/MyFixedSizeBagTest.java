package au.edu.uq.itee.comp3506.assn1.adts;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.edu.uq.itee.comp3506.assn1.gameworld.GameObject;

/* Implement your additional JUnit tests for FixedSizeBag in this test class. */
public class MyFixedSizeBagTest {
	private FixedSizeBag<GameObject> bag;

	@Before
	public void setupFixedSizeBag() {
		bag = new FixedSizeBag<GameObject>(3);
	}

	@Test (expected = IllegalArgumentException.class)
	public void invalidConstructor() {
		FixedSizeBag<GameObject> invalidBag = new FixedSizeBag<GameObject>(-1);
	}
	
	@Test
	public void removeOneItem() {
		GameObject item1 = new GameObject("Item1");
		GameObject item2 = new GameObject("Item2");

		bag.add(item1);
		Assert.assertTrue("Item was not removed!", bag.remove(item1));
		Assert.assertEquals(
				"Bag size is not 0 after removing the only one item in the bag!",
				0, bag.size());
		Assert.assertFalse(
				"Return false if the item is not in the bag!",
				bag.remove(item2));
	}

	@Test
	public void removeMultipleItem() {
		GameObject item1 = new GameObject("Item1");
		GameObject item2 = new GameObject("Item2");
		GameObject item3 = new GameObject("Item3");

		bag.add(item1);
		bag.add(item2);
		bag.add(item3);
		Assert.assertTrue("Item was not removed!", bag.remove(item1));
		Assert.assertTrue("Item was not removed!", bag.remove(item3));
		Assert.assertEquals(
				"Bag size is not 0 after removing the only one item in the bag!",
				1, bag.size());
		Assert.assertEquals(
				"The only one left item is not the first after removing all other items!",
				item2, bag.firstItem());

	}

	@Test
	public void testFirstItem() {
		GameObject item1 = new GameObject("Item1");
		GameObject item2 = new GameObject("Item2");
		Assert.assertEquals(
				"firstItem() should return null if the bag is empty!",
				null, bag.firstItem());

		bag.add(item1);
		bag.add(item2);
		Assert.assertEquals(
				"Returned item is not the first item in the bag!",
				item1, bag.firstItem());
	}

	@Test
	public void testNextItem() {
		GameObject item1 = new GameObject("Item1");
		GameObject item2 = new GameObject("Item2");
		GameObject item3 = new GameObject("Item3");
		GameObject item4 = new GameObject("Item4");
		Assert.assertEquals(
				"NextItem() should return null if the bag is empty!",
				null, bag.nextItem());

		bag.add(item1);
		bag.add(item2);
		bag.add(item3);
		bag.add(item4);
		// move cursor to the first item
		bag.firstItem();
		Assert.assertEquals("Returned item is not the next item!",
				item2, bag.nextItem());
		Assert.assertEquals("Returned item is not the next item!",
				item3, bag.nextItem());
		Assert.assertEquals("Returned item is not the next item!",
				null, bag.nextItem());
	}

	@Test
	public void testIsLast() {
		GameObject item1 = new GameObject("Item1");
		GameObject item2 = new GameObject("Item2");
		GameObject item3 = new GameObject("Item3");

		bag.add(item1);
		bag.add(item2);
		bag.add(item3);
		bag.firstItem();
		bag.nextItem();
		bag.nextItem();
		Assert.assertTrue(
				"The internal cursor is NOT at the last item in the bag",
				bag.isLast());
	}
	
	@Test
	public void testSize() {
		GameObject item1 = new GameObject("Item1");
		GameObject item2 = new GameObject("Item2");
		GameObject item3 = new GameObject("Item3");
		
		Assert.assertEquals("The size of an empty bag should be 0 !", 0, bag.size());
		
		bag.add(item1);
		bag.add(item2);
		bag.add(item3);
		Assert.assertEquals("The size of bag does not match items in the bag!", 3, bag.size());
		
		bag.remove(item2);
		Assert.assertEquals("The size of bag does not match items in the bag!", 2, bag.size());
	}

}
