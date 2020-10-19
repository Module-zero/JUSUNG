import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 접근법 :
// '연속' 이라는 조건에 집중함
// D[i] : A[i] 를 시작으로 하는 연속 수열 중 합이 최대인 수열의 합의 값
// 수열 맨 뒤에서부터 거꾸로 접근하면서 이전 수열의 합이 양수이면 더하고 음수이면 더하지 않는 방식 이용

// 개선사항 :
// 변수는 가능하면 전역으로 사용하는 거이 편할듯
public class Q1912 {

    static int[] A;
    static int[] D;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        A = new int[n];
        D = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n - 1; i >= 0; i--) {
            D[i] = A[i];
            if (i < (n - 1) && D[i + 1] >= 0) {
                D[i] += D[i + 1];
            }
        }

        Arrays.sort(D);
        System.out.print(D[n - 1]);
    }
}
