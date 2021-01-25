import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15486 {
    static int n;
    static int[][] arr = new int[2][1500500];
    static long[] dp = new long[1500500];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arr[0][i] = Integer.parseInt(st.nextToken());
            arr[1][i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            long p = dp[i - 1];
            if (arr[0][i - 1] == 1) {
                p += arr[1][i - 1];
            }
            dp[i] = Math.max(dp[i], p);

            int t = arr[0][i];
            if (i + t <= n + 1) {
                dp[i + t] = Math.max(dp[i + t], dp[i] + arr[1][i]);
            }
        }

        System.out.print(Math.max(dp[n], dp[n + 1]));
    }
}
