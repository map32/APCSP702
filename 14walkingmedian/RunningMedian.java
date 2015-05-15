import java.lang.*;
import java.util.*;

public class RunningMedian {
    private MyHeap max;
    private MyHeap min;
    private int maxL;
    private int minL;

    private final int MAX = 1;
    private final int BOTH = 0;
    private final int MIN = -1;

    int direction;

    public RunningMedian(){
	max = new MyHeap(true);
	min = new MyHeap(false);
	direction = MAX;
    }

    public void add(int d) throws NoSuchElementException{
	if(maxL+minL==0){
	    max.add(d);
	    maxL++;
	    return;
	}
	if(getMedian()<d){
	    min.add(d);
	    minL++;
	} else {
	    max.add(d);
	    maxL++;
	}
	adjust();
    }

    public double getMedian() throws NoSuchElementException{
	if(direction==MAX)
	    return max.peek();
	else if(direction==BOTH)
	    return (min.peek()+max.peek())/2.;
	else
	    return min.peek();
    }

    private void adjust(){
	if(maxL-minL==2){
	    min.add(max.remove());
	    maxL--;
	    minL++;
	} else if (maxL-minL==-2){
	    max.add(min.remove());
	    minL--;
	    maxL++;
	}
	if(maxL==minL){
	    direction = BOTH;
	} else if(maxL>minL){
	    direction = MAX;
	} else {
	    direction = MIN;
	}
    }

    public static void main(String[]args){
	RunningMedian hip = new RunningMedian();
	ArrayList nums = new ArrayList();
	Random r = new Random();
	for(int i=50;i>=0;i--){
	    int n=r.nextInt(100);
	    while(true){
		if(nums.contains(n)){
		    n = r.nextInt(100);
		    continue;
		}
		nums.add(n);
		hip.add(n);
		System.out.println(nums.toString());
		System.out.println(hip.getMedian()+" , "+hip.direction);
		break;
	    }
	}
    }
}
