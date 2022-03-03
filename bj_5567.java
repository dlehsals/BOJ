import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_5567 {
    static int[][] map;
    static boolean[] visited;
    static int n, m, answer;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visited = new boolean[n];
        answer = 0;
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = map[b][a] = 1;
        }
        for(int i=1;i<n;i++){
            if(map[0][i] == 1){
                if(!visited[i]){
                    answer++;
                    visited[i] = true;
                }
                for(int j=1;j<n;j++){
                    if(map[i][j] == 1 && !visited[j]){
                        answer++;
                        visited[j] = true;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
