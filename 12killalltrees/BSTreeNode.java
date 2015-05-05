import java.io.*;
import java.util.*;

public class BSTreeNode<T extends Comparable> {

    private T data;
    private BSTreeNode<T> left;
    private BSTreeNode<T> right;
    private int height;

    public BSTreeNode( T d ) {
 
	data = d;
	left = right = null;
	height = 1;
    }

    public BSTreeNode(T d, int h){
	this(d);
	h=height;
    }
    
    //accessors
    public T getData() {
	return data;
    }
    public BSTreeNode<T> getLeft() {
	return left;
    }
    public BSTreeNode<T> getRight() {
	return right;
    }
    public int getHeight(){
	return height;
    }

    public boolean hasLeft(){
	return left != null;
    }

    public boolean hasRight(){
	return right != null;
    }

    //mutators
    public void setData( T d ) {
	data = d;
    }
    public void setLeft( BSTreeNode<T> l ) {
	left = l;
    }
    public void setRight( BSTreeNode<T> r ) {
	right = r;
    }
    public void setHeight( int h){
	height = h;
    }

    public String toString(){
	return data.toString();
    }

    public int compareTo(BSTreeNode<T> n){
	return data.compareTo(n.getData());
    }

    public static void main( String[] args ) {
	BSTree<Integer> k = new BSTree<Integer>();
	int[] ar = {34, 12, 5, 64, 72, 89, 40, 43, 56, 98, 23, 37};
	Random r = new Random();
	for(int i=0;i<12;i+=1){
	    k.add(ar[i]);
	    System.out.println(k.toString());
	    System.out.println(".........................");
	    k.inOrder();
	    System.out.println(".........................");
	    System.out.println("height: "+k.getHeight());
	    System.out.println(".........................");
	}
	for(int i=0;i<6;i++){
	    k.remove(ar[i]);
	    System.out.println(k.toString());
	    System.out.println(".........................");
	    k.inOrder();
	    System.out.println(".........................");
	    System.out.println("height: "+k.getHeight());
	    System.out.println(".........................");
	}
    }

}




//

//

//Your binary search tree skeleton file:


class BSTree <T extends Comparable> {

    private BSTreeNode<T> root;
    private final boolean LEFT = false;
    private final boolean RIGHT = true;
    private boolean direction = LEFT;

    public BSTree() {
	root = null;
    }

    public boolean isEmpty() {
	return root == null;
    }
    public boolean isLeaf( BSTreeNode<T> t ) {
	return (t.getLeft() == null && t.getRight() == null);
    }

    /*======== public void add() ==========
      Inputs:   T c  
      Returns: 

      Wrapper for the recursive add method
      ====================*/
    public void add( T c ) {
	if(root==null){
	    root= new BSTreeNode<T>(c);
	} else {
	    add( root, new BSTreeNode<T>(c) );
	}
    }

    /*======== public BSTreeNode<T> add() ==========
      Inputs:  BSTreeNode<T> curr
               BSTreeNode<T> t 
      Returns: 

      Add t to the correct place in the tree rooted at curr.
      ====================*/
    private void add(BSTreeNode<T> curr, BSTreeNode<T> t) {
	if(curr.compareTo(t)>0){
	    if(curr.getLeft()==null){
		curr.setLeft(t);
		curr.getLeft().setHeight(curr.getHeight()+1);
	    } else {
		add(curr.getLeft(),t);
	    }
	} else if(curr.compareTo(t)<0){
	    if(curr.getRight()==null){
		curr.setRight(t);
		curr.getRight().setHeight(curr.getHeight()+1);
	    } else {
		add(curr.getRight(),t);
	    }
	}
    }

    /*======== public void remove() ==========
      Inputs:   T c  
      Returns: 
      
      Wrapper for the recursive remove method
      ====================*/
    public void remove( T c ) {
        remove( root, c );
    }

    /*======== public BSTreeNode<T> remove() ==========
      Inputs:   BSTreeNode<T> curr
		T c
      Returns: 

      Should remove the value c from the tree rooted at
      curr, if it exists.
      ====================*/
    private void remove( BSTreeNode<T> curr, T c ) {
	if(curr==null){
	    return;
	}
	if(curr.getData().compareTo(c)==0){
	    if(!(curr.hasLeft() && curr.hasRight())){
		curr=null;
		System.gc();
	    } else {
		curr.setData(tracedown(curr));
	    }
	} else {
	    if(curr.getData().compareTo(c)<0){
		remove(curr.getLeft(),c);
	    } else {
	        remove(curr.getRight(),c);
	    }
	}
    }
    
