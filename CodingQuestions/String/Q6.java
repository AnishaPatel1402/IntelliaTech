
// - Input: `"abc"`			
// - Output: `["abc", "acb", "bac", "bca", "cab", "cba"]`			

class Test {

    public static void permutations(String str, int start, int end) {

        if (start == end) {
            System.out.println(str);
            return;
        }

        for (int i = start; i <= end; i++) {

            str = swap(str, start, i);

            permutations(str, start + 1, end);

            str = swap(str, start, i); // backtrack
        }
    }

    public static String swap(String str, int i, int j) {

        char[] ch = str.toCharArray();

        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;

        return String.valueOf(ch);
    }

    public static void main(String[] args) {

        String str = "abc";

        permutations(str, 0, str.length() - 1);
    }
}
