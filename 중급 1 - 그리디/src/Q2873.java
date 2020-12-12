import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2873 {
    static int r, c;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        // r 또는 c 가 홀수라면 모든 좌표를 다 방문해서 도착좌표에 도달할 수 있음
        if (r % 2 != 0) {
           for (int i = 0; i < r; i++) {
               if (i % 2 == 0) {
                   for (int j = 0; j < c-1; j++) { sb1.append('R'); }
                   if (i != r-1) { sb1.append('D'); }
               } else {
                   for (int j = 0; j < c-1; j++) { sb1.append('L'); }
                   sb1.append('D');
               }
           }
           System.out.print(sb1);
        } else if (c % 2 != 0) {
            for (int i = 0; i < c; i++) {
                if (i % 2 == 0) {
                    for (int j = 0; j < r-1; j++) { sb1.append('D'); }
                    if (i != c-1) { sb1.append('R'); }
                } else {
                    for (int j = 0; j < r-1; j++) { sb1.append('U'); }
                    sb1.append('R');
                }
            }
            System.out.print(sb1);
        } else {
            // 체스판의 검은칸 중 가장 작은 값을 찾는다
            int minX = 0, minY = 1;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if ((i + j) % 2 != 0) {
                        if (map[minX][minY] > map[i][j]) {
                            minX = i;
                            minY = j;
                        }
                    }
                }
            }

            int startX = 0, startY = 0;
            int endX = r-1, endY = c-1;

            // 시작좌표와 도착좌표를 상하방향으로 계속 이동시켜서
            // 두개 모두 최소값 좌표가 포함되어있는 2*c 직사각형에 들어올때까지 반복
            while (endX - startX > 1) {
                // 나누기 2 를 해서 비교하는 이유는 위에서부터 2개행씩 묶었을때
                // 시작좌표와 최소값 좌표가 같은 그룹에 속해있는지를 체크하는 것
                // 즉 시작좌표가 최소값 좌표가 포함되어있는 2*c 직사각형에 들어왔는지를 체크
                if (startX/2 != minX/2) {
                    for (int i = 0; i < c-1; i++) { sb1.append('R'); }
                    sb1.append('D');
                    for (int i = 0; i < c-1; i++) { sb1.append('L'); }
                    sb1.append('D');
                    startX += 2;
                }
                if (endX/2 != minX/2) {
                    for (int i = 0; i < c-1; i++) { sb2.append('R'); }
                    sb2.append('D');
                    for (int i = 0; i < c-1; i++) { sb2.append('L'); }
                    sb2.append('D');
                    endX -= 2;
                }
            }

            // 시작좌표와 도착좌표를 좌우방향으로 계속 이동시켜서
            // 두개 모두 최소값 좌표가 포함되어있는 2*2 정사각형에 들어올때까지 반복
            while (endY - startY > 1) {
                if (startY/2 != minY/2) {
                    sb1.append('D');
                    sb1.append('R');
                    sb1.append('U');
                    sb1.append('R');
                    startY += 2;
                }
                if (endY/2 != minY/2) {
                    sb2.append('D');
                    sb2.append('R');
                    sb2.append('U');
                    sb2.append('R');
                    endY -= 2;
                }
            }

            // 2*2 정사각형은 2개의 유형임
            if (startY == minY) {
                sb1.append('R');
                sb1.append('D');
            } else {
                sb1.append('D');
                sb1.append('R');
            }

            sb2.reverse();
            System.out.print(sb1.toString() + sb2.toString());
        }
    }
}
