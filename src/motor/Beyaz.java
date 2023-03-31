package motor;

import java.util.ArrayList;
import java.util.List;

public class Beyaz extends OrtakHesap {			
	List<Integer> sahMudaheleKare = new ArrayList<>(20),hamle = new ArrayList<>(50);	
	
	public List<Integer> hamleleriAl(int[] tahta, List<Integer> sahTehtid, PerformansTest p) {
		//900 King, 9 Queen, 3 Knight, 4 Bishop, 5 Rook, 1 Pawn
		hamle = new ArrayList<>(50);		
		for (int i = 0;i < tahta.length;i++) {
			if (tahta[i] > 0) {
				if (tahta[i] == 1) {					
					piyon(i,i%8,i/8,tahta,p);						
				} else if (tahta[i] == 3) {				
					at(i,i%8,i/8,tahta,p);						
				} else if (tahta[i] == 4) {					
					fil(i,i%8,i/8,tahta,p);									
				} else if (tahta[i] == 5) {					
					kale(i,i%8,i/8,tahta,p);					
				} else if (tahta[i] == 9) {					
					vezir(i,i%8,i/8,tahta,p);						
				} else if (tahta[i] == 900) {
					sah(i,i%8,i/8,tahta,sahTehtid);
					rok(tahta,sahTehtid,p);
				}
			}			
		}				
		return hamle;
	}		
	public void sah(int i, int x, int y, int[] tahta, List<Integer> sahTehtid) {
		if (y-1 > -1 && tahta[i-8] < 1 && sahTehtid.get(i-8)%10 < 1) {hamleEkle(tahta[i],i,x,y-1);}
		if (y+1 < 8  && tahta[i+8] < 1 && sahTehtid.get(i+8)%10 < 1) {hamleEkle(tahta[i],i,x,y+1);}
		if (x+1 < 8  && tahta[i+1] < 1 && sahTehtid.get(i+1)%10 < 1) {hamleEkle(tahta[i],i,x+1,y);}
		if (x-1 > -1 && tahta[i-1] < 1 && sahTehtid.get(i-1)%10 < 1) {hamleEkle(tahta[i],i,x-1,y);}
		if (x-1 > -1 && y+1 < 8 && tahta[i+7] < 1  && sahTehtid.get(i+7)%10 < 1) {hamleEkle(tahta[i],i,x-1,y+1);}
		if (x+1 < 8  && y+1 < 8 && tahta[i+9] < 1  && sahTehtid.get(i+9)%10 < 1) {hamleEkle(tahta[i],i,x+1,y+1);}
		if (x+1 < 8  && y-1 > -1 && tahta[i-7] < 1 && sahTehtid.get(i-7)%10 < 1) {hamleEkle(tahta[i],i,x+1,y-1);}
		if (x-1 > -1 && y-1 > -1 && tahta[i-9] < 1 && sahTehtid.get(i-9)%10 < 1) {hamleEkle(tahta[i],i,x-1,y-1);}		
	}
	public void rok(int[] tahta, List<Integer> sahTehtid, PerformansTest p) {
		if (p.getBeyazSahCekis() == 0) {
			if (p.isBeyazSahKisaRok() && tahta[63] == 5.0 && tahta[62] == 0.0 && tahta[61] == 0.0 && sahTehtid.get(62)%10 < 1 && sahTehtid.get(61)%10 < 1) {hamle.add(2000000);} 
			if (p.isBeyazSahUzunRok() && tahta[56] == 5.0 && tahta[57] == 0.0 && tahta[58] == 0.0 && tahta[59] == 0.0 && sahTehtid.get(59)%10 < 1 && sahTehtid.get(58)%10 < 1) {hamle.add(6000000);}
		}
	}
	public void vezir(int i, int x, int y, int[] tahta, PerformansTest p) {
		if (acmazTest(i,tahta[i],tahta,p) == 0) {
			if (p.getBeyazSahCekis() == 0) {
				capraz(i,x,y,tahta);
				dikeyYatay(i,x,y,tahta);
			} else if (p.getBeyazSahCekis() == 1) {					
				sahCekisArama(tahta[i],i,i%8,i/8,tahta,p);
			}				
		}		
	}
	public void at(int i, int x, int y, int[] tahta, PerformansTest p) {
		if (acmazTest(i,tahta[i],tahta,p) == 0) {
			if (p.getBeyazSahCekis() == 0) {
				if (y > 1) {
					if (x > 0 && tahta[i-17] < 1) {
						hamleEkle(tahta[i],i,x-1,y-2);
					}
					if (x < 7 && tahta[i-15] < 1) {
						hamleEkle(tahta[i],i,x+1,y-2);
					}
				}
				if (y < 6) {
					if (x < 7 && tahta[i+17] < 1) {
						hamleEkle(tahta[i],i,x+1,y+2);
					}
					if (x > 0 && tahta[i+15] < 1) {
						hamleEkle(tahta[i],i,x-1,y+2);
					}
				}
				if (x > 1) {
					if (y > 0 && tahta[i-10] < 1) {
						hamleEkle(tahta[i],i,x-2,y-1);
					}
					if (y < 7 && tahta[i+6] < 1) {
						hamleEkle(tahta[i],i,x-2,y+1);
					}
				}
				if (x < 6) {
					if (y > 0 && tahta[i-6]  < 1) {
						hamleEkle(tahta[i],i,x+2,y-1);
					}
					if (y < 7 && tahta[i+10] < 1) {
						hamleEkle(tahta[i],i,x+2,y+1);
					}
				}
			} else if (p.getBeyazSahCekis() == 1) {					
				sahCekisArama(tahta[i],i,i%8,i/8,tahta,p);
			}			
		}		
	}
	public void fil(int i, int x, int y, int[] tahta, PerformansTest p) {
		if (acmazTest(i,tahta[i],tahta,p) == 0) {
			if (p.getBeyazSahCekis() == 0) {
				capraz(i,x,y,tahta);
			} else if (p.getBeyazSahCekis() == 1) {					
				sahCekisArama(tahta[i],i,i%8,i/8,tahta,p);
			}			
		}		
	}
	public void kale(int i, int x, int y, int[] tahta, PerformansTest p) {
		if (acmazTest(i,tahta[i],tahta,p) == 0) {
			if (p.getBeyazSahCekis() == 0) {
				dikeyYatay(i,x,y,tahta);
			} else if (p.getBeyazSahCekis() == 1) {					
				sahCekisArama(tahta[i],i,i%8,i/8,tahta,p);
			}			
		}		
	}
	public void piyon(int i, int x, int y, int[] tahta, PerformansTest p) {
		if (piyonAcmazTest(i,tahta[i],tahta,p) == 0) {
			if (p.getBeyazSahCekis() == 0) {
				if (y-1 > 0 && tahta[i-8] == 0.0) {hamleEkle(tahta[i],i,x,y-1);}
				if (y == 6 && tahta[i-16] == 0.0 && tahta[i-8] == 0.0) {hamleEkle(10,i,x,y-2);}
				if (x-1 > -1 && y-1 > 0 && tahta[i-9] < 0) {hamleEkle(tahta[i],i,x-1,y-1);}
				if (x+1 < 8  && y-1 > 0 && tahta[i-7] < 0) {hamleEkle(tahta[i],i,x+1,y-1);}			
				if (y == 1 && tahta[i-8] == 0.0) {hamleEkle(9000,i,x,y-1);hamleEkle(400,i,x,y-1);hamleEkle(300,i,x,y-1);hamleEkle(500,i,x,y-1);}				
				if (x-1 > -1 && y == 1 && tahta[i-9] < 0) {hamleEkle(9000,i,x-1,y-1);hamleEkle(400,i,x-1,y-1);hamleEkle(300,i,x-1,y-1);hamleEkle(500,i,x-1,y-1);}
				if (x+1 < 8  && y == 1 && tahta[i-7] < 0) {hamleEkle(9000,i,x+1,y-1);hamleEkle(400,i,x+1,y-1);hamleEkle(300,i,x+1,y-1);hamleEkle(500,i,x+1,y-1);}
				if (p.getGecerkenAlma() > -1 && Math.abs(p.getGecerkenAlma()-i%8) == 1 && i/8 == 3 && gecerkenAlmaAcmaz(i,tahta,p)) {hamleEkle(100,i,p.getGecerkenAlma(),y-1);}
			} else if (p.getBeyazSahCekis() == 1) {					
				sahCekisArama(tahta[i],i,i%8,i/8,tahta,p);
			}			
		}		
	}
	public void sahCekisArama(int tasDeger, int tasKonum, int x, int y, int[] tahta, PerformansTest k) {		
		 List<Integer> mudaheleKareleri = k.sahMudaheleKareAl();
		 for (int p = 0;p < mudaheleKareleri.size();p++) {
			 switch(tasDeger) {
			 case 3:
				 if (y-2 > -1 && x-1 > -1  && tasKonum-17 == mudaheleKareleri.get(p)) {hamleEkle(3,tasKonum,x-1,y-2);}	
		   		 if (y-1 > -1 && x-2 > -1  && tasKonum-10 == mudaheleKareleri.get(p)) {hamleEkle(3,tasKonum,x-2,y-1);}
		   		 if (y-1 > -1 && x+2 < 8   && tasKonum-6  == mudaheleKareleri.get(p)) {hamleEkle(3,tasKonum,x+2,y-1);}
		   		 if (y-2 > -1 && x+1 < 8   && tasKonum-15 == mudaheleKareleri.get(p)) {hamleEkle(3,tasKonum,x+1,y-2);}
		   		 if (y+2 < 8  && x+1 < 8   && tasKonum+17 == mudaheleKareleri.get(p)) {hamleEkle(3,tasKonum,x+1,y+2);}
		   		 if (y+2 < 8  && x-1 > -1  && tasKonum+15 == mudaheleKareleri.get(p)) {hamleEkle(3,tasKonum,x-1,y+2);}
		   		 if (y+1 < 8  && x-2 > -1  && tasKonum+6  == mudaheleKareleri.get(p)) {hamleEkle(3,tasKonum,x-2,y+1);}
		   		 if (y+1 < 8  && x+2 < 8   && tasKonum+10 == mudaheleKareleri.get(p)) {hamleEkle(3,tasKonum,x+2,y+1);}
				 break;
			 case 1:
				 if (y == 6 && tahta[tasKonum-16] == 0 && tahta[tasKonum-8] == 0 && tasKonum-16 == mudaheleKareleri.get(p)) {hamleEkle(10,tasKonum,x,y-2);}
				 if (y-1 > 0 && tahta[tasKonum-8] == 0 && tasKonum-8 == mudaheleKareleri.get(p)) {hamleEkle(1,tasKonum,x,y-1);}
				 if (y-1 > 0 && x-1 > -1 && tahta[tasKonum-9] < 0 && tasKonum-9 == mudaheleKareleri.get(p)) {hamleEkle(1,tasKonum,x-1,y-1);}
				 if (y-1 > 0 && x+1 < 8 && tahta[tasKonum-7] < 0 && tasKonum-7 == mudaheleKareleri.get(p)) {hamleEkle(1,tasKonum,x+1,y-1);}
				 if (y == 3 && k.getGecerkenAlma() > -1 && Math.abs(k.getGecerkenAlma()-x) == 1 && tahta[mudaheleKareleri.get(p)] == -1) {hamleEkle(100,tasKonum,k.getGecerkenAlma(),y-1);}
				 if (y == 1 && (tahta[mudaheleKareleri.get(p)] == 0) && tasKonum-8 == mudaheleKareleri.get(p)) {hamleEkle(9000,tasKonum,x,y-1);hamleEkle(400,tasKonum,x,y-1);hamleEkle(300,tasKonum,x,y-1);hamleEkle(500,tasKonum,x,y-1);}
				 if (y == 1 && (tahta[mudaheleKareleri.get(p)] < 0) && tasKonum-9 == mudaheleKareleri.get(p)) {hamleEkle(9000,tasKonum,x-1,y-1);hamleEkle(400,tasKonum,x-1,y-1);hamleEkle(300,tasKonum,x-1,y-1);hamleEkle(500,tasKonum,x-1,y-1);}
				 if (y == 1 && (tahta[mudaheleKareleri.get(p)] < 0) && tasKonum-7 == mudaheleKareleri.get(p)) {hamleEkle(9000,tasKonum,x+1,y-1);hamleEkle(400,tasKonum,x+1,y-1);hamleEkle(300,tasKonum,x+1,y-1);hamleEkle(500,tasKonum,x+1,y-1);}
				 break;
			 case 9:
				 sahCekisYatayVeDikey(tasKonum,tahta,mudaheleKareleri.get(p));
				 sahCekisCapraz(tasKonum,tahta,mudaheleKareleri.get(p));
				 break;
			 case 5:
				 sahCekisYatayVeDikey(tasKonum,tahta,mudaheleKareleri.get(p));
				 break;
			 case 4:
				 sahCekisCapraz(tasKonum,tahta,mudaheleKareleri.get(p));
				 break;
			 }	    	
	     }	     	    		    
	 }
	public void hamleEkle(int j, int i, int x, int y) {		
		hamle.add(j*100000+(y)*10000+(x)*1000+(i/8)*100+(i%8)*10);		
	}	
	public void capraz(int i, int x, int y, int[] tahta) {
		solAltKose(i,x,y,tahta);
		solUstKose(i,x,y,tahta);
		sagUstKose(i,x,y,tahta);		
		sagAltKose(i,x,y,tahta);	
	}
	public void solAltKose(int i, int x, int y, int[] tahta) {
		for (int solAltKose = 1;solAltKose <= 7;solAltKose++) {
			if (y+solAltKose < 8 && x-solAltKose > -1 && tahta[i+solAltKose*8-solAltKose] < 1) {hamleEkle(tahta[i],i,x-solAltKose,y+solAltKose);if (tahta[i+solAltKose*8-solAltKose] != 0.0) {break;}} else {return;}
		}
	}
	public void solUstKose(int i, int x, int y, int[] tahta) {
		for (int solUstKose = 1;solUstKose <= 7;solUstKose++) {
			if (y-solUstKose > -1 && x-solUstKose > -1 && tahta[i-solUstKose*8-solUstKose] < 1) {hamleEkle(tahta[i],i,x-solUstKose,y-solUstKose);if (tahta[i-solUstKose*8-solUstKose] != 0.0) {break;}} else {return;}
		}		
	}
	public void sagUstKose(int i, int x, int y, int[] tahta) {
		for (int sagUstKose = 1;sagUstKose <= 7;sagUstKose++) {
			if (y-sagUstKose > -1 && x+sagUstKose < 8 && tahta[i-sagUstKose*8+sagUstKose] < 1) {hamleEkle(tahta[i],i,x+sagUstKose,y-sagUstKose);if (tahta[i-sagUstKose*8+sagUstKose] != 0.0) {break;}} else {return;}
		}
	}
	public void sagAltKose(int i, int x, int y, int[] tahta) {
		for (int sagAltKose = 1;sagAltKose <= 7;sagAltKose++) {
			if (y+sagAltKose < 8 && x+sagAltKose < 8 && tahta[i+sagAltKose*8+sagAltKose] < 1) {hamleEkle(tahta[i],i,x+sagAltKose,y+sagAltKose);if (tahta[i+sagAltKose*8+sagAltKose] != 0.0) {break;}} else {return;}
		}
	}
	public void dikeyYatay(int i, int x, int y, int[] tahta) {
		ustDikey(i,x,y,tahta);
		altDikey(i,x,y,tahta);
		sagYatay(i,x,y,tahta);		
		solYatay(i,x,y,tahta);
	}
	public void ustDikey(int i, int x, int y, int[] tahta) {
		for (int ustDikey = 1;ustDikey <= i/8;ustDikey++) {
			if (ustDikey <= y && tahta[i-8*ustDikey] < 1) {hamleEkle(tahta[i],i,x,y-ustDikey);if (tahta[i-8*ustDikey] != 0.0) {break;}} else {return;}
		}
	}
	public void altDikey(int i, int x, int y, int[] tahta) {
		for (int altDikey = 1;altDikey <= 7-i/8;altDikey++) {
			if (altDikey <= 7-y && tahta[i+8*altDikey] < 1) {hamleEkle(tahta[i],i,x,y+altDikey);if (tahta[i+8*altDikey] != 0.0) {break;}} else {return;}
		}	
	}
	public void sagYatay(int i, int x, int y, int[] tahta) {
		for (int sagYatay = 1;sagYatay <= 7-i%8;sagYatay++) {
			if (sagYatay <= 7-x && tahta[i+sagYatay] < 1) {hamleEkle(tahta[i],i,x+sagYatay,y);if (tahta[i+sagYatay] != 0.0) {break;}} else {return;}
		}
	}
	public void solYatay(int i, int x, int y, int[] tahta) {
		for (int solYatay = 1;solYatay <= i%8;solYatay++) {
			if (solYatay <= x && tahta[i-solYatay] < 1) {hamleEkle(tahta[i],i,x-solYatay,y);if (tahta[i-solYatay] != 0.0) {break;}} else {return;}
		}
	}
	public int acmazTest(int hedefTasKare, int tasDeger, int[] tahta, PerformansTest p) {
		int sahX = p.getBeyazSahKonum()%10, sahY = p.getBeyazSahKonum()/10%10;		
		return acmazTestYatayVeDikey(hedefTasKare,tasDeger,tahta,p,sahX,sahY);		
	}
	public int acmazTestYatayVeDikey(int hedefTasKare, int tasDeger, int[] tahta, PerformansTest p, int sahX, int sahY) {
		if (sahX == hedefTasKare%8) {
			if (sahY > hedefTasKare/8) {
				return acmazKontrol(tasDeger,1,hedefTasKare,(sahY - hedefTasKare/8)-1,tahta,p);
			}
			return acmazKontrol(tasDeger,2,hedefTasKare,hedefTasKare/8-sahY-1,tahta,p);			
		} else if (sahY == hedefTasKare/8) {
			if (sahX > hedefTasKare%8) {
				return acmazKontrol(tasDeger,3,hedefTasKare,(sahX - hedefTasKare%8)-1,tahta,p);
			}
			return acmazKontrol(tasDeger,4,hedefTasKare,(hedefTasKare%8-sahX)-1,tahta,p);			
		}		
		return acmazTestCapraz(hedefTasKare,tasDeger,tahta,p,sahX,sahY);
	}
	public int acmazTestCapraz(int hedefTasKare, int tasDeger, int[] tahta, PerformansTest p, int sahX, int sahY) {
		if ((sahY*8+sahX) %9 == hedefTasKare%9) {
			if (sahY > hedefTasKare/8 && sahX > hedefTasKare%8) {
				return acmazKontrol(tasDeger,5,hedefTasKare,(sahY*8+sahX-hedefTasKare)/9-1,tahta,p);
			} else if (sahY < hedefTasKare/8 && sahX < hedefTasKare%8) {
				return acmazKontrol(tasDeger,6,hedefTasKare,(hedefTasKare-(sahY*8+sahX))/9-1,tahta,p);
			}
		} else if ((sahY*8+sahX) % 7 == hedefTasKare%7) {
			if (sahY > hedefTasKare/8 && sahX < hedefTasKare%8) {
				return acmazKontrol(tasDeger,7,hedefTasKare,(sahY*8+sahX-hedefTasKare)/7-1,tahta,p);
			} else if (sahY < hedefTasKare/8 && sahX > hedefTasKare%8) {
				return acmazKontrol(tasDeger,8,hedefTasKare,(hedefTasKare-(sahY*8+sahX))/7-1,tahta,p);
			}
		}		
		return 0;
	}
	public int piyonAcmazTest(int hedefTasKare, int tasDeger, int[] tahta, PerformansTest p) {
		int sahX = p.getBeyazSahKonum()%10, sahY = p.getBeyazSahKonum()/10%10;
		return piyonAcmazTestYatayVeDikey(hedefTasKare,tasDeger,tahta,p,sahX,sahY);
	}
	public int piyonAcmazTestYatayVeDikey(int hedefTasKare, int tasDeger, int[] tahta, PerformansTest p, int sahX, int sahY) {
		if (sahX == hedefTasKare%8) {    		
    		if (sahY > hedefTasKare/8) {
    			return piyonAcmaz(tasDeger,1,hedefTasKare,(sahY - hedefTasKare/8)-1,tahta,p);
    		}
    		return piyonAcmaz(tasDeger,2,hedefTasKare,hedefTasKare/8-sahY-1,tahta,p);    		
    	}
		else if (sahY == hedefTasKare/8) {
			if (sahX > hedefTasKare%8) {
				return piyonAcmaz(tasDeger,3,hedefTasKare,(sahX - hedefTasKare%8)-1,tahta,p);
			}
			return piyonAcmaz(tasDeger,4,hedefTasKare,(hedefTasKare%8-sahX)-1,tahta,p);			
		}		
		return piyonAcmazTestCapraz(hedefTasKare,tasDeger,tahta,p,sahX,sahY);
	}
	public int piyonAcmazTestCapraz(int hedefTasKare, int tasDeger, int[] tahta, PerformansTest p, int sahX, int sahY) {
		if ((sahY*8+sahX) %9 == hedefTasKare%9) {     		
			if (sahY > hedefTasKare/8 && sahX > hedefTasKare%8) {
				return piyonAcmaz(tasDeger,5,hedefTasKare,(sahY*8+sahX-hedefTasKare)/9-1,tahta,p);
			} else if (sahY < hedefTasKare/8 && sahX < hedefTasKare%8) {
				return piyonAcmaz(tasDeger,7,hedefTasKare,(hedefTasKare-(sahY*8+sahX))/9-1,tahta,p);
			}    		
    	}
		else if ((sahY*8+sahX) % 7 == hedefTasKare%7) {
			if (sahY > hedefTasKare/8 && sahX < hedefTasKare%8) {
				return piyonAcmaz(tasDeger,6,hedefTasKare,(sahY*8+sahX-hedefTasKare)/7-1,tahta,p);
			} else if (sahY < hedefTasKare/8 && sahX > hedefTasKare%8) {
				return acmazKontrol(tasDeger,8,hedefTasKare,(hedefTasKare-(sahY*8+sahX))/7-1,tahta,p);
			}
		}		
		return 0;
	}	
	public void acmazHesap(int tasDeger, int hangiYon, int i, int y, int x, int sahTaraf, PerformansTest k) {		
		if (k.getBeyazSahCekis() == 0) {			
			switch(hangiYon) {			
			case 1:			
				acmazHesapCase1(tasDeger,i,y,sahTaraf);
				break;
			case 2:
				acmazHesapCase2(tasDeger,i,y,sahTaraf);
				break;
			case 3:
				acmazHesapCase3(tasDeger,i,x,sahTaraf);
				break;
			case 4:
				acmazHesapCase4(tasDeger,i,x,sahTaraf);
				break;
			case 5:
				acmazHesapCase5(tasDeger,i,y,x,sahTaraf);
				break;
			case 6:
				acmazHesapCase6(tasDeger,i,y,x,sahTaraf);
				break;
			case 7:			
				acmazHesapCase7(tasDeger,i,y,x,sahTaraf);
				break;
			case 8:
				acmazHesapCase8(tasDeger,i,y,x,sahTaraf);
				break;
			}
		}							
	}
	public void acmazHesapCase1(int tasDeger, int i, int y, int sahTaraf) {
		for (int p = i/8-1,j = i%8;p >= y;p--) {				
			hamleEkle(tasDeger,i,j,p);				
		}
		for (int p = 1;p <= sahTaraf;p++) {
			hamleEkle(tasDeger,i,i%8,i/8+p);				
		}
	}
	public void acmazHesapCase2(int tasDeger, int i, int y, int sahTaraf) {
		for (int p = i/8+1,j = i%8;p <= y;p++) {				
			hamleEkle(tasDeger,i,j,p);				
		}
		for (int p = 1;p <= sahTaraf;p++) {
			hamleEkle(tasDeger,i,i%8,i/8-p);				
		}
	}
	public void acmazHesapCase3(int tasDeger, int i, int x, int sahTaraf) {
		for (int p = i/8,j = i%8-1;j >= x;j--) {
			hamleEkle(tasDeger,i,j,p);				
		}
		for (int p = 1;p <= sahTaraf;p++) {
			hamleEkle(tasDeger,i,i%8+p,i/8);				
		}
	}
	public void acmazHesapCase4(int tasDeger, int i, int x, int sahTaraf) {
		for (int p = i/8,j = i%8+1;j <= x;j++) {
			hamleEkle(tasDeger,i,j,p);				
		}			
		for (int p = 1;p <= sahTaraf;p++) {
			hamleEkle(tasDeger,i,i%8-p,i/8);				
		}
	}
	public void acmazHesapCase5(int tasDeger, int i, int y, int x, int sahTaraf) {
		for (int p = i/8-1,j = i%8-1;j >= x && p >= y;p--,j--) {			
			hamleEkle(tasDeger,i,j,p);				
		}			
		for (int p = 1;p <= sahTaraf;p++) {
			hamleEkle(tasDeger,i,i%8+p,i/8+p);				
		}
	}
	public void acmazHesapCase6(int tasDeger, int i, int y, int x, int sahTaraf) {
		for (int p = i/8+1,j = i%8+1;j <= x && p <= y;p++,j++) {			
			hamleEkle(tasDeger,i,j,p);				
		}
		for (int p = 1;p <= sahTaraf;p++) {
			hamleEkle(tasDeger,i,i%8-p,i/8-p);				
		}
	}
	public void acmazHesapCase7(int tasDeger, int i, int y, int x, int sahTaraf) {
		for (int p = i/8-1,j = i%8+1;j <= x && p >= y;p--,j++) {			
			hamleEkle(tasDeger,i,j,p);				
		}
		for (int p = 1;p <= sahTaraf;p++) {
			hamleEkle(tasDeger,i,i%8-p,i/8+p);				
		}
	}
	public void acmazHesapCase8(int tasDeger, int i, int y, int x, int sahTaraf) {
		for (int p = i/8+1,j = i%8-1;j >= x && p <= y;p++,j--) {			
			hamleEkle(tasDeger,i,j,p);				
		}
		for (int p = 1;p <= sahTaraf;p++) {
			hamleEkle(tasDeger,i,i%8+p,i/8-p);				
		}
	}
	public int acmazKontrol(int tasDeger,int hangiYon,int i,int sahTaraf,int[] tahta, PerformansTest k) {		
    	switch(hangiYon) {    	
    		case 1:    			
    			return acmazKontrolCase1(tasDeger,hangiYon,i,sahTaraf,tahta,k);    			    			
    		case 2:      			
    			return acmazKontrolCase2(tasDeger,hangiYon,i,sahTaraf,tahta,k);    			
    		case 3:    			
    			return acmazKontrolCase3(tasDeger,hangiYon,i,sahTaraf,tahta,k);    			
    		case 4:    			
    			return acmazKontrolCase4(tasDeger,hangiYon,i,sahTaraf,tahta,k);    			    			
    		case 5:    		
    			return acmazKontrolCase5(tasDeger,hangiYon,i,sahTaraf,tahta,k);    			    			    			    	
    		case 6:    			    			
    			return acmazKontrolCase6(tasDeger,hangiYon,i,sahTaraf,tahta,k);
    		case 7:    			
    			return acmazKontrolCase7(tasDeger,hangiYon,i,sahTaraf,tahta,k);    			
    		case 8:    			
    			return acmazKontrolCase8(tasDeger,hangiYon,i,sahTaraf,tahta,k);
    	}
    	return 0;
    }
	public int acmazKontrolCase1(int tasDeger,int hangiYon,int i,int sahTaraf,int[] tahta, PerformansTest k) {		
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
					acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,k);
				}    					
				return 1;
			}  				
		}
		return 0;    
	}
	public int acmazKontrolCase2(int tasDeger,int hangiYon,int i,int sahTaraf,int[] tahta, PerformansTest k) {
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
					acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,k);
				}    					
				return 1;
			}
		}
		return 0;
	}
	public int acmazKontrolCase3(int tasDeger,int hangiYon,int i,int sahTaraf,int[] tahta, PerformansTest k) {
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
					acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,k);
				}    					
				return 1;
			} 
		}
		return 0;
	}
	public int acmazKontrolCase4(int tasDeger,int hangiYon,int i,int sahTaraf,int[] tahta, PerformansTest k) {
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
					acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,k);    						
				}    					
				return 1;
			}    						    			
		}
		return 0;
	}
	public int acmazKontrolCase5(int tasDeger,int hangiYon,int i,int sahTaraf,int[] tahta, PerformansTest k) {
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
					acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,k);
				}    					
				return 1;
			}
		} 
		return 0;
	}
	public int acmazKontrolCase6(int tasDeger,int hangiYon,int i,int sahTaraf,int[] tahta, PerformansTest k) {
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
					acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,k);
				}    					
				return 1;
			}
		}
		return 0;
	}
	public int acmazKontrolCase7(int tasDeger,int hangiYon,int i,int sahTaraf,int[] tahta, PerformansTest k) {
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
					acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,k);
				}    					
				return 1;
			} 
		}
		return 0;
	}
	public int acmazKontrolCase8(int tasDeger,int hangiYon,int i,int sahTaraf,int[] tahta, PerformansTest k) {
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
					acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,k);
				}    					
				return 1;    					
			} 
		}
		return 0;
	}
	 public int piyonAcmaz(int tasDeger, int hangiYon, int i, int sahTaraf, int[] tahta, PerformansTest k) {			 
	    	switch (hangiYon) {
	    	case 1:
	    		return piyonAcmazCase1(i,sahTaraf,tahta,k);	    		
	    	case 2:	    	
	    		return piyonAcmazCase2(i,sahTaraf,tahta,k);
	    	case 3:	    		
	    		return piyonAcmazCase3(i,sahTaraf,tahta);	    		
	    	case 4:	  	    			    	
	    		return piyonAcmazCase4(i,sahTaraf,tahta);	    			    	
	    	case 5:	 	    			    		
	    		return piyonAcmazCase5(i,tasDeger,sahTaraf,tahta,k);	    	 
	    	case 6:	 	    			    		
	    		return piyonAcmazCase6(i,tasDeger,sahTaraf,tahta,k);    			    		
	    	case 7:	    		
	    		return piyonAcmazCase7(i,sahTaraf,tahta);	    	 			    		    
	    	}	    	
	    	return 0;
	 }
	 public int piyonAcmazCase1(int i, int sahTaraf,int[] tahta, PerformansTest k) {
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
					 piyonAcmazAlis(i,tahta);
				 }    					
				 return 1;
			 }
		 }
		 return 0;
	 }
	 public int piyonAcmazCase2(int i, int sahTaraf, int[] tahta, PerformansTest k) {
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
 					piyonAcmazAlis(i,tahta);
 				}    					
 				return 1;
 			}
 		}
 		return 0;
	 }
	 public int piyonAcmazCase3(int i, int sahTaraf, int[] tahta) {
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
		 return 0;
	 }
	 public int piyonAcmazCase4(int i, int sahTaraf, int[] tahta) {
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
		 return 0;
	 }
	 public int piyonAcmazCase5(int i, int tasDeger, int sahTaraf, int[] tahta, PerformansTest k) {
		 for (int p = 1;p <= sahTaraf;p++) {    			
 			if (tahta[(i/8+p)*8+i%8+p] != 0) {	    					    			
 				return 0;
 			}
		 }
		 for (int y = i/8-1,x = i%8-1;x > -1 && y > -1;y--,x--) {	    			 			
 			if (tahta[y*8+x] == -9 || tahta[y*8+x] == -4) {
 				if (k.getBeyazSahCekis() == 0 && y == i/8-1 && x == i%8-1 && i/8 > 1) {hamleEkle(tasDeger,i,x,y); return 1;}
 				if (i/8 > 1) {return 1;}
 			} else {
 				if (tahta[y*8+x] != 0.0) {return 0;}
 			}
		 }
		 return 0;
	 }
	 public int piyonAcmazCase6(int i, int tasDeger, int sahTaraf,int[] tahta, PerformansTest k) {
		 for (int p = 1;p <= sahTaraf;p++) {    			
 			if (tahta[(i/8+p)*8+i%8-p] != 0) {	    					    				
 				return 0;
 			}
		 }
		 for (int y = i/8-1,x = i%8+1;x < 8 && y > -1;y--,x++) {	    			 			
 			if (tahta[y*8+x] == -9 || tahta[y*8+x] == -4) {
 				if (k.getBeyazSahCekis() == 0 && y == i/8-1 && x == i%8+1) {hamleEkle(tasDeger,i,x,y); return 1;}
 				if (i/8 > 1) {return 1;}
 			} else {
 				if (tahta[y*8+x] != 0.0) {return 0;}
 			}
		 }
		 return 0;
	 }
	 public int piyonAcmazCase7(int i, int sahTaraf,int[] tahta) {
		 for (int p = 1;p <= sahTaraf;p++) {	    			
 			if (tahta[(i/8-p)*8+i%8-p] != 0) {	    					    				
 				return 0;
 			}	    			
		 }
		 for (int y = i/8+1,x = i%8+1;y < 8 && x < 8;y++,x++) {	    						 	    			
			 if (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) {    					
				 return 1;
			 } else {
				 if (tahta[(y*8+x)] != 0) {return 0;}
			 }
		 }
		 return 0;
	 }
	 public void piyonAcmazAlis(int i, int[] tahta) {
		 if (i/8-1 > 0 && tahta[(i/8-1)*8+i%8] == 0.0) {hamle.add(1*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
		 if (i/8 == 6 && tahta[(i/8-2)*8+i%8] == 0.0 && tahta[(i/8-1)*8+i%8] == 0.0)  {hamle.add(1*1000000+(i/8-2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
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
	 public void sahCekisCapraz(int i, int[] tahta, int hedefKare) {
		 sagUstKose(i,tahta,hedefKare);
		 sagAltKose(i,tahta,hedefKare);
		 solUstKose(i,tahta,hedefKare);
		 solAltKose(i,tahta,hedefKare);
	 }	
	 public void sagUstKose(int i, int[] tahta, int hedefKare) {
		 int l = sahCekisSagUstKoseHesaplanmasiGerekenKareSayisi(i,hedefKare);
		for (int sagUstKose = 1;sagUstKose <= l;sagUstKose++) {
			if (i-sagUstKose*7 == hedefKare) {hamleEkle(tahta[i],i,(i-sagUstKose*7)%8,(i-sagUstKose*7)/8);} else if (tahta[i-sagUstKose*7] != 0) {break;}
		}
	 }
	 public void sagAltKose(int i, int[] tahta, int hedefKare) {
		 int m = sahCekisSagAltKoseHesaplanmasiGerekenKareSayisi(i,hedefKare);
		 for (int sagAltKose = 1;sagAltKose <= m;sagAltKose++) {
			 if (i+sagAltKose*9 == hedefKare) {hamleEkle(tahta[i],i,(i+sagAltKose*9)%8,(i+sagAltKose*9)/8);} else if (tahta[i+sagAltKose*9] != 0) {break;}
		 }
	 }
	 public void solUstKose(int i, int[] tahta, int hedefKare) {
		 int j = sahCekisSolUstKoseHesaplanmasiGerekenKareSayisi(i,hedefKare);
		 for (int solUstKose = 1;solUstKose <= j;solUstKose++) {
			 if (i-solUstKose*9 == hedefKare) {hamleEkle(tahta[i],i,(i-solUstKose*9)%8,(i-solUstKose*9)/8);} else if (tahta[i-solUstKose*9] != 0) {break;}
		 }
	 }
	 public void solAltKose(int i, int[] tahta, int hedefKare) {
		 int k = sahCekisSolAltKoseHesaplanmasiGerekenKareSayisi(i,hedefKare);
		 for (int solAltKose = 1;solAltKose <= k;solAltKose++) {
			 if (i+solAltKose*7 == hedefKare) {hamleEkle(tahta[i],i,(i+solAltKose*7)%8,(i+solAltKose*7)/8);} else if (tahta[i+solAltKose*7] != 0) {break;}
		 }
	 }	 
	 public void sahCekisYatayVeDikey(int i, int[] tahta, int hedefKare) {
		 ustDikey(i,tahta,hedefKare);
		 altDikey(i,tahta,hedefKare);
		 sagYatay(i,tahta,hedefKare);
		 solYatay(i,tahta,hedefKare);
	 }
	 public void ustDikey(int i, int[] tahta, int hedefKare) {
		 int j = solYatayKareSayisi(i,hedefKare);
			for (int ustDikey = 1;ustDikey <= j;ustDikey++) {
				if (i-ustDikey*8 == hedefKare) {hamleEkle(tahta[i],i,i%8,(i-ustDikey*8)/8);} else if (tahta[i-ustDikey*8] != 0) {break;}
			}
	 }
	 public void altDikey(int i, int[] tahta, int hedefKare) {
		 int m = sagYatayKareSayisi(i,hedefKare);
		 for (int altDikey = 1;altDikey <= m;altDikey++) {
			 if (i+altDikey*8 == hedefKare) {hamleEkle(tahta[i],i,i%8,(i+altDikey*8)/8);} else if (tahta[i+altDikey*8] != 0) {break;}
		 }
	 }
	 public void sagYatay(int i, int[] tahta, int hedefKare) {
		 int k = ustDikeyKareSayisi(i,hedefKare);
		 for (int sagYatay = 1;sagYatay <= k;sagYatay++) {
			 if (i+sagYatay == hedefKare) {hamleEkle(tahta[i],i,i%8+sagYatay,i/8);} else if (tahta[i+sagYatay] != 0) {break;}
		 }
	 }
	 public void solYatay(int i, int[] tahta, int hedefKare) {
		 int l = altDikeyKareSayisi(i,hedefKare);
		 for (int solYatay = 1;solYatay <= l;solYatay++) {
			 if (i-solYatay == hedefKare) {hamleEkle(tahta[i],i,i%8-solYatay,i/8);} else if (tahta[i-solYatay] != 0) {break;}
		 }
	 }
	 
	 public void sahCekenTasNerede(int tasKonum, int tasDeger, PerformansTest p) {
		 sahMudaheleKare.clear();		 
		 sahCekenTasNeredeYatayVeDikey(tasKonum,p);		 
		 sahCekenTasNeredeCapraz(tasKonum,p,(p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10));
		 if (tasDeger == -3) {sahMudaheleKare.add(tasKonum);}	    	
		 p.sahMudaheleKareAyarla(sahMudaheleKare);	    	    
	 }
	 public void sahCekenTasNeredeYatayVeDikey(int i, PerformansTest p) {
		 int tasX = i%8, tasY = i/8, sahX = p.getBeyazSahKonum()%10, sahY = p.getBeyazSahKonum()/10%10;
		 if (sahX == tasX && sahY > tasY) {
			 sahY -= 1;
			 for (;sahY >= tasY;sahY--) {
				 sahMudaheleKare.add(sahY*8+sahX);
			 }    		
		 }
		 else if (sahX == tasX && sahY < tasY) {   		
			 sahY += 1;
			 for (;sahY <= tasY;sahY++) {    			
				 sahMudaheleKare.add(sahY*8+sahX);
			 }
		 }
		 else if (sahY == tasY && sahX > tasX) {
			 sahX -= 1;	    		
			 for (;sahX >= tasX;sahX--) {	    			
				 sahMudaheleKare.add(sahY*8+sahX);
			 }    		
		 }
		 else if (sahY == tasY && sahX < tasX) {    		
			 sahX += 1;
			 for (;sahX <= tasX;sahX++) {	    			
				 sahMudaheleKare.add(sahY*8+sahX);
			 }
		 }  
	 }
	 public void sahCekenTasNeredeCapraz(int i, PerformansTest p, int sahKonum) {
		 int tasX = i%8, tasY = i/8, sahX = p.getBeyazSahKonum()%10, sahY = p.getBeyazSahKonum()/10%10;
		 if (sahKonum %9 == i%9 && sahY > tasY) {			 
			 sahX -= 1;sahY -= 1;
			 for (;sahX >= tasX && sahY >= tasY;sahX--,sahY--) {    			
				 sahMudaheleKare.add(sahY*8+sahX);
			 }    		
		 }    	
		 else if (sahKonum % 9 == i%9 && sahY < tasY) {
			 sahX += 1;sahY += 1;
			 for (;sahX <= tasX && sahY <= tasY;sahX++,sahY++) {    			
				 sahMudaheleKare.add(sahY*8+sahX);	    		
			 }
		 }    	
		 else if (sahKonum % 7 == i%7 && sahY > tasY) {
			 sahX += 1;sahY -= 1;    		
			 for (;sahX <= tasX && sahY >= tasY;sahX++,sahY--) {    			
				 sahMudaheleKare.add(sahY*8+sahX);
			 }
		 }    	
		 else if (sahKonum % 7 == i%7 && sahY < tasY) {
			 sahX -= 1;sahY += 1;
			 for (;sahX >= tasX && sahY <= tasY;sahX--,sahY++) {    			
				 sahMudaheleKare.add(sahY*8+sahX);
			 }
		 }
	 }
}