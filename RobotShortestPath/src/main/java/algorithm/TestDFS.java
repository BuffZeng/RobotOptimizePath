package algorithm;

import java.util.ArrayList;
import java.util.Random;

public class TestDFS {
	
	public static void main(String[] args) {
		int row = 5;
		int col = 5;
//		int initx = 2;
//		int inity = 1;
//		int finx = 4;
//		int finy = 4;
//		int[] obsx = {0, 2, 4, 3};
//		int[] obsy = {1, 0, 3, 4};

		ArrayList<int[]> obs = new ArrayList<>();

		int[] tmp;
		Random random = new Random();
		
		int initx = random.nextInt(row);
		int inity = random.nextInt(col);
		int finx = random.nextInt(row);
		int finy = random.nextInt(col);
		
//		int rnd = random.nextInt(row * col);
//		System.out.println("Number of Obstacles: " + rnd);
		for(int i = 0; i < 5; i++) {
			tmp = new int[2];
			tmp[0] = random.nextInt(row);
			tmp[1] = random.nextInt(col);
			obs.add(tmp);
		}
		
		int[] obsx = new int[obs.size()];
		int[] obsy = new int[obs.size()];
		for(int i = 0; i < obs.size(); i++) {
			obsx[i] = obs.get(i)[0];
			obsy[i] = obs.get(i)[1];
		}

		Room r = new Room(row, col, obsx, obsy, initx, inity, finx, finy);
		int[][] room = r.createRoom();

		System.out.println("Start Point: (" + initx + ", " + inity + ")");
		System.out.println("End Point: (" + finx + ", " + finy + ")");

		System.out.println("Room Layout");
		System.out.print("==========================================");
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(room[i][j]);
			}
		}
		
		System.out.println("\nRoom Validation: " + r.checkPass(room));
		
		DFS d = new DFS(room, row, col, initx, inity, finx, finy);


		char[][] dfspath = d.initdfs();

		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(dfspath[i][j]);
			}
		}
				
	}

}
