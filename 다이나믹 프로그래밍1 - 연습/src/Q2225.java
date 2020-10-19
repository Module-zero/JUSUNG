import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접근법 :
// 다른 DP 문제들과 마찬가지로 우선
// 1. 문제의 경우의 수를 나눔(경우의 수는 '반드시 존재하는 경우' 를 기준으로 나눔)
// 2. 나눠지는 경우의 수를 기반으로 공통되는 부분을 추출하여 D 배열을 정의할 수 있음
// 3. 그리고 D 배열의 원소를 채우는 과정 역시 추론해볼 수 있음
// 4. 추론이 확정되면 점화식을 세울 수 있음
// 전반적인 DP 문제들은 위와 같은 방법으로 해결 가능

// 개선사항 :
// 없음
public class Q2225 {

    static int[][] D;
    static int divisor = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        D = new int[n+1][k+1];
        for (int i = 0; i <= n; i++) { D[i][1] = 1; }
        for (int i = 2; i <= k; i++) { D[0][i] = 1; }

        for (int i = 2; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                D[j][i] = (D[j-1][i] + D[j][i-1]) % divisor;
            }
        }
        System.out.print(D[n][k]);
    }
}
