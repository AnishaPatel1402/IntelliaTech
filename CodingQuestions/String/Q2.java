// 2. Longest Palindromic Substring	
// - Input: `"babad"`	
// - Output: `"bab"` (or `"aba"`)	

class Test{
    public static void main(String[] args) {
        String s = "babad";
        
        int maxPallinLen = 0;
        String maxPallinStr = null;

        for(int i=0; i<s.length(); i++){
            for(int j = i; j<s.length(); j++){
                int len = j-i+1;
                String str = s.substring(i,j+1);
                if(isPallindrome(str)){
                    if(maxPallinLen < len){
                        maxPallinLen = len;
                        maxPallinStr = str;
                    }
                }
            }
        }

        System.err.println("max pallindrome string : "+ maxPallinStr);
    }

    public static boolean isPallindrome(String s){
        String reverseString = new StringBuilder(s).reverse().toString();
        return reverseString.equals(s);
    }
}