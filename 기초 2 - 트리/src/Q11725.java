import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q11725 {
    static int n;
    static GraphList graph;
    static LinkedList<Integer> q = new LinkedList<>();
    static boolean[] visited;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new GraphList(n);
        visited = new boolean[n+1];
        parent = new int[n+1];

        // 트리 생성
        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph.putBidir(x, y);
        }

        // bfs 진행
        visited[1] = true;
        q.add(1);
        while (!q.isEmpty()) {
            int p = q.poll();
            ArrayList<Integer> list = graph.getList().get(p);
            for (int i = 0; i < list.size(); i++) {
                int node = list.get(i);
                if (!visited[node]) {
                    visited[node] = true;
                    q.add(node);
                    parent[node] = p;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < parent.length; i++) {
            sb.append(parent[i]+"\n");
        }
        System.out.print(sb);
    }

    static class GraphList {
        private ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        public GraphList(int size) {
            for (int i = 0; i <= size; i++) {
                list.add(new ArrayList<>());
            }
        }

        public ArrayList<ArrayList<Integer>> getList() {
            return this.list;
        }

        // 양방향 그래프 생성
        public void putBidir(int x, int y) {
            list.get(x).add(y);
            list.get(y).add(x);
        }
    }
}
