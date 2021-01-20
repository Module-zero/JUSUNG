import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q6549 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            if(n == 0) break;
            long [] a = new long[n];
            for(int i=0; i<n; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }
            Stack<Integer> s = new Stack<>();
            long ans = 0;
            for(int i=0; i<n; i++) {
                while(!s.isEmpty() && a[s.peek()] > a[i]) {
                    long height = a[s.pop()];
                    int width = i;
                    if(!s.isEmpty()) {
                        width = i-s.peek()-1;
                    }
                    if(ans < width * height) {
                        ans = width * height;
                    }
                }
                s.push(i);
            }
            while(!s.isEmpty()) {
                long height = a[s.pop()];
                int width = n;
                if(!s.isEmpty()) {
                    width = n-s.peek()-1;
                }
                if(ans < width * height) {
                    ans = width * height;
                }
            }
            System.out.println(ans);
        }
    }
}
