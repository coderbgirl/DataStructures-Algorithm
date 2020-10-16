package lab;

public class SortedSet {

	private SetElement root = null;

	public boolean isEmpty() {

		if (root == null)
			return true;

		return false;
	}

	public boolean add(int value) {

		SetElement currNode = root;
		SetElement prevNode = root;

		if (root == null) {
			root = new SetElement();
			root.value = value;
			return true;
		}

		while (currNode != null) {
			if (currNode.value == value) {
				return false;
			} else if (currNode.value > value) {
				prevNode = currNode;
				currNode = currNode.left;
			} else {
				prevNode = currNode;
				currNode = currNode.right;
			}
		}

		currNode = new SetElement();
		currNode.value = value;
		if (prevNode.value > value) {
			prevNode.left = currNode;
		} else
			prevNode.right = currNode;

		return true;
	}

	public boolean remove(int value) {

		if (root == null)
			return false;

		SetElement currNode = root;
		SetElement prevNode = null;
		SetElement leftChild = root.left;
		SetElement rightChild = root.right;

		while (currNode != null) {
			leftChild = currNode.left;
			rightChild = currNode.right;
			if (currNode.value == value) {
				if (currNode == root) {
					if (leftChild != null && rightChild == null) {
						root = leftChild;
					} else if (leftChild == null && rightChild != null) {
						root = rightChild;
					} else if (leftChild != null && rightChild != null) {
						root.value = findMaxLeftSubtree(currNode);
					} else {

						root.left = null;
						root.right = null;
						root = null;
					}
					return true;
				}

				if (leftChild == null && rightChild == null) {
					if (prevNode.left == currNode)
						prevNode.left = null;
					else if (prevNode.right == currNode)
						prevNode.right = null;
					else
						System.out.println("Something went terribly wrong. Need to check code!");
				} else if (leftChild != null && rightChild == null) {
					prevNode.left = leftChild;
				} else if (leftChild == null && rightChild != null) {
					prevNode.right = rightChild;
				} else {
					currNode.value = findMaxLeftSubtree(currNode);
				}

				return false;
			} else if (currNode.value > value) {
				prevNode = currNode;
				currNode = currNode.left;

			} else {
				prevNode = currNode;
				currNode = currNode.right;
			}
		}

		return false;
	}

	private int findMaxLeftSubtree(SetElement currNode) {
		SetElement prevNode = currNode;
		currNode = currNode.left;
		int ret;
		while (currNode.right != null) {
			prevNode = currNode;
			currNode = currNode.right;
		}
		ret = currNode.value;
		if (prevNode.left == currNode) {
			prevNode.left = null;
		} else
			prevNode.right = null;
		return ret;
	}

	public boolean contains(int value) {

		SetElement currNode = root;

		while (currNode != null) {
			if (currNode.value == value) {
				return true;
			} else if (currNode.value > value) {
				currNode = currNode.left;
			} else {
				currNode = currNode.right;
			}
		}
		return false;
	}
}

class SetElement {
	int value;
	SetElement left = null;
	SetElement right = null;
}
