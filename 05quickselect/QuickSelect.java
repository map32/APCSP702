import java.util.Arrays;

public class QuickSelect {

    /* public static void partition(int[] ary, int si, int ei){
	int[] d = new int[ary.length];
	for(int i=0;i<ary.length;i++){
	    if(i=<si || i>ei){
		d[i]=ary[i];
	    }
	}
	int start=si;
	int end=ei;
	int ri= si + (int)(Math.random()*(ei-si+1));
	int pivot=ary[ri];
	System.out.println("pivot="+pivot);
	for(int i=start;i <=end;i++){
	    if(ary[i]<pivot){
		d[si]=ary[i];
		si++;
	    } else if(ary[i]>pivot) {
		d[ei]=ary[i];
		ei--;
	    }
	    System.out.println(Arrays.toString(d));
	}
	d[ri]=pivot;
	System.out.println(Arrays.toString(d));
    }*/

    public static void partition2(int[] ary, int si, int ei){
	/*int[] d = new int[ary.length];
	for(int i=0;i<ary.length;i++){
	    if(i=<si || i>ei){
		d[i]=ary[i];
	    }
	    }*/
	int start=si;
	int end=ei;
	int ri= si + (int)(Math.random()*(ei-si+1));
	int pivot=ary[ri];
	System.out.println("pivot="+pivot);
	
	for(int i=start;i <=end;i++){
	    if(ary[i]<pivot){
		int t = ary[i];
		ary[i]=ary[si];
		ary[si]=t;
		si++;
	    } else if(ary[i]>pivot) {
		int t = ary[i];
		ary[i]=ary[ei];
		ary[si]=t;
		ei--;
	    }
	    System.out.println(Arrays.toString(ary));
	}
	ary[ri]=pivot;
	System.out.println(Arrays.toString(ary));
    }

    public static void main(String[]ss){
	int[] test = {12,43,532,-3,423,-34,54,83,-33,-644,-7};
	partition2(test,0,test.length-1);
    }
}