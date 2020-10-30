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

    static void go(int day, int sum) {
        System.out.println("day : " + day);
        System.out.println("sum : " + sum + "\n");

        if (day == n) {
            sum += P[day];
            if (max < sum) {
                max = sum;
            }
            return;
        }

        if (day > n) {
            if (max < sum) {
                max = sum;
            }
            return;
        }

        for (int i = day + T[day]; i <= n; i++) {
            go(i, sum + P[day]);
        }
    }

    /*
    static void go(int day, int sum) {

        for (int i = day; i <= n; i++) {
            if (day + T[day] > n) {
                if (max < sum) {
                    max = sum;
                }
                return;
            }
            go(day + T[i], sum + P[i]);
        }
    }
     */
}
