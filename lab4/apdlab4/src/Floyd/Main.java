package Floyd;
/**
 * @author cristian.chilipirea
 *
 */
public class Main extends Thread{
	int id;
	static int M = 9;
	static int cores = Runtime.getRuntime().availableProcessors();
	static int graph[][] = { { 0, 1, M, M, M }, 
	          { 1, 0, 1, M, M }, 
	          { M, 1, 0, 1, 1 }, 
	          { M, M, 1, 0, M },
	          { M, M, 1, M, 0 } };
	
	public void run() {
		 int start = (int) (id * 5 / cores);
		 int end = Math.min((id + 1) * (int)5 / cores, 5);
		 for (int k = 0; k < 5; k++) {
				for (int i = start; i < end; i++) {
					for (int j = 0; j < 5; j++) {
						graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);}}}
		
	}
	Main(int i){
		this.id=i;
	}

	public static void main(String[] args) {
		
		
		
		// Parallelize me (You might want to keep the original code in order to compare)
	
		int graph1[][] = { { 0, 1, M, M, M }, 
		          { 1, 0, 1, M, M }, 
		          { M, 1, 0, 1, 1 }, 
		          { M, M, 1, 0, M },
		          { M, M, 1, M, 0 } };
		for (int k = 0; k < 5; k++) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					graph1[i][j] = Math.min(graph1[i][k] + graph1[k][j], graph1[i][j]);
				}
			}
			
		}
		for (int i1 = 0; i1 < 5; i1++) {
			for (int j1 = 0; j1 < 5; j1++) {
				System.out.print(graph1[i1][j1] + " ");}
			System.out.println();
			}
		System.out.println();
		
		
		Thread threads[] = new Thread[cores];
		
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

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}
}
