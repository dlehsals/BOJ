import java.util.*;

class Solution {
    static Stack<Character> s;
    public String solution(String p) {  //문자열 p를 u와 v로 분리(균형잡힌 괄호 문자열)
        String answer = "";
        s = new Stack<>();
        if("".equals(p)) answer = "";

        answer = recur(p);

        return answer;
    }

    public static String recur(String p){  //균형잡힌 문자열을 제외한 나머지 문자열을 반환한다.
        String u = "";
        String v = "";
        int left = 0;
        int right = 0;
        String tmp = "";

        if(p.length() == 0) return "";

        for(int i=0;i<p.length();i++){
            char index = p.charAt(i);
            if(left > 0 && right > 0 && Math.abs(left-right) == 0) break;
            if(index == '(') left++;
            else right++;
            u += index;
            v = p.substring(i+1, p.length());
        }

        System.out.println("u : " + u);
        System.out.println("v : " + v);

        if(checkString(u)){         //올바른 괄호 문자열이라면?
            tmp += u + recur(v);
            v = tmp;
        }else{
            u = u.substring(1, u.length()-1);       //앞뒤 문자를 제거하고
            System.out.println("앞뒤문자 제거 : " + u);
            tmp += "(" + recur(v) + ")";
            u = u.replace(")", "1");    //괄호들을 변환해준 다음에
            u = u.replace("(", "2");
            u = u.replace("1", "(");
            u = u.replace("2", ")");
            System.out.println("치환 후 : " + u);
            tmp += u;
            v = tmp;
        }

        return v;
    }

    public static boolean checkString(String str){  // 올바른 괄호 문자열인지 체크한다
        if(str.charAt(0) == ')'){
            return false;
        }

        for(int i=0;i<str.length();i++){
            if(!s.isEmpty()){           //스택이 비어있지 않으면 비교를 한다
                if(s.peek() == str.charAt(i)){  //최근 들어온 문자랑 새로 들어올 문자가 같다면
                    s.push(str.charAt(i));
                }
                else
                    s.pop();
            }
            else
                s.push(str.charAt(i));
        }
        if(s.size() == 0) return true;

        return false;
    }
}
