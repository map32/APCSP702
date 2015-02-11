import java.util.*;
import java.io.*;


public class KnightsTour{
    //constants for the class
    //terminal specific character to clear screen , or hide/show cursor
    final static String clear =  "\033[2J";
    final static String hide =  "\033[?25l";
    final static String show =  "\033[?25h";

    //instance variable
    private int[][]board;


    //terminal specific character to move the cursor
    private String go(int x,int y){
	return ("\033[" + x + ";" + y + "H");
    }
 
    public void wait(int millis){
	try {
	    Thread.sleep(millis);
	}
	catch (InterruptedException e) {
	}
    }

    public String toString(){
	String ans = "";
	for(int i=0;i<board[0].length;i++){
	    ans += "\n";
	    for(int j=0;j<board[0].length;j++){
		if(board[i][j]==-1){
		    ans += "  ";
		} else if(board[i][j]<10){
		    ans += " "+board[i][j];
		} else {
		    ans += board[i][j];
		}
		if(j!=board[0].length-1){
		    ans += "|";
		}
	    }
	    ans += "\n";
	    if(i != board[0].length-1){
		for(int k=0;k<board[0].length*3-1;k++){
		    ans += "-";
		}
	    }
	}
	//build your knights tour here...
	//return hide + go(0,0) + ans + "\n" + show;
	return ans;
    }

    public KnightsTour(int size){
	board = new int[size][size];
	for(int i=0;i<size*size;i++){
	    board[i/size][i%size]=-1;
	}
    }

    

    public void solve(){
				
    }


    public void solve(int startx, int starty){
	board[startx][starty]=0;

    }



		
    public boolean solve(int x,int y,int currentMoveNumber){
	System.out.println(this);
	wait(20);
				
	return false;
    }

    public static void main(String[] afd){
	KnightsTour K = new KnightsTour(10);
	System.out.println(K.toString());
    }

}