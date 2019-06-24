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
import java.util.Hashtable;
import write.WriteToOutputFile;

public class RankPopulationLikelihood {
    public static void createPopRankOrder(String likelihoodFile,String outputFile,String homePath,String panelInfo){
        String record = null;
		//String fileName="typingLikelihood_Kiddlab.out";
		String fileName=likelihoodFile;
                String outputf =outputFile+"_rankOrder.txt";
		int index=0;
		String likelihoodFromTb="";		
		String popS[];		
		String cellLine="";
		String popNameV="";
                String SNPCount=""; //to print 
		StringBuffer rankOrderSB=new StringBuffer();
		ArrayList<IndividualBean> cellLinesV=new ArrayList<IndividualBean>();
		Hashtable<String,Integer> popRankHash=new Hashtable<String,Integer>();
		IndividualBean cellLineB=new IndividualBean();
		ArrayList<String> popV=new ArrayList<String>();
		
		try {

			FileReader fr = new FileReader(homePath+"/output/" + fileName);
			BufferedReader br = new BufferedReader(fr);
			record = new String();
			while ((record = br.readLine()) != null) {
				index++;
				if(index==2){
					popS=record.trim().split("\t");
					//create a vector for pops
					for(int b=1; b<popS.length;b++){
						popV.add(popS[b]);
					}
                                        rankOrderSB.append(panelInfo);
                                        WriteToOutputFile.WriteToRankPopFile(homePath,rankOrderSB,outputf);
					rankOrderSB.setLength(0);
					rankOrderSB.append("Individual").append("\t").append("SNP_Count").append("\t");
					for(int g=0; g<popV.size();g++){
                                            rankOrderSB.append(popV.get(g)).append("\t");
					}
					WriteToOutputFile.WriteToRankPopFile(homePath,rankOrderSB,outputf);
					rankOrderSB.setLength(0);
				}
				if(index>2){
					rankOrderSB.setLength(0);
					String f[]=record.trim().split("\t");
					
					cellLine=f[0].trim();
					SNPCount=f[1].trim();
					cellLinesV.clear();
					for(int k=2; k<f.length;k++){						
						likelihoodFromTb=f[k];						
						cellLinesV.add(new IndividualBean(popV.get(k-1),Double.parseDouble(likelihoodFromTb)));
					}
					//get rank order
					Collections.sort(cellLinesV,Collections.reverseOrder()); 
				 
			        //print rank order
			        for (int i = 0; i < cellLinesV.size(); i++) {  
			        	cellLineB=cellLinesV.get(i);
			        	popRankHash.put(cellLineB.getSampleName(), i+1);			            
			        }
			        rankOrderSB.append(cellLine).append("\t").append(SNPCount).append("\t");
			        for(int w=0; w<popV.size();w++){
			        	popNameV=popV.get(w);
			        	if(popRankHash.containsKey(popNameV)){
			        		rankOrderSB.append(popRankHash.get(popNameV));
			        	}
			        	rankOrderSB.append("\t");
			        }
			        WriteToOutputFile.WriteToRankPopFile(homePath,rankOrderSB,outputf);					
				}	
			}

		} catch (IOException e) {
			// catch possible io errors from readLine()
			System.out.println("Uh oh, got an IOException error!");
			e.printStackTrace();
		}
    }
}
