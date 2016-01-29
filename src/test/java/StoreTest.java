import java.util.Arrays;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

public class StoreTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void equals_returnsTrueIfSameName() {
    Store firstStore = new Store("Foot Locker");
    firstStore.save();
    Store secondStore = new Store("Foot Locker");
    secondStore.save();
    assertTrue(firstStore.equals(secondStore));
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Store.all().size(), 0);
  }

  @Test
  public void store_instantiatesNameAndFindsItInListOfStores() {
    Store store = new Store("Foot Locker");
    store.save();
    assertEquals("Foot Locker", Store.find(store.getId()).getName());
  }

  @Test
  public void store_deleteWorksProperly_0() {
    Store store = new Store("Foot Locker");
    store.save();
    store.delete();
    // TO DO: Add onto this test to test include storeConnection in stores_brands, after addStore & getAllStores methods are added to Brand class.
    assertEquals(0, Store.all().size());
  }
  //
  // @Test
  // public void store_updateWorksProperly() {
  //   Store store = new Store("Foot Locker");
  //   store.save();
  //   store.update("Sprint");
  //   assertEquals(store.getName(), "Sprint");
  // }
  //
  // @Test
  // public void getAllBrands_ListsAllBrandsCarriedByStore() {
  //   Store store = new Store("Foot Locker");
  //   store.save();
  //   Brand firstBrand = new Brand("Nike");
  //   firstBrand.save();
  //   Brand secondBrand = new Brand("Adidas");
  //   secondBrand.save();
  //   Brand thirdBrand = new Brand("New Balance");
  //   thirdBrand.save();
  //   store.addStore(firstBrand.getId());
  //   store.addStore(secondBrand.getId());
  //   store.addStore(thirdBrand.getId());
  //   Brand[] brands = new Brand[] {firstBrand, secondBrand, thirdBrand};
  //   assertTrue(store.getAllBrands().containsAll(Arrays.asList(brands)));
  // }

}
