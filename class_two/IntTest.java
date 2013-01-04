public class IntTest{
	public static void main(String[] args){
		int a = 5;
		int b = 3;
		int c = a + b;
		int d = a - b;
		int e = a * b;
		System.out.println("c = "+c);
		System.out.println("d = "+d);
		System.out.println("e = "+e);
		
//		结果为g = 1
//		因为当有若干个变量参与运算时，结果类型取决于这些类型中表示范围最大的
//		so此结果为int，0.5变为0(把小数部分全省略掉而并非四舍五入)
		int g = a / b;
		System.out.println("g = "+g);
		
//		结果为1.0
//		因为a为int，b为int，所以a/b结果也为int，现只是将int类型的0变为double类型，0 → 0.0
		double h = a / b;
		System.out.println("h = "+h);
		
//		结果为1.6666666666666667
//		强制类型转换，将int类型的a/b结果先转换为double，再赋值给double类型的i
		double i =(double)a / b;
		System.out.println("i = "+i);
		
//		结果为2
//		取余数
		int j = a % b;
		System.out.println("j = "+j);
		
//		结果的符号永远与被除数的符号一致
//		结果为2
		int a2 = 5;
		int b2 = -3;
		int c2 = a2 % b2;
		System.out.println("c2 = "+c2);
		
//		结果为-2
		int a3 = -5;
		int b3 = 3;
		int c3 = a3 % b3;
		System.out.println("c3 = "+c3);
	}
}