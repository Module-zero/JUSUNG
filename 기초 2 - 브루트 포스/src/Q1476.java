import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접근법 :
// 브루트 포스

// 이유 :
// 모든 경우를 다 해보아도 7980번 밖에 안됨
public class Q1476 {

    static int limit = 15*28*19;
    static int[][] arr = new int[limit + 1][4];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= limit; i++) {
            int e = arr[i-1][0] + 1;
            int s = arr[i-1][1] + 1;
            int m = arr[i-1][2] + 1;

            arr[i][0] = (e == 15) ? 15 : (e % 15);
            arr[i][1] = (s == 28) ? 28 : (s % 28);
            arr[i][2] = (m == 19) ? 19 : (m % 19);
            arr[i][3] = arr[i-1][3] + 1;

            if (arr[i][0] == E && arr[i][1] == S && arr[i][2] == M) {
                System.out.print(arr[i][3]);
                break;
            }
        }
    }
}
