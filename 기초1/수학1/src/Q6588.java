import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q6588 {

    static int[]prime = new int[1000000];
    static boolean[]check = new boolean[1000001];

    public static void prime() {
        int nPrime = 0;
        for (int i = 2; i <= 1000000; i++) {
            if (check[i] == false) {
                prime[nPrime++] = i;
                for (int j = i * 2; j <= 1000000; j += i) {
                    check[j] = true;
                }
            }
        }
    }

    public static void goldbach(int n) {

        int target = 0;
        for (int i = 0; prime[i] <= n; i++) {
            target = prime[i];
            if (check[n-target] == false) {
                break;
            }
        }

        if (target == 0) {
            System.out.println("Goldbach's conjecture is wrong.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(n + " = " + target + " + " + (n - target));
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        prime();

        while (true) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                break;
            }
            goldbach(num);
        }
    }
}
