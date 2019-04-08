package com.shopping.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartProductVo {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;//购物车中商品的数量
    private String productName;
    private String productSubtitle;
    private String productMainImage;
    private BigDecimal productPrice;
    private Integer productStatus;
    private BigDecimal productTotalPrice;
    private Integer productStock;
    private Integer productChecked;
    private String limitQuantity;//限制数量的一个返回结果

}
