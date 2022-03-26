import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719 {
    static int h, w, left, right, current, answer;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        arr = new int[w];
        answer = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<w;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<w-1;i++){
            current = arr[i];       //양 끝에는 물이 고일 수 없다.(블록 사이에만 물이 고이기 때문에)
            left = Integer.MIN_VALUE;
            right = Integer.MIN_VALUE;
            for(int j=0;j<i;j++){
                if(left < arr[j]){
                    left = arr[j];
                }
            }
            for(int j=i+1;j<w;j++){
                if(arr[j] > right){
                    right = arr[j];
                }
            }
            if(current < left && current < right){
                answer += Math.min(left, right) - current;
            }
        }
        System.out.println(answer);
    }
}
