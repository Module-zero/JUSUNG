import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16194 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int []p = new int[n + 1];
        int []d = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            p[i] = num;
        }

        for (int i = 1; i <= n; i++) {
            d[i] = p[i];
            for (int j = 1; j <= i; j++) {
                boolean b = (d[i] <= (p[j] + d[i - j]));
                d[i] = b ? d[i] : (p[j] + d[i - j]);
            }
        }
        System.out.println(d[n]);
    }
}
