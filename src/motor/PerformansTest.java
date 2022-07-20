package motor;

public class PerformansTest {
	private boolean beyazSahKisaRok, beyazSahUzunRok, siyahSahKisaRok, siyahSahUzunRok, siraKimde;
	private int siyahSahKonum, beyazSahKonum, elliHamleKurali, beyazSahCekis, siyahSahCekis, sahCekenTas;
	private double[] tahta;
	
	public PerformansTest(boolean siraKimde,boolean beyazSahKisaRok,boolean beyazSahUzunRok,boolean siyahSahKisaRok,boolean siyahSahUzunRok, double[] tahta) {
		this.siraKimde = siraKimde;
		this.beyazSahKisaRok = beyazSahKisaRok;
		this.beyazSahUzunRok = beyazSahUzunRok;
		this.siyahSahKisaRok = siyahSahKisaRok;
		this.siyahSahUzunRok = siyahSahUzunRok;
		this.tahta = tahta;
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
	public double[] getTahta() {
		return tahta;
	}
	public void setTahta(double[] tahta) {
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
	public int getSahCekenTas() {
		return sahCekenTas;
	}
	public void setSahCekenTas(int sahCekenTas) {
		this.sahCekenTas = sahCekenTas;
	}
}
