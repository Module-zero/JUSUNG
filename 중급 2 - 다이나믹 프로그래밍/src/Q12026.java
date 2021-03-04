import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q12026 {
    static int n;
    static String s;
    static long[][] d = new long[1111][1111];
    static char[] next = new char[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = br.readLine();
        next['B' - 'A'] = 'O';
        next['O' - 'A'] = 'J';
        next['J' - 'A'] = 'B';

        for (int len = 1; len <= n-1; len++) {
            for (int start = 0; start <= n-1-len; start++) {
                int sIdx = s.charAt(start) - 'A';
                int end = start + len;
                d[start][end] = next[sIdx] == s.charAt(end) ? len * len : Long.MAX_VALUE;
                for (int front = 1; front <= len - 1; front++) {
                    int mid = start + front;
                    if (d[start][mid] != Long.MAX_VALUE && d[mid][end] != Long.MAX_VALUE) {
                        d[start][end] = Math.min(d[start][end], d[start][mid] + d[mid][end]);
                    }

                    // int mIdx = s.charAt(mid) - 'A';
                    // d[start][end] = Math.min(d[start][end], d[start][mid] + d[mid][end]);
                    /*
                    if ((next[sIdx] == s.charAt(mid)) && (next[mIdx] == s.charAt(end))) {
                        d[start][end] = Math.min(d[start][end], d[start][mid] + d[mid][end]);
                    }
                     */
                }
            }
        }

        if (d[0][n-1] != Long.MAX_VALUE) {
            System.out.print(d[0][n-1]);
        } else {
            System.out.print(-1);
        }
    }
}
