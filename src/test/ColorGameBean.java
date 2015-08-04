package test;
/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import java.io.BufferedWriter;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import net.seninp.gi.GrammarRuleRecord;
import net.seninp.gi.GrammarRules;
import net.seninp.gi.RuleInterval;
//import net.seninp.gi.sequitur.SAXMotif;
import test.SAXRule;
import test.SequiturFactory;
import net.seninp.jmotif.sax.NumerosityReductionStrategy;
import test.SAXProcessor;
import test.TSProcessor;
import net.seninp.jmotif.sax.alphabet.Alphabet;
import net.seninp.jmotif.sax.alphabet.NormalAlphabet;
import net.seninp.jmotif.sax.datastructures.SAXRecords;
import net.seninp.util.StackTrace;


public class ColorGameBean {

    private String background = "yellow";
    private String foreground = "red";
    private String color1 = foreground;
    private String color2 = background;
    private String hint = "no";
    private double[] a;
    private double[] b;
    public int count=-1;
    private int attempts = 160;
        private int intval = 40;
    private boolean tookHints = false;




    public static String ts1 = "C:\\Users\\yfeng\\webapps\\GramVizWeb\\upload\\data2.txt";
	public static Alphabet normalA = new NormalAlphabet();
	public static TSProcessor tsp= new TSProcessor();
	private static final String CR = "\n";
	private static TSProcessor tp = new TSProcessor();
	private static NormalAlphabet na = new NormalAlphabet();
	private static SAXProcessor sp = new SAXProcessor();
	private static int SAX_WINDOW_SIZE = 100;
	private static int SAX_PAA_SIZE = 8;
	private static int SAX_ALPHABET_SIZE = 4;
	private static NumerosityReductionStrategy SAX_NR_STRATEGY=NumerosityReductionStrategy.NONE;
	private static double SAX_NORM_THRESHOLD=0.01;
	private static String IN_FILE="C:\\Users\\yfeng\\webapps\\GramVizWeb\\upload\\data2.txt";
	private static String OUT_FILE="out.txt";
	public static GrammarRules rules;
	private static Integer lens=0;
	public static ArrayList<ArrayList<Double>> motif_path=new ArrayList<ArrayList<Double>>();
	public static double[] series;
    public static ArrayList<Double> first_path = new ArrayList<Double>();
    public static ArrayList<Double> first_path_sample = new ArrayList<Double>();

    
    public void processRequest() {

        // background = "yellow";
        // foreground = "red";

        if (! color1.equals(foreground)) {
            if (color1.equalsIgnoreCase("black") ||
                        color1.equalsIgnoreCase("cyan")) {
                        background = color1;
                }
        }

        if (! color2.equals(background)) {
            if (color2.equalsIgnoreCase("black") ||
                        color2.equalsIgnoreCase("cyan")) {
                        foreground = color2;
            }
        }

        attempts++;
    }

    public void setColor2(String x) {
        color2 = x;
    }


