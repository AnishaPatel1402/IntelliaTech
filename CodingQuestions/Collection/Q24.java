// 24. Merge Two Sorted Lists		
// - Input: `l1 = [1, 2, 4], l2 = [1, 3, 4]`		
// - Output: `[1, 1, 2, 3, 4, 4]`		

import java.util.*;

class Test {

    public static ArrayList<Integer> mergeLists(ArrayList<Integer> l1, ArrayList<Integer> l2) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < l1.size() && j < l2.size()) {

            if (l1.get(i) <= l2.get(j)) {
                result.add(l1.get(i));
                i++;
            } else {
                result.add(l2.get(j));
                j++;
            }
        }

       
        while (i < l1.size()) {
            result.add(l1.get(i));
            i++;
        }

        while (j < l2.size()) {
            result.add(l2.get(j));
            j++;
        }

        return result;
    }

    public static void main(String[] args) {

        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        l1.add(4);

        ArrayList<Integer> l2 = new ArrayList<>();
        l2.add(1);
        l2.add(3);
        l2.add(4);

        ArrayList<Integer> merged = mergeLists(l1, l2);

        System.out.println("Merged List= " + merged);
    }
}
