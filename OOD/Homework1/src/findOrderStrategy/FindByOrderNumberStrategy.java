package findOrderStrategy;

import orders.Order;

public class FindByOrderNumberStrategy implements IFindOrderStrategy {
    @Override
    public String getFindByAttribute(Order order) {
        return order.getOrderNumber();
    }
}
