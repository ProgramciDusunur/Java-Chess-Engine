package motor;

import oyun.Oyun;

public class Motor extends Oyun {
	Tahta t = new Tahta();
	PerformansTest p = new PerformansTest();
	Hamleler hamle = new Hamleler();
	double[] temizTahta = p.getTahta();	
	long maksimumDerinlik = 3, toplamHamle = 0, varyant;
	public Motor() {
		long mili = System.nanoTime();		
		//FEN("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq -");
		//FEN("r4rk1/1pp1qppp/p1np1n2/2b1p1B1/2B1P1b1/P1NP1N2/1PP1QPPP/R4RK1 w - - 0 10");
		//FEN("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq -");
		FEN("rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R w KQ - 1 8");
		performansTestKok(1,p.isSiraKimde(),p.getTahta());
		System.out.println("Toplam Hamle = "+toplamHamle+" Alınan taş sayısı = "+hamle.captures+" Çekilen şah sayısı "+hamle.toplamSahSayisi+" Geçerken Alma "+hamle.gecerkenAl);
		double kacSaniye = ((System.nanoTime() - mili)/ 1000000000.0);
        System.out.println(kacSaniye);
        System.out.println("Saniyede Hamle = "+(int)(toplamHamle/kacSaniye));		
	}
	public static void main(String [] args) {		
		new Motor();		
	}		
	/*public void performansTest(int derinlik, boolean siraKimde, double[] tahta) {
		if (derinlik < maksimumDerinlik) {			
			int[] hamleler = hamle.hamleleriAl(siraKimde, tahta, p);			
			for (int i : hamleler) {
				t.hamleYap(i, false, siraKimde, tahta,p);
				System.out.println(i);
				tahtayiGoster(tahta);
				if (derinlik+1==maksimumDerinlik) {toplamHamle++;varyant++;}
				performansTest(derinlik+1,!siraKimde,tahta);
				t.hamleYap(i, true, siraKimde, tahta,p);
				tahtayiGoster(tahta);
			}
		} 
	}*/
	public void performansTestKok(int derinlik, boolean siraKimde, double[] tahta) {
		System.out.println("Hedef Derinlik "+(maksimumDerinlik-1));
		int[] hamleler = hamle.hamleleriAl(siraKimde, tahta, p);
		for (int i : hamleler) {			
			t.hamleYap(i, false, siraKimde, tahta, p);			
			if (derinlik+1==maksimumDerinlik) {toplamHamle++;}
			performansTest(derinlik+1,!siraKimde,tahta);
			System.out.println(mevcutHamleNotasyon(i,siraKimde) +" "+ varyant);
			varyant = 0;
			t.hamleYap(i, true, siraKimde, tahta, p);
		}
	}
	public void performansTest(int derinlik, boolean siraKimde, double[] tahta) {
		if (derinlik < maksimumDerinlik) {
			int[] hamleler = hamle.hamleleriAl(siraKimde, tahta, p);
			for (int i : hamleler) {
				System.out.println(i);
				t.hamleYap(i, false, siraKimde, tahta, p);
				tahtayiGoster(tahta);
				if (derinlik+1==maksimumDerinlik) {toplamHamle++;varyant++;}				
				performansTest(derinlik+1,!siraKimde,tahta);								
				t.hamleYap(i, true, siraKimde, tahta, p);
				//tahtayiGoster(tahta);
				
			}
		}
	}
	public String mevcutHamleNotasyon(int i, boolean hangiRenk) {		
		String hamle = "";		
			if (hangiRenk) {
				if (i/100000 == 900) {
					hamle ="S"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 9 || i/1000000 == 9 || i/100000000 == 9) {
					hamle ="V"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 3 || i/1000000 == 3 || i/10000000 == 3) {					
					hamle ="A"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 4 || i/1000000 == 4 || i/10000000 == 4) {
					hamle ="F"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 5 || i/1000000 == 5 || i/10000000 == 5) {
					hamle ="K"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 1 || i/1000000 == 1) {
					hamle ="P"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);;
				} else if (i == 200000) {
					hamle = "O-O";
				} else if (i == 600000) {
					hamle = "O-O-O";
				}				 
				//System.out.println(hamle);
			} else if (!hangiRenk) {
				if (i/100000 == 900) {
					hamle ="s"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 9 || i/1000000 == 9 || i/100000000 == 9) {
					hamle ="v"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 3 || i/1000000 == 3 || i/10000000 == 3) {
					hamle ="a"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 4 || i/1000000 == 4 || i/10000000 == 4) {
					hamle ="f"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 5 || i/1000000 == 5 || i/10000000 == 5) {
					hamle ="k"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 1 || i/1000000 == 1) {
					hamle = "p"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else if (i == 200000) {
					hamle = "O-O";
				} else if (i == 600000) {
					hamle = "O-O-O";
				}
				//System.out.println(hamle);
			}			
		return hamle;
	}
	public void FEN(String fen) {
		boolean siraKimde = false, siyahKisaRok = false, siyahUzunRok = false, beyazKisaRok = false, beyazUzunRok = false;
		int siyahSahKonum = 0, beyazSahKonum = 0;
		double[] tahta = new double[64];
		for (int i = 0, j = -1;i < fen.length();i++) {			
			if (j < 63 && fen.charAt(i) != '/') {				
				if (Character.isDigit(fen.charAt(i))) {					
					j+= Character.getNumericValue(fen.charAt(i));										
				} else {
					j++;
				}				
				//System.out.println(fen.charAt(i)+" "+j+" İ = "+i);										
				if (fen.charAt(i) == 'r') {
					tahta[j] = -5.0;
				} else if (fen.charAt(i) == 'n') {
					tahta[j] = -3.0;
				} else if (fen.charAt(i) == 'b') {
					tahta[j] = -4.0;
				} else if (fen.charAt(i) == 'q') {
					tahta[j] = -9.0;
				} else if (fen.charAt(i) == 'k') {					
					siyahSahKonum = j/8*10+j%8;					
					tahta[j] = -900.0;
				} else if (fen.charAt(i) == 'p') {
					tahta[j] = -1.0;
				} else if (fen.charAt(i) == 'R') {
					tahta[j] = 5.0;
				} else if (fen.charAt(i) == 'N') {
					tahta[j] = 3.0;
				} else if (fen.charAt(i) == 'B') {
					tahta[j] = 4.0;
				} else if (fen.charAt(i) == 'Q') {
					tahta[j] = 9.0;
				} else if (fen.charAt(i) == 'K') {
					beyazSahKonum = j/8*10+j%8;
					tahta[j] = 900.0;
				} else if (fen.charAt(i) == 'P') {
					tahta[j] = 1.0;
				} 				
				if (j == 63) {j++;}
			} else if (j > 63) {				
				j++;
				//System.out.println(fen.charAt(i)+" "+j+" İ = "+i);
				if (j == 66 && fen.charAt(i) == 'w') {					
					siraKimde = true;					
				} else if (j == 66 && fen.charAt(i) == 'b') {										
					siraKimde = false;
				}
				for (int p = i;j == 68 && p < 62 && fen.charAt(p) != ' ';p++) {
					//System.out.println("Yürüyorum yanımda bir karakedi dedi bana bir sigara ver "+fen.charAt(p));
					switch(fen.charAt(p)) {
					case '-':
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
				}			
			}			
		}
		tahtayiGoster(tahta);
		p = new PerformansTest(siraKimde, beyazKisaRok, beyazUzunRok, siyahKisaRok, siyahUzunRok, tahta, siyahSahKonum, beyazSahKonum);		
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