import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 접근법 :
// dfs 는 재귀로, bfs 는 큐로 구현
public class Q1260 {

    static int n;
    static int m;
    static int startV;
    static GraphList graph;

    static boolean[] dfsCheck;
    static StringBuilder dfsAnswer;
    static boolean[] bfsCheck;
    static StringBuilder bfsAnswer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        startV = Integer.parseInt(st.nextToken());
        graph = new GraphList(n);
        dfsCheck = new boolean[n + 1];
        dfsAnswer = new StringBuilder();
        bfsCheck = new boolean[n + 1];
        bfsAnswer = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph.put(v1, v2);
        }

        graph.sort();

        dfs(startV);
        dfsAnswer.deleteCharAt(dfsAnswer.length() - 1);
        System.out.println(dfsAnswer);

        bfs(startV);
        bfsAnswer.deleteCharAt(bfsAnswer.length() - 1);
        System.out.print(bfsAnswer);
    }

    static void dfs(int x) {
        dfsAnswer.append(x + " ");
        dfsCheck[x] = true;

        for (int i = 0; i < graph.getList().get(x).size(); i++) {
            int v = graph.getList().get(x).get(i);
            if (dfsCheck[v] == false) {
                dfs(v);
            }
        }
    }

    static void bfs(int x) {
        LinkedList<Integer> queue = new LinkedList<>();
        bfsCheck[x] = true;
        queue.add(x);

        while (!queue.isEmpty()) {
            int front = queue.poll();
            bfsAnswer.append(front + " ");
            for (int i = 0; i < graph.getList().get(front).size(); i++) {
                int v = graph.getList().get(front).get(i);
                if (bfsCheck[v] == false) {
                    bfsCheck[v] = true;
                    queue.add(v);
                }
            }
        }
    }

    // 인접리스트 클래스
    static class GraphList {

        private ArrayList<ArrayList<Integer>> list;

        public GraphList(int listSize) {
            list = new ArrayList<>();
            for (int i = 0; i < listSize + 1; i++) {
                list.add(new ArrayList<Integer>());
            }
        }

        public ArrayList<ArrayList<Integer>> getList() {
            return this.list;
        }

        // 각 정점에 연결되어 있는 노드들을 오름차순으로 정렬하여 재배치
        public void sort() {
            for (ArrayList<Integer> vList : this.list) {
                Collections.sort(vList);
            }
        }

        // 양방향 그래프 추가
        public void put(int x, int y) {
            list.get(x).add(y);
            list.get(y).add(x);
        }

        // 그래프 출력
        public void printGraph() {
            for(int i = 1; i < list.size(); i++) {
                System.out.print("정점 " + i + "의 인접리스트");
                for(int j = 0; j < list.get(i).size(); j++) {
                    System.out.print(" -> " + list.get(i).get(j));
                } System.out.println();
            }
        }
    }
}
