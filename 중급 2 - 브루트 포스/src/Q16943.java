import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16943 {
    static String a, b;
    static int b_;
    static boolean[] check = new boolean[10];
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        a = st.nextToken();
        b = st.nextToken();
        if (a.length() > b.length()) {
            System.out.print(-1);
            return;
        }

        b_ = Integer.parseInt(b);
        go(0, 0);

        if (max == Integer.MIN_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(max);
        }
    }

    static void go(int index, int sum) {

        if (index == a.length()) {
            if (sum > max && sum < b_) {
                max = sum;
            }
            return;
        }

        for (int i = 0; i < a.length(); i++) {
            if (!check[i]) {
                // 맨 앞자리에 0은 올 수 없음
                int n = a.charAt(i)-'0';
                if (index == 0 && n == 0) {
                    continue;
                }
                check[i] = true;
                go(index + 1, sum*10 + n);
                check[i] = false;
            }
        }
    }
}
