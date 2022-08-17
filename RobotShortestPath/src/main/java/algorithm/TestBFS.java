package algorithm;

public class TestBFS {

	public static void main(String[] args) {
		int row = 5;
		int col = 9;
		int initx = 0;
		int inity = 0;
		int finx = 0;
		int finy = 3;
		int[] obsx = {};
		int[] obsy = {};

		Room r = new Room(row, col, obsx, obsy, initx, inity, finx, finy);
		int[][] room = r.createRoom();
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(room[i][j]);
			}
		}

		BFS b = new BFS(room, row, col, initx, inity, finx, finy);
		
		char[][] path = b.getPath();
		
		System.out.println();
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(path[i][j]);
			}
		}
	}
}
