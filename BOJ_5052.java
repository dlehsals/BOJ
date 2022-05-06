import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_5052 {
    static int t, n;
    static String[] nums;
    static boolean flag;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        while(t-->0){
            flag = false;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            nums = new String[n];
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                nums[i] = st.nextToken();
            }

            Arrays.sort(nums);
            String str = nums[0];
            for(int i=1;i<nums.length;i++){
                if(nums[i].startsWith(str)){
                    flag = true;
                    break;
                }else
                    str = nums[i];
            }
            System.out.println(flag ? "NO" : "YES");
        }
    }
}
