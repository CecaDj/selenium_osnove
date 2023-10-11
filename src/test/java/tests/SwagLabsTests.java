package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

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

    @Test
    public void verifyTheTotalNumberOfMenuOptions(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        topNav.clickOnMenuButton();
        Assert.assertEquals(leftNav.getNumberOfMenuOptions(), 4,
                "There should be 4 options in menu.");
    }

    @Test
    public void verifyTheSpellingOfAllOptionsInMenu(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        topNav.clickOnMenuButton();
        leftNav.waitForMenuToBeVisible();

        ArrayList<String> text = new ArrayList<>();
        text.add("All Items");
        text.add("About");
        text.add("Logout");
        text.add("Reset App State");

        for (int i = 0; i < text.size(); i++) {
            Assert.assertEquals(leftNav.getMenuOptions().get(i).getAccessibleName(), text.get(i),
                    "Spelling of menu options should be correct.");
        }
    }

    @Test
    public void verifyIfAllItemsOptionIsWorking(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        topNav.clickOnMenuButton();
        leftNav.waitForMenuToBeVisible();

        leftNav.clickOnMenuOption(0);
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "inventory.html",
                 "User should be redirected to inventory page.");

    }
    @Test
    public void verifyIfAboutOptionIsWorking(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        topNav.clickOnMenuButton();

        leftNav.clickOnMenuOption(1);
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/",
                "User should be redirected to sauce labs website.");

    }

    @Test
    public void verifyIfLogoutOptionIsWorking(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        topNav.clickOnMenuButton();

        leftNav.clickOnMenuOption(2);
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl,
                "User should be redirected to log in page.");

    }

    @Test
    public void verifyIfResetAppStateIsWorking(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        topNav.clickOnMenuButton();

        leftNav.clickOnMenuOption(3);
        Assert.assertEquals(topNav.getCartText(), "0",
                "The state of web app should be reseted.");

    }

    @Test
    public void verifyIfTheEkisButtonIsPresented(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        topNav.clickOnMenuButton();

        Assert.assertTrue(leftNav.getEkisButton().isDisplayed(),
                "Ekis button should be visible.");
    }

    @Test
    public void verifyIfTheEkisButtonsIsWorking(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        topNav.clickOnCartButton();
        topNav.clickOnMenuButton();

        leftNav.clickOnEkisButton();

        Assert.assertFalse(leftNav.getEkisButton().isDisplayed(), "Left menu navigation should be closed.");
    }

    @Test
    public void verifyIfTheItemAddedIsPresented(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        inventory.clickOnAddToCartButton();
        topNav.clickOnCartButton();

        Assert.assertTrue(itemList.getSingleItem(0).isDisplayed(), "Added item should be visible.");

    }

    @Test
    public void verifyIfTheItemsTitleIsPresented(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        inventory.clickOnAddToCartButton();
        topNav.clickOnCartButton();

        wait
                .withMessage("Item's title should be visible.")
                .until(ExpectedConditions.textToBePresentInElement(itemList.getSingleItem(0),
                itemList.getItemsTitle(0)));
    }

    @Test
    public void verifyIfTheItemsDescriptionIsPresented(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        inventory.clickOnAddToCartButton();
        topNav.clickOnCartButton();

        wait
                .withMessage("Item's description should be visible.")
                .until(ExpectedConditions.textToBePresentInElement(itemList.getSingleItem(0),
                        itemList.getItemsDescription(0)));

    }
    @Test
    public void verifyIfTheQuantityOfItemIsPresented(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        inventory.clickOnAddToCartButton();
        topNav.clickOnCartButton();

        wait
                .withMessage("Item's quantity should be visible.")
                .until(ExpectedConditions.textToBePresentInElement(itemList.getSingleItem(0),
                        itemList.getItemsQuantity(0)));
    }

    @Test
    public void verifyIfTheItemsTitleIsClickable(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        inventory.clickOnAddToCartButton();
        topNav.clickOnCartButton();

        itemList.clickOnItemTitle(0);
    }

    @Test
    public void verifyIfTheItemsTitleIsWorking(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        inventory.clickOnAddToCartButton();
        topNav.clickOnCartButton();

        itemList.clickOnItemTitle(0);
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory-item"),
                "User should be redirected to item's page.");
    }

    @Test
    public void verifyIfTheRemoveButtonIsPresented(){
        login.clearAndTypeUsername(username);
        login.clearAndTypePassword(password);
        login.clickOnLoginButton();

        inventory.clickOnAddToCartButton();
        topNav.clickOnCartButton();

        Assert.assertTrue(itemList.getRemoveButton(0).isDisplayed(),
                "Remove button should be visible.");
    }


















}
