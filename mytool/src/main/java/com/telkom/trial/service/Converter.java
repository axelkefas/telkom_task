package com.telkom.trial.service;

import com.telkom.trial.domain.Argument;
import com.telkom.trial.domain.ErrorLog;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Converter {
    private final static String PATTERN_DEFAULT="";
    private final Argument argument;
    public Converter(Argument argument) throws IllegalAccessException, IOException {
        this.argument=argument;
        initData();
    }
    //TODO check file
    private void initData() throws IllegalAccessException, IOException {
        File file= new File(argument.getSource());
        if (!file.exists()){
            throw new IllegalArgumentException("File tidak ditemukan");
        }
        File out=null;
        BufferedWriter writer=null;
        if (!argument.getOutput().equalsIgnoreCase("console")){
            out = new File(argument.getOutput());
            if (!out.exists()){
                out.mkdirs();
            }else {
                out.delete();
            }
            try {
                writer=new BufferedWriter(new FileWriter(out));
            }catch (IOException e){
                e.printStackTrace();
                throw new IllegalAccessException("cant create or write to file");
            }
        }
        System.out.println("file ditemukan, start baca");
        try {
            Scanner scanner=new Scanner(new InputStreamReader(new FileInputStream(file)));
            while (scanner.hasNextLine()){
                String s=scanner.nextLine();
                String dateStr=s.substring(s.indexOf("[")+1,21);
                ErrorLog errorLog=ErrorLog.builder()
                        .localDateTime(dateStr!=null?LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss")):null)
                        .title(s.substring(s.indexOf("] ")+2, s.indexOf(": ")))
                        .message(s.substring(s.indexOf(": ")+1,s.indexOf(" in")))
                        .location(s.substring(s.indexOf(" in ")+3,s.indexOf(" on line ")))
                        .line(s.substring(s.indexOf(" on line ")+8))
                        .build();
                if (argument.getOutput().equalsIgnoreCase("console")){
                    if (argument.getType().equalsIgnoreCase("json")){
                        System.out.println(errorLog.toJSON());
                    }else {
                        System.out.println(errorLog.toString());
                    }
                }else {
                    System.out.println(errorLog.toJSON());
                    if (argument.getType().equalsIgnoreCase("json")){
                        writer.write(errorLog.toJSON().toString());
                    }else {
                        writer.write(errorLog.toString());
                    }
                    writer.newLine();
                }
            }

            System.out.println("program selesai");
        }catch (FileNotFoundException e){
            e.printStackTrace();
            throw new IllegalArgumentException("File not found");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("cannot write in to the file "+argument.getOutput());
        }finally {
            if (!argument.getOutput().equalsIgnoreCase("console")){
                writer.flush();
                writer.close();
            }
        }

    }

}
