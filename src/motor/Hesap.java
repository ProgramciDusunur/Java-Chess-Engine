package motor;

public class Hesap {		
	OyunOrtasi pozisyonel = new OyunOrtasi();	
	public double analiz(int[] tahta, boolean siraKimde) {
		if (siraKimde)
			return -beyazAnaliz(tahta);
		else			
			return -siyahAnaliz(tahta);
	}
	public double siyahAnaliz(int[] tahta) {
		double materyalFark = 0, beyazMobilite = 0, siyahMobilite = 0;		
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
				materyalFark += 9;
				break;
			case 9:				
				beyazMobilite += pozisyonel.getBeyazVezir(i);
				//beyazVezirPozisyonelHesap(i);
				materyalFark += -9;
				break;
			case -5:				
				siyahMobilite += pozisyonel.getSiyahKale(i);
				//siyahKalePozisyonelHesap(i);
				materyalFark += 5;
				break;
			case 5:				
				beyazMobilite += pozisyonel.getBeyazKale(i);
				//beyazKalePozisyonelHesap(i);
				materyalFark += -5;
				break;
			case -4:				
				siyahMobilite += pozisyonel.getSiyahFil(i);
				//siyahFilPozisyonelHesap(i);
				materyalFark += 3;
				break;
			case 4:				
				beyazMobilite += pozisyonel.getBeyazFil(i);
				//beyazFilPozisyonelHesap(i);
				materyalFark += -3;
				break;
			case -3:				
				siyahMobilite += pozisyonel.getSiyahAt(i);
				//siyahAtPozisyonelHesap(i);
				materyalFark += 3;
				break;
			case 3:				
				beyazMobilite += pozisyonel.getBeyazAt(i);
				//beyazAtPozisyonelHesap(i);
				materyalFark += -3;
				break;
			case -1:				
				siyahMobilite += pozisyonel.getSiyahPiyon(i);
				//siyahPiyonPozisyonelHesap(i);
				materyalFark += 1;
				break;
			case 1:								
				beyazMobilite += pozisyonel.getBeyazPiyon(i);
				//beyazPiyonPozisyonelHesap(i);
				materyalFark += -1;
				break;				
			}			
		}							
		return (siyahMobilite-beyazMobilite)/100+materyalFark;
	}
	public double beyazAnaliz(int[] tahta) {
		double materyalFark = 0, beyazMobilite = 0, siyahMobilite = 0;		
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
		return (beyazMobilite-siyahMobilite)/100+materyalFark;
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
