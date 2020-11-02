import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q1248 {

    static int N;
    static char[][] S;
    static ArrayList<Integer> A = new ArrayList<>();
    static boolean endFlag = false;

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

            // 최초의 답을 찾으면 끝
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
            // index 에 i 가 들어갈 수 있는지를 체크함
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
