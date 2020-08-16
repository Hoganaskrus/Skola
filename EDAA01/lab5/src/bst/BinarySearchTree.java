package bst;

import bst.BinarySearchTree.BinaryNode;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;
	// Som sagt tvek på denna, läs på metoden setalarmon
	private boolean alarm;

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(3);
		bst.add(7);
		bst.add(23);
		bst.add(2);
		bst.add(17);
		bst.add(10);
		bst.add(8);
		bst.add(9);
		Integer[] ir = new Integer[8];

		
		bst.rebuild();
		bst.printTree();
	}

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		size = 0;
		root = null;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		root = addhelp(root, x);

		// Se hjälpmetoden e tvek ifall man får
		if (alarm) {
			SetAlarmoff();
			return false;
		} else
			return true;
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return findhight(root);
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {

		inOrderprint(root);

	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size];
		int k = toArray(root, a, 0);
		root = buildTree(a, 0, k-1);

	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1
	 * (the first empty position in a).
	 */

	
	//Vi vill gå längsvänster till pos.left = null. Sätt in pos, sedan pos.right ifall den finns;
	//Sedan måste vi hitta vart,aaaa har vi inte höjden nvm; vi måste finna vilket index som passar till vilket.
	//Asså jag att hade bara tur med denna, men vi vill ju hålla öka index med ett varje gång den sätts in så det är väl på detta sättet det händer??
	
	
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		
		if(n != null){
	
		index = toArray(n.left,a,index);
		a[index] = n.element;
		index = toArray(n.right,a,index+1);
		
		return index;
	
		}
		return index;

	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in
	 * the array a are assumed to be in ascending order. Returns the root of
	 * tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if(first > last){
			
			//Stop
			return null;
		}
		
		//Standard här, vi ändrar last resp first så [1,5,6,8,9], blir left [1,5] resp [8,9] sen [1] resp [9]
		int mid = (first + last) / 2;
		BinaryNode<E> pos = new BinaryNode<E>(a[mid]);
		pos.left = buildTree(a,first,mid-1);
		pos.right = buildTree(a,mid+1,last);

		return pos;

	}

	// kolla om trädet är tumt, om då är height 0. Annars så rekursivt kollar
	// hur långt trädet är ifall man går längs vänster samt längs höger.
	// Tror det är det endaste som krävs då höjden kommer bestämas av antingen
	// det största elementet lr lägsta.
	private int findhight(BinaryNode<E> pos) {

		int height;
		if (root == null) {
			height = 0;
		} else {
			int heightright;
			int heightleft;
			if (pos.left == null) {
				heightleft = 1;
			} else {
				heightleft = 1 + findhight(pos.left);
			}
			if (pos.right == null) {
				heightright = 1;
			} else {

				heightright = 1 + findhight(pos.right);
			}
			height = Math.max(heightright, heightleft);
		}

		return height;

	}

	// Rekursivt kollar vart x ska sättas in och returnera hela trädet (skulle
	// kanske ändra dettta).
	// Ifall pos == null så är noden tom och där sätts den, annars jämförs
	// elementen och ser ifall den är större/mindre. Om den är lika sä sätt
	// alarmet på och returnera trädet som det e.
	// Ifall det inte är det så går det neråt i trädet, antingen höger lr
	// vänster beroende på comparevärdet.
	private BinaryNode<E> addhelp(BinaryNode<E> pos, E x) {

		if (pos == null) {
			pos = new BinaryNode<E>(x);
			size++;
			return pos;
		}
		int compare = pos.element.compareTo(x);

		if (compare == 0) {
			SetAlarmon();
			return pos;
		} else if (compare > 0) {
			pos.left = addhelp(pos.left, x);
			return pos;
		} else {
			pos.right = addhelp(pos.right, x);
			return pos;
		}

	}

	// Vet i fan ifall man får göra detta men kom inte på ngt annat, får ev.
	// hitta ett lättare sätt utan att ändra hela koden.
	private void SetAlarmon() {
		alarm = true;
	}

	private void SetAlarmoff() {
		alarm = false;
	}

	// 1. Börja på root. 2. Sålänge pos.left != null gå ner i trädet. 3. Då
	// pos.left == null är vi på lägsta noden printa pos. Koll om pos.right !=
	// null då printa den.
	// 4. Gör nu samma men med parenten
	private void inOrderprint(BinaryNode<E> bn) {
		if (bn != null) {
			inOrderprint(bn.left);
			System.out.println(bn.element);
			inOrderprint(bn.right);
		}

	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
