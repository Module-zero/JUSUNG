import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 재귀를 사용한 브루트 포스로 해결

// 정답 배열의 인덱스에 순서대로 접근하며 숫자의 경우들을 채워넣는 것이므로
// 인덱스의 순서에 따라 재귀를 호출하여 해결할 수 있음
public class Q10974 {

    static int[] answer;
    static boolean[] check;
    static StringBuilder sb = null;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        answer = new int[n+1];
        check = new boolean[n+1];
        sb = new StringBuilder();

        go(1, n);
        System.out.print(sb);
    }

    static void go(int index, int n) {

        if (index == n + 1) {
            for (int i = 1; i <= n; i++) {
                sb.append(answer[i] + " ");
            } sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (check[i] == true) {
                continue;
            }
            answer[index] = i;
            check[i] = true;
            go(index+1, n);
            check[i] = false;
        }
    }
}
