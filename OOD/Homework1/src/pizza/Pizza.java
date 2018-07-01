package pizza;

import java.util.List;

public class Pizza {
    private String size;
    private final List<String> toppings;

    public Pizza(String size, List<String> toppings){
        this.size = size;
        this.toppings = toppings;
    }

    public String toString(){
        StringBuilder toppingsBuilder = new StringBuilder();
        toppingsBuilder.append(size + " pizza\n");
        for (String topping :
                toppings) {
            toppingsBuilder.append("\t" + topping + "\n");
        }
        return toppingsBuilder.toString();
    }
}
