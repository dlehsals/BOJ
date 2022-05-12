import java.sql.SQLOutput;
import java.util.*;

class 메뉴리뉴얼 {
    static boolean[] visited;
    static Map<String, Integer> map;
    static int max;
    public static List<String> solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        map = new HashMap<>();
        for(int i=0;i<course.length;i++){
            int count = course[i];          //코스의 단품 개수(2, 3, 4...)
            max = Integer.MIN_VALUE;
            for(int j=0;j<orders.length;j++){
                char[] order = orders[j].toCharArray();       //이걸 char배열로 바꿔야함
                char[] tmp = new char[count];
                Arrays.sort(order);
                visited = new boolean[26];
                makeCombination(order, tmp, 0, 0, count);
            }
            for(Map.Entry<String, Integer> entrySet : map.entrySet()){
                if(max <= entrySet.getValue()){
                    max = entrySet.getValue();
                }
            }

            for(Map.Entry<String, Integer> entrySet : map.entrySet()){
                if(entrySet.getValue() == max && max >= 2)
                    answer.add(entrySet.getKey());
            }

            map.clear();
        }
        Collections.sort(answer);
        return answer;
    }

    public static void makeCombination(char[] order, char[] tmp, int start,int depth, int count){
        if(depth == count){
            String s = "";
            Arrays.sort(tmp);

            for(int i=0;i<count;i++){
                s+=tmp[i];
            }
            if(map.containsKey(s)){
                map.put(s, map.get(s)+1);
            }
            else
                map.put(s, 1);

            return;
        }

        for(int i=start;i<order.length;i++){
            int index = order[i]-65;
            char c = order[i];
            if(!visited[index]){
                visited[index] = true;
                tmp[depth] = c;
                makeCombination(order, tmp, i+1, depth+1, count);
                visited[index] = false;
            }
        }
    }

    public static void main(String[] args) {
        String[] orders = new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = new int[]{2, 3, 4};
        List<String> list = solution(orders, course);

        for(String s : list)
            System.out.println(s+" ");


    }
}