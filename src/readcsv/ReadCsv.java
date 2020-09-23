
package readcsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ReadCsv {

    private static <T> ArrayList removeDuplicates(ArrayList<T> list) {
        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();

        // Traverse through the first list
        for (T element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }

    private static void read_data(String path) {
        String filepath = path;
        File file = new File(filepath);
        /*
        balance[0] for bank number 1
        balance[1] for bank number 2
        balance[2] for bank number 3
        balance[3] for bank number 4
         */
        double balance[] = {0.0, 0.0, 0.0, 0.0};
        List<String> list = new ArrayList<String>();

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            PrintWriter pw = new PrintWriter("new_data.txt");
            Object[] rows = bf.lines().toArray();
            for (int i = 0; i < rows.length; i++) {
                String line = rows[i].toString().trim();
                String[] datai = line.split(",");
                list.add(datai[0]);
                String bank = datai[0];
                switch (bank) {
                    case "00001500205568600":
                        if (datai[1].equals("C")) {
                            balance[0] += Double.parseDouble(datai[2]);
                        } else {
                            balance[0] -= Double.parseDouble(datai[2]);
                        }
                        break;
                    case "00001300220978215":
                        if (datai[1].equals("C")) {
                            balance[1] += Double.parseDouble(datai[2]);
                        } else {
                            balance[1] -= Double.parseDouble(datai[2]);
                        }
                        break;
                    case "00002200540006410":
                        if (datai[1].equals("C")) {
                            balance[2] += Double.parseDouble(datai[2]);
                        } else {
                            balance[2] -= Double.parseDouble(datai[2]);
                        }
                        break;
                    case "00009650025500020":
                        if (datai[1].equals("C")) {
                            balance[3] += Double.parseDouble(datai[2]);
                        } else {
                            balance[3] -= Double.parseDouble(datai[2]);
                        }
                        break;
                    default:
                        break;
                }

            }
            ArrayList<String> newlist = removeDuplicates((ArrayList) list);
            for (int i = 0; i < newlist.size(); i++) {
                System.out.println(newlist.get(i) + "\t" + balance[i]);
                pw.println(newlist.get(i) + "\t\t" + balance[i]);

            }
            pw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        //read csv file
        read_data("C:\\Users\\malnsour\\Desktop\\New folder\\Read.Csv\\data.txt");
    }
}
