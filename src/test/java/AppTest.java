import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.junit.Assert.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Local Shoe Stores");
  }

  @Test
  public void storeAddedSuccessfully() {
    goTo("http://localhost:4567/");
    fill("#store-name").with("Woodstock Shoes");
    submit(".new-store");
    goTo("http://localhost:4567/stores");
    assertThat(pageSource()).contains("Woodstock");
  }

  @Test
  public void brandAddedSuccessfully() {
    goTo("http://localhost:4567/");
    fill("#brand-name").with("Adidas");
    submit(".new-brand");
    goTo("http://localhost:4567/brands");
    assertThat(pageSource()).contains("Adidas");
  }
  //
  //   @Test
  //   public void displayAllStores() {
  //     Store firstStore = new Store("Foot Locker");
  //     Store secondStore = new Store("Woodstock Shoes");
  //     firstStore.save();
  //     secondStore.save();
  //     goTo("http://localhost:4567/stores");
  //     assertThat(pageSource()).contains("Woodstock");
  //     assertEquals(Store.all().size(), 2);
  //   }
  //
  //   @Test
  //   public void displayAllBrands() {
  //     Brand firstBrand = new Brand("Nike");
  //     Brand secondBrand = new Brand("Adidas");
  //     Brand thirdBrand = new Brand("Sketchers");
  //     firstBrand.save();
  //     secondBrand.save();
  //     thirdBrand.save();
  //     goTo("http://localhost:4567/brands");
  //     assertThat(pageSource()).contains("Adidas");
  //     assertEquals(Brand.all().size(), 3);
  //   }

}
