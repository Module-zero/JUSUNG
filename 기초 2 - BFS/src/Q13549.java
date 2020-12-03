import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q13549 {

    static int n, k;
    static PriorityQueue<Node> q = new PriorityQueue<>();
    static boolean[] visited = new boolean[100001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        bfs(n);
    }

    static void bfs(int n) {

        q.add(new Node(n, 0));
        while (!q.isEmpty()) {
            Node p = q.poll();

            // 방문체크를 이 위치에서 하는 이유는?
            // 이런 유형의 문제에서 우선순위 큐를 사용할 때 핵심은 큐에 노드를 넣을때는 방문체크를 하지 않는 것임
            // 그러면 큐에 같은 값은 같지만 cost 가 다른 여러노드가 들어있을 수 있음
            // 우선순위 큐이므로 어차피 이중에서 가장 작은 cost 를 가지는 노드가 제일 먼저 나옴
            // 그럼 해당 노드를 방문체크하면 나머지 더 큰 cost 를 가지는 노드들은 큐에서 나와도 자동으로 걸러짐
            // 이런 방식을 사용해야 가장 작은 cost 를 가진 노드를 찾아낼 수 있음
            if (visited[p.getX()] == true) {
                continue;
            }
            visited[p.getX()] = true;

            if (p.getX() == k) {
                System.out.print(p.getCost());
                break;
            }

            go2(p, p.getX() * 2);
            go(p, p.getX() - 1);
            go(p, p.getX() + 1);
        }
    }

    // 큐에 노드를 넣을 때 방문여부를 체크하지 않음
    static void go2(Node p, int np) {
        if (np >= 0 && np <= 100000) {
            q.add(new Node(np, p.getCost()));
        }
    }

    // 큐에 노드를 넣을 때 방문여부를 체크하지 않음
    static void go(Node p, int np) {
        if (np >= 0 && np <= 100000) {
            q.add(new Node(np, p.getCost() + 1));
        }
    }

    static class Node implements Comparable<Node> {
        private int x;
        private int cost;
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
