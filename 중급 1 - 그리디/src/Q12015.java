import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q12015 {
    static int n;
    static int[] list;
    static ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        answer.add(list[0]);
        for (int i = 1; i < n; i++) {
            int lowBound = -1;
            for (int j = 0; j < answer.size(); j++) {
                if (answer.get(j) >= list[i]) {
                    lowBound = j;
                    break;
                }
            }
            if (lowBound != -1) {
                answer.set(lowBound, list[i]);
            } else {
                answer.add(list[i]);
            }
        }

        System.out.print(answer.size());
    }
}

