import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접근법 :
// 결과적으로 해결은 했지만 접근법 구상에만 한시간이 걸림
// 앞에서부터 증가하는 수열에 대한 D1 배열을 구하고
// 뒤에서부터 증가하는 수열에 대한 D2 배열을 구한 뒤 더해주면 됨

// 개선사항 :
// 없음
public class Q11054 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] A = new int[len];
        int[] D1 = new int[len];
        int[] D2 = new int[len];

        for (int i = 0; i < len; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            D1[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i] && D1[j] + 1 > D1[i]) {
                    D1[i] = D1[j] + 1;
                }
            }
        }

        for (int i = len - 1; i >= 0; i--) {
            D2[i] = 1;
            for (int j = len - 1; j > i; j--) {
                if (A[j] < A[i] && D2[j] + 1 > D2[i]) {
                    D2[i] = D2[j] + 1;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < len; i++) {
            int tmp = D1[i] + D2[i] - 1;
            if (tmp > answer) {
                answer = tmp;
            }
        }
        System.out.print(answer);
    }
}
