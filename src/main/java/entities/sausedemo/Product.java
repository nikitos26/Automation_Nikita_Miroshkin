package entities.sausedemo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class Product {
    private String productName;
    private String productType;
    private Double productPrice;

    public static class ProductBuilder{
        public ProductBuilder(){}
    }
}
