package test;

import java.util.List;

import motor.Hamleler;
import motor.PerformansTest;
import motor.Tahta;
import oyun.Oyun;

public class Perft extends Oyun {	
	Hamleler hamle = new Hamleler();
	PerformansTest p = new PerformansTest();
	Tahta t = new Tahta();
	long maksimumDerinlik = 6, toplamHamle = 0, varyant;
	public Perft() {
		//movegeneratorKontrol();
		FEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		long mili = System.nanoTime();				
		performansTestKok(0,p.isSiraKimde(),p.getTahta());
		System.out.println("Toplam Hamle = "+(toplamHamle)+" Alınan taş sayısı = "+hamle.captures+" Çekilen şah sayısı "+hamle.toplamSahSayisi+" Geçerken Alma "+hamle.gecerkenAl+" Atılan rok sayısı "+hamle.rok+" Mat sayisi "+hamle.mat);			
		double kacSaniye = ((System.nanoTime() - mili)/ 1000000000.0);
        System.out.println(kacSaniye);
        System.out.println("Saniyede Hamle = "+(int)(toplamHamle/kacSaniye));
	}
	public static void main(String [] args) {		
		new Perft();
	}
	/*public void performansTest(int derinlik, boolean siraKimde, int[] tahta) {
		if (derinlik < maksimumDerinlik) {			
			int[] hamleler = hamle.hamleleriAl(siraKimde, tahta, p);			
			for (int i : hamleler) {
				t.hamleYap(i, false, siraKimde, tahta,p,derinlik-1);
			    //System.out.println(i);
			    //tahtayiGoster(tahta);
				if (derinlik+1==maksimumDerinlik) {toplamHamle++;varyant++;}
				performansTest(derinlik+1,!siraKimde,tahta);
				t.hamleYap(i, true, siraKimde, tahta,p,derinlik-1);
			    //tahtayiGoster(tahta);
			}
		} 
	}*/
	public void performansTestKok(int derinlik, boolean siraKimde, int[] tahta) {
		System.out.println("Hedef Derinlik "+(maksimumDerinlik));
		List<Integer> hamleler = hamle.hamleleriAl(siraKimde, tahta, p);
		for (int i : hamleler) {			
			t.hamleYap(i, false, siraKimde, tahta, p, derinlik-1);					
			if (derinlik+1==maksimumDerinlik) {toplamHamle++;}
			performansTest(derinlik+1,!siraKimde,tahta);
			System.out.println(mevcutHamleNotasyon(i) +" "+ varyant);
			varyant = 0;
			t.hamleYap(i, true, siraKimde, tahta, p, derinlik-1);					
		}
	}
	public void performansTest(int derinlik, boolean siraKimde, int[] tahta) {
		if (derinlik < maksimumDerinlik) {
			List<Integer> hamleler = hamle.hamleleriAl(siraKimde, tahta, p);
			for (int i : hamleler) {
				t.hamleYap(i, false, siraKimde, tahta, p, derinlik-1);							
				if (derinlik+1==maksimumDerinlik) {toplamHamle++;varyant++;}													
				performansTest(derinlik+1,!siraKimde,tahta);				
				t.hamleYap(i, true, siraKimde, tahta, p, derinlik-1);						
			}
		}
	}
	public void movegeneratorKontrol() {
		long hesaplanmisToplamHamle = 0;
		int[] derinlik = {6,6,8,6,5,6};
		String[] fen = {"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1","r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq -"
		,"8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - -","r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1","rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8","r4rk1/1pp1qppp/p1np1n2/2b1p1B1/2B1P1b1/P1NP1N2/1PP1QPPP/R4RK1 w - - 0 10"};			
		for (int i = 0;i < fen.length;i++) {
			long mili = System.nanoTime();
			FEN(fen[i]);
			maksimumDerinlik = derinlik[i];
			performansTestKok(0,p.isSiraKimde(),p.getTahta());
			System.out.println("Toplam Hamle = "+toplamHamle+" Alınan taş sayısı = "+hamle.captures+" Çekilen şah sayısı "+hamle.toplamSahSayisi+" Geçerken Alma "+hamle.gecerkenAl+" Atılan rok sayısı "+hamle.rok+" Mat sayisi "+hamle.mat);			
			double kacSaniye = ((System.nanoTime() - mili)/ 1000000000.0);
	        System.out.println(kacSaniye);
	        System.out.println("Saniyede Hamle = "+(int)(toplamHamle/kacSaniye));
	        hesaplanmisToplamHamle += toplamHamle;
	        toplamHamle =0;hamle.captures = 0;hamle.toplamSahSayisi=0;hamle.gecerkenAl=0;hamle.rok=0;hamle.mat=0;
		}
		if (hesaplanmisToplamHamle == 18760479561L) {
			System.out.println("Movegenerator doğru çalışıyor !");
		}
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
					if (!rokIhtimal && Character.isAlphabetic(fen.charAt(p+2))) {
						gecerkenAlma =Character.getNumericValue(fen.charAt(p+2))-10;						
					}
				}				
			}			
		}		
		tahtayiGoster(tahta);		
		p = new PerformansTest(siraKimde, beyazKisaRok, beyazUzunRok, siyahKisaRok, siyahUzunRok, tahta, siyahSahKonum, beyazSahKonum, gecerkenAlma);				
	}
	public String mevcutHamleNotasyon(int i) {		
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
