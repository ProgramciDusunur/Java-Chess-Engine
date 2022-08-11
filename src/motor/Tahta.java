package motor;

import java.util.ArrayList;

import oyun.Oyun;

public class Tahta extends Oyun {
	boolean piyonTerfi = false;
	ArrayList<Double> alinanTaslar = new ArrayList<Double>();	
	Hamleler hamle = new Hamleler();
	
	public void hamleYap(int i, boolean geriCevir, boolean siraKimde, double[] tahta, PerformansTest p) {		
		switch(i/1000000) {
		case 2:
			if (geriCevir) {
				rokGeriAl(i,siraKimde,tahta,p);
			} else {
				rok(i,siraKimde,tahta,p);
			}
			break;
		case 6:
			if (geriCevir) {
				rokGeriAl(i,siraKimde,tahta,p);
			} else {
				rok(i,siraKimde,tahta,p);
			}
			break;
		case 900:
			if (geriCevir) {
				piyonTerfiGeriAl(i,siraKimde,tahta,p);
			} else {
				piyonTerfi(i,siraKimde,tahta,p);
			}
			
			break;
		case 40:
			if (geriCevir) {
				piyonTerfiGeriAl(i,siraKimde,tahta,p);
			} else {
				piyonTerfi(i,siraKimde,tahta,p);
			}
			break;
		case 50:
			if (geriCevir) {
				piyonTerfiGeriAl(i,siraKimde,tahta,p);
			} else {
				piyonTerfi(i,siraKimde,tahta,p);
			}
			break;
		case 30:
			if (geriCevir) {
				piyonTerfiGeriAl(i,siraKimde,tahta,p);
			} else {
				piyonTerfi(i,siraKimde,tahta,p);
			}
			break;
		case 1:
			if (geriCevir) {
				piyonTerfiCiftGidisGeriAl(i,siraKimde,tahta,p);
				
			} else {
				piyonTerfiCiftGidis(i,siraKimde,tahta,p);
			}			
			break;
		case 10:
			if (geriCevir) {
				gecerkenAlmaGeriAl(i,siraKimde,tahta,p);
			} else {
				gecerkenAlma(i,siraKimde,tahta,p);
			}
			
			break;
		default:				
			if (geriCevir) {
				siradanGidislerGeriAl(i,siraKimde,tahta,p);
			} else {
				siradanGidisler(i,siraKimde,tahta,p);
			}
			break;
		}
		
	}
	public void gecerkenAlma(int i, boolean siraKimde, double[] tahta, PerformansTest p) {
		if (siraKimde) {
			tahta[((i/10000)%10+1)*8+(i/1000)%10] = 0.0;
			tahta[((i/10000)%10)*8+(i/1000)%10] = 1.0;
		} else {
			tahta[((i/10000)%10-1)*8+(i/1000)%10] = 0.0;
			tahta[((i/10000)%10)*8+(i/1000)%10] = -1.0;
		}
	}
	public void gecerkenAlmaGeriAl(int i, boolean siraKimde, double[] tahta, PerformansTest p) {
		tahta[((i/10000)%10)*8+(i/1000)%10] = 0.0;
		if (siraKimde) {
			tahta[((i/10000)%10+1)*8+(i/1000)%10] = -1.0;			
		} else {
			tahta[((i/10000)%10-1)*8+(i/1000)%10] = 1.0;			
		}
	}
	public void rok(int i, boolean siraKimde, double[] tahta, PerformansTest p) {
		switch(i) {
		case 200000:
			if (siraKimde) {
				tahta[60] = 0.0;
				tahta[63] = 0.0;
				tahta[62] = 900.0;
				tahta[61] = 5.0;
				p.setBeyazSahKisaRok(false);
			} else {
				tahta[4] = 0.0;
				tahta[7] = 0.0;
				tahta[6] = -900.0;
				tahta[5] = -5.0;
				p.setSiyahSahKisaRok(false);
			}
			break;
		case 600000:
			if (siraKimde) {
				tahta[58] = 900.0;
				tahta[59] = 5.0;
				tahta[60] = 0.0;
				tahta[56] = 0.0;
				p.setBeyazSahUzunRok(false);
			} else {
				tahta[4] = 0.0;
				tahta[0] = 0.0;
				tahta[2] = -900.0;
				tahta[1] = -5.0;
				p.setSiyahSahUzunRok(false);
			}
		break;
		
		}
	}
	public void rokGeriAl(int i, boolean siraKimde, double[] tahta, PerformansTest p) {
		switch(i) {
		case 200000:
			if (siraKimde) {
				tahta[60] = 900.0;
				tahta[63] = 5.0;
				tahta[62] = 0.0;
				tahta[61] = 0.0;
				p.setBeyazSahKisaRok(true);
			} else {
				tahta[4] = -900.0;
				tahta[7] = -5.0;
				tahta[6] = 0.0;
				tahta[5] = 0.0;
				p.setSiyahSahKisaRok(true);
			}
			break;
		case 600000:
			if (siraKimde) {
				tahta[58] = 0.0;
				tahta[59] = 0.0;
				tahta[60] = 900.0;
				tahta[56] = 5.0;
				p.setBeyazSahUzunRok(true);
			} else {
				tahta[4] = -900.0;
				tahta[0] = 1.0;
				tahta[2] = 0.0;
				tahta[1] = 0.0;
				p.setSiyahSahUzunRok(true);
			}
		break;		
		}
	}
	public void piyonTerfi(int i, boolean siraKimde, double[] tahta, PerformansTest p) {
		switch(i/1000000) {
		case 900:			
			alinanTaslar.add(tahta[((i/10000)%10)*8+(i/1000)%10]);
			if (siraKimde) {
				tahta[((i/10000)%10)*8+(i/1000)%10] = 9.0;				
			} else {
				tahta[((i/10000)%10)*8+(i/1000)%10] = -9.0;
			}
			break;
		case 40:
			if (siraKimde) {
				tahta[((i/10000)%10)*8+(i/1000)%10] = 4.0;
			} else {
				tahta[((i/10000)%10)*8+(i/1000)%10] = -4.0;
			}
			break;
		case 50:
			if (siraKimde) {
				tahta[((i/10000)%10)*8+(i/1000)%10] = 5.0;
			} else {
				tahta[((i/10000)%10)*8+(i/1000)%10] = -5.0;
			}
			break;
		case 30:
			if (siraKimde) {
				tahta[((i/10000)%10)*8+(i/1000)%10] = 3.0;
			} else {
				tahta[((i/10000)%10)*8+(i/1000)%10] = -3.0;
			}
			break;
		}
		tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
	}
	public void piyonTerfiGeriAl(int i, boolean siraKimde, double[] tahta, PerformansTest p) {
		tahta[((i/10000)%10)*8+(i/1000)%10] = alinanTaslar.get(alinanTaslar.size()-1);
		if (i/10000000 == 5) {
			alinanTaslar.remove(alinanTaslar.size()-1);
		}
		if (siraKimde) {
			tahta[((i/100)%10)*8+(i/10)%10] = 1.0;
		} else {
			tahta[((i/100)%10)*8+(i/10)%10] = -1.0;
		}		
	}
	public void piyonTerfiCiftGidis(int i, boolean siraKimde, double[] tahta, PerformansTest p) {
		p.setGecerkenAlma((i/1000)%10);
		if (siraKimde) {
			tahta[((i/10000)%10)*8+(i/1000)%10] = 1.0;
		} else {
			tahta[((i/10000)%10)*8+(i/1000)%10] = -1.0;
		}
		tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
	}
	public void piyonTerfiCiftGidisGeriAl(int i, boolean siraKimde, double[] tahta, PerformansTest p) {
		p.setGecerkenAlma(-1);
		if (siraKimde) {
			tahta[((i/100)%10)*8+(i/10)%10] = 1.0;
		} else {
			tahta[((i/100)%10)*8+(i/10)%10] = -1.0;
		}
		tahta[((i/10000)%10)*8+(i/1000)%10] = 0.0;
	}
	public void siradanGidisler(int i, boolean siraKimde, double[] tahta, PerformansTest p) {
		alinanTaslar.add(tahta[((i/10000)%10)*8+(i/1000)%10]);
		if (siraKimde) {
			if (i/100000 == 900) {												
				p.setBeyazSahKonum((i/10000)%10*10+(i/1000)%10);
			}
			tahta[((i/10000)%10)*8+(i/1000)%10] = i/100000;
			tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
		}
		else {
			if (i/100000 == -900) {
				p.setSiyahSahKonum((i/10000)%10*10+(i/1000)%10);
			}
			tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
			tahta[((i/10000)%10)*8+(i/1000)%10] = (i/100000)*-1;
		}
	}
	public void siradanGidislerGeriAl(int i, boolean siraKimde, double[] tahta, PerformansTest p) {
		tahta[((i/10000)%10)*8+(i/1000)%10] = alinanTaslar.get(alinanTaslar.size()-1);
		alinanTaslar.remove(alinanTaslar.size()-1);
		if (siraKimde) {
			if (i/100000 == 900) {												
				p.setBeyazSahKonum((i/100)%10*10+(i/10)%10);
			}			
			tahta[((i/100)%10)*8+(i/10)%10] = i/100000;
		} else {
			if (i/100000 == 900) {												
				p.setSiyahSahKonum((i/100)%10*10+(i/10)%10);
			}								
			tahta[((i/100)%10)*8+(i/10)%10] = (i/100000)*-1;
		}
	}
}