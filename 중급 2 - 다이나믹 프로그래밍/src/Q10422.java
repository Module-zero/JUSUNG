import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10422 {
    static int t;
    static long[] d = new long[5001];
    static final int limit = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        d[0] = 1;
        for (int L = 2; L <= 5000; L+=2) {
            for (int i = 2; i <= L; i+=2) {
                d[L] += (d[i-2] * d[L-i]);
            }
            d[L] %= limit;
        }

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(d[n]).append("\n");
        }
        System.out.print(sb);
    }
}
