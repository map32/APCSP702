
import java.util.*;

public class Sorts {

    public static MyHeap heapify(int[] org){
	MyHeap hip = new MyHeap(true);
	for(int i : org){
	    hip.add(i);
	}
	return hip;
    }

    public static int[] sort(int[] org){
	MyHeap hip = heapify(org);
	for(int i=org.length-1;i>0;i--){
	    org[i]=hip.remove();
	}
	return org;
    }

    public static void main(String[] args){
	int[] l = new int[100];
	Random r = new Random();
	for(int i=0;i<100;i++){
	    l[i] = r.nextInt();
	}
	System.out.println(Arrays.toString(l));
	l = Sorts.sort(l);
	System.out.println(Arrays.toString(l));
    }
}
