

import java.io.*;
import java.util.*;

public class BTree<E> {

    public static final int PRE_ORDER = 0;
    public static final int IN_ORDER = 1;
    public static final int POST_ORDER = 2;
    

    private TreeNode<E> root;

    public BTree() {
	root = new TreeNode<E>();
    }

    /*======== public void add() ==========
      Inputs:   E d
      Returns: 
      
      Wrapper method for the recursive add()
      ====================*/     
    public void add( E d ) {
	Random r = new Random();
	int a = r.nextInt(2);
	if(root.get()==null){
	    root.set(d);
	    return;
	}
	if(root.hasNext()){
	    if(!root.hasLeft()){
		root.setLeft(new TreeNode<E>(d));
	    } else if(!root.hasRight()){
		root.setRight(new TreeNode<E>(d));
	    } else {
		if(a==0){
		    add(root.getLeft(), new TreeNode<E>(d));
		} else if(a==1){
		    add(root.getRight(), new TreeNode<E>(d));
		}
	    }
	} else {
	    if(a==0){
		root.setLeft(new TreeNode<E>(d));
	    } else if (a==1){
		root.setRight(new TreeNode<E>(d));
	    }
	}
}

    /*======== public void add() ==========
      Inputs:   TreeNode<E> curr, TreeNode<E> bn  
      Returns: 
      
      Adds bn to the tree rooted at curr. If curr has 
      an available child space, then attach bn there.

      Otherwise, try to add at the subtree rooted at
      one of curr's children. Choose the child to be
      added to randomly.
      ====================*/
    private void add( TreeNode<E> curr, TreeNode<E> bn ) {
	Random r = new Random();
	int a = r.nextInt(2);
	if(curr.hasLeft()){
	    if(curr.hasRight()){
		if(a==0){
		    add(curr.getLeft(),bn);
		} else {
		    add(curr.getRight(),bn);
		}
	    } else {
		curr.setRight(bn);
	    }
	} else {
	    if(curr.hasRight()){
		curr.setLeft(bn);
	    } else {
		if(a==0){
		    curr.setLeft(bn);
		} else {
		    curr.setRight(bn);
		}
	    }
	}
    }
    
    public void traverse( int mode) {
	if ( mode == PRE_ORDER )
	    preOrder( root );
	else if ( mode == IN_ORDER )
	    inOrder( root );
	else
	    postOrder( root );
	System.out.println();
    }

    /*======== public void preOrder() ==========
      Inputs:   TreeNode<E> curr  
      Returns: 
      
      Prints out the elements in the tree by doing an
      pre-order Traversal
      ====================*/
    public void preOrder( TreeNode<E> curr ) {
	System.out.print(curr.get()+" ");
	if(curr.hasLeft()){
	    preOrder(curr.getLeft());
	}
	if(curr.hasRight()){
	    preOrder(curr.getRight());
	}
    }


    /*======== public void inOrder() ==========
      Inputs:   TreeNode<E> curr  
      Returns: 
      
      Prints out the elements in the tree by doing an
      in-order Traversal
      ====================*/
    public void inOrder( TreeNode<E> curr ) {
	if(curr.hasLeft()){
	    inOrder(curr.getLeft());
	}
	System.out.print(curr.get()+" ");
	if(curr.hasRight()){
	    inOrder(curr.getRight());
	}
    }

    /*======== public void postOrder() ==========
      Inputs:   TreeNode<E> curr  
      Returns: 
      
      Prints out the elements in the tree by doing a
      post-order Traversal    

      ====================*/
    public void postOrder( TreeNode<E> curr ) {
	if(curr.hasLeft()){
	    postOrder(curr.getLeft());
	}
	if(curr.hasRight()){
	    postOrder(curr.getRight());
	}
	System.out.print(curr.get()+" ");
    }
    
    /*======== public int getHeight()) ==========
      Inputs:   
      Returns: The height of the tree

      Wrapper for the recursive getHeight method
      ====================*/
    public int getHeight() {
	return getHeight( root , 1);
    }
    /*======== public int getHeight() ==========
      Inputs:   TreeNode<E> curr  
      Returns:  The height of the tree rooted at node curr
      
      ====================*/
    public int getHeight( TreeNode<E> curr , int height) {
	if(curr.hasNext()){
	    if(curr.hasLeft()){
		return getHeight(curr.getLeft(), height+1);
	    } else {
		return getHeight(curr.getRight(),height+1);
	    }
	} else {
	    return height;
	}
    }

    /*======== public String getLevel() ==========
      Inputs:   TreeNode<E> curr
                int level
                int currLevel  
      Returns: A string containing all the elements on the
               given level, ordered left -> right
      
      ====================*/
    private String getLevel( TreeNode<E> curr, int level, int currLevel ) {
	String s = "";
	if(currLevel+1==level){
	    if(curr.hasLeft())
		s += curr.getLeft().toString()+" ";
	    if(curr.hasRight())
		s += curr.getRight().toString()+" ";
	    return s;
	} else if(currLevel>level){
	    return "";
	} else {
	    if(curr.hasLeft() && curr.hasRight())
		return getLevel(curr.getLeft(),level,currLevel+1) + getLevel(curr.getRight(),level,currLevel+1);
	    if(curr.hasLeft())
		return getLevel(curr.getLeft(),level,currLevel+1);
	    if(curr.hasRight())
		return getLevel(curr.getRight(),level,currLevel+1);
	    return "":
	}
    }
    
    /*======== public String toString()) ==========
      Inputs:   
      Returns: A string representation of the tree
     
      This string should display each level as a separate line.
      A simple version might look something like this:

      0
      1 2
      3 4 5

      Note that you cannot tell exactly where 3, 4 and 5 lie.
      That is ok, but if you want a CHALLENGE, you can try to
      get the output to look nicer, something like this:
             0

          1      2

            3  4   5

      ====================*/
    public String toString() {
	String s = root.toString()+"\n";
	for(int i=2;i<=getHeight();i++){
	    s += getLevel(root,i,1);
	    s += "\n";
	}
	return s;
    }
	

    public static void main( String[] args ) {

	BTree<Integer> t = new BTree<Integer>();

	for ( int i=0; i < 80; i++ ) 
	    t.add( i );
	System.out.println( "Pre-order: ");
	t.traverse( PRE_ORDER );
	System.out.println( "In-order: ");
	t.traverse( IN_ORDER );
	System.out.println( "Post-order: ");
	t.traverse( POST_ORDER );
	System.out.println( "Height: " + t.getHeight() );

	System.out.println( t );
    }
}
