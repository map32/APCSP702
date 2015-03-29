import java.util.Arrays;
import java.util.Random;

public class QuickSelect {

    static int[] array;

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

    public static int partition(int[] ary, int si, int ei){
	/*int[] d = new int[ary.length];
	for(int i=0;i<ary.length;i++){
	    if(i=<si || i>ei){
		d[i]=ary[i];
	    }
	    }*/
	int start=si;
	int end=ei;
	int ri= si + (int)(Math.random()*(end-start+1));
	//System.out.println(""+si+" "+ei+" "+ri);
	int pivot=ary[ri];
	int ds = 0;
	//System.out.println("pivot="+pivot);
	ary[ri] = ary[start];
	ary[start] = pivot;
	//System.out.println(Arrays.toString(ary));
	while(start+ds<=end){
	    if(ary[start+ds]<pivot){
		//start++;
		//ds++;
		int t = ary[start+ds];
		ary[start+ds]=ary[start];
		ary[start]=t;
		start++;
		//ds++;
	    } else if (ary[start+ds]>pivot){
		//end--;
		int t = ary[end];
		ary[end]=ary[start+ds];
		ary[start+ds]=t;
		end--;
	    } else if (ary[start+ds]==pivot){
		ds++;
	    }
	    //while(ary[end]>pivot){
	    //	end--;
	    //}
	    /**if(ary[start]==pivot){
		int t = ary[start];
		ary[start]=ary[start+1];
		ary[start+1]=t;
	    }
	    if(ary[end]==pivot){
		int t = ary[end];
		ary[end]=ary[end-1];
		ary[end+1]=t;
		}**/
	    /** if(start+ds<end){
		int t = ary[start];
		ary[start] = ary[end];
		ary[end] = t;
		}**/
	    //System.out.println(Arrays.toString(ary)+" "+start+" "+ds+" "+end);
	}
	//ary[si]=ary[end];
	//ary[end] = pivot;
	//ary[ri]=pivot;
	//System.out.println(Arrays.toString(ary));
	array=ary;
	return end;
    }

    public static int quickSelect(int ary[],int n){
	array=ary;
	int si=0;
	int ei=ary.length-1;
	int i=partition(array,si,ei);
	while(!(i==n)){
	    i=partition(array,si,ei);
	    if(i>n){
		ei=i-1;
	    } else if(i<n){
		si=i+1;
	    }
	}
	return array[i];
    }

    public static int[] quickSort(int ary[]){
        array=ary;
	quickhelp(0,array.length-1,partition(ary,0,array.length-1));
	return array;
    }
    
    private static void quickhelp(int si, int ei, int i){
	if(si>=ei || i+1>=array.length){
	    return;
	} else {
	    quickhelp(si,i-1,partition(array,si,i-1));
	    quickhelp(i+1,ei,partition(array,i+1,ei));
	}
    }

    public static void main(String[]ss){
	int[] test = randomize(-1000000,1000000,100000);
	//partition(test,0,test.length-1);
	System.out.println(Arrays.toString(quickSort(test)));
    }

    public static int[] randomize(int min, int max, int len){
	int[] a = new int[len];
	Random r = new Random();
	for(int i=0;i<len;i++){
	    a[i]=r.nextInt(max-min+1)+min;
	}
	return a;
    }

    public static String name(){
	return "shin.dong";
    }
}
