import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2281 {
    static int n, m;
    static int[] a = new int[1001];
    static long[][] d = new long[1111][1111];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < 1001; i++) {
            Arrays.fill(d[i], -1);
        }

        go(0, 0);
        System.out.print(d[0][0]);
    }

    static long go(int index, int col) {

        if (index == n) {
            return 0;
        }

        if (d[index][col] != -1) {
            return d[index][col];
        }

        long spare = m - col + 1;
        long ret = go(index + 1, a[index] + 1) + spare * spare;

        if (m - col >= a[index]) {
            long tmp = go(index + 1, col + a[index] + 1);
            ret = Math.min(ret, tmp);
            d[index][col] = ret;
        }

        return ret;
    }
}
