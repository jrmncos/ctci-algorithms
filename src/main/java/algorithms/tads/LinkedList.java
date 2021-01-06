package algorithms.tads;

import java.util.HashSet;

public class LinkedList<T> {
	private Node<T> head;
	
	private static class Node<T>{
		private T value;
		private Node<T> next;
		
		public Node(T elem) { value = elem;}
		
		@Override
		public String toString() { return value.toString(); }
	}
	
	public void appendToTail(T elem) {
		head = appendToTail(head, elem);
	}
	
	private Node<T> appendToTail(Node<T> node, T elem){
		if(node == null) {
			return new Node<T>(elem);
		}
		node.next = appendToTail(node.next, elem);
		return node;
	}
	
	public boolean delete(T elem) {
		//Border case: Empty Head, or Delete Head
		Node<T> aux = new Node<T>(elem);
		aux.next = head;
		return delete(aux, elem);
	}
	
	private boolean delete(Node<T> node, T elem) {
		if(node.next == null)
			return false;
		else if(node.next.value.equals(elem)) {
			node.next = node.next.next;
			return true;
		}
		else
			return delete(node.next, elem);
	}
	
	/*
	 * Write code to remove duplicates from an unsorted LinkedList
	 * How would you solve this problem if a temporary buffer is not allowed?
	 */
	/*
	 * Approach: Traverse LinkedList with a Map helper to track the duplicates nodes
	 */
	/*
	 * Book solution: Current and runner, is O(n*2)!
	 */
	public void removeDuplicates() {
		removeDuplicates(head, new HashSet<T>());
	}
	
	private void removeDuplicates(Node<T> node, HashSet<T> elems) {
		if(node == null)
			return;
		else if(elems.contains(node.value))
			node.next = node.next.next;
		else {
			elems.add(node.value);
			removeDuplicates(node.next, elems);
		}
	}
}
