import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1644 {
    static int n;
    static boolean[] check = new boolean[4000001];
    static int[] prime = new int[4000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        prime(n);

        // 투포인터
        int start = 0, end = 0, count = 0, sum = 0;
        while (start <= end) {
            if (sum == n) {
                sum -= prime[start++];
                count++;
            }
            else if (prime[end] == 0 || sum > n) {
                sum -= prime[start++];
            }  else {
                sum += prime[end++];
            }
        }

        System.out.print(count);
    }

    static void prime(int x) {
        int idx = 0;
        for (int i = 2; i <= x; i++) {
            if (!check[i]) {
                prime[idx++] = i;
                for (int j = i*2; j <= x; j+=i) {
                    check[j] = true;
                }
            }
        }
    }

}
