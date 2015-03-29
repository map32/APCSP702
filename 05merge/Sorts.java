import java.util.ArrayList;
import java.util.Random;

public class Sorts{

    private static ArrayList<Integer> list;
    private static ArrayList<Integer> list2;

    public static String name(){
	return "shin.dong";
    }

    public static void mergesort(int[] intlist){
	list = new ArrayList<Integer>();
	list2 = new ArrayList<Integer>();
	for(int i=0;i<intlist.length;i++){
	    list.add(intlist[i]);
	}
	list2 = merge(list);
	System.out.println(list2);
    }

    private static ArrayList<Integer> merge(ArrayList<Integer> a){
	ArrayList<Integer> z = new ArrayList<Integer>();
	if(a.size()==1){
	    return a;
	} else {
	    ArrayList<Integer> x = merge(new ArrayList<Integer>(a.subList(0,a.size()/2)));
	    ArrayList<Integer> y = merge(new ArrayList<Integer>(a.subList(a.size()/2,a.size())));
	    do {
		if(x.get(0).compareTo(y.get(0))<=0){
		    z.add(x.remove(0));
		} else {
		    z.add(y.remove(0));
		}
	    } while (x.size()>0 && y.size()>0);
	    if(x.size()>0){
		z.addAll(x);
	    } else if (y.size()>0){
		z.addAll(y);
	    }
	    return z;
	}
    }

    public static int[] randomize(int min, int max, int len){
	int[] a = new int[len];
	Random r = new Random();
	for(int i=0;i<len;i++){
	    a[i]=r.nextInt(max-min+1)+min;
	}
	return a;
    }

    public static void main(String[]dd){
	int[] a = randomize(-1000000,1000000,100000);
        mergesort(a);
	//	System.out.println(list2);
    }
}
