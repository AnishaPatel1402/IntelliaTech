// 10. Longest Common Prefix		
// - Input: `["flower", "flow", "flight"]`		
// - Output: `"fl"`		

class Test{
    public static String lcp(String[] strs){
        if (strs == null || strs.length == 0) {
            return "";
        }

        String result = "";

        for (int i = 0; i < strs[0].length(); i++) {

            char currentChar = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j++) {

                if (i >= strs[j].length() || strs[j].charAt(i) != currentChar) {
                    return result;
                }
            }

            result += currentChar;
        }

        return result;
    }
    public static void main(String[] args) {
        String[] arr = {"flower", "flow", "flight"};
        System.out.println("Answer : "+ lcp(arr));
    }
}