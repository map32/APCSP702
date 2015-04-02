import java.util.*;
import java.io.*;

public class Maze{
    private char[][]maze;
    private int maxx,maxy;
    private int startx,starty;
    private MyDeque frontier;
    private ArrayList<Coordinate> path;
    private static final String clear =  "\033[2J";
    private static final String hide =  "\033[?25l";
    private static final String show =  "\033[?25h";
    private String go(int x,int y){
return ("\033[" + x + ";" + y + "H");
    }

    /** Same constructor as before...*/
    public Maze(String filename){
	startx = -1;
	starty = -1;
	frontier = new MyDeque();
	//read the whole maze into a single string first
	String ans = "";
	try{
	    Scanner in = new Scanner(new File(filename));
	    
	    //keep reading next line
	    while(in.hasNext()){
		String line = in.nextLine();
		if(maxy == 0){
		    //calculate width of the maze
		    maxx = line.length();
		}
		//every new line add 1 to the height of the maze
		maxy++;
		ans += line;
	    }
	}
	catch(Exception e){
	    System.out.println("File: " + filename + " could not be opened.");
	    e.printStackTrace();
	    System.exit(0);
	}

	
	//copy from the single string to a 2D array
	maze = new char[maxx][maxy];
	for(int i = 0; i < ans.length(); i++){
	    char c = ans.charAt(i);
	    maze[i % maxx][i / maxx] = c;
	    if(c == 'S'){
		startx = i % maxx;
		starty = i / maxx;
	    }
	}
    }

    public String toString(){
	String s = new String();
	for(int i=0;i<maze.length;i++){
	    for(int j=0;j<maze[0].length;j++){
		s+=Character.toString(maze[i][j]);
	    }
	    s+="\n";
	}	
	return s;
    }

    public String toString(boolean animate){
	if(animate){
	    String ans = toString();
	    return hide + clear + ans + "\n" + show;	    
	} else {
	    return toString();
	}
    }

    /**Solve the maze using a frontier in a BFS manner. 
     * When animate is true, print the board at each step of the algorithm.
     * Replace spaces with x's as you traverse the maze. 
     */
    public boolean solveBFS(boolean animate){
	Coordinate c = new Coordinate(startx,starty);
	return solveBFS2(c,animate);
    }

    public boolean solveBFS2(Coordinate c, boolean animate){
	Coordinate up, down, left, right = null;
	char current = maze[c.x()][c.y()];
	
	if(current=='E'){
	    return true;
	} else if(current=='x'||current=='#'){
	    return false;
	}
	maze[c.x()][c.y()]='x';
	if(c.x()>0){
	    left = new Coordinate(c.x()-1,c.y());
	    frontier.addFirst(left);
	}
	if(c.x()<maxx-1){
	    right = new Coordinate(c.x()+1,c.y());
	    frontier.addFirst(right);
	}
	if(c.y()>0){
	    up = new Coordinate(c.x(),c.y()-1);
	    frontier.addFirst(up);
	}
	if(c.y()<maxy-1){
	    down = new Coordinate(c.x(),c.y()+1);
	    frontier.addFirst(down);
	}
	return solveBFS2((Coordinate)frontier.removeLast(),animate);
    }


    /**Solve the maze using a frontier in a DFS manner. 
     * When animate is true, print the board at each step of the algorithm.
     * Replace spaces with x's as you traverse the maze. 
     */
    //public boolean solveDFS(boolean animate){    }

    /** public boolean solveBFS(){
return solveBFS(false);
    }
    public boolean solveDFS(){
return solveDFS(false);
    }
    }**/
}
class Coordinate {
    private int x;
    private int y;
    public Coordinate(){
	x=0;
	y=0;
    }
    public Coordinate(int xx, int yy){
	x=xx;
	y=yy;
    }
    public int x(){
	return x;
    }

    public int y(){
	return y;
    }

    public void setx(int xx){
	x=xx;
    }

    public void sety(int yy){
	y=yy;
    }

    public void set(int xx, int yy){
	x=xx;
	y=yy;
    }

    public String toString(){
	return "("+x+","+y+")";
    }
}
