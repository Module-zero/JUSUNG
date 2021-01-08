import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12931 {
    static int n;
    static int[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        b = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        boolean isEven, isZero;
        do {
            isEven = true;
            isZero = true;
            for (int i = 0; i < n; i++) {
                if (b[i] % 2 != 0) {
                    isEven = false;
                    b[i]--;
                    answer++;
                }

                if (b[i] != 0) {
                    isZero = false;
                }
            }

            if (isZero) {
                break;
            }

            if (isEven) {
                for (int i = 0; i < n; i++) {
                    b[i] /= 2;
                }
                answer++;
            }
        } while (true);

        System.out.print(answer);
    }
}
