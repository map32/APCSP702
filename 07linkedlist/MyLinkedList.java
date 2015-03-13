public class MyLinkedList {
    
    private LNode nodes;
    private LNode last;
    private int size;
    
    public MyLinkedList() {
	nodes = null;
	last = nodes;
    }

    public int size(){
	return size;
    }

    public String toString(){
	String str = new String();
	LNode index = nodes;
	str+="[ ";
	while(index!=null){
	    str+=index.getValue()+", ";
	    index = index.getNext();
	}
	if(str=="[")
	    return "[]";
	return str.substring(0,str.length()-2)+" ]";
    }

    public boolean add(int value){
	if(last == null){
	    nodes = new LNode();
	    last = nodes;
	    last.set(value);
	    size++;
	    return true;
	}
	last.setNext(new LNode());
	last.getNext().set(value);
	last = last.getNext();
	size++;
	return true;
    }

    public int remove(int indexy){
	LNode index = nodes;
	if(indexy==0){
	    nodes = nodes.getNext();
	    size--;
	    return index.getValue();
	}
	int i=0;
	while(i+1!=indexy){
	    index = index.getNext();
	    i++;
	}
	if(indexy+1!=size){
	    index.setNext(index.getNext().getNext());
	} else {
	    last = index;
	    index.setNext(null);
	}
	size--;
	return index.getValue();
    }

    public void set(int index, int value){
	LNode current = nodes;
	while(index!=0){
	    current = current.getNext();
	    index--;
	}
	current.set(value);
    }

    public int get(int index){
	LNode current = nodes;
	while(index!=0){
	    current = current.getNext();
	    index--;  
	}
	return current.getValue();
    }

    public int indexOf(int value){
	LNode current = nodes;
	if(last.getValue()==value){
	    return size-1;
	}
	for(int i=0;i<size;i++){
	    if(current.getValue()==value){
		return i;
	    }
	    current = current.getNext();
	}
	return -1;
    }

    public static void main(String[] args){
	MyLinkedList lel = new MyLinkedList();
	lel.add(333);
	lel.add(444);
	lel.add(555);
	System.out.println(lel.toString() + lel.size()+lel.last);
	System.out.println(lel.indexOf(444));
	System.out.println(lel.toString() + lel.size()+lel.last);
    }
}