package ro.tuc.ds2020.dtos;

import java.util.List;

public class ShoppingBasketResponse {
    private double totalOptimizedCost;
    private List<ShoppingItemResponse> items;


    public double getTotalOptimizedCost() { return totalOptimizedCost; }
    public void setTotalOptimizedCost(double totalOptimizedCost) { this.totalOptimizedCost = totalOptimizedCost; }

    public List<ShoppingItemResponse> getItems() { return items; }
    public void setItems(List<ShoppingItemResponse> items) { this.items = items; }
}
