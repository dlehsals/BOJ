import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_4963 {
    static int[][] map;
    static boolean[][] visited;
    static int w, h, cnt;
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringTokenizer st2;
        while(true){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0) break;
            map = new int[h][w];
            visited = new boolean[h][w];
            for(int i=0;i<h;i++){
                st2 = new StringTokenizer(br.readLine());
                for(int j=0;j<w;j++){
                    map[i][j] = Integer.parseInt(st2.nextToken());
                }
            }
            cnt = 0;
            for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){
                    if(!visited[i][j] && map[i][j] == 1){
                        dfs(i, j);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
    public static void dfs(int x, int y){
        visited[x][y] = true;
        for(int i=0;i<8;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
            if(map[nx][ny] == 1 && !visited[nx][ny]){
                dfs(nx, ny);
            }
        }
    }
}
