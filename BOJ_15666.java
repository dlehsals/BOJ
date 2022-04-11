import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15666 {
    static int n, m;
    static int[] nums;
    static int[] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());       //n개 중에
        m = Integer.parseInt(st.nextToken());       //m개 고르기

        nums = new int[n];                          //고를 숫자들의 배열
        arr = new int[m];                           //고른 숫자들의 배열
        visited = new boolean[10][10];                   //숫자 이용했는지 체크

        st = new StringTokenizer(br.readLine(), " ");

        for(int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());  // 0 ~ n까지
        }
        Arrays.sort(nums);
        dfs(0, 0);
    }

    public static void dfs(int index, int depth){
        if(depth == m){
            for(int i=0;i<m;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
            return;
        }
        int preNum = -1;
        for(int i=index;i<n;i++){
            if(i > 0 && preNum == nums[i]) continue;
            preNum = nums[i];
            arr[depth] = nums[i];
            dfs(i, depth+1);
        }
    }
}
