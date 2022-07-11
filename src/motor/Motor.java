package motor;

import java.util.*;
import oyun.Oyun;

public class Motor extends Oyun {
	Tahta t = new Tahta();
	ArrayList<Integer> beyazH = new ArrayList<Integer>(),siyahH =new ArrayList<Integer>(),oynanacakVaryantlar =new ArrayList<Integer>();
	Hamleler hamle = new Hamleler();
	double[] temizTahta = tahta;	
	int maksimumDerinlik = 4, toplamHamle = 0, varyant;
	public Motor() {
		long mili = System.nanoTime();		
		performansTestKok(1,true, true);
		System.out.println(toplamHamle);
		double kacSaniye = ((System.nanoTime() - mili)/ 1000000000.0);
        System.out.println(kacSaniye);
		
	}
	public static void main(String [] args) {		
		new Motor();		
	}	
	
	public void performansTestKok(int derinlik, boolean siraKimde, boolean kokVaryant) {
		System.out.println("Hedef Derinlik = "+(maksimumDerinlik-derinlik));
		hamle.arama(siraKimde);
		String hamleler = hamle.varyantlar;
		//toplamHamle+= hamle.varyantlar.length()/6;		
		for (int i = 0;i < hamleler.length();i+=6) {			
			//System.out.println(hamleler.substring(i, i+6));			
			//mevcutHamleNotasyon(Integer.parseInt(hamleler.substring(i, i+6)),siraKimde);
			//System.out.println("Kök varyant");
			t.hamleYap(Integer.parseInt(hamleler.substring(i, i+6)), false, kokVaryant, tahta);
			//tahtayiGoster();
			performansTest(derinlik+1,!siraKimde);
			System.out.println(mevcutHamleNotasyon(Integer.parseInt(hamleler.substring(i, i+6)),siraKimde)+" "+varyant);
			varyant = 0;
			t.hamleYap(Integer.parseInt(hamleler.substring(i, i+6)), true, kokVaryant, tahta);
		}				
	}	
	public void performansTest(int derinlik, boolean siraKimde) {
		if (derinlik < maksimumDerinlik) {
			//System.out.println("Arama " +derinlik+" Kaç hamle "+hamle.varyantlar.length()/6);
			hamle.arama(siraKimde);
			String hamleler = hamle.varyantlar;
			for (int i = 0;i < hamleler.length();i+=6) {
				//System.out.println(i/6);
				//mevcutHamleNotasyon(Integer.parseInt(hamleler.substring(i, i+6)),siraKimde);				
				t.hamleYap(Integer.parseInt(hamleler.substring(i, i+6)), false, siraKimde, tahta);
				//tahtayiGoster();				
				//t.hamleYap(Integer.parseInt(hamle.varyantlar.substring(i, i+6)), true, siraKimde, tahta);			
				if (derinlik+1==maksimumDerinlik) {toplamHamle++;varyant++;}
				performansTest(derinlik+1,!siraKimde);
				t.hamleYap(Integer.parseInt(hamleler.substring(i, i+6)), true, siraKimde, tahta);
			}			
		} else {			
			//t.hamleYap(t.hamleGecmisi.get(t.hamleGecmisi.size()-1), true, !siraKimde, tahta);
			//System.out.println("Alt varyantın sonundayız "+derinlik+" Hamle geçmişi = "+t.hamleGecmisi.size());
		}
	}
	public String mevcutHamleNotasyon(int i, boolean hangiRenk) {
		String hamle = "";		
			if (hangiRenk) {
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
				//System.out.print(hamle);
			} else if (!hangiRenk) {
				if (i/100000 == 900) {
					hamle ="s"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 9) {
					hamle ="v"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 3) {
					hamle ="a"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 4) {
					hamle ="f"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 5) {
					hamle ="k"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				} else  if (i/100000 == 1) {
					hamle = "p"+Character.toLowerCase((char)(i/1000%10+65))+notasyonCevir(i/10000%10);
				}
				//System.out.print(hamle);
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