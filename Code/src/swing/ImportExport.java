package swing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ImportExport {
    public static void main (String [] args){
        ArrayList<ArrayList<String>> customer = importCSV("C:\\Users\\Vanness\\Downloads\\assignmentFiles\\vehicle.csv");
        for(int i=0;i<customer.size();i++){
            for(int j=0;j<customer.get(i).size();j++)
                System.out.print(customer.get(i).get(j) + "|");
            System.out.println();
        }
//        ImportExport.ExportCSV(customer, "C:\\Users\\Vanness\\Downloads\\customer2.csv");
    }
    public static ArrayList<ArrayList<String>> importCSV (String filePath){
        ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
        try{ 
            FileReader fr = new FileReader (filePath);
            BufferedReader bf = new BufferedReader (fr);
            
            // Initialize the 2d array with every element
            String line;
            int length=0;
            for(int i=0;(line=bf.readLine()) != null;i++){
                String [] elements = line.split(",");
                if(i==0)
                    length=elements.length;
                temp.add(new ArrayList<>(Arrays.asList(elements)));
                if(temp.get(i).size()<length)
                    temp.get(i).add("");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File was not found");
        }
        catch(IOException e){
            System.out.println("Problem with file output");
        }
        return temp;
    }
    
    public static String pathStorage (int pathCode){
        Scanner sc = new Scanner (System.in);
        while (pathCode <0 || pathCode >3)
            pathCode = sc.nextInt();
        sc.close();
        switch(pathCode){
            case 0:
                return "C:\\Users\\Vanness\\Downloads\\assignmentFiles\\cust.csv";
            case 1:
                return "C:\\Users\\Vanness\\Downloads\\assignmentFiles\\sales.csv";
            case 2:
                return "C:\\Users\\Vanness\\Downloads\\assignmentFiles\\employee.csv";
            default:
                return "C:\\Users\\Vanness\\Downloads\\assignmentFiles\\vehicle.csv";
        }
    }
    public static void ExportCSV (ArrayList<ArrayList<String>> temp,String filePath){
        try{
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<temp.size();i++){
                for(int j=0;j<temp.get(i).size();j++){
                    if(temp.get(i).get(j) == null){
                        sb.append("");
                        break;
                    }
                    sb.append(temp.get(i).get(j));
                    if(j == temp.get(i).size()-1)
                        break;
                    sb.append(",");
                }
                sb.append("\n");
            }
            bw.write(sb.toString());
            bw.close();
        }
        catch(IOException e){
        }
    }
}
