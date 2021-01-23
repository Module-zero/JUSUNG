import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

        Arrays.fill(dp, -1);
        dp[1] = 0;

        for (int i = 1; i <= n; i++) {

            if (dp[i] == -1) {
                continue;
            }

            int limit = Math.min(n, i + arr[i]);
            for (int j = i; j < limit; j++) {
                if (dp[j + 1] == -1 || dp[j + 1] > dp[i] + 1) {
                    dp[j + 1] = dp[i] + 1;
                }
            }
        }

        System.out.print(dp[n]);
    }
}
