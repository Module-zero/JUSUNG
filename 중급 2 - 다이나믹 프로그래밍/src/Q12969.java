import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12969 {
    static int n, k;
    static boolean[][][][] d = new boolean[31][31][31][1000];
    static char[] answer = new char[31];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (go(0, 0, 0, 0)) {
            for (char c : answer) {
                System.out.print(c);
            }
        } else {
            System.out.print(-1);
        }
    }

    static boolean go(int i, int a, int b, int p) {

        if (i == n) {
            return p == k;
        }

        if (d[i][a][b][p]) {
            return false;
        }

        d[i][a][b][p] = true;

        answer[i] = 'A';
        if (go(i+1, a+1, b, p)) {
            return true;
        }

        answer[i] = 'B';
        if (go(i+1, a, b+1, a+p)) {
            return true;
        }

        answer[i] = 'C';
        return go(i+1, a, b, a+b+p);
    }
}
