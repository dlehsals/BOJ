import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_10026 {
    static char[][] map;
    static boolean[][] visited;
    static int n, count, byungsin_cnt, good_cnt;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visited = new boolean[n][n];

        for(int i=0;i<n;i++){
            String str2 = br.readLine();
            for(int j=0;j<n;j++){
                map[i][j] = str2.charAt(j);
            }
        }
        count = 0;
        System.out.println("눈깔 정상인 새끼");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]){
                    eye_good(i, j);     //눈깔 정상인 사람
                    count++;
                }
            }
        }
        good_cnt = count;
        System.out.println();
        visited = new boolean[n][n];
        count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j] == 'G'){
                    map[i][j] = 'R';
                }
            }
        }
        System.out.println("눈깔 병신인새끼");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]){
                    eye_good(i, j);
                    count++;
                }
            }
        }
        byungsin_cnt = count;
        System.out.println(good_cnt + " " + byungsin_cnt);
    }
    public static void eye_good(int x, int y){
        visited[x][y] = true;
        char c = map[x][y];
        System.out.println("시작 알파벳 : " + c);
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if(map[nx][ny] == c && !visited[nx][ny]){       //만약 시작한 알파벳과 다음 이동할 알파벳이 같다면
                System.out.println("다음 알파벳 : " + map[nx][ny]);
                eye_good(nx, ny);
            }
        }
    }
}
