/*
 * Created by Haseena Rajeevan.
 */
package read;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Vector;
import write.WriteToOutputFile;


/**
 *
 * @author hr65
 */
public class ReadTxtFiles {
    
    /**
     * readSiteIdToHashJar
     * @param site55File
     * @return 
     */
    public static Hashtable<Integer, String> readSiteIdToHashJar(String homePath,String site55File) {
        System.out.println("read site file .. " );
        String record = null;
        Hashtable<Integer, String> siteIdHT = new Hashtable<Integer, String>();
         
        //--
        String rsnumS[];
        int index = 0;
        try {

            InputStream inputstream = ReadTxtFiles.class.getResourceAsStream("data/"+site55File);
            BufferedReader br = new BufferedReader((new InputStreamReader(inputstream)));
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                //System.out.println("record:" + record);
                
                rsnumS = record.split("\\|");
                siteIdHT.put(Integer.parseInt(rsnumS[0]), rsnumS[1]);
                
            }
        inputstream.close();
        } catch (IOException e) {
            // catch possible io errors from readLine()
            System.out.println("IOException error -"+e.getMessage());
            
        }
        return siteIdHT;
    }
    
    /**
     * readSiteOrderToHashJar
     * @param site55File
     * @return 
     */
    public static Hashtable<Integer, String> readSiteOrderToHashJar(String homePath,String site55File) {
       System.out.println("read site file .. " );
        String record = null;
        Hashtable<Integer, String> siteOrderHT = new Hashtable<Integer, String>();
        String rsnumS[];
        int index = 0;
        try {

            //InputStream inputstream = new FileInputStream("../data/"+site55File);
            InputStream inputstream = ReadTxtFiles.class.getResourceAsStream("data/"+site55File);
            BufferedReader br = new BufferedReader((new InputStreamReader(inputstream)));
            
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                //System.out.println("record:" + record);
                
                rsnumS = record.split("\\|");
                siteOrderHT.put(index, rsnumS[1]);
                
            }

        } catch (IOException e) {
            // catch possible io errors from readLine()
            System.out.println("IOException error -"+e.getMessage());
        }
        return siteOrderHT;
    }
    

public static Hashtable<String,Double> readReferenceFileForPhenoFreqJar(String homePath,String fileName) {
        System.out.println("read Reference File ");
        String record = null;
        String string[];        
        Hashtable<String,Double> snpPhenofreqHash=new Hashtable<String,Double>();
        StringBuilder phenofreqInfoSB=new StringBuilder();
        int index = 0;
        try {

            InputStream inputstream = ReadTxtFiles.class.getResourceAsStream("data/"+fileName);
            BufferedReader br = new BufferedReader((new InputStreamReader(inputstream)));
            
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                if (index > 1) {
                    phenofreqInfoSB.setLength(0);
                    //System.out.println("record:" + record);
                    string = record.split("\\|");
                    phenofreqInfoSB.append(string[0].trim()).append(";").append(string[2].trim()).append(";").
                            append(string[4].trim());
                    snpPhenofreqHash.put(phenofreqInfoSB.toString(), Double.parseDouble(string[5]));                    
                }
            }

        } catch (IOException e) {
            // catch possible io errors from readLine()
            System.out.println("IOException error -"+e.getMessage());
        }
        return snpPhenofreqHash;

    } // end of readReferenceFileForPhenoFreq55()
