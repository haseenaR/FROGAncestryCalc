/**
 * This main class reads the properties file, validates the input file,
 * and gets required fields to run the code.
 * Created by Haseena Rajeevan.
 * version 1.0
 * 3/21/2019
 */
package main;

import dv.ValidateFileHeader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;
import read.ReadTxtFiles;
import write.WriteToOutputFile;
import sub.OrderOfMagtitude;
import sub.OrderOfMagtitude;
import sub.RankPopulationLikelihood;

/**
 *
 * @author hr65
 */
public class ComputeBatchAnalysis {

    public static void main(String[] args) {
        String homePath="";
        String inputFilename="";
        String panel="";
        String panelInfo="";
        ArrayList<String> propertiesAL = new ArrayList<String>();
        String[] s;
        propertiesAL= ReadTxtFiles.readProperties();
        homePath=propertiesAL.get(0);
        inputFilename=propertiesAL.get(1);  
        panel=propertiesAL.get(2); 
        String errFile = "errFile.txt";
        String workingLog="workingLog.txt";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        
        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - homePath = "+homePath, workingLog);
        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - inputFilename = "+inputFilename, workingLog);
        WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - panel = "+panel, workingLog);
        String name="";
        //get filename
        if((inputFilename!=null)&&(inputFilename.length()>3)){            
            s=inputFilename.split("\\.");
            name=s[0].trim();            
        }else{
            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Issue with input filename.Make sure it follows the format 'filename.txt' ", workingLog);
            WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Issue with input filename.Make sure it follows the format 'filename.txt'",errFile);
            System.exit(1);
        }  
        if(panel.toUpperCase().equals("55AI")){
        panelInfo="KiddLab - Set of 55 AISNPs";
        Hashtable<Integer, String> siteOrderHash = new Hashtable<Integer, String>();
        siteOrderHash = ReadTxtFiles.readSiteOrderToHashJar(homePath,"Site_55AI.txt");
        Hashtable<Integer, String> siteIdHash = new Hashtable<Integer, String>();
        siteIdHash = ReadTxtFiles.readSiteIdToHashJar(homePath,"Site_55AI.txt");
        //sample order and Id
        Hashtable<Integer, String> sampleOrderHash = new Hashtable<Integer, String>();
        sampleOrderHash = ReadTxtFiles.readSampleOrderToHashJar(homePath,"Sample_55AI.txt");
        Hashtable<Integer, String> sampleIdHash = new Hashtable<Integer, String>();
        sampleIdHash = ReadTxtFiles.readSampleIdToHashJar(homePath,"Sample_55AI.txt");
        //Validate the file header.
        int valid = ValidateFileHeader.validateHeaders55(homePath,inputFilename, siteOrderHash);
        int invalidCount=0;
        
        if (valid == 1) {
           
           WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Validation complete. Starting computation - "+panel, workingLog);
            
            Hashtable<String, Double> referenceDataHash = new Hashtable<String, Double>();
            referenceDataHash = ReadTxtFiles.readReferenceFileForPhenoFreqJar(homePath,"referenceData_55.txt");
            //create separate cellline table for each individual in a indGenotype/ folder in input file
            Hashtable<Integer, String> individualHash = new Hashtable<Integer, String>();
            individualHash = ReadTxtFiles.readInputFileForIndividualGenotype(homePath,inputFilename, siteOrderHash);
            Hashtable<String, Integer> snpGenotypeHT = new Hashtable<String, Integer>();
            snpGenotypeHT = ReadTxtFiles.readSiteGenotypeToHashJar(homePath,"SiteGenotype_55AI.txt");           
            //validate genotype
            invalidCount=ValidateFileHeader.validateGenotype(homePath,individualHash,siteOrderHash,siteIdHash,snpGenotypeHT);
            if(invalidCount==0){
                //now it should be ready to compute
                computeLikelihood(name,homePath,sampleOrderHash, sampleIdHash, siteOrderHash, siteIdHash, individualHash, snpGenotypeHT, referenceDataHash,panelInfo);
                //create order of magnitude
                OrderOfMagtitude.createOrderOfMagnitude(name+"_likelihood.txt",name,homePath,panelInfo);
                //rank order population likelihood
                RankPopulationLikelihood.createPopRankOrder(name+"_likelihood.txt",name,homePath,panelInfo);
                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Computation complete- "+panel, workingLog);
                
            }else {
                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Invalid genotype - check err file to view the errors- "+panel, workingLog);
                
            System.exit(1);
        }
            } else {
            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Input file not valid- check err file to view the errors- "+panel, workingLog);
             
        }
        }
      if(panel.toUpperCase().equals("128AI")){
        panelInfo="Seldin's list of 128 AISNPs";
        Hashtable<Integer, String> siteOrderHash = new Hashtable<Integer, String>();
        siteOrderHash = ReadTxtFiles.readSiteOrderToHashJar(homePath,"Site_128AI.txt");
        Hashtable<Integer, String> siteIdHash = new Hashtable<Integer, String>();
        siteIdHash = ReadTxtFiles.readSiteIdToHashJar(homePath,"Site_128AI.txt");
        //sample order and Id
        Hashtable<Integer, String> sampleOrderHash = new Hashtable<Integer, String>();
        sampleOrderHash = ReadTxtFiles.readSampleOrderToHashJar(homePath,"Sample_128AI.txt");
        Hashtable<Integer, String> sampleIdHash = new Hashtable<Integer, String>();
        sampleIdHash = ReadTxtFiles.readSampleIdToHashJar(homePath,"Sample_128AI.txt");
        //Validate the file header.
        int valid = ValidateFileHeader.validateHeaders128(homePath,inputFilename, siteOrderHash);
        int invalidCount=0;
        
        if (valid == 1) {
           WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Validation complete. Starting compution- "+panel, workingLog);
           
            Hashtable<String, Double> referenceDataHash = new Hashtable<String, Double>();
            referenceDataHash = ReadTxtFiles.readReferenceFileForPhenoFreqJar(homePath,"referenceData_128.txt");
            //create separate cellline table for each individual in a indGenotype/ folder in input file
            Hashtable<Integer, String> individualHash = new Hashtable<Integer, String>();
            individualHash = ReadTxtFiles.readInputFileForIndividualGenotype(homePath,inputFilename, siteOrderHash);
            Hashtable<String, Integer> snpGenotypeHT = new Hashtable<String, Integer>();
            snpGenotypeHT = ReadTxtFiles.readSiteGenotypeToHashJar(homePath,"SiteGenotype_128AI.txt");           
            //validate genotype
            invalidCount=ValidateFileHeader.validateGenotype(homePath,individualHash,siteOrderHash,siteIdHash,snpGenotypeHT);
            if(invalidCount==0){
                //now it should be ready to compute
                computeLikelihood(name,homePath,sampleOrderHash, sampleIdHash, siteOrderHash, siteIdHash, individualHash, snpGenotypeHT, referenceDataHash,panelInfo);
                //create order of magnitude
                OrderOfMagtitude.createOrderOfMagnitude(name+"_likelihood.txt",name,homePath,panelInfo);
                //rank order population likelihood
                RankPopulationLikelihood.createPopRankOrder(name+"_likelihood.txt",name,homePath,panelInfo);
                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Computation complete.. "+panel, workingLog);
            }else {
                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Invalid genotype - check err file to view the errors- "+panel, workingLog);
            
            System.exit(1);
        }
            } else {
            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Input file not valid- check err file to view the errors- "+panel, workingLog);
            
        }
        } 
      if(panel.toLowerCase().equals("34plex")){
        panelInfo="SNPforID 34-plex";
        Hashtable<Integer, String> siteOrderHash = new Hashtable<Integer, String>();
        siteOrderHash = ReadTxtFiles.readSiteOrderToHashJar(homePath,"Site_34plex.txt");
        Hashtable<Integer, String> siteIdHash = new Hashtable<Integer, String>();
        siteIdHash = ReadTxtFiles.readSiteIdToHashJar(homePath,"Site_34plex.txt");
        //sample order and Id
        Hashtable<Integer, String> sampleOrderHash = new Hashtable<Integer, String>();
        sampleOrderHash = ReadTxtFiles.readSampleOrderToHashJar(homePath,"Sample_34plex.txt");
        Hashtable<Integer, String> sampleIdHash = new Hashtable<Integer, String>();
        sampleIdHash = ReadTxtFiles.readSampleIdToHashJar(homePath,"Sample_34plex.txt");
        //Validate the file header.
        int valid = ValidateFileHeader.validateHeaders34(homePath,inputFilename, siteOrderHash);
        int invalidCount=0;
        
        if (valid == 1) {
           WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Validation complete. Starting compution.. "+panel, workingLog);
           
            Hashtable<String, Double> referenceDataHash = new Hashtable<String, Double>();
            referenceDataHash = ReadTxtFiles.readReferenceFileForPhenoFreqJar(homePath,"referenceData_34plex.txt");
            //create separate cellline table for each individual in a indGenotype/ folder in input file
            Hashtable<Integer, String> individualHash = new Hashtable<Integer, String>();
            individualHash = ReadTxtFiles.readInputFileForIndividualGenotype(homePath,inputFilename, siteOrderHash);
            Hashtable<String, Integer> snpGenotypeHT = new Hashtable<String, Integer>();
            snpGenotypeHT = ReadTxtFiles.readSiteGenotypeToHashJar(homePath,"SiteGenotype_34plex.txt");           
            //validate genotype
            invalidCount=ValidateFileHeader.validateGenotype(homePath,individualHash,siteOrderHash,siteIdHash,snpGenotypeHT);
            if(invalidCount==0){
                //now it should be ready to compute
                computeLikelihood(name,homePath,sampleOrderHash, sampleIdHash, siteOrderHash, siteIdHash, individualHash, snpGenotypeHT, referenceDataHash,panelInfo);
                //create order of magnitude
                OrderOfMagtitude.createOrderOfMagnitude(name+"_likelihood.txt",name,homePath,panelInfo);
                //rank order population likelihood
                RankPopulationLikelihood.createPopRankOrder(name+"_likelihood.txt",name,homePath,panelInfo);
                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Computation completed.. "+panel, workingLog);
            }else {
                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Invalid genotype - check err file to view the errors -  "+panel, workingLog);
            
            System.exit(1);
        }
            } else {
            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Input file not valid- check err file to view the errors -  "+panel, workingLog);
            
        }
        }
      if(panel.toLowerCase().equals("combined")){
        panelInfo="Combined panel of Kiddlab-55,Seldin's-128, and SNPforID34-plex AISNPs (192 SNPs)";
        Hashtable<Integer, String> siteOrderHash = new Hashtable<Integer, String>();
        siteOrderHash = ReadTxtFiles.readSiteOrderToHashJar(homePath,"Site_combined.txt");
        Hashtable<Integer, String> siteIdHash = new Hashtable<Integer, String>();
        siteIdHash = ReadTxtFiles.readSiteIdToHashJar(homePath,"Site_combined.txt");
        //sample order and Id
        Hashtable<Integer, String> sampleOrderHash = new Hashtable<Integer, String>();
        sampleOrderHash = ReadTxtFiles.readSampleOrderToHashJar(homePath,"Sample_combined.txt");
        Hashtable<Integer, String> sampleIdHash = new Hashtable<Integer, String>();
        sampleIdHash = ReadTxtFiles.readSampleIdToHashJar(homePath,"Sample_combined.txt");
        //Validate the file header.
        int valid = ValidateFileHeader.validateHeadersCombined(homePath,inputFilename, siteOrderHash);
        int invalidCount=0;
        
        if (valid == 1) {
           WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Validation complete. Starting computation.. "+panel, workingLog);
           
            Hashtable<String, Double> referenceDataHash = new Hashtable<String, Double>();
            referenceDataHash = ReadTxtFiles.readReferenceFileForPhenoFreqJar(homePath,"referenceData_combined.txt");
            //create separate cellline table for each individual in a indGenotype/ folder in input file
            Hashtable<Integer, String> individualHash = new Hashtable<Integer, String>();
            individualHash = ReadTxtFiles.readInputFileForIndividualGenotype(homePath,inputFilename, siteOrderHash);
            Hashtable<String, Integer> snpGenotypeHT = new Hashtable<String, Integer>();
            snpGenotypeHT = ReadTxtFiles.readSiteGenotypeToHashJar(homePath,"SiteGenotype_combined.txt");           
            //validate genotype
            invalidCount=ValidateFileHeader.validateGenotype(homePath,individualHash,siteOrderHash,siteIdHash,snpGenotypeHT);
            if(invalidCount==0){
                //now it should be ready to compute
                computeLikelihood(name,homePath,sampleOrderHash, sampleIdHash, siteOrderHash, siteIdHash, individualHash, snpGenotypeHT, referenceDataHash,panelInfo);
                //create order of magnitude
                OrderOfMagtitude.createOrderOfMagnitude(name+"_likelihood.txt",name,homePath,panelInfo);
                //rank order population likelihood
                RankPopulationLikelihood.createPopRankOrder(name+"_likelihood.txt",name,homePath,panelInfo);
                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Computation complete.. "+panel, workingLog);
            }else {
                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Invalid genotype - check err file to view the errors - "+panel, workingLog);
            
            System.exit(1);
        }
            } else {
            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" -  Input file not valid- check err file to view the errors - "+panel, workingLog);
            
           
        }
        }
      if(panel.toLowerCase().equals("precision")){
        panelInfo="Precision ID Ancestry Panel";
        Hashtable<Integer, String> siteOrderHash = new Hashtable<Integer, String>();
        siteOrderHash = ReadTxtFiles.readSiteOrderToHashJar(homePath,"Site_precision.txt");
        Hashtable<Integer, String> siteIdHash = new Hashtable<Integer, String>();
        siteIdHash = ReadTxtFiles.readSiteIdToHashJar(homePath,"Site_precision.txt");
        //sample order and Id
        Hashtable<Integer, String> sampleOrderHash = new Hashtable<Integer, String>();
        sampleOrderHash = ReadTxtFiles.readSampleOrderToHashJar(homePath,"Sample_precision.txt");
        Hashtable<Integer, String> sampleIdHash = new Hashtable<Integer, String>();
        sampleIdHash = ReadTxtFiles.readSampleIdToHashJar(homePath,"Sample_precision.txt");
        //Validate the file header.
        int valid = ValidateFileHeader.validateHeadersPrecision(homePath,inputFilename, siteOrderHash);
        int invalidCount=0;
        
        if (valid == 1) {
           WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Validation complete - computing started - "+panel, workingLog);
            
           
            Hashtable<String, Double> referenceDataHash = new Hashtable<String, Double>();
            referenceDataHash = ReadTxtFiles.readReferenceFileForPhenoFreqJar(homePath,"referenceData_precision.txt");
            //create separate cellline table for each individual in a indGenotype/ folder in input file
            Hashtable<Integer, String> individualHash = new Hashtable<Integer, String>();
            individualHash = ReadTxtFiles.readInputFileForIndividualGenotype(homePath,inputFilename, siteOrderHash);
            Hashtable<String, Integer> snpGenotypeHT = new Hashtable<String, Integer>();
            snpGenotypeHT = ReadTxtFiles.readSiteGenotypeToHashJar(homePath,"SiteGenotype_precision.txt");           
            //validate genotype
            invalidCount=ValidateFileHeader.validateGenotype(homePath,individualHash,siteOrderHash,siteIdHash,snpGenotypeHT);
            if(invalidCount==0){
                //now it should be ready to compute
                computeLikelihood(name,homePath,sampleOrderHash, sampleIdHash, siteOrderHash, siteIdHash, individualHash, snpGenotypeHT, referenceDataHash,panelInfo);
                //create order of magnitude
                OrderOfMagtitude.createOrderOfMagnitude(name+"_likelihood.txt",name,homePath,panelInfo);
                //rank order population likelihood
                RankPopulationLikelihood.createPopRankOrder(name+"_likelihood.txt",name,homePath,panelInfo);
                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Computation complete - "+panel, workingLog);
            
            }else {
                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Invalid genotype - check err file to view the errors - "+panel, workingLog);
            
            System.exit(1);
        }
            } else {
            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+"Input file not valid- check err file to view the errors - "+panel, workingLog);
           
        }
        }
    }

    private static void computeLikelihood(String filename,String homePath,Hashtable sampleOrderHash, Hashtable sampleIdHash, Hashtable siteOrderHash, Hashtable siteIdHash, Hashtable individualHash, Hashtable snpGenotypeHT, Hashtable referenceDataHash,String panelInfo) {
        String outputFileName = filename+"_likelihood.txt";
        Hashtable<String, String> indGenotype = new Hashtable<String, String>();
        Hashtable<Integer, String> likelihoodHash = new Hashtable<Integer, String>();
        StringBuffer likelihoodSB = new StringBuffer();
        String TAB = "\t";
        String individual = "";
        String sampleName = "";
        int alfredSampleId = 0;
        String rsnumber = "";
        int alfredSiteId = 0;
        String genotype = "";
        String revGenotype = "";        
        int liSampleId = 0;
        String liSampleName = "";
        double pSite = 0.0;
        double matchPSite = 0.0;
        String genotypeSTR = "";
        int sampleCount = 0; 
        int siteCount=0;
        likelihoodSB.setLength(0);
        likelihoodSB.append(panelInfo);
        WriteToOutputFile.WriteToLikelihoodFile(homePath,likelihoodSB, outputFileName);
        likelihoodSB.setLength(0);
        likelihoodSB.append("Individual").append(TAB).append("SNP_Count").append(TAB);
        for (int x = 0; x < sampleOrderHash.size(); x++) {
            likelihoodSB.append(sampleOrderHash.get(x + 1)).append(TAB);
        }
        WriteToOutputFile.WriteToLikelihoodFile(homePath,likelihoodSB, outputFileName);
        likelihoodSB.setLength(0);
        for (int w = 0; w < individualHash.size(); w++) {
            likelihoodSB.setLength(0);
            individual = individualHash.get(w + 1).toString();
            likelihoodSB.append(individual).append(TAB);
            indGenotype = ReadTxtFiles.readGenotypeForRsInd(homePath,"_" + individual + ".out");
            for (int m = 0; m < sampleOrderHash.size(); m++) {
                sampleName = sampleOrderHash.get(m + 1).toString();
                
                alfredSampleId = getKey(sampleIdHash, sampleName);
                matchPSite = 1.0;
                sampleCount = 0;
                siteCount=0;
                //loop through to compute
                for (int l = 0; l < siteOrderHash.size(); l++) {
                    rsnumber = siteOrderHash.get(l + 1).toString();                    
                    alfredSiteId = getKey(siteIdHash, rsnumber);                    
                    genotype = "";
                    genotype = indGenotype.get(rsnumber);
                    genotypeSTR="";
                    if((genotype.equals("NN"))){
                        
                    }else { 
                            genotypeSTR = alfredSiteId + ";" + alfredSampleId + ";" + genotype;                            
                            if (referenceDataHash.containsKey(genotypeSTR)) {
                                pSite = Double.parseDouble(referenceDataHash.get(genotypeSTR).toString());                                
                            } else {
                                //flip genotype
                                revGenotype = getReverseCombo(genotype);
                                genotypeSTR = alfredSiteId + ";" + alfredSampleId + ";" + revGenotype;                                
                                pSite = Double.parseDouble(referenceDataHash.get(genotypeSTR).toString());                                
                            }

                            if (pSite == 0.0) {
                                siteCount=siteCount+1;
                                sampleCount = sampleCount + 1;
                            } else {
                                siteCount=siteCount+1;
                                sampleCount = sampleCount + 1;
                                matchPSite = matchPSite * pSite;                                
                            }   
                    }
                }
                matchPSite = roundThreeDecimals(matchPSite);
                likelihoodHash.put(alfredSampleId, matchPSite+"");                
            }
            //add the number of SNPs used in the computation.
            likelihoodSB.append(siteCount).append(TAB);
            //create the string and print into file
            liSampleName = "";
            liSampleId = 0;
            for (int li = 0; li < sampleOrderHash.size(); li++) {
                liSampleName = sampleOrderHash.get(li + 1).toString();
                
                liSampleId = getKey(sampleIdHash, liSampleName);
                likelihoodSB.append(likelihoodHash.get(liSampleId)).append(TAB);
            }
            WriteToOutputFile.WriteToLikelihoodFile(homePath,likelihoodSB, outputFileName);
        }
    }

    public static double roundThreeDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("0.###E0");
        return Double.valueOf(twoDForm.format(d));
    }
    public static int getKey(Hashtable<Integer, String> idHashtable, String value) {
        int key = 0;
        for (Map.Entry entry : idHashtable.entrySet()) {
            if (value.equals(entry.getValue())) {
                key = Integer.parseInt(entry.getKey().toString());
            }
        }
        return key;
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

}
