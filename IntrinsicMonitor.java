
package cw3part1;

/**
 *
 * @author Colin Smith H00323113
 */

public class IntrinsicMonitor 
{
    final private int capacity = 100;
    final private Object buffer[ ] = new Object [capacity];
    private int out = 0, in = 0;
    private int count = 0;
    
    public void put(Object item) throws InterruptedException {
        synchronized(this) 
            {
                while (count == capacity){
//                    System.out.println("Waiting, put(): "+ Thread.currentThread().getName());
                    wait();
                }
                buffer[in] = item;
                in = (in + 1) % capacity;
                count++;
                notifyAll();  
            }
         
    }
    public Object take() throws InterruptedException {
        Object item = new Object();
        synchronized(this)
            {
                while (count == 0){
//                    System.out.println("Waiting, take(): "+ Thread.currentThread().getName());
                    wait();
                }
                
                item = buffer[out];              
                out = (out + 1) % capacity;
                count--;
                
                notifyAll(); 
            }
        System.out.println("Shared data: " + item);
        return item;       
    }
}
