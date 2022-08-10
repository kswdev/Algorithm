package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1931 {

	private static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 0;
		int start = 0;
		int num   = Integer.parseInt(br.readLine());
		
		arr = new int[num][2];
		for(int i = 0; i < num; i++) {
			String input = br.readLine();
			String[] inputSplit = input.split(" ");
			arr[i][0] = Integer.parseInt(inputSplit[0]);
			arr[i][1] = Integer.parseInt(inputSplit[1]);
		}
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}
		});
		
		for(int i = 0; i < num; i++) {
			if(start <= arr[i][0]) {
				count++;
				start = arr[i][1];
			}
		}
		System.out.println(count);
	}
}
