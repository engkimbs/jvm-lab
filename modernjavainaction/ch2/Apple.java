package modernjavainaction.ch2;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Apple extends Fruit {
    public Apple(Color color, Double weight) {
        super(color, weight);
    }
}