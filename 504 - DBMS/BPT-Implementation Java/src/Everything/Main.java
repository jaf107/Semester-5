package Everything;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<KeyValuePair> dictionary = new ArrayList<>();
    public static int n = 5;

    public static void initDictionary() throws FileNotFoundException {
        File rawDictionary = new File("Translation.txt");
        Scanner sc = new Scanner(rawDictionary);

        while(sc.hasNextLine()){
            String currentLine = sc.nextLine();         //take one line input from the file
            String [] splitString = currentLine.split("[|]",0); //split around |, [] because | is a regex sign
            KeyValuePair keyValuePair = new KeyValuePair(splitString[1],splitString[2]);    //new kvp

            if(keyValuePair.english.contains("."))              //there are some . in the original dictionary instead of <spaces>
                keyValuePair.english = keyValuePair.english.replace(".", " ");

            if(dictionary.size()!=0){                           //remove duplicate words if needed
                if(!dictionary.get(dictionary.size()-1).english.equals(keyValuePair.english))
                    dictionary.add(keyValuePair);               //if the previous one isn't equal to this one then add it
            }
            else dictionary.add(keyValuePair);                  //if its the first one then no need
        }
        System.out.println(dictionary.size());                  //just for debugging purposes
    }

    public static void main(String[] args) throws FileNotFoundException {
        initDictionary();                                       //process the raw dictionary

        BPT bpt = new BPT(n-1);                             //create a new bpt

        for (KeyValuePair entry: dictionary) {                 //insert
            bpt.insert(entry.english, entry.bengali);
        }

        for (KeyValuePair kvp: dictionary) {                    //search
            System.out.println(kvp.english+" "+bpt.search(kvp.english));
        }

        Scanner scanner = new Scanner(System.in);               //custom search
        String s = scanner.nextLine();
        System.out.println(bpt.search(s));
    }
}
