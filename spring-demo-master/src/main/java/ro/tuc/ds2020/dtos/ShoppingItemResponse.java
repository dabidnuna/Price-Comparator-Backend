package ro.tuc.ds2020.dtos;

public class ShoppingItemResponse {
    private String productId;
    private String productName;
    private int quantity;
    private String store;
    private double unitPrice;
    private double totalPrice;


    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getStore() { return store; }
    public void setStore(String store) { this.store = store; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
