package grafik;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import oyun.Oyun;

public class Grafik extends JPanel  {

    private Oyun t = new Oyun();    
    List<Integer> tasCizim;
    private static final long serialVersionUID = 1L;
    private boolean arayuzKarar = false;
    private int kareBaslangicX=200 ,kareBaslangicY=200,kareArtisX = 75,kareArtisY = 75;
    
    public void arayuzAyarla() {
        arayuzKarar = !arayuzKarar;
    }
    public boolean arayuzKararAl() {
        return arayuzKarar;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (t.arayuzDinleyiciKarar() == false) {
        	t.sahTehtid((byte) -1);
            this.addMouseListener(t);
            this.addMouseMotionListener(t);
            t.arayuzDinleyiciKararAyar();
        }
        this.setBackground(Color.DARK_GRAY);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.WHITE);
        for (int i = 0;i < 64;i++) {
            if (i % 2 == 0) {
                if (i != 0 && i!= 8 && i != 16 && i!= 24 && i!= 32 && i!= 40 && i!= 48 && i!= 56 && i!= 64) {
                    kareBaslangicX+= 75;
                }
                if (i>=0 && i<8 || i>=16 && i<24 || i>=32 && i< 40 || i>=48 && i< 56) {
                    g2D.clearRect(kareBaslangicX, kareBaslangicY+(i/8*75), kareArtisX, kareArtisY);
                }
                else if (i>=8 && i<16 || i>=24 && i< 32 || i>=40 && i< 48 || i>=56 && i< 64) {
                    g2D.fill3DRect(kareBaslangicX, kareBaslangicY+(i/8*75), kareArtisX, kareArtisY, false);
                }
            }
            else  {
                g2D.setColor(Color.CYAN);
                if (i != 8) {
                    kareBaslangicX+= 75;
                }
                if (i>0 && i<8 || i>=16 && i<24 || i>=32 && i< 40 || i>=48 && i< 56) {
                    g2D.fill3DRect(kareBaslangicX, kareBaslangicY+(i/8*75), kareArtisX, kareArtisY, false);
                }
                else if (i>8 && i<16 || i>=24 && i< 32 || i>=40 && i< 48 || i>=56 && i< 64) {
                    g2D.clearRect(kareBaslangicX, kareBaslangicY+(i/8*75), kareArtisX, kareArtisY);
                }
            }
            if (i == 7 || i == 15 || i == 23 || i == 31 || i==39 || i== 47 || i== 55 || i==63) {
                kareBaslangicX = 200;
            }
        }
        if (arayuzKararAl()) {
            g2D.fill3DRect(725,350, kareArtisX, kareArtisY, false);              
            t.yeniArama(false);                     
            if (!t.cizilecekHamlelerVeriAl().isEmpty()) {
            	tasCizim = t.cizilecekHamlelerVeriAl();            	            
            	g2D.setColor(new Color(100,100,100));
            	for (int i : tasCizim) {            		
            		if (i < 0) {            			            			
            			g2D.fill3DRect(kareBaslangicX+((i*-1)/1000)%10*75,kareBaslangicY+((i*-1)/10000)%10*75, kareArtisX, kareArtisY, false);
            		}
            		else {
            			g2D.fill3DRect(kareBaslangicX+(i/1000)%10*75,kareBaslangicY+(i/10000)%10*75, kareArtisX, kareArtisY, false);
            		}
            		
            	}
            	
            }                    
            g2D.setColor(Color.GREEN);
        }        
    }

}