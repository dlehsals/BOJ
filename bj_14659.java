import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_14659 {
    static int n, count;
    static int[] arr;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        list = new ArrayList<Integer>();
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<n;i++){
            count = 0;
            for(int j=i+1;j<n;j++){
                if(arr[i] > arr[j]){
                    count++;
                }
            }
            list.add(count);
        }
        System.out.println(Collections.max(list));
    }
}
