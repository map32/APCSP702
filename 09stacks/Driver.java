public class Driver{
    public static void main(String[] args){
	MyStack<Integer> stack = new MyStack<Integer>();
	MyQueue<Integer> queue = new MyQueue<Integer>();
	stack.push(5);
	stack.push(7);
	stack.push(9);
	stack.push(10);
	System.out.println(stack);
	System.out.println(stack.pop());
	System.out.println(stack.pop());
	System.out.println(stack);
 
	queue.enqueue(7);
	queue.enqueue(9);
	queue.enqueue(11);
	queue.enqueue(13);
	System.out.println(queue);
	System.out.println(queue.dequeue());
	System.out.println(queue.dequeue());
	System.out.println(queue);
    }
}
