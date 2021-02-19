import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2294 {
    static int n, k;
    static int[] coins = new int[110];
    static int[] d = new int[10010];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.fill(d, 100000);
        d[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j - coins[i] >= 0) {
                    d[j] = Math.min(d[j - coins[i]] + 1, d[j]);
                }
            }
        }

        if (d[k] == 100000) {
            System.out.print(-1);
        } else {
            System.out.print(d[k]);
        }
    }
}
