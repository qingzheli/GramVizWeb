package test;

import java.util.ArrayList;
import java.util.Arrays;

import edu.hawaii.jmotif.sax.SAXFactory;
import edu.hawaii.jmotif.sax.alphabet.NormalAlphabet;
import net.seninp.gi.GrammarRuleRecord;
import net.seninp.gi.GrammarRules;
import net.seninp.gi.RuleInterval;
import net.seninp.jmotif.sax.NumerosityReductionStrategy;
import net.seninp.jmotif.sax.SAXException;
import net.seninp.jmotif.sax.datastructures.SAXRecords;
import test.TSUtils;

public class SequiturInterface {
	private static String IN_FILE;
	private static int SAX_WINDOW_SIZE = 100;
	private static int SAX_PAA_SIZE = 8;
	private static int SAX_ALPHABET_SIZE = 4;
	private static double SAX_NORM_THRESHOLD=0.01;
	private static int sample_rate=6;
	public static GrammarRules rules;		
	public static double[] alt;
	public static double[] lon;
	
	
	//private static String OUT_FILE="out.txt";
	private static TSProcessor tp = new TSProcessor();
	private static final NormalAlphabet normalA = new NormalAlphabet();
	
	public SequiturInterface(String name,String name_L) {
		IN_FILE=name;
	}
	public static void load() throws SAXException
	{
		alt = tp.readTS(IN_FILE, 0);
	}
	public static void set_resample(int k)
	{
		sample_rate=k;
	}
	public static void run() throws Exception {
		  SAXRecords saxData = discretizeEqually(alt, NumerosityReductionStrategy.EXACT,
          		SAX_WINDOW_SIZE, SAX_PAA_SIZE, SAX_ALPHABET_SIZE, SAX_NORM_THRESHOLD);
		  String str = saxData.getSAXString(" ");
          SAXRule grammar = SequiturFactory.runSequitur(str);      
          rules = grammar.toGrammarRulesData();
          SequiturFactory.updateRuleIntervals(rules, saxData, true, alt,
                  SAX_WINDOW_SIZE, SAX_PAA_SIZE);
          ArrayList<Double> path=new ArrayList<Double>();
          int lens=0;
          
          for (GrammarRuleRecord ruleRecord : rules) {

              if (!ruleRecord.getOccurrences().isEmpty()) {

                ArrayList<RuleInterval> intervals = ruleRecord.getRuleIntervals();                

                for(RuleInterval rl : intervals)
        	    {
                	lens=rl.getEndPos()-rl.getStartPos();
        	    	if(lens!=0)
        	    	{
        	    	    path=getmotif(rl.getStartPos(),rl.getEndPos());   
        	    	}
        	    }
                //getmotif4Record(intervals);
                //setFirstMotif();    
                resample(path,sample_rate);
                //setIMotif();    
                //resample2(sample_rate);
                return;
              }
          }
	}
	
	public static void set_name()
	{
		
	}
	
	public static SAXRecords discretizeEqually(double[] timeseries,
		      NumerosityReductionStrategy numerosityReductionStrategy, int saxWindowSize, int saxPAASize,
		      int alphabetSize, double normalizationThreshold) throws Exception {

		    SAXRecords saxFrequencyData = new SAXRecords();
		    double max = findMax(timeseries);
		    double min = findMin(timeseries);
		    for (int i = 0;i<timeseries.length;i++)
		    	timeseries[i] = (timeseries[i]-min)/(max-min);
		    int n = alphabetSize-1;
		    double[] cuts = new double[n];
		    double cut = 1/(double)alphabetSize;
		    
		    for (int i = 0; i<n; i++)
		    	{
		    	cuts[i] = cut*(i+1);
		    	System.out.print(" "+cuts[i]);
		    	}
		    System.out.println("cuts:");
		    // scan across the time series extract sub sequences, and convert
		    // them to strings`
		    char[] previousString = null;
		    for (int i = 0; i < timeseries.length - (saxWindowSize - 1); i++) {

		      // fix the current subsection
		      double[] subSection = Arrays.copyOfRange(timeseries, i, i + saxWindowSize);

		      // Do not Z normalize it
		    //  subSection = TSUtils.optimizedZNorm(subSection, normalizationThreshold);

		      // perform PAA conversion if needed
		      double[] paa = TSUtils.optimizedPaa(subSection, saxPAASize);

		      // Convert the PAA to a string.
		      char[] currentString = TSUtils.ts2String(paa, cuts);
		      /*
		      if(previousString !=null)
		      {
		    	  System.out.print("previous String: ");
		    	  for (int x = 0; x< previousString.length; x++)
		    		 System.out.print(previousString[x]);
		    	  System.out.println();
		      }
		      else
		    	  System.out.println("previous String is null.");
		      System.out.print("current String: ");
		      for (int x = 0; x< currentString.length; x++)
		    		 System.out.print(currentString[x]);
		      System.out.println();
		    	  */
		      if (NumerosityReductionStrategy.EXACT.equals(numerosityReductionStrategy)
		    	//	  && (previousString[previousString.length-1]==currentString[currentString.length-1])){  -qz
		          && Arrays.equals(previousString, currentString)) {   
		    	  
		    	/*
		    	 * I think there is a bug here on Numerosity Reduction.  
		    	 */
		    	  
		        // NumerosityReduction
		        continue;
		      }
		      else if ((null != previousString)
		          && NumerosityReductionStrategy.MINDIST.equals(numerosityReductionStrategy)) {
		        double dist = SAXFactory.saxMinDist(previousString, currentString,
		            normalA.getDistanceMatrix(alphabetSize));
		        if (0.0D == dist) {
		          continue;
		        }
		      }

		      previousString = currentString;

		      saxFrequencyData.add(currentString, i);
		    }

		    return saxFrequencyData;
		  }
	private static double findMax(double[] timeseries) {
		// TODO Auto-generated method stub
		
		
		double max = timeseries[0];
		for (int i=0;i<timeseries.length;i++)
			if (max<timeseries[i])
				max = timeseries[i];
		return max;
	}
	//requires: timeseries.length>0
	private static double findMin(double[] timeseries) {
		// TODO Auto-generated method stub
		
		
		double min = timeseries[0];
		for (int i=0;i<timeseries.length;i++)
			if (min>timeseries[i])
				min = timeseries[i];
		return min;
	}

	public static ArrayList<Double> resample(ArrayList<Double> first_path,Integer n)
	{
		ArrayList<Double> first_path_sample = new ArrayList<Double>();
		Integer l=first_path.size();
		Integer cyc=l/n;
		Integer c=0;
		first_path_sample.add(first_path.get(0));
		for (Double x : first_path)
		{
			c++;
			if(c==cyc)
			{
				first_path_sample.add(x);
				c=0;
			}
		}
		first_path_sample.add(first_path.get(l-1));
		return first_path_sample;
	}
	public static ArrayList<Double> getmotif(int x,int y) throws Exception {

	    // GET ONE SPECIAL subsequence DATA
        ArrayList<Double> dl=new ArrayList<Double>();
        
        for (int i = x; i < y; i++) {
			dl.add(alt[i]);
          }
        return dl;
	  }
   
}
