import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11066 {
    static int t;
    static int[] arr;
    static long[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n];
            d = new long[n][n];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                d[i][i] = arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 2; i <= n; i++) {
                for (int j = 0; j <= n-i; j++) {
                    long min = Long.MAX_VALUE;
                    for (int k = j; k <= j+i-2; k++) {
                        min = Math.min(min, d[j][k] + d[k+1][j+i-1]);
                    }
                    d[j][j+i-1] = min;
                }
            }

            System.out.println(d[0][n-1]);
        }
    }
}
