package findOrderStrategy;

import orders.Order;

public interface IFindOrderStrategy {
    String getFindByAttribute(Order order);
}
