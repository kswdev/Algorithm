package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BOJ_16928 {
	
	private static int a;
	private static int b;
	private static Queue<Integer> q;
	private static Map<Integer, Integer> map;
	private static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		
		String[] inputSplit = input.split(" ");
		
		a = Integer.parseInt(inputSplit[0]);
		b = Integer.parseInt(inputSplit[1]);
		
		visited = new int[101];
		map = new HashMap<>();
		for(int i = 0; i < a+b; i++) {
			String input2 = br.readLine();
			String[] input2Split = input2.split(" ");
			
			map.put(Integer.parseInt(input2Split[0]), Integer.parseInt(input2Split[1]));
		}
		bfs();
		System.out.println(visited[100]);
	}
	public static void bfs() {
		int start = 1;
		q = new LinkedList<>();
		visited[start] = 0;
		q.offer(start);
		while(!q.isEmpty()) {
			int n = q.poll();
			for(int i = 1; i < 7; i++) {
				int curN = n + i;
				if(map.containsKey(curN)) curN = map.get(curN);
				if(curN <= 100 && visited[curN] == 0) {
					visited[curN] = visited[n] + 1;
					q.offer(curN);
				}
			}
		}
	}
}
