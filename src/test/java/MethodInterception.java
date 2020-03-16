import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Capabilities;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MethodInterception {

    @Test
    public void annotationValue() {
        MainPage mainPage = createPage(MainPage.class);
        Assert.assertNotNull(mainPage);
        Assert.assertEquals(".//*[@test-attr='button_search']",mainPage.buttonSearch());
        Assert.assertEquals( ".//*[@test-attr='input_search']",mainPage.textInputSearch());
    }

    private MainPage createPage(Class clazz) {
        MainPage page = new MainPage() {
            @Override
            public String textInputSearch() {
                return null;
            }

            @Override
            public String buttonSearch() {
                return null;
            }
        };


        ClassLoader classLoader = page.getClass().getClassLoader();

        Class[] interfaces = page.getClass().getInterfaces();

        MainPage mainPage = (MainPage) Proxy.newProxyInstance(classLoader, interfaces, (InvocationHandler) new MyExtension(page));

       return mainPage;
}
}
