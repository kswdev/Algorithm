package dynamic_programming.gold3.app_activate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_7579 {

    private static int N, M;
    private static Queue<App> appQueue = new PriorityQueue<>();
    private static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[M+1];

        StringTokenizer memory = new StringTokenizer(br.readLine());
        StringTokenizer deactivate = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int appMemory = Integer.parseInt(memory.nextToken());
            int activate = Integer.parseInt(deactivate.nextToken());

            appQueue.add(new App(appMemory, activate));
        }

        Arrays.fill(dp, -1);
        dp[0] = 0;

        while (!appQueue.isEmpty()) {
            App app = appQueue.poll();

            for (int i = app.getMemory(); dp[i] == -1; i--)
                dp[i] = app.getActivate();
        }

        System.out.println(Arrays.toString(dp));

        //System.out.println(solve(M));
    }

    private static int solve(int m) {
        if (dp[m] != -1)
            return dp[m];



        return dp[m];
    }

    private static class App implements Comparable<App> {
        private int memory;
        private int activate;

        public App(int memory, int activate) {
            this.memory = memory;
            this.activate = activate;
        }

        public int getMemory() {
            return memory;
        }

        public int getActivate() {
            return activate;
        }

        @Override
        public int compareTo(App app) {
            return this.getActivate() - app.getActivate();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            App app = (App) o;
            return memory == app.memory && activate == app.activate;
        }

        @Override
        public int hashCode() {
            return Objects.hash(memory, activate);
        }
    }
}
