import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접근법 :
// 재귀를 활용한 브루트 포스

// 이유 :
// 15649번과 동일
// 수열이 오름차순이어야하므로 어차피 중복된 수는 나올 수 없기 때문에
// check 배열을 사용하지 않아도 됨

// 이 문제는 go2() 방법을 사용하면 O(2의 n제곱)으로 해결가능
public class Q15650 {

    static int[] answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        answer = new int[m];

        // go(0, n, m, 1);
        go2(1, 0, n, m);
    }

    public static void go(int index, int n, int m, int start) {

        if (index == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(answer[i] + " ");
            } System.out.println();
            return;
        }

        for (int i = start; i <= n; i++) {
            answer[index] = i;
            go(index+1, n, m, i+1);
        }
    }

    public static void go2(int num, int index, int n, int m) {

        if (index == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(answer[i] + " ");
            } System.out.println();
            return;
        }

        if (num > n) {
            return;
        }

        answer[index] = num;

        // 다음 자리(index+1)에 num+1 을 넣는 경우
        go2(num+1, index+1, n, m);

        // 현재 자리(index)에 num 을 넣는 경우
        go2(num+1, index, n, m);
    }
}
