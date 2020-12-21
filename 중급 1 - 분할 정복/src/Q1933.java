import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1933 {
    static int n;
    static PriorityQueue<Total> buildings = new PriorityQueue<>((o1, o2) -> o1.getPos() - o2.getPos());
    static PriorityQueue<Total> tmp = new PriorityQueue<>((o1, o2) -> o2.getHeight() - o1.getHeight());
    static TreeMap<Integer, Integer> answer = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            Total s = new Total(start, height, 0, null);
            Total e = new Total(end, height, 1, null);
            s.setOther(e);
            e.setOther(s);
            buildings.add(s);
            buildings.add(e);
        }

        while (!buildings.isEmpty()) {
            Total t = buildings.poll();

            // 시작점인 경우
            if (t.getType() == 0) {
                // 높이 정보의 업데이트를 위해 현재 좌표객체를 저장
                tmp.add(t);
                // 현재 좌표와 좌표에서 가장 높은 높이를 찾아 정답에 저장
                answer.put(t.getPos(), tmp.element().getHeight());
            } else {
                // 시작점 삭제
                tmp.remove(t.getOther());
                // 스카이라인의 끝인 경우
                if (!tmp.isEmpty()) {
                    answer.put(t.getPos(), tmp.element().getHeight());
                } else {
                    answer.put(t.getPos(), 0);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int prev = -1;
        Set<Integer> keys = answer.keySet();
        for (Integer k : keys) {
            int v = answer.get(k);
            if (prev != v) {
                prev = v;
                sb.append(k).append(" ").append(v).append(" ");
            }
        }
        System.out.print(sb);
    }

    static class Total {
        private final int pos, height, type;
        private Total other;
        public Total(int pos, int height, int type, Total other) {
            this.pos = pos;
            this.height = height;
            this.type = type;
            this.other = other;
        }
        public int getPos() { return pos; }
        public int getHeight() { return height; }
        public int getType() { return type; }
        public Total getOther() { return other; }
        public void setOther(Total other) { this.other = other; }
    }
}
