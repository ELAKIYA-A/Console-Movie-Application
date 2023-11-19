package com.mycompany.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

class Main{
    static String Movie_path= "C:\\Users\\elaki\\OneDrive\\Documents\\NetBeansProjects\\Main\\src\\main\\java\\com\\mycompany\\main\\Movies.text";
    static String temp_path="C:\\Users\\elaki\\OneDrive\\Documents\\NetBeansProjects\\Main\\src\\main\\java\\com\\mycompany\\main\\temp.text";
    static Scanner sc = new Scanner(System.in);
    public static void ShowMovies(){
        try {
            File myObj = new File(Movie_path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void AddMovie(){
        String name,year,director,lang,rating;
        System.out.println("Enter Movie Name");
        name = sc.nextLine();
        System.out.println("Enter Release Year");
        year = sc.nextLine();
        System.out.println("Enter Director Name");
        director = sc.nextLine();
        System.out.println("Enter Movie Language");
        lang = sc.nextLine();
        System.out.println("Enter Movie Rating");
        rating = sc.nextLine();
        String data = String.format("%-20s|%20s | %8s |%6s  | %6s\n",name,director,lang,year,rating);
        try{
            FileWriter myWriter = new FileWriter(Movie_path,true);
            myWriter.write(data);
            System.out.println("MOVIE ADDED SUCCESSFULLY");
            myWriter.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
         
    }
    public static void DeleteMovie(){
        try {
            boolean fl = true;
            System.out.println("Enter the Movie name you want to Delete:::");
            String name = sc.nextLine();
            File movie = new File(Movie_path);
            String path = movie.getAbsolutePath();
            File MovieFile = new File(path);
            File temp = new File(temp_path);
            FileWriter myWriter = new FileWriter(temp,true);
            Scanner readScanner = new Scanner(movie);
            while (readScanner.hasNextLine()){
                String data = readScanner.nextLine();
                String[] dataSet = data.split("[|]");
                if((dataSet[0].trim()).equalsIgnoreCase(name.trim())){
                    fl = false;
                    continue;
                }
                myWriter.write(data+"\n");
            }
            if(fl){
                System.out.println("MOVIE NOT FOUND");
            }else{
                System.out.println("DELETED SUCCESSFULLY");
            }
            myWriter.close();
            readScanner.close();
            MovieFile.delete();
            temp.renameTo(MovieFile);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static String[] Search(String name){
        String[] a = new String[5];
        try{
            File movie = new File(Movie_path);
            Scanner readScanner = new Scanner(movie);
            while (readScanner.hasNextLine()){
                String data = readScanner.nextLine();
                String[] dataSet = data.split("[|]");
                if((dataSet[0].trim()).equalsIgnoreCase(name.trim())){
                    for(int i=0;i<5;i++){
                        a[i] = dataSet[i].trim();
                    }
                }
            }
            readScanner.close();

        }
        catch(Exception e){
            System.out.println("Error");
        }
        return a;
    }
    public static void UpdateMovie(String Moviename){
        try {
            boolean fl = true;
            File movie = new File(Movie_path);
            String path = movie.getAbsolutePath();
            File MovieFile = new File(path);
            File temp = new File(temp_path);
            FileWriter myWriter1 = new FileWriter(temp,true);
            Scanner readScanner1 = new Scanner(movie);
            int Confirm=0;
            while (readScanner1.hasNextLine()){
                String data = readScanner1.nextLine();
                String[] dataSet = data.split("[|]");
                if((dataSet[0].trim()).equalsIgnoreCase(Moviename.trim())){
                    fl = false;
                    boolean change = true;
                    String Dname = dataSet[1].trim(),lang = dataSet[2].trim(),year = dataSet[3].trim(),rating = dataSet[4].trim();

                    while (change) {
                        System.out.println("1.Director Name\n2.Language\n3.Year\n4.Rating\n5.exit\nEnter the datail that need to updated:::");
                    int a = Integer.parseInt(sc.nextLine());
                    switch (a) {
                        case 1:
                            System.out.println("Enter the New director name");
                            Dname = sc.nextLine();
                            break;
                        case 2:
                            System.out.println("Enter the New Language");
                            lang = sc.nextLine();
                            break;
                        case 3:
                            System.out.println("Enter the New Year");
                            year = sc.nextLine();
                            break;
                        case 4:
                            System.out.println("Enter the New Rating");
                            rating = sc.nextLine();
                            break;
                        default:
                            change = false;
                            break;
                    }
                    }
                    System.out.println("Confirm Changes::\n1.YES\n2.NO");
                    Confirm = Integer.parseInt(sc.nextLine());
                    if(Confirm==1){
                        data = String.format("%-20s|%20s | %8s |%6s  | %6s",Moviename,Dname,lang,year,rating);
                    }
                }
                myWriter1.write(data+"\n");
            }
            if(fl){
                System.out.println("MOVIE NOT FOUND");
            }else if(Confirm == 2){
                System.out.println("Canceled");
            }else{
                System.out.println("UPDATED SUCCESSFULLY");

            }
            myWriter1.close();
            readScanner1.close();
            MovieFile.delete();
            temp.renameTo(MovieFile);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        boolean fl = true;
        while (fl) {
            System.out.println("enter your choice:\n1.Show Movies\n2.Add Movie\n3.Delete Movie\n4.search\n5.Update Movie\n6.Exit");
            int num = Integer.parseInt(scan.nextLine());
            switch (num) {
                case 1:
                    System.out.printf("%-20s|%20s | %8s |%6s  | %6s\n","NAME","DIRECTOR","LANGUAGE","YEAR","RATING");
                    System.out.println("=".repeat(70));
                    ShowMovies();
                    break;
                case 2:
                    AddMovie();
                    break;
                case 3:
                    DeleteMovie();
                    break;
                case 4:
                    System.out.println("Enter Movie to be searched");
                    String dat = sc.nextLine();
                    String SearchMovie[] = Search(dat);
                    if(SearchMovie[0]==null){
                        System.out.println("Movie Not Found");
                    }else{ 
                        System.out.printf(
                            "%-10s: "+SearchMovie[0]
                        +"\n%-10s: "+SearchMovie[1]
                        +"\n%-10s: "+SearchMovie[2]
                        +"\n%-10s: "+SearchMovie[3]
                        +"\n%-10s: "+SearchMovie[4]+"\n","Name","Director","Language","Year","Rating"
                        );
                    }
                    break;
                case 5:
                    System.out.println("Enter the movie that need to Updated:::");
                    String name = sc.nextLine();
                    UpdateMovie(name);
                    break;
                default:
                    fl = false;
                    break;
            }
            /*
                Filter Movies
            */
            
        }
        scan.close();


        
    }
}