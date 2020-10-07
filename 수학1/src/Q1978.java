import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1978 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int nPrimes = 0;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        while (count-- > 0) {
            int n = Integer.parseInt(st.nextToken());
            if (prime(n)) {
                nPrimes++;
            }
        }
        System.out.println(nPrimes);
    }

    public static boolean prime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
