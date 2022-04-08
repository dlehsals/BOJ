import java.util.*;

class Solution {
    static private String result;
    static private int answer;
    static private boolean[] visited;
    static private String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};
    static private String[] copy;
    public int solution(int n, String[] data) { // n : 조건의 개수, data[] : 조건

        answer = 0;
        result = "";
        visited = new boolean[8];
        copy = new String[data.length];
        for(int i=0;i<data.length;i++){
            copy[i] = data[i];
        }

        dfs("", 0);
        return answer;
    }

    public static void dfs(String str, int depth){
        if(depth == 8){
            if(check(str)) {
                answer++;
            }
            return;
        }
        for(int i=0;i<8;i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(str+friends[i], depth+1);
                visited[i] = false;
            }
        }
    }

    public static boolean check(String str){
        //System.out.println("str : " + str);
        for(String s : copy){
            char a = s.charAt(0);
            char b = s.charAt(2);
            char op = s.charAt(3);
            int diff = s.charAt(4)-'0';     //조건상의 거리
            int dist = Math.abs(str.indexOf(String.valueOf(a))-str.indexOf(String.valueOf(b)))-1; //실제 거리
            //System.out.println("dist : " + dist);
            if(op == '<'){
                if(!(dist < diff)) return false;
            }
            else if(op == '>'){
                if(!(dist > diff)) return false;
            }
            else{
                if((dist != diff)) return false;
            }
        }

        return true;
    }
}