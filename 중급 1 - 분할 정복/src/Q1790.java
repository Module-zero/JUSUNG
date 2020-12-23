import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1790 {
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (calLen(n) < k) {
            System.out.print(-1);
            return;
        }

        int answer = 0;
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = (left + right) / 2;
            int len = calLen(mid);
            if (len < k) {
                left = mid+1;
            } else {
                answer = mid;
                right = mid-1;
            }
        }

        String s = String.valueOf(answer);
        int len = calLen(answer);
        System.out.print(s.charAt(s.length()-1-(len-k)));
    }

    static int calLen(int n) {
        int digit = String.valueOf(n).length();
        int res = 0;
        for (int i = 1; i <= digit-1; i++) {
            res += 9*i*Math.pow(10, i-1);
        }
        res += digit * (n-Math.pow(10, digit-1)+1);
        return res;
    }
}
