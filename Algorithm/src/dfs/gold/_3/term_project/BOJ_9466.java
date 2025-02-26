package dfs.gold._3.term_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9466 {

    private static int T, count;
    private static int [] partner;
    private static boolean[] hasTeam;
    private static boolean[] checkedNotHasTeam;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            partner = new int[n+1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                partner[j] = Integer.parseInt(st.nextToken());
            }

            hasTeam = new boolean[partner.length+1];
            visited = new boolean[partner.length+1];
            checkedNotHasTeam = new boolean[partner.length+1];

            count = 0;

            for (int j = 1; j < partner.length; j++) {
                if (!hasTeam[j]) dfs(j);
            }

            System.out.println(partner.length - count - 1);
        }
    }

    private static void dfs(int start) {

        if (visited[start]) {
            teamChk(start, start, 0);
            return;
        }

        if (!checkedNotHasTeam[start] && !hasTeam[partner[start]]) {
            visited[start] = true;
            checkedNotHasTeam[start] = true;
            dfs(partner[start]);
            visited[start] = false;
        }
    }

    private static void teamChk(int s, int e, int cnt) {
        hasTeam[s] = true;
        checkedNotHasTeam[s] = false;
        count++;
        if (partner[s] != e) {
            teamChk(partner[s], e, cnt+1);
        }
    }
}
