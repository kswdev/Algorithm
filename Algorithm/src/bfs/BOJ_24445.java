package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_24445 {
	
	private static int N;
	private static int M;
	private static int R;
	
	private static Queue<Integer> q;
	private static ArrayList<Integer> list[];
	private static int[] visited;
	private static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String[] inputSplit = input.split(" ");
		
		N = Integer.parseInt(inputSplit[0]);
		M = Integer.parseInt(inputSplit[1]);
		R = Integer.parseInt(inputSplit[2]);
		
		q 	 = new LinkedList<>();
		list = new ArrayList[N];
		
		visited = new int[N];
		
		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < N; i++) {
			String input2 = br.readLine();
			String[] input2Split = input2.split(" ");
			
			int a = Integer.parseInt(input2Split[0]);
			int b = Integer.parseInt(input2Split[1]);
			
			list[a].add(b);
			list[b].add(a);
		}
		
		for(int i = 0; i < N; i++) {
			Collections.sort(list[i], Collections.reverseOrder());
		}
		
		for(int i = 1; i <= N; i++) {
			System.out.println(visited[i]);
		}
	}
	private static void bfs(int R) {
		visited[R] = cnt++;
		q.offer(R);
		while(!q.isEmpty()) {
			Integer a = q.poll();
			for(Integer b : list[a]) {
				if(visited[b] > 0) continue;
				q.offer(b);
				visited[b] = cnt++;
			}
		}
	}
}
