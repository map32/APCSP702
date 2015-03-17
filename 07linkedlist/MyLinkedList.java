public class MyLinkedList<T> {
    
    private LNode<T> nodes;
    private LNode<T> last;
    private int size;
    
    public MyLinkedList() {
	nodes = null;
	last = nodes;
    }

    public String name(){
	return "shin.dong";
    }

    public int size(){
	return size;
    }

    public String toString(){
	String str = new String();
	LNode<T> index = nodes;
	str+="[ ";
	while(index!=null){
	    str+=index.getValue()+", ";
	    index = index.getNext();
	}
	if(str=="[")
	    return "[]";
	return str.substring(0,str.length()-2)+" ]";
    }

    public boolean add(T value){
	if(last == null){
	    nodes = new LNode<T>();
	    last = nodes;
	    last.set(value);
	    size++;
	    return true;
	}
	last.setNext(new LNode<T>());
	last.getNext().set(value);
	last = last.getNext();
	size++;
	return true;
    }

    public boolean add(int index, T value){
	if(last == null){
	    nodes = new LNode<T>();
	    last = nodes;
	    last.set(value);
	    size++;
	    return true;
	}
	LNode<T> ind = nodes;
	while(index>0){
	    ind = ind.getNext();
	    index--;
	    if(ind==last){
		add(value);
		return true;
	    }
	}
	LNode<T> ind2 = ind.getNext();
	ind.setNext(new LNode<T>());
	ind.getNext().setNext(ind2);
	size++;
	return true;
    }

    public T remove(int indexy){
	LNode<T> index = nodes;
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

    public void set(int index, T value){
	LNode<T> current = nodes;
	while(index!=0){
	    current = current.getNext();
	    index--;
	}
	current.set(value);
    }

    public T get(int index){
	LNode<T> current = nodes;
	while(index!=0){
	    current = current.getNext();
	    index--;  
	}
	return current.getValue();
    }

    public int indexOf(T value){
	LNode<T> current = nodes;
	if(last.getValue()==value){
	    return size-1;
	}
	for(int i=0;i<size;i++){
	    if(current.getValue().equals(value)==true){
		return i;
	    }
	    System.out.println(current.getValue()+" "+value);
	    current = current.getNext();
	}
	return -1;
    }

    public static void main(String[] args){
	MyLinkedList lel = new MyLinkedList();
	lel.add(232);
	lel.add("ag");
	lel.add(true);
	System.out.println(lel.toString() + lel.size()+lel.last);
	System.out.println(lel.indexOf(232));
	System.out.println(lel.toString() + lel.size()+lel.last);
    }
}
