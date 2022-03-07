import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6603 {
    static int k;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine(), " ");
            k = Integer.parseInt(st.nextToken());
            arr = new int[k];
            visited = new boolean[k];
            if(k == 0) break;
            for(int i=0;i<k;i++){
                int num = Integer.parseInt(st.nextToken());
                arr[i] = num;
            }
            int depth = 0;              //숫자의 자릿수
            dfs(0, depth);
            System.out.println();
        }
    }
    private static void dfs(int num, int depth){
        if(depth == 6){             // 여섯개만 뽑으면 되니까~
            for(int i=0;i< arr.length;i++){
                if(visited[i]){
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println();
            return ;
        }

        for(int i=num;i<arr.length;i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i+1, depth+1);
                visited[i] = false;
            }
        }
    }
}
