package dfs;

import java.io.*;
import java.util.*;

public class BOJ_1325 {
    static List<Integer>[] lists;
    static int[] hackCounts;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        lists = new List[n + 1];
        hackCounts = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lists[start].add(end);
        }


        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];

            visited[i] = true;
            hackCounts[i]++;
            dfs(i);
        }

        int maxHackCount = 0;

        for (int i = 1; i <= n; i++) {
            maxHackCount = Math.max(maxHackCount, hackCounts[i]);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++)
            if (hackCounts[i] == maxHackCount)
                sb.append(i).append(" ");

        System.out.println(sb);
    }

    static void dfs(int currentIdx) {
        for (int nextIdx : lists[currentIdx]) {
            if (!visited[nextIdx]) {
                visited[nextIdx] = true;
                hackCounts[nextIdx]++;
                dfs(nextIdx);
            }
        }
    }
}
