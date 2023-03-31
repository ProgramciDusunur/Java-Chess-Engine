package motor;

import java.util.List;
import java.util.Scanner;

import oyun.Oyun;

public class Motor extends Oyun {
	Tahta t = new Tahta();	
	Hesap h = new Hesap();
	PerformansTest p = new PerformansTest();
	Hamleler hamle = new Hamleler();
	long toplamHamle = 0;
	int alfaHamle = 0, betaHamle = 0, derinlik = 7;
	double alfa = Double.NEGATIVE_INFINITY, beta = Double.POSITIVE_INFINITY;	
	public Motor() {		
		/*FEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		long mili = System.nanoTime();			
		//System.out.println(h.analiz(p.getTahta(), p.isSiraKimde()));		
		//System.out.println(minimaxKok(7,p.isSiraKimde(),alfa,beta));
		System.out.println(negascoutKok(8,p.isSiraKimde(),alfa,beta));
		System.out.println("Toplam Hamle = "+toplamHamle+" Alınan taş sayısı = "+hamle.captures+" Çekilen şah sayısı "+hamle.toplamSahSayisi+" Geçerken Alma "+hamle.gecerkenAl+" Atılan rok sayısı "+hamle.rok+" Mat sayisi "+hamle.mat);
		double kacSaniye = ((System.nanoTime() - mili)/ 1000000000.0);
        System.out.println(kacSaniye);
        System.out.println("Saniyede Hamle = "+(int)(toplamHamle/kacSaniye));
        System.out.println("Alfa Hamle = "+mevcutHamleNotasyon(alfaHamle,p.isSiraKimde()));*/
        //System.out.println("Beta Hamle = "+mevcutHamleNotasyon(betaHamle));
		UCI();
	}
	public static void main(String [] args) {		 
		new Motor();
	}	
	public double negascoutKok(int derinlik, boolean siraKimde, double alfa, double beta) {
		int[] tahta = p.getTahta();
		List<Integer> hamleler = hamle.hamleleriAl(siraKimde, tahta, p);
		double max = Double.NEGATIVE_INFINITY;
		for (int i : hamleler) {
			t.hamleYap(i, false, siraKimde, tahta, p, derinlik-1);
			double skor = Math.max(max,-negascout(derinlik - 1,!siraKimde,tahta,-beta,-alfa));			
			alfa = Math.max(alfa, skor);			
			t.hamleYap(i, true, siraKimde, tahta, p, derinlik-1);
			if (skor > max) {
				max = skor;
				alfaHamle = i;
			}						
		}
		return max;
	}
	public double negascout(int derinlik, boolean siraKimde, int[] tahta, double alfa, double beta) {
		if (derinlik == 0) {toplamHamle++;return h.analiz(tahta, p.isSiraKimde());}		
		List<Integer> hamleler = hamle.hamleleriAl(siraKimde, tahta, p);		
		double skor = Double.NEGATIVE_INFINITY;
		for (int i : hamleler) {
			t.hamleYap(i, false, siraKimde, tahta, p, derinlik-1);
			skor = Math.max(skor,-negascout(derinlik - 1,!siraKimde,tahta,-beta,-alfa));		
			alfa = Math.max(alfa, skor);
			t.hamleYap(i, true, siraKimde, tahta, p, derinlik-1);
			if (alfa >= beta) {				
				break;
			}			
		}
		return skor;
	}	
	public double minimaxKok(int derinlik, boolean siraKimde, double alfa, double beta) {		
		int[] tahta = p.getTahta();
		List<Integer> hamleler = hamle.hamleleriAl(siraKimde, tahta, p);
		double max = Double.NEGATIVE_INFINITY;
		for (int i : hamleler) {
			t.hamleYap(i, false, siraKimde, tahta, p, derinlik-1);			
	        double skor = min(derinlik - 1 ,!siraKimde,tahta,alfa,beta);
	        if(skor > max) {
	        	max = skor;
	        	alfaHamle = i;	        	
	        }	        
			t.hamleYap(i, true, siraKimde, tahta, p, derinlik-1);
		}	
		return max;
	}	
	public double max(int derinlik, boolean siraKimde, int[] tahta, double alfa, double beta) {
		if (derinlik == 0) {toplamHamle++;return h.analiz(tahta, p.isSiraKimde());}
		List<Integer> hamleler = hamle.hamleleriAl(siraKimde, tahta, p);		
		for (int i : hamleler) {
	    	t.hamleYap(i, false, siraKimde, tahta, p, derinlik-1);
	        double max = min(derinlik - 1 ,!siraKimde,tahta,alfa,beta);		        
	        t.hamleYap(i, true, siraKimde, tahta, p, derinlik-1);
	        alfa = Math.max(alfa, max);
	        if (beta <= alfa) {
                return alfa;
            }		        
	    }		    
	    return alfa;
	}
	public double min(int derinlik, boolean siraKimde, int[] tahta, double alfa, double beta) {
		if (derinlik == 0) {toplamHamle++;return h.analiz(tahta, p.isSiraKimde());}
		List<Integer> hamleler = hamle.hamleleriAl(siraKimde, tahta, p);			
		double min = Double.POSITIVE_INFINITY;			
	    for (int i : hamleler) {
	    	t.hamleYap(i, false, siraKimde, tahta, p, derinlik-1);
	        min = max( derinlik - 1,!siraKimde,tahta,alfa,beta);		        
	        t.hamleYap(i, true, siraKimde, tahta, p, derinlik-1);
	        beta = Math.min(beta, min);
	        if (beta <= alfa) {
                return beta;
            }		        
	    }		    
	    if (hamleler.isEmpty()) {
	    	if (siraKimde && p.getBeyazSahCekis() == 0 || !siraKimde && p.getSiyahSahCekis() == 0) {
	    		return 0.0;
	    	}
	    	else if (min == Double.POSITIVE_INFINITY) {		    		
	    		return min;
	    	}
	    }
	    return beta;
	}		
	
