import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Q14395 {
    static final int limit = 1000000000;
    static int s, t;
    static PriorityQueue<Node> q = new PriorityQueue<>();
    static Set<Integer> set = new HashSet<>();
    static String answer = "-1";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        if (s == t) {
            System.out.print(0);
            return;
        }

        q.add(new Node(s, 0, ""));
        while (!q.isEmpty()) {
            Node p = q.poll();
            int n = p.getN();
            int depth = p.getDepth();
            String route = p.getRoute();

            if (set.contains(n)) {
                continue;
            }
            set.add(n);

            if (n == t) {
                answer = route;
                break;
            }

            // + 연산
            if (n + n <= limit) {
                q.add(new Node(n + n, depth + 1, route + "+"));
            }

            // - 연산
            q.add(new Node(0, depth + 1, route + "-"));

            // * 연산
            if (n <= Math.sqrt(limit)) {
                q.add(new Node(n * n, depth + 1, route + "*"));
            }

            // / 연산
            if (n != 0) {
                q.add(new Node(1, depth + 1, route + "/"));
            }
        }

        System.out.print(answer);
    }

    static class Node implements Comparable<Node> {
        private int n, depth;
        private String route;
        public Node(int n, int depth, String route) {
            this.n = n;
            this.depth = depth;
            this.route = route;
        }
        public int getN() { return n; }
        public int getDepth() { return depth; }
        public String getRoute() { return route; }

        @Override
        public int compareTo(Node o) {
            if (this.depth != o.depth) {
                return this.depth - o.depth;
            } else {
                return this.route.compareTo(o.route);
            }
        }
    }
}
