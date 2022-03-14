import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569 {
    static int n, m, h;
    static int[][][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static public class Pair{
        int x;
        int y;
        int z;
        Pair(int z, int x, int y){
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");

        m = Integer.parseInt(tmp[0]);
        n = Integer.parseInt(tmp[1]);
        h = Integer.parseInt(tmp[2]);
        Queue<Pair> q = new LinkedList<>();
        map = new int[h][n][m];

        for(int i=0;i<h;i++){
            for(int j=0;j<n;j++){
                tmp = br.readLine().split(" ");
                for(int k=0;k<m;k++){
                    map[i][j][k] = Integer.parseInt(tmp[k]);
                    if(map[i][j][k] == 1) q.offer(new Pair(i, j, k));
                }
            }
        }
        //System.out.println("큐 사이즈 : " + q.size());
        while(!q.isEmpty()){
            //System.out.println("씨발");
            Pair now = q.poll();
            int x = now.x;
            int y = now.y;
            int z = now.z;
            //System.out.println("x, y, z : " + x + y + z);
            for(int i=0;i<6;i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nz = now.z + dz[i];
                if(nx < 0 || ny < 0 || nz < 0 || nx >= n || ny >= m || nz >= h) continue;
                if(map[nz][nx][ny] == 0){
                    //System.out.println("외않대냐고");
                    map[nz][nx][ny] = map[now.z][now.x][now.y] + 1;
                    q.offer(new Pair(nz, nx, ny));

                }
            }
        }

        int dates = Integer.MIN_VALUE;

        for(int i=0;i<h;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<m;k++){
                    if(map[i][j][k] == 0){
                        System.out.println(-1);
                        return ;
                    }
                    dates = Math.max(dates, map[i][j][k]);
                }
            }
        }

        if(dates == 1) {
            System.out.println(0);
            return;
        }
        else System.out.println(dates-1);
        return;
    }

}
