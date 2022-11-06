package motor;

import java.util.ArrayList;
import java.util.List;

public class PerformansTest {
	private boolean beyazSahKisaRok, beyazSahUzunRok, siyahSahKisaRok, siyahSahUzunRok, siraKimde;
	private int siyahSahKonum, beyazSahKonum, elliHamleKurali, beyazSahCekis, siyahSahCekis, gecerkenAlma = -1;
	private List<Integer> sahMudaheleKare = new ArrayList<>();
	private int[] tahta;
	
	public PerformansTest(boolean siraKimde,boolean beyazSahKisaRok,boolean beyazSahUzunRok,boolean siyahSahKisaRok,boolean siyahSahUzunRok, int[] tahta, int siyahSahKonum, int beyazSahKonum, int gecerkenAlma) {
		this.siraKimde = siraKimde;
		this.beyazSahKisaRok = beyazSahKisaRok;
		this.beyazSahUzunRok = beyazSahUzunRok;
		this.siyahSahKisaRok = siyahSahKisaRok;
		this.siyahSahUzunRok = siyahSahUzunRok;
		this.tahta = tahta;
		this.siyahSahKonum = siyahSahKonum;
		this.beyazSahKonum = beyazSahKonum;
		this.gecerkenAlma = gecerkenAlma;
	}
	public PerformansTest() {
		
	}
	
	
	public boolean isBeyazSahKisaRok() {
		return beyazSahKisaRok;
	}
	public void setBeyazSahKisaRok(boolean beyazSahKisaRok) {
		this.beyazSahKisaRok = beyazSahKisaRok;
	}
	public boolean isBeyazSahUzunRok() {
		return beyazSahUzunRok;
	}
	public void setBeyazSahUzunRok(boolean beyazSahUzunRok) {
		this.beyazSahUzunRok = beyazSahUzunRok;
	}
	public boolean isSiyahSahKisaRok() {
		return siyahSahKisaRok;
	}
	public void setSiyahSahKisaRok(boolean siyahSahKisaRok) {
		this.siyahSahKisaRok = siyahSahKisaRok;
	}
	public boolean isSiyahSahUzunRok() {
		return siyahSahUzunRok;
	}
	public void setSiyahSahUzunRok(boolean siyahSahUzunRok) {
		this.siyahSahUzunRok = siyahSahUzunRok;
	}
	public int getSiyahSahKonum() {
		return siyahSahKonum;
	}
	public void setSiyahSahKonum(int siyahSahKonum) {
		this.siyahSahKonum = siyahSahKonum;
	}
	public int getBeyazSahKonum() {
		return beyazSahKonum;
	}
	public void setBeyazSahKonum(int beyazSahKonum) {
		this.beyazSahKonum = beyazSahKonum;
	}
	public boolean isSiraKimde() {
		return siraKimde;
	}
	public void setSiraKimde(boolean siraKimde) {
		this.siraKimde = siraKimde;
	}
	public int[] getTahta() {
		return tahta;
	}
	public void setTahta(int[] tahta) {
		this.tahta = tahta;
	}
	public int getElliHamleKurali() {
		return elliHamleKurali;
	}
	public void setElliHamleKurali(int elliHamleKurali) {
		this.elliHamleKurali = elliHamleKurali;
	}
	public int getBeyazSahCekis() {
		return beyazSahCekis;
	}
	public void setBeyazSahCekis(int beyazSahCekis) {
		this.beyazSahCekis = beyazSahCekis;
	}
	public int getSiyahSahCekis() {
		return siyahSahCekis;
	}
	public void setSiyahSahCekis(int siyahSahCekis) {
		this.siyahSahCekis = siyahSahCekis;
	}	
	public int getGecerkenAlma() {
		return gecerkenAlma;
	}
	public void setGecerkenAlma(int gecerkenAlma) {
		this.gecerkenAlma = gecerkenAlma;
	}
	public List<Integer> sahMudaheleKareAl() {
		return sahMudaheleKare;
	}
	public void sahMudaheleKareAyarla(List<Integer> sahMudaheleKare) {
		this.sahMudaheleKare = sahMudaheleKare;
	}
}
