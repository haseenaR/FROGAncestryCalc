/**
MIT License

Copyright (c) 2019 haseenaR

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
**/
/**
 * The ValidateFileHeader class implements validation of input file.
 * Version 1.0
 * Created by Haseena Rajeevan.
 */
/**
 * The ValidateFileHeader class implements validation of input file.
 * Version 1.0
 * Created by Haseena Rajeevan.
 */
package dv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import static main.ComputeBatchAnalysis.getKey;
import read.ReadTxtFiles;

import write.WriteToOutputFile;

/**
 *
 * @author hr65
 */
public class ValidateFileHeader {
    public static int validateHeaders55(String homePath,String inputFile, Hashtable<Integer, String> siteOrderHash) {

        String individual = "";
        String record = null;
        int valid = 0;
        int index = 0;
        int validity = 0;
        String stringG[];
        StringBuffer typingInfo = new StringBuffer();
        int orderErr = 0;
        String rsnum = "";
        String siteOrderRS = "";
        String errFile = "errFile.txt";
        String workingLog="workingLog.txt";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Validating input file.", workingLog);
                        
        try {

            FileReader fr = new FileReader(homePath+"input/" + inputFile);
            BufferedReader br = new BufferedReader(fr);
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                if (index == 1) {

                    stringG = record.split("\\,");
                    //check count
                    if (stringG.length != 56) {

                        WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Check you input file.\r\nMake sure it is comma delimited.\r\nMake sure all the 55 rsnumbers are listed.", errFile);
                        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Check you input file.\r\nMake sure it is comma delimited.\r\nMake sure all the 55 rsnumbers are listed.", workingLog);
                        
                        if (record.indexOf(',') == -1) {

                            WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Your input file is not comma delimited.\r\n ", errFile);
                            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - - Your input file is not comma delimited.\r\n", workingLog);
                        
                        } else {

                            if (countOccurrences(record, ',') < 55) {
                                WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - You are either missing columns or de-limiting commas in your input file.\r\n ", errFile);
                                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - You are either missing columns or de-limiting commas in your input file.\r\n", workingLog);
                        
                            }
                        }
                        System.exit(index);
                    } else {
                        individual = stringG[0].trim();

                        for (int rs = 1; rs < stringG.length; rs++) {

                            rsnum = stringG[rs].trim();                            
                            siteOrderRS = siteOrderHash.get(rs).toString();                            
                            if (!rsnum.equals(siteOrderRS)) {
                                orderErr = 1;
                                
                                WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Issue with rsnumber order ", errFile);
                                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Issue with rsnumber order.\r\n", workingLog);
                        
                                System.exit(rs);
                            } else {

                            }
                        }
                    }

                }

            }
            if (orderErr == 1) {
                validity = 0;
            } else {
                validity = 1;
            }

        } catch (IOException e) {
            // catch possible io errors from readLine()
            System.out.println("IOException - "+e.getMessage());
            
        }
        return validity;
    }

    public static int validateHeaders128(String homePath,String inputFile, Hashtable<Integer, String> siteOrderHash) {

        String individual = "";
        String record = null;
        int valid = 0;
        int index = 0;
        int validity = 0;
        String stringG[];
        StringBuffer typingInfo = new StringBuffer();
        int orderErr = 0;
        String rsnum = "";
        String siteOrderRS = "";
        String errFile = "errFile.txt";
        String workingLog="workingLog.txt";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Validating input file.", workingLog);
                        
        try {

            FileReader fr = new FileReader(homePath+"input/" + inputFile);
            BufferedReader br = new BufferedReader(fr);
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                if (index == 1) {

                    stringG = record.split("\\,");
                    //check count
                    if (stringG.length != 129) {
                        WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Check you input file.\r\nMake sure it is comma delimited.\r\nMake sure all 128 rsnumbers are listed.", errFile);
                        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Check you input file.\r\nMake sure it is comma delimited.\r\nMake sure all 128 rsnumbers are listed.", workingLog);
                        
                        if (record.indexOf(',') == -1) {

                            WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Your input file is not comma delimited.\r\n ", errFile);
                            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error- Your input file is not comma delimited.\r\n ", workingLog);

                        } else {

                            if (countOccurrences(record, ',') < 128) {
                                WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - You are either missing columns or de-limiting commas in your input file.\r\n ", errFile);
                                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - You are either missing columns or de-limiting commas in your input file.\r\n ", workingLog);

                            }
                        }
                        System.exit(index);
                    } else {
                        individual = stringG[0].trim();

                        for (int rs = 1; rs < stringG.length; rs++) {

                            rsnum = stringG[rs].trim();                            
                            siteOrderRS = siteOrderHash.get(rs).toString();                            
                            if (!rsnum.equals(siteOrderRS)) {
                                orderErr = 1;
                                WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Issue with rsnumber order ", errFile);
                                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Issue with rsnumber order.\r\n ", workingLog);

                                System.exit(rs);
                            } else {

                            }
                        }
                    }

                }

            }
            if (orderErr == 1) {
                validity = 0;
            } else {
                validity = 1;
            }

        } catch (IOException e) {
            // catch possible io errors from readLine()
            System.out.println("IOException - "+e.getMessage());
            
        }
        return validity;
    }
