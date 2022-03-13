import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class BOJ_15663 {
    static int n, m;
    static boolean[] visited;
    static int[] nums;
    static int[] result;
    static HashSet<String> hs;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nums = new int[n];
        visited = new boolean[n];
        result = new int[m];
        hs = new LinkedHashSet<>();

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        dfs(0);

        hs.forEach(System.out::println);

    }
    private static void dfs(int depth){
        if(depth == m){
            String tmp = "";
            for(int i=0;i<m;i++){
                tmp += result[i]+ " ";

            }
            hs.add(tmp);
            return;
        }
        for(int i=0;i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                result[depth] = nums[i];
                dfs(depth+1);
                visited[i] = false;
            }
        }
    }
}
