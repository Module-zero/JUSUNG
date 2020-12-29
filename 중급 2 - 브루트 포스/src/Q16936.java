import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q16936 {
    static int n;
    static Node[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new Node[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            long num = 0, d = 0;
            long tmp = num = Long.parseLong(st.nextToken());
            while (tmp % 3 == 0) {
                d++;
                tmp /= 3;
            }
            arr[i] = new Node(num, d);
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (Node n : arr) {
            sb.append(n.getNum()).append(" ");
        }
        System.out.print(sb);
    }

    static class Node implements Comparable<Node> {
        private final long num, d;
        public Node(long num, long d) {
            this.num = num;
            this.d = d;
        }
        public long getNum() { return num; }

        @Override
        public int compareTo(Node o) {
            if (this.d != o.d) {
                return o.d > this.d ? 1 : -1;
            } else {
                return this.num > o.num ? 1 : -1;
            }
        }
    }
}
