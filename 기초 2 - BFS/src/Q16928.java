import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q16928 {

    static int n, m;
    static int[] arr = new int[101];
    static boolean[] visited = new boolean[101];
    static PriorityQueue<Node> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            arr[idx] = val;
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            arr[idx] = val;
        }

        q.add(new Node(1, 0));
        while(!q.isEmpty()) {
            Node p = q.poll();
            if (visited[p.getX()]) {
                continue;
            }
            visited[p.getX()] = true;

            if (p.getX() == 100) {
                System.out.print(p.getCost());
                break;
            }

            for (int i = 1; i <= 6; i++) {
                int nx = p.getX() + i;
                if (nx <= 100) {
                    if (arr[nx] != 0) {
                        q.add(new Node(arr[nx], p.getCost()+1));
                    }
                    else {
                        q.add(new Node(nx, p.getCost()+1));
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        private int x, cost;
        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
        public int getX() { return x; }
        public int getCost() { return cost; }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
