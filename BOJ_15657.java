import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15657 {
    static int n, m;
    static int[] nums;
    static int[] imsi;
    static public StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nums = new int[n];
        imsi = new int[m];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        dfs(0);
        System.out.print(sb);

    }
    private static void dfs(int depth){
        if(depth == m){
            for(int i=0;i<m;i++){
                sb.append(imsi[i] + " ");
            }
            sb.append("\n");
            return ;
        }
        for(int i=0;i<n;i++){
            imsi[depth] = nums[i];
            dfs(depth+1);
        }
    }
}
