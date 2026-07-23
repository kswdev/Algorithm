package programmers.lv3.square_count_arr;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SquareCountArr {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = st.countTokens();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = Integer.parseInt(br.readLine());
        int r = Integer.parseInt(br.readLine());

        Solution solution = new Solution();

        System.out.println(Arrays.toString(solution.solution(arr, l, r)));
    }
}
