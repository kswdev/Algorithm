package dijkstra.gold3;

/*
 * 도시 갯수 : n
 * 버스 갯수 : m
 *
 * 문제.
 *  1. n(1≤n≤1,000)개의 도시가 있다.
 *  2. 한 도시에서 출발하여 다른 도시에 도착하는 m(1≤m≤100,000)개의 버스가 있다.
 *  3. A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다.
 *  4. 그러면 A번째 도시에서 B번째 도시 까지 가는데 드는 최소비용과 경로를 출력
 *  5. 항상 시작점에서 도착점으로의 경로가 존재한다.
 *
 * 출력.
 *  첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
 *  둘째 줄에는 그러한 최소 비용을 갖는 경로에 포함되어있는 도시의 개수를 출력한다. 출발 도시와 도착 도시도 포함한다.
 *  셋째 줄에는 최소 비용을 갖는 경로를 방문하는 도시 순서대로 출력한다. 경로가 여러가지인 경우 아무거나 하나 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Comparator.comparingInt;

public class BOJ_11779 {

    private static List<CityStation> cityStations = new ArrayList<>();
    private static Stack<Integer> routeList = new Stack<>();
    private static int[] route;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int numOfCity = Integer.parseInt(br.readLine());
        int numOfBus = Integer.parseInt(br.readLine());
        route = new int[numOfCity + 1];

        for (int i = 0; i <= numOfCity; i++) {
            cityStations.add(new CityStation());
        }

        for (int i = 0; i < numOfBus; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            Ticket ticket = new Ticket(to, cost);
            cityStations.get(from).addTicket(ticket);
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());


        int result = minCost(start, end, numOfCity);
        setRoutes(end);
        int size = routeList.size();

        while (!routeList.isEmpty()) {
            sb.append(routeList.pop()).append(" ");
        }

        System.out.println(result);
        System.out.println(size);
        System.out.println(sb);
    }

    private static int minCost(int start, int end, int numOfCity) {
        Queue<int[]> queue = new PriorityQueue<>(comparingInt(a -> a[1]));
        queue.add(new int[] {start, 0});
        int[] dcost = new int[numOfCity + 1];
        Arrays.fill(dcost, Integer.MAX_VALUE);
        dcost[start] = 0;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int city = poll[0];
            int cost = poll[1];

            if (isArrive(city, end)) return cost;

            CityStation station = cityStations.get(city);

            for (Ticket ticket : station.getTickets()) {
                int nextCity = ticket.getDestination();
                int newCost = ticket.getCost();

                if (isCheaperThan(dcost[nextCity], cost + newCost)) continue;

                dcost[nextCity] = cost + newCost;
                queue.add(new int[] {nextCity, cost + newCost});
                route[nextCity] = city;
            }
        }

        throw new IllegalArgumentException();
    }

    private static class CityStation {
        List<Ticket> tickets = new ArrayList<>();

        private void addTicket(Ticket ticket) {
            this.tickets.add(ticket);
        }

        private List<Ticket> getTickets() {
            return this.tickets;
        }
    }

    private static class Ticket {
        private int destination;
        private int cost;

        public Ticket(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }

        public int getDestination() {
            return destination;
        }

        public int getCost() {
            return cost;
        }
    }

    private static void setRoutes(int end) {

        routeList.add(end);

        int prev = route[end];
        if (prev == 0) return;

        setRoutes(prev);
    }

    private static boolean isArrive(int destination, int end) {
        return destination == end;
    }

    private static boolean isCheaperThan(int accumulatedCost, int newCost) {
        return accumulatedCost < newCost;
    }
}
