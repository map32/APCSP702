import java.lang.IllegalArgumentException;
import java.lang.Math;

public class Recursion implements hw1 {
    public String name(){
	return "Shin,Dong";
    }

    public int fact(int n){
	if (n<0) {
	    throw new IllegalArgumentException("no factorials for neg numbers");
	} else if(n<2) {
	    return 1;
	} else {
	    return n*fact(n-1);
	}
    }

    public int fib(int n){
	if (n<0){
	    throw new IllegalArgumentException("no fibonacci for neg numbers");
	} else if(n==0){
	    return 0;
	} else if(n==1){
	    return 1;
	} else {
	    return fib(n-2)+fib(n-1);
	}
    }

    public double sqrt(double n){
	if(n<0){
	    throw new IllegalArgumentException("result should be real");
	}
	return sqrt2(n, n/3);
    }

    public double sqrt2(double n, double g){
	if(Math.abs(n-g*g)<0.000000000001){
	//if(n==g*g){
	    return g;
	} else {
	    return sqrt2(n,(n/g+g)/2);
	}
    }

    public static void main(String[] sf){
	Recursion r = new Recursion();
	//	System.out.println(r.fib(-1));
	//	System.out.println(r.fact(-1));
	//	System.out.println(r.sqrt(-1));
	for(int i=0;i<20;i++){
	    System.out.println(r.fib(i)+" "+r.fact(i)+" "+r.sqrt(i));
	}

    }

}





