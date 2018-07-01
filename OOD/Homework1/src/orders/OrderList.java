package orders;

import findOrderStrategy.IFindOrderStrategy;

import java.util.List;

class OrderList {
    private List<Order> orders;

    OrderList(List<Order> orders) {
        this.orders = orders;
    }

    public Order findOrder(String searchTerm, IFindOrderStrategy findOrderStrategy){
        for (Order order : orders) {
            String searchAttribute = findOrderStrategy.getFindByAttribute(order);
            if(searchAttribute.equals(searchTerm))
                return order;
        }
        return null;
    }
}
