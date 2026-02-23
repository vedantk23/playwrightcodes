package com.testng;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import org.testng.Assert;
import org.testng.annotations.*;

import java.nio.file.Paths;
import java.util.List;

public class WebSiteTesting {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chromium") String browserName) {

        playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false);

        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(options);
                break;
            case "webkit":
                browser = playwright.webkit().launch(options);
                break;
            default:
                browser = playwright.chromium().launch(options);
        }

        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://trytestingthis.netlify.app/");
    }

    // ---------------- Task 5 ----------------
    @Test(priority = 1)
    public void homepageValidation() {

        String title = page.title();
        System.out.println("Browser Title: " + title);

        Assert.assertTrue(title.contains("Try Testing"));

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("homepage.png")));
    }

    // ---------------- Task 6 ----------------
    @Test(priority = 2)
    public void textInputForm() {

        page.fill("#fname", "Vedant");
        page.fill("#lname", "Khandelwal");
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Submit"))
                .click();

        System.out.println("Form Submitted Successfully");
    }

    // ---------------- Task 7 ----------------
    @Test(priority = 3)
    public void radioAndCheckbox() {

        // Select Radio
        page.check("input[value='male']");

        // Select Checkbox
        page.getByLabel("Option 1").check();

        // Assertions
        Assert.assertTrue(page.isChecked("input[value='male']"));

        Assert.assertTrue(
                page.getByLabel("Option 1").isChecked());

        System.out.println("Radio & Checkbox Selected Successfully");
    }

    // ---------------- Task 8 ----------------
    @Test(priority = 4)
    public void dropdownHandling() {

        page.selectOption("#option", "option 2");

        String selected = page.locator("#option").inputValue();
        System.out.println("Selected Option: " + selected);

        Assert.assertEquals(selected, "option 2");
    }

    // ---------------- Task 9 ----------------
    @Test(priority = 5)
    public void alertHandling() {

        page.onDialog(dialog -> {
            System.out.println("Alert message: " + dialog.message());
            dialog.accept(); // press OK
        });

        page.getByText("Your Sample Alert Button!").click();
    }

    // ---------------- Task 10 ----------------
    @Test(priority = 6)
    public void tableHandling() {

        List<Locator> rows = page.locator("table tr").all();
        int rowCount = rows.size();

        int colCount = page.locator("table tr")
                .first().locator("th, td").count();

        System.out.println("Total Rows: " + rowCount);
        System.out.println("Total Columns: " + colCount);

        for (Locator row : rows) {
            System.out.println(row.innerText());
        }

        Assert.assertTrue(rowCount > 0);
    }

    // ---------------- Task 11 ----------------
    @Test(priority = 7)
    public void dragAndDrop() {

        page.waitForSelector("#drag1");
        page.waitForSelector("#div1");

        page.locator("#drag1").dragTo(page.locator("#div1"));

        Assert.assertTrue(
                page.locator("#div1").locator("#drag1").isVisible());
    }

    @AfterMethod
    public void tearDown() {
        browser.close();
        playwright.close();

    }
}
