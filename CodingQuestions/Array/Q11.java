
import java.util.HashMap;

class Test{
    public static int[] twoSum(int[] nums, int target) {
        int ans[] = new int[2];

        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(hm.containsKey(target-nums[i])) {
                ans[0] = i;
                ans[1] = hm.get(target-nums[i]);
                return ans;
            }
            hm.put(nums[i],i);
        }

        return ans;
    }
    public static void main(String[] args) {
        int arr[] = {2,7,11,15};
        int target = 9;
        int ans[] = twoSum(arr,target);
        System.err.println(ans[0] + "  "+ ans[1]);
    }
}