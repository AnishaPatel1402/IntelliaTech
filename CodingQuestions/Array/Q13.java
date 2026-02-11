// 13. Find the Missing Number		
// - Input: `nums = [3, 0, 1]`		
// - Output: `2`		
class Test{
    public static void main(String[] args) {
        int[] arr = {3,0,1};

        int n = arr.length;
        
        int sum  = (n*(n+1))/2;   
		
		int sum2=0;
		for(int i=0; i<n; i++) {
			sum2 += arr[i];
		}
		
        System.out.println("mising number is: "+  (sum-sum2));
    }
}