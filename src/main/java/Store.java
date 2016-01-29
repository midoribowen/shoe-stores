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

  public static Store find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id AS mId, name AS mName FROM stores WHERE id = :id";
      Store store = con.createQuery(sql)
                       .addParameter("id", id)
                       .executeAndFetchFirst(Store.class);
      return store;
    }
  }

  // TO-DO: delete method for storeConnection & store

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sqlDeleteStoreConnection = "DELETE FROM stores_brands WHERE stores_brands.store_id = :id";
      con.createQuery(sqlDeleteStoreConnection)
         .addParameter("id", mId)
         .executeUpdate();
    }

    try(Connection con = DB.sql2o.open()) {
      String sqlDeleteStore = "DELETE FROM stores WHERE id = :id";
      con.createQuery(sqlDeleteStore)
         .addParameter("id", mId)
         .executeUpdate();
    }
  }

  // TO-DO: update(String name) method

  public void update(String newName) {
    mName = newName;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stores SET name = :name WHERE id = :id";
      con.createQuery(sql)
         .addParameter("name", newName)
         .addParameter("id", mId)
         .executeUpdate();
    }
  }

  // // TO-DO: addBrand method

  public void addBrand(int brandId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stores_brands (store_id, brand_id) VALUES (:storeId, :brandId)";
      con.createQuery(sql)
         .addParameter("storeId", this.getId())
         .addParameter("brandId", brandId)
         .executeUpdate();
    }
  }

  // TO-DO: getAllBrands method

  public List<Brand> getAllBrands() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT brands.id AS mId, brands.name AS mName FROM brands " +
                   "INNER JOIN stores_brands ON brands.id = stores_brands.brand_id " +
                   "WHERE stores_brands.store_id = :id";
      List<Brand> brandList = con.createQuery(sql)
                                 .addParameter("id", mId)
                                 .executeAndFetch(Brand.class);
      return brandList;
    }
  }


}
