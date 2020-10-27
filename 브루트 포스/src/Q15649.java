import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접근법 :
// 재귀를 활용한 브루트포스

// 이유 :
// 무언가 반복되는 작업을 수행할 때 재귀를 사용함
// 길이가 m 인 수열에서 앞에서부터 한자리씩 수를 채워나간다고 했을 때
// 자리마다 동일한 과정을 거쳐서 수를 채워나가므로 재귀를 사용함
// check 배열은 수가 중복으로 나오는 것을 방지하기위함
public class Q15649 {

    static boolean[] check;
    static int[] answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        check = new boolean[n+1];
        answer = new int[m];

        go(0, n, m);
    }

    public static void go(int index, int n, int m) {

        if (index == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(answer[i] + " ");
            } System.out.println();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (check[i] == true) {
                continue;
            }
            answer[index] = i;
            check[i] = true;
            go(index+1, n, m);
            check[i] = false;
        }
    }
}
