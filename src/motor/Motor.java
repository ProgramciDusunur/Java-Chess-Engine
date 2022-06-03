package motor;

import java.util.LinkedList;

import tahta.Tahta;

public class Motor extends Tahta {
	LinkedList<Integer> beyazH = new LinkedList<Integer>(),siyahH =new LinkedList<Integer>(),oynanacakVaryantlar =new LinkedList<Integer>();
	Hamleler hamle = new Hamleler();
	LinkedList<String> test = new LinkedList<String>();	
	int maksimumDerinlik = 3, toplamHamle = 0;
	public Motor() {
		long mili = System.nanoTime();		
		System.out.println(hareketTest(1,true));
		double kacSaniye = ((System.nanoTime() - mili)/ 1000000000.0);
        System.out.println(kacSaniye);
		
	}
	public static void main(String [] args) {		
		new Motor();		
	}	
	public int hareketTest(int derinlik, boolean siraKimde) {		
		int pozisyonSayisi = 0;
		if (derinlik < maksimumDerinlik) {
			hamle.arama(siraKimde);
			System.out.println(hamle.varyantlar);
			pozisyonSayisi+= hamle.motorHamle.size();
			for (int i : hamle.motorHamle) {
				hamleYap(i,false);								
				mevcutHamleNotasyon(i,siraKimde);
				tahtayiGoster();
				hamleYap(i,true);				
			}
			//hareketTest(derinlik+1,!siraKimde);
		}
		
		return pozisyonSayisi;
	}
	public void hamleler(boolean sifirla) {
		
	}	
	public void hamleYap(int i, boolean geriCevir) {
		if (i > 0) {
			if (geriCevir) {
				tahta[((i/10000)%10)*8+(i/1000)%10] = 0.0;
				tahta[((i/100)%10)*8+(i/10)%10] = i/100000;
			} else {				
				tahta[((i/10000)%10)*8+(i/1000)%10] = i/100000;
				tahta[((i/100)%10)*8+(i/10)%10] = 0.0;
			}
		} else {
			if (geriCevir) {
				tahta[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10] = 0.0;
				tahta[(((i*-1)/100)%10)*8+((i*-1)/10)%10] = i/100000;
			} else {				
				tahta[(((i*-1)/10000)%10)*8+((i*-1)/1000)%10] = i/100000;
				tahta[(((i*-1)/100)%10)*8+((i*-1)/10)%10] = 0.0;
			}			
		}
	}
	public String mevcutHamleNotasyon(int i, boolean hangiRenk) {
		String hamle = "";		
			if (i > 0 && hangiRenk) {
				if (i/100000 == 900) {
					hamle ="S"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 9) {
					hamle ="V"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 3) {					
					hamle ="A"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 4) {
					hamle ="F"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 5) {
					hamle ="K"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 1) {
					hamle ="P"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);;
				}				
				System.out.println(hamle);
			} else if (i < 0 && !hangiRenk) {
				if (i/100000 == -900) {
					hamle ="s"+Character.toLowerCase((char)((i*-1)/1000%10+65))+notasyonCevir((i*-1)/10000%10);
				} else  if (i/100000 == -9) {
					hamle ="v"+Character.toLowerCase((char)((i*-1)/1000%10+65))+notasyonCevir((i*-1)/10000%10);
				} else  if (i/100000 == -3) {
					hamle ="a"+Character.toLowerCase((char)((i*-1)/1000%10+65))+notasyonCevir((i*-1)/10000%10);
				} else  if (i/100000 == -4) {
					hamle ="f"+Character.toLowerCase((char)((i*-1)/1000%10+65))+notasyonCevir((i*-1)/10000%10);
				} else  if (i/100000 == -5) {
					hamle ="k"+Character.toLowerCase((char)((i*-1)/1000%10+65))+notasyonCevir((i*-1)/10000%10);
				} else  if (i/100000 == -1) {
					hamle = "p"+Character.toLowerCase((char)((i*-1)/1000%10+65))+notasyonCevir((i*-1)/10000%10);
				}
				System.out.println(hamle);
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