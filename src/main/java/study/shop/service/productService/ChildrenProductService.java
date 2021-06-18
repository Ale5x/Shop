package study.shop.service.productService;

import study.shop.constant.DataShop;
import study.shop.model.product.EProduct;
import study.shop.service.ProductService;

public class ChildrenProductService extends ProductService {

    private EProduct eProduct = EProduct.CHILDREN;

    public ChildrenProductService() {
    }

    @Override
    public String getPathFile() {
        return DataShop.PATH_CHILDREN_PRODUCT;
    }

    @Override
    public Enum getCategory() {
        return eProduct;
    }

}
