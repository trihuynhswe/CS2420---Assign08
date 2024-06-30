package assign08;

import java.util.NoSuchElementException;

/**
 * This class store a dictionary of words and use it for spell-checking
 * documents.
 * 
 * @author: Kableb Neilson and Justin Huynh
 * @version: July 5th, 2024
 */

public class BinarySearchTree<E extends Comparable<? super E>> implements SortedSet<E> {

	// Declare the variables
	private BinaryNode<E> root;
	private int size;

	// Default Constructor
	private BinarySearchTree() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * Creates a new binary node, given references to children and its default
	 * constructor.
	 * 
	 * @param data       - data to be housed in this node
	 * @param leftChild  - reference to this node's left child
	 * @param rightChild - reference to this node's right child
	 */
	private class BinaryNode<E> {
		E data;
		BinaryNode<E> leftChild;
		BinaryNode<E> rightChild;

		BinaryNode(E data) {
			this.data = data;
			this.leftChild = null;
			this.rightChild = null;
		}
	}

	/**
	 * This method checks if the items are exist or not, if not using insert helper
	 * method for adding
	 */
	@Override
	public boolean add(E item) {
		if (contains(item)) { // check if the items exist or not
			return false;
		}
		root = insert(root, item); // using insert for add the item in the tree
		size += 1;
		return true;
	}

	/**
	 * This helper method use for the add methods. Check where can insert the item
	 * inside the tree. When value of the item < node, insert to left side,
	 * otherwise, insert right. If there is no value in the tree, insert the item as
	 * a root.
	 * 
	 * @param node
	 * @param item
	 * @return node inserted
	 */
	private BinaryNode<E> insert(BinaryNode<E> node, E item) {
		if (node == null) {
			return new BinaryNode<>(item);
		}
		if (item.compareTo(node.data) < 0) {
			node.leftChild = insert(node.leftChild, item);
		}
		if (item.compareTo(node.data) > 0) {
			node.rightChild = insert(node.rightChild, item);
		}
		return node;
	}

	/**
	 * This method removes all items from this set. The set will be empty after this
	 * method call.
	 */
	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean contains(E item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(E item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArrayRange(E begin, E end) {
		// TODO Auto-generated method stub
		return null;
	}

}
