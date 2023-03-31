package motor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hamleler {
	Beyaz beyaz = new Beyaz();
	Siyah siyah = new Siyah();	
	List<Integer> sahTehtid = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));	
	public int captures = 0, toplamSahSayisi = 0, gecerkenAl = 0, rok = 0, mat = 0;	
	/*public void arama(boolean beyazMiSiyahMi, int[] tahta, PerformansTest p) {		
		motorHamle.clear();		
		sahTehtid(tahta, p);		
		if (beyazMiSiyahMi) {			
			motorHamle.addAll(beyaz.hamleleriAl(tahta, sahTehtid, p));
		} else {			
			motorHamle.addAll(siyah.hamleleriAl(tahta, sahTehtid, p));
		}		
	}*/	
	public List<Integer> arama(boolean beyazMiSiyahMi, int[] tahta, PerformansTest p) {
		sahTehtid(tahta,p);
		if (beyazMiSiyahMi) {
			return beyaz.hamleleriAl(tahta, sahTehtid, p);
		}
		return siyah.hamleleriAl(tahta, sahTehtid, p);
	}
	public void sahTehtidReset() {
		sahTehtid = Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);		 	 	  
    }
	public void sahTehtid(int[] tahta, PerformansTest p) {		
    	sahTehtidReset();   	    	    	
    	for (int i = 0;i < 64;i++) {
    		if (tahta[i] != 0) {
    			if (tahta[i] == 1) {
    				sahTehtidBeyazPiyon(i,i%8,i/8,tahta,p);
    			} else if (tahta[i] == -1) {
    				sahTehtidSiyahPiyon(i,i%8,i/8,tahta,p);
    			} else if (tahta[i] == 3) {
    				beyazSahTehtidAt(i,i%8,i/8,tahta,p);
    			} else if (tahta[i] == -3) {
    				siyahSahTehtidAt(i,i%8,i/8,tahta,p);
    			} else if (tahta[i] == 4) {
    				beyazSahTehtidCapraz(i,tahta,p);
    			} else if (tahta[i] == -4) {
    				siyahSahTehtidCapraz(i,tahta,p);
    			} else if (tahta[i] == 5) {
    				beyazSahTehtidYatayVeDikey(i,tahta,p);
    			} else if (tahta[i] == -5) {
    				siyahSahTehtidYatayVeDikey(i,tahta,p);
    			} else if (tahta[i] == 9) {
    				beyazSahTehtidCapraz(i,tahta,p);
    				beyazSahTehtidYatayVeDikey(i,tahta,p);
    			} else if (tahta[i] == -9) {
    				siyahSahTehtidCapraz(i,tahta,p);
    				siyahSahTehtidYatayVeDikey(i,tahta,p);
    			} else if (tahta[i] == 900) {
    				beyazSahTehtidSah(i,tahta,p);
    			} else if (tahta[i] == -900) {
    				siyahSahTehtidSah(i,tahta,p);
    			}
    		}
    	}    		
    }	
	public void beyazSahTehtidAyarla(int kare, int sahCekenTas, int[] tahta, PerformansTest p) {		
		sahTehtid.set(kare, sahTehtid.get(kare)+10);
		if (tahta[kare] == -900) {    			
			toplamSahSayisi++;    			    			
			p.setSiyahSahCekis(p.getSiyahSahCekis()+1);    			
			siyah.sahCekenTasNerede(sahCekenTas,tahta[sahCekenTas],p);
		}
	}
	public void siyahSahTehtidAyarla(int kare, int sahCekenTas, int[] tahta, PerformansTest p) {		
		sahTehtid.set(kare, sahTehtid.get(kare)+1);
		if (tahta[kare] == 900) {     			
			toplamSahSayisi++;    			
			p.setBeyazSahCekis(p.getBeyazSahCekis()+1);
			beyaz.sahCekenTasNerede(sahCekenTas,tahta[sahCekenTas],p);
		}
	}
	public void sahTehtidBeyazPiyon(int tasKonumu, int x, int y, int[] tahta, PerformansTest p) {		
		if (y > 0) {
			if (x < 7) {
				beyazSahTehtidAyarla(tasKonumu-7,tasKonumu,tahta,p);
			}
			if (x > 0) {
				beyazSahTehtidAyarla(tasKonumu-9,tasKonumu,tahta,p);
			}
		}
	}
	public void sahTehtidSiyahPiyon(int tasKonumu, int x, int y, int[] tahta, PerformansTest p) {		    			
		if (y < 7) {
			if (x > 0) {
				siyahSahTehtidAyarla(tasKonumu+7,tasKonumu,tahta,p);
			}
			if (x < 7) {
				siyahSahTehtidAyarla(tasKonumu+9,tasKonumu,tahta,p);
			}
		}
	}
	public void beyazSahTehtidAt(int tasKonumu, int x, int y, int[] tahta, PerformansTest p) {	  
		if (y > 1) {
			if (x > 0) {
				beyazSahTehtidAyarla(tasKonumu-17,tasKonumu,tahta,p);
			}
			if (x < 7) {
				beyazSahTehtidAyarla(tasKonumu-15,tasKonumu,tahta,p);
			}
		}
		if (y < 6) {
			if (x < 7) {
				beyazSahTehtidAyarla(tasKonumu+17,tasKonumu,tahta,p);
			}
			if (x > 0) {
				beyazSahTehtidAyarla(tasKonumu+15,tasKonumu,tahta,p);
			}
		}
		if (x > 1) {
			if (y > 0) {
				beyazSahTehtidAyarla(tasKonumu-10,tasKonumu,tahta,p);
			}
			if (y < 7) {
				beyazSahTehtidAyarla(tasKonumu+6,tasKonumu,tahta,p);
			}
		}
		if (x < 6) {
			if (y > 0) {
				beyazSahTehtidAyarla(tasKonumu-6,tasKonumu,tahta,p);
			}
			if (y < 7) {
				beyazSahTehtidAyarla(tasKonumu+10,tasKonumu,tahta,p);
			}
		}
	}	
	public void siyahSahTehtidAt(int tasKonumu, int x, int y, int[] tahta, PerformansTest p) {
		if (y > 1) {
			if (x > 0) {
				siyahSahTehtidAyarla(tasKonumu-17,tasKonumu,tahta,p);
			}
			if (x < 7) {
				siyahSahTehtidAyarla(tasKonumu-15,tasKonumu,tahta,p);
			}
		}
		if (y < 6) {
			if (x < 7) {
				siyahSahTehtidAyarla(tasKonumu+17,tasKonumu,tahta,p);
			}
			if (x > 0) {
				siyahSahTehtidAyarla(tasKonumu+15,tasKonumu,tahta,p);
			}
		}
		if (x > 1) {
			if (y > 0) {
				siyahSahTehtidAyarla(tasKonumu-10,tasKonumu,tahta,p);
			}
			if (y < 7) {
				siyahSahTehtidAyarla(tasKonumu+6,tasKonumu,tahta,p);
			}
		}
		if (x < 6) {
			if (y > 0) {
				siyahSahTehtidAyarla(tasKonumu-6,tasKonumu,tahta,p);
			}
			if (y < 7) {
				siyahSahTehtidAyarla(tasKonumu+10,tasKonumu,tahta,p);
			}
		}
	}	
	public void beyazSahTehtidSah(int tasKonumu, int[] tahta, PerformansTest p) {
		if (tasKonumu/8-1 > -1) {beyazSahTehtidAyarla(tasKonumu-8,tasKonumu,tahta,p);}
		if (tasKonumu/8+1 <  8) {beyazSahTehtidAyarla(tasKonumu+8,tasKonumu,tahta,p);}
		if (tasKonumu%8+1 <  8) {beyazSahTehtidAyarla(tasKonumu+1,tasKonumu,tahta,p);}
		if (tasKonumu%8-1 > -1) {beyazSahTehtidAyarla(tasKonumu-1,tasKonumu,tahta,p);}
		if (tasKonumu%8-1 > -1 && tasKonumu/8+1 < 8) {beyazSahTehtidAyarla(tasKonumu+7,tasKonumu,tahta,p);}
		if (tasKonumu%8+1 < 8  && tasKonumu/8+1 < 8) {beyazSahTehtidAyarla(tasKonumu+9,tasKonumu,tahta,p);}
		if (tasKonumu%8+1 < 8  && tasKonumu/8-1 > -1) {beyazSahTehtidAyarla(tasKonumu-7,tasKonumu,tahta,p);}
		if (tasKonumu%8-1 > -1 && tasKonumu/8-1 > -1) {beyazSahTehtidAyarla(tasKonumu-9,tasKonumu,tahta,p);}
	}	
	public void siyahSahTehtidSah(int tasKonumu, int[] tahta, PerformansTest p) {
		if (tasKonumu/8-1 > -1) {siyahSahTehtidAyarla(tasKonumu-8,tasKonumu,tahta,p);}
		if (tasKonumu/8+1 <  8) {siyahSahTehtidAyarla(tasKonumu+8,tasKonumu,tahta,p);}
		if (tasKonumu%8+1 <  8) {siyahSahTehtidAyarla(tasKonumu+1,tasKonumu,tahta,p);}
		if (tasKonumu%8-1 > -1) {siyahSahTehtidAyarla(tasKonumu-1,tasKonumu,tahta,p);}
		if (tasKonumu%8-1 > -1 && tasKonumu/8+1 < 8) {siyahSahTehtidAyarla(tasKonumu+7,tasKonumu,tahta,p);}
		if (tasKonumu%8+1 < 8  && tasKonumu/8+1 < 8) {siyahSahTehtidAyarla(tasKonumu+9,tasKonumu,tahta,p);}
		if (tasKonumu%8+1 < 8  && tasKonumu/8-1 > -1) {siyahSahTehtidAyarla(tasKonumu-7,tasKonumu,tahta,p);}
		if (tasKonumu%8-1 > -1 && tasKonumu/8-1 > -1) {siyahSahTehtidAyarla(tasKonumu-9,tasKonumu,tahta,p);}
	}	
	public void beyazSahTehtidCapraz(int tasKonumu, int[] tahta, PerformansTest p) {		 
		beyazSolAltKose(tasKonumu,tasKonumu%8,tasKonumu/8,tahta,p);
		beyazSolUstKose(tasKonumu,tasKonumu%8,tasKonumu/8,tahta,p);
		beyazSagUstKose(tasKonumu,tasKonumu%8,tasKonumu/8,tahta,p);
		beyazSagAltKose(tasKonumu,tasKonumu%8,tasKonumu/8,tahta,p);
	}
	public void beyazSolAltKose(int tasKonumu,int x ,int y, int[] tahta, PerformansTest p) {		
		for (int solAltKose = 1;y+solAltKose < 8 && x-solAltKose > -1;solAltKose++) {
			if (tahta[tasKonumu+solAltKose*8-solAltKose] == -900 || tahta[tasKonumu+solAltKose*8-solAltKose] > -1) {beyazSahTehtidAyarla(tasKonumu+solAltKose*8-solAltKose,tasKonumu,tahta,p);if (tahta[tasKonumu+solAltKose*8-solAltKose] != 0.0 && tahta[tasKonumu+solAltKose*8-solAltKose] != -900.0) {return;}} else {break;}
		}
	}
	public void beyazSolUstKose(int tasKonumu,int x ,int y, int[] tahta, PerformansTest p) {		
		for (int solUstKose = 1;y-solUstKose > -1 && x-solUstKose > -1;solUstKose++) {
			if (tahta[tasKonumu-solUstKose*8-solUstKose] == -900 || tahta[tasKonumu-solUstKose*8-solUstKose] > -1) {beyazSahTehtidAyarla(tasKonumu-solUstKose*8-solUstKose,tasKonumu,tahta,p);if (tahta[tasKonumu-solUstKose*8-solUstKose] != 0.0 && tahta[tasKonumu-solUstKose*8-solUstKose] != -900.0) {return;}} else {break;}
			
		}
	}
	public void beyazSagUstKose(int tasKonumu,int x ,int y, int[] tahta, PerformansTest p) {		
		for (int sagUstKose = 1;y-sagUstKose > -1 && x+sagUstKose < 8;sagUstKose++) {
			if (tahta[tasKonumu-sagUstKose*8+sagUstKose] == -900 || tahta[tasKonumu-sagUstKose*8+sagUstKose] > -1) {beyazSahTehtidAyarla(tasKonumu-sagUstKose*8+sagUstKose,tasKonumu,tahta,p);if (tahta[tasKonumu-sagUstKose*8+sagUstKose] != 0.0 && tahta[tasKonumu-sagUstKose*8+sagUstKose] != -900.0) {return;}} else {break;}			
		}
	}
	public void beyazSagAltKose(int tasKonumu,int x ,int y, int[] tahta, PerformansTest p) {		
		for (int sagAltKose = 1;y+sagAltKose < 8 && x+sagAltKose < 8;sagAltKose++) {
			if (tahta[tasKonumu+sagAltKose*8+sagAltKose] == -900 || tahta[tasKonumu+sagAltKose*8+sagAltKose] > -1) {beyazSahTehtidAyarla(tasKonumu+sagAltKose*8+sagAltKose,tasKonumu,tahta,p);if (tahta[tasKonumu+sagAltKose*8+sagAltKose] != 0.0 && tahta[tasKonumu+sagAltKose*8+sagAltKose] != -900.0) {return;}} else {break;}			
		}
	}	
	public void beyazSahTehtidYatayVeDikey(int tasKonumu, int[] tahta, PerformansTest p) {		
		beyazSagYatay(tasKonumu,tasKonumu%8,tahta,p);
		beyazSolYatay(tasKonumu,tasKonumu%8,tahta,p);
		beyazUstDikey(tasKonumu,tasKonumu/8,tahta,p);
		beyazAltDikey(tasKonumu,tasKonumu/8,tahta,p);
	}
	public void beyazSagYatay(int tasKonumu,int x ,int[] tahta, PerformansTest p) {		
		for (int sagYatay = 1;sagYatay <= 7-x;sagYatay++) {
			if (tahta[tasKonumu+sagYatay] == -900 || tahta[tasKonumu+sagYatay] > -1) {beyazSahTehtidAyarla(tasKonumu+sagYatay,tasKonumu,tahta,p);if (tahta[tasKonumu+sagYatay] != 0.0 && tahta[tasKonumu+sagYatay] != -900.0) {return;}} else {break;}
		}
	}
	public void beyazSolYatay(int tasKonumu,int x ,int[] tahta, PerformansTest p) {		
		for (int solYatay = 1;solYatay <= x;solYatay++) {
			if (tahta[tasKonumu-solYatay] == -900 || tahta[tasKonumu-solYatay] > -1) {beyazSahTehtidAyarla(tasKonumu-solYatay,tasKonumu,tahta,p);if (tahta[tasKonumu-solYatay] != 0.0 && tahta[tasKonumu-solYatay] != -900.0) {return;}} else {break;}
		}
	}
	public void beyazUstDikey(int tasKonumu,int y, int[] tahta, PerformansTest p) {		
		for (int ustDikey = 1;ustDikey <= y;ustDikey++) {
			if (tahta[tasKonumu-ustDikey*8] == -900 || tahta[tasKonumu-ustDikey*8] > -1) {beyazSahTehtidAyarla(tasKonumu-ustDikey*8,tasKonumu,tahta,p);if (tahta[tasKonumu-ustDikey*8] != 0.0 && tahta[tasKonumu-ustDikey*8] != -900.0) {return;}} else {break;}
		}
	}
	public void beyazAltDikey(int tasKonumu,int y, int[] tahta, PerformansTest p) {		
		for (int altDikey = 1;altDikey <= 7-y;altDikey++) {
			if (tahta[tasKonumu+altDikey*8] == -900 || tahta[tasKonumu+altDikey*8] > -1) {beyazSahTehtidAyarla(tasKonumu+altDikey*8,tasKonumu,tahta,p);if (tahta[tasKonumu+altDikey*8] != 0.0 && tahta[tasKonumu+altDikey*8] != -900.0) {return;}} else {break;}
		}
	}	
	public void siyahSahTehtidCapraz(int tasKonumu, int[] tahta, PerformansTest p) {		 
		siyahSolAltKose(tasKonumu,tasKonumu%8,tasKonumu/8,tahta,p);
		siyahSolUstKose(tasKonumu,tasKonumu%8,tasKonumu/8,tahta,p);
		siyahSagUstKose(tasKonumu,tasKonumu%8,tasKonumu/8,tahta,p);
		siyahSagAltKose(tasKonumu,tasKonumu%8,tasKonumu/8,tahta,p);
	}
	public void siyahSolAltKose(int tasKonumu,int x ,int y, int[] tahta, PerformansTest p) {		
		for (int solAltKose = 1;y+solAltKose < 8 && x-solAltKose > -1;solAltKose++) {
			if (tahta[tasKonumu+solAltKose*8-solAltKose] == 900 || tahta[tasKonumu+solAltKose*8-solAltKose] < 1) {siyahSahTehtidAyarla(tasKonumu+solAltKose*8-solAltKose,tasKonumu,tahta,p);if (tahta[tasKonumu+solAltKose*8-solAltKose] != 0.0 && tahta[tasKonumu+solAltKose*8-solAltKose] != 900.0) {return;}} else {break;}
		}
	}
	public void siyahSolUstKose(int tasKonumu,int x ,int y, int[] tahta, PerformansTest p) {		
		for (int solUstKose = 1;y-solUstKose > -1 && x-solUstKose > -1;solUstKose++) {
			if (tahta[tasKonumu-solUstKose*8-solUstKose] == 900 || tahta[tasKonumu-solUstKose*8-solUstKose] < 1) {siyahSahTehtidAyarla(tasKonumu-solUstKose*8-solUstKose,tasKonumu,tahta,p);if (tahta[tasKonumu-solUstKose*8-solUstKose] != 0.0 && tahta[tasKonumu-solUstKose*8-solUstKose] != 900.0) {return;}} else {break;}
		}
	}
	public void siyahSagUstKose(int tasKonumu,int x ,int y, int[] tahta, PerformansTest p) {		
		for (int sagUstKose = 1;y-sagUstKose > -1 && x+sagUstKose < 8;sagUstKose++) {
			if (tahta[tasKonumu-sagUstKose*8+sagUstKose] == 900 || tahta[tasKonumu-sagUstKose*8+sagUstKose] < 1) {siyahSahTehtidAyarla(tasKonumu-sagUstKose*8+sagUstKose,tasKonumu,tahta,p);if (tahta[tasKonumu-sagUstKose*8+sagUstKose] != 0.0 && tahta[tasKonumu-sagUstKose*8+sagUstKose] != 900.0) {return;}} else {break;}
		}
	}
	public void siyahSagAltKose(int tasKonumu,int x ,int y, int[] tahta, PerformansTest p) {		
		for (int sagAltKose = 1;y+sagAltKose < 8 && x+sagAltKose < 8;sagAltKose++) {
			if (tahta[tasKonumu+sagAltKose*8+sagAltKose] == 900 || tahta[tasKonumu+sagAltKose*8+sagAltKose] < 1) {siyahSahTehtidAyarla(tasKonumu+sagAltKose*8+sagAltKose,tasKonumu,tahta,p);if (tahta[tasKonumu+sagAltKose*8+sagAltKose] != 0.0 && tahta[tasKonumu+sagAltKose*8+sagAltKose] != 900.0) {return;}} else {break;}
		}
	}	
	public void siyahSahTehtidYatayVeDikey(int tasKonumu, int[] tahta, PerformansTest p) {		
		siyahSagYatay(tasKonumu,tasKonumu%8,tahta,p);
		siyahSolYatay(tasKonumu,tasKonumu%8,tahta,p);
		siyahUstDikey(tasKonumu,tasKonumu/8,tahta,p);
		siyahAltDikey(tasKonumu,tasKonumu/8,tahta,p);
	}
	public void siyahSagYatay(int tasKonumu,int x ,int[] tahta, PerformansTest p) {		
		for (int sagYatay = 1;sagYatay <= 7-x;sagYatay++) {
			if (tahta[tasKonumu+sagYatay] == 900 || tahta[tasKonumu+sagYatay] < 1) {siyahSahTehtidAyarla(tasKonumu+sagYatay,tasKonumu,tahta,p);if (tahta[tasKonumu+sagYatay] != 0.0 && tahta[tasKonumu+sagYatay] != 900.0) {return;}} else {break;}
		}
	}
	public void siyahSolYatay(int tasKonumu,int x ,int[] tahta, PerformansTest p) {		
		for (int solYatay = 1;solYatay <= x;solYatay++) {
			if (tahta[tasKonumu-solYatay] == 900 || tahta[tasKonumu-solYatay] < 1) {siyahSahTehtidAyarla(tasKonumu-solYatay,tasKonumu,tahta,p);if (tahta[tasKonumu-solYatay] != 0.0 && tahta[tasKonumu-solYatay] != 900.0) {return;}} else {break;}
		}
	}
	public void siyahUstDikey(int tasKonumu,int y, int[] tahta, PerformansTest p) {		
		for (int ustDikey = 1;ustDikey <= y;ustDikey++) {
			if (tahta[tasKonumu-ustDikey*8] == 900 || tahta[tasKonumu-ustDikey*8] < 1) {siyahSahTehtidAyarla(tasKonumu-ustDikey*8,tasKonumu,tahta,p);if (tahta[tasKonumu-ustDikey*8] != 0.0 && tahta[tasKonumu-ustDikey*8] != 900.0) {return;}} else {break;}
		}
	}
	public void siyahAltDikey(int tasKonumu,int y, int[] tahta, PerformansTest p) {		
		for (int altDikey = 1;altDikey <= 7-y;altDikey++) {
			if (tahta[tasKonumu+altDikey*8] == 900 || tahta[tasKonumu+altDikey*8] < 1) {siyahSahTehtidAyarla(tasKonumu+altDikey*8,tasKonumu,tahta,p);if (tahta[tasKonumu+altDikey*8] != 0.0 && tahta[tasKonumu+altDikey*8] != 900.0) {return;}} else {break;}
		}
	}
	public List<Integer> hamleleriAl(boolean siraKimde, int[] tahta, PerformansTest p) {				
		p.setBeyazSahCekis(0);
		p.setSiyahSahCekis(0);			
		return arama(siraKimde,tahta,p);	
	}	
}