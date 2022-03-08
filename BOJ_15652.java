import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15652 {
    static int n, m;
    static boolean[] visited;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        visited = new boolean[n];

        dfs(0, 0);
    }
    private static void dfs(int num, int depth){
        if(depth == m){
            for(int i=0;i<m;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
            return ;
        }
        for(int i=num;i<n;i++){
            visited[i] = true;
            arr[depth] = i+1;
            dfs(i, depth+1);
            visited[i] = false;
        }
    }
}
