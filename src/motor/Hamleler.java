package motor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Hamleler {
	Beyaz beyaz = new Beyaz();
	Siyah siyah = new Siyah();
	List<Integer> motorHamle = new ArrayList<>(), gecerkenAlma = new ArrayList<>();
	List<Integer> sahMudaheleKare = new LinkedList<>();
	List<Integer> sahTehtid = new ArrayList<>(
			  Arrays.asList(0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0));	
	public int captures = 0, toplamSahSayisi = 0, gecerkenAl = 0, rok = 0, mat = 0;	
	public void arama(boolean beyazMiSiyahMi, int[] tahta, PerformansTest p) {		
		motorHamle.clear();		
		sahTehtid(tahta, p);
		if (beyazMiSiyahMi) {
			motorHamle.addAll(beyaz.hamleleriAl(tahta, sahTehtid, p));
		} else {
			motorHamle.addAll(siyah.hamleleriAl(tahta, sahTehtid, p));
		}		
	}
		
	public void sahTehtidReset() {     	    	
   	 sahTehtid = new ArrayList<>(
				  Arrays.asList(0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0
						   		));   	 
   }
	public void sahTehtid(int[] tahta, PerformansTest p) {		
    	sahTehtidReset();    	
    	for (byte i = 0;i < tahta.length;i++) {    		
    		switch (tahta[i]) {
    		case 900:
    			sahTehtidSah(i,tahta,p);
    			break;
    		case -900:
    			sahTehtidSah(i,tahta,p);
    			break;
    		case 3:
    			sahTehtidAt(i,tahta,p);
    			break;
    		case -3:
    			sahTehtidAt(i,tahta,p);
    			break;
    		case 4:
    			beyazSahTehtidCapraz(i,tahta,p);
    			break;
    		case -4:
    			siyahSahTehtidCapraz(i,tahta,p);
    			break;
    		case 5:    			
    			beyazSahTehtidYatayVeDikey(i,tahta,p);    			
    			break;
    		case -5:
    			siyahSahTehtidYatayVeDikey(i,tahta,p);
    			break;
    		case 9:    			
    			beyazSahTehtidCapraz(i,tahta,p);    			
    			beyazSahTehtidYatayVeDikey(i,tahta,p);
    			break;
    		case -9:    			
    			siyahSahTehtidCapraz(i,tahta,p);
    			siyahSahTehtidYatayVeDikey(i,tahta,p);    			
    			break;
    		case 1:
    			sahTehtidBeyazPiyon(i,tahta,p);
    			break;
    		case -1:
    			sahTehtidSiyahPiyon(i,tahta,p);
    			break;
    		}
    	}    	
    }			
	public void sahTehtidAyarla(double i, int kare, int sahCekenTas, int[] tahta, PerformansTest p) {    	
    	if (i > 0) {    	
    		sahTehtid.set(kare, sahTehtid.get(kare)+10);
    		if (tahta[kare] == -900) {    			
    			toplamSahSayisi++;    			    			
    			p.setSiyahSahCekis(p.getSiyahSahCekis()+1);    			
    			siyah.sahCekenTasNerede(sahCekenTas,tahta[sahCekenTas],p);
    		}
    	}
    	else {
    		sahTehtid.set(kare, sahTehtid.get(kare)+1);
    		if (tahta[kare] == 900) {     			
    			toplamSahSayisi++;    			
    			p.setBeyazSahCekis(p.getBeyazSahCekis()+1);
    			beyaz.sahCekenTasNerede(sahCekenTas,tahta[sahCekenTas],p);
    		}
    	}
    }
	public void sahTehtidBeyazPiyon(int i, int[] tahta, PerformansTest p) {
		if (i%8-1 > -1 && i/8-1 > -1) {sahTehtidAyarla(tahta[i], (i/8-1)*8+i%8-1,i,tahta,p);}    			
		if (i%8+1 < 8 &&  i/8-1 > -1) {sahTehtidAyarla(tahta[i], (i/8-1)*8+i%8+1,i,tahta,p);}
	}
	public void sahTehtidSiyahPiyon(int i, int[] tahta, PerformansTest p) {
		if (i%8-1 > -1 && i/8+1 < 8) {sahTehtidAyarla(tahta[i], (i/8+1)*8+i%8-1,i,tahta,p);}    			
		if (i%8+1 < 8  && i/8+1 < 8) {sahTehtidAyarla(tahta[i], (i/8+1)*8+i%8+1,i,tahta,p);}
	}
	public void sahTehtidAt(int i, int[] tahta, PerformansTest p) {
		if (i/8-2 > -1 && i%8-1 > -1) {sahTehtidAyarla(tahta[i], (i/8-2)*8+i%8-1,i,tahta,p);}	
		if (i/8-1 > -1 && i%8-2 > -1) {sahTehtidAyarla(tahta[i], (i/8-1)*8+i%8-2,i,tahta,p);}
		if (i/8-1 > -1 && i%8+2 < 8) {sahTehtidAyarla(tahta[i], (i/8-1)*8+i%8+2,i,tahta,p);}
		if (i/8-2 > -1 && i%8+1 < 8) {sahTehtidAyarla(tahta[i], (i/8-2)*8+i%8+1,i,tahta,p);}
		if (i/8+2 < 8 && i%8+1 < 8) {sahTehtidAyarla(tahta[i], (i/8+2)*8+i%8+1,i,tahta,p);}
		if (i/8+2 < 8 && i%8-1 > -1) {sahTehtidAyarla(tahta[i], (i/8+2)*8+i%8-1,i,tahta,p);}
		if (i/8+1 < 8 && i%8-2 > -1) {sahTehtidAyarla(tahta[i], (i/8+1)*8+i%8-2,i,tahta,p);}
		if (i/8+1 < 8 && i%8+2 < 8) {sahTehtidAyarla(tahta[i], (i/8+1)*8+i%8+2,i,tahta,p);}
	}
	public void sahTehtidSah(int i, int[] tahta, PerformansTest p) {
		if (i/8-1 > -1) {sahTehtidAyarla(tahta[i], (i/8-1)*8+i%8,i,tahta,p);}
		if (i/8+1 <  8) {sahTehtidAyarla(tahta[i], (i/8+1)*8+i%8,i,tahta,p);}
		if (i%8+1 <  8) {sahTehtidAyarla(tahta[i], (i/8)*8+i%8+1,i,tahta,p);}
		if (i%8-1 > -1) {sahTehtidAyarla(tahta[i], (i/8)*8+i%8-1,i,tahta,p);}
		if (i%8-1 > -1 && i/8+1 < 8)  {sahTehtidAyarla(tahta[i], (i/8+1)*8+i%8-1,i,tahta,p);}
		if (i%8+1 < 8  && i/8+1 < 8)  {sahTehtidAyarla(tahta[i], (i/8+1)*8+i%8+1,i,tahta,p);}
		if (i%8+1 < 8  && i/8-1 > -1) {sahTehtidAyarla(tahta[i], (i/8-1)*8+i%8+1,i,tahta,p);}
		if (i%8-1 > -1 && i/8-1 > -1) {sahTehtidAyarla(tahta[i], (i/8-1)*8+i%8-1,i,tahta,p);}
	}	
	public void beyazSahTehtidCapraz(int i, int[] tahta, PerformansTest p) {
		 for (int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1;solAltKose <= 7 || solUstKose <= 7 || sagUstKose <= 7 || sagAltKose <= 7;solAltKose++,solUstKose++,sagUstKose++,sagAltKose++) {    			    			 	    			
			  if ((i/8-solUstKose > -1 && i%8-solUstKose > -1) && (tahta[(i/8-solUstKose)*8+i%8-solUstKose] == 0 || (tahta[(i/8-solUstKose)*8+i%8-solUstKose] == -900 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] > 0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] < 900 ))) {sahTehtidAyarla(tahta[i],(i/8-solUstKose)*8+i%8-solUstKose,i,tahta,p);if (tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] != -900.0) {solUstKose = 8;}} else {solUstKose = 8;}	    				    		
			  if ((i/8+solAltKose < 8 && i%8-solAltKose > -1)  && (tahta[(i/8+solAltKose)*8+i%8-solAltKose] == 0 || (tahta[(i/8+solAltKose)*8+i%8-solAltKose] == -900 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] > 0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] < 900 ))) {sahTehtidAyarla(tahta[i],(i/8+solAltKose)*8+i%8-solAltKose,i,tahta,p);if (tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] != -900.0) {solAltKose = 8;}} else {solAltKose = 8;}	    				    		
			  if ((i/8-sagUstKose > -1 && i%8+sagUstKose < 8)  && (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == 0 || (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == -900 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] > 0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] < 900 ))) {sahTehtidAyarla(tahta[i],(i/8-sagUstKose)*8+i%8+sagUstKose,i,tahta,p);if (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != -900.0) {sagUstKose = 8;}} else {sagUstKose = 8;}	    				    		
			  if ((i/8+sagAltKose < 8 && i%8+sagAltKose < 8)   && (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == 0 || (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == -900 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] > 0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] < 900 ))) {sahTehtidAyarla(tahta[i],(i/8+sagAltKose)*8+i%8+sagAltKose,i,tahta,p);if (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != -900.0) {sagAltKose = 8;}} else {sagAltKose = 8;}	    			
		 } 
	 }
	public void beyazSahTehtidYatayVeDikey(int i, int[] tahta, PerformansTest p) {
		 for (int ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1;ustDikey <= i/8 || altDikey <= 7-i/8 || solYatay <= i%8 || sagYatay <= 7-i%8;solYatay++,sagYatay++,ustDikey++,altDikey++) {    				    				    				    				    			    				    				    				    				    				    				    	    			
		     if ((sagYatay <= 7-i%8) && (tahta[((i/8)*8)+i%8+sagYatay] == 0 || (tahta[((i/8)*8)+i%8+sagYatay] == -900 || tahta[((i/8)*8)+i%8+sagYatay] > 0 && tahta[((i/8)*8)+i%8+sagYatay] < 900))) {sahTehtidAyarla(tahta[i],((i/8)*8)+i%8+sagYatay,i,tahta,p);if (tahta[((i/8)*8)+i%8+sagYatay] != 0.0 && tahta[(((i/8))*8)+i%8+sagYatay] != -900.0) {sagYatay = 8-i%8;}} else {sagYatay= 8-i%8;}	    				    		
			 if ((solYatay <= i%8)   && (tahta[((i/8)*8)+i%8-solYatay] == 0 || (tahta[((i/8)*8)+i%8-solYatay] == -900 || tahta[((i/8)*8)+i%8-solYatay] > 0 && tahta[((i/8)*8)+i%8-solYatay] < 900))) {sahTehtidAyarla(tahta[i],((i/8)*8)+i%8-solYatay,i,tahta,p);if (tahta[((i/8)*8)+i%8-solYatay] != 0.0 && tahta[(((i/8))*8)+i%8-solYatay] != -900.0) {solYatay = i%8+1;}} else {solYatay= i%8+1;}	    				    				    				    	
			 if ((ustDikey <= i/8)   && (tahta[((i/8-ustDikey)*8)+i%8] == 0 || (tahta[((i/8-ustDikey)*8)+i%8] == -900 || tahta[((i/8-ustDikey)*8)+i%8] > 0 && tahta[((i/8-ustDikey)*8)+i%8] < 900))) {sahTehtidAyarla(tahta[i],((i/8-ustDikey)*8)+i%8,i,tahta,p);if (tahta[((i/8-ustDikey)*8)+i%8] != 0.0 && tahta[(((i/8)-ustDikey)*8)+i%8] != -900.0) {ustDikey = i/8+1;}} else {ustDikey= i/8+1;}	    				    		
			 if ((altDikey <= 7-i/8) && (tahta[((i/8+altDikey)*8)+i%8] == 0 || (tahta[((i/8+altDikey)*8)+i%8] == -900 || tahta[((i/8+altDikey)*8)+i%8] > 0 && tahta[((i/8+altDikey)*8)+i%8] < 900))) {sahTehtidAyarla(tahta[i],((i/8+altDikey)*8)+i%8,i,tahta,p);if (tahta[((i/8+altDikey)*8)+i%8] != 0.0 && tahta[(((i/8)+altDikey)*8)+i%8] != -900.0) {altDikey = 8-i/8;}} else {altDikey= 8-i/8;}			 
		 }
	}
	public void siyahSahTehtidCapraz(int i, int[] tahta, PerformansTest p) {
			for (int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1;solAltKose <= 7 || solUstKose <= 7 || sagUstKose <= 7 || sagAltKose <= 7;solAltKose++,solUstKose++,sagUstKose++,sagAltKose++) {    			    			 	    			
				if ((i/8-solUstKose > -1 && i%8-solUstKose > -1) && (tahta[(i/8-solUstKose)*8+i%8-solUstKose] == 0 || (tahta[(i/8-solUstKose)*8+i%8-solUstKose] == 900 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] < 0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] > -900 ))) {sahTehtidAyarla(tahta[i],(i/8-solUstKose)*8+i%8-solUstKose,i,tahta,p);if (tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 900.0) {solUstKose = 8;}} else {solUstKose = 8;}	    				    		
				if ((i/8+solAltKose < 8 && i%8-solAltKose > -1)  && (tahta[(i/8+solAltKose)*8+i%8-solAltKose] == 0 || (tahta[(i/8+solAltKose)*8+i%8-solAltKose] == 900 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] < 0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] > -900 ))) {sahTehtidAyarla(tahta[i],(i/8+solAltKose)*8+i%8-solAltKose,i,tahta,p);if (tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 900.0) {solAltKose = 8;}} else {solAltKose = 8;}	    				    		
				if ((i/8-sagUstKose > -1 && i%8+sagUstKose < 8)  && (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == 0 || (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == 900 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] < 0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] > -900 ))) {sahTehtidAyarla(tahta[i],(i/8-sagUstKose)*8+i%8+sagUstKose,i,tahta,p);if (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 900.0) {sagUstKose = 8;}} else {sagUstKose = 8;}	    				    		
				if ((i/8+sagAltKose < 8 && i%8+sagAltKose < 8)   && (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == 0 || (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == 900 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] < 0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] > -900 ))) {sahTehtidAyarla(tahta[i],(i/8+sagAltKose)*8+i%8+sagAltKose,i,tahta,p);if (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 900.0) {sagAltKose = 8;}} else {sagAltKose = 8;}	    			
			} 
	}
	public void siyahSahTehtidYatayVeDikey(int i, int[] tahta, PerformansTest p) {
			for (int ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1;ustDikey <= i/8 || altDikey <= 7-i/8 || solYatay <= i%8 || sagYatay <= 7-i%8;solYatay++,sagYatay++,ustDikey++,altDikey++) {    				    				    				    				    			    				    				    				    				    				    				    	    			
				if ((sagYatay <= 7-i%8) && (tahta[((i/8)*8)+i%8+sagYatay] == 0 || (tahta[((i/8)*8)+i%8+sagYatay] == 900 || tahta[((i/8)*8)+i%8+sagYatay] < 0 && tahta[((i/8)*8)+i%8+sagYatay] > -900))) {sahTehtidAyarla(tahta[i],((i/8)*8)+i%8+sagYatay,i,tahta,p);if (tahta[((i/8)*8)+i%8+sagYatay] != 0.0 && tahta[(((i/8))*8)+i%8+sagYatay] != 900.0) {sagYatay = 8-i%8;}} else {sagYatay= 8-i%8;}	    				    		
				if ((solYatay <= i%8)   && (tahta[((i/8)*8)+i%8-solYatay] == 0 || (tahta[((i/8)*8)+i%8-solYatay] == 900 || tahta[((i/8)*8)+i%8-solYatay] < 0 && tahta[((i/8)*8)+i%8-solYatay] > -900))) {sahTehtidAyarla(tahta[i],((i/8)*8)+i%8-solYatay,i,tahta,p);if (tahta[((i/8)*8)+i%8-solYatay] != 0.0 && tahta[(((i/8))*8)+i%8-solYatay] != 900.0) {solYatay = i%8+1;}} else {solYatay= i%8+1;}	    				    				    				    	
				if ((ustDikey <= i/8)   && (tahta[((i/8-ustDikey)*8)+i%8] == 0 || (tahta[((i/8-ustDikey)*8)+i%8] == 900 || tahta[((i/8-ustDikey)*8)+i%8] < 0 && tahta[((i/8-ustDikey)*8)+i%8] > -900))) {sahTehtidAyarla(tahta[i],((i/8-ustDikey)*8)+i%8,i,tahta,p);if (tahta[((i/8-ustDikey)*8)+i%8] != 0.0 && tahta[(((i/8)-ustDikey)*8)+i%8] != 900.0) {ustDikey = i/8+1;}} else {ustDikey= i/8+1;}	    				    		
				if ((altDikey <= 7-i/8) && (tahta[((i/8+altDikey)*8)+i%8] == 0 || (tahta[((i/8+altDikey)*8)+i%8] == 900 || tahta[((i/8+altDikey)*8)+i%8] < 0 && tahta[((i/8+altDikey)*8)+i%8] > -900))) {sahTehtidAyarla(tahta[i],((i/8+altDikey)*8)+i%8,i,tahta,p);if (tahta[((i/8+altDikey)*8)+i%8] != 0.0 && tahta[(((i/8)+altDikey)*8)+i%8] != 900.0) {altDikey = 8-i/8;}} else {altDikey= 8-i/8;}	    			
			}
	}	
	public int[] hamleleriAl(boolean siraKimde, int[] tahta, PerformansTest p) {				
		p.setBeyazSahCekis(0);
		p.setSiyahSahCekis(0);
		arama(siraKimde, tahta, p);
		if (motorHamle.isEmpty()) {mat++;}		
		return motorHamle.stream().mapToInt(i->i).toArray();
	}	
}