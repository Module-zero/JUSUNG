import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q15656 {

    static int[] answer;
    static int[] nums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        answer = new int[m];
        nums = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        go(0, n, m);
        System.out.print(sb);
    }

    public static void go(int index, int n, int m) {

        if (index == m) {
            for (int i = 0; i < m; i++) {
                sb.append(answer[i] + " ");
            } sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            answer[index] = nums[i];
            go(index+1, n, m);
        }
    }
}
