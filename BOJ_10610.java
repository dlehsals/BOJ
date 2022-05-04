import java.util.Arrays;
import java.util.Scanner;

public class BOJ_10610{
    static String n;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //30의 배수가 될려면 끝자리는 무조건 0,
        n = sc.next();
        arr = new int[10];
        long sum = 0;
        for(int i=0;i<n.length();i++){
            arr[Integer.parseInt(n.charAt(i)+"")]++;
            sum += Long.parseLong(n.charAt(i)+"");
        }

        if(!n.contains("0") || sum % 3 != 0){
            System.out.println("-1");
            return;
        }

        String str = "";
        for(int i=9;i>=0;i--){
            while(arr[i] > 0){
                str += i;
                arr[i]--;
            }
        }
        System.out.println(str);
    }
}