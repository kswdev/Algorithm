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
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.AbstractMap.*;

public class BOJ_11779 {

    private static List<CityStation> cityStations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int numOfCity = Integer.parseInt(br.readLine());
        int numOfBus = Integer.parseInt(br.readLine());

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

        History result = minCost(start, end, numOfCity);

        System.out.println(result.getAccumulatedCost());
        System.out.println(result.getVisited().size());
        System.out.println(
                result.getVisited().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }

    private static History minCost(int start, int end, int numOfCity) {
        Queue<SimpleEntry<Ticket, History>> queue = new PriorityQueue<>(comparingInt(
                entry -> entry.getValue().getAccumulatedCost()));

        int[] cost = new int[numOfCity + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;

        History history = new History(start);
        Ticket ticket = new Ticket(start, 0);
        queue.add(new SimpleEntry<>(ticket, history));

        while (!queue.isEmpty()) {
            SimpleEntry<Ticket, History> entry = queue.poll();
            Ticket currTicket = entry.getKey();
            History currHistory = entry.getValue();

            if (isArrive(currTicket.getDestination(), end))
                return currHistory;

            CityStation station = cityStations.get(currTicket.getDestination());

            for (Ticket nextTicket : station.getTickets()) {
                if (isCheaperOrEqual(cost[nextTicket.getDestination()], currHistory, nextTicket)) {
                    History nextHistory = new History(currHistory);
                    nextHistory.checkTicket(nextTicket);
                    cost[nextTicket.getDestination()] = currHistory.getAccumulatedCost() + nextTicket.getCost();
                    queue.add(new SimpleEntry<>(nextTicket, nextHistory));
                }
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

    private static class History {
        private List<Integer> visited = new ArrayList<>();
        private int accumulatedCost = 0;

        private History(int startCity) {
            this.visited.add(startCity);
        }

        private History(History history) {
            this.visited.addAll(history.getVisited());
            this.accumulatedCost = history.getAccumulatedCost();
        }

        private void checkTicket(Ticket ticket) {
            int cityNum = ticket.getDestination();
            this.visited.add(cityNum);
            this.accumulatedCost += ticket.getCost();
        }

        private int getAccumulatedCost() {
            return accumulatedCost;
        }

        private List<Integer> getVisited() {
            return this.visited;
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

    private static boolean isArrive(int destination, int end) {
        return destination == end;
    }

    private static boolean isCheaperOrEqual(int cost, History history, Ticket ticket) {
        return cost > (history.getAccumulatedCost() + ticket.getCost());
    }
}
