package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_13305 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		int stationNum = Integer.parseInt(br.readLine());
		int[] distance = new int[stationNum-1];
		int[] price    = new int[stationNum];
		
		String inputDistance   = br.readLine();
		String[] distanceSplit = inputDistance.split(" ");
		String inputPrice    = br.readLine();
		String[] priceSplit  = inputPrice.split(" ");
		
		for(int i = 0; i < stationNum-1; i++) {
			distance[i] = Integer.parseInt(distanceSplit[i]);
		}
		for(int i = 0; i < stationNum; i++) {
			price[i] = Integer.parseInt(priceSplit[i]);
		}
		
		long result = 0;
		int start   = price[0];
		
		for(int i = 0; i < stationNum-1; i++) {
			if(price[i] < start) {
				start = price[i];
			}
			result += start*distance[i];
		}
		System.out.println(result);
	}
}

/*acmicpc.net/problem/13305*/
