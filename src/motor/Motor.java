package motor;

import oyun.Oyun;

public class Motor extends Oyun {
	Tahta t = new Tahta();	
	Hesap h = new Hesap();
	PerformansTest p = new PerformansTest();
	Hamleler hamle = new Hamleler();
	long maksimumDerinlik = 1, toplamHamle = 0, varyant;
	int makineHamlesi = 0;
	double alfa, beta;
	public Motor() {
		long mili = System.nanoTime();		
		FEN("r1bqkbnr/pppppppp/2n5/8/3P4/8/PPP1PPPP/RNBQKBNR w KQkq - 1 2");		
		//performansTestKok(1,p.isSiraKimde(),p.getTahta());
		//minmax(1,p.isSiraKimde(),p.getTahta(),true);		
		System.out.println("Toplam Hamle = "+toplamHamle+" Alınan taş sayısı = "+hamle.captures+" Çekilen şah sayısı "+hamle.toplamSahSayisi+" Geçerken Alma "+hamle.gecerkenAl+" Atılan rok sayısı "+hamle.rok+" Mat sayisi "+hamle.mat);
		double kacSaniye = ((System.nanoTime() - mili)/ 1000000000.0);
        System.out.println(kacSaniye);
        System.out.println("Saniyede Hamle = "+(int)(toplamHamle/kacSaniye));		
	}
	public static void main(String [] args) {		
		new Motor();
	}		
	
	public void minmax(int derinlik, boolean siraKimde, int[] tahta, boolean minimumSkorYadaMaksimumSkor) {
		if (derinlik < maksimumDerinlik) {
			if (minimumSkorYadaMaksimumSkor) {
				max(derinlik, siraKimde, tahta);				
			} else {
				min(derinlik, siraKimde, tahta);				
			}
		}			
	}
	//System.out.println(mevcutHamleNotasyon(makineHamlesi,siraKimde)+" "+beta/100);
	public void min(int derinlik, boolean siraKimde, int[] tahta) {
		if (derinlik < maksimumDerinlik) {			
			int[] hamleler = hamle.hamleleriAl(siraKimde, tahta, p);
			for (int i : hamleler) {
				t.hamleYap(i, false, siraKimde, tahta, p, derinlik-1);			
				if (beta > h.analiz(tahta, siraKimde)) {					
					//tahtayiGoster(tahta);
					beta = h.analiz(tahta, siraKimde);
					makineHamlesi = i;
				}				
				if (derinlik+1==maksimumDerinlik) {toplamHamle++;varyant++;}
				minmax(derinlik+1,!siraKimde,tahta,true);
				t.hamleYap(i, true, siraKimde, tahta, p, derinlik-1);
			}
			
		}
	}
	public void max(int derinlik, boolean siraKimde, int[] tahta) {
		if (derinlik < maksimumDerinlik) {
			int[] hamleler = hamle.hamleleriAl(siraKimde, tahta, p);
			for (int i : hamleler) {
				t.hamleYap(i, false, siraKimde, tahta, p, derinlik-1);				
				if (alfa < h.analiz(tahta, siraKimde)) {					
					//tahtayiGoster(tahta);
					alfa = h.analiz(tahta, siraKimde);
					makineHamlesi = i;
				}
				if (derinlik+1==maksimumDerinlik) {toplamHamle++;varyant++;}				
				minmax(derinlik+1,!siraKimde,tahta,false);
				t.hamleYap(i, true, siraKimde, tahta, p, derinlik-1);
			}				
		}
	}
	public String mevcutHamleNotasyon(int i, boolean hangiRenk) {		
		String hamle = "";
		hamle =""+Character.toLowerCase((char)(i/10%10+65))+notasyonCevir(i/100%10)+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
		switch(i/1000000) {
		case 900:
			hamle +="Q";
			break;
		case 50:
			hamle +="R";
			break;
		case 40:
			hamle +="B";
			break;
		case 30:
			hamle +="N";
			break;
		case 2:
			hamle = "O-O";
			break;
		case 6:
			hamle = "O-O-O";
			break;
		}						
		return hamle;
	}
	public void FEN(String fen) {
		boolean siraKimde = false, siyahKisaRok = false, siyahUzunRok = false, beyazKisaRok = false, beyazUzunRok = false, rokIhtimal = false;
		int siyahSahKonum = 0, beyazSahKonum = 0, gecerkenAlma = -1;
		int[] tahta = new int[64];
		for (int i = 0, j = -1;i < fen.length();i++) {			
			if (j < 63 && fen.charAt(i) != '/') {				
				if (Character.isDigit(fen.charAt(i))) {					
					j+= Character.getNumericValue(fen.charAt(i));										
				} else {
					j++;
				}																
				if (fen.charAt(i) == 'r') {
					tahta[j] = -5;
				} else if (fen.charAt(i) == 'n') {
					tahta[j] = -3;
				} else if (fen.charAt(i) == 'b') {
					tahta[j] = -4;
				} else if (fen.charAt(i) == 'q') {
					tahta[j] = -9;
				} else if (fen.charAt(i) == 'k') {					
					siyahSahKonum = j/8*10+j%8;					
					tahta[j] = -900;
				} else if (fen.charAt(i) == 'p') {
					tahta[j] = -1;
				} else if (fen.charAt(i) == 'R') {
					tahta[j] = 5;
				} else if (fen.charAt(i) == 'N') {
					tahta[j] = 3;
				} else if (fen.charAt(i) == 'B') {
					tahta[j] = 4;
				} else if (fen.charAt(i) == 'Q') {
					tahta[j] = 9;
				} else if (fen.charAt(i) == 'K') {
					beyazSahKonum = j/8*10+j%8;
					tahta[j] = 900;
				} else if (fen.charAt(i) == 'P') {
					tahta[j] = 1;
				} 				
				if (j == 63) {j++;}
			} else if (j > 63) {				
				j++;				
				if (j == 66 && fen.charAt(i) == 'w') {					
					siraKimde = true;					
				} else if (j == 66 && fen.charAt(i) == 'b') {										
					siraKimde = false;
				}
				for (int p = i;j == 68 && p < 62 && fen.charAt(p) != ' ';p++) {					
					switch(fen.charAt(p)) {
					case '-':
						rokIhtimal = true;
						if (Character.isAlphabetic(fen.charAt(p+2))) {
							gecerkenAlma =Character.getNumericValue(fen.charAt(p+2))-10;
						}						 			
						break;
					case 'K':
						beyazKisaRok = true;
						break;
					case 'Q':
						beyazUzunRok = true;
						break;
					case 'k':
						siyahKisaRok = true;
						break;
					case 'q':						
						siyahUzunRok = true;
						break;					
					}
					if (!rokIhtimal) {
						if (Character.isAlphabetic(fen.charAt(p+2))) {
							gecerkenAlma =Character.getNumericValue(fen.charAt(p+2))-10;
						}						 			
					}
				}				
			}			
		}		
		tahtayiGoster(tahta);		
		p = new PerformansTest(siraKimde, beyazKisaRok, beyazUzunRok, siyahKisaRok, siyahUzunRok, tahta, siyahSahKonum, beyazSahKonum, gecerkenAlma);				
	}
	public int notasyonCevir(int j) {
		switch (j) {
		case 0:
			return 8;
		case 1:
			return 7; 
		case 2:
			return 6;
		case 3:
			return 5;
		case 4:
			return 4;
		case 5:
			return 3;
		case 6:
			return 2;
		case 7:
			return 1;
		}
		return j;
	}
}
