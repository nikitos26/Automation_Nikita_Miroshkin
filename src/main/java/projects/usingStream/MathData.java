package projects.usingStream;

import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
public class MathData {
    private List<Integer> numbers = Arrays.asList(-79, 99, -83, -75, -78, -22, -57, 84, 11, 15);

    public void formattedNumbers() {
        log.info(numbers.stream()
                .map(number -> Math.abs(number))
                .sorted()
                .collect(Collectors.toList()));
    }

}
