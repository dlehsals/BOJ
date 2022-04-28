import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1920 {
    static int n, m;
    static int[] cmpArr;        //비교되어지는 수들의 배열
    static int[] findArr;       //찾아야 하는 수들의 배열
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        cmpArr = new int[n];

        for(int i=0;i<n;i++){
            cmpArr[i] = sc.nextInt();
        }

        m = sc.nextInt();
        findArr = new int[m];

        for(int i=0;i<m;i++){
            findArr[i] = sc.nextInt();
        }

        Arrays.sort(cmpArr);

        for(int i=0;i<findArr.length;i++){
            int key = findArr[i];
            if(binarySearch(key) >= 0)
                System.out.println(1);
            else
                System.out.println(0);
        }
    }

    public static int binarySearch(int key){
        int lo = 0;
        int hi = cmpArr.length-1;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            if(key < cmpArr[mid]){
                hi = mid-1;
            }else if(key == cmpArr[mid]){
                return mid;
            }else if(key > cmpArr[mid]){
                lo = mid+1;
            }
        }
        return -1;
    }
}
