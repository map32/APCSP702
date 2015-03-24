public class MyStack<T> extends MyLinkedList<T>{

    public void push(T value){
	add(0,value);
    }

    public T pop(){
	return remove(0);
    }

    /**public T peek(){
	return nodes.getValue();
	}**/
}