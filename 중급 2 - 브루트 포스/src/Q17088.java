import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17088 {
    static int n;
    static int[] arr = new int[100000];
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int d = arr[0] - arr[1];
        go(1, arr[0], d, 0);
        go(1, arr[0]+1, d+1, 1);
        go(1, arr[0]-1, d-1, 1);

        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    static void go(int index, int prev, int d, int cnt) {

        if (index == n) {
            min = Math.min(min, cnt);
            return;
        }

        int val = arr[index];
        if (index == 1) {
            go(index+1, val, d, cnt);
            go(index+1, val+1, d-1, cnt+1);
            go(index+1, val-1, d+1, cnt+1);
        } else {
            int d1 = prev - val;
            int d2 = prev - (val + 1);
            int d3 = prev - (val - 1);

            if (d == d1) { go(index+1, val, d1, cnt); }
            if (d == d2) { go(index+1, val+1, d2, cnt+1); }
            if (d == d3) { go(index+1, val-1, d3, cnt+1); }
        }
    }
}
