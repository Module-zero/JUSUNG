import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 접근법 :
// dfs 혹은 bfs

// 이유 :
// dfs 혹은 bfs 는 연결 그래프를 순차적으로 탐색하며
// 방문여부와 같은 노드의 상태에 대한 정보를 저장하는 check 배열을 반드시 사용함
// 해당 문제는 이 배열에 방문여부와 더불어 그룹번호까지 저장함으로서 해결가능

public class Q1707 {

    static int caseNum;
    static int V;
    static int E;
    static GraphList graph;
    static int[] check;
    static boolean flag;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        caseNum = Integer.parseInt(br.readLine());

        while (caseNum-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            graph = new GraphList(V);
            check = new int[V + 1];
            flag = false;

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                graph.put(v1, v2);
            }

            for (int i = 1; i <= V; i++) {
                if (graph.getList().get(i).size() != 0 && check[i] == 0) {
                    dfs(i, 1);
                }
            }

            if (flag == false) {
                System.out.print("YES\n");
            }
            else {
                System.out.print("NO\n");
            }
        }
    }

    static void dfs(int x, int checkNum) {

        check[x] = checkNum;

        for (int i = 0; i < graph.getList().get(x).size(); i++) {
            int v = graph.getList().get(x).get(i);

            // 이분그래프가 아님
            if (check[x] == check[v]) {
                flag = true;
            }

            if (check[v] == 0) {
                dfs(v, 3 - checkNum);
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
