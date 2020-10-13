import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2004 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int nFive = count(n, 5) - (count(m, 5) + count(n-m, 5));
        int nTwo = count(n, 2) - (count(m, 2) + count(n-m, 2));
        System.out.println(nFive < nTwo ? nFive : nTwo);
    }

    public static int count(int n, int x) {
        int sum = 0;
        for (long i = x; i <= n; i *= x) {
            sum += n / i;
        }
        return sum;
    }
}
