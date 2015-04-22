import java.util.Random;

public BTree<T>{
    TreeNode<T> root;
    
    public void add(T data){
	if(root.hasNext()){
	    if(!root.hasLeft()){
		root.setLeft(new TreeNode<T>(data));
	    } else if(!root.hasRight()){
		root.setRight(new TreeNode<T>(data));
	    }
	} else {
	    Random r = new Random();
	    int a = r.nextInt(2);
	    if(a==0){
		root.setLeft(new TreeNode<T>(data));
	    } (a==1){
		root.set
    }

    public vo
}