package algorithm;

public class TestBFS {

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

		BFS b = new BFS(room, row, col, initx, inity, finx, finy);
		
		int[][] dis = b.bfs();
		char[][] path = b.getPath(dis);
		
		System.out.println();
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(dis[i][j] + "\t");
			}
		}
		
		System.out.println();
		for(int i = 0; i < row; i++) {
			System.out.println();
			for(int j = 0; j < col; j++) {
				System.out.print(path[i][j]);
			}
		}
	}
}