public static Hashtable<Integer,String> readInputFileForIndividualGenotype(String homePath,String inputFilename, Hashtable rsnumberHT) {
System.out.println("read input file..  ");		
    String filename = "";
		String individual="";
		
		String record = null;
		String genotype="";
		int index=0;
		String string[];
		String stringG[];
		StringBuffer typingInfo=new StringBuffer();
		StringBuilder individualSB= new StringBuilder();
                Hashtable<Integer,String> individualHT=new Hashtable<Integer,String>();
		String rsnum="";
		
		try {

			FileReader fr = new FileReader(homePath+"input/" + inputFilename);
			BufferedReader br = new BufferedReader(fr);
			record = new String();
			while ((record = br.readLine()) != null) {
				index++;
				if((index>1)){
					//System.out.println("record:" + record);
                                        individualSB.setLength(0);
					string=record.split("\\,");
					individual=string[0].trim();
					individualSB.append(index-1).append("|").append(individual);
                                        individualHT.put(index-1, individual);
					filename = "_" + individual;
					//System.out.println("individual filename:" + filename);
					typingInfo.setLength(0);
					for(int i=1;i<string.length;i++){
						
						rsnum=rsnumberHT.get(i).toString();
						//split each string
						if(string[i].length()<2){
							genotype="NN";
						}else{
							genotype=string[i].trim();
							
						}
						
						typingInfo.append(rsnum).append("\t").append(genotype).append("\n");
						
						
					}
					//System.out.println(typingInfo.toString());
					WriteToOutputFile.WriteToIndGenotypeFile(homePath,typingInfo.toString(), filename + ".out");
					WriteToOutputFile.WriteToIndFile(homePath,individualSB, "individuals.out");
				}
				

			}

		} catch (IOException e) {
			// catch possible io errors from readLine()
			System.out.println("IOException error -"+e.getMessage());
		}
                return individualHT;
	}    
