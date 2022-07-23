package algorithm;

import java.util.Arrays;

public class Room {
	
	// room row, column, obstacles' positions
	private int row;
	private int col;
	private int[] obsx;
	private int[] obsy;
	private int initx;
	private int inity;
	private int finx;
	private int finy;
	
	public Room(int row, int col, int[] obsx, int[] obsy, int initx, int inity, int finx, int finy) {
		this.row = row;
		this.col = col;
		this.obsx = obsx;
		this.obsy = obsy;
		this.initx = initx;
		this.inity = inity;
		this.finx = finx;
		this.finy = finy;
	}


	// Set up grid graph through users preference.
	public int[][] createRoom() {
		int[][] room = new int[row][col];
		// Set all as available path
		for (int[] r: room) {
		    Arrays.fill(r, 1);
		}
		for (int i = 0; i < obsx.length; i++) {
			// Set obstacles
			room[obsx[i]][obsy[i]] = 0;
		}
		return room;
	}
	
	public boolean checkPass(int[][] room) {
		if(room[initx][inity] == 0) {
			return false;
		}
		if (room[finx][finy] == 0) {
			return false;
		} 
		
		return true;
	}
}
