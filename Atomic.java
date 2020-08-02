
package cw3part1;

/**
 *
 * @author Colin Smith H00323113
 */

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {
    
    final AtomicInteger count = new AtomicInteger(10);
    final AtomicBoolean check = new AtomicBoolean(false);
    final private int capacity = 100;
    final private Object buffer[ ] = new Object [capacity];
    private int out = 0, in = 0;
    
    public void put(Object item) throws InterruptedException {
        
        if (check.compareAndSet(false, true)){
            
            if (count.compareAndSet(capacity, capacity)){
                System.out.println("Waiting, put(): "+ Thread.currentThread().getName());
            }
            buffer[in] = item; 
            in = (in + 1) % capacity;
            count.getAndIncrement();
        }
    }
    
    public Object take() throws InterruptedException {
        Thread.sleep(1);
        Object item = new Object();
        if (check.compareAndSet(true, false)){
            if (count.compareAndSet(0, 0)){
                System.out.println("Waiting, take(): "+ Thread.currentThread().getName());
            }
        
            item = buffer[out];
            out = (out + 1) % capacity;
            count.getAndDecrement();
        }
        System.out.println("Shared data: " + item);
        
        return item;        
    } 
}
