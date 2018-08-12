package au.edu.uq.itee.comp3506.assn1.adts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.edu.uq.itee.comp3506.assn1.gameworld.GameObject;

/* Implement your additional JUnit tests for LinkedList in this test class. */
public class MyLinkedListTest {

	private LinkedList<GameObject> list;

	/**
	 * Create an empty list for tests
	 */
	@Before
	public void createEmptyLinkedList() {
		list = new LinkedList<GameObject>();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void invalidRemove() {
		list.remove();
	}

	@Test
	public void removeOneItem() {
		GameObject item1 = new GameObject("Item 1");
		GameObject item2 = new GameObject("Item 2");
		GameObject item3 = new GameObject("Item 3");
		GameObject item4 = new GameObject("Item 4");
		list.addToEnd(item1);
		list.addToEnd(item4);
		list.insert(item3);
		list.insert(item2);

		// remove item2, and cursor should move to item3 after removing
		list.remove();
		Assert.assertFalse("The item was not removed!",
				list.find(item2));
		Assert.assertTrue(
				"After removing one item in the list, cursor should reference to one next item!",
				list.getNext().equals(item4));

	}

	@Test
	public void removeLastOneItem() {
		GameObject item1 = new GameObject("Item 1");
		GameObject item2 = new GameObject("Item 2");
		GameObject item3 = new GameObject("Item 3");
		list.addToEnd(item1);
		list.addToEnd(item2);
		list.addToEnd(item3);
		list.remove();
		Assert.assertFalse("The item was not removed!",
				list.find(item3));
		Assert.assertTrue(
				"After removing last one item in the list, cursor should reference to one previous item!",
				list.getNext().equals(item2));
		Assert.assertTrue(
				"Removed the wrong item! Only cursor referenced item should be removed!",
				list.find(item2));
		Assert.assertTrue(
				"Removed the wrong item! Only cursor referenced item should be removed!",
				list.find(item1));
	}

	@Test
	public void removeMultipleItem() {
		GameObject item1 = new GameObject("Item 1");
		GameObject item2 = new GameObject("Item 2");
		GameObject item3 = new GameObject("Item 3");
		GameObject item4 = new GameObject("Item 4");
		list.addToEnd(item1);
		list.addToEnd(item4);
		list.insert(item3);
		list.insert(item2);

		// remove item2, and cursor should move to item3 after removing
		list.remove();
		// remove item3, and cursor should move to item 4 after removing
		list.remove();

		Assert.assertFalse(
				"The first should be removed item was not removed!",
				list.find(item2));
		Assert.assertFalse(
				"The second should be removed item was not removed!",
				list.find(item3));

		Assert.assertTrue(
				"After removing item in the list, cursor should reference to one next item!",
				list.getNext().equals(item4));
	}

	@Test
	public void testGetFirst() {
		// test of empty list
		Assert.assertTrue(
				"getFirst() should return null if the list is empty!",
				list.getFirst() == null);

		GameObject item1 = new GameObject("Item 1");
		GameObject item2 = new GameObject("Item 2");
		GameObject item3 = new GameObject("Item 3");

		// test of one item list
		list.insert(item1);
		Assert.assertEquals(
				"getFirst() should return the only item in the list if there is only one item in the list.",
				item1, list.getFirst());

		// test of multiple items list
		list.addToEnd(item2);
		list.addToEnd(item3);
		Assert.assertEquals(
				"The item returned is not the first item in the list!",
				item1, list.getFirst());
	}

	@Test
	public void testGetNext() {
		// test of empty list
		Assert.assertTrue(
				"getNext() should return null if the list is empty!",
				list.getNext() == null);

		GameObject item1 = new GameObject("Item 1");
		GameObject item2 = new GameObject("Item 2");
		GameObject item3 = new GameObject("Item 3");

		// test of one item list
		list.insert(item1);
		Assert.assertEquals(
				"getNext() should return the only item in the list if there is only one item in the list.",
				item1, list.getNext());

		// test of multiple items list
		list.addToEnd(item3);
		list.insert(item2);
		Assert.assertEquals(
				"The item returned is not the next item after cursor in the list!",
				item3, list.getNext());

		Assert.assertEquals(
				"If the cursor is at the end of the list it remains at that position, and returns the item at that position.",
				item3, list.getNext());
	}

	@Test
	public void testGetLast() {
		// test of empty list
		Assert.assertTrue(
				"getLast() should return null if the list is empty!",
				list.getLast() == null);

		GameObject item1 = new GameObject("Item 1");
		GameObject item2 = new GameObject("Item 2");
		GameObject item3 = new GameObject("Item 3");

		// test of one item list
		list.insert(item1);
		Assert.assertEquals(
				"getLast() should return the only item in the list if there is only one item in the list.",
				item1, list.getLast());

		// test of multiple items list
		list.addToEnd(item2);
		list.addToEnd(item3);
		Assert.assertEquals(
				"The item returned is not the last item in the list!",
				item3, list.getLast());
	}

	@Test
	public void testGetPrevious() {
		// test of empty list
		Assert.assertTrue(
				"getPrevious() should return null if the list is empty!",
				list.getPrevious() == null);

		GameObject item1 = new GameObject("Item 1");
		GameObject item2 = new GameObject("Item 2");
		GameObject item3 = new GameObject("Item 3");

		// test of one item list
		list.insert(item1);
		Assert.assertEquals(
				"getPrevious() should return the only item in the list if there is only one item in the list.",
				item1, list.getPrevious());

		// test of multiple items list
		list.addToEnd(item2);
		list.addToEnd(item3);

		Assert.assertEquals(
				"The item returned is not the previous item after cursor in the list!",
				item2, list.getPrevious());

		Assert.assertEquals(
				"The item returned is not the previous item after cursor in the list!",
				item1, list.getPrevious());

		Assert.assertEquals(
				"If the cursor is at the first item of the list it remains at that position, and returns the item at that position.",
				item1, list.getPrevious());
	}

	@Test
	public void testFind() {
		GameObject item1 = new GameObject("Item 1");
		GameObject item2 = new GameObject("Item 2");
		GameObject item3 = new GameObject("Item 3");
		GameObject item4 = new GameObject("Item 4");

		// search in an empty list
		Assert.assertFalse(
				"find() should return false if the list is empty!",
				list.find(item1));

		// search in a list with one item
		list.addToEnd(item1);
		Assert.assertTrue(
				"Return true if the item you are trying to find is in the list!",
				list.find(item1));
		Assert.assertFalse(
				"Return false if the item you are trying to find is NOT in the list!",
				list.find(item2));

		// search in a list with multiple items
		list.addToEnd(item3);
		list.insert(item2);
		list.insert(item2);
		Assert.assertTrue(
				"Return true if the item you are trying to find is in the list!",
				list.find(item2));
		Assert.assertEquals(
				"The cursor should remain at the position where it found the first instance of the item trying to find!",
				item2, list.getNext());

		Assert.assertFalse(
				"Return false only if the item you are trying to find is NOT in the list!",
				list.find(item4));
		Assert.assertTrue(
				"If not fount, cursor should remain at the last position in the list!",
				list.isLast());
	}
	
	@Test
	public void testIsEmpty() {
		GameObject item1 = new GameObject("Item 1");
		GameObject item2 = new GameObject("Item 2");
		//test of empty list
		Assert.assertTrue("The list shoule be Empty!", list.isEmpty());
		
		//test of list with one item
		list.addToEnd(item1);
		Assert.assertFalse("The list shoule NOT be Empty!", list.isEmpty());
		list.remove();
		Assert.assertTrue("The list shoule be Empty!", list.isEmpty());
	}
	
	@Test
	public void testIsLast() {
		GameObject item1 = new GameObject("Item 1");
		GameObject item2 = new GameObject("Item 2");
		GameObject item3 = new GameObject("Item 3");
		//test of empty list
		Assert.assertFalse("isLast() should return false if the list is empty!", list.isLast());
		
		//test of list with one item
		list.addToEnd(item1);
		Assert.assertTrue("The cursor should be at the last item in the list!", list.isLast());
		
		//test of list with multiple items
		list.addToEnd(item3);
		list.insert(item2);
		Assert.assertFalse("The cursor is shoule NOT be at the last item in the list!", list.isLast());
		list.getLast();
		Assert.assertTrue("The cursor should be at the last item in the list!", list.isLast());
		
	}
	
	

}
