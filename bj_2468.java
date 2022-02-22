import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_2468 {
    static int[][] map;
    static boolean[][] visited;
    static int n, min_height, max_height, cnt;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        visited = new boolean[n+1][n+1];
        list = new ArrayList<>();
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        min_height = 0;
        max_height = map[0][0];
        for(int i=0;i<n;i++){
            for(int j=1;j<n;j++){
                if(max_height < map[i][j])
                    max_height = map[i][j];
            }
        }
        for(int height = min_height;height <= max_height;height++){
            cnt = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(map[i][j] > height && !visited[i][j]){
                        cnt++;
                        dfs(i, j, height);
                    }
                }
            }
            list.add(cnt);
            visited = new boolean[n+1][n+1];
        }
        int max_cnt = Collections.max(list);
        System.out.print(max_cnt);
    }
    public static void dfs(int x, int y, int height) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if(map[nx][ny] > height && !visited[nx][ny]){
                dfs(nx, ny, height);
            }
        }
    }
}
