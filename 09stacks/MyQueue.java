public class MyQueue<T> extends MyLinkedList<T>{

    public void enqueue(T value){
	if(value!=null){
	    add(value);
	}
    }
    
    public T dequeue(){
	return remove(0);
    }
}
