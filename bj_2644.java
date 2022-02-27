import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2644 {
    static int[][] map;
    static int[] visited;
    static int n, m, start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());               //구해야 하는거
        end = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        visited = new int[n+1];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = map[y][x] = 1;
        }
        bfs(start, end);
        System.out.println(visited[end] == 0 ? -1 : visited[end]);

    }
    public static void bfs(int start, int end){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while(!q.isEmpty()){
            int current = q.poll();
            if(current == end) break;
            for(int i=1;i<=n;i++){
                if(map[current][i] == 1 && visited[i] == 0){
                    q.offer(i);
                    visited[i] = visited[current] + 1;
                }
            }
        }
    }
}
