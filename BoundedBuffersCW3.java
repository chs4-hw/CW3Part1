package cw3part1;
/**
 *
 * @author Colin Smith H00323113
 */

import java.util.logging.Level;
import java.util.logging.Logger;

public class BoundedBuffersCW3
{   
    public static void main(String[] args)throws InterruptedException 
    {
        final IntrinsicMonitor intrinsicMonitor = new IntrinsicMonitor();
        final Atomic atomic = new Atomic();
        ObjCreation item = new ObjCreation("object");

        final Runnable producer;
        producer = () -> {
            while (true) {
                try {
//                    intrinsicMonitor.put(item);
                    atomic.put(item);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(BoundedBuffersCW3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        for (int i = 0; i < 1; i++){
            new Thread (producer).start();
        }
        
        final Runnable consumer;
        consumer = () -> {
            while (true) {
                try {
//                    intrinsicMonitor.take();
                    atomic.take();
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(BoundedBuffersCW3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        for (int i = 0; i < 1; i++){
            new Thread (consumer).start();
        }
    }   
}
