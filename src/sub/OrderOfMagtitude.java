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
/*
 * Created by Haseena Rajeevan.
 */
package sub;

import bean.IndividualBean;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import write.WriteToOutputFile;

public class OrderOfMagtitude {

    public static void createOrderOfMagnitude(String likelihoodFile,String outputFile,String homePath,String panelInfo) {
        String record = null;
        String fileName = likelihoodFile;
        String outputf =outputFile+"_orderOfMag.txt";
        int index = 0;
        String likelihoodFromTb = "";
        Double likelihoodVal1 = 0.0;
        Double likelihoodVal = 0.0;
        String popS[];
        String individual = "";
        String SNPCount=""; //to print
        StringBuffer firstOrderOfMagSB = new StringBuffer();
        ArrayList<IndividualBean> cellLinesV = new ArrayList<IndividualBean>();
        IndividualBean cellLineB = new IndividualBean();
        ArrayList<String> popV = new ArrayList<String>();

        try {

            FileReader fr = new FileReader(homePath+"/output/" + fileName);
            BufferedReader br = new BufferedReader(fr);
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                if (index == 2) {
                    popS = record.trim().split("\t");
                    //create a vector for pops
                    for (int b = 1; b < popS.length; b++) {
                        popV.add(popS[b]);
                    }
                    firstOrderOfMagSB.append(panelInfo);
                    WriteToOutputFile.WriteToOrderOfMagFile(homePath,firstOrderOfMagSB, outputf);
                    firstOrderOfMagSB.setLength(0);
                    firstOrderOfMagSB.append("Individual").append("\t").append("SNP_Count").append("\t");
                    WriteToOutputFile.WriteToOrderOfMagFile(homePath,firstOrderOfMagSB, outputf);
                    firstOrderOfMagSB.setLength(0);
                }
                if (index > 2) {
                    SNPCount="";
                    firstOrderOfMagSB.setLength(0);
                    String f[] = record.trim().split("\t");
                    individual = f[0].trim();
                    SNPCount=f[1].trim();
                    cellLinesV.clear();
                    for (int k = 2; k < f.length; k++) {
                        likelihoodFromTb = f[k];
                        cellLinesV.add(new IndividualBean(popV.get(k - 1), Double.parseDouble(likelihoodFromTb)));
                    }
                    //get rank order					
                    Collections.sort(cellLinesV, Collections.reverseOrder());
                    firstOrderOfMagSB.append(individual).append("\t").append(SNPCount).append("\t");
                    for (int i = 0; i < cellLinesV.size(); i++) {
                        if (i == 0) {
                            cellLineB = cellLinesV.get(i);
                            likelihoodVal1 = cellLineB.getLikelihood();
                            firstOrderOfMagSB.append(cellLineB.getSampleName()).append("\t");
                            likelihoodVal1 = likelihoodVal1 / 10.00;
                        }
                        if (i > 0) {
                            cellLineB = cellLinesV.get(i);
                            likelihoodVal = cellLineB.getLikelihood();
                            if (likelihoodVal > likelihoodVal1) {
                                //flag as same order of magnitude
                                firstOrderOfMagSB.append(cellLineB.getSampleName()).append("\t");
                            }
                        }
                    }
                    //print rank order        
                    WriteToOutputFile.WriteToOrderOfMagFile(homePath,firstOrderOfMagSB, outputf);
                }
            }
        } catch (IOException e) {
            // catch possible io errors from readLine()
            System.out.println("Uh oh, got an IOException error!");
            e.printStackTrace();
        }
    }
}
