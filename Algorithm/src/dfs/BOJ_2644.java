package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2644 {
	public static int N;
	public static int M;
	public static int[][] chon;
	public static List list[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num 	= Integer.parseInt(br.readLine());
		String rel  = br.readLine();
		list = new ArrayList[num+1];
		
		for(List<Integer> li : list) {
			li = new ArrayList<Integer>();
		}
		
		String[] relSplit = rel.split(" ");
		
		N = Integer.parseInt(relSplit[0]);
		M = Integer.parseInt(relSplit[1]);
		
		int number = Integer.parseInt(br.readLine());
		chon = new int[num + 1][num + 1];
		for(int i = 0; i < number; i++) {
			String str = br.readLine();
			
			String[] strSplit = str.split(" ");
			
			int a = Integer.parseInt(strSplit[0]);
			int b = Integer.parseInt(strSplit[1]);
			
			list[a].add(b);
			list[b].add(a);
		}
		
		dfs(0, 1, 1);
		
	}
	
	public static void dfs(int from, int to, int ch) {
		chon[from][to] = ch;
		chon[to][from] = ch;
		
		ArrayList<Integer> li = (ArrayList<Integer>) list[from];
	}
}
