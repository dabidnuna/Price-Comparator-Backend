package ro.tuc.ds2020.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Discount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "storeName", nullable = false)
    private String storeName;

    @Column(name = "fromDate", nullable = false)
    private LocalDate fromDate;

    @Column(name = "toDate", nullable = false)
    private LocalDate toDate;

    @Column(name = "percentage", nullable = false)
    private int percentage;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    public Discount() {}


    public Discount(String storeName, LocalDate fromDate, LocalDate toDate, int percentage, Product product) {
        this.storeName = storeName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.percentage = percentage;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
