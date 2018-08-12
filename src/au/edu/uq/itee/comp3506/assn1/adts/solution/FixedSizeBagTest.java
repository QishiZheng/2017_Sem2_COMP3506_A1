package au.edu.uq.itee.comp3506.assn1.adts.solution;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import au.edu.uq.itee.comp3506.assn1.gameworld.GameObject;
import au.edu.uq.itee.comp3506.assn1.adts.FixedSizeBag;

/**
 * Tests for FixedSizeBag implemented by Lachlan Healey.
 * 
 * @author leggy <l.healey@uq.edu.au>
 */
public class FixedSizeBagTest {
	private FixedSizeBag<GameObject> bag;

	private GameObject item1, item2, item3, item4, item5, item6, item7;

	@Before
	public void setupFixedSizeBag() {
		bag = new FixedSizeBag<GameObject>(3);
		item1 = new GameObject("Item 1 to be Added");
		item2 = new GameObject("Item 2 to be Added");
		item3 = new GameObject("Item 3 to be Added");
		item4 = new GameObject("Item 4 to be Added");
		item5 = new GameObject("Item 5 to be Added");
		item6 = new GameObject("Item 6 to be Added");
		item7 = new GameObject("Item 7 to be Added");
	}

	@Test
	public void newBagIsEmpty() {
		assertThat("A newly created bag does not have a size of 0.", bag.size(), is(equalTo(0)));
		assertEquals("First item bag should return null", null, bag.firstItem());

	}

	/*
	 * Can't be too picky with exceptions here (as its not in the specification),
	 * although an exception of some description ought to be thrown.
	 */
	@Test(expected = Exception.class)
	public void negativeSizeBag() {
		FixedSizeBag<GameObject> negativeBag = new FixedSizeBag<GameObject>(-10);

	}

	@Test
	public void addOneItem() {
		assertThat("Item not successfully added to an empty bag.", bag.add(item1), is(equalTo(true)));
		assertThat("Bag size is not 1 after adding 1 item.", bag.size(), is(equalTo(1)));
		assertThat("The only item added to the bag is not the first item in the bag.", bag.firstItem(),
				is(equalTo(item1)));
	}

	@Test
	public void addMultipleItems() {
		assertEquals("Adding item to empty bag wasn't successful", true, bag.add(item1));
		assertEquals("Bag size is not 1 after adding 1 items.", 1, bag.size());

		assertEquals("Adding item to bag with two free spaces wasn't successful", true, bag.add(item2));
		assertEquals("Bag size is not 2 after adding 2 items.", 2, bag.size());

		assertEquals("Adding item to bag with one free space wasn't successful", true, bag.add(item3));
		assertEquals("Bag size is not 3 after adding 3 items.", 3, bag.size());

		assertEquals("Adding item to full bag was successful", false, bag.add(item4));
		assertEquals("Bag size is not 3 after adding 3 items successfully.", 3, bag.size());
	}

	@Test
	public void addAndRemoveMultipleItems() {
		bag.add(item1);
		bag.add(item2);
		bag.add(item3);

		assertEquals("Bag size is not 3 after adding 3 items.", 3, bag.size());

		assertEquals("Removing item not in list succeeds.", false, bag.remove(item4));

		assertEquals("Removing item1 did not succeed.", true, bag.remove(item1));
		assertEquals("Bag size is not 2 after adding 3 items and removing 1.", 2, bag.size());

		assertEquals("Removing item2 did not succeed.", true, bag.remove(item2));
		assertEquals("Bag size is not 1 after adding 3 items and removing 2.", 1, bag.size());

		assertEquals("Removing item2 did not succeed.", true, bag.remove(item3));
		assertEquals("Bag size is not 0 after adding 3 items and removing 3.", 0, bag.size());

	}

	@Test
	public void overFillBag() {
		bag.add(item1);
		bag.add(item2);
		bag.add(item3);
		assertThat("Added more items than maximum size of bag.", bag.add(item4), is(equalTo(false)));
		assertThat("Bag size is not 3 after over filling a bag with a maximum size of 3.", bag.size(), is(equalTo(3)));
	}

//	@Test
//	public void iterating() {
//		FixedSizeBag<GameObject> bigBag = new FixedSizeBag<GameObject>(10);
//
//		bigBag.add(item1);
//		bigBag.add(item2);
//		bigBag.add(item3);
//		bigBag.add(item4);
//		bigBag.add(item5);
//		bigBag.add(item6);
//		bigBag.add(item7);
//
//		Set<GameObject> objects = new HashSet<GameObject>();
//		GameObject item;
//
//		objects.add(bigBag.firstItem());
//
//		while ((item = bigBag.nextItem()) != null) {
//			objects.add(item);
//		}
//
//		assertEquals("There are not 7 items in the bag.", 7, objects.size());
//
//		assertEquals("Item1 is not in the set after iteration.", true, objects.contains(item1));
//		assertEquals("Item2 is not in the set after iteration.", true, objects.contains(item2));
//		assertEquals("Item3 is not in the set after iteration.", true, objects.contains(item3));
//		assertEquals("Item4 is not in the set after iteration.", true, objects.contains(item4));
//		assertEquals("Item5 is not in the set after iteration.", true, objects.contains(item5));
//		assertEquals("Item6 is not in the set after iteration.", true, objects.contains(item6));
//		assertEquals("Item7 is not in the set after iteration.", true, objects.contains(item7));
//
//		objects.clear();
//
//		bigBag.remove(item1);
//		bigBag.remove(item3);
//		bigBag.remove(item5);
//		bigBag.remove(item7);
//
//		objects.add(bigBag.firstItem());
//		do {
//			item = bigBag.nextItem();
//			objects.add(item);
//		} while (!bigBag.isLast());
//
//		assertEquals("There are not 3 items in the bag.", 3, objects.size());
//
//		assertEquals("Item2 is not in the set after iteration.", true, objects.contains(item2));
//		assertEquals("Item4 is not in the set after iteration.", true, objects.contains(item4));
//		assertEquals("Item6 is not in the set after iteration.", true, objects.contains(item6));
//
//	}
}
