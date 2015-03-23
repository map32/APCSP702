public class MyQueue extends MyLinkedList{
    
    MyLinkedList queue;

    public void enqueue(T value){
	if(value!=null){
	    queue.add(value);
	}
    }
    
    public T dequeue(){
	return queue.remove(
    }