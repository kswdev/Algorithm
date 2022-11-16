package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_11725 {

	public static int N;
	public static List<Integer>[] list;
	public static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N 		= Integer.parseInt(br.readLine());
		list 	= new ArrayList[N+1];
		parents = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < N-1; i++) {
			String node = br.readLine();
			String[] nodeSplit = node.split(" ");
			
			int a = Integer.parseInt(nodeSplit[0]);
			int b = Integer.parseInt(nodeSplit[1]);
			
			list[a].add(b);
			list[b].add(a);
		}
		
		dfs(0, 1);
		
		for(int i = 2; i <= N; i++) {
			System.out.println(parents[i]);
		}
	}
	
	public static void dfs(int parent, int start) {
		parents[start] = parent;

		for(int child : list[start]) {
			if(child == parent) continue;
			dfs(start, child);
		}
	}
}
