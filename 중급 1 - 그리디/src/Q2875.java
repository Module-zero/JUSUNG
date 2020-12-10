import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2875 {
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int count = 0;
        while (true) {
            n -= 2;
            m -= 1;
            if (n + m >= k) {
                count++;
                if (n < 2 || m < 1) {
                    break;
                }
            } else {
                break;
            }
        }
        System.out.print(count);
    }
}
