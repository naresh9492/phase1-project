package com.locked.me;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LockedMe {
    static String RECORD;
    File folderName;

    public LockedMe() {
        RECORD = System.getProperty("user.dir");
        folderName = new File(RECORD+"/files");
        if (!folderName.exists())
            folderName.mkdirs();
        System.out.println("RECORD : "+ folderName.getAbsolutePath());
    }

    private static final String WELCOME_PROMPT =
            "\n*****************  LockedMe.com *******************"+
                    "\n***************** NARESH KURUBA *******************\n";

    private static final String MAIN_MENU =
            "\nMAIN MENU - Select Your Option: \n"+
                    "1.  List files in directory\n"+
                    "2.  Busines Level Operations\n"+
                    "3.  Exit Program";

    private static final String SECONDARY_MAIN_MENU =
            "   \nSelect Your Option: \n"+
                    "   1. Add a file\n"+
                    "   2. Delete a file\n"+
                    "   3. Search a file\n"+
                    "   4. GoBack";

    void showPrimaryMenu() {
        System.out.println(MAIN_MENU);
        try{
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice){
                case 1 : {
                    showFiles();
                    showPrimaryMenu();
                }
                case 2 : {
                    showSecondaryMenu();
                }
                case 3 : {
                    System.out.println("Thank You");
                    System.exit(0);
                }
                default: showPrimaryMenu();
            }
        }
        catch (Exception e){
            System.out.println("Please enter 1, 2 or 3");
            showPrimaryMenu();
        }
    }

    void showSecondaryMenu() {
        System.out.println(SECONDARY_MAIN_MENU);
        try{
            Scanner scanner = new Scanner(System.in);
            char[] input = scanner.nextLine().toLowerCase().trim().toCharArray();
            char option = input[0];

            switch (option){
                case '1' : {
                    System.out.print("↳ Adding a file...Please Enter a File Name : ");
                    String filename = scanner.next().trim().toLowerCase();
                    addFile(filename);
                    break;
                }
                case '2' : {
                    System.out.print("↳ Deleting a file...Please Enter a File Name : ");
                    String filename = scanner.next().trim();
                    deleteFile(filename);
                    break;
                }
                case '3' : {
                    System.out.print("↳ Searching a file...Please Enter a File Name : ");
                    String filename = scanner.next().trim();
                    searchFile(filename);
                    break;
                }
                case '4' : {
                    System.out.println("Going Back to MAIN menu");
                    showPrimaryMenu();
                    break;
                }
                default : System.out.println("Please enter 1, 2, 3 or 4");
            }
            showSecondaryMenu();
        }
        catch (Exception e){
            System.out.println("Please enter 1, 2, 3 or 4");
            showSecondaryMenu();
        }
    }

    void showFiles() {
        if (folderName.list().length==0)
            System.out.println("The folder is empty");
        else {
            String[] list = folderName.list();
            System.out.println("The files in "+ folderName +" are :");
            Arrays.sort(list);
            for (String str:list) {
                System.out.println(str);
            }
        }
    }

    void addFile(String fileName) throws IOException {
        File filepath = new File(folderName +"/"+fileName);
        String[] list = folderName.list();
        for (String file: list) {
            if (fileName.equalsIgnoreCase(file)) {
                System.out.println("File " + fileName + " already exists at " + folderName);
                return;
            }
        }
        filepath.createNewFile();
        System.out.println("File "+fileName+" added to "+ folderName);
    }

    void deleteFile(String fileName) {
        File filepath = new File(folderName +"/"+fileName);
        String[] list = folderName.list();
        for (String file: list) {
            if (fileName.equals(file) && filepath.delete()) {
                System.out.println("File " + fileName + " deleted from " + folderName);
                return;
            }
        }
        System.out.println("Delete Operation failed. FILE NOT FOUND");
    }

    void searchFile(String filename) {
        String[] list = folderName.list();
        for (String file: list) {
            if (filename.equals(file)) {
                System.out.println("FOUND : File " + filename + " exists at " + folderName);
                return;
            }
        }
        System.out.println("File NOT found (FNF)");
    }

    public static void main(String[] args) {
        System.out.println(WELCOME_PROMPT);
        LockedMe menu = new LockedMe();
        menu.showPrimaryMenu();
    }
}