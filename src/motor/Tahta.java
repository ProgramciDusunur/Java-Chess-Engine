package motor;

import java.util.HashMap;

import oyun.Oyun;

public class Tahta extends Oyun {
	boolean piyonTerfi = false;	
	HashMap<Integer, Integer> alinanTaslar = new HashMap<>();
	HashMap<Integer, Integer> kisaRokUzunRok = new HashMap<>();
	HashMap<Integer, Integer> kisaRok = new HashMap<>();
	HashMap<Integer, Integer> uzunRok = new HashMap<>();
	HashMap<Integer, Integer> kaleKisaRok = new HashMap<>();
	HashMap<Integer, Integer> kaleUzunRok = new HashMap<>();
	Hamleler hamle = new Hamleler();	
	public void hamleYap(int i, boolean geriCevir, boolean siraKimde, int[] tahta, PerformansTest p, int pozisyonSira) {		
		switch(i/1000000) {
		case 2:
			if (geriCevir) {
				rokGeriAl(i,siraKimde,tahta,p,pozisyonSira);
			} else {
				rok(i,siraKimde,tahta,p,pozisyonSira);
			}
			break;
		case 6:			
			if (geriCevir) {
				rokGeriAl(i,siraKimde,tahta,p,pozisyonSira);
			} else {				
				rok(i,siraKimde,tahta,p,pozisyonSira);
			}
			break;
		case 900:
			if (geriCevir) {
				piyonTerfiGeriAl(i,siraKimde,tahta,pozisyonSira);
			} else {
				piyonTerfi(i,siraKimde,tahta,p,pozisyonSira);
			}
			
			break;
		case 40:
			if (geriCevir) {
				piyonTerfiGeriAl(i,siraKimde,tahta,pozisyonSira);
			} else {
				piyonTerfi(i,siraKimde,tahta,p,pozisyonSira);
			}
			break;
		case 50:
			if (geriCevir) {
				piyonTerfiGeriAl(i,siraKimde,tahta,pozisyonSira);
			} else {
				piyonTerfi(i,siraKimde,tahta,p,pozisyonSira);
			}
			break;
		case 30:
			if (geriCevir) {
				piyonTerfiGeriAl(i,siraKimde,tahta,pozisyonSira);
			} else {
				piyonTerfi(i,siraKimde,tahta,p,pozisyonSira);
			}
			break;
		case 1:
			if (geriCevir) {
				piyonCiftGidisGeriAl(i,siraKimde,tahta,p);
				
			} else {
				piyonCiftGidis(i,siraKimde,tahta,p);
			}			
			break;
		case 10:
			if (geriCevir) {
				gecerkenAlmaGeriAl(i,siraKimde,tahta);
			} else {
				gecerkenAlma(i,siraKimde,tahta,p);
			}
			
			break;
		default:				
			if (geriCevir) {
				siradanGidislerGeriAl(i,siraKimde,tahta,p,pozisyonSira);
			} else {
				siradanGidisler(i,siraKimde,tahta,p,pozisyonSira);
			}
			break;
		}		
	}
	public void gecerkenAlma(int i, boolean siraKimde, int[] tahta, PerformansTest p) {
		p.setGecerkenAlma(-1);
		tahta[((i/100)%10)*8+(i/10)%10] = 0;
		if (siraKimde) {						
			tahta[((i/10000)%10+1)*8+(i/1000)%10] = 0;
			tahta[((i/10000)%10)*8+(i/1000)%10] = 1;
		} else {
			tahta[((i/10000)%10-1)*8+(i/1000)%10] = 0;
			tahta[((i/10000)%10)*8+(i/1000)%10] = -1;
		}
	}
	public void gecerkenAlmaGeriAl(int i, boolean siraKimde, int[] tahta) {		
		tahta[((i/10000)%10)*8+(i/1000)%10] = 0;		
		if (siraKimde) {
			tahta[((i/10000)%10+1)*8+(i/1000)%10] = -1;
			tahta[((i/100)%10)*8+(i/10)%10] = 1;
		} else {
			tahta[((i/10000)%10-1)*8+(i/1000)%10] = 1;
			tahta[((i/100)%10)*8+(i/10)%10] = -1;
		}
	}
	public void rok(int i, boolean siraKimde, int[] tahta, PerformansTest p, int pozisyonSira) {
		p.setGecerkenAlma(-1);
		switch(i) {
		case 2000000:
			if (siraKimde) {
				tahta[60] = 0;
				tahta[63] = 0;
				tahta[62] = 900;
				tahta[61] = 5;
				p.setBeyazSahKonum(7*10+6);
				p.setBeyazSahKisaRok(false);
				if (p.isBeyazSahUzunRok()) {
					p.setBeyazSahUzunRok(false);
					kisaRokUzunRok.put(pozisyonSira, i);
				}
				
				
			} else {
				tahta[4] = 0;
				tahta[7] = 0;
				tahta[6] = -900;
				tahta[5] = -5;				
				p.setSiyahSahKonum(0*10+6);
				p.setSiyahSahKisaRok(false);
				if (p.isSiyahSahUzunRok()) {
					p.setSiyahSahUzunRok(false);
					kisaRokUzunRok.put(pozisyonSira, i);
				}				
			}
			break;
		case 6000000:
			if (siraKimde) {
				tahta[58] = 900;
				tahta[59] = 5;
				tahta[60] = 0;
				tahta[56] = 0;
				p.setBeyazSahKonum(7*10+2);
				p.setBeyazSahUzunRok(false);				
				if (p.isBeyazSahKisaRok()) {
					p.setBeyazSahKisaRok(false);
					kisaRokUzunRok.put(pozisyonSira, i);
				}
				
			} else {				
				tahta[4] = 0;
				tahta[0] = 0;
				tahta[2] = -900;
				tahta[3] = -5;
				p.setSiyahSahKonum(0*10+2);
				p.setSiyahSahUzunRok(false);
				if (p.isSiyahSahKisaRok()) {
					p.setSiyahSahKisaRok(false);
					kisaRokUzunRok.put(pozisyonSira, i);
				}
				
			}
		break;		
		}
	}
	public void rokGeriAl(int i, boolean siraKimde, int[] tahta, PerformansTest p, int pozisyonSira) {		
		switch(i) {
		case 2000000:
			if (siraKimde) {
				tahta[60] = 900;
				tahta[63] = 5;
				tahta[62] = 0;
				tahta[61] = 0;
				p.setBeyazSahKonum(7*10+4);
				p.setBeyazSahKisaRok(true);
				if (kisaRokUzunRok.containsKey(pozisyonSira) && kisaRokUzunRok.containsValue(i)) {
					p.setBeyazSahUzunRok(true);
					kisaRokUzunRok.remove(pozisyonSira,i);
				}
			} else {
				tahta[4] = -900;
				tahta[7] = -5;
				tahta[6] = 0;
				tahta[5] = 0;
				p.setSiyahSahKonum(0*10+4);
				p.setSiyahSahKisaRok(true);
				if (kisaRokUzunRok.containsKey(pozisyonSira) && kisaRokUzunRok.containsValue(i)) {
					p.setSiyahSahUzunRok(true);
					kisaRokUzunRok.remove(pozisyonSira,i);
				}
			}
			break;
		case 6000000:
			if (siraKimde) {
				tahta[58] = 0;
				tahta[59] = 0;
				tahta[60] = 900;
				tahta[56] = 5;
				p.setBeyazSahKonum(7*10+4);
				p.setBeyazSahUzunRok(true);
				if (kisaRokUzunRok.containsKey(pozisyonSira) && kisaRokUzunRok.containsValue(i)) {
					p.setBeyazSahKisaRok(true);
					kisaRokUzunRok.remove(pozisyonSira,i);
				}				
			} else {
				tahta[4] = -900;
				tahta[0] = -5;
				tahta[2] = 0;
				tahta[3] = 0;
				p.setSiyahSahKonum(0*10+4);
				p.setSiyahSahUzunRok(true);
				if (kisaRokUzunRok.containsKey(pozisyonSira) && kisaRokUzunRok.containsValue(i)) {
					p.setSiyahSahKisaRok(true);
					kisaRokUzunRok.remove(pozisyonSira,i);
				}
			}
		break;		
		}
	}
	public void piyonTerfi(int i, boolean siraKimde, int[] tahta, PerformansTest p, int pozisyonSira) {
		p.setGecerkenAlma(-1);
		switch(i/1000000) {
		case 900:						
			alinanTaslar.put(pozisyonSira, tahta[((i/10000)%10)*8+(i/1000)%10]);
			if (siraKimde) {
				tahta[((i/10000)%10)*8+(i/1000)%10] = 9;				
			} else {
				tahta[((i/10000)%10)*8+(i/1000)%10] = -9;
			}
			break;
		case 40:
			if (siraKimde) {
				tahta[((i/10000)%10)*8+(i/1000)%10] = 4;
			} else {
				tahta[((i/10000)%10)*8+(i/1000)%10] = -4;
			}
			break;
		case 50:
			if (siraKimde) {
				tahta[((i/10000)%10)*8+(i/1000)%10] = 5;
			} else {
				tahta[((i/10000)%10)*8+(i/1000)%10] = -5;
			}
			break;
		case 30:
			if (siraKimde) {
				tahta[((i/10000)%10)*8+(i/1000)%10] = 3;
			} else {
				tahta[((i/10000)%10)*8+(i/1000)%10] = -3;
			}
			break;
		}
		tahta[((i/100)%10)*8+(i/10)%10] = 0;
	}
	public void piyonTerfiGeriAl(int i, boolean siraKimde, int[] tahta, int pozisyonSira) {		
		tahta[((i/10000)%10)*8+(i/1000)%10] = alinanTaslar.get(pozisyonSira);
		if (i/10000000 == 5) {
			alinanTaslar.remove(pozisyonSira);
		}
		if (siraKimde) {
			tahta[((i/100)%10)*8+(i/10)%10] = 1;
		} else {
			tahta[((i/100)%10)*8+(i/10)%10] = -1;
		}		
	}
	public void piyonCiftGidis(int i, boolean siraKimde, int[] tahta, PerformansTest p) {
		p.setGecerkenAlma((i/1000)%10);
		if (siraKimde) {
			tahta[((i/10000)%10)*8+(i/1000)%10] = 1;
		} else {
			tahta[((i/10000)%10)*8+(i/1000)%10] = -1;
		}
		tahta[((i/100)%10)*8+(i/10)%10] = 0;
	}
	public void piyonCiftGidisGeriAl(int i, boolean siraKimde, int[] tahta, PerformansTest p) {
		p.setGecerkenAlma(-1);		
		if (siraKimde) {
			tahta[((i/100)%10)*8+(i/10)%10] = 1;
		} else {
			tahta[((i/100)%10)*8+(i/10)%10] = -1;
		}
		tahta[((i/10000)%10)*8+(i/1000)%10] = 0;
	}
	public void siradanGidisler(int i, boolean siraKimde, int[] tahta, PerformansTest p, int pozisyonSira) {
		p.setGecerkenAlma(-1);
		alinanTaslar.put(pozisyonSira, tahta[((i/10000)%10)*8+(i/1000)%10]);
		if (siraKimde) {
			if (i/100000 == 900) {												
				p.setBeyazSahKonum((i/10000)%10*10+(i/1000)%10);
				if (p.isBeyazSahKisaRok()) {
					kisaRok.put(pozisyonSira, i);
					p.setBeyazSahKisaRok(false);
				}
				if (p.isBeyazSahUzunRok()) {
					uzunRok.put(pozisyonSira, i);
					p.setBeyazSahUzunRok(false);
				}				
			} 			
			tahta[((i/10000)%10)*8+(i/1000)%10] = i/100000;
			tahta[((i/100)%10)*8+(i/10)%10] = 0;
			if (i/100000 == 5 && ((i/100)%10)*8+(i/10)%10 == 63 && p.isBeyazSahKisaRok()) {
				p.setBeyazSahKisaRok(false);
				kaleKisaRok.put(pozisyonSira, i);
			}
			if (i/100000 == 5 && ((i/100)%10)*8+(i/10)%10 == 56 && p.isBeyazSahUzunRok()) {
				p.setBeyazSahUzunRok(false);
				kaleUzunRok.put(pozisyonSira, i);
			}
		}
		else {
			if (i/100000 == 900) {				
				p.setSiyahSahKonum((i/10000)%10*10+(i/1000)%10);
				if (p.isSiyahSahKisaRok()) {
					kisaRok.put(pozisyonSira, i);
					p.setSiyahSahKisaRok(false);
				}
				if (p.isSiyahSahUzunRok()) {
					uzunRok.put(pozisyonSira, i);
					p.setSiyahSahUzunRok(false);
				}
			}
			if (i/100000 == 5 && ((i/100)%10)*8+(i/10)%10 == 7 && p.isSiyahSahKisaRok()) {
				p.setSiyahSahKisaRok(false);
				kaleKisaRok.put(pozisyonSira, i);
			}
			if (i/100000 == 5 && ((i/100)%10)*8+(i/10)%10 == 0 && p.isSiyahSahUzunRok()) {							
				p.setSiyahSahUzunRok(false);
				kaleUzunRok.put(pozisyonSira, i);
			}
			tahta[((i/100)%10)*8+(i/10)%10] = 0;
			tahta[((i/10000)%10)*8+(i/1000)%10] = (i/100000)*-1;
		}
	}
	public void siradanGidislerGeriAl(int i, boolean siraKimde, int[] tahta, PerformansTest p, int pozisyonSira) {		
		tahta[((i/10000)%10)*8+(i/1000)%10] = alinanTaslar.get(pozisyonSira);
		alinanTaslar.remove(pozisyonSira);
		if (siraKimde) {
			if (i/100000 == 900) {												
				p.setBeyazSahKonum((i/100)%10*10+(i/10)%10);
				if (!p.isBeyazSahKisaRok() && kisaRok.containsKey(pozisyonSira) && kisaRok.containsValue(i)) {
					p.setBeyazSahKisaRok(true);
					kisaRok.remove(pozisyonSira);
				}
				if (!p.isBeyazSahUzunRok() && uzunRok.containsKey(pozisyonSira) && uzunRok.containsValue(i)) {
					p.setBeyazSahUzunRok(true);
					uzunRok.remove(pozisyonSira);
				}
				
			}
			if (i/100000 == 5 && ((i/100)%10)*8+(i/10)%10 == 63 && kaleKisaRok.containsValue(i) && kaleKisaRok.containsKey(pozisyonSira)) {
				p.setBeyazSahKisaRok(true);
				kaleKisaRok.remove(pozisyonSira, i);
			}
			if (i/100000 == 5 && ((i/100)%10)*8+(i/10)%10 == 56 && kaleUzunRok.containsValue(i) && kaleUzunRok.containsKey(pozisyonSira)) {
				p.setBeyazSahUzunRok(true);
				kaleUzunRok.remove(pozisyonSira, i);
			}
			tahta[((i/100)%10)*8+(i/10)%10] = i/100000;
		} else {
			if (i/100000 == 900) {												
				p.setSiyahSahKonum((i/100)%10*10+(i/10)%10);
				if (!p.isSiyahSahKisaRok() && kisaRok.containsKey(pozisyonSira) && kisaRok.containsValue(i)) {
					p.setSiyahSahKisaRok(true);	
					kisaRok.remove(pozisyonSira);
				}
				if (!p.isSiyahSahUzunRok() && uzunRok.containsKey(pozisyonSira) && uzunRok.containsValue(i)) {
					p.setSiyahSahUzunRok(true);
					uzunRok.remove(pozisyonSira);
				}				
			}	
			if (i/100000 == 5 && ((i/100)%10)*8+(i/10)%10 == 7 && kaleKisaRok.containsValue(i) && kaleKisaRok.containsKey(pozisyonSira)) {
				p.setSiyahSahKisaRok(true);
				kaleKisaRok.remove(pozisyonSira, i);
			}
			if (i/100000 == 5 && ((i/100)%10)*8+(i/10)%10 == 0 && kaleUzunRok.containsValue(i) && kaleUzunRok.containsKey(pozisyonSira)) {
				p.setSiyahSahUzunRok(true);
				kaleUzunRok.remove(pozisyonSira, i);
			}
			tahta[((i/100)%10)*8+(i/10)%10] = (i/100000)*-1;
		}
	}
}