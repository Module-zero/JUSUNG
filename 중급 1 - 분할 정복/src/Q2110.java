import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q2110 {
    static int n, c;
    static ArrayList<Integer> house = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            house.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(house);

        int answer = 1;
        int left = 1;
        int right = house.get(house.size()-1) - house.get(0);
        while (left <= right) {
           int mid = (left+right)/2;
           // 가장 인접한 두 공유기 사이의 거리가 mid 일 때, c 개의 공유기를 설치할 수 있는가?
           if (possible(mid)) {
               // 설치할 수 있다면 mid 가 더 커질 수 있는지 확인
               left = mid + 1;
               answer = Math.max(answer, mid);
           } else {
               // 설치할 수 없다면 mid 는 더 작아져야함
               right = mid - 1;
           }
        }

        System.out.print(answer);
    }

    static boolean possible(int dist) {
        // 공유기의 개수
        int cnt = 1;
        int last = house.get(0);
        for (Integer i : house) {
            if (i - last >= dist) {
                cnt++;
                last = i;
            }
        }
        return cnt >= c;
    }
}