	public String mevcutHamleNotasyon(int i, boolean siraBeyazdaMi) {		
		String hamle;		
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
			if (siraBeyazdaMi) {
				hamle = "e1g1";
			} else {
				hamle = "e8g8";
			}
			//hamle = "O-O";
			break;
		case 6:
			if (siraBeyazdaMi) {
				hamle = "e1c1";
			} else {
				hamle = "e8c8";
			}
			//hamle = "O-O-O";
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
					if (!rokIhtimal && Character.isAlphabetic(fen.charAt(p+2))) {
						gecerkenAlma =Character.getNumericValue(fen.charAt(p+2))-10;																			 	
					}
				}				
			}			
		}		
		//tahtayiGoster(tahta);		
		p = new PerformansTest(siraKimde, beyazKisaRok, beyazUzunRok, siyahKisaRok, siyahUzunRok, tahta, siyahSahKonum, beyazSahKonum, gecerkenAlma);				
	}
	public void pozisyonuHazirla(String inputString) {		
		int[] tahta = p.getTahta();			
		int hamleSayisi = 0, tasinBulunduguKare, tasinGidecegiKare;		
		for (int i = 24;i+5 < inputString.length() || i+3 < inputString.length();i+=5,hamleSayisi++) {
			tasinBulunduguKare = notasyonCevir(Character.getNumericValue(inputString.charAt(i+1)))*8+Character.getNumericValue(inputString.charAt(i))-10;					
			tasinGidecegiKare = notasyonCevir(Character.getNumericValue(inputString.charAt(i+3)))*8+Character.getNumericValue(inputString.charAt(i+2))-10;			
			if (tahta[tasinBulunduguKare] == 900 && "e1g1".equals(inputString.substring(i, i+4))) {
				tahta[tasinGidecegiKare-1] = 5;
				tahta[63] = 0;
				beyazRokAyarla();
			} else if (tahta[tasinBulunduguKare] == 900 && "e1c1".equals(inputString.substring(i, i+4))) {
				tahta[tasinGidecegiKare+1] = 5;
				tahta[56] = 0;
				beyazRokAyarla();
			} else if (tahta[tasinBulunduguKare] == -900 && "e8g8".equals(inputString.substring(i, i+4))) {
				tahta[tasinGidecegiKare-1] = -5;
				tahta[7] = 0;
				siyahRokAyarla();				
			} else if (tahta[tasinBulunduguKare] == -900 && "e8c8".equals(inputString.substring(i, i+4))) {
				tahta[tasinGidecegiKare+1] = -5;
				tahta[0] = 0;
				siyahRokAyarla();				
			} else if (tahta[tasinBulunduguKare] == 1 && tahta[tasinGidecegiKare] == 0 && tasinBulunduguKare/8 == 3 && notasyonCevir(Character.getNumericValue(inputString.charAt(i+1))) != notasyonCevir(Character.getNumericValue(inputString.charAt(i+3)))) {
				tahta[tasinGidecegiKare+8] = 0;				
			} else if (tahta[tasinBulunduguKare] == -1 && tahta[tasinGidecegiKare] == 0 && tasinBulunduguKare/8 == 4 && notasyonCevir(Character.getNumericValue(inputString.charAt(i+1))) != notasyonCevir(Character.getNumericValue(inputString.charAt(i+3)))) {
				tahta[tasinGidecegiKare-8] = 0;
			}
			tahta[tasinGidecegiKare] = tahta[tasinBulunduguKare];
			tahta[tasinBulunduguKare] = 0;			
		}				
		if (hamleSayisi % 2 == 0) {p.setSiraKimde(true);} else {p.setSiraKimde(false);}				
		p.setTahta(tahta);
	}
	public void beyazRokAyarla() {		
		p.setBeyazSahKisaRok(false);
		p.setBeyazSahUzunRok(false);
	}
	public void siyahRokAyarla() {
		p.setSiyahSahKisaRok(false);
		p.setSiyahSahUzunRok(false);
	}	

	public void UCI() {		
		boolean oyunHazirMi = false;
		Scanner input = new Scanner(System.in);
		String inputString;
		while (input.hasNext()) {			
			inputString = input.nextLine();
			if ("uci".equals(inputString)) {
				System.out.println("id name Spiritual");
				System.out.println("id author ProgramciDusunur");
				System.out.println("uciok");
			} else if ("ucinewgame".equals(inputString)) {
				FEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
				oyunHazirMi = true;
			} else if ("isready".equals(inputString)) {
				System.out.println("readyok");
			} else if ("white".equals(inputString)) {
				
			} else if ("go".equals(""+inputString.charAt(0)+inputString.charAt(1)) && oyunHazirMi) {				
				long mili = System.nanoTime();
				double skor = negascoutKok(derinlik,p.isSiraKimde(),alfa,beta);
				double kacSaniye = ((System.nanoTime() - mili)/ 1000000000.0);
				String hamle = mevcutHamleNotasyon(alfaHamle,p.isSiraKimde());
				System.out.println("info depth "+derinlik+" time "+((int)kacSaniye*1000)+" nodes "+toplamHamle+" nps "+(int)(toplamHamle/kacSaniye)+" "+" score "+skor+" cp "+10+" hashfull "+0+" pv "+hamle);				
				System.out.println("bestmove "+hamle);
				toplamHamle = 0;				
			} else if (inputString.length() > 22 && "position startpos moves".equals(inputString.substring(0, 23))) {
				FEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
				pozisyonuHazirla(inputString);
				tahtayiGoster(p.getTahta());
			} else if (inputString.length() > 8 && "set depth".equals(inputString.substring(0, 9))) {				
				derinlik = inputString.charAt(10)-48;				
			}
		}
		input.close();
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
		case 8:
			return 0;
		}
		return j;
	}
}