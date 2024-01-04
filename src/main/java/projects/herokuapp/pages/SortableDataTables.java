package projects.herokuapp.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class SortableDataTables extends BasePage {
    private final String pageUrl = "https://the-internet.herokuapp.com/tables";
    private final By header = By.tagName("h3");
    private final By cell = By.tagName("td");
    private final By rowInTable = By.tagName("tr");
    private final By tableHeader = By.tagName("thead");
    private final By tableBody = By.tagName("tbody");

    private List<Integer> numbers = Arrays.asList(-79, 99, -83, -75, -78, -22, -57, 84, 11, 15);

    public void verifyPage() {
        wait.until(textToBe(header, "Data Tables"));
        Assert.assertEquals(driver.getCurrentUrl(), pageUrl, "Page has wrong link.");
    }

    private WebElement getTableByIndex(Integer tableIndex) {
        return driver.findElement(By.id("table" + tableIndex));
    }

    public WebElement getTableBodyByTable(Integer tableIndex) {
        return getTableByIndex(tableIndex).findElement(tableBody);
    }

    private List<WebElement> getTableHeaderRow(Integer tableIndex) {
        return getTableByIndex(tableIndex).findElements(tableHeader);
    }

    private Integer getAmountColumns(Integer tableIndex) {
        return getTableBodyByTable(tableIndex).findElements(rowInTable).size();
    }

    private List<String> getDataFromTableRow(Integer tableIndex, Integer rowIndex) {
        return getTableBodyByTable(tableIndex).findElements(rowInTable).get(rowIndex).findElements(cell).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAllDataFromRowsTable(Integer tableIndex) {
        List<String> allData = new ArrayList<>();
        for (int i = 0; i <= getAmountColumns(tableIndex) - 1; i++) {
            allData.addAll(getDataFromTableRow(tableIndex, i));
        }
        return allData.stream()
                .filter(email -> email.contains("@"))
                .collect(Collectors.toList());
    }

    public void formattedEmail(Integer tableIndex) {
        getAllDataFromRowsTable(tableIndex).stream()
                .map(formattedEmail -> formattedEmail.replaceAll("@[a-zA-Z.]+", "@gmail.com"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public void formattedNumbers() {
        System.out.println(numbers.stream()
                .map(number -> Math.abs(number))
                .sorted()
                .collect(Collectors.toList()));
    }
}
