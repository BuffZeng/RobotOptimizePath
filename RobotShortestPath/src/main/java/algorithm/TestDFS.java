package algorithm;

import java.util.Arrays;

public class TestDFS {
	
	public static void main(String[] args) {
		int row = 5;
		int col = 5;
		int initx = 2;
		int inity = 1;
		int finx = 4;
		int finy = 4;
		int[] obsx = {0, 2};
		int[] obsy = {1, 0};
		
		Room r = new Room(row, col, obsx, obsy);
		int[][] room = r.createRoom(row, col, obsx, obsy);
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(room[i][j]);
			}
		}
		
		// Create a 2D array record the path.
		char[][] record = new char[row][col];
		for (char[] rec : record) {
		    Arrays.fill(rec, '-');
		}
		
		// Create a 2D array record visited nodes.
		int[][] visit = new int[row][col];
		for (int[] v: visit) {
		    Arrays.fill(v, 0);
		}
		
		DFS d = new DFS(room, row, col, initx, inity, finx, finy);
		d.dfs(initx, inity, record, visit);
		
		System.out.println();
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(record[i][j]);
			}
		}
				
	}

}
