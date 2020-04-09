package Generator;

import Domain.Company;
import Domain.Invoice;
import Domain.Product;
import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.ls.LSOutput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvoiceGenerator extends ProductGenerator  {
    private ArrayList<Invoice> invoices=new ArrayList<>();

    public InvoiceGenerator(ArrayList<String> names, int n1, int n2,int n3) throws ParseException {
        super(names, n1, n2);
        this.constructInvoice(n3);
    }

    private void constructInvoice(int n3) throws ParseException {
        ArrayList<Company> companies=getCompanies();
        int[] freqVector =new int [companies.size()];
        for(int i=0;i<companies.size();i++)
            freqVector[i]=0;
        Random rd=new Random();
        int m=0;
        for(int i=0;i<n3;i++)
        {
            ArrayList<Company> companiesForEach=new ArrayList<>();
            //here I generate a random company and I put in the array companiesForEach
            int nr=rd.nextInt(companies.size());
            while(freqVector[nr]==1)
                nr=rd.nextInt(companies.size());
            companiesForEach.add(companies.get(nr));
            freqVector[nr]=1;

            int nrProducts=rd.nextInt(3);//generate a random number between 1 and 3
            ArrayList<Product> productsForEach=new ArrayList<>();
            int x=rd.nextInt(this.getProducts().size());//here I generate a random index for products
            productsForEach.add(this.getProducts().get(x));
            for(int j=1;j<nrProducts;j++)//generates random products
            {
                int y=rd.nextInt(this.getProducts().size());
                while(x==y)
                    y=rd.nextInt(this.getProducts().size());
                productsForEach.add(this.getProducts().get(y));
                x=y;
            }

            //here I built the invoices
            if(n3<companies.size()&&m==n3-1) //I put this condition here so that all remaining companies will be added to the last invoice
            {
                //here I take all the remaining companies and put to the last invoice
                //this treats only the case in which the nr. of companies is greater than the invoices one
                for(int k=0;k<companies.size();k++)
                    if(freqVector[k]==0)
                        companiesForEach.add(companies.get(k));
                if (i <= (10 * n3) / 100)//at least 10% of the generated invoices must be "unpaid".
                    invoices.add(new Invoice(i, companiesForEach, productsForEach, getTotal(productsForEach),
                            this.getDueDate(), "unpaid"));
                else
                    invoices.add(new Invoice(i, companiesForEach, productsForEach, getTotal(productsForEach),
                            this.getDueDate(), getPayDate()));
            }
            else
            {
                if (i <= (10 * n3) / 100)//at least 10% of the generated invoices must be "unpaid".
                    invoices.add(new Invoice(i, companiesForEach, productsForEach, getTotal(productsForEach),
                            this.getDueDate(), "unpaid"));
                else
                    invoices.add(new Invoice(i, companiesForEach, productsForEach, getTotal(productsForEach),
                            this.getDueDate(), getPayDate()));
            }
            m++;
        }
    }

    public ArrayList<Invoice> getInvoices(){return this.invoices;}

    //this function forms a random date in the next 5 days
    //returns a date as a string
    static String getDueDate() throws ParseException {
        Random rd=new Random();
        int d=rd.nextInt(6);
        String dt = LocalDate.now().toString();  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.DATE, d);  // number of days to add
        return dt = sdf.format(c.getTime());  // dt is now the new date
    }
     //this function forms a random date in the previous 5 days
    //returns a date as a string
    static String getPayDate() throws ParseException {
        Random rd=new Random();
        int d=rd.nextInt(6);
        String dt = LocalDate.now().toString();  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.DATE, -d);  // number of days to substract
        return dt = sdf.format(c.getTime());  // dt is now the new date
    }

    //this function computes the total price of some products
    //returns a double
    public double getTotal(ArrayList<Product> p){
        double t=0;
        for(int i=0;i<p.size();i++)
            t+=p.get(i).getPrice();
        return t;
    }

    private Invoice getInvoice(int id){
        for(Invoice i:invoices)
            if(i.getId()==id)
                return i;
            return null;
    }

    //this is the function for the first option
    public void duplicate(){
        Random rd=new Random();
        int i=rd.nextInt(invoices.size());
        while(invoices.get(i).getPayDate().length()>11)//I want to duplicate an invoice that it wasn't already duplicated
            i=rd.nextInt(invoices.size());
        Invoice invoice=new Invoice(invoices.size(),invoices.get(i).getSeller(),invoices.get(i).getProducts(),
                invoices.get(i).getTotal(),invoices.get(i).getDueDate(),invoices.get(i).getPayDate()+
                "\u001B[31m"+"\nThis one is duplicate!"+"\u001B[0m");
        invoices.add(invoice);
        System.out.println("\u001B[32m"+"Invoice successfuly duplicated!"+"\u001B[0m");
    }

    //this is the function for the second option
    public void orderInvoices(){
        List<Invoice> paidInvoices=new ArrayList<>();
        List<Invoice> unpaidInvoices=new ArrayList<>();
        //
        for(Invoice i : invoices)
        {
            if(i.getPayDate().equals("unpaid"))
                unpaidInvoices.add(i);
            else
                paidInvoices.add(i);
        }
        unpaidInvoices= unpaidInvoices.stream().sorted(Comparator.comparing(Invoice::getPayDate)).collect(Collectors.toList());;
        paidInvoices= paidInvoices.stream().sorted(Comparator.comparing(Invoice::getPayDate).reversed()).collect(Collectors.toList());;
        for(Invoice i:unpaidInvoices)
        {
            System.out.println("\u001B[33m"+"______________________________"+"\u001B[0m");
            System.out.println("Id:"+i.getId());
            System.out.println("_______________");
            for(Company c: i.getSeller())
                System.out.println(c);
            System.out.println("_______________");
            System.out.println(i.getDueDate()+"\n"+i.getPayDate());
            System.out.println("_______________");
            for(Product p:i.getProducts())
                System.out.println(p.getName());
            System.out.println("_______________");
            System.out.println(i.getTotal());
            System.out.println("_______________");
            String [] elems1=i.getDueDate().split("-");
            String local=LocalDate.now().toString();
            String [] elems2=local.split("-");
            int daysLeft=Integer.parseInt(elems1[2])-Integer.parseInt(elems2[2]);
            System.out.println("\u001B[31m"+"There are only "+daysLeft+" days left to pay!\n"+"\u001B[0m");
            System.out.println("\u001B[33m"+"______________________________"+"\u001B[0m");
        }
        for(Invoice i:paidInvoices)
        {
            System.out.println("\u001B[33m"+"______________________________"+"\u001B[0m");
            System.out.println("Id:"+i.getId());
            System.out.println("_______________");
            for(Company c: i.getSeller())
                System.out.println(c);
            System.out.println("_______________");
            System.out.println(i.getDueDate()+"\u001B[31m"+"\nThe invoice was paid on:"+"\u001B[0m"+i.getPayDate());
            for(Product p:i.getProducts())
                System.out.println(p.getName());
            System.out.println("_______________");
            System.out.println(i.getTotal());
            System.out.println("\u001B[33m"+"______________________________"+"\u001B[0m");
        }
    }

    //this is the function for the third option
    public void searchInvoice(String text){
        //here I took 4 arrays, in which I put the products such that they verify the conditions from the problem
        List<Invoice> results1=new ArrayList<>();
        int [] freqVect=new int [invoices.size()];//using this vector I make myself sure I won't have 2 identical invoices in output
        for(int i=0;i<invoices.size();i++)
            freqVect[i]=0;
        results1= invoices.stream().filter(x->{
            for(Company c:x.getSeller())
            {
                String [] s=c.getName().split(" ");
                if(s[0].equals(text)&&freqVect[x.getId()]==0)
                {
                    freqVect[x.getId()]=1;
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        List<Invoice> results2=new ArrayList<>();
        results2= invoices.stream().filter(x->{
            for(Company c:x.getSeller())
            {
                String [] s=c.getName().split(" ");
                if(s[1].equals(text)&&freqVect[x.getId()]==0)
                {
                    freqVect[x.getId()]=1;
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        List<Invoice> results3=new ArrayList<>();
        results3= invoices.stream().filter(x->{
            for(Company c:x.getSeller())
            {
                String [] s=c.getName().split(" ");
                if(!s[1].equals(text)&&!s[0].equals(text)&&c.getName().contains(text)&&freqVect[x.getId()]==0)
                {
                    freqVect[x.getId()]=1;
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        List<Invoice> results4=new ArrayList<>();
        results4= invoices.stream().filter(x->{
            int ok=0;
            for(Product p:x.getProducts())
            {
                if (p.getName().equals(text)) {
                    ok = 1;
                    break;
                }
            }
            return ok == 1;
        }).collect(Collectors.toList());
        List<Invoice> resultsF = Stream.concat(results1.stream(), results2.stream())
                .collect(Collectors.toList());
        List<Invoice> resultsS = Stream.concat(results3.stream(), results4.stream())
                .collect(Collectors.toList());
        //I concatenate all the 4 lists
        List<Invoice> results = Stream.concat(resultsF.stream(), resultsS.stream())
                .collect(Collectors.toList());
        int nr = Math.min(results.size(), 10);
        for(Invoice inv: results)
        {
            System.out.println("\u001B[33m"+"______________________________"+"\u001B[0m");
            for(Company c:inv.getSeller())
                System.out.println(c.getName());
            System.out.println("_______________");
            System.out.println(inv.getDueDate()+"\n"+inv.getPayDate());
            System.out.println("_______________");
            for(Product p:inv.getProducts())
                System.out.println(p.getName());
            System.out.println("\u001B[33m"+"______________________________"+"\u001B[0m");
            nr--;
            if(nr==0)
                break;
        }
    }
    public void payInvoice(int id){
        Invoice i=getInvoice(id);
        if(i!=null&&i.getPayDate().equals("unpaid"))
        {
            i.setPayDate(LocalDate.now().toString());
            System.out.println("\u001B[32m"+"Invoice successfuly paid!"+"\u001B[0m");
        }
        else
            System.out.println("\u001B[31m"+"\nThe invoice is already paid!"+"\u001B[0m");
    }
}
