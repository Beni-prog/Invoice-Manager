package UI;

import Domain.Company;
import Domain.Invoice;
import Domain.Product;
import Generator.CompanyGenerator;
import Generator.FileReaderCompany;
import Generator.InvoiceGenerator;
import Generator.ProductGenerator;
import jdk.swing.interop.SwingInterOpUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private void newDataMenu(){
        System.out.println("\u001B[35m" + "______________________________" + "\u001B[35m");
        System.out.println("1. Press 1 for inputting the set of 8 company names from keyboard.\n");
        System.out.println("2. Press 2 for using the predefined set of company names.\n");
        System.out.println("0. Exit\n");
        System.out.println("\u001B[35m" + "______________________________" + "\u001B[0m");

    }
    private void persistedDataMenu(){
        System.out.println("\u001B[35m" + "______________________________" + "\u001B[35m");
        System.out.println("1. Duplicate an invoice.\n");
        System.out.println("2. Order the invoice list.\n");
        System.out.println("3. Search the list by giving a text.\n");
        System.out.println("4. Pay an invoice.\n");
        System.out.println("5. Show all the invoices!\n");
        System.out.println("0. Exit.\n");
        System.out.println("\u001B[35m" + "______________________________" + "\u001B[0m");
    }

    public void options() throws ParseException {
        while (true) {
            newDataMenu();
            Scanner in = new Scanner(System.in);
            int command = in.nextInt();
            while(command<0||command>2)
            {
                System.out.println("\u001B[31m"+"Invalid command!Please input a valid one: "+"\u001B[0m");
                command=in.nextInt();
            }
            if (command == 1) {
                ArrayList<String> names = new ArrayList<>();
                String s = in.nextLine();
                System.out.println("1. Input the set of 8 Company names.\n");
                for (int i = 0; i < 8; i++) {
                    s = in.nextLine();
                    names.add(s);
                }
                System.out.println("The number of companies wanted to be generated: ");
                int nc = in.nextInt();
                System.out.println("Give me the number of the products wanted to be generated: ");
                int np = in.nextInt();
                System.out.println("Give me the number of the invoices wanted to be generated: ");
                int ni = in.nextInt();
                InvoiceGenerator ig = new InvoiceGenerator(names, nc, np, ni);

                while (true) {
                    System.out.println("Available options: \n");
                    persistedDataMenu();
                    System.out.println("Give me an option: ");
                    int option = in.nextInt();
                    while (option < 0 || option > 5) {
                        System.out.println("\u001B[31m"+"Invalid command!Please input a valid one: "+"\u001B[0m");
                        option = in.nextInt();
                    }
                    if (option == 1) {
                        ig.duplicate();
                        //System.out.println(ig.getInvoices());
                    }
                    if (option == 2) {
                        ig.orderInvoices();
                    }
                    if (option == 3) {
                        System.out.println("Give me the text on which you want to do the search:");
                        String s1 = in.nextLine();
                        s1 = in.nextLine();
                        while (s1.length() < 3) {
                            System.out.println("Your text should have at least 3 character. Please input another one!");
                            s1 = in.nextLine();
                        }
                        ig.searchInvoice(s1);
                    }
                    if (option == 4) {
                        System.out.println("Give me the id of the invoice you want to pay, please: ");
                        int id = in.nextInt();
                        ig.payInvoice(id);
                    }
                    if(option==5){
                        for (Invoice inv : ig.getInvoices()) {
                            System.out.println("\u001B[33m" + "______________________________" + "\u001B[0m");
                            System.out.println("Id:"+inv.getId());
                            System.out.println("_______________");
                            System.out.println("Seller:");
                            for (Company c : inv.getSeller())
                                System.out.println(c);
                            System.out.println("_______________");
                            System.out.println("Dates:");
                            System.out.println(inv.getDueDate() + "\n" + inv.getPayDate());
                            System.out.println("_______________");
                            System.out.println("Products:");
                            for (Product p : inv.getProducts())
                                System.out.println(p);
                            System.out.println("\u001B[33m" + "______________________________" + "\u001B[0m");
                        }
                    }
                    if (option == 0)
                        break;
                }
            }
            if (command == 2) {
                FileReaderCompany frc = new FileReaderCompany("C:\\Users\\Smart_Slim\\IdeaProjects\\Invoice Manager\\src\\companies.txt");
                System.out.println("The number of companies wanted to be generated: ");
                int nc = in.nextInt();
                System.out.println("Give me the number of the products wanted to be generated: ");
                int np = in.nextInt();
                System.out.println("Give me the number of the invoices wanted to be generated: ");
                int ni = in.nextInt();
                InvoiceGenerator ig = new InvoiceGenerator(frc.getNames(), nc, np, ni);

                while (true) {
                    System.out.println("Available options: \n");
                    persistedDataMenu();
                    System.out.println("Give me an option: ");
                    int option = in.nextInt();
                    while (option < 0 || option > 5) {
                        System.out.println("\u001B[31m"+"Invalid command!Please input a valid one: "+"\u001B[0m");
                        option = in.nextInt();
                    }
                    if (option == 1) {
                        ig.duplicate();
                        //System.out.println(ig.getInvoices());
                    }
                    if (option == 2) {
                        ig.orderInvoices();
                    }
                    if (option == 3) {
                        System.out.println("Give me the text on which you want to do the search:");
                        String s = in.nextLine();
                        s = in.nextLine();
                        while (s.length() < 3) {
                            System.out.println("Your text should have at least 3 character. Please input another one!");
                            s = in.nextLine();
                        }
                        ig.searchInvoice(s);
                    }
                    if (option == 4) {
                        System.out.println("Give me the id of the invoice you want to pay, please: ");
                        int id = in.nextInt();
                        ig.payInvoice(id);
                    }
                    if(option==5){
                        for (Invoice inv : ig.getInvoices()) {
                            System.out.println("\u001B[33m" + "______________________________" + "\u001B[0m");
                            System.out.println("Id:"+inv.getId());
                            System.out.println("_______________");
                            System.out.println("Seller:");
                            for (Company c : inv.getSeller())
                                System.out.println(c);
                            System.out.println("_______________");
                            System.out.println("Dates:");
                            System.out.println(inv.getDueDate() + "\n" + inv.getPayDate());
                            System.out.println("_______________");
                            System.out.println("Products:");
                            for (Product p : inv.getProducts())
                                System.out.println(p);
                            System.out.println("\u001B[33m" + "______________________________" + "\u001B[0m");
                        }
                    }
                    if (option == 0)
                        break;
                }
            }
            if(command==0)
                break;
        }
    }
}
