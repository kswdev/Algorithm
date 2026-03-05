package dynamic_programming.gold1.candy;

/*
    문제.
        다솜이는 슈퍼에서 사탕을 사려고 한다.
        슈퍼에는 사탕이 N종류가 있다.
        각각의 사탕은 가격이 있다.
        다솜이는 사탕을 사는데, 사탕의 가격의 합이 소수가 되게하려고 한다.
        가격이 같은 사탕은 모양이 같게 생겼다.
        따라서 다솜이는 사탕을 적절히 샀을 때, 그 모양이 전부 똑같은 방법은 사지 않으려고 한다.

    ex)
        예를 들어,
            (1, 2, 1, 3, 1), (3, 1, 1, 1, 2)
        사는 것은 같은 방법이다.
        따라서 한 번만 세야 한다.

        예를 들어,
            (1, 1), (1, 1, 1), (1, 1, 1, 1, 1), (1, 1, 1, 1, 1, 1, 1)
        사는 것은 같은 방법이 아니다.
        따라서 네 번 세야 한다.

    입력.
        첫째 줄에 슈퍼에 있는 사탕의 개수 N이 주어진다.
        N은 50보다 작거나 같은 자연수이다.
        둘째 줄부터 N개의 줄에 각 사탕의 가격이 주어진다.
        사탕의 가격은 10,000보다 작거나 같은 음이 아닌 정수이다.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1415 {

    private static int N;
    private static List<Integer> candyCount = new ArrayList<>();
    private static List<Integer> candyValue = new ArrayList<>();

    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int sum = 0;

        for (int i = 0; i < N; i++) {
            int price = Integer.parseInt(br.readLine());
            if (candyValue.contains(price)) {
                int index = candyValue.indexOf(price);
                candyCount.set(index, candyCount.get(index) + 1);
            } else {
                candyValue.add(price);
                candyCount.add(1);
            }
            sum += price;
        }

        dp = new long[sum+1][candyValue.size()+1];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        long result = 0;

        for (int i = 2; i <= sum; i++) {
            if (isPrime(i)) {
                result += solve(i, 0);
            }
        }

        System.out.println(result);
    }

    // 사탕 가격의 합이 n 일 때 조합 갯수
    private static long solve(int n, int start) {

        if (n == 0) return 1;
        if (n < 0) return 0;
        if (start == candyCount.size()) return 0;

        if (dp[n][start] != -1) {
            return dp[n][start];
        }

        long result = 0;

        int count = candyCount.get(start);
        int price = candyValue.get(start);

        for (int i = 0; i <= count; i++) {
            result += solve(n - (price*i), start+1);
        }

        return dp[n][start] = result;
    }


    private static boolean isPrime(int n) {
        int sqrt = (int) Math.sqrt(n);

        for (int i = 2; i <= sqrt; i++)
            if (n % i == 0) return false;

        return true;
    }
}
