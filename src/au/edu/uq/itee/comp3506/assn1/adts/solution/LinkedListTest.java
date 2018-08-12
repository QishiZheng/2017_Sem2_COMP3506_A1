package au.edu.uq.itee.comp3506.assn1.adts.solution;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import au.edu.uq.itee.comp3506.assn1.gameworld.GameObject;
import au.edu.uq.itee.comp3506.assn1.adts.LinkedList;

/**
 * Tests for LinkedList implemented by Lachlan Healey.
 * 
 * @author leggy <l.healey@uq.edu.au>
 */
public class LinkedListTest {
	private LinkedList<GameObject> list;

	private GameObject item1, item2, item3;

	@Before
	public void prepareList() {
		list = new LinkedList<GameObject>();
		item1 = new GameObject("Item 1");
		item2 = new GameObject("Item 2");
		item3 = new GameObject("Item 3");

	}

	@Test
	public void newListIsEmpty() {
		assertThat("A newly created list is not empty.", list.isEmpty(), is(equalTo(true)));
		assertEquals("Empty lists should not have the cursor at the end.", false, list.isLast());
	}

	@Test
	public void addOneItem() {
		list.addToEnd(item1);
		assertThat("Adding an item resulted in an empty list.", list.isEmpty(), is(equalTo(false)));
		assertThat("Adding one item did not result in it being the last item.", list.isLast(), is(equalTo(true)));
		assertThat("The only item added to the list is not the first item in the list.", list.getFirst(),
				is(equalTo(item1)));
		assertThat("The only item added to the list is not the last item in the list.", list.getLast(),
				is(equalTo(item1)));
	}

	@Test
	public void insertionTest() {
		list.insert(item1);
		assertThat("Inserting an item resulted in an empty list.", list.isEmpty(), is(equalTo(false)));
		assertThat("Inserting one item did not result in it being the last item.", list.isLast(), is(equalTo(true)));
		assertThat("The only item inserted into the list is not the first item in the list.", list.getFirst(),
				is(equalTo(item1)));
		assertThat("The only item inserted into the list is not the last item in the list.", list.getLast(),
				is(equalTo(item1)));

		list.insert(item2);
		assertThat(
				"Inserting multiple items into an empty list resulted in the cursor refering to the end of the list.",
				list.isLast(), is(equalTo(false)));
		assertThat("The last item inserted into an empty list is not the first item in the list.", list.getFirst(),
				is(equalTo(item2)));

		// Setting cursor to the end of the list.
		list.getLast();

		list.insert(item3);

		// List should now be [Item 2, Item 3, Item 1]
		// Cursor should be at Item 3.

		assertThat(
				"Inserting new item into a list with two items (cursor at last) resulted in the cursor referring to the list item.",
				list.isLast(), is(equalTo(false)));
		assertThat(
				"Inserting new item into a list with two items (cursor at last) resulted in the new item in the first position.",
				list.getFirst(), not(equalTo(item3)));
		assertThat(
				"Inserting new item into a list with two items (cursor at last) resulted in the new item in the last position.",
				list.getLast(), not(equalTo(item3)));

		list.getLast();
		assertThat(
				"Inserting new item into a list with two items (cursor at last) resulted in that item not being in the middle of the two elements.",
				list.getPrevious(), is(equalTo(item3)));

	}

	/**
	 * Requires insertion to work. See above. Tests getFirst, getLast, getNext and
	 * getPrev.
	 */
	@Test
	public void getTest() {
		LinkedList<GameObject> list = new LinkedList<GameObject>();
		assertThat("First item in empty list is not null", list.getFirst(), is(equalTo(null)));
		assertThat("Last item in empty list is not null", list.getLast(), is(equalTo(null)));
		assertThat("Next item in empty list is not null", list.getNext(), is(equalTo(null)));
		assertThat("Previous item in empty list is not null", list.getPrevious(), is(equalTo(null)));

		list.insert(item2);
		list.insert(item1);
		// List is now [Item 1, Item 2]
		assertThat("Last element inserted is not first.", list.getFirst(), is(equalTo(item1)));
		assertThat("First element inserted is not last.", list.getLast(), is(equalTo(item2)));

		// getNext Testing
		list.getFirst();
		assertThat("Next element (cursor at start) is not last element in list.", list.getNext(),
				is(equalTo(list.getLast())));
		list.getLast();
		assertThat("Next element (cursor at end) is not last element in list.", list.getNext(),
				is(equalTo(list.getLast())));

		// getPrevious Testing
		list.getFirst();
		assertThat("Previous element (cursor at start) is not first element in list.", list.getPrevious(),
				is(equalTo(list.getFirst())));
		list.getLast();
		assertThat("Previous element (cursor at end) is not first element in list.", list.getPrevious(),
				is(equalTo(list.getFirst())));
	}

