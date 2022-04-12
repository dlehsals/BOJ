import java.util.*;

class 오픈채팅방 {
    static HashMap<String, String> hm;
    static List<String> list;
    public String[] solution(String[] record) {
        hm = new HashMap<>();
        list = new ArrayList<>();

        for(int i=0;i<record.length;i++){
            String[] tmp = record[i].split(" ");
            //tmp[0] : Enter, Leave, Change
            //tmp[1] : 아이디
            //tmp[2] : 닉네임
            if(tmp[0].equals("Enter")){
                list.add(tmp[1]+"님이 들어왔습니다.");
                hm.put(tmp[1], tmp[2]);
            }else if(tmp[0].equals("Leave")){
                list.add(tmp[1]+"님이 나갔습니다.");
            }else if(tmp[0].equals("Change")){
                hm.replace(tmp[1], tmp[2]);
            }
        }

        String[] answer = new String[list.size()];
        for(int i=0;i<list.size();i++){
            int index = list.get(i).indexOf("님");
            String name = list.get(i).substring(0, index);
            answer[i] = hm.get(name)+list.get(i).substring(index, list.get(i).length());
        }

        return answer;
    }
}