    private T tracedown(BSTreeNode<T> curr){
	if(direction==LEFT){
	    curr = curr.getLeft();
	} else {
	    curr = curr.getRight();
	}
	direction = !direction;
	BSTreeNode<T> parent = null;
	while(curr!=null){
	    parent = curr;
	    if(direction==LEFT){
		curr = curr.getLeft();
	    } else {
		curr = curr.getRight();
	    }
	}
	if(parent.hasLeft()){
	    direction=LEFT;
	    return tracedown(parent);
	} else if(parent.hasRight()){
	    direction=RIGHT;
	    return tracedown(parent);
	} else {
	    T d = parent.getData();
	    parent = null;
	    System.gc();
	    return d;
	}
    }

    /*======== public void inOrder()) ==========
      Inputs:   
      Returns: 

      Wrapper for the recursive inOrder method
      ====================*/
    public void inOrder() {
	inOrderHelper( root );
	System.out.println();
    }

    /*======== public void inOrderHelper() ==========
      Inputs:   BSTreeNode<T> t  
      Returns: 
      
      Performs an in-order traversal for the tree with 
      root t.
      ====================*/
    public void inOrderHelper( BSTreeNode<T> t ) {
	if (t == null) 
	    return;
	inOrderHelper( t.getLeft() );
	System.out.print( t.getData() + " ");
	inOrderHelper( t.getRight() );
    }

    
    public int getHeight(){
	return getHeight(root);
    }
    
    private int getHeight(BSTreeNode<T> r ){
	if(r == null){
	    return 0;
	}else{
	    //System.out.println("recursion height");
	    return 1 + Math.max(getHeight(r.getLeft()),
				getHeight(r.getRight()));
	}
    }
    
    private int maxLength() {
	// returns the minimum number of characters required
	// to print the data from any node in the tree
	if (root == null)
	    return 0;
	return maxLength(root);
    }
    
    private int maxLength(BSTreeNode<T> curr) {
	int max = curr.toString().length();
	int temp;
	if (curr.getLeft() != null) {
	    temp = maxLength(curr.getLeft());
	    if (temp > max)
		max = temp;
	}
	if (curr.getRight() != null) {
	    temp = maxLength(curr.getRight());
	    if (temp > max)
		max = temp;
	}
	return max;
    }
    
    private String spaces(double n) {
	// returns a String of n spaces
	String result = "";
	for (int i = 0; i < n; i++)
	    result += " ";
	return result;
    }
    
    /*
      getLevel will produce a String for each level of the tree.
      The resulting Strings will look like this:
      
      ._______________________________
      ._______________._______________
      ._______._______._______._______
      .___.___.___.___.___.___.___.___
      ._._._._._._._._._._._._._._._._
      
      toString will combine those Strings and provide an output that
      will look like this:
      
      _______________.
      _______._______________.
      ___._______._______._______.
      _.___.___.___.___.___.___.___.
      ._._._._._._._._._._._._._._._.
      In these diagrams, each dot represents wordLength characters,
      each underscore represents wordLength spaces, and, for any nodes
      that are null, the dots will be "replaced" by underscores.
    */
    
    private String getLevel(BSTreeNode<T> curr, int currLevel, int targetLevel, int height, int wordLength) {
	if (currLevel == 1){
	    return curr.toString() + 
		spaces(wordLength - curr.toString().length()) +
		spaces(wordLength * 
		       Math.pow(2, height - targetLevel + 1) - 
		       wordLength);
	}
	String result = "";
	if (curr.getLeft() != null){
	    result += getLevel(curr.getLeft(), currLevel - 1, targetLevel, height, wordLength);
	}else{
	    result += spaces(wordLength * Math.pow(2, height - targetLevel + currLevel - 1));
	}
	if (curr.getRight() != null){
	    result += getLevel(curr.getRight(), currLevel - 1, targetLevel, height, wordLength);
	}else{ 
	    result += spaces(wordLength * Math.pow(2, height - targetLevel + currLevel - 1));
	}
	return result;
    }
    
    public String toString() {
	if (root == null)
	    return "";
	String result = "";
	int height = getHeight();
	int wordLength = maxLength();
	// add the every level of the tree except the last one
	for (int level = 1; level < height; level++){
	    // remove extra spaces from the end of each level's String to prevent lines from
	    // getting unnecessarily long and add spaces to the front of each level's String
	    // to keep everything centered
	    result += spaces(wordLength * Math.pow(2, height - level) - wordLength) +
		getLevel(root, level, level, height, wordLength).replaceFirst("\\s+$", "") +
		"\n";
	}
	// now add the last level (level = height)
	result += getLevel(root, height, height, height, wordLength).replaceFirst("\\s+$", "");
	
	return result;
    }
    
    


}

