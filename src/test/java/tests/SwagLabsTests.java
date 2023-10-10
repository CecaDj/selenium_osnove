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

    @Test
    public void verifyTheTitleInHeader(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        Assert.assertEquals(topNav.getHeaderTitle(), "Swag Labs",
                "Title in header should be Swag Labs.");

    }

    @Test
    public void verifyIfTheCartIconIsPresented(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        Assert.assertTrue(topNav.getCartLink().isDisplayed(), "Cart icon should be presented.");
    }

    @Test
    public void verifyIfTheHamburgerMenuButtonIsPresented(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        Assert.assertTrue(topNav.getMenuButton().isDisplayed(), "Hamburger menu button should be presented");
    }

    @Test
    public void verifyIfTheHamburgerMenuButtonIsEnabled(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        Assert.assertTrue(topNav.getMenuButton().isEnabled(), "Hamburger menu button should be enabled.");

    }

    @Test
    public void verifyIfTheCartIconIsEnabled(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        Assert.assertTrue(topNav.getCartLink().isEnabled(), "The cart icon should be enabled.");

    }
    @Test
    public void verifyIfTheHamburgerButtonIsWorking(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        topNav.clickOnMenuButton();
        leftNav.waitForMenuToBeVisible();
    }

    @Test
    public void verifyIfTheCartIconIsWorking(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        topNav.clickOnCartButton();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/cart.html"),
                "User should be redirected to cart page.");
    }

    @Test
    public void verifyIfTheCartIconHasCorrectNumberOfAddedItems(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        inventory.clickOnAddToCartButton();
        topNav.clickOnCartButton();
        Assert.assertEquals(topNav.getCartText(), "1",
                "Number in the cart icon should be equivalent to the total number of added items");

    }

    @Test
    public void verifyTheSubHeaderTitle(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        Assert.assertEquals(subHeader.getSubHeaderTitle(), "Your Cart",
                "Sub-header title should be valid");
    }






}
