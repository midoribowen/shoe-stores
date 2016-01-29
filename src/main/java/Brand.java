import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Brand {
  private int mId;
  private String mName;

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  public Brand(String name) {
    this.mName = name;
  }

  @Override
  public boolean equals(Object otherBrand) {
    if (!(otherBrand instanceof Brand)) {
      return false;
    } else {
      Brand newBrand = (Brand) otherBrand;
      return this.getName().equals(newBrand.getName());
    }
  }
  // TO-DO: all method for Brand
  //
  // public static List<Brand> all() {
  //
  // }
  //
  // To-DO: save method for brand
  //
  // public void save() {
  //
  // }
  //
  //
  // TO-DO: find method for brand
  //
  //   public static Brand find(int id) {
  //
  // }
  //
  //
  // // TO-DO: addStore method
  //
  // public void addStore(int storeId) {
  //
  // }

  // TO-DO: getAllStores method
  //
  // public List<Store> getAllStores() {
  //
  // }


}
