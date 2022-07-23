package algorithm;

public class TestDIJKSTRA {
	
	public static void main(String[] args) {
		int row = 5;
		int col = 5;
		int initx = 0;
		int inity = 1;
		int finx = 3;
		int finy = 0;
		int[] obsx = {0, 1, 2, 4, 4};
		int[] obsy = {2, 2, 3, 0, 1};

		Room r = new Room(row, col, obsx, obsy, initx, inity, finx, finy);
		int[][] room = r.createRoom();
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(room[i][j]);
			}
		}

		DIJKSTRA dj = new DIJKSTRA(room, row, col, initx, inity, finx, finy);
				
		char[][] path = dj.getPath();
		
		System.out.println();
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(path[i][j] + " ");
			}
		}
	}

}
