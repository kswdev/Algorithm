package dynamic_programming.gold5.encrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2011 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pwd = br.readLine();

        int[] dp = new int[pwd.length()];

        for (int i = 0; i < pwd.length(); i++) {
            int target = Integer.parseInt(pwd.substring(0, i));

            if (i == 0) dp[i] = 1;
            else {
                dp[i] = dp[i-1]+1;
                if (target <= 26) dp[i] += 1;
            }
        }
    }
}

/**
 * 2
 */

/**
 * 2/5
 * 25
 */

/**
 * 2/5/1
 * 25/1
 */

/**
 * 2/5/1/1
 * 25/1/1

 * 2/5/11
 * 25/11
 */

/**
 * 2/5/1/1/4
 * 2/5/11/4
 * 25/1/1/4
 * 25/11/4

 * 25/1/14
 * 2/5/1/14
 */