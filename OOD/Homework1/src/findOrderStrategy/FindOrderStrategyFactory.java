package findOrderStrategy;

public class FindOrderStrategyFactory {
    public static IFindOrderStrategy getFindByRewardsNumberStrategy() {
        return new FindByRewardsNumberStrategy();
    }

    public static IFindOrderStrategy getFindByLastNameStrategy() {
        return new FindByLastNameStrategy();
    }

    public static IFindOrderStrategy getFindByOrderNumberStrategy() {
        return new FindByOrderNumberStrategy();
    }
}
