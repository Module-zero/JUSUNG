import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2293 {
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

        d[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = coins[i]; j <= k; j++) {
                d[j] += d[j-coins[i]];
            }
        }

        System.out.print(d[k]);
    }
}
