package motor;

import java.util.ArrayList;

import oyun.Oyun;

public class Tahta extends Oyun {
	
	ArrayList<Integer> alinanTaslar = new ArrayList<Integer>();	
	public void hamleYap(int i, boolean geriCevir, boolean siraKimde, double[] tahta, PerformansTest p) {		
		if (geriCevir) {			
			if (siraKimde) {
				if (i/100000 == 7) {
					//sahKonumAyarla(10000+(i/10000%10)*1000+(i/1000%10)*100+(sahKonumAl()/10%10)*10+sahKonumAl()%10);
					tahta[((i/10000)%10)*8+(i/1000)%10] = alinanTaslar.get(alinanTaslar.size()-1);
					alinanTaslar.remove(alinanTaslar.size()-1);
					tahta[((i/100)%10)*8+(i/10)%10] = 900;
				} else if (i == 200000) {					
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
				}
				else {
					tahta[((i/10000)%10)*8+(i/1000)%10] = alinanTaslar.get(alinanTaslar.size()-1);
					alinanTaslar.remove(alinanTaslar.size()-1);
					tahta[((i/100)%10)*8+(i/10)%10] = i/100000;
				}
				
			} else {
				if (i/100000 == 7) {
					//sahKonumAyarla(10000+(sahKonumAl()/1000%10)*1000+(sahKonumAl()/100%10)*100+((i/10000)%10)*10+(i/1000)%10);
					tahta[((i/10000)%10)*8+(i/1000)%10] = alinanTaslar.get(alinanTaslar.size()-1);
					alinanTaslar.remove(alinanTaslar.size()-1);
					tahta[((i/100)%10)*8+(i/10)%10] = -900;
				} else {
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
				}
				else {
					tahta[((i/10000)%10)*8+(i/1000)%10] = i/100000;
					tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
				}
				
			} else {				
				tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
				tahta[((i/10000)%10)*8+(i/1000)%10] = (i/100000)*-1;
			}						
		}
	}	
}