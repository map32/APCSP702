public class LNode {

    private int value;
    private LNode next;
    private int index;

    public int  getNext(int index){
	if(index==0){
	    return value;
	}
	return next.getNext(index--);
    }

    public void setNext(int value, int index){
	if(index==0){
	    this.value=value;
	} else {
	    next.setNext(index--);
	}
    }

    public String toString(){
	return value;
    }
}