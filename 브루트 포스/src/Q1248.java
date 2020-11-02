import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 재귀를 활용한 백트래킹

// 결과적으로는 탐색을 통해 순열을 구하는 문제임
// 즉 일단은 모든 경우의 수를 재귀를 통해 구하는 방법을 도입할 수 있음
// 그런데 해당 문제는 모든 경우의 수를 구할 경우 시간초과가 발생함
// 백트래킹은 구할 수 있는 모든 경우의 수를 구하는 과정에서 필요없는 과정은 제하는 방법임
// 따라서 해당 과정에서 불필요한 재귀는 수행하지 않도록 해야함

// 문제를 이해하는데 시간이 오래걸림
// 주어진 조건을 잘 활용하지 못함
// 전반적인 구현 능력이 아직 부족함

public class Q1248 {

    static int N;
    static char[][] S;
    static ArrayList<Integer> A = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new char[N][N];
        StringBuilder sb = new StringBuilder(br.readLine());
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                S[i][j] = sb.charAt(0);
                sb.deleteCharAt(0);
            }
        }
        go(0);
    }

    static boolean go(int index) {

        if (index == N) {
            for (Integer i : A) {
                System.out.print(i + " ");
            } System.out.println();

            // 최초의 답을 찾으면 true 를 리턴해서
            // 재귀를 전부 빠져나가도록 함
            return true;
        }

        if (S[index][index] == '0') {
            A.add(0);
            if (go(index + 1)) {
                return true;
            }
            else {
                A.remove(A.size() - 1);
                return false;
            }
        }

        int start = S[index][index] == '+' ? 1 : -10;
        int end = S[index][index] == '-' ? -1 : 10;
        for (int i = start; i <= end; i++) {
            A.add(i);
            // index 의 위치에 i 가 들어갈 수 있는지를 체크함
            if (check(index, i) == false) {
                A.remove(A.size() - 1);
                continue;
            }

            if (go(index + 1)) {
                return true;
            }
            else {
                A.remove(A.size() - 1);
            }
        }
        return false;
    }

    static boolean check(int index, int num) {
        for (int i = 0; i <= index; i++) {
            int sum = 0;
            for (int k = i; k <= index; k++) {
                sum += A.get(k);
            }
            if (sum > 0) {
                if (S[i][index] == '-' || S[i][index] == '0') {
                    return false;
                }
            }
            else if (sum < 0) {
                if (S[i][index] == '+' || S[i][index] == '0') {
                    return false;
                }
            }
            else {
                if (S[i][index] == '+' || S[i][index] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
