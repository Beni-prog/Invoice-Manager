package Generator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReaderCompany {
    private ArrayList<String> names=new ArrayList<>();

    public FileReaderCompany(String filename) {
        this.readNames(filename);
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void readNames(String filename)
    {
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(filename));
            String line = null;
            while ((line = br.readLine()) != null)
            {
                String[] elems = line.split(",");
                int i=0;
                while(i<8) {
                    this.names.add(elems[i]);
                    i++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br != null)
                try {
                    br.close();
                }
                catch (IOException e)
                {
                    System.out.println("Error while closing the file " + e);
                }
        }


    }
}
