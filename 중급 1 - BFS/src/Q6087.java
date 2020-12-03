import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q6087 {
    static int w, h;
    static char[][] map;
    static boolean[][][] visited;
    static PriorityQueue<Node> q = new PriorityQueue<>();
    static ArrayList<Node> razer = new ArrayList<>();
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        visited = new boolean[h][w][4];
        for (int i = 0; i < h; i++) {
            String s = br.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'C') {
                   razer.add(new Node(i, j, -1, 0));
                }
            }
        }

        Node start = razer.get(0);
        Node end = razer.get(1);

        // 시작좌표를 모든 방향으로 큐에 삽입
        for (int i = 0; i < 4; i++) {
            q.add(new Node(start.getX(), start.getY(), i, 0));
        }

        while (!q.isEmpty()) {
            Node p = q.poll();
            int x = p.getX();
            int y = p.getY();
            int dir = p.getDir();
            int cost = p.getCost();

            if (visited[x][y][dir] == true) {
                continue;
            }
            visited[x][y][dir] = true;

            if (x == end.getX() && y == end.getY()) {
                System.out.print(cost);
                break;
            }

            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx >= 0 && ny >= 0 && nx < h && ny < w) {
                if (map[nx][ny] != '*') {
                    q.add(new Node(nx, ny, dir, cost));
                    q.add(new Node(nx, ny, (dir+1)%4, cost+1));
                    q.add(new Node(nx, ny, (dir+3)%4, cost+1));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        private int x, y, dir, cost;
        public Node(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getDir() { return dir; }
        public int getCost() { return cost; }
        public void setDir(int dir) { this.dir = dir; }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
