package ro.tuc.ds2020.dtos;

public class ProductRecommendationDTO {
    private String productId;
    private String productName;
    private String brand;
    private double unitPrice;
    private double quantity;
    private String unit;
    private String store;


    public ProductRecommendationDTO(String productId, String productName, String brand,
                                    double unitPrice, double quantity, String unit, String store) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.unit = unit;
        this.store = store;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
