package com.lvnam0801.Luna.ResourceRepresentation;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductCreateRequest {
    private String name;
    private String photoURL;
    private String origin;
    private int wholesalePrice;
    private int retailPrice;
    private String status;
    private int manufacturerID;
    private int categoryID;
    private List<ProductDimensionRequest> dimensions; // New field for dimensions
}