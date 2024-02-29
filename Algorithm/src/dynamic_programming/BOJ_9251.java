package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9251 {

	private static int[][] map;
	private static int[] dp;
	private static char[] input;
	private static char[] input2;
	private static int N,M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input  = br.readLine().toCharArray();
		input2 = br.readLine().toCharArray();

		N = input.length;
		M = input2.length;

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (input[i] == input2[j])
					map[i][j] = 1;
			}
		}

		int count = countLCS();
		System.out.println(count);
	}

	private static int countLCS() {
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
			}
		}


		return map[N-1][M-1];
	}
}
