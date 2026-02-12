// 25. Find Intersection of Two Linked Lists			
// - Input: `headA = [4, 1, 8, 4, 5], headB = [5, 6, 1, 8, 4, 5]`			
// - Output: `8` (the intersecting node value)			

import java.util.*;

class Test {

    public static void printIntersection(LinkedList<Integer> l1, LinkedList<Integer> l2) {

        HashSet<Integer> set = new HashSet<>(l1);

        for (Integer val : l2) {
            if (set.contains(val)) {
                System.out.print(val + " ");
                set.remove(val);   
            }
        }
    }

    public static void main(String[] args) {

        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(4);
        list1.add(1);
        list1.add(8);
        list1.add(4);
        list1.add(5);

        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(5);
        list2.add(6);
        list2.add(1);
        list2.add(8);
        list2.add(4);
        list2.add(5);

        System.out.print("Intersection: ");
        printIntersection(list1, list2);
    }
}
