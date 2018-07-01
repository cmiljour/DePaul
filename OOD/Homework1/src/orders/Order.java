package orders;

import pizza.Pizza;

import java.util.List;

public class Order {
    final List<Pizza> pizzas;
    final String firstName;
    final String lastName;
    final String orderNumber;
    final String rewardsMemberNumber;

    Order(List<Pizza> pizzas, String firstName, String lastName, String orderNumber, String rewardsMemberNumber){
        this.pizzas = pizzas;
        this.firstName = firstName;
        this.lastName = lastName;
        this.orderNumber = orderNumber;
        this.rewardsMemberNumber = rewardsMemberNumber;
    }

    public String getOrderNumber(){
        return orderNumber;
    }

    public String getRewardsMemberNumber(){
        return rewardsMemberNumber;
    }

    public String getLastName(){
        return lastName;
    }
}
