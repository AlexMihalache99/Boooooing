
/**
 * 
 * A class that implements a queue.  It is your job to complete this class.  Your queue
 * will use a linked list constructed by QueueElements.  However, your queue must be general and allow
 * setting of any type of Object.  Also you cannot use ArrayLists or arrays (you will get zero).  
 * @author Alexandru Mihalache
 *
 */

import java.util.NoSuchElementException;

public class Queue<T> {

	// TODO: You need some data to store the queue. Put the attributes here.
	
	//the head and tail of the queue
	private QueueElement<T> front;
	private QueueElement<T> rear;

	/**
	 * Constructs an empty Queue.
	 */
	public Queue() {
		// TODO: Write the Queue constructor

		this.front = null;
		this.rear = null;

	}

	/**
	 * Returns true if the queue is empty
	 */
	public boolean isEmpty() {
		// TODO: Needs to return true when empty and false otherwise
		return this.front == null && this.rear == null;

	}

	/**
	 * Returns the element at the head of the queue
	 */
	public T peek() throws NoSuchElementException {

		if (this.isEmpty()) {/// if the queue is empty throw a specific error 
			throw new NoSuchElementException();
		}

		return this.rear.getElement();

	}

	/**
	 * Removes the front element of the queue
	 */
	public void dequeue() throws NoSuchElementException {

		if (this.isEmpty()) {/// if the queue is empty throw a specific error
			throw new NoSuchElementException();
		}

		this.rear = rear.getNext();
        
		/// if rear becomes NULL, then change front also as NULL
		if (this.rear == null)
			this.front = null;

	}

	/**
	 * Puts an element on the back of the queue.
	 */
	public void enqueue(T element) {

		QueueElement<T> temp = new QueueElement<T>(element, null);

		if (this.isEmpty()) {///if the queue is empty change both front and rear
			this.rear = temp;
			this.front = temp;

		} else {
			this.front.setNext(temp);
			this.front = temp;	
		}
	}

	/**
	 * Method to print the full contents of the queue in order from head to tail.
	 */
	public void print() {

		QueueElement<T> current = this.rear;

		while (current != null) {
			System.out.print(current.getElement() + ",");
			current = current.getNext();
		}
		System.out.println();

	}
}
