package lesson_12;

import org.testng.annotations.Test;
import projects.usingStream.MathData;

public class SortedNumbers {
    MathData mathData;

    @Test(priority = 1, description = "Get sorted numbers.")
    public void sortedNumbers() {
        mathData = new MathData();
        mathData.formattedNumbers();
    }
}
