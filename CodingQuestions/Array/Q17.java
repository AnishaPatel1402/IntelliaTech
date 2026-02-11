
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 17. Find Duplicates in an Array		
// - Input: `nums = [4,3,2,7,8,2,3,1]`		
// - Output: `[2, 3]`		

class Test{
    public static List<Integer> findDuplicates(int[] nums) {

        Set<Integer> set = new HashSet<>();
        List<Integer> duplicates = new ArrayList<>();

        for (int num : nums) {

            if (set.contains(num)) {
                duplicates.add(num);
            } else {
                set.add(num);
            }
        }

        return duplicates;
    }
    public static void main(String[] args) {
        int arr[] = {4,3,2,7,8,2,3,1};
        List<Integer> dublicates = findDuplicates(arr);
        for(int i=0; i<dublicates.size(); i++){
            System.out.print(dublicates.get(i)+" ");
        }
    }
}