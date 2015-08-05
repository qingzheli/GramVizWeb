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
import test.SequiturInterface;

public class ColorGameBean {

    private String background = "yellow";
    private String foreground = "red";
    private String color1 = foreground;
    private String color2 = background;
    private String hint = "no";
    private double[] a;
    private double[] b;
    public int count=0;
    private int attempts = 160;
        private int intval = 40;
    private boolean tookHints = false;




    //public static String ts1 = "C:\\Users\\yfeng\\webapps\\GramVizWeb\\upload\\data2.txt";
	public static Alphabet normalA = new NormalAlphabet();
	//public static TSProcessor tp_L= new TSProcessor();
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
	private static String IN_FILE_L="C:\\Users\\yfeng\\webapps\\GramVizWeb\\upload\\data1.txt";
	
	private static String OUT_FILE="out.txt";
	public static GrammarRules rules;
	private static Integer lens=0;
	public static ArrayList<ArrayList<Double>> motif_path=new ArrayList<ArrayList<Double>>();
	public static ArrayList<ArrayList<Double>> motif_pathL=new ArrayList<ArrayList<Double>>();
	
	public static double[] series;
	public static double[] seriesL;
	
    public static ArrayList<Double> first_path = new ArrayList<Double>();
    public static ArrayList<Double> first_path_sample = new ArrayList<Double>();
    public static ArrayList<Double> first_path_L = new ArrayList<Double>();
    public static ArrayList<Double> first_path_sample_L = new ArrayList<Double>();
    
    
    public static ArrayList<Double> second_path = new ArrayList<Double>();
    public static ArrayList<Double> second_path_sample = new ArrayList<Double>();
    public static ArrayList<Double> second_path_L = new ArrayList<Double>();
    public static ArrayList<Double> second_path_sample_L = new ArrayList<Double>();
    
    public static int count2=0;
    private static Integer sample_rate=6;
	
    

    public void setColor2(String x) {
        color2 = x;
    }


    public static void run() throws Exception {
    	
		// TODO Auto-generated method stub
		//testNormalize();
		//System.out.println("Hello World");
        //        String TEST3_STRING = "acc bcc acc bcc ccc acc bcc ccc dcc acc bcc ccc dcc ecc acc bcc ccc dcc ecc fcc";
         //       SAXRule r = SequiturFactory.runSequitur(TEST3_STRING);
          //      System.out.println(r.printRules());
    	SequiturInterface.run();
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
	    
	    //double temp=first_path_sample.get(count);
    	return first_path_sample.get(count).doubleValue();
	  
    }

    public double returnLon(){
	//double x=b[count];
    //    count++;
    //    return x;
    	//Test with one array
    	double x=first_path_sample_L.get(count).doubleValue();
    	count++;
    	  return x;

    }
    
    
public double returnLat2(){
	    
	    //double temp=first_path_sample.get(count);
    	return second_path_sample.get(count2).doubleValue();
	  
    }

    public double returnLon2(){
	//double x=b[count];
    //    count++;
    //    return x;
    	//Test with one array
    	double x=second_path_sample_L.get(count2).doubleValue();
    	count2++;
    	  return x;

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

    public void setSampleRate(int value) {
        sample_rate = value;
        }

    
    public int getIntval() {
        return intval;
        }
    public void clear()
    {
       count=0;
       count2=0;
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
        ArrayList<Double> dlL=new ArrayList<Double>();
		
        for (int i = x; i < y; i++) {
			dl.add(series[i]);
			dlL.add(seriesL[i]);
          }
		motif_path.add(dl);
		motif_pathL.add(dlL);
		
	  }
   
	public static void setFirstMotif() throws Exception {
		// implement after getmotif4Record
		first_path=motif_path.get(0);
		first_path_L=motif_pathL.get(0);
	}
	
	public static void setIMotif() throws Exception {
		// implement after getmotif4Record
		second_path=motif_path.get(1);
		second_path_L=motif_pathL.get(1);
	}
	
	public static void resample(Integer n)
	{
		Integer l=first_path.size();
		Integer cyc=l/n;
		Integer c=0;
	    int k=0;
		first_path_sample.add(first_path.get(0));
		for (Double x : first_path)
		{
			c++;
			if(c==cyc)
			{
				first_path_sample.add(x);
				first_path_sample_L.add(first_path_L.get(k));
				c=0;
			}
			k++;
		}
		first_path_sample.add(first_path.get(l-1));
		first_path_sample_L.add(first_path_L.get(l-1));
	}
	
	
	public static void resample2(Integer n)
	{
		Integer l=second_path.size();
		Integer cyc=l/n;
		Integer c=0;
	    int k=0;
		second_path_sample.add(second_path.get(0));
		for (Double x : second_path)
		{
			c++;
			if(c==cyc)
			{
				second_path_sample.add(x);
				second_path_sample_L.add(second_path_L.get(k));
				c=0;
			}
			k++;
		}
		second_path_sample.add(second_path.get(l-1));
		second_path_sample_L.add(second_path_L.get(l-1));
	
     }
}

