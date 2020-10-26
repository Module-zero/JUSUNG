import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 접근법 :
// dfs 혹은 bfs 를 사용

// 이유 :
// dfs, bfs 는 '연결되어있는 노드' 라면 반드시 방문함
// 연결되어있지 않으면 절대 방문하지 않음
// 따라서 한 정점에서 시작하여 한번의 탐색만으로도 연결되어있는 모든 노드가 방문된 것으로 체크됨
public class Q11724 {

    static int n;
    static int m;
    static GraphList graph;
    static boolean[] check;
    static int count;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new GraphList(n);
        check = new boolean[n + 1];
        count = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph.put(v1, v2);
        }

        for (int i = 1; i <= n; i++) {
            if (check[i] == false) {
                dfs(i);
                count++;
            }
        }
        System.out.print(count);
    }

    static void dfs(int x) {
        check[x] = true;
        for (int i = 0; i < graph.getList().get(x).size(); i++) {
            int v = graph.getList().get(x).get(i);
            if (check[v] == false) {
                dfs(v);
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
