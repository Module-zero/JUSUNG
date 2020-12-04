import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 루트를 직접 찾은 뒤 inorder 를 진행해야함
public class Q2250 {
    static final int LEFT = 0;
    static final int RIGHT = 1;
    static int n;
    static GraphList graph;
    static int column = 1;
    static int maxLv = 0;
    static boolean[] rootCheck = new boolean[10001];
    static int[] min = new int[10001];
    static int[] max = new int[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new GraphList(n);

        // 트리 구성
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int parent = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            graph.putOneWay(parent, left);
            graph.putOneWay(parent, right);
            if (left != -1) { rootCheck[left] = true; }
            if (right != -1) { rootCheck[right] = true; }
        }

        // 초기화
        for (int i = 0; i < min.length; i++) {
            min[i] = Integer.MAX_VALUE;
            max[i] = Integer.MIN_VALUE;
        }

        // 루트 찾기
        int root = 0;
        for (int i = 1; i <= n; i++) {
            if (rootCheck[i] == false) {
                root = i;
            }
        }

        inOrder(root, 1);

        int level = 0, width = 0;
        for (int i = 1; i <= maxLv; i++) {
           if (width < max[i]-min[i]+1) {
               level = i;
               width = max[i]-min[i]+1;
           }
        }

        System.out.print(level+" "+width);
    }

    static void inOrder(int parent, int level) {
        if (parent == -1) {
            return;
        }
        ArrayList<Integer> root = graph.getList().get(parent);
        inOrder(root.get(LEFT), level+1);

        min[level] = Math.min(min[level], column);
        max[level] = Math.max(max[level], column);
        column++;

        inOrder(root.get(RIGHT), level+1);
        maxLv = Math.max(level, maxLv);
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
        public void putBidir(int x, int y) {
            list.get(x).add(y);
            list.get(y).add(x);
        }

        // 단방향 그래프 추가
        public void putOneWay(int x, int y) {
            list.get(x).add(y);
        }

        // 그래프 출력
        public void printGraph() {
            for(int i = 1; i < list.size(); i++) {
                System.out.print(i+": ");
                for(int j = 0; j < list.get(i).size(); j++) {
                    System.out.print(list.get(i).get(j)+" ");
                } System.out.println();
            }
        }
    }
}
