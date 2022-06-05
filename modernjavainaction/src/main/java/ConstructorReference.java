import threadcreation.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConstructorReference {

    public static void main(String[] args) {
        List<Double> weights = Arrays.asList(7.0, 3.1, 4.2, 10.1);
        List<Apple> apples = map(weights, Apple::new);
        apples.forEach(System.out::println);
    }

    public static List<Apple> map(List<Double> list, Function<Double, Apple> f) {
        return list.stream().map(f).collect(Collectors.toList());
    }
}
