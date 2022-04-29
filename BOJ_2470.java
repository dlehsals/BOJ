import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {
    static int n, min;
    static int[] arr;
    static int[] result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        result = new int[2];
        min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        binarySearch();
        System.out.println(result[0]+" " + result[1]);

    }

    public static void binarySearch(){
        int low = 0;
        int high = n-1;

        while(low < high){
            int sum = arr[low] + arr[high];

            if(Math.abs(sum) < min){
                result[0] = arr[low];
                result[1] = arr[high];
                min = Math.abs(sum);
                if(sum == 0) break;
            }
            if(sum > 0)
                high--;
            else
                low++;
        }
    }
}
