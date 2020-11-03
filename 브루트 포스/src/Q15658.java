import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15658 {

    static int N;
    static int[] A;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        int plus = Integer.parseInt(st.nextToken());
        int sub = Integer.parseInt(st.nextToken());
        int mult = Integer.parseInt(st.nextToken());
        int div = Integer.parseInt(st.nextToken());

        // go(1, A[0], plus, sub, mult, div);
        go(0, 0, plus, sub, mult, div);

        System.out.println(max);
        System.out.print(min);
    }

    static void go(int index, int sum, int plus, int sub, int mult, int div) {

        if (index == N) {
            if (max < sum) {
                max = sum;
            }
            if (min > sum) {
                min = sum;
            }
            return;
        }

        if (plus > 0) {
            go(index + 1, sum + A[index], plus-1, sub, mult, div);
        }
        if (sub > 0) {
            go(index + 1, sum - A[index], plus, sub-1, mult, div);
        }
        if (mult > 0) {
            go(index + 1, sum * A[index], plus, sub, mult-1, div);
        }
        if (div > 0) {
            go(index + 1, sum / A[index], plus, sub, mult, div-1);
        }
    }
}
