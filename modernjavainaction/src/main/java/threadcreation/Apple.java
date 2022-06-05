package threadcreation;


import lombok.ToString;

@ToString(callSuper = true)
public class Apple extends Fruit {
    public Apple(Color color, Double weight) {
        super(color, weight);
    }

    public Apple(Double weight) {
        super(weight);
    }
}