import java.util.*;
import java.lang.*;

class HNode {
    int data;
    HNode left = null;
    HNode right = null;

    public HNode(){
    }

    public HNode(int d){
	data = d;
    }

    public void set(int d){
	data = d;
    }

    public void setL(HNode n){
	left = n;
    }

    public void setR(HNode r){
	right = r;
    }

    /*public boolean has(){
	return data != null;
	}*/

    public boolean hasL(){
	return left != null;
    }

    public boolean hasR(){
	return right != null;
    }

    public int get(){
	return data;
    }

    public HNode getL(){
	return left;
    }
    
    public HNode getR(){
	return right;
    }

}

public class MyHeap {
    private final boolean MAX = true;
    private final boolean LEFT = true;
    private final boolean MIN = false;
    private final boolean RIGHT = false;
    private boolean type;
    int height, index;
    private HNode root = null;

    public MyHeap(){
	this(true);
    }

    public MyHeap(boolean isMax){
	type = isMax;
	root = null;
	height = 0;
	index = 0;
    }

    public void add(int d){
	root=add(d,root,1);
	index++;
	if(index==Math.pow(2,height)){
	    index=0;
	    height++;
	}
    }

    public HNode add(int d, HNode n, int lheight){
	if(n==null){
	    if(lheight == height + 1){
		n = new HNode(d);
		return n;
	    } else {
		return null;
	    }
	} else {
	    HNode k = add(d,n.getL(),lheight+1);
	    if(k!=null){
		if(height == lheight){
		    n.setL(k);
		}
		return swap(n,LEFT);
	    } else {
		k = add(d,n.getR(),lheight+1);
		if (k!=null){
		    if(height == lheight){
			n.setR(k);
		    }
		    return swap(n,RIGHT);
		}
	    }
	    return null;
	}
    }

    private HNode swap(HNode n, boolean direction){
	if(n!=null && n.getL()!=null){
	    if(direction==RIGHT){
		if(type==MAX){
		    if(n.getR().get()>n.get()){
			int t = n.get();
			n.set(n.getR().get());
			n.getR().set(t);
		    }
		} else {
		    if(n.getR().get()<n.get()){
			int t = n.get();
			n.set(n.getR().get());
			n.getR().set(t);
		    }
		}
	    } else {
		if(type==MAX){
		    if(n.getL().get()>n.get()){
			int t = n.get();
			n.set(n.getL().get());
			n.getL().set(t);
		    }
		} else {
		    if(n.getL().get()<n.get()){
			int t = n.get();
			n.set(n.getL().get());
			n.getL().set(t);
		    }
		}
	    }
	}
	return n;
    }

    public int remove() throws NoSuchElementException{
	if(root==null){
	    throw new NoSuchElementException();
	}
	index--;
	if(index==-1){
	    index=(int)Math.pow(2,height-1)-1;
	    height--;
	}
	return remove(root,1);
    }

    private int remove(HNode val, int lheight){
	int k = val.get();
	if(height==0){
	    root=null;
	    return k;
	}
	if(lheight==height){
	    if(val.hasR()){
		if(!val.getR().hasL() && !val.getR().hasR()){
		    val.set(val.getR().get());
		    val.setR(null);
		    return k;
		}
	    } else if(val.hasL()){
		if (!val.getL().hasR() && !val.getL().hasL()){
		    val.set(val.getL().get());
		    val.setL(null);
		    return k;
		}
	    } else {
		throw new RuntimeException();
	    }
	} else {
	    int j=k;
	    try {
		j=remove(val.getR(),lheight+1);
	    } catch (RuntimeException e){
		j=remove(val.getL(),lheight+1);
	    } finally {
		val.set(j);
		swap(val,LEFT);
		swap(val,RIGHT);
	    }
	}
	return k;
    }

    public int peek() throws NoSuchElementException{
	if(root==null){
	    throw new NoSuchElementException();
	}
	return root.get();
    }

    public String toString(){
	String s = "";
	if(root==null){
	    return s;
	}
	s+=root.get();
	s+="\n";
	for(int i=1;i<=height;i++){
	    s+=addstring(root.getL(),1,i);
	    s+=" ";
	    s+=addstring(root.getR(),1,i);
	    s+="\n";
	}
	return s;
    }
    
    public String addstring(HNode n, int localheight, int target){
	if(n==null)
	    return "";
	if(localheight==target){
	    return ""+n.get();
	} else {
	    return addstring(n.getL(),localheight+1,target) + " " + addstring(n.getR(),localheight+1,target);
	}
    }

    public static void main(String[]args){
	MyHeap hip=new MyHeap(false);
	ArrayList nums = new ArrayList();
	int[] w = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
	Random r = new Random();
	for(int i=18;i>=0;i--){
	    int n=r.nextInt(100);
	    while(w[i]==100){
		if(nums.contains(n)){
		    n = r.nextInt(100);
		    continue;
		}
		nums.add(n);
		hip.add(n);
		System.out.println(hip.toString());
		System.out.println(hip.height+" "+hip.index);
		break;
	    }
	    nums.add(w[i]);
	    hip.add(w[i]);
	    System.out.println(hip.toString());
	    System.out.println(hip.height+" "+hip.index);
	}
	for(int i=0;i<19;i++){
	    System.out.println(hip.remove());
	    System.out.println(hip.height+","+hip.index);
	    System.out.println(hip.toString());
	}
    }
}    