public static int validateHeaders34(String homePath,String inputFile, Hashtable<Integer, String> siteOrderHash) {

        String individual = "";
        String record = null;
        int valid = 0;
        int index = 0;
        int validity = 0;
        String stringG[];
        StringBuffer typingInfo = new StringBuffer();
        int orderErr = 0;
        String rsnum = "";
        String siteOrderRS = "";
        String errFile = "errFile.txt";
        String workingLog="workingLog.txt";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Validating input file..  ",workingLog);
        try {

            FileReader fr = new FileReader(homePath+"input/" + inputFile);
            BufferedReader br = new BufferedReader(fr);
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                if (index == 1) {

                    stringG = record.split("\\,");
                    //check count
                    if (stringG.length != 35) {

                        WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Check you input file.\r\nMake sure it is comma delimited.\r\nMake sure all 34 rsnumbers are listed.", errFile);
                        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Check you input file.\r\nMake sure it is comma delimited.\r\nMake sure all 34 rsnumbers are listed.  ",workingLog);
                        if (record.indexOf(',') == -1) {

                            WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Your input file is not comma delimited.\r\n ", errFile);
                            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Your input file is not comma delimited.  ",workingLog);
                        
                        } else {

                            if (countOccurrences(record, ',') < 34) {
                                WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - You are either missing columns or de-limiting commas in your input file.\r\n ", errFile);
                                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - You are either missing columns or de-limiting commas in your input file.  ",workingLog);
                        
                            }
                        }
                        System.exit(index);
                    } else {
                        individual = stringG[0].trim();

                        for (int rs = 1; rs < stringG.length; rs++) {

                            rsnum = stringG[rs].trim();                            
                            siteOrderRS = siteOrderHash.get(rs).toString();                            
                            if (!rsnum.equals(siteOrderRS)) {
                                orderErr = 1;
                                WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Issue with rsnumber order ", errFile);
                                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Issue with rsnumber order.  ",workingLog);
                        
                                System.exit(rs);
                            } else {

                            }
                        }
                    }

                }

            }
            if (orderErr == 1) {
                validity = 0;
            } else {
                validity = 1;
            }

        } catch (IOException e) {
            // catch possible io errors from readLine()
            System.out.println("IOException - "+e.getMessage());
            
        }
        return validity;
    }
