package motor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import oyun.Oyun;

public class Hamleler extends Oyun {
	public ArrayList<Integer> motorHamle = new ArrayList<Integer>();
	public ArrayList<Integer> sahTehtid = new ArrayList<Integer>(
			  Arrays.asList(0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0));	
	int captures = 0, toplamSahSayisi = 0;	
	public void arama(boolean beyazMiSiyahMi, double[] tahta, PerformansTest p) {			
		motorHamle.clear();
		sahTehtid((byte) -1, tahta);	
		for (byte i = 0;i < tahta.length;i++) {									
			//King movements, Sah hareketleri
			if (beyazMiSiyahMi && tahta[i] == 900 || !beyazMiSiyahMi && tahta[i] == -900) {					
				if (i/8-1 > -1 && tahta[((i/8)-1)*8+i%8] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8)%10 < 1 || i/8-1 > -1 && tahta[((i/8)-1)*8+i%8] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8)/10 < 1) {arayuzCizilecekHamleAyarla(7,i,i%8,i/8-1,tahta);}
				if (i/8+1 < 8 && tahta[((i/8)+1)*8+i%8] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8)%10 < 1 || i/8+1 < 8 && tahta[((i/8)+1)*8+i%8] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8)/10 < 1) {arayuzCizilecekHamleAyarla(7,i,i%8,i/8+1,tahta);}
				if (i%8+1 < 8 && tahta[((i/8))*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8))*8+i%8+1)%10 < 1 || i%8+1 < 8 && tahta[((i/8))*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8))*8+i%8+1)/10 < 1) {arayuzCizilecekHamleAyarla(7,i,i%8+1,i/8,tahta);}
				if (i%8-1 > -1 && tahta[((i/8))*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8))*8+i%8-1)%10 < 1 || i%8-1 > -1 && tahta[((i/8))*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8))*8+i%8-1)/10 < 1) {arayuzCizilecekHamleAyarla(7,i,i%8-1,i/8,tahta);}
				if (i%8-1 > -1 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8-1)%10 < 1 || i%8-1 > -1 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8-1)/10 < 1) {arayuzCizilecekHamleAyarla(7,i,i%8-1,i/8+1,tahta);}
				if (i%8+1 < 8 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8+1)%10 < 1 || i%8+1 < 8 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8+1)/10 < 1) {arayuzCizilecekHamleAyarla(7,i,i%8+1,i/8+1,tahta);}
				if (i%8+1 < 8 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8+1)%10 < 1 || i%8+1 < 8 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8+1)/10 < 1) {arayuzCizilecekHamleAyarla(7,i,i%8+1,i/8-1,tahta);}
				if (i%8-1 > -1 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8-1)%10 < 1 || i%8-1 > -1 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8-1)/10 < 1) {arayuzCizilecekHamleAyarla(7,i,i%8-1,i/8-1,tahta);}				
				if (tahta[60] != 900) {if (p.isBeyazSahKisaRok()) {p.setBeyazSahKisaRok(false);}if (p.isBeyazSahUzunRok()) {p.setBeyazSahUzunRok(false);}}
				if (tahta[4] != -900) {if (p.isSiyahSahKisaRok()) {p.setSiyahSahKisaRok(false);}if (p.isSiyahSahUzunRok()) {p.setSiyahSahUzunRok(false);}}
				if (tahta[i] == -900 && p.isSiyahSahKisaRok() && tahta[7] == -5.0 && tahta[6] == 0.0 && tahta[5] == 0.0 || tahta[i] == 900 && p.isBeyazSahKisaRok() && tahta[63] == 5.0 && tahta[62] == 0.0 && tahta[61] == 0.0) {motorHamle.add(200000);}
				if (tahta[i] == -900 && p.isSiyahSahUzunRok() && tahta[0] == -5.0 && tahta[1] == 0.0 && tahta[2] == 0.0 && tahta[3] == 0.0 || tahta[i] == 900 && p.isBeyazSahUzunRok() && tahta[56] == 5.0 && tahta[57] == 0.0 && tahta[58] == 0.0 && tahta[59] == 0.0) {motorHamle.add(600000);}													
			}
			//Knight movements, At hareketleri
			if (beyazMiSiyahMi && tahta[i] == 3 || !beyazMiSiyahMi && tahta[i] == -3) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {
						if (tahta[i] == 3 && i/8-2 > -1 && i%8-1 > -1 && tahta[((i/8)-2)*8+i%8-1] < 1 || tahta[i] == -3 && i/8-2 > -1 && i%8-1 > -1 && tahta[((i/8)-2)*8+i%8-1] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8-2,tahta);}				
						if (tahta[i] == 3 && i/8-1 > -1 && i%8-2 > -1 && tahta[((i/8)-1)*8+i%8-2] < 1 || tahta[i] == -3 && i/8-1 > -1 && i%8-2 > -1 && tahta[((i/8)-1)*8+i%8-2] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8-1,tahta);}
						if (tahta[i] == 3 && i/8-1 > -1 && i%8+2 < 8 && tahta[((i/8)-1)*8+i%8+2] < 1 || tahta[i] == -3 && i/8-1 > -1 && i%8+2 < 8 && tahta[((i/8)-1)*8+i%8+2] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8-1,tahta);}
						if (tahta[i] == 3 && i/8-2 > -1 && i%8+1 < 8 && tahta[((i/8)-2)*8+i%8+1] < 1 || tahta[i] == -3 && i/8-2 > -1 && i%8+1 < 8 && tahta[((i/8)-2)*8+i%8+1] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8-2,tahta);}
						if (tahta[i] == 3 && i/8+2 < 8 && i%8+1 < 8 && tahta[((i/8)+2)*8+i%8+1] < 1 || tahta[i] == -3 && i/8+2 < 8 && i%8+1 < 8 && tahta[((i/8)+2)*8+i%8+1] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8+2,tahta);}
						if (tahta[i] == 3 && i/8+2 < 8 && i%8-1 > -1 && tahta[((i/8)+2)*8+i%8-1] < 1 || tahta[i] == -3 && i/8+2 < 8 && i%8-1 > -1 && tahta[((i/8)+2)*8+i%8-1] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8+2,tahta);}
						if (tahta[i] == 3 && i/8+1 < 8 && i%8-2 > -1 && tahta[((i/8)+1)*8+i%8-2] < 1 || tahta[i] == -3 && i/8+1 < 8 && i%8-2 > -1 && tahta[((i/8)+1)*8+i%8-2] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8+1,tahta);}
						if (tahta[i] == 3 && i/8+1 < 8 && i%8+2 < 8 && tahta[((i/8)+1)*8+i%8+2] < 1 || tahta[i] == -3 && i/8+1 < 8 && i%8+2 < 8 && tahta[((i/8)+1)*8+i%8+2] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8+1,tahta);}
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i,tahta);
						}					
					}
				}				
			}
			//Bishop movements, Fil hareketleri
			if (beyazMiSiyahMi && tahta[i] == 4 || !beyazMiSiyahMi && tahta[i] == -4) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {
						uzuntasHesap(true,i,tahta);
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i,tahta);
						}					
					}
				}								
			}
			//Rook movements, Kale hareketleri
			if (beyazMiSiyahMi && tahta[i] == 5 || !beyazMiSiyahMi && tahta[i] == -5) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {
						uzuntasHesap(false,i,tahta);
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i,tahta);
						}					
					}
				}							
				if (beyazKisaRokAtilabilirMi() && tahta[63] != 5.0) {beyazKisaRokAyarla(false);} else if (siyahKisaRokAtilabilirMi() && tahta[7] != -5.0) {siyahKisaRokAyarla(false);}
                if (beyazUzunRokAtilabilirMi() && tahta[56] != 5.0) {beyazUzunRokAyarla(false);} else if (siyahUzunRokAtilabilirMi() && tahta[0] != -5.0) {siyahUzunRokAyarla(false);}
			}
			//Queen movements, Vezir hareketleri
			if (beyazMiSiyahMi && tahta[i] == 9 || !beyazMiSiyahMi && tahta[i] == -9) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {					
						uzuntasHesap(false,i,tahta);
						uzuntasHesap(true,i,tahta);
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i,tahta);
						}					
					}
				}						
			}
			//Pawn movements, Piyon hareketleri
			if (beyazMiSiyahMi && tahta[i] == 1 || !beyazMiSiyahMi && tahta[i] == -1) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {
						if (i/8-1 > 0 && tahta[i] == 1 || i/8+1 < 7 && tahta[i] == -1) {if (tahta[i] == 1 && tahta[(i/8-1)*8+i%8] == 0.0 || tahta[i] == -1 && tahta[(i/8+1)*8+i%8] == 0.0) {if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else {motorHamle.add((-1*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10)*-1);}}}
						if (i/8 == 6 && tahta[i] == 1 || i/8 == 1 && tahta[i] == -1) {if (tahta[i] == 1 && tahta[(i/8-2)*8+i%8] == 0.0 && tahta[(i/8-1)*8+i%8] == 0.0 || tahta[i] == -1 && tahta[(i/8+2)*8+i%8] == 0.0 && tahta[(i/8+1)*8+i%8] == 0.0)  {if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else {motorHamle.add((-1*100000-(i/8+2)*10000-(i%8)*1000-(i/8)*100-(i%8)*10)*-1);}}}
						if (tahta[i] == 1 && i/8-1 > 0 && i%8-1 > -1 && tahta[(i/8-1)*8+i%8-1] < 0 || tahta[i] == -1 && i/8+1 < 7 && i%8-1 > -1 && tahta[(i/8+1)*8+i%8-1] > 0) {captures++; if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else {motorHamle.add((-1*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10)*-1);}}
						if (tahta[i] == 1 && i/8-1 > 0 && i%8+1 < 8 && tahta[(i/8-1)*8+i%8+1] < 0 || tahta[i] == -1 && i/8+1 < 7 && i%8+1 < 8 && tahta[(i/8+1)*8+i%8+1] > 0) {captures++;if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else {motorHamle.add((-1*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10)*-1);}}
						if (tahta[i] > 0 && (hamleler.size() > 0) && hamleler.get(hamleler.size()-1)/100000 == -1 && i/8 == 3 && ((hamleler.get(hamleler.size()-1)*-1)/10000%10) == 3 && ((hamleler.get(hamleler.size()-1)*-1)/100%10) == 1 || tahta[i] < 0 && (hamleler.size() > 0) && hamleler.get(hamleler.size()-1)/100000 == 1 && i/8 == 4 && (hamleler.get(hamleler.size()-1)/10000%10) == 4 && (hamleler.get(hamleler.size()-1)/100%10) == 6) { System.out.println("Geçerken alma !"); if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-1)*10000+((hamleler.get(hamleler.size()-1)/1000%10)*-1)*1000+(i/8)*100+(i%8)*10);} else {motorHamle.add(1*100000+(i/8+1)*10000+((hamleler.get(hamleler.size()-1)/1000%10))*1000+(i/8)*100+(i%8)*10);}}
						if (i/8 == 1 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8] == 0.0) {motorHamle.add(9*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
						if (i/8 == 6 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8] == 0.0) {motorHamle.add(-9*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-4*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-3*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-5*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}
						if (i/8 == 1 && i%8-1 > -1 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8-1] < 0 || i/8 == 6 && i%8-1 > -1 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8-1] > 0) {if (tahta[i] > 0) {motorHamle.add(9*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);} else {motorHamle.add(-9*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-4*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-3*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-5*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
						if (i/8 == 1 && i%8+1 < 8 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8+1] < 0 || i/8 == 6 && i%8+1 < 8 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8+1] > 0) {if (tahta[i] > 0) {motorHamle.add(9*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);} else {motorHamle.add(-9*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-4*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-3*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-5*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}						
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i,tahta);
						}					
					}		
				}									
			}
		}		 
	}
	public int acmazTest(byte i, int j, double[] tahta) {     	
    	if (j > 0 && (sahKonumAl()/100)%10 == i%8 && (sahKonumAl()/1000)%10 > i/8 || j < 0 && sahKonumAl()%10 == i%8 && (sahKonumAl()/10)%10 > i/8) {    		
    		if (j > 0) {return acmazKontrol(j,1,i,(sahKonumAl()/1000%10 - i/8)-1,tahta);} else if (j < 0) {return acmazKontrol(j,1,i,(sahKonumAl()/10%10 - i/8)-1,tahta);}    		    	
    	}
    	else if (j > 0 && (sahKonumAl()/100)%10 == i%8 && (sahKonumAl()/1000)%10 < i/8 || j < 0 && sahKonumAl()%10 == i%8 && (sahKonumAl()/10)%10 < i/8) {    		
    		if (j > 0) {return acmazKontrol(j,2,i,i/8-(sahKonumAl()/1000)%10-1,tahta);} else if (j < 0)  {return acmazKontrol(j,2,i,i/8-(sahKonumAl()/10)%10-1,tahta);}    		
    	}
    	else if (j > 0 && (sahKonumAl()/1000)%10 == i/8 && (sahKonumAl()/100)%10 > i%8 || j < 0 && (sahKonumAl()/10)%10 == i/8 && sahKonumAl()%10 > i%8) {    		
    		if (j > 0) {return acmazKontrol(j,3,i,(sahKonumAl()/100%10 - i%8)-1,tahta);} else if (j < 0)  {return acmazKontrol(j,3,i,(sahKonumAl()%10 - i%8)-1,tahta);}    		
    	}
    	else if (j > 0 && (sahKonumAl()/1000)%10 == i/8 && (sahKonumAl()/100)%10 < i%8 || j < 0 && (sahKonumAl()/10)%10 == i/8 && sahKonumAl()%10 < i%8) {    		
    		if (j > 0) {return acmazKontrol(j,4,i,(i%8-sahKonumAl()/100%10)-1,tahta);} else if (j < 0)  {return acmazKontrol(j,4,i,(i%8-sahKonumAl()%10)-1,tahta);}    		
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) %9 == i%9 && (sahKonumAl()/1000)%10 > i/8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 9 == i%9 && (sahKonumAl()/10)%10 > i/8) {    		
    		if (j > 1) {return acmazKontrol(j,5,i,(((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)-i)/9-1,tahta);} else if (j < -1)  {return acmazKontrol(j,5,i,(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)-i)/9-1,tahta);}
    		else {    			
    			if (j == 1) {return piyonAcmaz(j,5,i,(((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)-i)/9-1,tahta);}
    		}
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) %9 == i%9 && (sahKonumAl()/1000)%10 < i/8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 9 == i%9 && (sahKonumAl()/10)%10 < i/8) {    		
    		if (j > 1) {return acmazKontrol(j,6,i,(i-(((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)))/9-1,tahta);} else if (j < -1)  {return acmazKontrol(j,6,i,(i-(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)))/9-1,tahta);}
    		else {    			
    			if (j == -1) {return piyonAcmaz(j,6,i,(i-(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)))/9-1,tahta);}
    		}
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) % 7 == i%7 && (sahKonumAl()/1000)%10 > i/8 && sahKonumAl()/100%10 < i%8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 7 == i%7 && (sahKonumAl()/10)%10 > i/8 && sahKonumAl()%10 < i%8) {    	
    		if (j > 1) {return acmazKontrol(j,7,i,((((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10))-i)/7-1,tahta);} else if (j < -1)  {return acmazKontrol(j,7,i,((((sahKonumAl()/10)%10)*8+(sahKonumAl()%10))-i)/7-1,tahta);}    		
    		else {
    			if (j == 1) {return piyonAcmaz(j,7,i,((((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10))-i)/7-1,tahta);}
    		}
    	}
    	else if (j > 0 && (((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)) % 7 == i%7 && (sahKonumAl()/1000)%10 < i/8 && sahKonumAl()/100%10 > i%8 || j < 0 && (((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)) % 7 == i%7 && (sahKonumAl()/10)%10 < i/8 && sahKonumAl()%10 > i%8) {    		
    		if (j > 1) {return acmazKontrol(j,8,i,(i-(((sahKonumAl()/1000)%10)*8+((sahKonumAl()/100)%10)))/7-1,tahta);} else if (j < -1)  {return acmazKontrol(j,8,i,(i-(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)))/7-1,tahta);}
    		else {
    			if (j == -1) {return piyonAcmaz(j,8,i,(i-(((sahKonumAl()/10)%10)*8+(sahKonumAl()%10)))/7-1,tahta);}
    		}
    	}     	
    	return 0;
    }
	public int acmazKontrol(int tasDeger,int hangiYon,int i,int sahTaraf,double[] tahta) {    	
    	LinkedList<Integer> acmazKare = new LinkedList<Integer>();    	    	    			   
    	switch(hangiYon) {    	
    		case 1:      			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8] != 0) {
    					return 0;
    				} else {    					
    					acmazKare.add((i/8+p)*8+i%8);
    				}    				
    			}
    			for (int y = i/8-1,x = i%8;y > -1;y--) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || y == 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {
    					acmazKare.add(y*8+x);
    					acmazHesap(i,true,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add((y*8+x));
    				}    				
    			}    			
    			break;
    		case 2:      			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8] != 0) {
    					return 0;
    				} else {
    					acmazKare.add((i/8-p)*8+i%8);
    				}    				
    			}
    			for (int y = i/8+1,x = i%8;y < 8;y++) {    				    				
    				if ((tahta[i] > 0 && (tahta[y*8+x] != -5 && tahta[y*8+x] != -9 && tahta[y*8+x] != 0) || tahta[i] < 0 && (tahta[y*8+x] != 5 && tahta[y*8+x] != 9 && tahta[y*8+x] != 0))  || y == 7) {    					
    					return 0;
    				}    				    				
    				if (tahta[i] > 0 && (tahta[y*8+x] == -5 || tahta[y*8+x] == -9) || tahta[i] < 0 && (tahta[y*8+x] == 5 || tahta[y*8+x] == 9)) {
    					acmazKare.add(y*8+x);
    					acmazHesap(i,true,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add(y*8+x);
    				}
    			}
    			break;
    		case 3:    			    			
    			for (int p = 1;p <= sahTaraf;p++) {    				
    				if (tahta[(i/8)*8+i%8+p] != 0) {    					
    					return 0;
    				} else {
    					acmazKare.add((i/8)*8+i%8+p);
    				}    				
    			}
    			for (int y = i/8,x = i%8-1;x > -1;x--) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || x == 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {
    					acmazKare.add(y*8+x);
    					acmazHesap(i,true,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add(y*8+x);
    				}
    			}
    			break;
    		case 4:    			
    			for (int p = 1;p <= sahTaraf;p++) {    				    			
    				if (tahta[(i/8)*8+i%8-p] != 0) {    			    					
    					return 0;
    				} else {
    					acmazKare.add((i/8)*8+i%8-p);    				
    				}    				
    			}
    			for (int y = i/8,x = i%8+1;x < 8;x++) {
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || x == 7) {    					
    					return 0;
    				}    		
    				acmazHesap(i,true,acmazKare);
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {
    					acmazKare.add(y*8+x);
    					acmazHesap(i,true,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add(y*8+x);    					
    				}
    						    				
    			}
    			break;    			
    		case 5:    		
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8+p] != 0) {
    					return 0;
    				} else {
    					acmazKare.add((i/8+p)*8+i%8+p);
    				}
    			}
    			for (int y = i/8-1,x = i%8-1;x > -1 && y > -1;y--,x--) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || x == 0 && y == 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {    					
    					acmazKare.add(y*8+x);
    					acmazHesap(i,false,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add(y*8+x);    					
    				}
    			}    			
    			break;    			    		
    		case 6:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8-p] != 0) {
    					return 0;
    				} else {
    					acmazKare.add((i/8-p)*8+i%8-p);
    				}    				
    			}
    			for (int y = i/8+1,x = i%8+1;x < 8 && y < 8;y++,x++) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || x == 7 && y == 7) {    					
    					return 0;
    				}    				
    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {    					
    					acmazKare.add(y*8+x);
    					acmazHesap(i,false,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add(y*8+x);
    				}
    			}
    			break;
    		case 7:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8-p] != 0) {
    					return 0;
    				} else {
    					acmazKare.add((i/8+p)*8+i%8-p);
    				}    				
    			}
    			for (int y = i/8-1,x = i%8+1;x < 8 && y > -1;y--,x++) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || x == 7 && y == 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {
    					acmazKare.add(y*8+x);
    					acmazHesap(i,false,acmazKare);
    					return 1;
    				} else {
    					acmazKare.add(y*8+x);
    				}
    			}
    			break;
    		case 8:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8+p] != 0) {
    					return 0;
    				} else {
    					acmazKare.add((i/8-p)*8+i%8+p);
    				}
    				
    			}
    			for (int y = i/8+1,x = i%8-1;x > -1 && y < 8;y++,x--) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tahta[i] < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) || x == 0 && y == 7) {    					
    					return 0;
    				}    				    				
    				if (tahta[i] > 0 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tahta[i] < 0 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {
    					acmazKare.add(y*8+x);
    					acmazHesap(i,false,acmazKare);
    					return 1;    					
    				} else {
    					acmazKare.add(y*8+x);
    				}
    			}    			
    			break;
    	}
    	return 0;
    }
	 public int piyonAcmaz(int tasDeger, int hangiYon, int i, int sahTaraf, double[] tahta) {    	
	    	switch (hangiYon) {
	    	case 5:    		
	    		for (int p = 1;p <= sahTaraf;p++) {    			
	    			if (tahta[(i/8+p)*8+i%8+p] != 0) {    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8-1,x = i%8-1;x > -1 && y > -1;y--,x--) {
	    			if (((y == i/8-1 && x == i%8-1)) && (tahta[y*8+x] == -9 || tahta[y*8+x] == -4)) {
	    				arayuzCizilecekHamleAyarla(tasDeger,i,x,y);
	    				return 1;
	    			} else if (tahta[y*8+x] == -9 || tahta[y*8+x] == -4) {
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
	    			if (((y == i/8+1 && x == i%8+1)) && (tahta[y*8+x] == 9 || tahta[y*8+x] == 4)) {
	    				arayuzCizilecekHamleAyarla(tasDeger,i,x,y);
	    				return 1;
	    			} else if (tahta[y*8+x] == 9 || tahta[y*8+x] == 4) {
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
	    			if (((y == i/8-1 && x == i%8+1)) && (tahta[y*8+x] == -9 || tahta[y*8+x] == -4)) {
	    				arayuzCizilecekHamleAyarla(tasDeger,i,x,y);
	    				return 1;
	    			} else if (tahta[y*8+x] == -9 || tahta[y*8+x] == -4) {
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
	    			if (((y == i/8+1 && x == i%8-1)) && (tahta[y*8+x] == 9 || tahta[y*8+x] == 4)) {
	    				arayuzCizilecekHamleAyarla(tasDeger,i,x,y);
	    				return 1;
	    			} else if (tahta[y*8+x] == 9 || tahta[y*8+x] == 4) {
	    				return 1;
	    			}
	    		}
	    		break;
	    	}	    	
	    	return 0;
	    }
	public void sahTehtid() {     	    	
   	 sahTehtid = new ArrayList<Integer>(
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
	public void sahTehtid(byte j, double[] tahta) { 		
    	sahTehtid();    	    	
    	for (byte i = 0;i < tahta.length;i++) {    		    		    	
    		if (tahta[i] == 900 || tahta[i] == -900) {     			    		
        		if (i/8-1 > -1 && tahta[i] == 900 || i/8-1 > -1 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8,i,tahta);}
        		if (i/8+1 < 8 && tahta[i] == 900 || i/8+1 < 8 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8,i,tahta);}
        		if (i%8+1 < 8 && tahta[i] == 900 || i%8+1 < 8 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8))*8+i%8+1,i,tahta);}
        		if (i%8-1 > -1 && tahta[i] == 900 || i%8-1 > -1 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8))*8+i%8-1,i,tahta);}
        		if (i%8-1 > -1 && i/8+1 < 8 && tahta[i] == 900 || i%8-1 > -1 && i/8+1 < 8 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8-1,i,tahta);}
        		if (i%8+1 < 8 && i/8+1 < 8 && tahta[i] == 900 || i%8+1 < 8 && i/8+1 < 8 &&  tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8+1,i,tahta);}
        		if (i%8+1 < 8 && i/8-1 > -1 && tahta[i] == 900 || i%8+1 < 8 && i/8-1 > -1 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8+1,i,tahta);}
        		if (i%8-1 > -1 && i/8-1 > -1 && tahta[i] == 900 || i%8-1 > -1 && i/8-1 > -1 && tahta[i] == -900) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8-1,i,tahta);}
        	}
    		if (tahta[i] == 3 || tahta[i] == -3) {
    			if (tahta[i] == 3 && i/8-2 > -1 && i%8-1 > -1 || tahta[i] == -3 && i/8-2 > -1 && i%8-1 > -1) {sahTehtidAyarla(tahta[i], ((i/8)-2)*8+i%8-1,i,tahta);}	
				if (tahta[i] == 3 && i/8-1 > -1 && i%8-2 > -1 || tahta[i] == -3 && i/8-1 > -1 && i%8-2 > -1) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8-2,i,tahta);}
				if (tahta[i] == 3 && i/8-1 > -1 && i%8+2 < 8 || tahta[i] == -3 && i/8-1 > -1 && i%8+2 < 8) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8+2,i,tahta);}
				if (tahta[i] == 3 && i/8-2 > -1 && i%8+1 < 8 || tahta[i] == -3 && i/8-2 > -1 && i%8+1 < 8) {sahTehtidAyarla(tahta[i], ((i/8)-2)*8+i%8+1,i,tahta);}
				if (tahta[i] == 3 && i/8+2 < 8 && i%8+1 < 8 || tahta[i] == -3 && i/8+2 < 8 && i%8+1 < 8) {sahTehtidAyarla(tahta[i], ((i/8)+2)*8+i%8+1,i,tahta);}
				if (tahta[i] == 3 && i/8+2 < 8 && i%8-1 > -1 || tahta[i] == -3 && i/8+2 < 8 && i%8-1 > -1) {sahTehtidAyarla(tahta[i], ((i/8)+2)*8+i%8-1,i,tahta);}
				if (tahta[i] == 3 && i/8+1 < 8 && i%8-2 > -1 || tahta[i] == -3 && i/8+1 < 8 && i%8-2 > -1) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8-2,i,tahta);}
				if (tahta[i] == 3 && i/8+1 < 8 && i%8+2 < 8 || tahta[i] == -3 && i/8+1 < 8 && i%8+2 < 8) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8+2,i,tahta);}
    		}
    		if (tahta[i] == 4 || tahta[i] == -4) {
    			sahTehtidUzunTasHesap(true,i,tahta);
    		}
    		if (tahta[i] == 5 || tahta[i] == -5) {
    			sahTehtidUzunTasHesap(false,i,tahta);
    		}
    		if (tahta[i] == 9 || tahta[i] == -9) {
    			sahTehtidUzunTasHesap(true,i,tahta);
    			sahTehtidUzunTasHesap(false,i,tahta);
    		}
    		if (tahta[i] == 1 || tahta[i] == -1) {
    			if (tahta[i] == 1 && i/8-1 > 0 && i%8-1 > -1 || tahta[i] == -1 && i/8+1 < 7 && i%8-1 > -1) {if (tahta[i] > 0) {sahTehtid.set((i/8-1)*8+i%8-1, sahTehtid.get((i/8-1)*8+i%8-1)+10);}else{sahTehtid.set((i/8+1)*8+i%8-1, sahTehtid.get((i/8+1)*8+i%8-1)+1);}}
				if (tahta[i] == 1 && i/8-1 > 0 && i%8+1 < 8 || tahta[i] == -1 && i/8+1 < 7 && i%8+1 < 8) {if (tahta[i] > 0) {sahTehtid.set((i/8-1)*8+i%8+1, sahTehtid.get((i/8-1)*8+i%8+1)+10);}else{sahTehtid.set((i/8+1)*8+i%8+1, sahTehtid.get((i/8+1)*8+i%8+1)+1);}}
    		}    		
    	}    	
    }
	 public void sahCekisArama(double tas, byte i, double[] tahta) {	    	   
	    	LinkedList<Integer> mudaheleKareleri = sahMudaheleKareAl();
	    	for (int p = 0;p < mudaheleKareleri.size();p++) {    		
	    		if (Math.abs(tas) == 3) {
	        		if (i/8-2 > -1 && i%8-1 > -1 && ((i/8)-2)*8+i%8-1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8-2);}	
	    			if (i/8-1 > -1 && i%8-2 > -1 && ((i/8)-1)*8+i%8-2 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8-1);}
	    			if (i/8-1 > -1 && i%8+2 < 8 && ((i/8)-1)*8+i%8+2 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8-1);}
	    			if (i/8-2 > -1 && i%8+1 < 8 && ((i/8)-2)*8+i%8+1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8-2);}
	    			if (i/8+2 < 8 && i%8+1 < 8 && ((i/8)+2)*8+i%8+1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8+2);}
	    			if (i/8+2 < 8 && i%8-1 > -1 && ((i/8)+2)*8+i%8-1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8+2);}
	    			if (i/8+1 < 8 && i%8-2 > -1 && ((i/8)+1)*8+i%8-2 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8+1);}
	    			if (i/8+1 < 8 && i%8+2 < 8 && ((i/8)+1)*8+i%8+2 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8+1);}
	        	}
	    		else if (tas == 1) {    			
	    			if (i/8 == 6 && tahta[((i/8)-2)*8+i%8] == 0 && ((i/8)-2)*8+i%8 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8-2);}
	    			if (((i/8)-1)*8+i%8 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8-1);}
	    		}
	    		else if (tas == -1) {    			    		
	    			if (i/8 == 1 && tahta[((i/8)+2)*8+i%8] == 0 && ((i/8)+2)*8+i%8 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8+2);}
	    			if (((i/8)+1)*8+i%8 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8+1);}
	    		}
	    		else if (Math.abs(tas) == 9) {
	    			sahCekisUzunTasHesap(true,i,tahta,mudaheleKareleri.get(p));
	    			sahCekisUzunTasHesap(false,i,tahta,mudaheleKareleri.get(p));
	    		}
	    		else if (Math.abs(tas) == 5) {
	    			sahCekisUzunTasHesap(false,i,tahta,mudaheleKareleri.get(p));
	    		}
	    		else if (Math.abs(tas) == 4) {
	    			sahCekisUzunTasHesap(true,i,tahta,mudaheleKareleri.get(p));
	    		}
	    	}    	
	    }
	public void sahTehtidAyarla(double i, int kare, byte sahCekenTas, double[] tahta) {    	
    	if (i > 0) {
    		sahTehtid.set(kare, sahTehtid.get(kare)+10);
    		if (tahta[kare] == -900) {
    			System.out.println("Test");
    			toplamSahSayisi++;
    			sahCekenTasAyarla(sahCekenTas);
    			siyahSahCekisAyarla((byte) (siyahSahCekisAl()+1));
    			sahCekenTasNerede(sahCekenTasAl(),(byte)tahta[sahCekenTasAl()],tahta);
    		}
    	}
    	else {
    		sahTehtid.set(kare, sahTehtid.get(kare)+1);
    		if (tahta[kare] == 900) {
    			System.out.println("Test 2");
    			toplamSahSayisi++;
    			sahCekenTasAyarla(sahCekenTas);
    			beyazSahCekisAyarla((byte) (beyazSahCekisAl()+1));
    			sahCekenTasNerede(sahCekenTasAl(),(byte)tahta[sahCekenTasAl()],tahta);
    		}
    	}
     }    	
	 public void uzuntasHesap(boolean yon, byte i, double[] tahta) {
	    	if (yon) {    		
	    		for (int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1;solAltKose <= 7 || solUstKose <= 7 || sagUstKose <= 7 || sagAltKose <= 7;solAltKose++,solUstKose++,sagUstKose++,sagAltKose++) {    			
	    			if (i/8+solAltKose < 8 && i%8-solAltKose > -1 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] < 1 && tahta[i] > 0 || i/8+solAltKose < 8 && i%8-solAltKose > -1 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] > -1 && tahta[i] < 0) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-solAltKose,i/8+solAltKose,tahta);if (tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0) {solAltKose = 8;}} else {solAltKose = 8;}
	    			if (i/8-solUstKose > -1 && i%8-solUstKose > -1 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] < 1  && tahta[i] > 0 || i/8-solUstKose > -1 && i%8-solUstKose > -1 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] > -1  && tahta[i] < 0) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-solUstKose,i/8-solUstKose,tahta);if (tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0) {solUstKose = 8;}} else {solUstKose = 8;}
	    			if (i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] < 1 && tahta[i] > 0 || i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] > -1 && tahta[i] < 0) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+sagUstKose,i/8-sagUstKose,tahta);if (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0) {sagUstKose = 8;}} else {sagUstKose = 8;}
	    			if (i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] < 1 && tahta[i] > 0 || i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] > -1 && tahta[i] < 0) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+sagAltKose,i/8+sagAltKose,tahta);if (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0) {sagAltKose = 8;}} else {sagAltKose = 8;}    			
	    		}    		
	    	}
	    	else {
	    		for (int ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1;ustDikey <= i/8 || altDikey <= (7-(i/8)) || solYatay <= i%8 || sagYatay <= (7-(i%8));solYatay++,sagYatay++,ustDikey++,altDikey++) {    			
	    			if ((ustDikey <= i/8) && tahta[(((i/8)-ustDikey)*8)+i%8] < 1 && tahta[i] > 0 || (ustDikey <= i/8) && tahta[(((i/8)-ustDikey)*8)+i%8] > -1 && tahta[i] < -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8-ustDikey,tahta);if (tahta[(((i/8)-ustDikey)*8)+i%8] != 0.0) {ustDikey = i/8+1;}} else {ustDikey= i/8+1;}
	    			if ((altDikey <= (7-(i/8))) && tahta[(((i/8)+altDikey)*8)+i%8] < 1 && tahta[i] > 0 || (altDikey <= (7-(i/8))) && tahta[(((i/8)+altDikey)*8)+i%8] > -1 && tahta[i] < -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8+altDikey,tahta);if (tahta[(((i/8)+altDikey)*8)+i%8] != 0.0) {altDikey = (8-(i/8));}} else {altDikey= (8-(i/8));}
	    			if ((sagYatay <= (7-(i%8))) && tahta[(((i/8))*8)+i%8+sagYatay] < 1 && tahta[i] > 0 || (sagYatay <= (7-(i%8))) && tahta[(((i/8))*8)+i%8+sagYatay] > -1 && tahta[i] < -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+sagYatay,i/8,tahta);if (tahta[(((i/8))*8)+i%8+sagYatay] != 0.0) {sagYatay = (8-(i%8));}} else {sagYatay= (8-(i%8));}
	    			if ((solYatay <= i%8) && tahta[(((i/8))*8)+i%8-solYatay] < 1 && tahta[i] > 0 || (solYatay <= i%8) && tahta[(((i/8))*8)+i%8-solYatay] > -1 && tahta[i] < -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-solYatay,i/8,tahta);if (tahta[(((i/8))*8)+i%8-solYatay] != 0.0) {solYatay = i%8+1;}} else {solYatay= i%8+1;}    			
	    		}
	    	}
	}
	 public void sahTehtidUzunTasHesap(boolean yon, byte i, double[] tahta) {
	    	if (yon) {    		
	    		for (int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1;solAltKose <= 7 || solUstKose <= 7 || sagUstKose <= 7 || sagAltKose <= 7;solAltKose++,solUstKose++,sagUstKose++,sagAltKose++) {    			    			 
	    			if (i/8+solAltKose < 8 && i%8-solAltKose > -1 && (tahta[(i/8+solAltKose)*8+i%8-solAltKose] > -1 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] == -900) && tahta[i] > 0 || i/8+solAltKose < 8 && i%8-solAltKose > -1 && (tahta[(i/8+solAltKose)*8+i%8-solAltKose] < 1 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] == 900) && tahta[i] < 0) {sahTehtidAyarla(tahta[i], (i/8+solAltKose)*8+i%8-solAltKose,i,tahta);if (tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 900.0 && tahta[i] < -1 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] != -900.0 && tahta[i] > 0) {solAltKose = 8;}} else {solAltKose = 8;}
	    			if (i/8-solUstKose > -1 && i%8-solUstKose > -1 && (tahta[(i/8-solUstKose)*8+i%8-solUstKose] > -1 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] == -900)  && tahta[i] > 0 || i/8-solUstKose > -1 && i%8-solUstKose > -1 && (tahta[(i/8-solUstKose)*8+i%8-solUstKose] < 1 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] == 900)  && tahta[i] < 0) {sahTehtidAyarla(tahta[i], (i/8-solUstKose)*8+i%8-solUstKose,i,tahta);if (tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 900.0 && tahta[i] < -1 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] != -900.0 && tahta[i] > 0) {solUstKose = 8;}} else {solUstKose = 8;}
	    			if (i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] > -1 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == -900) && tahta[i] > 0 || i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] < 1 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == 900) && tahta[i] < 0) {sahTehtidAyarla(tahta[i], (i/8-sagUstKose)*8+i%8+sagUstKose,i,tahta);if (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 900.0 && tahta[i] < -1 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != -900.0 && tahta[i] > 0) {sagUstKose = 8;}} else {sagUstKose = 8;}
	    			if (i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] > -1 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == -900) && tahta[i] > 0 || i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] < 1 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == 900 ) && tahta[i] < 0) {sahTehtidAyarla(tahta[i], (i/8+sagAltKose)*8+i%8+sagAltKose,i,tahta);if (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 900.0 && tahta[i] < -1 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != -900.0 && tahta[i] > 0) {sagAltKose = 8;}} else {sagAltKose = 8;}
	    		}    		
	    	}
	    	else {
	    		for (int ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1;ustDikey <= i/8 || altDikey <= (7-(i/8)) || solYatay <= i%8 || sagYatay <= (7-(i%8));solYatay++,sagYatay++,ustDikey++,altDikey++) {    			
	    			if ((ustDikey <= i/8) && (tahta[(((i/8)-ustDikey)*8)+i%8] > -1 || tahta[(((i/8)-ustDikey)*8)+i%8] == -900) && tahta[i] > 0 || (ustDikey <= i/8) && (tahta[(((i/8)-ustDikey)*8)+i%8] < 1  || tahta[(((i/8)-ustDikey)*8)+i%8] == 900)&& tahta[i] < -1) {sahTehtidAyarla(tahta[i], (((i/8)-ustDikey)*8)+i%8,i,tahta);if (tahta[(((i/8)-ustDikey)*8)+i%8] != 0.0 && tahta[(((i/8)-ustDikey)*8)+i%8] != 900.0 && tahta[i] < -1 || tahta[(((i/8)-ustDikey)*8)+i%8] != 0.0 && tahta[(((i/8)-ustDikey)*8)+i%8] != -900.0 && tahta[i] > 0) {ustDikey = i/8+1;}} else {ustDikey= i/8+1;}
	    			if ((altDikey <= (7-(i/8))) && (tahta[(((i/8)+altDikey)*8)+i%8] > -1 || tahta[(((i/8)+altDikey)*8)+i%8] == -900) && tahta[i] > 0 || (altDikey <= (7-(i/8))) && (tahta[(((i/8)+altDikey)*8)+i%8] < 1 || tahta[(((i/8)+altDikey)*8)+i%8] == 900) && tahta[i] < -1) {sahTehtidAyarla(tahta[i], (((i/8)+altDikey)*8)+i%8,i,tahta);if (tahta[(((i/8)+altDikey)*8)+i%8] != 0.0 && tahta[(((i/8)+altDikey)*8)+i%8] != 900.0 && tahta[i] < -1 || tahta[(((i/8)+altDikey)*8)+i%8] != 0.0 && tahta[(((i/8)+altDikey)*8)+i%8] != -900.0 && tahta[i] > 0) {altDikey = (8-(i/8));}} else {altDikey= (8-(i/8));}
	    			if ((sagYatay <= (7-(i%8))) && (tahta[(((i/8))*8)+i%8+sagYatay] > -1 || tahta[(((i/8))*8)+i%8+sagYatay] == -900) && tahta[i] > 0 || (sagYatay <= (7-(i%8))) && (tahta[(((i/8))*8)+i%8+sagYatay] < 1 || tahta[(((i/8))*8)+i%8+sagYatay] == 900) && tahta[i] < -1) {sahTehtidAyarla(tahta[i], (((i/8))*8)+i%8+sagYatay,i,tahta);if (tahta[(((i/8))*8)+i%8+sagYatay] != 0.0 && tahta[(((i/8))*8)+i%8+sagYatay] != 900.0 && tahta[i] < -1 || tahta[(((i/8))*8)+i%8+sagYatay] != 0.0 && tahta[(((i/8))*8)+i%8+sagYatay] != -900.0 && tahta[i] > 0) {sagYatay = (8-(i%8));}} else {sagYatay= (8-(i%8));}
	    			if ((solYatay <= i%8) && (tahta[(((i/8))*8)+i%8-solYatay] > -1 || tahta[(((i/8))*8)+i%8-solYatay] == -900) && tahta[i] > 0 || (solYatay <= i%8) && (tahta[(((i/8))*8)+i%8-solYatay] < 1 || tahta[(((i/8))*8)+i%8-solYatay] == 900) && tahta[i] < -1) {sahTehtidAyarla(tahta[i], (((i/8))*8)+i%8-solYatay,i,tahta);if (tahta[(((i/8))*8)+i%8-solYatay] != 0.0 && tahta[(((i/8))*8)+i%8-solYatay] != 900.0 && tahta[i] < -1 || tahta[(((i/8))*8)+i%8-solYatay] != 0.0 && tahta[(((i/8))*8)+i%8-solYatay] != -900.0 && tahta[i] > 0) {solYatay = i%8+1;}} else {solYatay= i%8+1;}    			
	    		}
	    	}    	    	    	
	    }
	public void arayuzCizilecekHamleAyarla(double j, int i,int x, int y, double[] tahta) {
		if (tahta[y*8+x] != 0.0) {
			captures++;
		}
    	if (j > 0) {
    		motorHamle.add((int) (j*100000+(y)*10000+(x)*1000+(i/8)*100+(i%8)*10));
    	}
    	else {
    		motorHamle.add((int) ((j*100000-(y)*10000-(x)*1000-(i/8)*100-(i%8)*10)*-1));
    	}
    	//varyantlar+= motorHamle.getLast();
    }	
	public int[] hamleleriAl(boolean siraKimde, double[] tahta, PerformansTest p) {
		arama(siraKimde, tahta, p);
		int[] hamleler = new int[motorHamle.size()];
		hamleler = motorHamle.stream().mapToInt(i->i).toArray();;		
		return hamleler;
	}

}
