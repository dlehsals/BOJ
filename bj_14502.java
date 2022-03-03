import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_14502 {
    static int[][] map;
    static int[][] copy;
    static int[][] virusMap;
    static boolean[][] visited;
    static int n, m, answer;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

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
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        copy = new int[n][m];
        virusMap = new int[n][m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = map[i][j];
            }
        }
        makeWall(0);
        System.out.println(answer);
    }

    static private void makeWall(int count){    //벽 세우기(dfs를 이용해서 모든 경우의 수를 확인)
        if(count == 3) {
            spreadVirus();
            safeArea();
            return;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(copy[i][j] == 0){
                    copy[i][j] = 1;
                    makeWall(count + 1);
                    copy[i][j] = 0;
                }
            }
        }
    }

    static private void safeArea(){         //안전구역 체크
        int safeCount = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(virusMap[i][j] == 0){
                    safeCount++;
                }
            }
        }
        answer = Math.max(safeCount, answer);
    }

    static private void spreadVirus(){
        Queue<Pair> q = new LinkedList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                virusMap[i][j] = copy[i][j];
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(virusMap[i][j] == 2){
                    q.offer(new Pair(i, j));
                }
            }
        }
        while(!q.isEmpty()){
            Pair p = q.poll();
            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(virusMap[nx][ny] == 0 && !visited[nx][ny]){
                    virusMap[nx][ny] = 2;
                    q.offer(new Pair(nx, ny));
                }
            }
        }
    }
}