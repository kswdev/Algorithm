package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1916 {

    private static int N, M;
    private static List<List<Human>> busList = new ArrayList<>();
    private static int[] dist;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1];
        visited = new boolean[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i <= N; i++)
            busList.add(new ArrayList<>());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end   = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            busList.get(start).add(new Human(end, price));
        }

        st = new StringTokenizer(br.readLine());

        int startCityNum = Integer.parseInt(st.nextToken());
        int endCityNum   = Integer.parseInt(st.nextToken());

        dist[startCityNum] = 0;

        foundShortestPrice(startCityNum);

        System.out.println(dist[endCityNum]);
    }

    private static void foundShortestPrice(int start) {
        Queue<Human> pQ = new PriorityQueue<>();
        pQ.add(new Human(start, 0));

        while (!pQ.isEmpty()) {
            Human now = pQ.poll();

            if (visited[now.getEnd()])
                continue;

            visited[now.getEnd()] = true;

            for (Human next : busList.get(now.getEnd())) {
                if (dist[next.getEnd()] > dist[now.getEnd()] + next.getPrice()) {
                    dist[next.getEnd()] = dist[now.getEnd()] + next.getPrice();
                    next.setPrice(dist[next.getEnd()]);
                    pQ.add(next);
                }
            }
        }
    }

    private static class Human implements Comparable<Human> {
        private int end;
        private int price;

        private void setPrice(int price) {
            this.price = price;
        }

        public Human(int end, int price) {
            this.end = end;
            this.price = price;
        }

        public int getEnd() {
            return end;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public int compareTo(Human b2) {
            return price - b2.price;
        }
    }
}
