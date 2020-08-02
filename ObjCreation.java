package cw3part1;

/**
 *
 * @author Colin Smith H00323113
 */

public class ObjCreation {
    
    private String item;

    public ObjCreation(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return item;
    }
    
    
}
