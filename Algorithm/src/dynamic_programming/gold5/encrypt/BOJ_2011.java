package dynamic_programming.gold5.encrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2011 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pwd = br.readLine();
        int MOD = 1000000;

        int[] dp = new int[pwd.length()+1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= pwd.length(); i++) {

            int now = pwd.charAt(i-1) - '0';
            int prev = pwd.charAt(i-2) - '0';

            if(1 <= now && now <= 9)
                dp[i] = dp[i-1] % MOD;

            if(prev == 0) continue;

            int value = (prev * 10) + now;

            if(value >= 10 && value <= 26) {
                dp[i] += dp[i-2] % MOD;
            }
        }

        if (pwd.charAt(0) == '0')
            System.out.println(0);
        else
            System.out.println(dp[pwd.length()] % MOD);
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