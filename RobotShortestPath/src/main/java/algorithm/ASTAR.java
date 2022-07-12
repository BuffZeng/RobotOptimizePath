package algorithm;

public class ASTAR {
	
	private int[][] room;
	private int row;
	private int col;
	private int initx;
	private int inity;
	private int finx;
	private int finy;
	// right, down, left, up
	private int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};

	public ASTAR(int[][] room, int row, int col, int initx, int inity, int finx, int finy) {
		this.room = room;
		this.row = row;
		this.col = col;
		this.initx = initx;
		this.inity = inity;
		this.finx = finx;
		this.finy = finy;
	}
	
	public void astar() {
		
	}

}
