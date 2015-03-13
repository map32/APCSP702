public class LNode {

    private int value;
    private LNode next;

    public LNode  getNext(){
	return next;
    }

    public void set(int value){
	this.value = value;
    }

    public void setNext(LNode value){
	next = value;
    }

    public int getValue(){
	return value;
    }

    public String toString(){
	return Integer.toString(value);
    }
}