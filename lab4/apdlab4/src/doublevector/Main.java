package doublevector;

import apdlab4_ex3.MyThread;

/**
 * @author cristian.chilipirea
 *
 */
public class Main extends Thread{
	//start end
	int id;
	static int v[];
	static int  N = 100000013;
	static int cores = Runtime.getRuntime().availableProcessors();
	public void run() {
		 int start = (int) (id * (double)N / cores);
		 int end = Math.min((id + 1) * (int)(double)N / cores, N);
		 for (int i = start; i < end; i++) 
				v[i] = v[i] * 2;
		
	}
	Main(int i){
		this.id=i;
	}

	public static void main(String[] args) {
		
		v = new int[N];
		Thread threads[] = new Thread[cores];
		for(int i=0;i<N;i++)
			v[i]=i;
		
		for (int i = 0; i < cores; i++)
			threads[i] = new Thread(new Main(i));
		for (int i = 0; i < cores; i++)
			threads[i].start();
		for (int i = 0; i < cores; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Parallelize me
		/*
		for (int i = 0; i < N; i++) {
			v[i] = v[i] * 2;
		}*/

		for (int i = 0; i < N; i++) {
			//System.out.println(v[i]);
			if(v[i]!= i*2) {
				System.out.println("Wrong answer");
				System.exit(1);
			}
		}
		System.out.println("Correct");
	}

}
