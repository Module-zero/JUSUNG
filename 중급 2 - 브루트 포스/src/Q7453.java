import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q7453 {
    static int n;
    static int[] a = new int[4000];
    static int[] b = new int[4000];
    static int[] c = new int[4000];
    static int[] d = new int[4000];
    static long[] ab = new long[16000000];
    static long[] cd = new long[16000000];
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab[i*n+j] = a[i] + b[j];
                cd[i*n+j] = -(c[i] + d[j]);
            }
        }

        Arrays.sort(ab, 0, n*n);
        Arrays.sort(cd, 0, n*n);

        int p1 = 0, p2 = 0;
        while (p1 < n*n && p2 < n*n) {
            if (ab[p1] == cd[p2]) {
                int n1 = 0, n2 = 0;
                long src1 = ab[p1], src2 = cd[p2];
                while (p1 < n*n && ab[p1] == src1) {
                    n1++;
                    p1++;
                }
                while (p2 < n*n && cd[p2] == src2) {
                    n2++;
                    p2++;
                }
                answer += (long)n1*n2;
            } else if (ab[p1] > cd[p2]) {
                p2++;
            } else {
                p1++;
            }
        }

        System.out.print(answer);
    }
}
