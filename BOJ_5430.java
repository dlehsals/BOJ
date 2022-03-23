import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5430 {
    static int T, n;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String p = "";              //함수
        String tmp = "";            //배열 저장할 문자열

        T = Integer.parseInt(st.nextToken());

        for(int i=0;i<T;i++){

            p = br.readLine();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            sb = new StringBuilder();

            String str = br.readLine();
            tmp = str.substring(1, str.length()-1);
            String[] arr = tmp.split(",");
            Deque<String> dq = new ArrayDeque<>();          //숫자들을 넣을 덱

            for(int j=0;j<arr.length;j++){                  //빈 배열이 아닌경우 덱에 넣어준다
                if(!"".equals(arr[j]))
                    dq.offer(arr[j]);
            }
            System.out.println(answer(dq, p));
        }
    }
    public static String answer(Deque<String> dq, String p){
        boolean reverse = false;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i) == 'R'){
                reverse = !reverse;
            }
            else{
                if(dq.size() == 0){
                    return "error";
                }
                else{
                    if(reverse){
                        dq.removeLast();
                    }else
                        dq.removeFirst();
                }
            }
        }
        sb = new StringBuilder();
        sb.append("[");
        while(!dq.isEmpty()){
            if(!reverse){
                sb.append(dq.pollFirst());
            }else
                sb.append(dq.pollLast());
            if(dq.size() != 0)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

}
