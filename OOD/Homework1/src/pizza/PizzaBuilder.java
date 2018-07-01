package pizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder implements IPizzaBuilder {
    private List<String> toppings = new ArrayList<String>();
    private String size;

    @Override
    public void addTopping(String topping) {
        toppings.add(topping);
    }

    @Override
    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public Pizza getPizza() {
        return new Pizza(size, toppings);
    }
}
