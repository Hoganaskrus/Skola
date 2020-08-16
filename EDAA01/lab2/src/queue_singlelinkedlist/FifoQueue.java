package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e){
	try{
		if(size == 0){
			last = new QueueNode<E>(e);
			last.next = last;
			size++;
			return true;
		}
		size++;
		QueueNode<E> first = last.next;
		QueueNode<E > temp = new QueueNode<E>(e);
		last.next = temp;
		last = temp;
		last.next = first;
		return true;
	}catch(NullPointerException es){
		return false;
	}
		
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(size == 0){
			return null;
		}
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		try{
			
			E tempelement = last.next.element;
			last.next = last.next.next;
			size--;
			return tempelement;
		}catch(NullPointerException es){
			return null;
		}
		
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) throws IllegalArgumentException{
		if(this.equals(q)){
			throw new IllegalArgumentException();
		}
		QueueNode<E> temp = last.next;
		while(q.size != 0){
			size--;
			last.next = q.last.next;
		}
		last.next = temp;
		
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E>{
		private QueueNode<E> pos;
		private int times;
		private QueueIterator() {
			try{
				pos = last.next;
				times = 0;
			}catch(NullPointerException es){
				
			}
			
			
		}
		
		@Override
		public boolean hasNext() {
		
		if(times == size){
			return false;
		}
		if(pos != last.next || times <= 5){
			return true;
		}
			return false;
		}

		@Override
		public E next() throws NoSuchElementException {
			
				if(hasNext()){
			
				E temp = pos.element;
				pos = pos.next;
				times++;
				return temp;
				}
			
			throw new NoSuchElementException();
		}
		
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
