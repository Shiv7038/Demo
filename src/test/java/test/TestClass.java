package test;

import Base.BaseClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;



public class TestClass extends BaseClass {
    @Test(priority = 0, description = "Invalid Login Scenario with wrong username and password.")
    public void invalidLoginTest_InvalidUserNameInvalidPassword(Method method) {
        //ExtentReports Description
       // startTest(method.getName(), "Invalid Login Scenario with invalid username and password.");
      //  LogUtil.info("Hi");
    }
    @Test(priority = 1, description = "Invalid Login Scenario with empty username and password.")
    public void invalidLoginTest_EmptyUserEmptyPassword(Method method) {
        //ExtentReports Description
      //  startTest(method.getName(), "Invalid Login Scenario with empty username and password.");
     //  LogUtil.info("hii");
    }
}
