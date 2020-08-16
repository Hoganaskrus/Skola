package testqueue;

import static org.junit.Assert.*;
import queue_singlelinkedlist.FifoQueue;
import org.junit.Test;

public class TestAppendFifoQueue {
	private FifoQueue<Integer >myQueue1 = new FifoQueue<Integer>();
	private FifoQueue<Integer >myQueue2 = new FifoQueue<Integer>();
	
	
	
	@Test
	public void twoEmptyQueues(){
		myQueue1.append(myQueue2);
	}
	
	@Test
	public void test() {
		myQueue1.offer(1);
		myQueue1.offer(2);
		myQueue2.offer(3);
		myQueue2.offer(4);
		fail("Not yet implemented");
	}

}
