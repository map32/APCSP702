//import java.util.Node;

public class LNode<T> {

    private T value;
    private LNode<T> next;

    public LNode(){
    }

    public LNode(T data){
	set(data);
    }

    public LNode<T> getNext(){
	return next;
    }

    public void set(T value){
	this.value = value;
    }

    public void setNext(LNode<T> value){
	next = value;
    }

    public T getValue(){
	return value;
    }

    public String toString(){
	return value.toString();
    }
}