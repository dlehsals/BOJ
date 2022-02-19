import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int map[][];
    static boolean visited[];
    static int N, M, V;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        st = new StringTokenizer(str, " ");

        N = Integer.parseInt(st.nextToken());  //정점의 갯수
        M = Integer.parseInt(st.nextToken());  //간선의 갯수
        V = Integer.parseInt(st.nextToken());  //탐색을 시작할 번호

        map = new int[N+1][N+1]; //좌표를 0이 아닌 1부터
        visited = new boolean[N+1];

        for(int i=0;i<M;i++){
            String s = br.readLine();
            st = new StringTokenizer(s, " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = map[y][x] = 1;   //정점의 좌표를 간선으로 이어주기
        }
        dfs(V);
        visited = new boolean[N+1];
        System.out.println();

        bfs();
        System.out.println();

    }

    public static void dfs(int n){
        visited[n] = true;
        System.out.print(n + " ");
        for(int i=1;i<=N;i++){
            if(map[n][i] == 1 && visited[i] == false){
                //System.out.println("인접한 정점 찾기");
                dfs(i);
            }
        }
    }

    public static void bfs(){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(V);
        visited[V] = true;
        System.out.print(V + " ");

        while(!queue.isEmpty()){
            int next_N = queue.poll();

            for(int i=1;i<=N;i++){
                if(map[next_N][i] == 1 && visited[i] == false){
                    //System.out.println("인접한 정점 찾기");
                    queue.offer(i);
                    visited[i] = true;
                    System.out.print(i + " ");
                }
            }
        }
    }

}
