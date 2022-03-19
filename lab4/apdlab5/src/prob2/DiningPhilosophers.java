package prob2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class DiningPhilosophers {

  static int philosophersNumber = 5;
  static Philosopher philosophers[] = new Philosopher[philosophersNumber];
  static Fork forks[] = new Fork[philosophersNumber];

  static class Fork {

    public Semaphore sem = new Semaphore(1);

    void grab() {
      try {
        sem.acquire();
      }
      catch (Exception e) {
        e.printStackTrace(System.out);
      }
    }

    void release() {
      sem.release();
    }

    boolean isFree() {
      return sem.availablePermits() > 0;
    }

  }

  static class Philosopher extends Thread {

    public int number;
    public Fork leftFork;
    public Fork rightFork;

    Philosopher(int num, Fork left, Fork right) {
      number = num;
      leftFork = left;
      rightFork = right;
    }

    public void run(){
      
      if (number!=0)
	      while (true) {
	        leftFork.grab();
	        System.out.println("Philosopher #" + number + " grabs left fork.");
	        rightFork.grab();
	        System.out.println("Philosopher #" + number + " grabs right fork.");
	        eat();
	        leftFork.release();
	        System.out.println("Philosopher #" + number + " releases left fork.");
	        rightFork.release();
	        System.out.println("Philosopher #" + number + " releases right fork.");
	      }
      else 
    	  while (true) {
    		  	rightFork.grab();
    	        System.out.println("Philosopher #" + number + " grabs right fork.");
    	        leftFork.grab();
    	        System.out.println("Philosopher #" + number + " grabs left fork.");
    	        eat();
    	        rightFork.release();
    	        System.out.println("Philosopher #" + number + " releases right fork.");
    	        leftFork.release();
    	        System.out.println("Philosopher #" + number + " releases left fork.");
    	      }
      
    }

    void eat() {
      try {
        int sleepTime = 100;
        System.out.println("Philosopher #" + number+ " eats for " + sleepTime);
        Thread.sleep(sleepTime);
      }
      catch (Exception e) {
        e.printStackTrace(System.out);
      }
    }

  }

  public static void main(String argv[]) {
   

    for (int i = 0; i < philosophersNumber; i++) {
      forks[i] = new Fork();
    }

    for (int i = 0; i < philosophersNumber; i++) {
      philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % philosophersNumber]);
      philosophers[i].start();
    }

    while (true) {
      try {
    	
    	boolean deadlock = true;
        for (Fork f : forks) {
          if (f.isFree()) {
            deadlock = false;
            break;
          }
        }
        if (deadlock) {
          Thread.sleep(1000);
          System.out.println(" deadlock!");
          break;
        }
      }
      catch (Exception e) {
        e.printStackTrace(System.out);
      }
    }

    System.out.println("Done!");
   
  }

}