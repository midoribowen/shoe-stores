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

  public static List<Brand> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id AS mId, name AS mName FROM brands";
      return con.createQuery(sql).executeAndFetch(Brand.class);
    }
  }

  // To-DO: save method for brand

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO brands (name) VALUES (:name)";
      this.mId = (int) con.createQuery(sql, true)
                          .addParameter("name", this.mName)
                          .executeUpdate()
                          .getKey();
    }
  }

  //
  // TO-DO: find method for brand

  public static Brand find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id AS mId, name AS mName FROM brands WHERE id = :id";
      Brand brand = con.createQuery(sql)
                       .addParameter("id", id)
                       .executeAndFetchFirst(Brand.class);
      return brand;
    }
  }


  // // TO-DO: addStore method

  public void addStore(int storeId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stores_brands (store_id, brand_id) VALUES (:storeId, :brandId)";
      con.createQuery(sql)
         .addParameter("storeId", storeId)
         .addParameter("brandId", this.getId())
         .executeUpdate();
    }
  }

  // TO-DO: getAllStores method

  public List<Store> getAllStores() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT stores.id AS mId, stores.name AS mName FROM stores " +
                   "INNER JOIN stores_brands ON stores.id = stores_brands.store_id " +
                   "WHERE stores_brands.brand_id = :id";
      List<Store> storeList = con.createQuery(sql)
                                 .addParameter("id", mId)
                                 .executeAndFetch(Store.class);
      return storeList;
    }
  }


}
