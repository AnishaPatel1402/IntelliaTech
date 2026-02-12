// 23. Detect Cycle in a Linked List			
// - Input: `head = [3, 2, 0, -4]` (with a cycle starting at index 1)			
// - Output: `true`			


import java.util.*;

class Test {

    public static boolean hasCycle(LinkedList<Integer> list) {
        HashSet<Integer> set = new HashSet<>();

        for (Integer value : list) {
            if (set.contains(value)) {
                return true;  
            }
            set.add(value);
        }

        return false;
    }

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(20);  

        if (hasCycle(list)) System.out.println("List has cycle");
        else System.out.println("No cycle foundin list");
        
    }
}
