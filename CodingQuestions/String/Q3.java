import java.util.Arrays;

// 3. Check Anagram	
// - Input: `"listen", "silent"`	
// - Output: `true`	

class Test{

    public static boolean anagramOrNot(String s1, String s2){
        if(s1.length()!=s2.length()) return false;
        if(s1.equals(s2)) return true;
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        char[] charArray1 = s1.toCharArray();
        char[] charArray2 = s2.toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        return Arrays.equals(charArray1, charArray2);
    }
    public static void main(String[] args) {
        String s1 = "listen";
        String s2 = "silent";

        System.out.println(anagramOrNot(s1,s2));
    }
}