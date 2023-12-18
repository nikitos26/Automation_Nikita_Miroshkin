package projects.herokuapp.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class UploadFile extends BasePage {
    private final By header = By.tagName("h3");
    private final By chooseFileBtn = By.id("file-upload");
    private final String filePath = "/Users/nikitamiroskin/Desktop/Test.rtf";
    private final By uploadBtn = By.id("file-submit");
    private final By uploadedFile = By.id("uploaded-files");


    public void verifyPage(){
        Assert.assertEquals(driver.findElement(header).getText(), "File Uploader", "Wrong header");
        Assert.assertTrue(driver.findElement(chooseFileBtn).isDisplayed(), "Choose Button doesn't appear");
    }

    public void uploadFile(){
        driver.findElement(chooseFileBtn).sendKeys(filePath);
    }

    public void clickUploadBtn(){
        click(uploadBtn);
    }

    public void verifyUploadedFile(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(uploadedFile)));
        Assert.assertEquals(driver.findElement(uploadedFile).getText(), "Test.rtf",
                "The wrong file is uploaded");
    }
}
