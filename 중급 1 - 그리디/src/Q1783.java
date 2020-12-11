import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1783 {
    static int n, m;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if (n == 1) {
            answer = 1;
        } else if (n == 2) {
            answer = Math.min(4, (m+1)/2);
        } else if (m < 7) {
            answer = Math.min(4, m);
        } else {
            answer = 5 + m - 7;
        }

        System.out.print(answer);
    }
}
