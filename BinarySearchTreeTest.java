package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

	BinarySearchTree<Integer> bst;
	
	@BeforeEach
	void setUp() throws Exception {
		bst = new BinarySearchTree<>();
		bst.add(50);
		bst.add(25);
		bst.add(75);
		bst.add(13);
		bst.add(18);
		bst.add(48);
		bst.add(38);
		bst.add(3);
		bst.add(63);
		bst.add(93);
		
	}


	@Test
	void testAddAndContains() {	
		bst.add(73);
		assertTrue(bst.contains(73));
	}

	@Test
	void testClear() {
		bst.clear();
		assertEquals(0,bst.size());
	}

	@Test
	void testFirst() {
		assertEquals(3,bst.first());
	}
	
	@Test
	void testFirstNoSuchElement() throws NoSuchElementException{
		bst.clear();
		NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () -> {
			bst.first();
		});
		assertEquals(thrown.getMessage(), "This Binary Search Tree is Empty.");
	}

	@Test
	void testIsEmpty() {
		bst.clear();
		assertTrue(bst.isEmpty());
	}

	@Test
	void testLast() {
		assertEquals(93,bst.last());
	}
	
	@Test
	void testLasttNoSuchElement() throws NoSuchElementException{
		bst.clear();
		NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () -> {
			bst.last();
		});
		assertEquals(thrown.getMessage(), "This Binary Search Tree is Empty.");
	}

	@Test
	void testRemove() {
		bst.remove(75);
		assertFalse(bst.contains(75));
	}
	
	@Test
	void testRemoveRoot() {
		bst.remove(50);
		assertEquals(bst.size(), 9);
		assertFalse(bst.contains(50));
		
	}
	
	@Test
	void testRemoveLeaf() {
		bst.remove(3);
		assertEquals(bst.size(), 9);
		assertFalse(bst.contains(3));
		
	}

	@Test
	void testSize() {
		assertEquals(10,bst.size());
		bst.remove(18);
		assertEquals(9,bst.size());
		bst.add(59);
		assertEquals(10,bst.size());
	}

	@Test
	void testToArray() {
		Object[] test = {3, 13, 18, 25, 38, 48, 50, 63, 75, 93};
		assertTrue(Arrays.equals(test, bst.toArray()));
	}

	@Test
	void testToArrayRange() {
		Object[] test = {38, 48, 50, 63, 75};
		Object[] test2 = bst.toArrayRange(38, 75);
		assertTrue(Arrays.equals(test, test2));
	}
	
	@Test
	void testToArrayRangeOneElement() {
		Object[] test = {38};
		Object[] test2 = bst.toArrayRange(38, 38);
		assertTrue(Arrays.equals(test, test2));
	}
	
	

}
