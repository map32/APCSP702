import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LakeMaking{
    int R, C, E, N;

    int[][] map;
    int[][] instructions;

    File file;

    public LakeMaking(){
	file = new File("lakemake.in");
	
	Scanner sc = new Scanner(file);

	try {
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
		    for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
			    map[i][j]=sc.nextInt();
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
    }

    public void execute(){
    }

}