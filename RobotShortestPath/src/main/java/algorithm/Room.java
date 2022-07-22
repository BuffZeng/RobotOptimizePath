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
		} else if (room[finx][finy] == 0) {
			return false;
		} else if (room[0][0] == 1 && room[0][1] == 0 && room[1][0] == 0) {
			return false;
		}else if (room[0][col - 1] == 1 && room[0][col - 2] == 0 && room[1][col - 1] == 0) {
			return false;
		}else if (room[row - 1][0] == 1 && room[row - 1][1] == 0 && room[row - 2][0] == 0) {
			return false;
		}else if (room[row - 1][col - 1] == 1 && room[row - 1][col - 2] == 0 && room[row - 2][col - 1] == 0) {
			return false;
		}
		
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < col - 1; j++) {
				if (room[i][j] == 1 && room[i - 1][j] == 0 && room[i + 1][j] == 0
						&& room[i][j + 1] == 0 && room[i][j - 1] == 0) {
					return false;
				}
			}
		}
		for (int j = 1; j < col - 1; j++) {
			if(room[0][j] == 1 && room[0][j - 1] == 0 && room[0][j + 1] == 0
					&& room[1][j] == 0) {
				return false;
			}
		}
		for (int j = 1; j < col - 1; j++) {
			if(room[row - 1][j] == 1 && room[row - 1][j - 1] == 0 && room[row - 1][j + 1] == 0
					&& room[row - 2][j] == 0) {
				return false;
			}
		}
		for (int i = 1; i < row - 1; i++) {
			if (room[i][0] == 1 && room[i - 1][0] == 0 && room[i + 1][0] == 0
					&& room[i][1] == 0) {
				return false;
			}
		}
		for (int i = 1; i < row - 1; i++) {
			if (room[i][col - 1] == 1 && room[i - 1][col - 1] == 0 && room[i + 1][col - 1] == 0
					&& room[i][col - 2] == 0) {
				return false;
			}
		}
		
		return true;
	}
}
