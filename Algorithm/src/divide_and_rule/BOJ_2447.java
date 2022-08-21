package divide_and_rule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2447 {
	
	private static char[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		
		arr = new char[num][num];

		star(0, 0, num, false);

		
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println("");
		}
	}
	
	public static void star(int x, int y, int num, boolean check) {
		if(check) {
			for(int i = x; i < x + num; i++) {
				for(int j = y; j < y + num; y++) {
					arr[i][j] = ' ';
				}
			}
			return;
		}
		
		if(num == 1) {
			arr[x][y] = '*';
			return;
		}
		
		int size = num/3;
		int count = 0;
		for(int i = x; i < x+num; i += size) {
			for(int j = y; j < y+num; j += size) {
				count++;
				if(count == 5) {
					star(i, j, size, true);
				} else {
					star(i, j, size, false);
				}
			}
		}
	}
}

/**
 public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		
		

		for (int i = 0; i < num; i++)
		{
			for (int j = 0; j < num; j++)
				star(i, j, num / 3);
			System.out.print("\n");
		}
	}
	
	public static void star(int x, int y, int n) {
		if ((x / n) % 3 == 1 && (y / n) % 3 == 1) System.out.print(" ");
		else
		{
			if (n == 1) System.out.print("*");
			else star(x, y, n / 3);
		}
	}
 **/
