// 16. Product of Array Except Self		
// - Input: `nums = [1, 2, 3, 4]`		
// - Output: `[24, 12, 8, 6]`		

class Test{

    public static int[] productOfArray(int arr[]){
        
        int n = arr.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {

            int product = 1;

            for (int j = 0; j < n; j++) {

                if (i != j) {
                    product *= arr[j];
                }
            }

            result[i] = product;
        }

        return result;
    }
    public static void main(String[] args) {
        int arr[] = {1,2,3,4};
        int ans[] = productOfArray(arr);
        for(int i=0; i<ans.length; i++){
            System.out.print(ans[i]+" ");
        }
    }
}