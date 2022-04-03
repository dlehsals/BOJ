import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10974 {
    static int n;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new boolean[n];

        solve(0, 0);
    }

    public static void solve(int num, int depth){
        if(depth == n){
            for(int i=0;i<n;i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i=0;i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                arr[depth] = i+1;
                solve(i, depth+1);
                visited[i] = false;
            }
        }
    }
}
