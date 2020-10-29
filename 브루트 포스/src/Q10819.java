import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 재귀를 사용한 브루트 포스

// 우선 N의 범위가 3~8로 작아서 브루트 포스가 가능했음
// 따라서 재귀를 사용하여 모든 수열의 경우의 수열 구한 뒤 계산하여 답을 구함
public class Q10819 {

    static int n;
    static int[] nums;
    static int[] answers;
    static boolean[] check;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n+1];
        answers = new int[n+1];
        check = new boolean[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        go(1);
        System.out.print(max);
    }

    static void go(int index) {

        if (index == n+1) {
            int res = result();
            if (max < res) {
                max = res;
            }
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (check[i] == true) {
                continue;
            }
            answers[index] = nums[i];
            check[i] = true;
            go(index+1);
            check[i] = false;
        }
    }

    static int result() {
        int res = 0;
        for (int i = 1; i < n; i++) {
            res += Math.abs(answers[i] - answers[i+1]);
        }
        return res;
    }
}
