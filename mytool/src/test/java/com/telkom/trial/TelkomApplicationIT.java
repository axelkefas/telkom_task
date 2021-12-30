package com.telkom.trial;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Slf4j
public class TelkomApplicationIT {
    @Test
    public void testParse(){
        String s="[18-Aug-2021 07:31:55 UTC] PHP Fatal error:  Allowed memory size of 134217728 bytes exhausted (tried to allocate 1753088 bytes) in /home/martinmu/public_html/application/controllers/Master.php on line 55";
        System.out.println(s.substring(s.indexOf(": ")+1,s.indexOf(" in")));
        System.out.println(s.substring(s.indexOf(" in ")+3,s.indexOf(" on line ")));
        System.out.println(s.substring(s.indexOf(" on line ")+8));
    }
    @Test
    public void tulisFile() throws Exception{
        File file=new File("/home/danang/tulis.log");
        BufferedWriter writer=new BufferedWriter(new FileWriter(file));
        writer.write("Hello world");
        writer.newLine();
        writer.write("apa");
        writer.flush();
        writer.close();
    }
}
