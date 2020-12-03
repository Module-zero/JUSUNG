import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q1212 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder octNum = new StringBuilder(br.readLine());
        StringBuilder answer = new StringBuilder();

        List<String> list = new ArrayList<>();
        list.add("000");
        list.add("001");
        list.add("010");
        list.add("011");
        list.add("100");
        list.add("101");
        list.add("110");
        list.add("111");

        for (int i = 0; i < octNum.length(); i++) {
            int num = octNum.charAt(i) - '0';
            answer.append(list.get(num));
        }

        if (!answer.toString().equals("000")) {
            while (answer.charAt(0) != '1') {
                answer.deleteCharAt(0);
            }
            System.out.print(answer);
        }
        else {
            System.out.print("0");
        }
    }
}
