import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12970 {
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int a = 0, b = 0;
        for (int i = 1; i <= n/2; i++) {
            if (k <= (i*(n-i))) {
                a = i;
                b = n-i;
                break;
            }
        }

        if (a == 0 && b == 0) {
            System.out.print(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a; i++) {
            sb.append('A');
        }
        for (int i = 0; i < (k / a); i++) {
            sb.append('B');
        }
        b -= (k / a);
        if ((k % a) != 0) {
            sb.insert(k % a, 'B');
            b--;
        }
        for (int i = 0; i < b; i++) {
            sb.insert(0, 'B');
        }
        System.out.print(sb);
    }
}
