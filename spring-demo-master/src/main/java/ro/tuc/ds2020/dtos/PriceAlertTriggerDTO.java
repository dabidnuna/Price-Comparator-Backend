package ro.tuc.ds2020.dtos;

public class PriceAlertTriggerDTO {

    private String userEmail;
    private String productName;
    private String storeName;
    private double currentPrice;
    private double targetPrice;

    public PriceAlertTriggerDTO(String userEmail, String productName, String storeName,
                                double currentPrice, double targetPrice) {
        this.userEmail = userEmail;
        this.productName = productName;
        this.storeName = storeName;
        this.currentPrice = currentPrice;
        this.targetPrice = targetPrice;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }
}
