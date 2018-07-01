package findOrderStrategy;

import orders.Order;

public class FindByLastNameStrategy implements IFindOrderStrategy {
    @Override
    public String getFindByAttribute(Order order) {
        return order.getLastName();
    }
}
