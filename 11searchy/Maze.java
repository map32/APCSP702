import java.util.*;
import java.io.*;

public class Maze{
    private char[][]maze;
    private int maxx,maxy;
    private int startx,starty;
    private MyDeque<LNode<Coordinate>> frontier;
    private ArrayList<Coordinate> path;
    private LNode<Coordinate> start;
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
	frontier = new MyDeque<LNode<Coordinate>>();
	path = new ArrayList<Coordinate>();
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

    public char add(char c){
	if(c==' '){
	    return '1';
	} else {
	    if(Character.getNumericValue(c)==-1 || c=='S'){
		return c;
	    }
	    return (char)(Character.getNumericValue(c)+1+48);
	}
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
	LNode<Coordinate> c = new LNode<Coordinate>(new Coordinate(startx,starty));
	c.setNext(null);
	start = c;
	return solveBFS2(c,animate);
    }

    public boolean solveBFS2(LNode<Coordinate> c, boolean animate){
	Coordinate up, down, left, right = null;
	char current = maze[c.getValue().x()][c.getValue().y()];
	LNode<Coordinate> now;
	
	if(animate){
	    System.out.println(toString(animate));
	    System.out.println(""+c.getValue().x()+","+c.getValue().y()+","+current);
	}
	
	if(current=='E'){
	    now = c;
	    while(now!=null){
		//System.out.println(now.getValue());
		path.add(0,now.getValue());
		now=now.getNext();
	    }
	    System.out.println(toString());
	    return true;
	} else if(current=='x' || current=='#'){
	    //maze[c.getValue().x()][c.getValue().y()]='x';
	    try {
		return solveBFS2(frontier.removeLast(),animate);
	    } catch (NoSuchElementException e){
		return false;
	    }
	}
        maze[c.getValue().x()][c.getValue().y()]='x';
	if(c.getValue().x()>0){
	    left = new Coordinate(c.getValue().x()-1,c.getValue().y());
	    now = new LNode<Coordinate>(left);
	    now.setNext(c);
	    frontier.addFirst(now);
	}
	if(c.getValue().x()<maxx-1){
	    right = new Coordinate(c.getValue().x()+1,c.getValue().y());
	    now = new LNode<Coordinate>(right);
	    now.setNext(c);
	    frontier.addFirst(now);
	}
	if(c.getValue().y()>0){
	    up = new Coordinate(c.getValue().x(),c.getValue().y()-1);
	    now = new LNode<Coordinate>(up);
	    now.setNext(c);
	    frontier.addFirst(now);
	}
	if(c.getValue().y()<maxy-1){
	    down = new Coordinate(c.getValue().x(),c.getValue().y()+1);
	    now = new LNode<Coordinate>(down);
	    now.setNext(c);
	    frontier.addFirst(now);
	}
	try {
	    return solveBFS2(frontier.removeLast(),animate);
	} catch (NoSuchElementException e){
	    return false;
	}
    }


    /**Solve the maze using a frontier in a DFS manner. 
     * When animate is true, print the board at each step of the algorithm.
     * Replace spaces with x's as you traverse the maze. 
     */

    public boolean solveDFS(boolean animate){
	LNode<Coordinate> c = new LNode<Coordinate>(new Coordinate(startx,starty));
	c.setNext(null);
	start = c;
	return solveDFS2(c,animate);
    }

    public boolean solveDFS2(LNode<Coordinate> c, boolean animate){
	Coordinate up, down, left, right = null;
	char current = maze[c.getValue().x()][c.getValue().y()];
	LNode<Coordinate> now;
	
	if(animate){
	    System.out.println(toString(animate));
	    System.out.println(""+c.getValue().x()+","+c.getValue().y()+","+current);
	}
	
	if(current=='E'){
	    now = c;
	    while(now!=null){
		//System.out.println(now.getValue());
		path.add(0,now.getValue());
		now=now.getNext();
	    }
	    System.out.println(toString());
	    return true;
	} else if(current=='x' || current=='#'){
	    //maze[c.getValue().x()][c.getValue().y()]='x';
	    try {
		return solveDFS2(frontier.removeLast(),animate);
	    } catch (NoSuchElementException e){
		return false;
	    }
	}
        maze[c.getValue().x()][c.getValue().y()]='x';
	if(c.getValue().x()>0){
	    left = new Coordinate(c.getValue().x()-1,c.getValue().y());
	    now = new LNode<Coordinate>(left);
	    now.setNext(c);
	    frontier.addLast(now);
	}
	if(c.getValue().x()<maxx-1){
	    right = new Coordinate(c.getValue().x()+1,c.getValue().y());
	    now = new LNode<Coordinate>(right);
	    now.setNext(c);
	    frontier.addLast(now);
	}
	if(c.getValue().y()>0){
	    up = new Coordinate(c.getValue().x(),c.getValue().y()-1);
	    now = new LNode<Coordinate>(up);
	    now.setNext(c);
	    frontier.addLast(now);
	}
	if(c.getValue().y()<maxy-1){
	    down = new Coordinate(c.getValue().x(),c.getValue().y()+1);
	    now = new LNode<Coordinate>(down);
	    now.setNext(c);
	    frontier.addLast(now);
	}
	try {
	    return solveDFS2(frontier.removeLast(),animate);
	} catch (NoSuchElementException e){
	    return false;
	}
    }

    public boolean solveBFS(){
	 return solveBFS(false);
     }
    public boolean solveDFS(){
	return solveDFS(false);
    }

    public int[] solutionCoordinates(){
	int[] pa = new int[path.size()*2];
	for(int i=0;i<path.size();i++){
	    Coordinate c = path.get(i);
	    pa[2*i]=c.x();
	    pa[2*i+1]=c.y();
	}
	//System.out.println(pa);
	return pa;
    }

    public String name(){
	return "shin.dong";
    }

    public static void main(String[]args){
	Maze m = new Maze("data3.dat");
	m.solveDFS(false);
	m.pathy();
	int[] s = m.solutionCoordinates();
	for(int i : s){
	    System.out.println(i);
	}
	//System.out.println();
    }

    public void pathy(){
	for(int i=0;i<path.size();i++){
	    System.out.println(path.get(i).toString());
	}
    }

}

/**class Frontier{
    public MyDeque<Coordinate> frontier;
    public MyDeque<LNode> node;
    
    public void add(Coordinate c){
    }
    public Coordinate remove(){
	return frontier.removeLast();
    }
    }**/

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
