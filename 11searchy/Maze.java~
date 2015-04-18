import java.util.*;
import java.io.*;
import java.lang.Thread;

public class Maze{
    private char[][]maze;
    private int maxx,maxy;
    private int startx,starty;
    private int endx,endy;
    private int steps;
    private Frontier frontier;
    private ArrayList<Coordinate> path;
    private LNode<Coordinate> currentNode;
    private final int BFS = 0;
    private final int DFS = 1;
    private final int Best = 2;
    private final int AStar = 3;
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
	steps=0;
	frontier = new Frontier();
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
	    if(c == 'E'){
		endx = i % maxx;
		endy = i / maxx;
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
        return presolve(animate,BFS);
    }

    public boolean solveDFS(boolean animate){
        return presolve(animate,DFS);
    }

    public boolean solveBest(boolean animate){
        return presolve(animate,Best);
    }

    public boolean solveAStar(boolean animate){
        return presolve(animate,AStar);
    }

    public boolean presolve(boolean animate, int mode){
	currentNode = new LNode<Coordinate>(new Coordinate(startx,starty));
        int solved = 0;
	char current;
	currentNode.setNext(null);
	while(solved==0){
	    solved = solve(currentNode,animate,mode);
	}
        if(solved==1)
	    return true;
	else
	    return false;
    }

    public int solve(LNode<Coordinate> c, boolean animate, int mode){
	//System.out.println(""+c.getValue().x()+","+c.getValue().y());
	char current = maze[c.getValue().x()][c.getValue().y()];
	LNode<Coordinate> now;
	
	if(animate){
	    System.out.println(toString(false));
	    //System.out.println(""+c.getValue().x()+","+c.getValue().y()+","+current);
	    //System.out.println(frontier.toString());
	    /**try{
		Thread.sleep(1);
	    }catch(Exception e){
	    }**/
	}
	steps++;
	if(current=='E'){
	    addPath(c);
	    System.out.println(toString());
	    return 1;
	}/** else if(current=='#'){
	    try {
		if(mode==BFS)
		    currentNode=frontier.removeLast();
		else if(mode==DFS)
		    currentNode=frontier.removeFirst();
		else if(mode==Best || mode==AStar)
		    currentNode=frontier.removeSmallest();
		return 0;
	    } catch (NoSuchElementException e){
		System.out.println(toString());
		return -1;
	    }
	    }**/
	if(steps!=1){
	    maze[c.getValue().x()][c.getValue().y()]='x';
        }
	getNeighbors(c,mode);
	try {
	    if(mode==BFS)
		currentNode=frontier.removeLast();
	    else if(mode==DFS)
		currentNode=frontier.removeFirst();
	    else if(mode==Best || mode==AStar)
		currentNode=frontier.removeSmallest();
	} catch (NoSuchElementException e){
	    System.out.println(toString());
	    return -1;
	}
	return 0;
    }

    public void addPath(LNode<Coordinate> start){
	char m[][] = new char[maxx][maxy];
	path.clear();
	while(start!=null){
	    path.add(0,start.getValue());
	    m[start.getValue().x()][start.getValue().y()]='x';
	    start=start.getNext();
	}
	System.out.println(path.toString());
        for(int i=0;i<maxx;i++){
	    for(int j=0;j<maxy;j++){
		System.out.print(m[i][j]);
	    }
	    System.out.println();
	}
    }

    public void getNeighbors(LNode<Coordinate> c, int mode){
	LNode<Coordinate> now;
	int priority=0;
	int x = c.getValue().x();
	int y = c.getValue().y();
	char u = maze[x][y-1];
	char d = maze[x][y+1];
	char l = maze[x-1][y];
	char r = maze[x+1][y];
	if(mode==Best||mode==AStar){
	    priority=Math.abs(x-endx)+Math.abs(y-endy);}
	if(mode==AStar){
	    priority+=steps;
	}
	if(x>0 && (l==' '||l=='E') ){
	    Coordinate left = new Coordinate(x-1,y);
	    now = new LNode<Coordinate>(left);
	    now.setNext(c);
	    frontier.add(now,priority);
	}
	if(x<maxx-1 && (r==' '||r=='E')){
	    Coordinate right = new Coordinate(x+1,y);
	    now = new LNode<Coordinate>(right);
	    now.setNext(c);
	    frontier.add(now,priority);
	}
	if(y>0 && (u==' '||u=='E')){
	    Coordinate up = new Coordinate(x,y-1);
	    now = new LNode<Coordinate>(up);
	    now.setNext(c);
	    frontier.add(now,priority);
	}
	if(y<maxy-1 && (d==' '||d=='E')){
	    Coordinate down = new Coordinate(x,y+1);
	    now = new LNode<Coordinate>(down);
	    now.setNext(c);
	    frontier.add(now,priority);
	}
    }


    /**Solve the maze using a frontier in a DFS manner. 
     * When animate is true, print the board at each step of the algorithm.
     * Replace spaces with x's as you traverse the maze. 
     */


    public boolean solveBFS(){
	return solveBFS(false);
    }
    public boolean solveDFS(){
	return solveDFS(false);
    }
    public boolean solveBest(){
	return solveBest(false);
    }
    public boolean solveAStar(){
	return solveAStar(false);
    }

    public int getSteps(){
	return steps;
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
	if (args.length==0){
	    System.out.println("please enter the map name");
	    return;
	}
	Maze m = new Maze(args[0]);
	System.out.println("DFS");
	m.solveDFS(false);
        System.out.println(m.getSteps());
	System.out.println("\nBFS");
        m = new Maze(args[0]);
	m.solveBFS(true);
        System.out.println(m.getSteps());
	System.out.println("\nBest");
        m = new Maze(args[0]);
	m.solveBest(false);
        System.out.println(m.getSteps());
	System.out.println("\nA*");
        //m = new Maze(args[0]);
	//m.solveAStar(true);
        //System.out.println(m.getSteps());
	//m.pathy();
	/**int[] s = m.solutionCoordinates();
	for(int i : s){
	    System.out.println(i);
	    }**/
	//System.out.println();
    }

}

class Frontier{
    private MyDeque<LNode<Coordinate>> frontier;
    private ArrayList<LNode<Coordinate>> display;

    public Frontier(){
	frontier = new MyDeque<LNode<Coordinate>>();
	display = new ArrayList<LNode<Coordinate>>();
    }

    public void add(LNode<Coordinate> c, int priority){
	frontier.add(c,priority);
    }
    public LNode<Coordinate> removeLast() throws NoSuchElementException{
	return frontier.removeLast();
    }
    public LNode<Coordinate> removeFirst() throws NoSuchElementException{
	return frontier.removeFirst();
    }
    public LNode<Coordinate> removeSmallest() throws NoSuchElementException{
	return frontier.removeSmallest();
    }

    public String toString(){
	String ans = "";
	ans = frontier.toString();
	return ans;
    }
    
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
