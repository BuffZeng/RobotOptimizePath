package algorithm;

import java.util.ArrayList;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		int row = 5;
		int col = 5;
//		int initx = 1;
//		int inity = 2;
//		int finx = 10;
//		int finy = 4;
//		int[] obsx = {0, 2};
//		int[] obsy = {1, 0};

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


		System.out.println("\n\n" + "BFS Path Result");
		System.out.print("==========================================");

		BFS b = new BFS(room, row, col, initx, inity, finx, finy);

		long startTime=System.nanoTime();

		char[][] bfspath = b.getPath();

		long endTime=System.nanoTime();
		System.out.println("\nTime: "+(endTime-startTime)+"ns");

		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(bfspath[i][j]);
			}
		}


		System.out.println("\n\n" + "A* Path Result");
		System.out.print("==========================================");

		ASTAR astar = new ASTAR(room, row, col, initx, inity, finx, finy);

		startTime=System.nanoTime();

		char[][] astarpath = astar.astar();

		endTime=System.nanoTime();
		System.out.println("\nTime: "+(endTime-startTime)+"ns");

		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(astarpath[i][j]);
			}
		}

		System.out.println("\n\n" + "Dijkstra Path Result");
		System.out.print("==========================================");

		DIJKSTRA dj = new DIJKSTRA(room, row, col, initx, inity, finx, finy);

		startTime=System.nanoTime();

		char[][] dijkstrapath = dj.getPath();

		endTime=System.nanoTime();
		System.out.println("\nTime: "+(endTime-startTime)+"ns");

		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(dijkstrapath[i][j]);
			}
		}
		
		
		System.out.println("\n\n" + "DFS Path Result");
		System.out.print("==========================================");

		DFS d = new DFS(room, row, col, initx, inity, finx, finy);

		startTime=System.nanoTime();

		char[][] dfspath = d.initdfs();

		endTime=System.nanoTime();
		System.out.println("\nTime: "+(endTime-startTime)+"ns");

		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(dfspath[i][j]);
			}
		}

	}
}
