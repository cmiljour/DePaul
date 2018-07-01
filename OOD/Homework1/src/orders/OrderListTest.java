package orders;

import findOrderStrategy.FindOrderStrategyFactory;
import findOrderStrategy.IFindOrderStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderListTest {
    private OrderList orderList;

    @Before
    public void setupOrdersForSearching(){
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(new ArrayList<>(), "Jeffrey", "Sharpe", "1234567", "2980734"));
        orders.add(new Order(new ArrayList<>(), "Bob", "Dylan", "8923749", "9084823"));
        orders.add(new Order(new ArrayList<>(), "Paul", "McCartney", "4567423", "9812932"));
        orders.add(new Order(new ArrayList<>(), "Michael", "Jordan", "6423432", "8478234"));
        orders.add(new Order(new ArrayList<>(), "Scottie", "Pippin", "9283798", "2343465"));

        orderList = new OrderList(orders);
    }

    @Test
    public void testFindOrderByRewardsNumber(){
        // Arrange
        IFindOrderStrategy findStrategy = FindOrderStrategyFactory.getFindByRewardsNumberStrategy();

        // Act
        Order foundOrder = orderList.findOrder("9812932", findStrategy);

        // Assert
        assertEquals("Paul", foundOrder.firstName);
        assertEquals("McCartney", foundOrder.lastName);
        assertEquals("4567423", foundOrder.orderNumber);
        assertEquals("9812932", foundOrder.rewardsMemberNumber);
    }

    @Test
    public void testFindOrderByLastName(){
        // Arrange
        IFindOrderStrategy findStrategy = FindOrderStrategyFactory.getFindByLastNameStrategy();

        // Act
        Order foundOrder = orderList.findOrder("Jordan", findStrategy);

        // Assert
        assertEquals("Michael", foundOrder.firstName);
        assertEquals("Jordan", foundOrder.lastName);
        assertEquals("6423432", foundOrder.orderNumber);
        assertEquals("8478234", foundOrder.rewardsMemberNumber);
    }

    @Test
    public void testFindOrderByOrderNumber(){
        // Arrange
        IFindOrderStrategy findStrategy = FindOrderStrategyFactory.getFindByOrderNumberStrategy();

        // Act
        Order foundOrder = orderList.findOrder("1234567", findStrategy);

        // Assert
        assertEquals("Jeffrey", foundOrder.firstName);
        assertEquals("Sharpe", foundOrder.lastName);
        assertEquals("1234567", foundOrder.orderNumber);
        assertEquals("2980734", foundOrder.rewardsMemberNumber);
    }
}
