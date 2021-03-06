import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// READ ALL STORES

    get("/stores", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stores", Store.all());
      model.put("template", "templates/stores.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// CREATE NEW STORE

    post("/stores", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Store store = new Store(request.queryParams("store-name"));
      store.save();
      model.put("store", store);
      response.redirect("/");
      return null;
    });

// DELETE A STORE

    post("/stores/delete", (request, response) -> {
      Store store = Store.find(Integer.parseInt(request.queryParams("id")));
      store.delete();
      response.redirect("/stores");
      return null;
    });

// READ SINGULAR STORE

    get("/store/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("store", Store.find(Integer.parseInt(request.params(":id"))));
      model.put("brands", Brand.all());
      model.put("template", "templates/store.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// UPDATE STORE

    post("/store/:id", (request, response) -> {
      Store store = Store.find(Integer.parseInt(request.params("id")));
      String newName = request.queryParams("new-name");
      store.update(newName);
      response.redirect("/store/" + store.getId());
      return null;
    });

// ADD A BRAND TO STORE

    post("/store/:id/add-brand", (request, response) -> {
      Store store = Store.find(Integer.parseInt(request.queryParams("store-id")));
      store.addBrand(Integer.parseInt(request.queryParams("add-brand")));
      response.redirect("/store/" + store.getId());
      return null;
    });


// TO-DO Remove a brand from a store using Brand.removeBrand() method


// READ ALL BRANDS

    get("/brands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("brands", Brand.all());
      model.put("template", "templates/brands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// CREATE BRAND

    post("/brands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Brand brand = new Brand(request.queryParams("brand-name"));
      brand.save();
      model.put("brand", brand);
      response.redirect("/");
      return null;
    });

// READ SINGULAR BRAND

    get("/brand/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("brand", Brand.find(Integer.parseInt(request.params(":id"))));
      model.put("stores", Store.all());
      model.put("template", "templates/brand.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// ADD A STORE TO A BRAND

    post("/brand/:id/add-store", (request, response) -> {
      Brand brand = Brand.find(Integer.parseInt(request.queryParams("brand-id")));
      brand.addStore(Integer.parseInt(request.queryParams("add-store")));
      response.redirect("/brand/" +brand.getId());
      return null;
    });

  }
}
