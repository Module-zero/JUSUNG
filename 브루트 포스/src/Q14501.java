import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14501 {

    static int n;
    static int[] T;
    static int[] P;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        T = new int[n+1];
        P = new int[n+1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            T[i] = Integer.parseInt(st.nextToken());
            P[i]= Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            go(i, 0);
        }

        System.out.print(max);
    }

    // day : 현재 일자
    // sum : day 일 전까지 상담으로 얻은 금액
    static void go(int day, int sum) {

        // 현재 일자의 상담을 진행할 경우
        // n 일을 초과하는지를 확인
        if (day + T[day] > n) {

            // 상담이 정확히 n 일에 딱 채워지는 경우
            if (day + T[day] == n+1) {
                sum += P[day];
            }

            if (max < sum) {
                max = sum;
            }
            return;
        }

        for (int i = day + T[day]; i <= n; i++) {
            go(i, sum + P[day]);
        }
    }
}
