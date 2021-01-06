import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1644 {
    static int n;
    static boolean[] check = new boolean[4000001];
    static long[] dp = new long[4000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        prime(n);

        long cnt = 0;
        int idx = 1;
        for (int i = 2; i <= n; i++) {
            if (!check[i]) {
                // System.out.print(i+" ");
                dp[idx] = dp[idx-1] + i;
                idx++;
                cnt++;
            }
        }

        System.out.print(cnt);
        /*
        int i = 1;
        while (dp[i] != 0) {
            System.out.print(dp[i] + " ");
            i++;
        }
         */

        /*
        for (int i = 0; i <= n; i++) {
            System.out.print(dp[i]+ " ");
        }
         */
    }

    static void prime(int x) {
        for (int i = 2; i <= x; i++) {
            if (!check[i]) {
                for (int j = i*2; j <= x; j+=i) {
                    check[j] = true;
                }
            }
        }
    }

}
