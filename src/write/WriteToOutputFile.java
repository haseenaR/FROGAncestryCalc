/*
 * Created by Haseena Rajeevan.
 */
package write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author hr65
 */
public class WriteToOutputFile {

    /**
     * ************************************************************************
     * writeToFile(data);
 *************************************************************************
     */
    public static void WriteToErrFile(String homePath,String data, String outPutFileName) {

        BufferedWriter out;
        
        File newFile = new File(homePath+"output/" +outPutFileName);
        if (!newFile.exists()) {
            // System.out.println("File do not exist");
            try {
                out = new BufferedWriter(new FileWriter(newFile));
                out.write(data.toString());
                out.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // System.out.println("File exists");
            try {

                out = new BufferedWriter(new FileWriter(newFile, true));
                out.newLine();
                out.write(data.toString());
                // out.newLine();
                out.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }//end of write to file
    public static void WriteToIndGenotypeFile(String homePath,String data, String outPutFileName) {

        BufferedWriter out;
        String theFilename = homePath+"input/indGenotype/" + outPutFileName;
        File newFile = new File(theFilename);
        if (!newFile.exists()) {
            // System.out.println("File do not exist");
            try {
                out = new BufferedWriter(new FileWriter(newFile));
                out.write(data.toString());
                out.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // System.out.println("File exists");
            try {

                out = new BufferedWriter(new FileWriter(newFile, true));
                out.newLine();
                out.write(data.toString());
                // out.newLine();
                out.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }//end of write to file

    public static void WriteToIndFile(String homePath,StringBuilder individualSB, String individualsout) {
        BufferedWriter out;
        String theFilename = homePath+"input/ind/" + individualsout;
        File newFile = new File(theFilename);
        if (!newFile.exists()) {
            // System.out.println("File do not exist");
            try {
                out = new BufferedWriter(new FileWriter(newFile));
                out.write(individualSB.toString());
                out.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // System.out.println("File exists");
            try {

                out = new BufferedWriter(new FileWriter(newFile, true));
                out.newLine();
                out.write(individualSB.toString());
                // out.newLine();
                out.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void WriteToLikelihoodFile(String homePath,StringBuffer likelihoodSB, String outputFileName) {
        BufferedWriter out;
        String theFilename = homePath+"output/" + outputFileName;
        File newFile = new File(theFilename);
        if (!newFile.exists()) {
            // System.out.println("File do not exist");
            try {
                out = new BufferedWriter(new FileWriter(newFile));
                out.write(likelihoodSB.toString());
                out.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // System.out.println("File exists");
            try {

                out = new BufferedWriter(new FileWriter(newFile, true));
                out.newLine();
                out.write(likelihoodSB.toString());
                // out.newLine();
                out.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void WriteToOrderOfMagFile(String homePath,StringBuffer firstOrderOfMagSB, String outputFileName) {
        
        BufferedWriter out;
        String theFilename =  homePath+"output/" +outputFileName;
        File newFile = new File(theFilename);
        if (!newFile.exists()) {
            // System.out.println("File do not exist");
            try {
                out = new BufferedWriter(new FileWriter(newFile));
                out.write(firstOrderOfMagSB.toString());
                out.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // System.out.println("File exists");
            try {

                out = new BufferedWriter(new FileWriter(newFile, true));
                out.newLine();
                out.write(firstOrderOfMagSB.toString());
                // out.newLine();
                out.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void WriteToRankPopFile(String homePath,StringBuffer rankOrderSB, String outputFileName) {
        
        BufferedWriter out;
        String theFilename =  homePath+"output/" +outputFileName;
        File newFile = new File(theFilename);
        if (!newFile.exists()) {
            // System.out.println("File do not exist");
            try {
                out = new BufferedWriter(new FileWriter(newFile));
                out.write(rankOrderSB.toString());
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // System.out.println("File exists");
            try {
                out = new BufferedWriter(new FileWriter(newFile, true));
                out.newLine();
                out.write(rankOrderSB.toString());
                // out.newLine();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } 
    }

    public static void WriteToWorkingLog(String homePath, String msg, String workingLog) {
      BufferedWriter out;
        
        File newFile = new File(homePath+"log/" +workingLog);
        if (!newFile.exists()) {
            // System.out.println("File do not exist");
            try {
                out = new BufferedWriter(new FileWriter(newFile));
                out.write(msg.toString());
                out.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // System.out.println("File exists");
            try {

                out = new BufferedWriter(new FileWriter(newFile, true));
                out.newLine();
                out.write(msg.toString());
                // out.newLine();
                out.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }//end of write to file
}
