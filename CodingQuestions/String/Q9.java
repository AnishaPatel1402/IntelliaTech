// 9. String to Integer (atoi)	
// - Input: `"42"`	
// - Output: `42`	

class Test{
    public static int stringToInteger(String str){
        int result = 0;

        for (int i = 0; i < str.length(); i++) {
            int digit = str.charAt(i) - '0';
            result = result * 10 + digit;
        }

        return result;
    }
    public static void main(String[] args) {
        String str = "42";
        System.out.println(stringToInteger(str));
    }
}