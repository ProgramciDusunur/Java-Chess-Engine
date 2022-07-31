package motor;

import java.util.ArrayList;

import oyun.Oyun;

public class Tahta extends Oyun {
	
	ArrayList<Integer> alinanTaslar = new ArrayList<Integer>();	
	Hamleler hamle = new Hamleler();
	public void hamleYap(int i, boolean geriCevir, boolean siraKimde, double[] tahta, PerformansTest p) {		
		if (geriCevir) {			
			if (siraKimde) {
				 if (i == 200000) {					
					tahta[60] = 900.0;
					tahta[63] = 5.0;
					tahta[62] = 0.0;
					tahta[61] = 0.0;
					p.setBeyazSahKisaRok(true);
				} else if (i == 600000) {
					tahta[58] = 0.0;
					tahta[59] = 0.0;
					tahta[60] = 900.0;
					tahta[56] = 5.0;
					p.setBeyazSahUzunRok(true);
				} else if (i/1000000 == 9 || i/1000000 == 4 || i/1000000 == 5 || i/1000000 == 3) {
					tahta[((i/10000)%10)*8+(i/1000)%10] = 0.0;
					tahta[((i/100)%10)*8+(i/10)%10] = 1.0;
				} else if (i/100000000 == 9 || i/10000000 == 4 || i/10000000 == 5 || i/10000000 == 3) {
					tahta[((i/10000)%10)*8+(i/1000)%10] = alinanTaslar.get(alinanTaslar.size()-1);
					alinanTaslar.remove(alinanTaslar.size()-1);
					tahta[((i/100)%10)*8+(i/10)%10] = 1.0;
				} else if (i/1000000 == 1) {
					tahta[((i/10000)%10)*8+(i/1000)%10] = 0.0;
					tahta[((i/100)%10)*8+(i/10)%10] = 1.0;					
					p.setGecerkenAlma(-1);
				}
				else {
					if (i/100000 == 900) {												
						p.setBeyazSahKonum((i/100)%10*10+(i/10)%10);
					}
					tahta[((i/10000)%10)*8+(i/1000)%10] = alinanTaslar.get(alinanTaslar.size()-1);
					alinanTaslar.remove(alinanTaslar.size()-1);
					tahta[((i/100)%10)*8+(i/10)%10] = i/100000;					
				}
				
			} else {
				if (i == 200000) {
					tahta[4] = -900.0;
					tahta[7] = -5.0;
					tahta[6] = 0.0;
					tahta[5] = 0.0;
					p.setSiyahSahKisaRok(true);
				} else if (i == 600000) {
					tahta[4] = -900.0;
					tahta[0] = -5.0;
					tahta[2] = 0.0;
					tahta[1] = 0.0;
					p.setSiyahSahUzunRok(true);
				} else if (i/1000000 == 9 || i/1000000 == 4 || i/1000000 == 5 || i/1000000 == 3) {
					tahta[((i/10000)%10)*8+(i/1000)%10] = 0.0;
					tahta[((i/100)%10)*8+(i/10)%10] = -1.0;
				} else if (i/100000000 == 9 || i/10000000 == 4 || i/10000000 == 5 || i/10000000 == 3) {
					tahta[((i/10000)%10)*8+(i/1000)%10] = alinanTaslar.get(alinanTaslar.size()-1);
					alinanTaslar.remove(alinanTaslar.size()-1);
					tahta[((i/100)%10)*8+(i/10)%10] = -1.0;
				} else if (i/1000000 == 1) {
					tahta[((i/10000)%10)*8+(i/1000)%10] = 0.0;
					tahta[((i/100)%10)*8+(i/10)%10] = -1.0;					
					p.setGecerkenAlma(-1);
				}
				else {
					if (i/100000 == 900) {												
						p.setSiyahSahKonum((i/100)%10*10+(i/10)%10);
					}					
					tahta[((i/10000)%10)*8+(i/1000)%10] = alinanTaslar.get(alinanTaslar.size()-1);
					alinanTaslar.remove(alinanTaslar.size()-1);
					tahta[((i/100)%10)*8+(i/10)%10] = (i/100000)*-1;
				}			
			}					
		} else {			
			alinanTaslar.add((int) tahta[((i/10000)%10)*8+(i/1000)%10]);			
			if (siraKimde) {
				if (i == 200000) {
					tahta[60] = 0.0;
					tahta[63] = 0.0;
					tahta[62] = 900.0;
					tahta[61] = 5.0;
					p.setBeyazSahKisaRok(false);
				} else if (i == 600000) {
					tahta[58] = 900.0;
					tahta[59] = 5.0;
					tahta[60] = 0.0;
					tahta[56] = 0.0;
					p.setBeyazSahUzunRok(false);
				} else if (i/1000000 == 9 || i/1000000 == 4 || i/1000000 == 5 || i/1000000 == 3) {
					tahta[((i/10000)%10)*8+(i/1000)%10] = i/1000000;
					tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
				} else if (i/100000000 == 9 || i/10000000 == 4 || i/10000000 == 5 || i/10000000 == 3) {
					if (i/100000000 == 9) {
						tahta[((i/10000)%10)*8+(i/1000)%10] = i/100000000;
					} else {
						tahta[((i/10000)%10)*8+(i/1000)%10] = i/10000000;
					}
					tahta[((i/100)%10)*8+(i/10)%10] = 0.0;					
				} else if (i/1000000 == 1) {					
					tahta[((i/10000)%10)*8+(i/1000)%10] = i/1000000;
					tahta[((i/100)%10)*8+(i/10)%10] = 0.0;					
					p.setGecerkenAlma((i/1000)%10);
					
				}
				else {
					if (i/100000 == 900) {												
						p.setBeyazSahKonum((i/10000)%10*10+(i/1000)%10);
					}
					tahta[((i/10000)%10)*8+(i/1000)%10] = i/100000;
					tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
				}				
			} else {				
				if (i == 200000) {
					tahta[4] = 0.0;
					tahta[7] = 0.0;
					tahta[6] = -900.0;
					tahta[5] = -5.0;
					p.setSiyahSahKisaRok(false);
				} else if (i == 600000)  {
					tahta[4] = 0.0;
					tahta[0] = 0.0;
					tahta[2] = -900.0;
					tahta[1] = -5.0;
					p.setSiyahSahUzunRok(false);
				} else if (i/1000000 == 9 || i/1000000 == 4 || i/1000000 == 5 || i/1000000 == 3) {
					tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
					tahta[((i/10000)%10)*8+(i/1000)%10] = (i/1000000)*-1;
				} else if (i/100000000 == 9 || i/10000000 == 4 || i/10000000 == 5 || i/10000000 == 3) {
					tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
					if (i/100000000 == 9) {
						tahta[((i/10000)%10)*8+(i/1000)%10] = (i/100000000)*-1;
					} else {
						tahta[((i/10000)%10)*8+(i/1000)%10] = (i/10000000)*-1;
					}
					tahta[((i/100)%10)*8+(i/10)%10] = 0.0;					
				} else if (i/1000000 == 1) {
					tahta[((i/10000)%10)*8+(i/1000)%10] = (i/1000000)*-1;
					tahta[((i/100)%10)*8+(i/10)%10] = 0.0;	
					p.setGecerkenAlma((i/1000)%10);
				}
				else {
					if (i/100000 == -900) {
						p.setSiyahSahKonum((i/10000)%10*10+(i/1000)%10);
					}
					tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
					tahta[((i/10000)%10)*8+(i/1000)%10] = (i/100000)*-1;
				}				
			}			
		}
	}	
}