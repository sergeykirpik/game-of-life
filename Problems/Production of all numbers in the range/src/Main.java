import java.util.function.*;


class Operator {

    public static LongBinaryOperator binaryOperator = (left, right) -> {
        long product = left;
        for (long number = left + 1; number <= right; number++) {
            product *= number;
        }
        return product;
    };
}