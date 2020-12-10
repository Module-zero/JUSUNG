import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Q1744 {
    static int n;
    static int sum = 0;
    static ArrayList<Integer> list1 = new ArrayList<>();
    static ArrayList<Integer> list2 = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num <= 0) {
                // 0을 포함한 음수를 저장
                list1.add(num);
            } else if (num >= 2) {
                // 1을 제외한 양수를 저장
                list2.add(num);
            } else {
                // 1이 있을 경우 1을 먼저 답에 더해둠
                sum += num;
            }
        }

        Collections.sort(list1);
        Collections.sort(list2, Collections.reverseOrder());
        cal(list1);
        cal(list2);
        System.out.print(sum);
    }

    static void cal(ArrayList<Integer> list) {
        if (list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i += 2) {
            if (i == list.size() || i+1 == list.size()) {
                if (i+1 == list.size()) {
                    sum += list.get(i);
                }
                return;
            }
            sum += list.get(i) * list.get(i+1);
        }
    }
}
