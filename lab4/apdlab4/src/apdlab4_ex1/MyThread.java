package apdlab4_ex1;
/**
 * @author cristian.chilipirea
 * 
 *         Why is value set correct even though we do not use
 *         locks/synchronized?
 *         DO NOT MODIFY
 */
public class MyThread implements Runnable {
	static int value = 0;

	@Override
	public void run() {
		for (int i = 0; i < Main.N; i++)
			
			synchronized(this) {
				value+=3;
			}
		//value=value + 3;
	}
	
	public synchronized int run1() {
		value=value + 3;
		//System.out.println(value);
		return value;
	}
}