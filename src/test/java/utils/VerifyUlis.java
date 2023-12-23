package utils;

import org.testng.Assert;

public class VerifyUlis {

    public static void verifyTowTextAreEquels(String actualText,String expectedText){
        Assert.assertEquals(actualText,expectedText);
    }
}
