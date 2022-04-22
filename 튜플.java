import java.util.*;

class 튜플 {
    public List<Integer> solution(String s) {
        List<Integer> tuple = new ArrayList<>();
        String tmp = s.substring(2, s.length()-2).replace("},{", "-");
        String[] str = tmp.split("-");

        Arrays.sort(str, (a, b)->a.length()-b.length());

        for(String ss : str){
            String[] temp = ss.split(",");
            for(int i=0;i<temp.length;i++){
                int num = Integer.parseInt(temp[i]);
                if(!tuple.contains(num))
                    tuple.add(num);
            }
        }
        return tuple;
    }
}