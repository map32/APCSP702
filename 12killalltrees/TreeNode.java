public class TreeNode<T>{
    private T data;
    private TreeNode<T> left=null;
    private TreeNode<T> right=null;
    
    public TreeNode(T d, TreeNode<T> l, TreeNode<T> r){
	this(d,l);
	right = r;
    }

    public TreeNode(T d, TreeNode<T> l){
	this(d);
	left = l;
    }

    public TreeNode(T d){
	data = d;
    }

    public T get(){
	return data;
    }

    public TreeNode<T> getLeft(){
	return left;
    }

    public TreeNode<T> getRight(){
	return right;
    }

    public void set(T d){
	data = d;
    }
    
    public void setLeft(TreeNode<T> l){
	left = l;
    }

    public void setRight(TreeNode<T> r){
	right = r;
    }

    public boolean hasNext(){
	return (hasLeft() || hasRight());
    }

    public boolean hasLeft(){
	return left != null;
    }
    
    public boolean hasRight(){
	return right != null;
    }

    public String toString(){
	return data.toString();
    }
}