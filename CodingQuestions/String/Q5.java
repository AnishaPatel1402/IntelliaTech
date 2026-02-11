// 5. First Non-Repeating Character																									
// - Input: `"leetcode"`																									
// - Output: `"l"`		

class Test{

    public static char nonRepeatingChar(String str){
        boolean isVisited[] = new boolean[26];
        for(int i=0; i<str.length(); i++){
            boolean flag = false;
            if(isVisited[str.charAt(i)-'a']) continue;
            for(int j=i+1; j<str.length(); j++){
                if(str.charAt(i)==str.charAt(j)){
                    flag = true;
                    isVisited[str.charAt(i)-'a'] = true;
                    break;
                }
            }
            if(flag == false){
                return str.charAt(i);
            }
        }
        return '@';
    }

    public static void main(String[] args) {
        String str = "leetcode";
        System.out.println("first non repeating character is : "+ nonRepeatingChar(str));
    }
}