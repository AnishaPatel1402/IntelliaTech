// 4. String Compression	
// - Input: `"aabcccccaaa"`	
// - Output: `"a2b1c5a3"`	

class Test{
    public static String stringCompression(String str){
        char[] charArray = str.toCharArray();

        StringBuilder sb = new StringBuilder();
        int count = 1;
        for(int i=0; i<charArray.length-1; i++){
            if(str.charAt(i)==str.charAt(i+1)) count++;
            else{
                sb.append(str.charAt(i));
                sb.append(count);
                count = 1;
            }
        }
        sb.append(str.charAt(charArray.length-1));
        sb.append(count);
        return sb.toString();
    }
    public static void main(String[] args) {
        String str = "aabcccccaaa";

        System.err.println("Compressed String : "+ stringCompression(str));
    }
}