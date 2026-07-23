package programmers.lv3.square_count_arr;

/**
 *
 * 1이상의 정수로 이루어진 길이가 N인 1차원 정수 배열 arr가 주어진다. -> int[N] arr
 * 다음 규칙에 따라 배열 brr 을 만든다.
 *  - 배열 arr의 인덱스 순서대로 arr[i]를 배열 brr에 연속으로 arr[i]개씩 추가한다.
 *  - ex) arr = [2, 1, 5] -> brr = [2, 2, 1, 5, 5, 5, 5, 5]

 * brr의 부분 배열1 구간의 양 끝을 나타내는 l, r이 주어질 때 아래 2가지를 구하려고 합니다.
 *  1. K: brr의 l번째 원소부터 r번째 원소까지의 합 = brr[l-1] + brr[l] + brr[l+1] + ... + brr[r-2] + brr[r-1]
 *  2. C: 길이가 r - l + 1인 brr의 부분 배열 중 합이 K인 부분 배열의 개수

 *  1차원 정수 배열 [K, C]를 return 하도록 solution 함수를 완성하라

 * 제한.
 *  1 <= N <= 100,000
 *  1 <= arr[i] <= 100,000
 *  1 <= l <= r <= arr.sum()
 *  brr.sum <= 10^15
 */

public class Solution {

    private long start, end;
    private long K, C;

    public long[] solution(int[] arr, long l, long r) {
        // l, r -> start, end 멤버 필드에 할당
        start = l-1;
        end = r-1;

        // K 구하기
        K = sumBrr(arr, start, end);

        // C 구하기
        C = countBrr(arr, start, end, K);

        return new long[] {K, C};
    }

    private long countBrr(int[] arr, long start, long end, long k) {
        long count = 0;
        long length = end - start;

        long max = 0;
        for (int v : arr)
            max += v;

        long prevSum = sumBrr(arr, 0, length);

        if (prevSum == k) count++;

        int prevLeftIdx = 0;

        for (long i = 1; i + length < max; i++) {
            int leftIdx = getIndexOfArr(arr, i);
            int rightIdx = getIndexOfArr(arr, i + length);

            long sum = prevSum - arr[prevLeftIdx] + arr[rightIdx];

            if (sum == k) count++;

            prevLeftIdx = leftIdx;
            prevSum = sum;
        }

        return count;
    }


    private long sumBrr(int[] arr, long left, long right) {
        long answer = 0;

        // left 와 right 를 비교한다.
        if (left != right) {
            // left, right 가 속한 arr 의 인덱스를 구한다.
            int leftIdx = getIndexOfArr(arr, left);
            int rightIdx = getIndexOfArr(arr, right);

            // leftIdx 와 rightIdx 를 비교한다.
            if (leftIdx == rightIdx) {
                answer += (right - left + 1) * arr[leftIdx];
            } else {

                // left 잘린 부분의 합을 구한다.
                long leftSum = 0;
                for (int i = 0; i <= leftIdx; i++) {
                    leftSum += arr[i];
                }
                answer += arr[leftIdx] * (leftSum - left);

                // right 잘린 부분의 합을 구한다.
                long rightSum = 0;
                for (int i = 0; i < rightIdx; i++) {
                    rightSum += arr[i];
                }
                answer += arr[rightIdx] * (right + 1 - rightSum);

                // left, right 사이에 잘리지 않은 arr 원소의 인덱스들의 제곱수들을 answer 에 더한다.
                for (int i = leftIdx + 1; i < rightIdx; i++) {
                    answer += (long) Math.pow(arr[i], 2);
                }
            }
        } else {
            // brr 배열의 left 원소만 반환한다.
            int leftIdx = getIndexOfArr(arr, left);
            answer = arr[leftIdx];
        }

        return answer;
    }

    private int getIndexOfArr(int[] arr, long brrIdx) {
        int arrIdx = 0;
        int idx = 0;

        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];

            if (idx + value <= brrIdx) {
                idx += value;
            } else {
                arrIdx = i;
                break;
            }
        }

        return arrIdx;
    }
}
