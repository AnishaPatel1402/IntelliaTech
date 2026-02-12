// 19. Container With Most Water		
// - Input: `arr = [1,8,6,2,5,4,8,3,7]`		
// - Output: `49`		

class Test{

    public static int maxWater(int[] arr) {
        int n = arr.length;

        int maxWater = Integer.MIN_VALUE;
        int i = 0, j = n-1;
        while(i<j){
            int water = Math.min(arr[i],arr[j]) * (j-i);
            maxWater = Math.max(maxWater, water);

            if(arr[i] < arr[j]) i++;
            else j--;
        }
       
        return maxWater;
    }
    public static void main(String[] args) {
        int arr[] = {1,8,6,2,5,4,8,3,7};
        System.err.println(maxWater(arr));
    }
}