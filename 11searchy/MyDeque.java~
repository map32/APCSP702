import java.util.NoSuchElementException;

public class MyDeque<T> {

    private Object[] array;
    public int capacity;
    public int size;
    public int start;
    public int end;
    
    public MyDeque(int s){
	array = new Object[s];
	capacity = s;
    }

    public MyDeque(){
	this(10);
    }

    //eventually implement constructor that copies an array

    public void addFirst(T value){
	size++;
	resize();
	if(size!=1)
	    start = wrap(start,-1);
        array[start] = value;
    }

    public void addLast(T value){
	size++;
	resize();
	if(size!=1)
	    end = wrap(end,1);
	array[end] = value;
    }

    public T removeFirst() throws NoSuchElementException {
	T value = (T)array[start];
	if(size==0){
	    throw new NoSuchElementException();
	} else if(size==1){
            array[start] = null;
	    size--;
	    return value;
	}
	array[start] = null;
	start = wrap(start,1);
	size--;
	resize();
	return value;
    }
    
    public T removeLast() throws NoSuchElementException {
	T value = (T)array[end];
	if(size==0){
	    throw new NoSuchElementException();
	} else if(size==1){
	    array[end]=null;
	    //end = wrap(end,-1);
	    size--;
	    return value;
	}
	array[end] = null;
	end = wrap(end,-1);
	size--;
	if(resize()){
	    end = wrap(end,1);
	}
	return value;
    }

    public T getFirst() {
	return (T)array[start];
    }

    public T getLast() {
	return (T)array[end];
    }

    public int wrap(int index, int adv){
	return (capacity+index+adv)%capacity; 
    }

    private boolean resize(){
	int capacity2;
	if(size>=capacity){
	    capacity2 = capacity*2;
	    // System.out.println(capacity);
	} else if(capacity<21){
	    return false;
	} else if(size*4<=capacity){
	    capacity2 = capacity/2;
	} else {
	    return false;
	}
	//System.out.println("g");
	Object[] array2 = new Object[capacity2];
	int i=start;
	int j=0;
	do {
	    //System.out.println(i);
	    array2[j]=array[i];
	    i = wrap(i,1);
	    j++;
	    //System.out.println(start+" "+end);
	} while (i!=end);
	array2[j]=array[i];
	start=0;
	end=wrap(start,size-2);
	capacity = capacity2;
	array = array2;
	return true;
    }

    public String toString(){
	String s = "[ ";
	int i=start;
	while(i!=end){
	    s += array[i];
	    s += " ";
	    i = wrap(i,1);
	}
	if(start!=end){
	    s += array[i];
	    s += " ";
	}
	s+="]";
	s+=" [";
	for(Object o : array){
	    s += o;
	    s += " ";
	}
	s+=" ]";
	return s;
    }
    
    public String name(){
	return "shin.dong";
    }

    public static void main(String[] args){
	//System.out.println("f");
	MyDeque<Object> q = new MyDeque<Object>();
        for(int i=0;i<100;i++){
	    q.addFirst(i);
	    q.addLast(i);
	}
	System.out.println(q);
	for(int i=0;i<100;i++){
	    q.removeFirst();
	    q.removeLast();
	}
	System.out.println(q);
	System.out.println(q.start +" "+ q.end);

    }

}

