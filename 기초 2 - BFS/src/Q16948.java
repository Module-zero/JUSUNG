import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q16948 {

    static int n, r1, c1, r2, c2;
    static PriorityQueue<Node> q = new PriorityQueue<>();
    static boolean[][] visited = new boolean[201][201];
    static int[] dx = {-2, -2, 0, 0, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -1, 1};
    static int answer = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        q.add(new Node(r1, c1, 0));
        while (!q.isEmpty()) {
            Node p = q.poll();
            if (visited[p.getX()][p.getY()]) {
                continue;
            }
            visited[p.getX()][p.getY()] = true;

            if (p.getX() == r2 && p.getY() == c2) {
                answer = p.getCost();
                break;
            }

            for (int i = 0; i < 6; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    q.add(new Node(nx, ny, p.getCost() + 1));
                }
            }
        }

        System.out.print(answer);
    }

    static class Node implements Comparable<Node> {
        private int x, y, cost;
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getCost() { return cost; }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
