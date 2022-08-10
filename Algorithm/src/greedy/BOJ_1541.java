package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1541 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int result = 0;
		String[] inputSplit = input.split("\\-");
		ArrayList<Integer> al = new ArrayList<>();
		for(int i = 0; i < inputSplit.length; i++) {
			int sum = 0;
			String[] numSplit = inputSplit[i].split("\\+");
			for(int j = 0; j < numSplit.length; j++) {
				sum += Integer.parseInt(numSplit[j]);
			}
			al.add(sum);
		}
		result = al.get(0);
		for(int i = 1; i < al.size(); i++) {
			result -=al.get(i);
		}
		System.out.println(result);
	}
}
