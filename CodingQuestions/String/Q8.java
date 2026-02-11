// 8. String Rotation	
// - Input: `"waterbottle", "erbottlewat"`	
// - Output: `true`	

class Test{
    public static boolean stringRotation(String s, String rotate){
        if(s.length()!=rotate.length()) return false;
       
        for (int i = 0; i < s.length(); i++) {
            s = s.substring(1) + s.charAt(0);
            if (s.equals(rotate)) {
                return true;
            }
        }

        return false;
    }
    public static void main(String[] args) {
        String str = "waterbottle";
        String rotate = "erbottlewat";
        System.out.println(stringRotation(str, rotate));
    }
}