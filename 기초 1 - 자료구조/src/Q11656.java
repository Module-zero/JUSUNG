import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q11656 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder(br.readLine());

        int len = sb.length();
        for (int i = 0; i < len; i++) {
            list.add(sb.toString());
            sb.deleteCharAt(0);
        }
        Collections.sort(list);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
