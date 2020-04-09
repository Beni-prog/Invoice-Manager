package Generator;

import Domain.Product;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class ProductGenerator extends CompanyGenerator{
    private ArrayList<Product> products=new ArrayList<>();
    public ProductGenerator(ArrayList<String> names, int n1,int n2) {
        super(names, n1);
        this.constructProducts(n2);
    }

    public void constructProducts(int n2){
        Random rd=new Random();
        String s;
        s =getRandomText(5);
        double rangeMin=0.1;
        double rangeMax=999.9;
        double p = rangeMin + (rangeMax - rangeMin) * rd.nextDouble();//this is the price
        //I chose to form the first product here, because I want to make myself sure that the names of the products
        //will be unique
        products.add(new Product(0,s,p));
        for(int i=1;i<n2;i++)
        {

            String s1=getRandomText(5);
            while(s.equals(s1))
                s1=getRandomText(5);
            p = rangeMin + (rangeMax - rangeMin) * rd.nextDouble();
            products.add(new Product(i,s,p));
            s=s1;
        }
    }

    //this function forms a random text of chars, of length len
    //returns a string
    public static String getRandomText(int len) {
        StringBuilder b = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i<len;i++) {
            char c = (char)(65+r.nextInt(25));
            b.append(c);
        }
        return b.toString();
    }
    public ArrayList<Product> getProducts(){return this.products;}
}
