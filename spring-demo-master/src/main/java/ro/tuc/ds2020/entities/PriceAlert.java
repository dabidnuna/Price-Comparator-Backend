package ro.tuc.ds2020.entities;

import javax.persistence.*;

@Entity
public class PriceAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private double targetPrice;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    public PriceAlert() {}


    public PriceAlert(String userEmail, double targetPrice, Product product) {
        this.userEmail = userEmail;
        this.targetPrice = targetPrice;
        this.product = product;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
