import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Q14226 {

    static int n;
    static PriorityQueue<Node> q = new PriorityQueue<>();
    static boolean[][] visited = new boolean[10000][10000];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        bfs();
    }

    static void bfs() {
        q.add(new Node(1, 0, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (visited[node.getsCnt()][node.getbCnt()] == true) {
                continue;
            }

            visited[node.getsCnt()][node.getbCnt()] = true;

            if (node.getsCnt() == n) {
                System.out.print(node.getCost());
                break;
            }

            if (node.getsCnt() > 0) {
                // 화면에서 삭제
                q.add(new Node(node.getsCnt()-1, node.getbCnt(), node.getCost() + 1));
                // 클립보드에 복사
                q.add(new Node(node.getsCnt(), node.getsCnt(), node.getCost() + 1));
            }

            if (node.getbCnt() > 0) {
                // 화면에 붙여넣기
                q.add(new Node(node.getsCnt() + node.getbCnt(), node.getbCnt(), node.getCost() + 1));
            }
        }
    }

    static class Node implements Comparable<Node> {
        private int sCnt;
        private int bCnt;
        private int cost;
        public Node(int sCnt, int bCnt, int cost) {
            this.sCnt = sCnt;
            this.bCnt = bCnt;
            this.cost = cost;
        }
        public int getsCnt() { return sCnt; }
        public int getbCnt() { return bCnt; }
        public int getCost() { return cost; }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
