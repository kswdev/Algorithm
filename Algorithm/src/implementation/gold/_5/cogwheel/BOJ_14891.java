package implementation.gold._5.cogwheel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_14891 {

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Wheels wheels = new Wheels();

        for (int i = 1; i <= 8; i*=2) {
            String wheelLine = br.readLine();
            Wheel wheel = new Wheel(i);
            for (int j = 0; j < 8; j++) {
                wheel.getTip().addLast((wheelLine.charAt(j) - 48));
            }
            wheels.getList().add(wheel);
        }

        int actionNum = Integer.parseInt(br.readLine());
        int[][] action = new int[actionNum][2];

        for (int i = 0; i < actionNum; i++) {
            String[] actionLine = br.readLine().split(" ");
            action[i][0] = Integer.parseInt(actionLine[0]);
            action[i][1] = Integer.parseInt(actionLine[1]);
        }

        System.out.println(sumTip(wheels, action));
    }

    private static int sumTip(Wheels wheels, int[][] action) {

        for (int[] a : action) {
            int wheelId = a[0];
            int spinDirection = a[1];
            visited = new boolean[4];
            wheels.spin(wheelId-1, spinDirection);
        }

        return wheels.sumTopTip();
    }

    private static class Wheels {
        private final List<Wheel> list = new ArrayList<>();

        public List<Wheel> getList() { return list; }

        public void spin(int wheelId, int spinDirection) {
            if (visited[wheelId]) return;

            visited[wheelId] = true;

            Wheel currentWheel = list.get(wheelId);

            int currentRightTip = currentWheel.getTip().getRightTip();
            int currentLeftTip = currentWheel.getTip().getLeftTip();

            if (spinDirection == 1) {
                int lastTip = currentWheel.getTip().pollLast();
                currentWheel.getTip().addFirst(lastTip);
            } else if (spinDirection == -1) {
                int firstTip = currentWheel.getTip().pollFirst();
                currentWheel.getTip().addLast(firstTip);
            }

            spinNext(wheelId + 1, spinDirection * -1, currentRightTip);
            spinPrev(wheelId - 1, spinDirection * -1, currentLeftTip);
        }

        public void spinNext(int nextWheelId, int spinDirection, int currentRightTip) {
            if (nextWheelId > 3) return;
            Wheel nextWheel = list.get(nextWheelId);
            if (nextWheel.getTip().getLeftTip() != currentRightTip)
                spin(nextWheelId, spinDirection);
        }

        public void spinPrev(int prevWheelId, int spinDirection, int currentLeftTip) {
            if (prevWheelId < 0) return;
            Wheel prevWheel = list.get(prevWheelId);
            if (prevWheel.getTip().getRightTip() != currentLeftTip)
                spin(prevWheelId, spinDirection);
        }

        public int sumTopTip() {
            return list.stream()
                    .mapToInt(wheel -> wheel.tip.pollFirst() * wheel.getPoint())
                    .reduce(0, Integer::sum);
        }
    }


    private static class Wheel {
        private IntDequeue tip = new IntDequeue(8);
        private int point;

        public Wheel(int point) {
            this.point = point;
        }

        public IntDequeue getTip() {
            return tip;
        }

        public int getPoint() {
            return point;
        }
    }

    private static class IntDequeue {
        private final int capacity;
        private final int[] arr;
        private int head = 0;
        private int tail = 0;

        public IntDequeue (int capacity) {
            this.capacity = capacity;
            arr = new int[capacity];
        }

        public void addFirst (int x) {
            head = (head - 1 + capacity) % capacity;
            arr[head] = x;
        }

        public void addLast (int x) {
            arr[tail] = x;
            tail = (tail + 1) % capacity;
        }

        public int pollFirst () {
            int headVal = arr[head];
            arr[head] = -1;
            head = (head + 1) % capacity;
            return headVal;
        }

        public int pollLast () {
            tail = (tail - 1 + capacity) % capacity;
            int tailVal = arr[tail];
            arr[tail] = -1;
            return tailVal;
        }

        public int getRightTip () {
            int rightNum = (head + 2) % capacity;
            return arr[rightNum];
        }

        public int getLeftTip () {
            int leftNum = (head - 2 + capacity) % capacity;
            return arr[leftNum];
        }
    }
}
