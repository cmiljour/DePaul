package pizza;

public interface IPizzaBuilder {
    void addTopping(String topping);
    void setSize(String size);
    Pizza getPizza();
}
