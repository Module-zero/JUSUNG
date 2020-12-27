import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1300 {
    static long n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
        k = Long.parseLong(br.readLine());

        long answer = 0;
        long left = 1;
        long right = n*n;
        while (left <= right) {
            long mid = (left+right)/2;
            long idx = getIdx(mid);
            if (idx >= k) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.print(answer);
    }

    static long getIdx(long s) {
        long cnt = 0;
        for (int i = 1; i <= n; i++) {
            cnt += Math.min(n, s/i);
        }
        return cnt;
    }
}
