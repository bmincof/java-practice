package ch6;

import ch5.Dish;
import provider.DishProvider;

import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

public class PartitioningExample {
    public static void main(String[] args) {
        List<Dish> menu = DishProvider.getDishList();

        Map<Boolean, List<Dish>> partitionedMenu = menu.stream()
            .collect(partitioningBy(Dish::isVegetarian));

        System.out.println(partitionedMenu);

        // collect 형식을 지정할 수 있다
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream()
            .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));

        System.out.println(vegetarianDishesByType);

        // multi level partitioning도 가능
        Map<Boolean, Map<Boolean, List<Dish>>> example = menu.stream()
            .collect(partitioningBy(Dish::isVegetarian,
                    partitioningBy(d -> d.getCalories() > 500)));
    }
}