	@Test
	public void findTest() {
		LinkedList<GameObject> list = new LinkedList<GameObject>();

		assertThat("Empty list contains an element.", list.find(item1), is(equalTo(false)));

		list.addToEnd(item1);

		assertThat("List containing an element purports not to.", list.find(item1), is(equalTo(true)));
		assertThat("Cursor is not at first after successful find(..) invocation.", list.getPrevious(),
				is(equalTo(list.getFirst())));

		list.addToEnd(item2);
		assertThat("List containing an element purports not to.", list.find(item2), is(equalTo(true)));
		assertThat("Cursor is not at last element after successful find(..) invocation.", list.isLast(),
				is(equalTo(true)));

		assertThat("List does not contain element, yet claims to.", list.find(item3), is(equalTo(false)));

		// List should be [Item 1, Item 2]
		// Cursor will be at Item 2.

		assertThat("Cursor is not at last element after unsuccessful find(..) invocation.", list.isLast(),
				is(equalTo(true)));

	}

	@Test
	public void addTwoItems() {
		list.addToEnd(item1);
		list.addToEnd(item2);
		assertThat("Adding multiple items to end of list did not result in the last one being the last item.",
				list.isLast(), is(equalTo(true)));
		assertThat("The first item added to the list is not the first item in the list.", list.getFirst(),
				is(equalTo(item1)));
		assertThat("The last item added to the list is not the last item in the list.", list.getLast(),
				is(equalTo(item2)));
	}

	@Test
	public void insertTwoItems() {
		list.insert(item1);
		list.insert(item2);
		assertThat(
				"Inserting multiple items into an empty list resulted in the cursor refering to the end of the list.",
				list.isLast(), is(equalTo(false)));
		assertThat("The first item inserted into an empty list is not the last item in the list.", list.getLast(),
				is(equalTo(item1)));
		assertThat("The last item inserted into an empty list is not the first item in the list.", list.getFirst(),
				is(equalTo(item2)));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeFromEmptyList() {
		list.remove();
	}

	@Test
	public void removeOnlyItem() {
		list.addToEnd(item1);
		assertEquals("First item was not equals to last item.", list.getLast(), list.getFirst());

		// Moves cursor to first item, which is also the only item.
		list.getFirst();
		list.remove();

		assertEquals("List was not empty.", true, list.isEmpty());
	}

	@Test
	public void removeHead() {
		list.addToEnd(item1);
		list.addToEnd(item2);
		assertEquals("First item was not equal to item1.", item1, list.getFirst());
		assertEquals("Last item was not equal to item2.", item2, list.getLast());

		// Moves cursor to first item
		list.getFirst();
		list.remove();

		// Item 2 should now be head and tail
		assertEquals("Last item was not equal to item2.", item2, list.getFirst());
	}

	@Test
	public void removeTail() {
		list.addToEnd(item1);
		list.addToEnd(item2);
		assertEquals("First item was not equal to item1.", item1, list.getFirst());
		assertEquals("Last item was not equal to item2.", item2, list.getLast());

		// Moves cursor to last item
		list.getLast();
		list.remove();

		// Item 1 should now be head and tail
		assertEquals("First item was not equal to item1.", item1, list.getFirst());
		assertEquals("Last item was not equal to item1.", item1, list.getLast());

	}

	@Test
	public void removeCenter() {
		list.addToEnd(item1);
		list.addToEnd(item2);
		list.addToEnd(item3);
		assertEquals("First item was not equal to item1.", item1, list.getFirst());
		assertEquals("Last item was not equal to item3.", item3, list.getLast());

		// Moves cursor to first item, and then the next (middle) item
		list.getFirst();
		list.getNext();
		list.remove();

		// Item1 should be head, Item3 should be tail, and Item2 should not be in the
		// list.
		assertEquals("First item was not equal to item1.", item1, list.getFirst());
		assertEquals("Last item was not equal to item3.", item3, list.getLast());

		assertEquals("Item2 should not be in the list after being removed.", false, list.find(item2));
	}

	@Test
	public void iteration() {
		list.addToEnd(item1);
		list.addToEnd(item2);
		list.addToEnd(item3);

		assertEquals("First item in list should be item1.", item1, list.getFirst());
		assertEquals("Second item in list should be item2.", item2, list.getNext());
		assertEquals("Third item in list should be item3.", item3, list.getNext());
		assertEquals("Third item in list should be the last item.", item3, list.getLast());

		list.getFirst();
		do {
			list.getNext();
		} while (!list.isLast());
		assertEquals("Cursor should be at last item.", item3, list.getLast());

	}
}
