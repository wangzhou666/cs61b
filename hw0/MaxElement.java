public class MaxElement {
   public static int max(int[] m) {
   		int temp = m[0];
   		for (int i : m) {
   			if (temp < i){
   				temp = i;	
   			}
   		}
       	return temp;
   }
   public static void main(String[] args) {
      int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};  
      System.out.println(max(numbers));    
   }
}