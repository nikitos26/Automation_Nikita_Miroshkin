package lesson_8;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import projects.herokuapp.pages.*;

import static projects.herokuapp.pages.HomePageItems.*;

public class HerokuAppTests extends BaseTest {
    HomePage homePage;

    @BeforeMethod
    public void precondition() {
        homePage = new HomePage();
        homePage.open("https://the-internet.herokuapp.com/");
    }

    @Test(priority = 1)
    public void iframeTest() {
        Frames frames = new Frames();
        homePage.clickOnItem(FRAMES);
        frames.verifyTitleText();
        frames.clickOnItem("iFrame");
        frames.switchToFrame();
        frames.enterText("Your content goes here.");
        frames.verifyTextInField();
        frames.unSwitchToFrame();
    }

    @Test(priority = 2)
    public void uploadFileTest() {
        UploadFile uploadFile = new UploadFile();
        homePage.clickOnItem(FILE_UPLOAD);
        uploadFile.verifyPage();
        uploadFile.uploadFile();
        uploadFile.clickUploadBtn();
        uploadFile.verifyUploadedFile();
    }

    @Test(priority = 3)
    public void ContextMenuTest(){
        ContextMenu contextMenu = new ContextMenu();
        homePage.clickOnItem(CONTEXT_MENU);
        contextMenu.verifyTitleText();
        contextMenu.rightClickOnArea();
        contextMenu.waitAlertAppear();
        contextMenu.verifyTextAlert();
        contextMenu.closeAlert();
    }

    @Test(priority = 4)
    public void DynamicControls() {
        DynamicControls dynamicControls = new DynamicControls();
        homePage.clickOnItem(DYNAMIC_CONTROLS);
        dynamicControls.verifyPage();
        dynamicControls.clickRemoveBtn();
        dynamicControls.checkboxDisappear();
        dynamicControls.verifyTextMessageCheckbox();
        dynamicControls.verifyInputStatus(true);
        dynamicControls.clickEnableBtn();
        dynamicControls.verifyInputTextMessage();
        dynamicControls.verifyInputStatus(false);
    }
}
