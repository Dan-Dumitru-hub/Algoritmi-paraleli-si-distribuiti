package apdlab4_ex5;
/**
 * @author cristian.chilipirea
 * 
 *         Solve the dead-lock
 */
public class MyThreadB implements Runnable {

	@Override
	public void run() {
		synchronized (Main.lockB) {
		//	System.out.println("block3");
			for (int i = 0; i < Main.N; i++)
				Main.valueB++;
		//	System.out.println("block4");
			synchronized (Main.lockB) {
			//	System.out.println("block4");
				for (int i = 0; i < Main.N; i++)
					Main.valueA++;
			}
		}
	}
}
