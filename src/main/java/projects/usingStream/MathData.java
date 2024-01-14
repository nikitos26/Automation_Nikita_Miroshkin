package projects.usingStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MathData {
    private List<Integer> numbers = Arrays.asList(-79, 99, -83, -75, -78, -22, -57, 84, 11, 15);

    public void formattedNumbers() {
        System.out.println(numbers.stream()
                .map(number -> Math.abs(number))
                .sorted()
                .collect(Collectors.toList()));
    }

}
