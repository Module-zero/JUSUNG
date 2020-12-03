import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15652 {

    static int[] answer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        answer = new int[m];

        go(0, n, m, 1);

        System.out.print(sb);
    }

    public static void go(int index, int n, int m, int start) {

        if (index == m) {
            for (int i = 0; i < m; i++) {
                sb.append(answer[i] + " ");
            } sb.append("\n");
            return;
        }

        for (int i = start; i <= n; i++) {
            answer[index] = i;
            go(index+1, n, m, i);
        }
    }
}
