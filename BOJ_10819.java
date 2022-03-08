import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10819 {
    static int[] nums;
    static int[] imsi;
    static boolean[] visited;
    static int n, answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        nums = new int[n];              //입력받은 숫자들을 저장할 배열
        visited = new boolean[n];
        imsi = new int[n];              //임시로 뽑은 숫자들을 저장할 배열(모든 경우의 수를 계산해야 되서)

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        answer = 0;
        dfs(0);
        System.out.println(answer);
    }

    private static void dfs(int depth){
        if(depth == n){
            int sum = 0;
            for(int i=0;i<n-1;i++){         // i번째와 i+1번째를 계산하기 때문에 배열의 길이 - 1 미만만큼 돈다
                sum += Math.abs(imsi[i]-imsi[i+1]);         //임시로 뽑은 수열들의 값을 절대값으로 취해주고
            }
            answer = Math.max(sum, answer);             //static으로 선언된 answer의 값을 계속 최댓값으로 갱신
            return ;
        }
        for(int i=0;i<n;i++){
            if(!visited[i]){
                imsi[depth] = nums[i];              //아직 사용하지 않은 숫자들에 한해서
                visited[i] = true;
                dfs(depth+1);                 //재귀 ㄱㄱ
                visited[i] = false;
            }
        }
    }
}
