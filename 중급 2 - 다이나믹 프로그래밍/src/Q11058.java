import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q11058 {
    static int n;
    static long[] d = new long[110];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            d[i] = i;
            for (int j = 1; j <= i; j++) {
                int nPaste = Math.max(i-j-2, 0);
                d[i] = Math.max(d[j] + nPaste*d[j], d[i]);
            }
        }

        System.out.print(d[n]);
    }
}
