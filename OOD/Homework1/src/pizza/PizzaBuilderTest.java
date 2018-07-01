package pizza;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PizzaBuilderTest {
    @Test
    public void testPizzaOutputFormat(){
        // Arrange
        PizzaBuilder builder = new PizzaBuilder();
        builder.addTopping("Mushrooms");
        builder.addTopping("Pepperoni");
        builder.addTopping("Sausage");
        builder.addTopping("Green Peppers");
        builder.addTopping("Giardiniera");
        builder.setSize("Small");
        Pizza pizza = builder.getPizza();

        // Act
        String pizzaString = pizza.toString();

        // Assert
        assertEquals("Small pizza\n\tMushrooms\n\tPepperoni\n\tSausage\n\tGreen Peppers\n\tGiardiniera\n",
                pizzaString);
    }
}
