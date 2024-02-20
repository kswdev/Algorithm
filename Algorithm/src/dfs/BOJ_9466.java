package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_9466 {

    private static int T;
    private static List<int []> partners = new ArrayList<>();
    private static boolean[] hasTeam;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] partner = new int[n+1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                partner[j] = Integer.parseInt(st.nextToken());
            }

            partners.add(partner);
        }

        for (int k = 0; k < partners.size(); k++) {
            int[] test1 = partners.get(k);

            hasTeam = new boolean[test1.length+1];
            visited = new boolean[test1.length+1];

            for (int i = 1; i < test1.length; i++)
                if (!hasTeam[i]) dfs(i, i, k, 0);

            int count = 0;

            for (int i = 1; i < test1.length; i++)
                if (!hasTeam[i]) count++;

            System.out.println(count);
        }
    }

    private static void dfs(int start, int end, int k, int depth) {
        int[] test1 = partners.get(k);

        if (test1[start] == end) {
            teamChk(end, 0, depth, test1);
            return;
        }

        if (!visited[start]) {
            visited[start] = true;
            dfs(test1[start], end, k, depth+1);
            visited[start] = false;
        }
    }

    private static void teamChk(int s, int cnt, int depth, int[] test) {
        hasTeam[s] = true;

        if (cnt < depth) {
            teamChk(test[s], cnt+1, depth, test);
        }
    }
}
