import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class bj_2636 {
    static int[][] map;
    static boolean[][] visited;
    static int n, m, time, cheeseCnt, firstCnt;
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static List<Integer> list;
    static public class Pair{
        int x;
        int y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        firstCnt = 0;
        time = 0;
        list = new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] == 1) firstCnt++;
            }
        }
        while(true){
            cheeseCnt = 0;
            time++;
            visited = new boolean[n][m];
            bfs(0, 0);
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(map[i][j] == 1) cheeseCnt++;
                }
            }
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(map[i][j] == 7)
                        map[i][j] = 0;
                }
            }
            if(cheeseCnt == 0){
                System.out.println(time);
                break;
            }
            list.add(cheeseCnt);
        }
        if(list.size() > 0){
            System.out.println(list.get(list.size()-1));
        }else{
            System.out.println(firstCnt);
        }

    }
    static public void bfs(int x, int y){
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Pair p = q.poll();
            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(!visited[nx][ny]){
                   if(map[nx][ny] == 0){
                       visited[nx][ny] = true;
                       q.offer(new Pair(nx, ny));
                   }
                   else if(map[nx][ny] == 1){
                       visited[nx][ny] = true;
                       map[nx][ny] = 7;
                   }
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] == 7) map[i][j] = 0;
            }
        }
    }
}
