import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 접근법 :
// D 배열을 채우는 과정에서 걸러주는 조건을 찾아내는 것이 가장 중요함(A[i] > A[j])

// 개선사항 :
// 없음
public class Q11055 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] D = new long[n];
        long[] A = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            D[i] = A[i];
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j] && A[i] + D[j] > D[i]) {
                    D[i] = A[i] + D[j];
                }
            }
        }

        Arrays.sort(D);
        System.out.print(D[D.length - 1]);
    }
}
