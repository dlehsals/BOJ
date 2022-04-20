import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1541 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        int firstNum = 0;
        int answer = 0;
        int index = str.indexOf('-');
        String temp = "";
        if(index != -1){
            String t = "";
            for(int i=0;i<index;i++){
                if(Character.isDigit(str.charAt(i))){
                    t += str.charAt(i);
                }else{
                    firstNum += Integer.parseInt(t);
                    t="";
                }
            }
            firstNum += Integer.parseInt(t);
            //System.out.println("first : " + firstNum);
            for(int i=index+1;i<str.length();i++){
                if(Character.isDigit(str.charAt(i))){
                    temp += str.charAt(i);
                }
                else{
                    answer += Integer.parseInt(temp);
                    temp = "";
                }
            }
            answer += Integer.parseInt(temp);
            answer *= -1;
            System.out.println(firstNum+answer);
        }else{
            for(int i=0;i<str.length();i++){
                if(Character.isDigit(str.charAt(i))){
                    temp += str.charAt(i);
                }
                else{
                    answer += Integer.parseInt(temp);
                    temp = "";
                }
            }
            answer += Integer.parseInt(temp);
            System.out.println(answer);
        }

    }
}
