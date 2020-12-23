import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2805 {
    static int n, m;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new long[n];
        st = new StringTokenizer(br.readLine(), " ");

        long max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        long answer = 0;
        long left = 0;
        long right = max;
        while (left <= right) {
            long mid = (left+right)/2;
            long myTree = getMyTree(mid);
            if (myTree < m) {
                right = mid-1;
            } else {
                answer = mid;
                left = mid+1;
            }
        }

        System.out.print(answer);
    }

    // 절단기의 높이가 h 일때 집에 가져갈 수 있는 나무의 길이를 리턴
    static long getMyTree(long h) {
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] >= h) {
                res += arr[i] - h;
            }
        }
        return res;
    }
}
