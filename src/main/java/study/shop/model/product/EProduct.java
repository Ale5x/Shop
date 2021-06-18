package study.shop.model.product;

public enum  EProduct {

    FOOD("Food"),
    CHILDREN("Children"),
    CONSTRUCTION("Construction"),
    GARDEN("Garden"),
    PHONE("Phone"),
    UNKNOWN("Unknown");

    EProduct() {}

    String category;

    EProduct(String category){
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
