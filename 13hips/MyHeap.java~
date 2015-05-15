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
    private int height, index;
    private HNode root = null;
    private HNode curr = null;

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
	add(d,root,1);
	index++;
	if(index==Math.pow(2,height)){
	    index=0;
	    height++;
	}
    }

    public boolean add(int d, HNode n, int lheight){
	if(n==null){
	    if(lheight == height + 1){
		n = new HNode(d);
		curr = n;
		return true;
	    } else {
		return false;
	    }
	} else {
	    if(traverse(d,n.getL(),lheight+1)){
		if(height == lheight){
		    n.setL(curr);
		}
		swap(n);
		return true;
	    } else if (traverse(d,n.getR(),lheight+1)){
		if(height == lheight){
		    n.setR(curr);
		}
		swap(n);
		return true;
	    }
	    return false;
	}
    }

    public boolean traverse(int d, HNode n, int localheight){
	System.out.println(""+localheight+","+height+","+index);
	if(localheight>height+1)
	    return false;
	if(localheight==height+1 && index <= Math.pow(2,height)){
	    if(n==null){
		n = new HNode(d);
		curr = n;
		return true;
	    }
	} else {
	    if(traverse(d,n.getL(),localheight+1)){
		if(localheight==height){
		    n.setL(curr);
		    return true;
		}
	    } else if(traverse(d,n.getR(),localheight+1)){
		if(localheight==height){
		    n.setR(curr);
		    return true;
		}
	    }
	}
	return false;
    }

    private void swap(HNode n){
	if(n!=null && n.getL()!=null){
	    if(index%2==0){
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
    }

    public int remove(){
	int k = root.get();
        root.set(remove(root));
	return k;
    }

    private int remove(HNode val){
	if(!val.hasL() && !val.hasR()){
	    int k = val.get();
	    val=null;
	    return k;
	} else if(!val.hasL()){
	    int k = val.getL().get();
	    val=null;
	    return k;
	} else {
	    int k;
	    if(type==MAX){
		if(val.getL().get()>val.getR().get()){
		    k = remove(val.getL());
		} else {
		    k = remove(val.getR());
		}
	    } else {
		if(val.getL().get()<val.getR().get()){
		    k = remove(val.getL());
		} else {
		    k = remove(val.getR());
		}
	    }
	    val.set(k);
	    return k;
	}
    }

    public int peek(){
	return root.get();
    }

    public String toString(){
	String s = "";
	s+=root.get();
	s+="\n";
	for(int i=1;i<=height;i++){
	    s+=addstring(root.getL(),i,1);
	    s+=" ";
	    s+=addstring(root.getR(),i,1);
	    s+="\n";
	}
	return s;
    }
    
    public String addstring(HNode n, int localheight, int target){
	if(localheight==target){
	    if(n==null){
		return "";
	    } else {
		return ""+n.get();
	    }
	} else {
	    return addstring(n.getL(),localheight+1,target) + " " + addstring(n.getR(),localheight+1,target);
	}
    }

    public static void main(String[]args){
	MyHeap hip=new MyHeap();
	ArrayList nums = new ArrayList();
	Random r = new Random();
	for(int i=0;i<10;i++){
	    int n=r.nextInt(100);
	    while(true){
		if(nums.contains(n)){
		    n = r.nextInt(100);
		    continue;
		}
		nums.add(n);
		hip.add(n);
		System.out.println(hip.toString());
		break;
	    }
	}
    }
}    
