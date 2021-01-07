import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q7453 {
    static int n;
    static int[] a = new int[4000];
    static int[] b = new int[4000];
    static int[] c = new int[4000];
    static int[] d = new int[4000];
    static HashMap<Long, Integer> map = new HashMap<>();
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
                long num = a[i] + b[j];
                if (!map.containsKey(num)) {
                    map.put(num, 1);
                } else {
                    int cnt = map.get(num);
                    map.put(num, cnt+1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long num = c[i] + d[j];
                if (map.containsKey(-num)) {
                    answer += map.get(-num);
                }
            }
        }

        System.out.print(answer);
    }
}
