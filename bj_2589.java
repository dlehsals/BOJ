import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class bj_2589 {
    static int n, m, minTime;
    static Character map[][];
    static int visited[][];
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {1, -1, 0, 0};
    static public class Pair{
        int x;
        int y;
        int distance;
        Pair(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new Character[n][m];
        visited = new int[n][m];

        for(int i=0;i<n;i++){
            String str = br.readLine();
            for(int j=0;j<m;j++){
                map[i][j] = str.charAt(j);
            }
        }
        minTime = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] == 'L'){
                    visited = new int[n][m];
                    int time = bfs(i, j);
                    minTime = Math.max(minTime, time);
                }
            }
        }
        System.out.println(minTime);
    }
    static public int bfs(int x, int y){
        int answer = 0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x, y, 0));
        visited[x][y] = 1;

        while(!q.isEmpty()){
            Pair curNode = q.poll();
            for(int i=0;i<4;i++){
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(map[nx][ny] == 'L' && visited[nx][ny] == 0){
                    visited[nx][ny] = 1;
                    q.offer(new Pair(nx, ny, curNode.distance+1));
                    answer = Math.max(answer, curNode.distance+1);
                }
            }
        }
        return answer;
    }
}
