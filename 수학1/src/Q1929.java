import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 소수 구하기(에라토스테네스의 체)
public class Q1929 {

    static int[]prime = new int[1000000];
    static boolean[]check = new boolean[1000001];
    static int nPrime = 0;

    public static void prime(int n) {
        for (int i = 2; i <= n; i++) {
            if (check[i] == false) {
                prime[nPrime++] = i;
                for (int j = i * 2; j <= n; j += i) {
                    check[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        prime(b);

        for (int i = 0; prime[i] != 0; i++) {
            if (prime[i] >= a) {
                System.out.println(prime[i]);
            }
        }
    }
}
