import java.io.*;
import java.util.StringTokenizer;

public class Q15651 {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;
    static int m;
    static int[] answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = new int[m];

        go(0, n, m);

        bw.close();
    }

    static void go(int index, int n, int m) throws IOException{

        if (index == m) {
            for (int i = 0; i < m; i++) {
                bw.write(answer[i] + " ");
            }
            bw.newLine();
            bw.flush();
            return;
        }

        for (int i = 1; i <= n; i++) {
            answer[index] = i;
            go(index+1, n, m);
        }
    }
}
