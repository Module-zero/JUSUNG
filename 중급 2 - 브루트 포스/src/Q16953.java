import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16953 {
    static int a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        go(b, 1);
    }

    static void go(int b, int cnt) {

        if (b == a) {
            System.out.print(cnt);
            return;
        }

        if (b < a) {
           System.out.print(-1);
           return;
        }

        if (b % 10 == 1) {
            b -= 1;
            go(b/10, cnt+1);
        } else {
            if (b % 2 != 0) {
                System.out.print(-1);
                return;
            }
            go(b/2, cnt+1);
        }
    }
}
