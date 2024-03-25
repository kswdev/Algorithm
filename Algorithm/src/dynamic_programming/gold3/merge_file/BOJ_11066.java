package dynamic_programming.gold3.merge_file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11066 {

    private static int T, K;
    private static int[] novelPage;
    private static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //TODO testcase 별로 반복문 추가하기

        K = Integer.parseInt(st.nextToken());

        novelPage = new int[K+1];
        dp        = new int[K+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= K; i++) {
            novelPage[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(novelPage);

        dp[1] = novelPage[1];
        dp[2] = dp[1] + novelPage[2];

    }

    private static int sumPaging(int page) {

        if (page <= 2) return dp[page];

        return 1;
    }
}
