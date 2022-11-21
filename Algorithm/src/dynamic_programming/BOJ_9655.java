package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9655 {
   public static int N;
   public static String[] dp;
   public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      N = Integer.parseInt(br.readLine());
      
      if(N % 2 == 0) System.out.println("CY");
      else System.out.println("SK");
      
   }
}

/**
 * 
     DP[0] = 0;
   DP[1] = 1;
   DP[2] = 2;

   for (int i = 3; i <= N; i++) {
      DP[i] = min(DP[i - 1] + 1, DP[i - 3] + 1);
 * 
 **/