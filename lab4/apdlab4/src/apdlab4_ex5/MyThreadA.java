package apdlab4_ex5;
/**
 * @author cristian.chilipirea
 * 
 *         Solve the dead-lock
 */
public class MyThreadA implements Runnable {

	@Override
	public void run() {
		synchronized (Main.lockA) {
			//System.out.println("block1");
			for (int i = 0; i < Main.N; i++)
				Main.valueA++;
			//System.out.println("block2");
			synchronized (Main.lockA) {
			//	System.out.println("block2");
				for (int i = 0; i < Main.N; i++)
					Main.valueB++;
			}
		}
	}
}
