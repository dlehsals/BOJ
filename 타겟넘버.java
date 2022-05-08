public class 타겟넘버 {
    static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;

        dfs(numbers, 0, 0, target);
        return answer;
    }

    public static void dfs(int[] numbers, int index, int depth, int target){
        if(depth == numbers.length){
            if(index == target) answer++;
            return;
        }else{
            dfs(numbers, index+numbers[depth], depth + 1, target);
            dfs(numbers, index-numbers[depth], depth + 1, target);
        }
    }
}
