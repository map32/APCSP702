import java.lang.*;

public class NQueens{
    
    final static String clear =  "\033[2J";
    final static String hide =  "\033[?25l";
    final static String show =  "\033[?25h";

    private boolean[][] board;
    private int[][] coords;

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

    public NQueens(int size){
	board = new boolean[size][size];
	coords = new int[size][2];
	for(int i=0;i<size;i++){
	    coords[i][0]=-1;
	    coords[i][1]=-1;
	}
    }

    public String name(){
	return "shin.dong";
    }

    public String toString(){
	String ans = "";
	for(int i=0;i<board[0].length;i++){
	    
	    ans += "\n";
	    for(int j=0;j<board[0].length;j++){
		if(board[i][j]==false){
		    boolean s = false;
		    /*for(int k=0;k<board[0].length;k++){
			if(coords[k][0]==j && coords[k][1]==i){
			    ans += k+"T";
			    s=true;
			}
			}*/
		    if(s==false){
			ans += "  ";
		    }
		} else {
		    ans += " Q";
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
	return hide + go(0,0) + ans + "\n" + show;
    }

    public boolean solve(){
	return solve2(0,0,0);
    }

    public boolean solve(int x){
	return solve2(x,0,0) || solve2(0,x,0);
    }

    public boolean solve2(int x, int y, int n){
	for(int i=0;i<n;i++){
	    if(y==coords[i][0] || x==coords[i][1] || Math.abs(y-coords[i][0])==Math.abs(x-coords[i][1])){
		return false;
	    }
		
	}
	coords[n][0]=y;
	coords[n][1]=x;
	//System.out.println(this);
	//System.out.println(y+","+x+","+n);
	if(n+1==board[0].length){
	    board[y][x]=true;
	    return true;
	} else {
	    for(int j=0;j<board.length;j++){
		for(int i=0;i<board.length;i++){
		    //wait(30);
		    if (solve2(j,i,n+1)){
			board[y][x]=true;
			return true;
		    }
		}
	    }
	}
	return false;
    }
    
    public static void main(String[] args){
	NQueens N = new NQueens(Integer.parseInt(args[0]));
	if(N.solve(1)){
	    System.out.println(N);
	    System.out.println("yeeeeeeeeeeey solved xDDDDDDDDDDDDDDDDDDDDDDDDDDD");
	}
    }
    

}
