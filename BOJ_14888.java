import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888 {
    static int n, max, min;
    static int[] nums;
    static int[] funcs;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        nums = new int[n];
        funcs = new int[4];
        max = Integer.MIN_VALUE;            //제일 작은 값으로 해야 재귀를 돌면서 나오는 수들과 비교해 최댓값을 구할 수 있다
        min = Integer.MAX_VALUE;            //제일 큰 값으로 해야 재귀를 돌면서 나오는 수들과 비교해 최솟값을 구할 수 있다이기

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<4;i++){
            funcs[i] = Integer.parseInt(st.nextToken());
        }

        calculate(nums[0], 1);
        System.out.println(max);
        System.out.println(min);
    }
    private static void calculate(int start, int depth){
        if(depth == n){
            max = Math.max(max, start);
            min = Math.min(min, start);
            return;
        }
        for(int i=0;i<4;i++){
            if(funcs[i] == 0) continue;
            if(i == 0){
                funcs[i]--;
                calculate(start + nums[depth], depth + 1);
            }else if(i == 1){
                funcs[i]--;
                calculate(start - nums[depth], depth + 1);
            }else if(i == 2){
                funcs[i]--;
                calculate(start * nums[depth], depth + 1);
            }else if(i == 3){
                funcs[i]--;
                calculate(start / nums[depth], depth + 1);
            }
            funcs[i]++;
        }
    }
}
