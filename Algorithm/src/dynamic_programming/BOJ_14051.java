package dynamic_programming;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14051 {

    private static int N;
    private static int[] date;
    private static int[] val;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        date = new int[N+1];
        val  = new int[N+1];
        dp   = new int[N+1];

        String input;
        for(int i = 1; i < N + 1; i++) {
            input = br.readLine();
            date[i] = Integer.parseInt(input.split(" ")[0]);
            val[i]  = Integer.parseInt(input.split(" ")[1]);
        }


    }
}
