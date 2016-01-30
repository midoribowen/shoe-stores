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

    @Test
    public void displayAllStores() {
      Store firstStore = new Store("Foot Locker");
      Store secondStore = new Store("Woodstock Shoes");
      firstStore.save();
      secondStore.save();
      goTo("http://localhost:4567/stores");
      assertThat(pageSource()).contains("Woodstock");
      assertEquals(Store.all().size(), 2);
    }

    @Test
    public void displayAllBrands() {
      Brand firstBrand = new Brand("Nike");
      Brand secondBrand = new Brand("Adidas");
      Brand thirdBrand = new Brand("Sketchers");
      firstBrand.save();
      secondBrand.save();
      thirdBrand.save();
      goTo("http://localhost:4567/brands");
      assertThat(pageSource()).contains("Adidas");
      assertEquals(Brand.all().size(), 3);
    }

    @Test
    public void deleteStore() {
      Store firstStore = new Store("Foot Locker");
      Store secondStore = new Store("Woodstock Shoes");
      firstStore.save();
      secondStore.save();
      goTo("http://localhost:4567/stores");
      click("option", withText("Foot Locker"));
      submit(".delete-store");
      assertThat(!(pageSource()).contains("Foot Locker"));
    }

    @Test
    public void updateStore() {
      Store store = new Store("Foot Locker");
      store.save();
      goTo("http://localhost:4567/store/" + Integer.toString(store.getId()));
      fill("#new-name").with("Woodstock Shoes");
      submit(".update-store");
      assertThat(pageSource()).contains("Woodstock Shoes");
    }

    @Test
    public void addBrandToStore() {
      Store store = new Store("Foot Locker");
      store.save();
      Brand firstBrand = new Brand("Sketchers");
      firstBrand.save();
      goTo("http://localhost:4567/store/" + Integer.toString(store.getId()));
      click("option", withText("Sketchers"));
      submit(".add-brand");
      assertThat(pageSource()).contains("Sketchers");
    }

}
