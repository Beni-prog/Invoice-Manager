package Generator;

import Domain.Company;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class CompanyGenerator{
    private ArrayList<Company> companies=new ArrayList<>();

    public CompanyGenerator(ArrayList<String> names,int n1) {
        this.constructCompanies(names,n1);
    }

    private void constructCompanies(ArrayList<String> names, int n1) {
        int [][] a=new int [8][8] ;
        Random rd=new Random();
        //here I fill up my matrix with 0
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                a[i][j]=0;
            }
        //here I generate some numbers and I mark those pairs by putting 1 in that matrix cell
        //I do this because of the uniqueness of the names in companies
        for(int i=0;i<n1;i++)
            {
                int x=rd.nextInt(8);
                int y=rd.nextInt(8);
                while(x==y)
                     y=rd.nextInt(8);
                if(a[x][y]==0)
                    a[x][y]=1;
                else
                    i--;
            }
        ArrayList<String> fullNames=new ArrayList<>();
        //here I form the full names of the companies
        int k=0;
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(a[i][j]==1)
                {
                    if (k >= 3)
                    {
                        fullNames.add(names.get(i) + " " + names.get(j));
                    }
                    else
                        {
                            //here I put the third name for 3 companies
                            int elem=rd.nextInt(8);
                            while(i==elem || j==elem)
                                 elem=rd.nextInt(8);//because I can't have 2 identical names in one company name
                            fullNames.add(names.get(i) + " " + names.get(j) + " " + names.get(elem));
                            k++;
                        }
                }
        //here I generate phone numbers
        ArrayList<Long> numbers=new ArrayList<>();
        for(int i=0;i<n1;i++)
        {
            long x=1;
            for(int j=1;j<14;j++)
            {
                int y=rd.nextInt(10);
                x=x*10+y;
            }
            numbers.add(x);
        }
        //here I form those n1 companies
        for(int i=0;i<n1;i++)
            companies.add(new Company(fullNames.get(i), numbers.get(i).toString()));

    }
    public  ArrayList<Company> getCompanies(){return  this.companies;}
}
