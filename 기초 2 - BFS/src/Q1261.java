import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1261 {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static PriorityQueue<Node> q = new PriorityQueue<Node>();
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        q.add(new Node(0, 0, 0));
        while (!q.isEmpty()) {
            Node p = q.poll();
            if (visited[p.getX()][p.getY()]) {
                continue;
            }
            visited[p.getX()][p.getY()] = true;

            if (p.getX() == n-1 && p.getY() == m-1) {
                System.out.print(p.getCost());
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                int cost = p.getCost();
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (map[nx][ny] == 1) {
                        cost++;
                    }
                    q.add(new Node(nx, ny, cost));
                }
            }
        }
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
