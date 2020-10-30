import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14889 {

    static int n;
    static int[][] team;
    static int[] answer;
    static int diff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        team = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                team[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = new int[n];
        go(0, 1);
        System.out.print(diff);
    }

    static void go(int index, int start) {

        if (index == n/2) {
            int[] startTeam = new int[n/2];
            int[] linkTeam = new int[n/2];
            boolean[] check = new boolean[n+1];
            for (int i = 0; i < n/2; i++) {
                startTeam[i] = answer[i];
                check[answer[i]] = true;
            }
            int i = 1;
            int j = 0;
            while (i <= n) {
                if (check[i] == false) {
                    linkTeam[j++] = i;
                }
                i++;
            }

            /*
            System.out.print("startTeam : ");
            for (i = 0; i < startTeam.length; i++) {
                System.out.print(startTeam[i] + " ");
            } System.out.println();
            System.out.print("linkTeam : ");
            for (i = 0; i < linkTeam.length; i++) {
                System.out.print(linkTeam[i] + " ");
            } System.out.println();
             */

            int startTeamSum = 0;
            for (i = 0; i < startTeam.length-1; i++) {
                for (j = i+1; j < startTeam.length; j++) {
                    int p1 = startTeam[i];
                    int p2 = startTeam[j];
                    startTeamSum += (team[p1][p2] + team[p2][p1]);
                }
            }

            int linkTeamSum = 0;
            for (i = 0; i < linkTeam.length-1; i++) {
                for (j = i+1; j < linkTeam.length; j++) {
                    int p1 = linkTeam[i];
                    int p2 = linkTeam[j];
                    linkTeamSum += (team[p1][p2] + team[p2][p1]);
                }
            }

            if (diff > Math.abs(startTeamSum - linkTeamSum)) {
                diff = Math.abs(startTeamSum - linkTeamSum);
            }
            return;
        }

        for (int i = start; i <= n; i++) {

            // 정답 배열의 첫번째 자리수가 처음으로 바뀌면 함수 종료
            // 이후에 나오는 탐색은 무의미함
            if (index == 0 && i == 2) {
                return;
            }

            answer[index] = i;
            go(index+1, i+1);
        }
    }
}
