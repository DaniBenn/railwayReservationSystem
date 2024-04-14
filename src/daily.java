import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class daily {
    public String  minRemoveToValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> st = new HashSet<>();

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '(') {
                stack.push(i);
            } else if(ch ==')'){
                if(!stack.isEmpty()) {
                    stack.pop();
                } else {
                    st.add(i);
                }
            }
        }
        while(!stack.isEmpty()) {
            st.add(stack.pop());
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <s.length(); i++) {
            if (!st.contains(i)){
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

    public void moveZeros(int[] nums) {
        int count = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0) {
                int temp = nums[count];
                nums[count] = nums[i];
                nums[i] = temp;
                count++;
            }
        }
    }

    public int singleNumber(int[] nums) {
        int number = 0;

        for(int num : nums){
            number ^= num;
        }
        return number;
    }
}
