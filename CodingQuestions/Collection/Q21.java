
// - Input: `head = [1, 1, 2, 3, 3]`		
// - Output: `[1, 2, 3]`

import java.util.*;

class Test{

    public static List<Integer> removeDuplicates(List<Integer> list) {

        if (list.size() == 0) {
            return list;
        }

        List<Integer> result = new ArrayList<>();
        result.add(list.get(0));

        for (int i = 1; i < list.size(); i++) {

            if (!list.get(i).equals(list.get(i - 1))) {
                result.add(list.get(i));
            }
        }

        return result;
    }

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 1, 2, 3, 3);

        List<Integer> result = removeDuplicates(list);

        System.out.println(result);
    }
}

                                                