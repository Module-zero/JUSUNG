import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q2206 {
    static int n, m;
    static int[][] map;
    static LinkedList<Node> q = new LinkedList<>();
    static boolean[][][] visited;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m][2];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        visited[0][0][0] = true;
        q.add(new Node(0, 0, 1, 0));
        while (!q.isEmpty()) {
            Node p = q.poll();
            int count = p.getCount();
            if (p.getX() == n-1 && p.getY() == m-1) {
                answer = p.getCost();
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (!visited[nx][ny][count]) {
                        if (map[nx][ny] == 1) {
                            if (count == 0) {
                                visited[nx][ny][count] = true;
                                q.add(new Node(nx, ny, p.getCost()+1, count+1));
                            }
                        } else {
                            visited[nx][ny][count] = true;
                            q.add(new Node(nx, ny, p.getCost()+1, count));
                        }
                    }
                }
            }
        }

        System.out.print(answer);
    }

    static class Node implements Comparable<Node> {
        private int x, y, cost, count;
        public Node(int x, int y, int cost, int count) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.count = count;
        }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getCost() { return cost; }
        public int getCount() { return count; }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
