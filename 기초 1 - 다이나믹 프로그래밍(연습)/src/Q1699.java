import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 접근법 :
// D[i] : i를 제곱수의 합으로 표현할 때 항의 최소개수
// DP 문제를 풀 때, 경우의 수를 나누는 과정을 트리로 표현해보면 겹치는 부분을 찾기가 수월함
// 그리고 겹치는 부분을 D 배열에 어떻게 표현하고 활용할 지를 생각해내면 됨

// 개선사항 :
// 항상 문제를 풀기 전, 내가 생각하는 방법에 대한 시간복잡도 계산이 필요함
public class Q1699 {

    static int[] D;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        D = new int[n + 1];
        D[0] = 0;
        D[1] = 1;

        for (int i = 2; i <= n; i++) {
            D[i] = D[i - 1] + 1;
            for (int j = 2; j*j <= i; j++) {
                if (D[i] > D[i - j*j] + 1) {
                    D[i] = D[i - j*j] + 1;
                }
            }
        }

        System.out.print(D[n]);
    }
}
