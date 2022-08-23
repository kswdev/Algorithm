package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14889 {

	private static int num;
	private static int[][] arr;
	private static boolean[] visited;
	private static int min = 1000000000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 백트래킹
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		num = Integer.parseInt(br.readLine());
		
		arr 	= new int[num][num];
		visited = new boolean[num];
		
		for(int i = 0; i < num; i++) {
			String input = br.readLine();
			String[] inputSplit = input.split(" ");
			for(int j = 0; j < num; j++) {
				arr[i][j] = Integer.parseInt(inputSplit[j]);
			}
		}
		dfs(0, 0);
		System.out.println(min);
	}
	
	public static void dfs(int d, int count) {
		if(count == num/2) {
			int team_start = 0;
			int team_link  = 0;
			
			for(int i = 0; i < num-1; i++) {
				for(int j = i; j < num; j++) {
					if(visited[i] && visited[j]) {
						team_start += arr[i][j];
						team_start += arr[j][i];
						
					}
					
					else if(!visited[i] && !visited[j]) {
						team_link += arr[i][j];
						team_link += arr[j][i];
						
					}
				}
			}
			
			int val = Math.abs(team_link - team_start);
			
			if(val == 0) {
				min = val;
				
				return;
			}
			min = Math.min(val,min);
			
		}
		
		for(int i = d; i < num; i++) {
			if(!visited[i]) {
				visited[i] = true;
				
				dfs(i+1, count+1);
				visited[i] = false;
			}
		}
	}
}
