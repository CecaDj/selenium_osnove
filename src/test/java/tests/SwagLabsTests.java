package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SwagLabsTests extends BasicTest{

    @Test
    public void verifyErrorIsDisplayedWhenUsernameIsMissing(){

        login.clickOnLoginButton();
        Assert.assertEquals(
                login.getErrorMessageText(),
                "Epic sadface: Username is required",
                "Error message is not valid when username is missing");

    }

    @Test
    public void verifyErrorIsDisplayedWhenPasswordIsMissing(){
        login.clearAndTypeUsername(username);
        login.clickOnLoginButton();

        Assert.assertEquals(
                login.getErrorMessageText(),
                "Epic sadface: Password is required",
                "Error message is not valid when password is missing");
    }

    @Test
    public void verifyErrorIsDisplayedWhenCredentialsAreWrong(){
        String passwordInvalid = "invalidpassword";

        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(passwordInvalid);
        login.clickOnLoginButton();

        Assert.assertEquals(
                login.getErrorMessageText(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message is not valid when credentials are wrong.");
    }

    @Test
    public void verifyErrorIsDisplayedWhenUserIsLocked() {
        String usernameLocked = "locked_out_user";

        login.clearAndTypeUsername(usernameLocked);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        Assert.assertEquals(
                login.getErrorMessageText(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Error message is not valid when user is locked out.");
    }

    @Test
    public void verifySuccessfulLogin(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "User should be redirected to Inventory page.");

        topNav.clickOnMenuButton();

        leftNav.waitForMenuToBeVisible();

        Assert.assertTrue(leftNav.doesLogoutButtonExist(),
                "Logout link should exists on menu.");

        leftNav.clickOnLogoutLink();

        Assert.assertTrue(
                login.doesUsernameInputExist(),
                "Should be redirected to login page after logout.");


    }

    @Test
    public void addingProductsToCart() {
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "User should be redirected to Inventory page.");

        inventory.scrollToItem();
        inventory.clickOnAddToCartButton();
        Assert.assertTrue(inventory.getRemoveButton().isDisplayed());

        Assert.assertEquals(topNav.getCartText(), "1");

    }

    @Test
    public void verifyUrl(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "cart.html",
                "User should be redirected to Cart page.");

    }

    @Test
    public void verifyTheTitlePage(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        inventory.clickOnAddToCartButton();
        topNav.clickOnCartButton();

        Assert.assertEquals(driver.getTitle(), "Swag Labs",
                "Page title should be Swag Labs.");

    }






}
