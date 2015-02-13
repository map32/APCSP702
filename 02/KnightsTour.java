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
		if(board[i][j]==0){
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
	    board[i/size][i%size]=0;
	}
    }


    public boolean solve(){
	Random r = new Random();
	return solve(r.nextInt(board[0].length),r.nextInt(board[0].length),1,board);
    }


    public boolean solve(int x, int y, int currentMoveNumber, int[][] thisboard){
	int[][] board2 = new int[board[0].length][board[0].length];
	for(int i=0;i<board[0].length;i++){
	    for(int j=0;j<board[0].length;j++){
		board2[i][j] = thisboard[i][j];
	    }
	}
	try {
	    if(board2[x][y] < currentMoveNumber && board2[x][y]!=0){
		return false;
	    } else {
		board2[x][y]=currentMoveNumber;
		if(currentMoveNumber == (board[0].length*board[0].length)){
		    board2[x][y]=currentMoveNumber;
		    board = board2;
		    return true;
		}
	    }
	} catch (ArrayIndexOutOfBoundsException e){
	    return false;
	}
	board = board2;
	//System.out.println(this);
	//wait(2);
	return solve(x+2,y+1,currentMoveNumber+1,board2) || solve(x+2,y-1,currentMoveNumber+1,board2) || solve(x-2,y-1,currentMoveNumber+1,board2) || solve(x-2,y+1,currentMoveNumber+1,board2) || solve(x+1,y+2,currentMoveNumber+1,board2) || solve(x+1,y-2,currentMoveNumber+1,board2) || solve(x-1,y-2,currentMoveNumber+1,board2) || solve(x-1,y+2,currentMoveNumber+1,board2);
    }



		
    /*    public boolean solve(int x,int y,int currentMoveNumber){
	try {
	    if(board[x][y]<currentMoveNumber){
		return false;
	    } else {
		board[x][y]=currentMoveNumber;
		
	    }
	System.out.println(this);
	wait(20);
	
	return false;
	}*/

    public static void main(String[] afd){
	KnightsTour K = new KnightsTour(5);
	if(!K.solve()){
	    System.out.println(K);
	    System.out.println("no solution");
	} else {
	    System.out.println(K);
	    System.out.println("solved");
	}
    }

}