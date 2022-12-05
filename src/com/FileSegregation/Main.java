package com.FileSegregation;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;

public class Main {

    public static DateTimeFormatter datetimef = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    static LocalDateTime nowtime = LocalDateTime.now();

    public static void main(String[] args) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();

//        System.out.println(dtf.format(now));

        String fileName = "LogFiles\\";
//        System.out.println(fileName);
        File f = new File(fileName);
        if(!f.exists()){
            f.mkdirs();
        }
        String mainPath = fileName + dtf.format(now) + ".txt";
        FileOutputStream fo = new FileOutputStream(mainPath);

        System.setOut(new PrintStream(fo));

        String input;

        String[] st = {"Specific File", "Individual File"};

//        Icon ic = new ImageIcon()

        int n = JOptionPane.showOptionDialog(null, "Choose your Operation:", "File Segregator", 0, 3, null, st, st[0]);

//        System.out.println(n);

        if(n == 0){ // Specific File
            input = JOptionPane.showInputDialog(null, "Enter Your File Extension:", ".pdf");
            String des;
            String src;
            if(!input.isEmpty()) {
                src = JOptionPane.showInputDialog(null, "Enter the Source Path:");
                if(!src.isEmpty()) {
                    des = JOptionPane.showInputDialog(null, "Enter the Destination Path:");
                    specificFile(input,src,des);
                }
            }
            else {
                specificFile(input);
            }



        }
        else if(n == 1){ // Individual Files
            input = JOptionPane.showInputDialog(null, "Enter the Source Path:");
            individualFile(input);
        }



    }

    public static void specificFile(String input) throws IOException {
        String src = "D:\\COURSES\\BTECH\\PROJECT\\FileSegregation\\GitIgnore\\Unsegregated";
        String des = "D:\\COURSES\\BTECH\\PROJECT\\FileSegregation\\GitIgnore\\Segregated";
        specificFile(input,src,des);
    }

    public static void specificFile(String input, String src, String des) throws IOException {
        String Source = src;
//        System.out.println(Source);
//        System.out.println(des);
        File directoryPath = new File(Source);
        FilenameFilter textFilefilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(input)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        //List of all the text files
        String filesList[] = directoryPath.list(textFilefilter);
//        System.out.println(filesList.length);
        // System.out.println("List of the text files in the specified directory:");

//        System.out.println(Arrays.toString(filesList));
        for (String fileName : filesList) {
//            System.out.println("FileName: " + fileName);
            String sr = Source + "\\" + fileName;
//            System.out.println("S: " + Source);
            System.out.println(datetimef.format(nowtime) + ": Moving File = " + sr);
            String target = des + "\\" + fileName;
//            System.out.println("D: " + des);
            System.out.println(datetimef.format(nowtime) + ": Moved File = " + target + "\n\n");
            File targetCursor = new File(target);
            if(!targetCursor.exists()){
                Path temp = Files.move(Paths.get(sr), Paths.get(target));
            }
//            System.out.println(des);
//            File file1 = new File(Source);
//            File file2 = new File(des);
//            boolean bool1 = Source.contains(input);
////            System.out.println(bool1);
//            boolean bool = file1.renameTo(file2);
////            System.out.println(bool);
//            if (bool1) {
//                if (bool) {
//                    System.out.println("Succesfully");
//                }
//            } else if (bool) {
//                System.out.println("Not Succesfully");
//            }
       /*  boolean bool = file1.renameTo(file2);
        System.out.println(bool);
        if(bool){
            System.out.println("Succesfully");
        } */
//            System.out.println(fileName);
        }
    }

    public static void individualFile(String input) throws Exception {
        String Source = input + "\\";
        File directoryPath = new File(Source);

        String filesList[] = directoryPath.list();
//        System.out.println(filesList.length);

        TreeMap<String, ArrayList<String>> code = new TreeMap<>();

        ArrayList<String> arrStr = new ArrayList<>();

        for (String fileName : filesList) {
            String[] result = fileName.split("\\.");
//            System.out.println(Arrays.toString(result));
            if(result.length > 1 && !arrStr.contains(result[1])){
                arrStr.add(result[1]);
            }
//            System.out.println(arrStr);
        }

        for(String matchStr : arrStr) {
            String src = "D:\\COURSES\\BTECH\\PROJECT\\FileSegregation\\GitIgnore\\Unsegregated\\";
            String path = src + matchStr;
            File theDir = new File(path);
            if (!theDir.exists()){
                 theDir.mkdirs();
            }
            for(String fileName : filesList){
                String dotExt = "." + matchStr;
//                System.out.println(fileName);
                if(fileName.endsWith(dotExt)){
//                    System.out.println("RUNNING DOT");
                    System.out.println(datetimef.format(nowtime) + ": Moving File = " + src + fileName);
                    System.out.println(datetimef.format(nowtime) + ": Moved File = " + src + matchStr + "\\" + fileName + "\n");
                    String sour = src + fileName;
                    String target = src + matchStr + "\\" + fileName;
                    File targetCursor = new File(target);
                    if(!targetCursor.exists()){
                        Path temp = Files.move(Paths.get(sour), Paths.get(target));
                    }
//                    Path temp = Files.move(Paths.get(src + fileName), Paths.get(src + matchStr + "\\" + fileName));
                }
            }
        }

//        System.out.println(arrStr);

////        System.out.println("Individual File Executed");
//        File theDir = new File("D:\\COURSES\\HELLO");
//
//
//
//        if (!theDir.exists() && theDir.mkdirs()){
//
//        }
//        else {
//
//        }
    }
}
