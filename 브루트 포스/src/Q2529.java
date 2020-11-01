import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q2529 {

    static int k;
    static char[] ieq;
    static boolean[] check;
    static ArrayList<String> list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        ieq = new char[k];
        check = new boolean[10];
        list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            ieq[i] = st.nextToken().charAt(0);
        }

        go(0, "");

        System.out.println(list.get(list.size()-1));
        System.out.println(list.get(0));
    }

    static void go(int index, String num) {

        if (index == k + 1) {
            if (pass(num) == true) {
                list.add(num);
            }
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (check[i] == true) {
                continue;
            }
            check[i] = true;
            go(index+1, num+Integer.toString(i));
            check[i] = false;
        }
    }

    static boolean pass(String num) {

        boolean res = true;
        for (int i = 0; i < ieq.length; i++) {
            if (ieq[i] == '<') {
                if (num.charAt(i) > num.charAt(i+1)) {
                    res = false;
                    break;
                }
            }
            else {
                if (num.charAt(i) < num.charAt(i+1)) {
                    res = false;
                    break;
                }
            }
        }
        return res;
    }
}
