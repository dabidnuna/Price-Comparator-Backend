package ro.tuc.ds2020.dtos;

public class PriceAlertRequestDTO {

    private String productId;
    private String userEmail;
    private double targetPrice;

    public PriceAlertRequestDTO() {}

    public PriceAlertRequestDTO(String productId, String userEmail, double targetPrice) {
        this.productId = productId;
        this.userEmail = userEmail;
        this.targetPrice = targetPrice;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }
}
