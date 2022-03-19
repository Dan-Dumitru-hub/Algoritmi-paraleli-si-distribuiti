package apdlab4_ex0;



public class Main extends Thread{
	int id;
	public void run() {
		//String threadId = Thread.currentThread().getName();
		System.out.println("Hello from "+id);
	}
	Main(int id){
		this.id=id;
	}

	public static void main(String[] args) {
		int cores = Runtime.getRuntime().availableProcessors();
		Thread threads[] = new Thread[cores];
		
		
		
			
			for (int i = 0; i < cores; i++)
				threads[i] = new Thread(new Main(i));
			
			for (int i = 0; i < cores; i++)
				threads[i].run();
			for (int i = 0; i < cores; i++) {
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		
		
	}
}