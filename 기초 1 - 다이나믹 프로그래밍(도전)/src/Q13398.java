import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접근법 :
// D 배열을 2개 사용해야함
// D1[i] : 앞에서부터 A[i] 까지 연속합의 최대값
// D2[i] : 뒤에서부터 A[i] 까지 연속합의 최대값

// 개선사항 :
// 최소값, 최대값을 구할 때 Integer.MAX_VALUE, Integer.MIN_VALUE 활용
// 삼중조건문 활용
public class Q13398 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] A = new int[n];
        int[] D1 = new int[n];
        int[] D2 = new int[n];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        D1[0] = A[0];
        for (int i = 1; i < n; i++) {
            D1[i] = (D1[i-1] > 0) ? (A[i] + D1[i-1]) : A[i];
        }

        D2[n-1] = A[n-1];
        for (int i = n-2; i >= 0; i--) {
            D2[i] = (D2[i+1] > 0) ? (A[i] + D2[i+1]) : A[i];
        }

        for (int i = 0; i < n; i++) {
            if (D1[i] > max) {
                max = D1[i];
            }
        }

        for (int i = 1; i < n-1; i++) {
            int maxCandidate = D1[i-1] + D2[i+1];
            if (maxCandidate > max) {
                max = maxCandidate;
            }
        }

        System.out.print(max);
    }
}
