import java.io.*;
import java.util.Scanner;

public class Silver{
    int R, C, S;

    int N;

    String[] map;
    int[][] instructions = new int[2][2];

    File file;

    public Silver() throws FileNotFoundException{
	file = new File("ctravel.in");
	try {
	    Scanner sc = new Scanner(file);
	    int i=0;
	    while(sc.hasNextLine()) {
		if(i==0){
		    R=sc.nextInt();
		    C=sc.nextInt();
		    S=sc.nextInt();
		    map = new String[R];
		    sc.nextLine();
		} else if(i<R+1) {
		    for(int r=0;r<R;r++){
			map[r]=sc.nextLine();
			System.out.println(""+i+r+map[r]);
			i++;
		    }
		    //sc.nextLine();
		} else {
		    if(sc.hasNextInt()){
			instructions[0][0]=sc.nextInt();
			instructions[0][1]=sc.nextInt();
			instructions[1][0]=sc.nextInt();
			instructions[1][1]=sc.nextInt();
			System.out.println(""+instructions[0][0]+instructions[0][1]+instructions[1][0]+instructions[1][1]);
		    }
		}
		i++;
	    }
	}
	catch (FileNotFoundException e){
	    //e.printStackTrace();
	}
	execute();
    }

    public void execute(){
	calculate(instructions[0][0],instructions[0][1],0);
	output();
    }
    
    public boolean calculate(int r, int c, int z){
	try{
	if(z>S || map[r-1].substring(c-1,c).equals("*")){
	    return false;
	} else {
	    if(z==S && r==instructions[1][0] && c==instructions[1][1]){
		N++;
		System.out.println(N);
		return true;
	    } else {
		return calculate(r-1,c,z+1) || calculate(r+1,c,z+1) || calculate(r,c-1,z+1) || calculate(r,c+1,z+1);
	    }
	}
	} catch (ArrayIndexOutOfBoundsException e){
	    //return false;
	    //e.printStackTrace();
	} catch (StringIndexOutOfBoundsException e){
	}
	return false;
    }

    public void output(){
	System.out.println(""+R+" "+C+" "+S+" "+N);
	try {
	    file = new File("ctravel.out");
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write(N);
	    writer.close();
	} catch (Exception e){
	    e.printStackTrace();
	}
    }

    public static void main(String[] ab) throws FileNotFoundException{
	Silver A = new Silver();
    }
}