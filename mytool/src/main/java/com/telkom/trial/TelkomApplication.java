package com.telkom.trial;

import com.telkom.trial.domain.Argument;
import com.telkom.trial.service.Converter;

public class TelkomApplication {
    public static void main(String[] args) {
        try {
            Argument argument=Argument.builder()
                    .source(args[0])
                    .build();
            String s=args[1];
            if (s!=null){
                insertion(argument,s,args[2]);
            }
            if (args.length>=4){
                s=args[3];
                if (s!=null){
                    insertion(argument,s,args[4]);
                }
            }
            new Converter(argument);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Parameter yang dibutuhkan untuk program ini [source param] \n -t untuk konversi ke dalam format json atau plain text" +
                    "\n -o untuk membuat output file");
        }
    }
    private static void insertion(Argument argument,String s,String v){
        if (s.equalsIgnoreCase("-t")){
            argument.setType(v);
        }else {
            argument.setOutput(v);
        }
    }
}
