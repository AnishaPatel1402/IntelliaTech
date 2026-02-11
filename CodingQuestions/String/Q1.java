	
// 1. Reverse Words in a String	
// - Input: `"Hello World"`	
// - Output: `"olleH dlroW"`	

class Test{

    public static String reverseString(String s){
        StringBuilder sb = new StringBuilder();
        for(int i=s.length()-1; i>=0; i--){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String str = "Hello World";
        String[] strArray = str.split(" ");

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<strArray.length; i++){
            sb.append(reverseString(strArray[i]));
            if(i!=strArray.length-1) sb.append(" ");
        }

        System.out.println("String : "+ str);
        System.out.println("Reverse string : "+ sb.toString());
    }
}