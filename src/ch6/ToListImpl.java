package ch6;

import ch5.Dish;
import provider.DishProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListImpl {
    public static void main(String[] args) {
        List<Dish> menu = DishProvider.getDishList();

        List<Dish> dishes = menu.stream().collect(new CustomizedToList<>());
        System.out.println(dishes);

        // 위와 같음
        menu.stream().collect(
            ArrayList::new,
            List::add,
            List::addAll
        );
    }
}

class CustomizedToList<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(
            EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT)
        );
    }
}
