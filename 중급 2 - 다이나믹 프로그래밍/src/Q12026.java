import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q12026 {
    static int n;
    static String s;
    static long[] d = new long[1111];
    static char[] next = new char[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = br.readLine();
        next['B' - 'A'] = 'O';
        next['O' - 'A'] = 'J';
        next['J' - 'A'] = 'B';

        for (int len = 1; len <= n - 1; len++) {
            d[len] = Long.MAX_VALUE;
            for (int mid = 0; mid <= len - 1; mid++) {
                int mIdx = s.charAt(mid) - 'A';
                if (d[mid] != Long.MAX_VALUE && next[mIdx] == s.charAt(len)) {
                    d[len] = Math.min(d[len], d[mid] + (len-mid)*(len-mid));
                }
            }
        }

        if (d[n - 1] == Long.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(d[n - 1]);
        }
    }
}
