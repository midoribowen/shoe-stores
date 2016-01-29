import java.util.Arrays;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

public class BrandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void equals_returnsTrueIfSameName() {
    Brand firstBrand = new Brand("Nike");
    firstBrand.save();
    Brand secondBrand = new Brand("Nike");
    secondBrand.save();
    assertTrue(firstBrand.equals(secondBrand));
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Brand.all().size(), 0);
  }

  // @Test
  // public void brands_instatiatesName() {
  //   Brand brand = new Brand("Nike");
  //   brand.save();
  //   assertEquals("Nike", Brand.find(brand.getId()).getName());
  // }
  //
  // @Test
  // public void getAllStores_ListsAllStoresThatCarryBrand() {
  //   Brand brand = new Brand("Nike");
  //   brand.save();
  //   Store firstStore = new Store("Foot Locker");
  //   firstStore.save();
  //   Store secondStore = new Store("Sprint");
  //   secondStore.save();
  //   Store thirdStore = new Store("Woodstock Shoes");
  //   thirdStore.save();
  //   brand.addStore(firstStore.getId());
  //   brand.addStore(secondStore.getId());
  //   brand.addStore(thirdStore.getId());
  //   Store[] stores = new Store[] {firstStore, secondStore, thirdStore};
  //   assertTrue(brand.getAllStores().containsAll(Arrays.asList(stores)));
  // }

}
