package cn.zzx.controller;



import java.io.File;
import java.io.FileInputStream;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException {

        //
       FileInputStream fileInputStream=new FileInputStream(new File("D://1.txt"));
        byte[] ns=new byte[1024*1024];
        int len=-1;
       /* while ((len= fileInputStream.read(ns))!=-1){
            System.out.println(new String(ns,0,len,"UTF-8"));
        }*/
        Scanner scanner=new Scanner(fileInputStream);
        System.out.println(scanner.nextLine());
        System.out.println(scanner.nextLine());
        System.out.println(scanner.nextLine());
        System.out.println(scanner.nextLine());
        System.out.println(scanner.nextLine());
        System.out.println(scanner.nextLine());


    }
}
