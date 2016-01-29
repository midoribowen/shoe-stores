import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Store {
  private int mId;
  private String mName;

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  public Store(String name) {
    this.mName = name;
  }

  @Override
  public boolean equals(Object otherStore) {
    if (!(otherStore instanceof Store)) {
      return false;
    } else {
      Store newStore = (Store) otherStore;
      return this.getName().equals(newStore.getName());
    }
  }
  // TO-DO: all method for Store

  public static List<Store> all() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT id AS mId, name AS mName FROM stores";
     return con.createQuery(sql).executeAndFetch(Store.class);
   }
  }

  // To-DO: save method for store

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stores(name) VALUES (:name)";
      this.mId = (int) con.createQuery(sql, true)
                          .addParameter("name", this.mName)
                          .executeUpdate()
                          .getKey();
    }
  }


  // TO-DO: find method for store
  //
  //   public static Store find(int id) {
  //
  // }
  //
  // TO-DO: delete method for storeConnection & store
  //
  // public void delete() {
  //
  // }
  //

  // TO-DO: update(String name) method
  //
  // public void update(String name) {
  //
  // }
  //
  // // TO-DO: addBrand method
  //
  // public void addBrand(int brandId) {
  //
  // }

  // TO-DO: getAllBrands method
  //
  // public List<Brand> getAllBrands() {
  //
  // }


}
