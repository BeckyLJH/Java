public class BooleanTest {
	public static void main(String[] args){
		int a = 20;
		int b = 30;
		boolean x,y,z;
		x = a > b;
		y = a < b;
		z = (a + b) == 50;
//		z = a + b == 50;
//		boolean的结果只有两个true/false
		System.out.println("x = "+x);
		System.out.println("y = "+y);
		System.out.println("z = "+z);
	}
}
