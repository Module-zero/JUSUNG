import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 모을 수 있는 에너지의 최대값
// -> 모든 경우를 테스트해보면 됨

// 리스트를 쓰면 정말 편한 문제임
// 컬렉션을 못쓰는 문제인 경우 직접 리스트 구현해서 풀어도 편할듯

public class Q16198 {

    static int N;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ArrayList<Integer> w = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            w.add(Integer.parseInt(st.nextToken()));
        }

        go(w, 0);
        System.out.print(max);
    }

    static void go(ArrayList<Integer> w, int sum) {

        if (w.size() == 2) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 1; i < w.size()-1; i++) {
            ArrayList<Integer> tmpW = (ArrayList<Integer>)w.clone();
            tmpW.remove(i);
            go(tmpW, sum + w.get(i-1) * w.get(i+1));
        }
    }
}
