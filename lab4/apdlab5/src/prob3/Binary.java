package prob3;

public class Binary extends Thread {
	int id;
	static int cores=4;
	int v[]= { 1, 5, 7, 10, 12, 14, 15, 18, 20, 22, 25, 27, 30, 64, 110, 220 }; ;
	int key=110; 
	boolean found = false; 
	int part = 0; 
	  
	Binary(){}
	
	public void run() {
		
		 int low = (int) ((id *v.length)/4);
		 int high =  (int) (((id+1) *v.length)/4);
		
		 int thread_part = part++; 
		    int mid;
		    
		    while (low < high && !found)  { 
		    	  
		       
		        mid = (high - low) / 2 + low; 
		  
		        if (v[mid] == key)  { 
		            found = true; 
		            System.out.println(v[mid] + " "+ mid);
		            break; 
		        } 
		  
		        else if (v[mid] > key) 
		            high = mid - 1; 
		        else
		            low = mid + 1; 
		    } 
	}
	
	
	 public static void main(String argv[]) throws InterruptedException {
		 
		 Binary[] y=new Binary[cores];
		 for(int i=0;i<cores;i++) {
			 y[i]=new Binary();
			 y[i].id=i;}
			 
		 for(int i=0;i<cores;i++)
			 y[i].start();
		 
		 for(int i=0;i<cores;i++)
			 y[i].join();
		 for(int i=0;i<cores;i++)
		 System.out.println(y[i].found);
		 
		 
		 
	 }

}
