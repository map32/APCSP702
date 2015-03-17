import java.io.*;
import java.util.Scanner;

public class LakeMaking{
    int R, C, E, N;

    int[][] map;
    int[][] instructions;

    File file;

    public LakeMaking() throws FileNotFoundException{
	file = new File("lakemake.in");
	

	try {
	    Scanner sc = new Scanner(file);
	    int i=0;
	    while(sc.hasNextLine()) {
		if(i==0){
		    R=sc.nextInt();
		    C=sc.nextInt();
		    E=sc.nextInt();
		    N=sc.nextInt();
		    map = new int[R][C];
		    instructions = new int[N][3];
		} else if(i<R+1) {
		    for(int r=0;r<R;r++){
			for(int c=0;c<C;c++){
			    map[r][c]=sc.nextInt();
			}
		    }
		} else {
		    instructions[i-R-1][0]=sc.nextInt();
		    instructions[i-R-1][1]=sc.nextInt();
		    instructions[i-R-1][2]=sc.nextInt();
		}
		i++;
	    }
	}
	catch (FileNotFoundException e){
	    e.printStackTrace();
	}
	execute();
    }

    public void execute(){
	for(int i=0;i<instructions.length;i++){
	    int k = largest(instructions[i][0],instructions[i][1]);
	    for(int r=instructions[i][0]-1;r<instructions[i][0]+2;r++){
		for(int c=instructions[i][1]-1;c<instructions[i][1]+2;c++){
		    if(!(map[r][c]<k-instructions[i][2])){
			map[r][c]=k-instructions[i][2];
		    }
		}
	    }
	}
	output();
    }

    public int largest(int r, int c){
	int k=map[r][c];
	for(int i=0;i<3;i++){
	    for(int j=0;j<3;j++){
		if(k<map[i+r][j+c]){
		    k=map[i+r][j+c];
		}
	    }
	}
	return k;
    }

    public void output(){
	int sum=0;
	for(int r=0;r<R;r++){
	    for(int c=0;c<C;c++){
		if(E>map[r][c]){
		    sum+=(E-map[r][c])*36*36;
		}
	    }
	}
	try {
	    file = new File("lakemake.out");
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write(sum);
	    writer.close();
	} catch (Exception e){
	    e.printStackTrace();
	}
    }
}