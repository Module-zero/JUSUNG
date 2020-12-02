import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q3055 {
    static int r, c;
    static char[][] map;
    static PriorityQueue<Node> q = new PriorityQueue<>();
    static boolean[][] visited;
    static int answer = -1;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];

        // 고슴도치, 물의 시작위치를 모두 저장
        ArrayList<Node> nodes = new ArrayList<>();
        Node end = null;
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') {
                    nodes.add(new Node(i, j, 0, 1));
                } else if (map[i][j] == '*') {
                    nodes.add(new Node(i, j, 0, 0));
                } else if (map[i][j] == 'D'){
                    end = new Node(i, j, -1, -1);
                }
            }
        }

        for (Node n : nodes) {
            visited[n.getX()][n.getY()] = true;
            q.add(n);
        }

        while (!q.isEmpty()) {
            Node p = q.poll();
            int type = p.getType();
            int depth = p.getDepth();
            if (p.getX() == end.getX() && p.getY() == end.getY()) {
                answer = depth;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
                    if (map[nx][ny] != 'X' && !visited[nx][ny]) {
                        // 물은 비버굴에 들어갈 수 없음
                        if (type == 0) {
                            if (map[nx][ny] != 'D') {
                                visited[nx][ny] = true;
                                q.add(new Node(nx, ny, depth+1, 0));
                            }
                        } else {
                            visited[nx][ny] = true;
                            q.add(new Node(nx, ny, depth+1, 1));
                        }
                    }
                }
            }
        }

        if (answer == -1) {
            System.out.print("KAKTUS");
        } else {
            System.out.print(answer);
        }
    }

    static class Node implements Comparable<Node> {
        // 물 : type -> 0
        // 고슴도치 : type -> 1
        private int x, y, depth, type;
        public Node(int x, int y, int depth, int type) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.type = type;
        }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getDepth() { return depth; }
        public int getType() { return type; }

        @Override
        public int compareTo(Node o) {
            if (this.depth - o.depth != 0) {
                return this.depth - o.depth;
            } else {
                return this.type - o.type;
            }
        }

        public String toString() {
            return  x+","+y+","+depth+","+type;
        }
    }
}