public static int validateHeadersCombined(String homePath,String inputFile, Hashtable<Integer, String> siteOrderHash) {

        String individual = "";
        String record = null;
        int valid = 0;
        int index = 0;
        int validity = 0;
        String stringG[];
        StringBuffer typingInfo = new StringBuffer();
        int orderErr = 0;
        String rsnum = "";
        String siteOrderRS = "";
        String errFile = "errFile.txt";
        String workingLog="workingLog.txt";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Validating input file.  ",workingLog);
                        
        try {

            FileReader fr = new FileReader(homePath+"input/" + inputFile);
            BufferedReader br = new BufferedReader(fr);
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                if (index == 1) {

                    stringG = record.split("\\,");
                    //check count
                    if (stringG.length != 193) {

                        WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Check you input file.\r\nMake sure it is comma delimited.\r\nMake sure all 192 rsnumbers are listed.", errFile);
                        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Check you input file.\r\nMake sure it is comma delimited.\r\nMake sure all 192 rsnumbers are listed. ",workingLog);
        
                        if (record.indexOf(',') == -1) {

                            WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Your input file is not comma delimited.\r\n ", errFile);
                            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Your input file is not comma delimited. ",workingLog);
        
                        } else {

                            if (countOccurrences(record, ',') < 192) {
                                WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - You are either missing columns or de-limiting commas in your input file.\r\n ", errFile);
                                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - You are either missing columns or de-limiting commas in your input file. ",workingLog);
        
                            }
                        }
                        System.exit(index);
                    } else {
                        individual = stringG[0].trim();

                        for (int rs = 1; rs < stringG.length; rs++) {

                            rsnum = stringG[rs].trim();                            
                            siteOrderRS = siteOrderHash.get(rs).toString();                            
                            if (!rsnum.equals(siteOrderRS)) {
                                orderErr = 1;
                                WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Issue with rsnumber order ", errFile);
                                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Issue with rsnumber order. ",workingLog);
        
                                System.exit(rs);
                            } else {

                            }
                        }
                    }

                }

            }
            if (orderErr == 1) {
                validity = 0;
            } else {
                validity = 1;
            }

        } catch (IOException e) {
            // catch possible io errors from readLine()
            System.out.println("IOException - "+e.getMessage());
            
        }
        return validity;
    }
    public static int countOccurrences(String haystack, char needle) {

        int count = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle) {
                count++;
            }
        }

        return count;
    }

    public static int validateGenotype(String homePath,Hashtable<Integer, String> individualHash, Hashtable<Integer, String> siteOrderHash, Hashtable<Integer, String> siteIdHash, Hashtable<String, Integer> snpGenotypeHT) {
        String individual = "";
        Hashtable<String, String> indGenotype = new Hashtable<String, String>();
        String rsnumber = "";
        int alfredSiteId = 0;
        String genotype = "";
        String genotypeSTR = "";
        int inValidCount = 0;
        String errFile = "errFile.txt";
        String workingLog="workingLog.txt";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        //check genotype for validity
        for (int w = 0; w < individualHash.size(); w++) {

            individual = individualHash.get(w + 1);

            indGenotype = ReadTxtFiles.readGenotypeForRsInd(homePath,"_" + individual + ".out");
            for (int l = 0; l < siteOrderHash.size(); l++) {
                rsnumber = siteOrderHash.get(l + 1);
                alfredSiteId = getKey(siteIdHash, rsnumber);
                genotype = "";
                genotype = indGenotype.get(rsnumber);
                genotypeSTR = "";
                if ((genotype.equals("NN"))) {

                } else {

                    if (confirmGenotype(alfredSiteId, genotype, snpGenotypeHT) > 0) {

                    } else {
                        inValidCount = inValidCount + 1;
                        WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Invalid genotype in the input file-"+rsnumber+ " -"+ genotype + "\r\n ", errFile);
                        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Invalid genotype in the input file-"+rsnumber+ " -"+ genotype + "\r\n ", workingLog);
                    
                    }
                }
            }
        }
        return inValidCount;
    }

    private static int confirmGenotype(int siteId, String genotype, Hashtable<String, Integer> snpGenotypeHT) {

        int confirming = 0;
        StringBuilder snpGenoSB = new StringBuilder();
        snpGenoSB.setLength(0);
        snpGenoSB.append(siteId).append(";").append(genotype);
        String revGenotype = "";

        if (snpGenotypeHT.containsKey(snpGenoSB.toString())) {
            confirming = Integer.parseInt(snpGenotypeHT.get(snpGenoSB.toString()).toString());
        } else {
            revGenotype = getReverseCombo(genotype);
            snpGenoSB.setLength(0);
            snpGenoSB.append(siteId).append(";").append(revGenotype);
            if (snpGenotypeHT.containsKey(snpGenoSB.toString())) {
                confirming = Integer.parseInt(snpGenotypeHT.get(snpGenoSB.toString()).toString());
            } else {
                confirming = 0;
            }
        }
        return confirming;
    }

    private static String getReverseCombo(String genotype) {

        String revString = "";
        if (genotype.equals("GT")) {
            revString = "TG";
        }
        if (genotype.equals("TG")) {
            revString = "GT";
        }
        if (genotype.equals("CT")) {
            revString = "TC";
        }
        if (genotype.equals("TC")) {
            revString = "CT";
        }
        if (genotype.equals("AG")) {
            revString = "GA";
        }
        if (genotype.equals("GA")) {
            revString = "AG";
        }
        if (genotype.equals("AC")) {
            revString = "CA";
        }
        if (genotype.equals("CA")) {
            revString = "AC";
        }
        if (genotype.equals("GC")) {
            revString = "CG";
        }
        if (genotype.equals("CG")) {
            revString = "GC";
        }
        if (genotype.equals("AT")) {
            revString = "TA";
        }
        if (genotype.equals("TA")) {
            revString = "AT";
        }
        if (genotype.equals("NN")) {

            revString = "NN";
        }
        return revString;
    }

    public static int validateHeadersPrecision(String homePath, String inputFilename, Hashtable<Integer, String> siteOrderHash) {
        System.out.println("Validating input file..  ");
        String individual = "";

        String record = null;
        int valid = 0;
        int index = 0;
        int validity = 0;
        String stringG[];
        StringBuffer typingInfo = new StringBuffer();
        int orderErr = 0;
        String rsnum = "";
        String siteOrderRS = "";
        String errFile = "errFile.txt";
        String workingLog="workingLog.txt";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Validating input file.  ",workingLog);
        
        try {

            FileReader fr = new FileReader(homePath+"input/" + inputFilename);
            BufferedReader br = new BufferedReader(fr);
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                if (index == 1) {

                    stringG = record.split("\\,");
                    //check count
                    if (stringG.length != 166) {

                        WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Check you input file.\r\nMake sure it is comma delimited.\r\nMake sure all 165 rsnumbers are listed.", errFile);
                        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Check you input file.\r\nMake sure it is comma delimited.\r\nMake sure all 165 rsnumbers are listed.  ",workingLog);
        
                        if (record.indexOf(',') == -1) {

                            WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Your input file is not comma delimited.\r\n ", errFile);
                            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Your input file is not comma delimited.\r\n ", workingLog);

                        } else {

                            if (countOccurrences(record, ',') < 165) {
                                WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - You are either missing columns or de-limiting commas in your input file.\r\n ", errFile);
                                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - You are either missing columns or de-limiting commas in your input file.\r\n ", workingLog);

                            }
                        }
                        System.exit(index);
                    } else {
                        individual = stringG[0].trim();

                        for (int rs = 1; rs < stringG.length; rs++) {

                            rsnum = stringG[rs].trim();                            
                            siteOrderRS = siteOrderHash.get(rs).toString();                            
                            if (!rsnum.equals(siteOrderRS)) {
                                orderErr = 1;
                                WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Issue with rsnumber order ", errFile);
                                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Issue with rsnumber order ", workingLog);
                                
                                System.exit(rs);
                            } else {

                            }
                        }
                    }

                }

            }
            if (orderErr == 1) {
                validity = 0;
            } else {
                validity = 1;
            }

        } catch (IOException e) {
            // catch possible io errors from readLine()
            System.out.println("IOException - "+e.getMessage());
            
        }
        return validity; }
}
