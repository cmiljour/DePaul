package findOrderStrategy;

import orders.Order;

public class FindByRewardsNumberStrategy implements IFindOrderStrategy {
    @Override
    public String getFindByAttribute(Order order) {
        return order.getRewardsMemberNumber();
    }
}
