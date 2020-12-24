import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2261 {
    static int n;
    static ArrayList<Pair> list = new ArrayList<Pair>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Pair(x, y));
        }

        // x 좌표를 기준으로 오름차순 정렬
        list.sort(Comparator.comparingInt(Pair::getX));
        System.out.print(closest(0, n-1));
    }

    // list 의 start ~ end 까지의 좌표들 사이의 가장 가까운 거리를 리턴
    static int closest(int start, int end) {
        // 좌표의 개수를 구함
        int size = end-start+1;
        if (size <= 3) {
            return direct(start, end);
        }
        int mid = (start+end) / 2;
        int left = closest(start, mid);
        int right = closest(mid+1, end);
        int min = Math.min(left, right);

        // 같은 위치의 좌표가 2개 이상 주어진 경우
        if (min == 0) { return 0; }

        // mid 까지의 x 방향의 거리가 min 보다 작은 경우인 좌표들을 저장
        ArrayList<Pair> gray = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            int x = list.get(i).getX() - list.get(mid).getX();
            if (x*x < min) {
                gray.add(new Pair(list.get(i).getX(), list.get(i).getY()));
            }
        }

        // y 좌표를 기준으로 오름차순 정렬
        gray.sort(Comparator.comparingInt(Pair::getY));

        int last = gray.size()-1;
        for (int i = 0; i <= last-1; i++) {
           for (int j = i+1; j <= last; j++) {
               int y = gray.get(i).getY() - gray.get(j).getY();
               // 두 점 사이의 y 방향의 거리가 min 보다 작은 경우의 좌표들을 저장
               if (y*y < min) {
                   int dist = getDist(gray.get(i), gray.get(j));
                   min = Math.min(min, dist);
               } else {
                   // 이 이후는 어차피 거리가 min 보다 클 것이므로 확인해볼 필요가 없음
                   break;
               }
           }
        }

        return min;
    }

    // list 의 start ~ end 까지의 좌표들 사이의 가장 가까운 거리 직접 계산해서 리턴
    static int direct(int start, int end) {
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end-1; i++) {
            for (int j = i+1; j <= end; j++) {
                int dist = getDist(list.get(i), list.get(j));
                min = Math.min(min, dist);
            }
        }
        return min;
    }

    // 두 좌표 사이의 거리를 리턴한다. 이 때, 편의상 루트 계산을 하지않고 리턴함을 유의해야함
    static int getDist(Pair p1, Pair p2) {
        int x1 = p1.getX(), y1 = p1.getY();
        int x2 = p2.getX(), y2 = p2.getY();
        return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
    }

    static class Pair {
        private final int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() { return x; }
        public int getY() { return y; }
    }
}
