import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16917 {
    static int a, b, c, x, y;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        int maxBanBanCnt = Math.max(x, y)*2;
        for (int i = 0; i <= maxBanBanCnt; i+=2) {
            int nx = Math.max(x-i/2, 0);
            int ny = Math.max(y-i/2, 0);
            int price = nx*a + ny*b + i*c;
            min = Math.min(min, price);
        }

        System.out.print(min);
    }
}
