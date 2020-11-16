import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q16947 {

    static int N;
    static GraphList graph;
    static int[] visited;
    static LinkedList<Integer> queue = new LinkedList<>();
    static int cycleStart = -1;
    static ArrayList<Integer> cycleList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new GraphList(N);
        visited = new int[N+1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph.put(n1,n2);
        }

        // 사이클의 시작지점을 찾아냄
        int start = graph.getList().get(1).get(0);
        cycleStart = findCycleStart(start);

        // 사이클에 속하는 역들을 찾아냄
        visited = new int[N+1];
        findCycle(cycleStart, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(getAnswer(i) + " ");
        }
        System.out.print(sb);
    }

    // n 역부터 순환선까지의 거리를 리턴
    static int getAnswer(int n) {
        if (checkCycle(n)) {
            return 0;
        }
        visited = new int[N+1];
        queue = new LinkedList<>();
        queue.add(n);
        visited[n] = 1;
        while (!queue.isEmpty()) {
            int p = queue.poll();
            ArrayList<Integer> list = graph.getList().get(p);
            for (int i = 0; i < list.size(); i++) {
                int np = list.get(i);
                if (visited[np] == 0) {
                    queue.add(np);
                    visited[np] = visited[p] + 1;
                    if (checkCycle(np)) {
                        return visited[np]-1;
                    }
                }
            }
        }
        return -1;
    }

    // n 이 사이클에 포함되는지 체크
    static boolean checkCycle(int n) {
        for (Integer i : cycleList) {
            if (i == n) {
                return true;
            }
        }
        return false;
    }


    static boolean findCycle(int start, int depth) {
        visited[start] = depth;
        cycleList.add(start);
        ArrayList<Integer> list = graph.getList().get(start);
        for (int i = 0;  i < list.size(); i++) {
            int np = list.get(i);
            if (visited[start]-1 != visited[np] && np == cycleStart) {
                return true;
            }
            if (visited[np] == 0) {
                if (findCycle(np, depth+1)) {
                    return true;
                }
                cycleList.remove(cycleList.size()-1);
                visited[np] = 0;
            }
        }
        return false;
    }

    static int findCycleStart(int start) {
        queue.add(start);
        visited[start] = 1;
        while (!queue.isEmpty()) {
            int p = queue.poll();
            ArrayList<Integer> list = graph.getList().get(p);
            for (int i = 0; i < list.size(); i++) {
                int np = list.get(i);
                if (visited[np] == 0) {
                    queue.add(np);
                    visited[np] = visited[p] + 1;
                }
                else {
                    // 사이클의 정점의 개수가 홀수
                    if (visited[np] == visited[p]) {
                        return np;
                    }
                    // 사이클의 정점의 개수가 짝수
                    if(visited[np] == visited[p] + 1) {
                        return np;
                    }
                }
            }
        }
        return -1;
    }

    // 인접리스트 클래스
    static class GraphList {

        private ArrayList<ArrayList<Integer>> list;

        public GraphList(int listSize) {
            list = new ArrayList<>();
            // List 는 제로베이스이므로 입력된 사이즈보다 하나 더 크게 잡아줌
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
