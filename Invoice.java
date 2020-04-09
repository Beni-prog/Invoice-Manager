package Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    private int id;
    private ArrayList<Company> seller=new ArrayList<>();
    private ArrayList<Product> products=new ArrayList<>();
    private double total;
    private String dueDate;
    private String payDate;

    public Invoice(int id, ArrayList<Company> seller, ArrayList<Product> products, double total, String dueDate, String payDate) {
        this.id = id;
        this.seller = seller;
        this.products = products;
        this.total = total;
        this.dueDate = dueDate;
        this.payDate = payDate;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public ArrayList<Company> getSeller() {
        return seller;
    }

    public void setSeller(ArrayList<Company> seller) {
        this.seller = seller;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    private void setTotal(double total) {
        this.total = total;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public void setIsDuplicate(){
        this.payDate=this.payDate+" "+"is duplicate!!!";
    }

    public String toString() {
        return "\n\n\n" + "Invoice: " +id +"\n"+"_______________"+"\n"+ "Seller: "+seller.toString() +"\n" +"_______________"+"\n"+
                "Products: " + products.toString() +"\n"+"_______________"+"\n"+
                "Total: " + total +"\n"+"_______________"+"\n"+
                "Due Date: " + dueDate +"\n"+"_______________"+"\n"+
                "Pay Date: " + payDate+"\u001B[33m"+"\n" +"______________________________"+ "\u001B[0m";
    }
}
