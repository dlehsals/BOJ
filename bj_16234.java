/*
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class bj_16234 {
    static int n, l, r, days;
    static boolean state;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Pair> association;
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
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        visited = new boolean[n][n];
        days = 0;
        association = new ArrayList<>();

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while(true){
            state = false;
            visited = new boolean[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(!visited[i][j]){

                    }
                }
            }
        }

    }
    static public int bfs(){
        Pair start = association.get(0);            //맨 처음 체크할 나라
        Queue<Pair> q = new LinkedList<>();
        q.offer(start);

        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i=0;i<4;i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(!visited[nx][ny]){

                }
            }
        }

        return
    }
    static public List<Pair> open15gg(){       //l과 r사이 인구를 가진 나라가 있으면 리스트에 쳐넣고 state = true를 시켜서 루프종료할지 말지 결정한다
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j] >= l && map[i][j] <= r){
                    state = true;
                    association.add(new Pair(i, j));
                }
                else state = false;
            }
        }
        return association;
    }
    static public void showMap(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
*/
