The FROG-kb Ancestry Inference Batch Likelihood Computation Tool
     Unzip the FROGAncestryCalc.zip file.
     NOTE: when you unzip the file, a folder called FROGAncestryCalc will be created.
 
  The mainfolder contains: 
  -------------------
(1) a file called: FROGAncestryCalc.properties 
   Open this file with a simple text editor and follow the instructions
   stored in the top lines of that file to set the 
          home directory (homePath), 
          input file name (inputFilename), 
      and the appropriate AI panel name (panelInfo).
   The program will not run if this information is set wrong.

   > > > IMPORTANT REMINDER!< < <
   Before starting a new job remember to update the properties file with 
   the appropriate new input file name and AIpanel name.


(2) FROGAncestryCalc.jar - Package of Java class files associated with FROGAncestryCalc

(3) 'input' folder contains a sample genotype input file (comma ',' delimited), 
       and two folders named 'ind' and 'indGenotype'.
	     The 'ind' and 'indGenotype' folders are used during the calculations.DO NOT DELETE THESE FOLDERS.


     	/****************************************************************************************
	Preparing the input file you want to analyze

	* Follow the SNP order given in the sample file for the particular AI panel you use.
	*      Currently, you can test any one of five AI panels including
	*          (a) KiddLab - Set of 55 AISNPs 
	*          (b) Seldin's list of 128 AISNPs 
	*          (c) SNPforID 34-plex
	*          (d) Combined panel of Kiddlab-55,Seldin's-128, and SNPforID34-plex AISNPs (192 SNPs)
	*          (e) Precision ID Ancestry Panel                            
	* The SNP labels and genotypes must be ordered by rsnumber(alphanumeric). 
	* The required order can be achieved by sorting (ascending) the rsnumbers in an Excel Spreadsheet.
	* 	
	* The SampleInputFiles directory contains example input files organized for each of the different AIpanels
	* for which you can generate likelihoods.  For instance, '55_aisnps_sample.txt' is a sample input file.
  *
  * Make sure the 'Individual Identifers' in your file are all unique. 
  *
  * Consult the appropriate file in the 'SNPInfo' directory to find the set of alleles corresponding to
  *  each SNP in the AIpanel you want to analyze.	
  *
  * Make sure the input filename follows the format 'filename.txt' or 'filename.csv'
 
 
(4) The 'output' folder. This is where your output files will be created. 
	/****************************************************************************************
	* Check 'Sample_Output' folder to view sample output files. 
  *
	* The three output files created are tab-delimited and can be opened in an Excel spreadsheet.
	* Three files are written for each successful calculation. The names have the following format
	* where "filename" is the name of the input file you created for analysis.	
	*      'filename_likelihood.txt' - 
	*      'filename_orderOfMag.txt' - 
	*      'filename_rankOrder.txt' - 
  * An error message file will appear in the 'Sample_Output' folder when there is a problem that
  *   you need to fix such as a validation error. (For instance, the wrong alleles appear in a SNP genotype.)  
	*   The error message file name is:  'errFile.txt' 
	* 
	* Important! The output files created from previous jobs you have run including 
	* any error message file (called errFile.txt) generated are deleted from the OUTPUT folder at the start of a new job.  	
  *  
	/****************************************************************************************
	
	
(5) The 'log' folder is where you can find a file named - workingLog.txt.
    This file logs information related to the processing of every job you run. 
    This file also has a copy of any error messages that appear in the errFile.txt file. 
    Note: the contents of this file accumulate across jobs run until you decide to delete the log information.

(6) jre7 - Java Runtime Environment.

(7) FROGAncestryCalc.bat - Batch file to run the code.

	Either, Double click on the .bat file to run the program successfully.
        Or, open a command line window; move to the FrogBLT folder; type: FROGAncestryCalc.bat
        Or, open a Power shell window; move to the FrogBLT folder; type: .\FROGAncestryCalc.bat

(8) SNPInfo folder - containes SNP information files for each panel.
	55_aisnps_alleles.txt - SNP and allele information for the KiddLab - Set of 55 AISNPs.
	128_aisnps_alleles.txt - SNP and allele information for the Seldin's list of 128 AISNPs.
	34_plex_alleles.txt - SNP and allele information for the SNPforID 34-plex panel.
	combined_alleles.txt - SNP and allele information for the Combined panel of Kiddlab-55,Seldin's-128, and SNPforID34-plex AISNPs.
	precision_alleles.txt - SNP and allele information for the Precision ID Ancestry Panel .

(9) sampleInputFiles folder - containes sample input files for all four panels.
	55_aisnps_sample.txt - KiddLab - Set of 55 AISNPs
	128_aisnps_sample.txt - Seldin's list of 128 AISNPs
	34_plex_sample.txt - SNPforID 34-plex
	combined_sample.txt - Combined panel of Kiddlab-55,Seldin's-128, and SNPforID34-plex AISNPs
	precision_sample.txt - Precision ID Ancestry Panel


End of ReadMe file
