import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11066 {
    static int t;
    static int[] arr;
    static long[][] d1, d2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n];
            d1 = new long[n][n];
            d2 = new long[n][n];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                d1[i][i] = arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 2; i <= n; i++) {
                for (int j = 0; j <= n-i; j++) {
                    long min1 = Long.MAX_VALUE;
                    long min2 = Long.MAX_VALUE;
                    for (int k = j; k <= j+i-2; k++) {
                        min1 = Math.min(min1, d1[j][k] + d1[k+1][j+i-1]);
                        min2 = Math.min(min2, d2[j][k] + d2[k+1][j+i-1]);
                    }
                    d1[j][j+i-1] = min1;
                    d2[j][j+i-1] = min1 + min2;
                }
            }

            System.out.println(d2[0][n-1]);
        }
    }
}
