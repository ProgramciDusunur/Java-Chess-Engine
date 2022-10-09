package motor;

import java.util.ArrayList;
import java.util.LinkedList;

public class Beyaz {	
	ArrayList<Integer> hamle = new ArrayList<Integer>();
	public ArrayList<Integer> hamleleriAl(int[] tahta, ArrayList<Integer> sahTehtid, PerformansTest p) {		
		for (int i = 0;i < tahta.length;i++) {
			switch (tahta[i]) {
			case 900:
				sah(i,i%8,i/8,tahta,sahTehtid, p);
				break;
			case 9:
				if (p.getBeyazSahCekis() == 0 && acmazTest(i,tahta[i],tahta,p) == 0) {vezir(i,i%8,i/8,tahta);} else if (p.getBeyazSahCekis() == 1) {sahCekisArama(tahta[i],i,tahta,p);}
				break;
			case 3:
				if (p.getBeyazSahCekis() == 0 && acmazTest(i,tahta[i],tahta,p) == 0) {at(i,i%8,i/8,tahta);} else if (p.getBeyazSahCekis() == 1) {sahCekisArama(tahta[i],i,tahta,p);}
				break;
			case 4:
				if (p.getBeyazSahCekis() == 0 && acmazTest(i,tahta[i],tahta,p) == 0) {fil(i,i%8,i/8,tahta);} else if (p.getBeyazSahCekis() == 1) {sahCekisArama(tahta[i],i,tahta,p);}
				break;
			case 5:
				if (p.getBeyazSahCekis() == 0 && acmazTest(i,tahta[i],tahta,p) == 0) {kale(i,i%8,i/8,tahta);} else if (p.getBeyazSahCekis() == 1) {sahCekisArama(tahta[i],i,tahta,p);}
				break;
			case 1:
				if (p.getBeyazSahCekis() == 0 && acmazTest(i,tahta[i],tahta,p) == 0) {piyon(i,i%8,i/8,tahta,p);} else if (p.getBeyazSahCekis() == 1) {sahCekisArama(tahta[i],i,tahta,p);}
				break;
			}
		}
		return hamle;
	}
	public void sah(int i, int x, int y, int[] tahta, ArrayList<Integer> sahTehtid, PerformansTest p) {
		if (y-1 > -1 && tahta[(y-1)*8+x] < 1 && sahTehtid.get((y-1)*8+x)%10 < 1) {hamleEkle(tahta[i],i,x,y-1,tahta);}
		if (y+1 < 8  && tahta[(y+1)*8+x] < 1 && sahTehtid.get((y+1)*8+x)%10 < 1) {hamleEkle(tahta[i],i,x,y+1,tahta);}
		if (x+1 < 8  && tahta[(y)*8+x+1] < 1 && sahTehtid.get((y)*8+x+1)%10 < 1) {hamleEkle(tahta[i],i,x+1,y,tahta);}
		if (x-1 > -1 && tahta[(y)*8+x-1] < 1 && sahTehtid.get((y)*8+x-1)%10 < 1) {hamleEkle(tahta[i],i,x-1,y,tahta);}
		if (x-1 > -1 && y+1 < 8 && tahta[(y+1)*8+x-1] < 1  && sahTehtid.get((y+1)*8+x-1)%10 < 1) {hamleEkle(tahta[i],i,x-1,y+1,tahta);}
		if (x+1 < 8  && y+1 < 8 && tahta[(y+1)*8+x+1] < 1  && sahTehtid.get((y+1)*8+x+1)%10 < 1) {hamleEkle(tahta[i],i,x+1,y+1,tahta);}
		if (x+1 < 8  && y-1 > -1 && tahta[(y-1)*8+x+1] < 1 && sahTehtid.get((y-1)*8+x+1)%10 < 1) {hamleEkle(tahta[i],i,x+1,y-1,tahta);}
		if (x-1 > -1 && y-1 > -1 && tahta[(y-1)*8+x-1] < 1 && sahTehtid.get((y-1)*8+x-1)%10 < 1) {hamleEkle(tahta[i],i,x-1,y-1,tahta);}		
		if (p.getBeyazSahCekis() == 0 && tahta[60] == 900) {
			if (p.isBeyazSahKisaRok() && tahta[63] == 5.0 && tahta[62] == 0.0 && tahta[61] == 0.0 && sahTehtid.get(62)%10 < 1 && sahTehtid.get(61)%10 < 1) {hamle.add(2000000);} 
			if (p.isBeyazSahUzunRok() && tahta[56] == 5.0 && tahta[57] == 0.0 && tahta[58] == 0.0 && tahta[59] == 0.0 && sahTehtid.get(59)%10 < 1 && sahTehtid.get(58)%10 < 1) {hamle.add(6000000);}
		}		
	}
	public void vezir(int i, int x, int y, int[] tahta) {
		capraz(i,x,y,tahta);
		dikeyYatay(i,x,y,tahta);
	}
	public void at(int i, int x, int y, int[] tahta) {
		if (y-2 > -1 && x-1 > -1 && tahta[(y-2)*8+x-1] < 1) {hamleEkle(tahta[i],i,x-1,y-2,tahta);}				
		if (y-1 > -1 && x-2 > -1 && tahta[(y-1)*8+x-2] < 1) {hamleEkle(tahta[i],i,x-2,y-1,tahta);}
		if (y-1 > -1 && x+2 < 8  && tahta[(y-1)*8+x+2] < 1) {hamleEkle(tahta[i],i,x+2,y-1,tahta);}
		if (y-2 > -1 && x+1 < 8  && tahta[(y-2)*8+x+1] < 1) {hamleEkle(tahta[i],i,x+1,y-2,tahta);}
		if (y+2 < 8  && x+1 < 8  && tahta[(y+2)*8+x+1] < 1) {hamleEkle(tahta[i],i,x+1,y+2,tahta);}
		if (y+2 < 8  && x-1 > -1 && tahta[(y+2)*8+x-1] < 1) {hamleEkle(tahta[i],i,x-1,y+2,tahta);}
		if (y+1 < 8  && x-2 > -1 && tahta[(y+1)*8+x-2] < 1) {hamleEkle(tahta[i],i,x-2,y+1,tahta);}
		if (y+1 < 8  && x+2 < 8  && tahta[(y+1)*8+x+2] < 1) {hamleEkle(tahta[i],i,x+2,y+1,tahta);}
	}
	public void fil(int i, int x, int y, int[] tahta) {
		capraz(i,x,y,tahta);
	}
	public void kale(int i, int x, int y, int[] tahta) {
		dikeyYatay(i,x,y,tahta);
	}
	public void piyon(int i, int x, int y, int[] tahta, PerformansTest p) {
		if (y-1 > 0 && tahta[(y-1)*8+x] == 0.0) {hamle.add(1*100000+(y-1)*10000+(x)*1000+(y)*100+(x)*10);}
		if (y == 6 && tahta[(y-2)*8+x] == 0.0 && tahta[(y-1)*8+x] == 0.0) {hamle.add(1*1000000+(y-2)*10000+(x)*1000+(y)*100+(x)*10);}
		if (x-1 > -1 && y-1 > 0 && tahta[(y-1)*8+x-1] < 0) {hamle.add(1*100000+(y-1)*10000+(x-1)*1000+(y)*100+(x)*10);}
		if (x+1 < 8  && y-1 > 0 && tahta[(y-1)*8+x+1] < 0) {hamle.add(1*100000+(y-1)*10000+(x+1)*1000+(y)*100+(x)*10);}		
		if (y == 1 && tahta[(y-1)*8+x] == 0.0) {hamle.add(9*100000000+(y-1)*10000+(x)*1000+(y)*100+(x)*10);hamle.add(4*10000000+(y-1)*10000+(x)*1000+(y)*100+(x)*10);hamle.add(3*10000000+(y-1)*10000+(x)*1000+(y)*100+(x)*10);hamle.add(5*10000000+(y-1)*10000+(x)*1000+(y)*100+(x)*10);}		
		if (x-1 > -1 && y == 1 && tahta[(y-1)*8+x-1] < 0) {hamle.add(9*100000000+(y-1)*10000+(x-1)*1000+(y)*100+(x)*10);hamle.add(4*10000000+(y-1)*10000+(x-1)*1000+(x)*100+(x)*10);hamle.add(3*10000000+(y-1)*10000+(x-1)*1000+(i/8)*100+(x)*10);hamle.add(5*10000000+(y-1)*10000+(x-1)*1000+(y)*100+(x)*10);}
		if (x+1 < 8  && y == 1 && tahta[(y-1)*8+x+1] < 0) {hamle.add(9*100000000+(y-1)*10000+(x+1)*1000+(y)*100+(x)*10);hamle.add(4*10000000+(y-1)*10000+(x+1)*1000+(y)*100+(x)*10);hamle.add(3*10000000+(y-1)*10000+(x+1)*1000+(i/8)*100+(x)*10);hamle.add(5*10000000+(y-1)*10000+(x+1)*1000+(y)*100+(x)*10);}
		if (p.getGecerkenAlma() > -1 && Math.abs(p.getGecerkenAlma()-i%8) == 1 && i/8 == 3 && gecerkenAlmaAcmaz(i,tahta,p)) {hamle.add(1*10000000+(i/8-1)*10000+(p.getGecerkenAlma())*1000+(i/8)*100+(i%8)*10);}
	}
	public void sahCekisArama(int tas, int i, int[] tahta, PerformansTest k) {		 
		 LinkedList<Integer> mudaheleKareleri = k.sahMudaheleKareAl();
		 for (int p = 0;p < mudaheleKareleri.size();p++) {
			 switch(tas) {
			 case 3:
				 if (i/8-2 > -1 && i%8-1 > -1 && (i/8-2)*8+i%8-1 == mudaheleKareleri.get(p)) {hamleEkle(tas,i,i%8-1,i/8-2,tahta);}	
	    		 if (i/8-1 > -1 && i%8-2 > -1 && (i/8-1)*8+i%8-2 == mudaheleKareleri.get(p)) {hamleEkle(tas,i,i%8-2,i/8-1,tahta);}
	    		 if (i/8-1 > -1 && i%8+2 < 8 && (i/8-1)*8+i%8+2 == mudaheleKareleri.get(p)) {hamleEkle(tas,i,i%8+2,i/8-1,tahta);}
	    		 if (i/8-2 > -1 && i%8+1 < 8 && (i/8-2)*8+i%8+1 == mudaheleKareleri.get(p)) {hamleEkle(tas,i,i%8+1,i/8-2,tahta);}
	    		 if (i/8+2 < 8 && i%8+1 < 8 && (i/8+2)*8+i%8+1 == mudaheleKareleri.get(p)) {hamleEkle(tas,i,i%8+1,i/8+2,tahta);}
	    		 if (i/8+2 < 8 && i%8-1 > -1 && (i/8+2)*8+i%8-1 == mudaheleKareleri.get(p)) {hamleEkle(tas,i,i%8-1,i/8+2,tahta);}
	    		 if (i/8+1 < 8 && i%8-2 > -1 && (i/8+1)*8+i%8-2 == mudaheleKareleri.get(p)) {hamleEkle(tas,i,i%8-2,i/8+1,tahta);}
	    		 if (i/8+1 < 8 && i%8+2 < 8 && (i/8+1)*8+i%8+2 == mudaheleKareleri.get(p)) {hamleEkle(tas,i,i%8+2,i/8+1,tahta);}
				 break;
			 case 1:
				 if (i/8 == 6 && tahta[(i/8-2)*8+i%8] == 0 && tahta[(i/8-1)*8+i%8] == 0 && (i/8-2)*8+i%8 == mudaheleKareleri.get(p)) {hamleEkle(10,i,i%8,i/8-2,tahta);}
	    			if ((i/8-1 > 0) && tahta[(i/8-1)*8+i%8] == 0 && ((i/8)-1)*8+i%8 == mudaheleKareleri.get(p)) {hamleEkle(tas,i,i%8,i/8-1,tahta);}
	    			if ((i/8-1 > 0 && i%8-1 > -1) && tahta[(i/8-1)*8+i%8-1] < 0 && (i/8-1)*8+i%8-1== mudaheleKareleri.get(p)) {hamleEkle(tas,i,i%8-1,i/8-1,tahta);}
	    			if ((i/8-1 > 0 && i%8+1 < 8) && tahta[(i/8-1)*8+i%8+1] < 0 && (i/8-1)*8+i%8+1== mudaheleKareleri.get(p)) {hamleEkle(tas,i,i%8+1,i/8-1,tahta);}
	    			if (i/8 == 3 && k.getGecerkenAlma() > -1 && Math.abs(k.getGecerkenAlma()-i%8) == 1 && tahta[mudaheleKareleri.get(p)] == -1) {hamle.add(1*10000000+(i/8-1)*10000+(k.getGecerkenAlma())*1000+(i/8)*100+(i%8)*10);}
	    			if (i/8 == 1 && (tahta[mudaheleKareleri.get(p)] == 0) && (i/8-1)*8+i%8 == mudaheleKareleri.get(p)) {hamle.add(9*100000000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);hamle.add(4*10000000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);hamle.add(3*10000000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);hamle.add(5*10000000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
	    			if (i/8 == 1 && (tahta[mudaheleKareleri.get(p)] < 0) && (i/8-1)*8+i%8-1 == mudaheleKareleri.get(p)) {hamle.add(9*100000000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);hamle.add(4*10000000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);hamle.add(3*10000000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);hamle.add(5*10000000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}
	    			if (i/8 == 1 && (tahta[mudaheleKareleri.get(p)] < 0) && (i/8-1)*8+i%8+1 == mudaheleKareleri.get(p)) {hamle.add(9*100000000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);hamle.add(4*10000000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);hamle.add(3*10000000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);hamle.add(5*10000000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}
				 break;
			 case 9:
				 sahCekisUzunTasHesap(true,i,tahta,mudaheleKareleri.get(p));
	    		 sahCekisUzunTasHesap(false,i,tahta,mudaheleKareleri.get(p));
				 break;
			 case 5:
				 sahCekisUzunTasHesap(false,i,tahta,mudaheleKareleri.get(p));
				 break;
			 case 4:
				 sahCekisUzunTasHesap(true,i,tahta,mudaheleKareleri.get(p));
				 break;
			 }	    	
	     }	     	    		    
	 }
	public void hamleEkle(double j, int i, int x, int y, int[] tahta) {
		hamle.add((int) (j*100000+(y)*10000+(x)*1000+(i/8)*100+(i%8)*10));
	}	
	public void capraz(int i, int x, int y, int[] tahta) {
		for (int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1;solAltKose <= 7 || solUstKose <= 7 || sagUstKose <= 7 || sagAltKose <= 7;solAltKose++,solUstKose++,sagUstKose++,sagAltKose++) {    			
			if (y+solAltKose < 8  && x-solAltKose > -1 && tahta[(y+solAltKose)*8+x-solAltKose] < 1) {hamleEkle(tahta[i],i,x-solAltKose,y+solAltKose,tahta);if (tahta[(y+solAltKose)*8+x-solAltKose] != 0.0) {solAltKose = 8;}} else {solAltKose = 8;}
			if (y-solUstKose > -1 && x-solUstKose > -1 && tahta[(y-solUstKose)*8+x-solUstKose] < 1) {hamleEkle(tahta[i],i,x-solUstKose,y-solUstKose,tahta);if (tahta[(y-solUstKose)*8+x-solUstKose] != 0.0) {solUstKose = 8;}} else {solUstKose = 8;}
			if (y-sagUstKose > -1 && x+sagUstKose < 8  && tahta[(y-sagUstKose)*8+x+sagUstKose] < 1) {hamleEkle(tahta[i],i,x+sagUstKose,y-sagUstKose,tahta);if (tahta[(y-sagUstKose)*8+x+sagUstKose] != 0.0) {sagUstKose = 8;}} else {sagUstKose = 8;}
			if (y+sagAltKose < 8  && x+sagAltKose < 8  && tahta[(y+sagAltKose)*8+x+sagAltKose] < 1) {hamleEkle(tahta[i],i,x+sagAltKose,y+sagAltKose,tahta);if (tahta[(y+sagAltKose)*8+x+sagAltKose] != 0.0) {sagAltKose = 8;}} else {sagAltKose = 8;}    			
		}    		
	}
	public void dikeyYatay(int i, int x, int y, int[] tahta) {
		for (int ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1;ustDikey <= i/8 || altDikey <= 7-i/8 || solYatay <= i%8 || sagYatay <= 7-i%8;solYatay++,sagYatay++,ustDikey++,altDikey++) {    			
			if (ustDikey <= y   && tahta[((y-ustDikey)*8)+x] < 1) {hamleEkle(tahta[i],i,x,y-ustDikey,tahta);if (tahta[((y-ustDikey)*8)+x] != 0.0) {ustDikey = y+1;}} else {ustDikey= y+1;}
			if (altDikey <= 7-y && tahta[((y+altDikey)*8)+x] < 1) {hamleEkle(tahta[i],i,x,y+altDikey,tahta);if (tahta[((y+altDikey)*8)+x] != 0.0) {altDikey = 8-y;}} else {altDikey= 8-y;}
			if (sagYatay <= 7-x && tahta[((y)*8)+x+sagYatay] < 1) {hamleEkle(tahta[i],i,x+sagYatay,y,tahta);if (tahta[((y)*8)+x+sagYatay] != 0.0) {sagYatay = 8-x;}} else {sagYatay= 8-x;}
			if (solYatay <= x   && tahta[((y)*8)+x-solYatay] < 1) {hamleEkle(tahta[i],i,x-solYatay,y,tahta);if (tahta[((y)*8)+x-solYatay] != 0.0) {solYatay = x+1;}} else {solYatay= x+1;}    			
		}
	}
	public int acmazTest(int i, int j, int[] tahta, PerformansTest p) {
		int sahX = p.getBeyazSahKonum()%10, sahY = p.getBeyazSahKonum()/10%10;
		if (sahX == i%8 && sahY > i/8) {    		
    		if (j == 1) {return piyonAcmaz(j,1,i,(sahY - i/8)-1,tahta,p);} else {return acmazKontrol(j,1,i,(sahY - i/8)-1,tahta,p);}
    	}
    	else if (sahX == i%8 && sahY < i/8) {
    		if (j == 1) {return piyonAcmaz(j,2,i,i/8-sahY-1,tahta,p);} else {return acmazKontrol(j,2,i,i/8-sahY-1,tahta,p);}
    	}
    	else if (sahY == i/8 && sahX > i%8) {
    		if (j == 1) {return piyonAcmaz(j,3,i,(sahX - i%8)-1,tahta,p);} else {return acmazKontrol(j,3,i,(sahX - i%8)-1,tahta,p);}
    	}
    	else if (sahY == i/8 && sahX < i%8) {    		
    		if (j == 1) {return piyonAcmaz(j,4,i,(i%8-sahX)-1,tahta,p);} else {return acmazKontrol(j,4,i,(i%8-sahX)-1,tahta,p);}	
    	}
    	else if (sahY*8+sahX %9 == i%9 && sahY > i/8) {    		    	
    		if (j == 1) {return piyonAcmaz(j,5,i,(sahY*8+sahX-i)/9-1,tahta,p);} else {return acmazKontrol(j,5,i,(sahY*8+sahX-i)/9-1,tahta,p);}
    	}
    	else if (sahY*8+sahX %9 == i%9 && sahY < i/8) {    		
    		if (j == 1) {return piyonAcmaz(j,12,i,(i-(sahY*8+sahX))/9-1,tahta,p);} else {return acmazKontrol(j,6,i,(i-(sahY*8+sahX))/9-1,tahta,p);}
    	}
    	else if (sahY*8+sahX % 7 == i%7 && sahY > i/8 && sahX < i%8) {    		
    		if (j == 1) {return piyonAcmaz(j,7,i,(sahY*8+sahX-i)/7-1,tahta,p);} else {return acmazKontrol(j,7,i,(sahY*8+sahX-i)/7-1,tahta,p);}
    	}
    	else if (sahY*8+sahX % 7 == i%7 && sahY < i/8 && sahX > i%8) {    		
    		return acmazKontrol(j,8,i,(i-(sahY*8+sahX))/7-1,tahta,p);
    	}
		return 0;
	}
	public void acmazHesap(int tasDeger, int hangiYon, int i, int y, int x, int sahTaraf, int[] tahta, PerformansTest k) {		
		if (k.getBeyazSahCekis() == 0) {			
			switch(hangiYon) {			
			case 1:			
				for (int p = i/8-1,j = i%8;p >= y;p--) {				
					hamleEkle(tasDeger,i,j,p,tahta);				
				}
				for (int p = 1;p <= sahTaraf;p++) {
					hamleEkle(tasDeger,i,i%8,i/8+p,tahta);				
				}
				break;
			case 2:
				for (int p = i/8+1,j = i%8;p <= y;p++) {				
					hamleEkle(tasDeger,i,j,p,tahta);				
				}
				for (int p = 1;p <= sahTaraf;p++) {
					hamleEkle(tasDeger,i,i%8,i/8-p,tahta);				
				}
				break;
			case 3:
				for (int p = i/8,j = i%8-1;j >= x;j--) {
					hamleEkle(tasDeger,i,j,p,tahta);				
				}
				for (int p = 1;p <= sahTaraf;p++) {
					hamleEkle(tasDeger,i,i%8+p,i/8,tahta);				
				}
				break;
			case 4:
				for (int p = i/8,j = i%8+1;j <= x;j++) {
					hamleEkle(tasDeger,i,j,p,tahta);				
				}			
				for (int p = 1;p <= sahTaraf;p++) {
					hamleEkle(tasDeger,i,i%8-p,i/8,tahta);				
				}
				break;
			case 5:
				for (int p = i/8-1,j = i%8-1;j >= x && p >= y;p--,j--) {			
					hamleEkle(tasDeger,i,j,p,tahta);				
				}			
				for (int p = 1;p <= sahTaraf;p++) {
					hamleEkle(tasDeger,i,i%8+p,i/8+p,tahta);				
				}
				break;
			case 6:
				for (int p = i/8+1,j = i%8+1;j <= x && p <= y;p++,j++) {			
					hamleEkle(tasDeger,i,j,p,tahta);				
				}
				for (int p = 1;p <= sahTaraf;p++) {
					hamleEkle(tasDeger,i,i%8-p,i/8-p,tahta);				
				}
				break;
			case 7:			
				for (int p = i/8-1,j = i%8+1;j <= x && p >= y;p--,j++) {			
					hamleEkle(tasDeger,i,j,p,tahta);				
				}
				for (int p = 1;p <= sahTaraf;p++) {
					hamleEkle(tasDeger,i,i%8-p,i/8+p,tahta);				
				}
				break;
			case 8:
				for (int p = i/8+1,j = i%8-1;j >= x && p <= y;p++,j--) {			
					hamleEkle(tasDeger,i,j,p,tahta);				
				}
				for (int p = 1;p <= sahTaraf;p++) {
					hamleEkle(tasDeger,i,i%8+p,i/8-p,tahta);				
				}
				break;
			}
		}
								
	}
	public int acmazKontrol(int tasDeger,int hangiYon,int i,int sahTaraf,int[] tahta, PerformansTest k) {		
    	switch(hangiYon) {    	
    		case 1:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8] != 0) {
    					return 0;
    				}    				
    			}
    			for (int y = i/8-1,x = i%8;y > -1;y--) {    				    				
    				if (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) {    					
    					if (tasDeger > 4) {    						
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;
    				}  				
    			}    			
    			break;
    		case 2:      			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8] != 0) {
    					return 0;
    				}    				
    			}
    			for (int y = i/8+1,x = i%8;y < 8;y++) {    				    				
    				if (tahta[y*8+x] != -5 && tahta[y*8+x] != -9 && tahta[y*8+x] != 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[y*8+x] == -5 || tahta[y*8+x] == -9) {
    					if (tasDeger > 4) {
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;
    				}
    			}
    			break;
    		case 3:    			
    			for (int p = 1;p <= sahTaraf;p++) {    				
    				if (tahta[(i/8)*8+i%8+p] != 0) {    					
    					return 0;
    				}   				    				
    			}
    			for (int y = i/8,x = i%8-1;x > -1;x--) {    				    				
    				if (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) {
    					if (tasDeger > 4) {
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;
    				} 
    			}
    			break;
    		case 4:    			
    			for (int p = 1;p <= sahTaraf;p++) {    				
    				if (tahta[(i/8)*8+i%8-p] != 0) {    					
    					return 0;
    				}    				
    			}
    			for (int y = i/8,x = i%8+1;x < 8;x++) {    				
    				if (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
    					return 0;
    				}    		    				    				
    				if (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) {
    					if (tasDeger > 4) {    						
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);    						
    					}    					
    					return 1;
    				}    						    			
    			}
    			break;    			
    		case 5:    		
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8+p] != 0) {
    					return 0;
    				}    				
    			}
    			for (int y = i/8-1,x = i%8-1;x > -1 && y > -1;y--,x--) {    				    				
    				if (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) {    				
    					if (tasDeger == 4 || tasDeger == 9) {    						
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;
    				}
    			}    			
    			break;    			    		
    		case 6:    			    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8-p] != 0) {
    					return 0;
    				}
    				
    			}
    			for (int y = i/8+1,x = i%8+1;x < 8 && y < 8;y++,x++) {    				    				
    				if (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) {    					
    					if (tasDeger == 4 || tasDeger == 9) {
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;
    				}
    			}
    			break;
    		case 7:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8-p] != 0) {
    					return 0;
    				}    				
    			}
    			for (int y = i/8-1,x = i%8+1;x < 8 && y > -1;y--,x++) {    				    				
    				if (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) {
    					if (tasDeger == 4 || tasDeger == 9) {
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;
    				} 
    			}
    			break;
    		case 8:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8+p] != 0) {
    					return 0;
    				}    				
    			}
    			for (int y = i/8+1,x = i%8-1;x > -1 && y < 8;y++,x--) {    				    				
    				if (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) {
    					if (tasDeger == 4 || tasDeger == 9) {
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;    					
    				} 
    			}    			
    			break;
    	}
    	return 0;
    }
	 public int piyonAcmaz(int tasDeger, int hangiYon, int i, int sahTaraf, int[] tahta, PerformansTest k) {			 
	    	switch (hangiYon) {
	    	case 1:
	    		for (int p = 1;p <= sahTaraf;p++) {
	    			if (tahta[(i/8+p)*8+i%8] != 0) {
	    				return 0;
	    			}    				
	    		}
 			for (int y = i/8-1,x = i%8;y > -1;y--) {    				    				
 				if (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
 					return 0;
 				}    				    				
 				if (tahta[(y*8+x)] < -4) {
 					if (k.getBeyazSahCekis() == 0) {
 						if (i/8-1 > 0) {if (tahta[(i/8-1)*8+i%8] == 0.0) {hamle.add(1*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}}
 						if (i/8 == 6) {if (tahta[(i/8-2)*8+i%8] == 0.0 && tahta[(i/8-1)*8+i%8] == 0.0)  {hamle.add(1*1000000+(i/8-2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}}
 					}    					
 					return 1;
 				}  				
 			}
	    		break;
	    	case 2:	    	
	    		for (int p = 1;p <= sahTaraf;p++) {
	    			if (tahta[(i/8-p)*8+i%8] != 0) {    					
 						return 0;
 					}    				
	    		}
	    		for (int y = i/8+1,x = i%8;y < 8;y++) {    				
	    			if ((tahta[y*8+x] != -5 && tahta[y*8+x] != -9 && tahta[y*8+x] != 0)) {    					
	    				return 0;
	    			}    				    				
	    			if (tahta[(y*8+x)] < -4) {
	    				if (k.getBeyazSahCekis() == 0) {
	    					if (i/8-1 > 0) {if (tahta[(i/8-1)*8+i%8] == 0.0) {hamle.add(1*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}}
	    					if (i/8 == 6) {if (tahta[(i/8-2)*8+i%8] == 0.0 && tahta[(i/8-1)*8+i%8] == 0.0)  {hamle.add(1*1000000+(i/8-2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}}
	    				}    					
	    				return 1;
	    			}
	    		}
	    		break;
	    	case 3:	    		
	    		for (int p = 1;p <= sahTaraf;p++) {    			
	    			if (tahta[(i/8)*8+i%8+p] != 0) {	    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8,x = i%8-1;x > -1;x--) {    				    				
	    			if (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
 						return 0;
 					}    				    				
 					if (tahta[(y*8+x)] < -4) {    					
 						return 1;
 					} 
	    		}
	    		break;
	    	case 4:	  	    			    	
	    		for (int p = 1;p <= sahTaraf;p++) {    			
	    			if (tahta[(i/8)*8+i%8-p] != 0) {    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8,x = i%8+1;x < 8;x++) {    				
	    			if (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
	    				return 0;
	    			}    		    				    				
	    			if (tahta[(y*8+x)] < -4) {    					    					
	    				return 1;
	    			} 						    			
	    		}
	    		break;	    		
	    	case 5:	 	    		
	    		for (int p = 1;p <= sahTaraf;p++) {    			
	    			if (tahta[(i/8+p)*8+i%8+p] != 0) {    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8-1,x = i%8-1;x > -1 && y > -1;y--,x--) {
	    			if (k.getBeyazSahCekis() == 0 && (y == i/8-1 && x == i%8-1 && i/8 > 1) && (tahta[y*8+x] == -9 || tahta[y*8+x] == -4)) {	    				
	    				hamleEkle(tasDeger,i,x,y,tahta);
	    				return 1;
	    			} else if (!(tahta[y*8+x] == -9 || tahta[y*8+x] == -4) && tahta[y*8+x] != 0.0) {	    				
	    				return 0;
	    			} else if ((tahta[y*8+x] == -9 || tahta[y*8+x] == -4) && i/8 > 1) {
	    				return 1;
	    			}    			
	    		}
	    		break;	    	 	
	    	case 7:	 	    			    		
	    		for (int p = 1;p <= sahTaraf;p++) {    			
	    			if (tahta[(i/8+p)*8+i%8-p] != 0) {	    					    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8-1,x = i%8+1;x < 8 && y > -1;y--,x++) {	    			
	    			if (k.getBeyazSahCekis() == 0 && ((y == i/8-1 && x == i%8+1)) && (tahta[y*8+x] == -9 || tahta[y*8+x] == -4)) {
	    				hamleEkle(tasDeger,i,x,y,tahta);
	    				return 1;
	    			} else if (!(tahta[y*8+x] == -9 || tahta[y*8+x] == -4) && tahta[y*8+x] != 0.0) {
	    				return 0;
	    			} else if ((tahta[y*8+x] == -9 || tahta[y*8+x] == -4) && i/8 > 1) {
	    				return 1;
	    			}    		
	    		}    		
	    		break;
	    	case 12:	    		
	    		for (int p = 1;p <= sahTaraf;p++) {	    			
	    			if (tahta[(i/8-p)*8+i%8-p] != 0) {	    					    				
	    				return 0;
	    			}	    			
	    		}
	    		for (int y = i/8+1,x = i%8+1;y < 8 && x < 8;y++,x++) {	    			
	    			if (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) {    					
    					return 1;
    				}
	    		}
	    		break;
	    	 			    		    
	    	}	    	
	    	return 0;
	    }
	 public boolean gecerkenAlmaAcmaz(int i, int[] tahta, PerformansTest p) {		
			int tas = tahta[(i/8)*8+p.getGecerkenAlma()];
			tahta[(i/8)*8+p.getGecerkenAlma()] = 0;
			if (acmazTest(i,tahta[i],tahta,p) == 0) {
				tahta[(i/8)*8+p.getGecerkenAlma()] = tas;
				return true;					
			} else {			
				tahta[(i/8)*8+p.getGecerkenAlma()] = tas;
				return false;
			}		
	 }
	 public void sahCekisUzunTasHesap(boolean yon, int i, int[] tahta, int hedefKare) {
		 int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1,ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1,m = 0,j = 0,k = 0, l = 0;	    	
	    	if (yon) {	    		
	    		if (hedefKare % 9 == i%9) {	    			
	        		if (i < hedefKare) {m = 7 - Math.max(i/8, i%8);} else {j = Math.min(i/8, i%8);}    	
	        	}
	    		else if (hedefKare % 7 == i%7) {	    			
	        		if (i < hedefKare) {k = 7 - Math.max(i/8, 7 - i%8);} else {l = Math.min(i/8, 7 - i%8);}    		
	        	}
	    		for (;sagAltKose <= m | solUstKose <= j || sagUstKose <= l || solAltKose <= k;sagAltKose++,solUstKose++,sagUstKose++,solAltKose++) {	    			
	    			if (sagAltKose <= m && i+sagAltKose*9 < 64 && i+sagAltKose*9 != hedefKare && tahta[i+sagAltKose*9] != 0) {sagAltKose = 8;}else if (i+sagAltKose*9 == hedefKare) {hamleEkle(tahta[i],i,(i+sagAltKose*9)%8,(i+sagAltKose*9)/8,tahta);}    			
	    			if (solUstKose <= j && i-solUstKose*9 > -1 && i-solUstKose*9 != hedefKare && tahta[i-solUstKose*9] != 0) {solUstKose = 8;}else if (i-solUstKose*9 == hedefKare) {hamleEkle(tahta[i],i,(i-solUstKose*9)%8,(i-solUstKose*9)/8,tahta);}
	    			if (sagUstKose <= l && i-sagUstKose*7 > -1 && i-sagUstKose*7 != hedefKare && tahta[i-sagUstKose*7] != 0) {sagUstKose = 8;}else if (i-sagUstKose*7 == hedefKare) {hamleEkle(tahta[i],i,(i-sagUstKose*7)%8,(i-sagUstKose*7)/8,tahta);}
	    			if (solAltKose <= k && i+solAltKose*7 < 64 && i+solAltKose*7 != hedefKare && tahta[i+solAltKose*7] != 0) {solAltKose = 8;}else if (i+solAltKose*7 == hedefKare) {hamleEkle(tahta[i],i,(i+solAltKose*7)%8,(i+solAltKose*7)/8,tahta);}	    				    				    			
	    		}
	    	} else {	    		
	    		if (i%8 == hedefKare%8) {    			
	    			if (i < hedefKare) {m = hedefKare/8-i/8;}else {j = i/8-hedefKare/8;}
	    		}
	    		else if (i/8 == hedefKare/8) {
	    			if (i < hedefKare) {k = hedefKare%8-i%8;}else {l = i%8-hedefKare%8;}	    			
	    		}
	    		for (;ustDikey <= j || altDikey <= m || sagYatay <= k || solYatay <= l;ustDikey++,altDikey++,sagYatay++,solYatay++) {
	    			if (i-ustDikey*8 > -1 && i-ustDikey*8 != hedefKare && tahta[i-ustDikey*8] != 0) {ustDikey = 8;}else if (i-ustDikey*8 == hedefKare) {hamleEkle(tahta[i],i,i%8,(i-ustDikey*8)/8,tahta);}    				
	    			if (i+altDikey*8 < 64 && i+altDikey*8 != hedefKare && tahta[i+altDikey*8] != 0) {altDikey = 8;}else if (i+altDikey*8 == hedefKare) {hamleEkle(tahta[i],i,i%8,(i+altDikey*8)/8,tahta);}	    				    		
	    			if (i/8*8+i%8+sagYatay < 64 && i/8*8+i%8+sagYatay != hedefKare &&tahta[i/8*8+i%8+sagYatay] != 0) {sagYatay = 8;}else if (i/8*8+i%8+sagYatay == hedefKare) {hamleEkle(tahta[i],i,i%8+sagYatay,i/8,tahta);}	    				    				    		
	    			if (i/8*8+i%8-solYatay > -1 && i/8*8+i%8-solYatay != hedefKare && tahta[i/8*8+i%8-solYatay] != 0) {solYatay = 8;}else if (i/8*8+i%8-solYatay == hedefKare) {hamleEkle(tahta[i],i,i%8-solYatay,i/8,tahta);}
	    		}
	    	}	    		    
	 }
}