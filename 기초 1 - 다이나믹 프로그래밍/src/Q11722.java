import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 접근법 :
// 11053번 문제와 동일

// 개선사항 :
// 없음
public class Q11722 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] A = new int[len];
        for (int i = 0; i < len; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] D = new int[len];
        for (int i = 0; i < len; i++) {
            D[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[j] > A[i] && D[j] + 1 > D[i]) {
                    D[i] = D[j] + 1;
                }
            }
        }
        Arrays.sort(D);
        System.out.println(D[D.length - 1]);
    }
}
