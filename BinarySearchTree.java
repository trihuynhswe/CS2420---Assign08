package assign08;

import java.util.NoSuchElementException;

/**
 * BinarySearchTree is a generic implementation of a binary search tree (BST)
 * that implements the SortedSet interface. The tree stores elements of a type
 * that extends Comparable, allowing for efficient storage, retrieval, and
 * removal of elements in sorted order.
 * 
 * @param <Type> the type of the elements in this tree, which must be comparable
 *               to itself.
 * @author: Kableb Neilson and Justin Huynh
 * @version: July 5th, 2024
 */

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	// Declare the variables
	private BinaryNode<Type> root;
	private int size;

	// Default Constructor initializes an empty BST.
	private BinarySearchTree() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * BinaryNode represents a node in the BST.
	 * 
	 * @param <Type>     the type of element stored in the node
	 * @param data       - data to be housed in this node
	 * @param leftChild  - reference to this node's left child
	 * @param rightChild - reference to this node's right child
	 */
	private class BinaryNode<Type> {
		Type data;
		BinaryNode<Type> leftChild;
		BinaryNode<Type> rightChild;

		/**
		 * Constructs a BinaryNode with the specified data and null children.
		 * 
		 * @param data the data to be stored in the node
		 */
		BinaryNode(Type data) {
			this.data = data;
			this.leftChild = null;
			this.rightChild = null;
		}
	}

	/**
	 * Adds the specified element to the BST if it is not already present.
	 * 
	 * @param item the element to be added
	 * @return true if the element was added, false if it was already present
	 */
	@Override
	public boolean add(Type item) {
		if (contains(item)) { // check if the items exist or not
			return false;
		}
		root = insert(root, item); // using insert for add the item in the tree
		size += 1;
		return true;
	}

	/**
	 * Recursively inserts the specified element into the BST.
	 * 
	 * @param node - the root of the subtree into which the element is to be
	 *             inserted
	 * @param item - the element to be inserted
	 * @return the new root of the subtree
	 */
	private BinaryNode<Type> insert(BinaryNode<Type> node, Type item) {

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

	/**
	 * Checks if the BST contains the specified element.
	 * 
	 * @param item the element to be checked
	 * @return true if the element is present, false otherwise
	 */
	@Override
	public boolean contains(Type item) {
		return containHelper(root, item);
	}

	/**
	 * Recursively checks if the BST contains the specified element.
	 * 
	 * @param node the root of the subtree to be checked
	 * @param item the element to be checked
	 * @return true if the element is present, false otherwise
	 */
	private boolean containHelper(BinaryNode<Type> node, Type item) {
		if (node == null) {
			return false;
		}
		if (item == node.data) {
			return true;
		} else if (item.compareTo(node.data) < 0) {
			return containHelper(node.leftChild, item);
		} else if (item.compareTo(node.data) > 0) {
			return containHelper(node.rightChild, item);
		} else {
			return true;
		}
	}

	/**
	 * Returns the smallest element in the BST.
	 * 
	 * @return the smallest element in the BST
	 * @throws NoSuchElementException if the BST is empty
	 */
	@Override
	public Type first() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return findMin(root).data;
	}

	/**
	 * Checks if the BST is empty.
	 * 
	 * @return true if the BST is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (root == null && size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the largest element in the BST.
	 * 
	 * @return the largest element in the BST
	 * @throws NoSuchElementException if the BST is empty
	 */
	@Override
	public Type last() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return findMax(root).data;
	}

	/**
	 * Removes the specified element from the BST if it is present.
	 * 
	 * @param item the element to be removed
	 * @return true if the element was removed, false if it was not present
	 */
	@Override
	public boolean remove(Type item) {
		if (!contains(item)) {
			return false;
		}
		root = removeHelper(root, item);
		size--;
		return true;
	}

	/**
	 * Recursively removes the specified element from the BST.
	 * 
	 * @param node the root of the subtree from which the element is to be removed
	 * @param item the element to be removed
	 * @return the new root of the subtree
	 */
	private BinaryNode<Type> removeHelper(BinaryNode<Type> node, Type item) {
		if (node == null) {
			return null;
		}
		if (item.compareTo(node.data) < 0) {
			node.leftChild = removeHelper(node.leftChild, item);
		} else if (item.compareTo(node.data) > 0) {
			node.rightChild = removeHelper(node.rightChild, item);
		} else {
			if (node.leftChild == null) {
				return node.rightChild;
			} else if (node.rightChild == null) {
				return node.leftChild;
			} else {
				BinaryNode<Type> minLargeNode = findMin(node.rightChild);
				node.data = minLargeNode.data;
				node.rightChild = removeHelper(node.rightChild, minLargeNode.data);
			}
		}
		return node;
	}

	/**
	 * Finds the node with the smallest value in the subtree rooted at the specified
	 * node.
	 * 
	 * @param node the root of the subtree
	 * @return the node with the smallest value in the subtree
	 */
	private BinaryNode<Type> findMin(BinaryNode<Type> node) {
		while (node.leftChild != null) {
			node = node.leftChild;
		}
		return node;
	}

	/**
	 * Finds the node with the largest value in the subtree rooted at the specified
	 * node.
	 * 
	 * @param node the root of the subtree
	 * @return the node with the largest value in the subtree
	 */
	private BinaryNode<Type> findMax(BinaryNode<Type> node) {
		while (node.rightChild != null) {
			node = node.rightChild;
		}
		return node;
	}

	/**
	 * Returns the number of elements in the BST.
	 * 
	 * @return the number of elements in the BST
	 */
	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArrayRange(Type begin, Type end) {
		// TODO Auto-generated method stub
		return null;
	}

}
