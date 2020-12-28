import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1561 {
    static long n, m;
    static long[] arr = new long[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Long.parseLong(st.nextToken());
        m = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long minute = 0;
        long left = 0;
        long right = 2000000000L * 30;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (possible(mid) >= n) {
                minute = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        long answer = 0;
        if (minute == 0) {
            answer = n;
        } else {
            // minute-1 분이 지났을 때 탑승한 아이들의 수
            long prev = m;
            for (int i = 0; i < m; i++) {
                prev += (minute-1) / arr[i];
            }

            // minute 분에 탑승하는 아이들의 수
            long goal = n - prev;
            for (int i = 0; i < m; i++) {
                if (minute % arr[i] == 0) {
                    goal--;
                    if (goal == 0) {
                        answer = i+1;
                    }
                }
            }
        }

        System.out.print(answer);
    }

    // x 분이 지났을 때, 몇 명의 아이들이 탑승했는가
    static long possible(long x) {
        long cnt = m;
        for (int i = 0; i < m; i++) {
            cnt += x / arr[i];
        }
        return cnt;
    }
}
