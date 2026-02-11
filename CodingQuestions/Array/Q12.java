// 12. Rotate Array		
// - Input: `nums = [1, 2, 3, 4, 5, 6, 7], k = 3`		
// - Output: `[5, 6, 7, 1, 2, 3, 4]`		

class Test{
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        int n = 7;
        int k = 3;
        k = k % n;

        //1. reverse from 0 to n-k-1
       int i = 0 , j = n-k-1;
       while(i<j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        i++; j--;
       }

       //2. reverse from n-k to n-1
       i = n-k; j = n-1;
       while(i<j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        i++; j--;
       }


       //3. reverse from 0 to n-1
       i = 0; j = n-1;
       while(i<j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        i++; j--;
       }

       for(i=0; i<n; i++){
        System.err.print(arr[i]+ " ");
       }
    }
}