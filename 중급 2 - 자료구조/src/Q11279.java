import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Q11279 {
    static int n;
    static PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2 - o1);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
           int x = Integer.parseInt(br.readLine());
           if (x == 0) {
               if (q.isEmpty()) {
                   sb.append("0\n");
               } else {
                   sb.append(q.poll()).append("\n");
               }
           } else {
               q.add(x);
           }
        }

        System.out.print(sb);
    }
}
