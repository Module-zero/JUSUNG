import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1654 {
    static int k, n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[k];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        long answer = 0;
        long left = 1;
        long right = max;
        while (left <= right) {
            long mid = (left+right)/2;
            long count = getCount(mid);
            if (count < n) {
                right = mid-1;
            } else {
                answer = mid;
                left = mid+1;
            }
        }

        System.out.print(answer);
    }

    // 랜선 하나의 길이가 n 일 때, 몇 개의 랜선을 만들 수 있는 지를 리턴
    static long getCount(long n) {
        long count = 0;
        for (int i = 0; i < k; i++) {
            count += arr[i] / n;
        }
        return count;
    }
}