    public static void getmotif() throws Exception {
		// TODO Auto-generated method stub
		//testNormalize();
		//System.out.println("Hello World");
        //        String TEST3_STRING = "acc bcc acc bcc ccc acc bcc ccc dcc acc bcc ccc dcc ecc acc bcc ccc dcc ecc fcc";
         //       SAXRule r = SequiturFactory.runSequitur(TEST3_STRING);
          //      System.out.println(r.printRules());
                series = tp.readTS(IN_FILE, 0);
                SAXRecords saxData = sp.ts2saxViaWindow(series, SAX_WINDOW_SIZE, SAX_PAA_SIZE, na.getCuts(SAX_ALPHABET_SIZE),
                        SAX_NR_STRATEGY, SAX_NORM_THRESHOLD);
                
                
                
                
                String str = saxData.getSAXString(" ");
                
                SAXRule grammar = SequiturFactory.runSequitur(str);
                
                rules = grammar.toGrammarRulesData();
                
                SequiturFactory.updateRuleIntervals(rules, saxData, true, series,
                        SAX_WINDOW_SIZE, SAX_PAA_SIZE);
                
                String fname = OUT_FILE;
                
                
                BufferedWriter bw = null;
                try {
                  bw = new BufferedWriter(new FileWriter(new File(fname)));
                  StringBuffer sb = new StringBuffer();
                  sb.append("# filename: ").append(fname).append(CR);
                  sb.append("# sliding window: ").append(SAX_WINDOW_SIZE).append(CR);
                  sb.append("# paa size: ").append(SAX_PAA_SIZE).append(CR);
                  sb.append("# alphabet size: ").append(SAX_ALPHABET_SIZE).append(CR);
                  bw.write(sb.toString());
                }
                catch (IOException e) {
                  System.err.print("Encountered an error while writing stats file: \n" + StackTrace.toString(e)
                      + "\n");
                }

                for (GrammarRuleRecord ruleRecord : rules) {

                  StringBuffer sb = new StringBuffer();
                  sb.append("/// ").append(ruleRecord.getRuleName()).append(CR);
                  sb.append(ruleRecord.getRuleName()).append(" -> \'")
                      .append(ruleRecord.getRuleString().trim()).append("\', expanded rule string: \'")
                      .append(ruleRecord.getExpandedRuleString()).append("\'").append(CR);

                  if (!ruleRecord.getOccurrences().isEmpty()) {

                    ArrayList<RuleInterval> intervals = ruleRecord.getRuleIntervals();
                    
                   
                    getmotif4Record(intervals);
                    setFirstMotif();    
                    resample(6);
                    return;
/*                    
for (int i = 0; i < intervals.size(); i++) {
                      starts[i] = intervals.get(i).getStartPos();
                      lengths[i] = intervals.get(i).getEndPos() - intervals.get(i).getStartPos();
                    }

                    sb.append("subsequences starts: ").append(Arrays.toString(starts)).append(CR);
                    sb.append("subsequences lengths: ").append(Arrays.toString(lengths)).append(CR);
                  }

                  sb.append("rule occurrence frequency ").append(ruleRecord.getOccurrences().size()).append(CR);
                  sb.append("rule use frequency ").append(ruleRecord.getRuleUseFrequency()).append(CR);
                  sb.append("min length ").append(ruleRecord.minMaxLengthAsString().split(" - ")[0]).append(CR);
                  sb.append("max length ").append(ruleRecord.minMaxLengthAsString().split(" - ")[1]).append(CR);
                  sb.append("mean length ").append(ruleRecord.getMeanLength()).append(CR);

                  if (fileOpen) {
                    try {
                      bw.write(sb.toString());
                    }
                    catch (IOException e) {
                      System.err.print("Encountered an error while writing stats file: \n"
                          + StackTrace.toString(e) + "\n");
                    }
                  }
                }
               // try to write stats into the file
                  if (fileOpen) {
                    try {
                      bw.close();
                    }
                    catch (IOException e) {
                      System.err.print("Encountered an error while writing stats file: \n"
                          + StackTrace.toString(e) + "\n");
                    }
*/
                  }
                  
                }
                
                
                //double[] series = tp.readTS(TS2GrammarParameters.IN_FILE, 0);

	}

    //a test for js
    public void getPath() {
         //for file read, it should read a upload file
         //for grammarViz, it should return motif path
        //test for number
        a=new double[2];
        b=new double[2];
        b[1]=116.33354;
        b[0]=116.33457;

        a[1]=40.00185;
        a[0]=39.98475;

        //end of get Path, path will store in arraylist in the really application

    }

    public double returnLat(){
	    count++;
	    //double temp=first_path_sample.get(count);
    	return first_path_sample.get(count).doubleValue();
	  
    }

    public double returnLon(){
	//double x=b[count];
    //    count++;
    //    return x;
    	//Test with one array
    	  return 116.33354;

    }
    public void setColor1(String x) {
        color1 = x;
    }

    public void setAction(String x) {
        if (!tookHints)
            tookHints = x.equalsIgnoreCase("Hint");
        hint = x;
    }

    public String getColor2() {
         return background;
    }

    public String getColor1() {
         return foreground;
    }

    public int getAttempts() {
        return attempts;
    }

    public Integer getLens() {
    	return lens;
    }
    public boolean getHint() {
        return hint.equalsIgnoreCase("Hint");
    }

    public boolean getSuccess() {
        if (background.equalsIgnoreCase("black") ||
            background.equalsIgnoreCase("cyan")) {

            if (foreground.equalsIgnoreCase("black") ||
                foreground.equalsIgnoreCase("cyan")) {
                return true;
            }
            return false;
        }

        return false;
    }

    public boolean getHintTaken() {
        return tookHints;
    }

    public void reset() {
        foreground = "red";
        background = "yellow";
    }

    public void setIntval(int value) {
        intval = value;
        }

    public int getIntval() {
        return intval;
        }
    public void clear()
    {
       count=0;
    }
	public static void getmotif4Record(ArrayList<RuleInterval> intervals) throws Exception {

	    // GET ONE SPECIAL MOTIF DATA
		;
	    for(RuleInterval rl : intervals)
	    {
	    	if(lens==0)
	    		lens=rl.getEndPos()-rl.getStartPos();
	    	getmotif(rl.getStartPos(),rl.getEndPos());
	    }

	  }

	public static void getmotif(int x,int y) throws Exception {

	    // GET ONE SPECIAL subsequence DATA
        ArrayList<Double> dl=new ArrayList<Double>();
		for (int i = x; i < y; i++) {
			dl.add(series[i]);
          }
		motif_path.add(dl);
	  }
   
	public static void setFirstMotif() throws Exception {
		// implement after getmotif4Record
		first_path=motif_path.get(0);
	}
	public static void resample(Integer n)
	{
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
	}
}