public static Hashtable<Integer, String> readSampleOrderToHashJar(String homePath,String sample55File) {
        System.out.println("read sample file..  ");
        String record = null;
        Hashtable<Integer, String> sampleOrderHT = new Hashtable<Integer, String>();
        String sampleS[];
        int index = 0;
        try {

            InputStream inputstream = ReadTxtFiles.class.getResourceAsStream("data/"+sample55File);
            BufferedReader br = new BufferedReader((new InputStreamReader(inputstream)));
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                //System.out.println("record:" + record);
                
                sampleS = record.split("\\|");
                sampleOrderHT.put(index, sampleS[1]);
               
            }

        } catch (IOException e) {
            // catch possible io errors from readLine()
            System.out.println("IOException error -"+e.getMessage());
        }
        return sampleOrderHT;
    }    
    public static Hashtable<Integer, String> readSampleIdToHashJar(String homePath,String sample55File) {
        System.out.println("read samples file .. ");
        String record = null;
        Hashtable<Integer, String> sampleIdHT = new Hashtable<Integer, String>();
        String sampleS[];
        int index = 0;
        try {
            InputStream inputstream = ReadTxtFiles.class.getResourceAsStream("data/"+sample55File);
            BufferedReader br = new BufferedReader((new InputStreamReader(inputstream)));
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                //System.out.println("record:" + record);
                
                sampleS = record.split("\\|");
                sampleIdHT.put(Integer.parseInt(sampleS[0]), sampleS[1]);
               
            }

        } catch (IOException e) {
            // catch possible io errors from readLine()
            System.out.println("IOException error -"+e.getMessage());
        }
        return sampleIdHT;
    }
    /**************************************************************************
	 * getGenoForRsInd
	 * @param string
	 * @return
	 *************************************************************************/
	
	public static Hashtable<String, String> readGenotypeForRsInd(String homePath,String fileName) {
            //System.out.println("read genotype file .. ");
		Hashtable<String,String> genoTypeHash=new Hashtable<String,String>();
		String record="";
		//System.out.println("file name:"+fileName);
		try {
			
			FileReader fr = new FileReader(homePath+"input/indGenotype/" + fileName);
			BufferedReader br = new BufferedReader(fr);
			record = new String();
			while ((record = br.readLine()) != null) {
				//System.out.println("record:" + record);
				String f[]=record.split("\t");
				//System.out.println(" f[0].trim()- " + f[0].trim()+" f[1].trim() - "+f[1].trim());
				genoTypeHash.put(f[0].trim(),f[1].trim());
				

			}

		} catch (IOException e) {
			// catch possible io errors from readLine()
			System.out.println("IOException error -"+e.getMessage());
		}
		return genoTypeHash;
	}

    
    public static Hashtable<String, Integer> readSiteGenotypeToHashJar(String homePath,String siteGenotype55File) {
        //System.out.println("read genotype file .. ");
        String record = null;
        Hashtable<String, Integer> siteGenotypeHT = new Hashtable<String, Integer>();
        String siteGenoS[];
        int index = 0;
        try {
            InputStream inputstream = ReadTxtFiles.class.getResourceAsStream("data/"+siteGenotype55File);
            BufferedReader br = new BufferedReader((new InputStreamReader(inputstream)));
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                //System.out.println("record:" + record);
                if(index>1){
                siteGenoS = record.split("\\|");
                siteGenotypeHT.put(siteGenoS[0]+";"+siteGenoS[1], index);
                }
            }

        } catch (IOException e) {
            // catch possible io errors from readLine()
            System.out.println("IOException error -"+e.getMessage());
        }
        return siteGenotypeHT;
    }
    //Read properties file
    public static ArrayList<String> readProperties() {
        //System.out.println("read genotype file .. ");
        String record = null;
        ArrayList<String> propertiesAL = new ArrayList<String>();
        String[] homePathS;
        String homePath="";
        String[] inputFilenameS;
        String inputFilename="";
        String[] panelS;
        String panel="";
        int index = 0;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String errFile = "errFile.txt";
        String workingLog="workingLog.txt";
        //WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Start reading properties file ", workingLog);
        try {
            InputStream inputstream = new FileInputStream("FROGAncestryCalc.properties");
            BufferedReader br = new BufferedReader((new InputStreamReader(inputstream)));
            record = new String();
            while ((record = br.readLine()) != null) {
                index++;
                System.out.println("record "+record);
                if(record.contains("homePath")){
                homePathS = record.split("=");
                homePath=homePathS[1].trim();
                System.out.println("homePath - "+homePath);
                if(homePath.endsWith("\\/")){
                    System.out.println("Do nothing");
                }else{
                    homePath=homePath+"/";
                }
                propertiesAL.add(homePath);
                }
                if(record.contains("inputFilename")){
                inputFilenameS = record.split("=");
                inputFilename=inputFilenameS[1].trim();
                System.out.println("inputFilename - "+inputFilename);
                
                 propertiesAL.add(inputFilename);
                }
                if(record.contains("panelInfo")){
                panelS = record.split("=");
                panel=panelS[1].trim();
                System.out.println("panel - "+panel);                
                propertiesAL.add(panel);
                }
                
            }

        }catch (FileNotFoundException fe) {
            // catch possible io errors from readLine()
            System.out.println("Missing properties file. Code cannot run if the properties file is missing."+fe.getMessage()); 
            System.exit(1);
        }catch (IOException e) {
            // catch possible io errors from readLine()
            
            System.out.println("Properties file issues - "+e.getMessage());
            System.exit(1);
        }
        if((homePath.isEmpty())||(inputFilename.isEmpty())||(panel.isEmpty())){
                    System.out.println("Your properties file is not complete."); 
                    System.exit(1);
                }else{
            if((panel.equalsIgnoreCase("55AI"))||(panel.equalsIgnoreCase("128AI"))||
                    (panel.equalsIgnoreCase("34plex"))||(panel.equalsIgnoreCase("combined"))||(panel.equalsIgnoreCase("precision"))){
                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" --------------Start------------- ", workingLog);
                WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Properties file read completed - ", workingLog);
            
            }else{
            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" --------------Start------------- "+homePath, workingLog);
                    
            WriteToOutputFile.WriteToErrFile(homePath,timeStamp+" - Check panel keyword in properties file ", errFile);
            WriteToOutputFile.WriteToWorkingLog(homePath,timeStamp+" - Error - Check panel keyword in properties file - ", workingLog);
            System.exit(1);
            }
            }
        return propertiesAL;
    }
    
}
