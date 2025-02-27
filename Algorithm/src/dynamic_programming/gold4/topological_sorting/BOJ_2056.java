package dynamic_programming.gold4.topological_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2056 {

    private static int[] dp;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        dp = new int[n+1];

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(0, 0, 0));

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int precedeTaskCount = Integer.parseInt(st.nextToken());

            Task newTask = new Task(i, time, precedeTaskCount);

            tasks.add(newTask);

            for (int j = 0; j < precedeTaskCount; j++) {
                int requiredTaskNumber = Integer.parseInt(st.nextToken());
                tasks.get(requiredTaskNumber).addNextTask(newTask);
            }
        }

        works(tasks, n);

        System.out.println(result);
    }

    private static void works(List<Task> tasks, int totalTasksCount) {

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 1; i <= totalTasksCount; i++) {
            if (tasks.get(i).precedeTaskCount == 0) {
                dp[i] = tasks.get(i).time;
                queue.add(new int[] {i, tasks.get(i).time});
            }
        }

        while (!queue.isEmpty()) {
            int[] subTask = queue.poll();

            int taskNumber = subTask[0];
            int taskTime = subTask[1];

            result = Math.max(result, taskTime);

            for (Task nextTask : tasks.get(taskNumber).nextTasks) {

                int nextTaskNum = nextTask.no;
                int nextTaskTime = nextTask.time;

                dp[nextTaskNum] = Math.max(taskTime + nextTaskTime,  dp[nextTaskNum]);

                if (--nextTask.precedeTaskCount == 0) {
                    queue.add(new int[] {nextTask.no, dp[nextTaskNum]});
                }
            }
        }
    }

    private static class Task {
        private int no;
        private int time;
        private int precedeTaskCount;

        private List<Task> nextTasks = new ArrayList<>();

        private Task(int no, int time,  int precedeTaskCount) {
            this.no = no;
            this.time = time;
            this.precedeTaskCount = precedeTaskCount;
        }

        private void addNextTask(Task nextTask) {
            this.nextTasks.add(nextTask);
        }
    }
}