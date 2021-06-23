package study.shop.model.product;

import study.shop.constant.DataShop;

public enum  EProduct {

    FOOD("Food", DataShop.PATH_FOOD_PRODUCT),
    CHILDREN("Children", DataShop.PATH_CHILDREN_PRODUCT),
    CONSTRUCTION("Construction", DataShop.PATH_CONSTRUCTION_PRODUCT),
    GARDEN("Garden", DataShop.PATH_GARDEN_PRODUCT),
    PHONE("Phone", DataShop.PATH_PHONE_PRODUCT),
    UNKNOWN("Unknown"),
    TEST("Test", DataShop.PATH_TEST_PRODUCT);

    EProduct() {}

    String category;
    String pathProduct;

    EProduct(String category){
        this.category = category;
    }

    EProduct(String category, String pathProduct){
        this.category = category;
        this.pathProduct = pathProduct;
    }

    public String getCategory() {
        return category;
    }

    public String getPathProduct() {
        return pathProduct;
    }
}
