package motor;

import oyun.Oyun;

public class Hesap {	
	Oyun o = new Oyun();
	PozisyonelHesap pozisyonel = new PozisyonelHesap();	
	public double analiz(int[] tahta, boolean siraKimde) {
		double materyalFark = 0, beyazMobilite = 0, siyahMobilite = 0, mobilite = 0;		
		for (int i = 0;i < tahta.length;i++) {
			switch(tahta[i]) {			
			case -900:
				siyahMobilite += pozisyonel.getSiyahSah(i);
				//siyahSahPozisyonelHesap(i);
				break;
			case 900:				
				beyazMobilite += pozisyonel.getBeyazSah(i);
				//beyazSahPozisyonelHesap(i);
				break;
			case -9:				
				siyahMobilite += pozisyonel.getSiyahVezir(i);
				//siyahVezirPozisyonelHesap(i);
				materyalFark += -9;
				break;
			case 9:				
				beyazMobilite += pozisyonel.getBeyazVezir(i);
				//beyazVezirPozisyonelHesap(i);
				materyalFark += 9;
				break;
			case -5:				
				siyahMobilite += pozisyonel.getSiyahKale(i);
				//siyahKalePozisyonelHesap(i);
				materyalFark += -5;
				break;
			case 5:				
				beyazMobilite += pozisyonel.getBeyazKale(i);
				//beyazKalePozisyonelHesap(i);
				materyalFark += 5;
				break;
			case -4:				
				siyahMobilite += pozisyonel.getSiyahFil(i);
				//siyahFilPozisyonelHesap(i);
				materyalFark += -3;
				break;
			case 4:				
				beyazMobilite += pozisyonel.getBeyazFil(i);
				//beyazFilPozisyonelHesap(i);
				materyalFark += 3;
				break;
			case -3:				
				siyahMobilite += pozisyonel.getSiyahAt(i);
				//siyahAtPozisyonelHesap(i);
				materyalFark += -3;
				break;
			case 3:				
				beyazMobilite += pozisyonel.getBeyazAt(i);
				//beyazAtPozisyonelHesap(i);
				materyalFark += 3;
				break;
			case -1:				
				siyahMobilite += pozisyonel.getSiyahPiyon(i);
				//siyahPiyonPozisyonelHesap(i);
				materyalFark += -1;
				break;
			case 1:								
				beyazMobilite += pozisyonel.getBeyazPiyon(i);
				//beyazPiyonPozisyonelHesap(i);
				materyalFark += 1;
				break;				
			}			
		}					
		return minMaxDeger(mobilite,beyazMobilite,siyahMobilite,siraKimde)/100+materyalFark;
	}
	public double minMaxDeger(double mobilite, double beyazMobilite, double siyahMobilite, boolean siraKimde)  {
		if (siraKimde)
			return beyazMinMaxDeger(beyazMobilite,siyahMobilite);
		else
			return siyahMinMaxDeger(beyazMobilite,siyahMobilite);
	}
	public double beyazMinMaxDeger(double beyazMobilite, double siyahMobilite) {
		return beyazMobilite-siyahMobilite;		
	}
	public double siyahMinMaxDeger(double beyazMobilite, double siyahMobilite) {		
		return siyahMobilite-beyazMobilite;		
	}
	public void beyazSahPozisyonelHesap(int i) {
		
	}
	public void siyahSahPozisyonelHesap(int i) {
		
	}
	public void beyazVezirPozisyonelHesap(int i) {
		
	}
	public void siyahVezirPozisyonelHesap(int i) {
		
	}
	public void beyazKalePozisyonelHesap(int i) {
		
	}
	public void siyahKalePozisyonelHesap(int i) {
		
	}
	public void beyazFilPozisyonelHesap(int i) {
		
	}
	public void siyahFilPozisyonelHesap(int i) {
		
	}
	public void beyazAtPozisyonelHesap(int i) {
		
	}
	public void siyahAtPozisyonelHesap(int i) {
		
	}
	public void beyazPiyonPozisyonelHesap(int i) {
		
	}
	public void siyahPiyonPozisyonelHesap(int i) {
		
	}
}
