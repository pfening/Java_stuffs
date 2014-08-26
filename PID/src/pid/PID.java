package pid;



public class PID {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        System.out.println("hello Gabor");

        Thread.sleep(10);
        
        long stopTime = System.currentTimeMillis();

        System.out.println("Elapsed time was " + (stopTime - startTime) + " miliseconds.");
    }
    
}
