import java.util.NoSuchElementException;
import java.util.Random;

public class MyDeque<T> {

    private Object[] array;
    private int[] priority;
    private int capacity;
    private int size;
    private int start;
    private int end;
    
    public MyDeque(int s){
	array = new Object[s];
	priority = new int[s];
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

    public void add(T value, int priority){
	addFirst(value);
	this.priority[start] = priority;
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

    public T removeSmallest() throws NoSuchElementException {
	T val;
	if(size==0){
	    throw new NoSuchElementException();
	} else {
	    int small = start;
	    for(int i=start;i!=end;i=wrap(i,1)){
		if(priority[small]>priority[i]){
		    small = i;
		}
	    }
	    if(priority[small]>priority[end]){
		small = end;
	    }
	    val = (T)array[small];
	    //System.out.print(priority[small]+" ");
	    priority[small] = priority[end];
	    array[small] = removeLast();
	    //end = wrap(end,-1);
	    //size--;
	    //resize();
	}
	return val;
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
	int[] priority2 = new int[capacity2];
	int i=start;
	int j=0;
	do {
	    //System.out.println(i);
	    array2[j]=array[i];
	    priority2[j]=priority[i];
	    i = wrap(i,1);
	    j++;
	    //System.out.println(start+" "+end);
	} while (i!=end);
	array2[j]=array[i];
	priority2[j]=priority[i];
	start=0;
	end=wrap(start,size-2);
	capacity = capacity2;
	array = array2;
	priority = priority2;
	return true;
    }

    public String toString(){
	String s = "[ ";
	int i=start;
	if(size==0)
	    return "[ ]";
	while(i!=end){
	    s += array[i];
	    s += " ";
	    i = wrap(i,1);
	}
	s += array[i];
	/**for(Object o : array){
	    s += o;
	    s += " ";
	    }**/
	s+=" ]";
	return s;
    }
    
    public int size(){
	return size;
    }

    public String name(){
	return "shin.dong";
    }

    public static void main(String[] args){
	//System.out.println("f");
	MyDeque<Object> q = new MyDeque<Object>();
	Random r = new Random();
        for(int i=0;i<100;i++){
	    q.add(i,r.nextInt());
	}
	for(int i=0;i<100;i++){
	    Object t = q.removeSmallest();
	    System.out.println(i+" "+t+" "+q.toString());
	}
    }

}

