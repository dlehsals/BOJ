import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889 {
    static int n;
    static int map[][];
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visited = new boolean[n];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(min);
    }

    public static void calc(){
        int start = 0;
        int link = 0;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(visited[i] == true && visited[j] == true){
                    start += map[i][j];
                    start += map[j][i];
                }
                if(visited[i] == false && visited[j] == false){
                    link += map[i][j];
                    link += map[j][i];
                }
            }
        }
        int tmp = Math.abs(start-link);
        if(tmp == 0){
            System.out.println(tmp);
            System.exit(0);
        }
        min = Math.min(tmp, min);
    }

    public static void dfs(int num, int depth){
        if(depth == n / 2){
            calc();
            return;
        }

        for(int i=num;i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i, depth+1);
                visited[i] = false;
            }
        }
    }
}
