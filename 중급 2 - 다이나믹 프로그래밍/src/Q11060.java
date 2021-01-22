import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11060 {
    static int n;
    static int[] arr = new int[1005];
    static int[] dp = new int[1005];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 1) {
            System.out.print(0);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (i > 1 && dp[i] == 0) {
                continue;
            }
            int cnt = arr[i];
            for (int j = i; j < n && j < i + cnt; j++) {
                if (dp[j + 1] == 0) {
                    dp[j + 1] = dp[i] + 1;
                } else {
                    dp[j + 1] = Math.min(dp[j + 1], dp[i] + 1);
                }
            }
        }

        if (dp[n] == 0) {
            System.out.print(-1);
        } else {
            System.out.print(dp[n]);
        }
    }
}
