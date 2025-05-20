package ro.tuc.ds2020.dtos;

public class PriceEntryRequestDTO {

    private String productId;
    private String storeName;
    private double price;
    private String currency;

    public PriceEntryRequestDTO() {}

    public PriceEntryRequestDTO(String productId, String storeName, double price, String currency) {
        this.productId = productId;
        this.storeName = storeName;
        this.price = price;
        this.currency = currency;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
