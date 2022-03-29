package org.acme;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;

@QuarkusTest
@TestProfile(QuinoaTestProfiles.Enable.class)
@QuarkusTestResource(PlaywrightManager.class)
public class UITest {

    @PlaywrightManager.InjectPlaywright
    BrowserContext context;

    @TestHTTPResource("/")
    URL url;

    @Test
    void name() {
        final Page page = context.newPage();
        Response response = page.navigate(url.toString());
        Assertions.assertEquals("OK", response.statusText());

        page.waitForLoadState();

        String title = page.title();
        Assertions.assertEquals("React App", title);

        // Make sure the component loaded and hits the backend
        String greeting = page.innerText(".quinoa");
        Assertions.assertEquals("Hello from RESTEasy Reactive", greeting);
    }
}
