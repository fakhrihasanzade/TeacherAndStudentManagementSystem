package org.example.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileRead {

    public static void fileReadFromService(String fileName){

        try{

            FileReader fileReader=new FileReader(fileName);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            System.out.println(bufferedReader.readLine());

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

}
