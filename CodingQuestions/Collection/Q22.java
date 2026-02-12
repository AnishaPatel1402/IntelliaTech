
import java.util.Collections;
import java.util.LinkedList;

// 22. Reverse a Linked List		
// - Input: `head = [1, 2, 3, 4, 5]`		
// - Output: `[5, 4, 3, 2, 1]`		

class Test{
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Collections.reverse(list);

        System.out.println(list);
    }
}