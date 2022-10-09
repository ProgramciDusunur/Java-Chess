package motor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Hamleler {
	Beyaz beyaz = new Beyaz();
	Siyah siyah = new Siyah();
	public ArrayList<Integer> motorHamle = new ArrayList<Integer>(), gecerkenAlma = new ArrayList<Integer>();
	public LinkedList<Integer> sahMudaheleKare = new LinkedList<Integer>();
	public ArrayList<Integer> sahTehtid = new ArrayList<Integer>(
			  Arrays.asList(0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0,
					  		0,0,0,0,0,0,0,0));	
	int captures = 0, toplamSahSayisi = 0, gecerkenAl = 0, rok = 0, mat = 0;	
	public void arama(boolean beyazMiSiyahMi, int[] tahta, PerformansTest p) {		
		motorHamle.clear();		
		sahTehtid(false, tahta, p,beyazMiSiyahMi);
		/*if (beyazMiSiyahMi) {
			motorHamle.addAll(beyaz.hamleleriAl(tahta, sahTehtid, p));
		} else {
			motorHamle.addAll(siyah.hamleleriAl(tahta, sahTehtid, p));
		}*/
		for (byte i = 0;i < tahta.length;i++) {									
			//King movements, Sah hareketleri
			if (beyazMiSiyahMi && tahta[i] == 900 || !beyazMiSiyahMi && tahta[i] == -900) {					
				if ((i/8-1 > -1) && (beyazMiSiyahMi && tahta[(i/8-1)*8+i%8] < 1 && sahTehtid.get((i/8-1)*8+i%8)%10 < 1 || !beyazMiSiyahMi && tahta[(i/8-1)*8+i%8] > -1 && sahTehtid.get((i/8-1)*8+i%8)/10 < 1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8-1,tahta);}
				if ((i/8+1 < 8) && (beyazMiSiyahMi && tahta[(i/8+1)*8+i%8] < 1 && sahTehtid.get((i/8+1)*8+i%8)%10 < 1 || !beyazMiSiyahMi && tahta[(i/8+1)*8+i%8] > -1 && sahTehtid.get((i/8+1)*8+i%8)/10 < 1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8+1,tahta);}
				if ((i%8+1 < 8) && (beyazMiSiyahMi && tahta[(i/8)*8+i%8+1] < 1 && sahTehtid.get((i/8)*8+i%8+1)%10 < 1 || !beyazMiSiyahMi && tahta[(i/8)*8+i%8+1] > -1 && sahTehtid.get((i/8)*8+i%8+1)/10 < 1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8,tahta);}
				if ((i%8-1 > -1) && (beyazMiSiyahMi && tahta[(i/8)*8+i%8-1] < 1 && sahTehtid.get((i/8)*8+i%8-1)%10 < 1 || !beyazMiSiyahMi && tahta[(i/8)*8+i%8-1] > -1 && sahTehtid.get((i/8)*8+i%8-1)/10 < 1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8,tahta);}
				if ((i%8-1 > -1 && i/8+1 < 8) && (beyazMiSiyahMi && tahta[(i/8+1)*8+i%8-1] < 1 && sahTehtid.get((i/8+1)*8+i%8-1)%10 < 1 || !beyazMiSiyahMi && tahta[(i/8+1)*8+i%8-1] > -1 && sahTehtid.get((i/8+1)*8+i%8-1)/10 < 1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8+1,tahta);}
				if ((i%8+1 < 8 && i/8+1 < 8) && (beyazMiSiyahMi && tahta[(i/8+1)*8+i%8+1] < 1 && sahTehtid.get((i/8+1)*8+i%8+1)%10 < 1 || !beyazMiSiyahMi && tahta[(i/8+1)*8+i%8+1] > -1 && sahTehtid.get((i/8+1)*8+i%8+1)/10 < 1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8+1,tahta);}
				if ((i%8+1 < 8 && i/8-1 > -1) && (beyazMiSiyahMi && tahta[(i/8-1)*8+i%8+1] < 1 && sahTehtid.get((i/8-1)*8+i%8+1)%10 < 1 || !beyazMiSiyahMi && tahta[(i/8-1)*8+i%8+1] > -1 && sahTehtid.get((i/8-1)*8+i%8+1)/10 < 1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8-1,tahta);}
				if ((i%8-1 > -1 && i/8-1 > -1) && (beyazMiSiyahMi && tahta[(i/8-1)*8+i%8-1] < 1 && sahTehtid.get((i/8-1)*8+i%8-1)%10 < 1 || !beyazMiSiyahMi && tahta[(i/8-1)*8+i%8-1] > -1 && sahTehtid.get((i/8-1)*8+i%8-1)/10 < 1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8-1,tahta);}				
				//if (tahta[60] != 900) {if (p.isBeyazSahKisaRok()) {p.setBeyazSahKisaRok(false);}if (p.isBeyazSahUzunRok()) {p.setBeyazSahUzunRok(false);}}
				//if (tahta[4] != -900) {if (p.isSiyahSahKisaRok()) {p.setSiyahSahKisaRok(false);}if (p.isSiyahSahUzunRok()) {p.setSiyahSahUzunRok(false);}}
				if (p.getSiyahSahCekis() == 0 && (!beyazMiSiyahMi && p.isSiyahSahKisaRok() && tahta[7] == -5.0 && tahta[6] == 0.0 && tahta[5] == 0.0 && tahta[4] == -900 && sahTehtid.get(5)/10 < 1 && sahTehtid.get(6)/10 < 1) || p.getBeyazSahCekis() == 0 && (beyazMiSiyahMi && p.isBeyazSahKisaRok() && tahta[63] == 5.0 && tahta[62] == 0.0 && tahta[61] == 0.0 && tahta[60] == 900 && sahTehtid.get(62)%10 < 1 && sahTehtid.get(61)%10 < 1)) {rok++;motorHamle.add(2000000);}
				if (p.getSiyahSahCekis() == 0 && (!beyazMiSiyahMi && p.isSiyahSahUzunRok() && tahta[0] == -5.0 && tahta[1] == 0.0 && tahta[2] == 0.0 && tahta[4] == -900 && tahta[3] == 0.0 && sahTehtid.get(2)/10 < 1 && sahTehtid.get(3)/10 < 1) || p.getBeyazSahCekis() == 0 && (beyazMiSiyahMi && p.isBeyazSahUzunRok() && tahta[56] == 5.0 && tahta[57] == 0.0 && tahta[60] == 900 && tahta[58] == 0.0 && tahta[59] == 0.0 && sahTehtid.get(59)%10 < 1 && sahTehtid.get(58)%10 < 1)) {rok++;motorHamle.add(6000000);}				
			}
			//Knight movements, At hareketleri
			if (beyazMiSiyahMi && tahta[i] == 3 || !beyazMiSiyahMi && tahta[i] == -3) {
				if (acmazTest(i,(int)tahta[i],tahta,p) == 0) {
					if (!beyazMiSiyahMi && p.getSiyahSahCekis() == 0 || beyazMiSiyahMi && p.getBeyazSahCekis() == 0) {
						if (i/8-2 > -1 && i%8-1 > -1 && (beyazMiSiyahMi && tahta[(i/8-2)*8+i%8-1] < 1 || !beyazMiSiyahMi && tahta[(i/8-2)*8+i%8-1] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8-2,tahta);}				
						if (i/8-1 > -1 && i%8-2 > -1 && (beyazMiSiyahMi && tahta[(i/8-1)*8+i%8-2] < 1 || !beyazMiSiyahMi && tahta[(i/8-1)*8+i%8-2] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8-1,tahta);}
						if (i/8-1 > -1 && i%8+2 < 8 && (beyazMiSiyahMi && tahta[(i/8-1)*8+i%8+2] < 1 || !beyazMiSiyahMi && tahta[(i/8-1)*8+i%8+2] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8-1,tahta);}
						if (i/8-2 > -1 && i%8+1 < 8 && (beyazMiSiyahMi && tahta[(i/8-2)*8+i%8+1] < 1 || !beyazMiSiyahMi && tahta[(i/8-2)*8+i%8+1] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8-2,tahta);}
						if (i/8+2 < 8 && i%8+1 < 8 && (beyazMiSiyahMi && tahta[(i/8+2)*8+i%8+1] < 1 || !beyazMiSiyahMi && tahta[(i/8+2)*8+i%8+1] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8+2,tahta);}
						if (i/8+2 < 8 && i%8-1 > -1 && (beyazMiSiyahMi && tahta[(i/8+2)*8+i%8-1] < 1 || !beyazMiSiyahMi && tahta[(i/8+2)*8+i%8-1] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8+2,tahta);}
						if (i/8+1 < 8 && i%8-2 > -1 && (beyazMiSiyahMi && tahta[(i/8+1)*8+i%8-2] < 1 || !beyazMiSiyahMi && tahta[(i/8+1)*8+i%8-2] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8+1,tahta);}
						if (i/8+1 < 8 && i%8+2 < 8 && (beyazMiSiyahMi && tahta[(i/8+1)*8+i%8+2] < 1 || !beyazMiSiyahMi && tahta[(i/8+1)*8+i%8+2] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8+1,tahta);}
					} else if (!beyazMiSiyahMi && p.getSiyahSahCekis() == 1 || beyazMiSiyahMi && p.getBeyazSahCekis() == 1) {
						sahCekisArama(tahta[i],i,tahta,p,beyazMiSiyahMi);																													
					}
				}				
			}
			//Bishop movements, Fil hareketleri
			if (beyazMiSiyahMi && tahta[i] == 4 || !beyazMiSiyahMi && tahta[i] == -4) {
				if (acmazTest(i,(int)tahta[i],tahta,p) == 0) {
					if (!beyazMiSiyahMi && p.getSiyahSahCekis() == 0 || beyazMiSiyahMi && p.getBeyazSahCekis() == 0) {
						uzuntasHesap(true,i,tahta,beyazMiSiyahMi);
					} else if (!beyazMiSiyahMi && p.getSiyahSahCekis() == 1 || beyazMiSiyahMi && p.getBeyazSahCekis() == 1) {
						sahCekisArama(tahta[i],i,tahta,p,beyazMiSiyahMi);																													
					}
				}								
			}
			//Rook movements, Kale hareketleri
			if (beyazMiSiyahMi && tahta[i] == 5 || !beyazMiSiyahMi && tahta[i] == -5) {
				if (acmazTest(i,(int)tahta[i],tahta,p) == 0) {					
					if (!beyazMiSiyahMi && p.getSiyahSahCekis() == 0 || beyazMiSiyahMi && p.getBeyazSahCekis() == 0) {						
						uzuntasHesap(false,i,tahta,beyazMiSiyahMi);
					} else if (!beyazMiSiyahMi && p.getSiyahSahCekis() == 1 || beyazMiSiyahMi && p.getBeyazSahCekis() == 1) {
						sahCekisArama(tahta[i],i,tahta,p,beyazMiSiyahMi);																													
					}
				}									
			}
			//Queen movements, Vezir hareketleri
			if (beyazMiSiyahMi && tahta[i] == 9 || !beyazMiSiyahMi && tahta[i] == -9) {				
				if (acmazTest(i,(int)tahta[i],tahta,p) == 0) {					
					if (!beyazMiSiyahMi && p.getSiyahSahCekis() == 0 || beyazMiSiyahMi && p.getBeyazSahCekis() == 0) {						
						uzuntasHesap(false,i,tahta,beyazMiSiyahMi);
						uzuntasHesap(true,i,tahta,beyazMiSiyahMi);
					} else if (!beyazMiSiyahMi && p.getSiyahSahCekis() == 1 || beyazMiSiyahMi && p.getBeyazSahCekis() == 1) {
						sahCekisArama(tahta[i],i,tahta,p,beyazMiSiyahMi);										
					}
				} 						
			}
			//Pawn movements, Piyon hareketleri
			if (beyazMiSiyahMi && tahta[i] == 1 || !beyazMiSiyahMi && tahta[i] == -1) {				
				if (acmazTest(i,(int)tahta[i],tahta,p) == 0) {					
					if (!beyazMiSiyahMi && p.getSiyahSahCekis() == 0 || beyazMiSiyahMi && p.getBeyazSahCekis() == 0) {						
						if (i/8-1 > 0 && tahta[i] == 1 || i/8+1 < 7 && tahta[i] == -1) {if (tahta[i] == 1 && tahta[(i/8-1)*8+i%8] == 0.0 || tahta[i] == -1 && tahta[(i/8+1)*8+i%8] == 0.0) {if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else {motorHamle.add(1*100000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}}}
						if (i/8 == 6 && tahta[i] == 1 || i/8 == 1 && tahta[i] == -1) {if (tahta[i] == 1 && tahta[(i/8-2)*8+i%8] == 0.0 && tahta[(i/8-1)*8+i%8] == 0.0 || tahta[i] == -1 && tahta[(i/8+2)*8+i%8] == 0.0 && tahta[(i/8+1)*8+i%8] == 0.0)  {if (tahta[i] > 0) {motorHamle.add(1*1000000+(i/8-2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else {motorHamle.add(1*1000000+(i/8+2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}}}
						if (i%8-1 > -1 && (tahta[i] == 1 && i/8-1 > 0 && tahta[(i/8-1)*8+i%8-1] < 0 || tahta[i] == -1 && i/8+1 < 7 && tahta[(i/8+1)*8+i%8-1] > 0)) {captures++; if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else {motorHamle.add(1*100000+(i/8+1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}}
						if (i%8+1 < 8 && (tahta[i] == 1 && i/8-1 > 0 && tahta[(i/8-1)*8+i%8+1] < 0 || tahta[i] == -1 && i/8+1 < 7 && tahta[(i/8+1)*8+i%8+1] > 0)) {captures++;if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else {motorHamle.add(1*100000+(i/8+1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}}						
						if (i/8 == 1 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8] == 0.0) {motorHamle.add(9*100000000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*10000000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*10000000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*10000000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
						if (i/8 == 6 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8] == 0.0) {motorHamle.add(9*100000000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*10000000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*10000000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*10000000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
						if (i%8-1 > -1 && (i/8 == 1 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8-1] < 0 || i/8 == 6 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8-1] > 0)) {if (tahta[i] > 0) {motorHamle.add(9*100000000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*10000000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*10000000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*10000000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);} else {motorHamle.add(9*100000000+(i/8+1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*10000000+(i/8+1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*10000000+(i/8+1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*10000000+(i/8+1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}}
						if (i%8+1 < 8 && (i/8 == 1 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8+1] < 0 || i/8 == 6 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8+1] > 0)) {if (tahta[i] > 0) {motorHamle.add(9*100000000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*10000000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*10000000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*10000000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);} else {motorHamle.add(9*100000000+(i/8+1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*10000000+(i/8+1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*10000000+(i/8+1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*10000000+(i/8+1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}}
						if (p.getGecerkenAlma() > -1 && Math.abs(p.getGecerkenAlma()-i%8) == 1 && (!beyazMiSiyahMi && i/8 == 4 || beyazMiSiyahMi && i/8 == 3) && gecerkenAlmaAcmaz(i,tahta,p)) {gecerkenAl++;if (tahta[i] > 0) {motorHamle.add(1*10000000+(i/8-1)*10000+(p.getGecerkenAlma())*1000+(i/8)*100+(i%8)*10);} else {motorHamle.add(1*10000000+(i/8+1)*10000+(p.getGecerkenAlma())*1000+(i/8)*100+(i%8)*10);}}
					} else if (!beyazMiSiyahMi && p.getSiyahSahCekis() == 1 || beyazMiSiyahMi && p.getBeyazSahCekis() == 1) {						
						sahCekisArama(tahta[i],i,tahta,p,beyazMiSiyahMi);																													
					}		
				}							
			}
		}
	}
	public boolean gecerkenAlmaAcmaz(int i, int[] tahta, PerformansTest p) {		
		int tas = tahta[(i/8)*8+p.getGecerkenAlma()];
		tahta[(i/8)*8+p.getGecerkenAlma()] = 0;
		if (acmazTest(i,tahta[i],tahta,p) == 0) {
			tahta[(i/8)*8+p.getGecerkenAlma()] = tas;
			return true;					
		} else {			
			tahta[(i/8)*8+p.getGecerkenAlma()] = tas;
			return false;
		}		
	}	
	public int acmazTest(int i, int j, int[] tahta, PerformansTest p) {		
		if (j > 0 && p.getBeyazSahKonum()%10 == i%8 && p.getBeyazSahKonum()/10%10 > i/8 || j < 0 && p.getSiyahSahKonum()%10 == i%8 && p.getSiyahSahKonum()/10%10 > i/8) {    		
    		if (j > 1) {return acmazKontrol(j,1,i,(p.getBeyazSahKonum()/10%10 - i/8)-1,tahta,p);} else if (j < -1) {return acmazKontrol(j,1,i,(p.getSiyahSahKonum()/10%10 - i/8)-1,tahta,p);}
    		else {if (j == 1) {return piyonAcmaz(j,1,i,(p.getBeyazSahKonum()/10%10 - i/8)-1,tahta,p);} else {return piyonAcmaz(j,13,i,(p.getSiyahSahKonum()/10%10 - i/8)-1,tahta,p);}}
    	}
    	else if (j > 0 && p.getBeyazSahKonum()%10 == i%8 && p.getBeyazSahKonum()/10%10 < i/8 || j < 0 && p.getSiyahSahKonum()%10 == i%8 && p.getSiyahSahKonum()/10%10 < i/8) {
    		if (j > 1) {return acmazKontrol(j,2,i,i/8-p.getBeyazSahKonum()/10%10-1,tahta,p);} else if (j < -1)  {return acmazKontrol(j,2,i,i/8-p.getSiyahSahKonum()/10%10-1,tahta,p);}
    		else {if (j == 1) {return piyonAcmaz(j,2,i,i/8-p.getBeyazSahKonum()/10%10-1,tahta,p);} else {return piyonAcmaz(j,11,i,i/8-p.getSiyahSahKonum()/10%10-1,tahta,p);}}
    	}
    	else if (j > 0 && p.getBeyazSahKonum()/10%10 == i/8 && p.getBeyazSahKonum()%10 > i%8 || j < 0 && p.getSiyahSahKonum()/10%10 == i/8 && p.getSiyahSahKonum()%10 > i%8) {
    		if (j > 1) {return acmazKontrol(j,3,i,(p.getBeyazSahKonum()%10 - i%8)-1,tahta,p);} else if (j < -1)  {return acmazKontrol(j,3,i,(p.getSiyahSahKonum()%10 - i%8)-1,tahta,p);}
    		else {
    			if (j == 1) {return piyonAcmaz(j,3,i,(p.getBeyazSahKonum()%10 - i%8)-1,tahta,p);} else {return piyonAcmaz(j,3,i,(p.getSiyahSahKonum()%10 - i%8)-1,tahta,p);}
    		}
    	}
    	else if (j > 0 && p.getBeyazSahKonum()/10%10 == i/8 && p.getBeyazSahKonum()%10 < i%8 || j < 0 && p.getSiyahSahKonum()/10%10 == i/8 && p.getSiyahSahKonum()%10 < i%8) {    		
    		if (j > 1) {return acmazKontrol(j,4,i,(i%8-p.getBeyazSahKonum()%10)-1,tahta,p);} else if (j < -1)  {return acmazKontrol(j,4,i,(i%8-p.getSiyahSahKonum()%10)-1,tahta,p);}
    		else {
    			if (j == 1) {return piyonAcmaz(j,4,i,(i%8-p.getBeyazSahKonum()%10)-1,tahta,p);} else {return piyonAcmaz(j,4,i,(i%8-p.getSiyahSahKonum()%10)-1,tahta,p);}
    		}
    	}
    	else if (j > 0 && ((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10)) %9 == i%9 && p.getBeyazSahKonum()/10%10 > i/8 || j < 0 && ((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)) % 9 == i%9 && p.getSiyahSahKonum()/10%10 > i/8 && p.getSiyahSahKonum()%10 > i%8) {    		    	
    		if (j > 1) {return acmazKontrol(j,5,i,((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10)-i)/9-1,tahta,p);} else if (j < -1)  {return acmazKontrol(j,5,i,((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)-i)/9-1,tahta,p);}
    		else {    			
    			if (j == 1) {return piyonAcmaz(j,5,i,((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10)-i)/9-1,tahta,p);} else {return piyonAcmaz(j,10,i,((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)-i)/9-1,tahta,p);}
    		}
    	}
    	else if (j > 0 && ((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10)) %9 == i%9 && p.getBeyazSahKonum()/10%10 < i/8 || j < 0 && ((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)) % 9 == i%9 && p.getSiyahSahKonum()/10%10 < i/8 && p.getSiyahSahKonum()%10 < i%8) {    		
    		if (j > 1) {return acmazKontrol(j,6,i,(i-((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10)))/9-1,tahta,p);} else if (j < -1)  {return acmazKontrol(j,6,i,(i-((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)))/9-1,tahta,p);}
    		else {    			
    			if (j == -1) {return piyonAcmaz(j,6,i,(i-((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)))/9-1,tahta,p);} else {return piyonAcmaz(j,12,i,(i-(p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10))/9-1,tahta,p);}
    		}
    	}
    	else if (j > 0 && ((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10)) % 7 == i%7 && p.getBeyazSahKonum()/10%10 > i/8 && p.getBeyazSahKonum()%10 < i%8 || j < 0 && ((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)) % 7 == i%7 && p.getSiyahSahKonum()/10%10 > i/8 && p.getSiyahSahKonum()%10 < i%8) {    		
    		if (j > 1) {return acmazKontrol(j,7,i,(((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10))-i)/7-1,tahta,p);} else if (j < -1)  {return acmazKontrol(j,7,i,(((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10))-i)/7-1,tahta,p);}    		
    		else {
    			if (j == 1) {return piyonAcmaz(j,7,i,(((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10))-i)/7-1,tahta,p);} else {return piyonAcmaz(j,9,i,(((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10))-i)/7-1,tahta,p);}
    		}
    	}
    	else if (j > 0 && ((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10)) % 7 == i%7 && p.getBeyazSahKonum()/10%10 < i/8 && p.getBeyazSahKonum()%10 > i%8 || j < 0 && ((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)) % 7 == i%7 && p.getSiyahSahKonum()/10%10 < i/8 && p.getSiyahSahKonum()%10 > i%8) {    		
    		if (j > 0) {return acmazKontrol(j,8,i,(i-((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10)))/7-1,tahta,p);} else if (j < -1)  {return acmazKontrol(j,8,i,(i-((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)))/7-1,tahta,p);}
    		else {
    			if (j == -1) {return piyonAcmaz(j,8,i,(i-((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)))/7-1,tahta,p);}
    		}
    	}   	     	    
    	return 0;
    }
	public int acmazKontrol(int tasDeger,int hangiYon,int i,int sahTaraf,int[] tahta, PerformansTest k) {		
    	switch(hangiYon) {    	
    		case 1:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8] != 0) {
    					return 0;
    				}    				
    			}
    			for (int y = i/8-1,x = i%8;y > -1;y--) {    				    				
    				if ((tasDeger > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tasDeger < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0))) {    					
    					return 0;
    				}    				    				
    				if (tasDeger > 1 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tasDeger < -1 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {    					
    					if (tasDeger > 4 || tasDeger < -4) {    						
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;
    				}  				
    			}    			
    			break;
    		case 2:      			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8] != 0) {
    					return 0;
    				}    				
    			}
    			for (int y = i/8+1,x = i%8;y < 8;y++) {    				    				
    				if ((tasDeger > 0 && (tahta[y*8+x] != -5 && tahta[y*8+x] != -9 && tahta[y*8+x] != 0) || tasDeger < 0 && (tahta[y*8+x] != 5 && tahta[y*8+x] != 9 && tahta[y*8+x] != 0))) {    					
    					return 0;
    				}    				    				
    				if (tasDeger > 1 && (tahta[y*8+x] == -5 || tahta[y*8+x] == -9) || tasDeger < -1 && (tahta[y*8+x] == 5 || tahta[y*8+x] == 9)) {
    					if (tasDeger > 4 || tasDeger < -4) {
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;
    				}
    			}
    			break;
    		case 3:    			
    			for (int p = 1;p <= sahTaraf;p++) {    				
    				if (tahta[(i/8)*8+i%8+p] != 0) {    					
    					return 0;
    				}   				    				
    			}
    			for (int y = i/8,x = i%8-1;x > -1;x--) {    				    				
    				if ((tahta[i] > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tasDeger < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0))) {    					
    					return 0;
    				}    				    				
    				if (tasDeger > 1 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tasDeger < -1 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {
    					if (tasDeger > 4 || tasDeger < -4) {
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;
    				} 
    			}
    			break;
    		case 4:    			
    			for (int p = 1;p <= sahTaraf;p++) {    				
    				if (tahta[(i/8)*8+i%8-p] != 0) {    					
    					return 0;
    				}    				
    			}
    			for (int y = i/8,x = i%8+1;x < 8;x++) {    				
    				if ((tasDeger > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tasDeger < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0))) {    					
    					return 0;
    				}    		    				    				
    				if (tasDeger > 1 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tasDeger < -1 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {
    					if (tasDeger > 4 || tasDeger < -4) {    						
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);    						
    					}    					
    					return 1;
    				}    						    			
    			}
    			break;    			
    		case 5:    		
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8+p] != 0) {
    					return 0;
    				}    				
    			}
    			for (int y = i/8-1,x = i%8-1;x > -1 && y > -1;y--,x--) {    				    				
    				if ((tasDeger > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tasDeger < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0))) {    					
    					return 0;
    				}    				    				
    				if (tasDeger > 1 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tasDeger < -1 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {    				
    					if ((tasDeger == 4 || tasDeger == 9) || (tasDeger == -4 || tasDeger == -9)) {    						
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;
    				}
    			}    			
    			break;    			    		
    		case 6:    			    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8-p] != 0) {
    					return 0;
    				}
    				
    			}
    			for (int y = i/8+1,x = i%8+1;x < 8 && y < 8;y++,x++) {    				    				
    				if ((tasDeger > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tasDeger < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0))) {    					
    					return 0;
    				}    				    				
    				if (tasDeger > 1 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tasDeger < -1 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {    					
    					if ((tasDeger == 4 || tasDeger == 9) || (tasDeger == -4 || tasDeger == -9)) {
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;
    				}
    			}
    			break;
    		case 7:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8-p] != 0) {
    					return 0;
    				}    				
    			}
    			for (int y = i/8-1,x = i%8+1;x < 8 && y > -1;y--,x++) {    				    				
    				if ((tasDeger > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tasDeger < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) ) {    					
    					return 0;
    				}    				    				
    				if (tasDeger > 1 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tasDeger < -1 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {
    					if ((tasDeger == 4 || tasDeger == 9) || (tasDeger == -4 || tasDeger == -9)) {
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;
    				} 
    			}
    			break;
    		case 8:    			
    			for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8+p] != 0) {
    					return 0;
    				}    				
    			}
    			for (int y = i/8+1,x = i%8-1;x > -1 && y < 8;y++,x--) {    				    				
    				if ((tasDeger > 0 && (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tasDeger < 0 && (tahta[(y*8+x)] != 4 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0))) {    					
    					return 0;
    				}    				    				
    				if (tasDeger > 1 && (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) || tasDeger < -1 && (tahta[(y*8+x)] == 4 || tahta[(y*8+x)] == 9)) {
    					if ((tasDeger == 4 || tasDeger == 9) || (tasDeger == -4 || tasDeger == -9)) {
    						acmazHesap(tasDeger,hangiYon,i,y,x,sahTaraf,tahta,k);
    					}    					
    					return 1;    					
    				} 
    			}    			
    			break;
    	}
    	return 0;
    }
	public void acmazHesap(int tasDeger, int hangiYon, int i, int y, int x, int sahTaraf, int[] tahta, PerformansTest k) {		
		if (k.getBeyazSahCekis() == 0 && k.getSiyahSahCekis() == 0) {			
			switch(hangiYon) {			
			case 1:			
				for (int p = i/8-1,j = i%8;p >= y;p--) {				
					arayuzCizilecekHamleAyarla(tasDeger,i,j,p,tahta);				
				}
				for (int p = 1;p <= sahTaraf;p++) {
					arayuzCizilecekHamleAyarla(tasDeger,i,i%8,i/8+p,tahta);				
				}
				break;
			case 2:
				for (int p = i/8+1,j = i%8;p <= y;p++) {				
					arayuzCizilecekHamleAyarla(tasDeger,i,j,p,tahta);				
				}
				for (int p = 1;p <= sahTaraf;p++) {
					arayuzCizilecekHamleAyarla(tasDeger,i,i%8,i/8-p,tahta);				
				}
				break;
			case 3:
				for (int p = i/8,j = i%8-1;j >= x;j--) {
					arayuzCizilecekHamleAyarla(tasDeger,i,j,p,tahta);				
				}
				for (int p = 1;p <= sahTaraf;p++) {
					arayuzCizilecekHamleAyarla(tasDeger,i,i%8+p,i/8,tahta);				
				}
				break;
			case 4:
				for (int p = i/8,j = i%8+1;j <= x;j++) {
					arayuzCizilecekHamleAyarla(tasDeger,i,j,p,tahta);				
				}			
				for (int p = 1;p <= sahTaraf;p++) {
					arayuzCizilecekHamleAyarla(tasDeger,i,i%8-p,i/8,tahta);				
				}
				break;
			case 5:
				for (int p = i/8-1,j = i%8-1;j >= x && p >= y;p--,j--) {			
					arayuzCizilecekHamleAyarla(tasDeger,i,j,p,tahta);				
				}			
				for (int p = 1;p <= sahTaraf;p++) {
					arayuzCizilecekHamleAyarla(tasDeger,i,i%8+p,i/8+p,tahta);				
				}
				break;
			case 6:
				for (int p = i/8+1,j = i%8+1;j <= x && p <= y;p++,j++) {			
					arayuzCizilecekHamleAyarla(tasDeger,i,j,p,tahta);				
				}
				for (int p = 1;p <= sahTaraf;p++) {
					arayuzCizilecekHamleAyarla(tasDeger,i,i%8-p,i/8-p,tahta);				
				}
				break;
			case 7:			
				for (int p = i/8-1,j = i%8+1;j <= x && p >= y;p--,j++) {			
					arayuzCizilecekHamleAyarla(tasDeger,i,j,p,tahta);				
				}
				for (int p = 1;p <= sahTaraf;p++) {
					arayuzCizilecekHamleAyarla(tasDeger,i,i%8-p,i/8+p,tahta);				
				}
				break;
			case 8:
				for (int p = i/8+1,j = i%8-1;j >= x && p <= y;p++,j--) {			
					arayuzCizilecekHamleAyarla(tasDeger,i,j,p,tahta);				
				}
				for (int p = 1;p <= sahTaraf;p++) {
					arayuzCizilecekHamleAyarla(tasDeger,i,i%8+p,i/8-p,tahta);				
				}
				break;
			}
		}
								
	}
	 public int piyonAcmaz(int tasDeger, int hangiYon, int i, int sahTaraf, int[] tahta, PerformansTest k) {			 
	    	switch (hangiYon) {
	    	case 1:
	    		for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8+p)*8+i%8] != 0) {
    					return 0;
    				}    				
    			}
    			for (int y = i/8-1,x = i%8;y > -1;y--) {    				    				
    				if (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) {
    					if (k.getBeyazSahCekis() == 0) {
    						if (i/8-1 > 0) {if (tahta[(i/8-1)*8+i%8] == 0.0) {motorHamle.add(1*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}}
    						if (i/8 == 6) {if (tahta[(i/8-2)*8+i%8] == 0.0 && tahta[(i/8-1)*8+i%8] == 0.0)  {motorHamle.add(1*1000000+(i/8-2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}}
    					}    					
    					return 1;
    				}  				
    			}
	    		break;
	    	case 2:	    	
	    		for (int p = 1;p <= sahTaraf;p++) {
    				if (tahta[(i/8-p)*8+i%8] != 0) {    					
    					return 0;
    				}    				
    			}
    			for (int y = i/8+1,x = i%8;y < 8;y++) {    				
    				if ((tahta[y*8+x] != -5 && tahta[y*8+x] != -9 && tahta[y*8+x] != 0)) {    					
    					return 0;
    				}    				    				
    				if ((tahta[y*8+x] == -5 || tahta[y*8+x] == -9)) {
    					if (k.getBeyazSahCekis() == 0) {
    						if (i/8-1 > 0) {if (tahta[(i/8-1)*8+i%8] == 0.0) {motorHamle.add(1*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}}
    						if (i/8 == 6) {if (tahta[(i/8-2)*8+i%8] == 0.0 && tahta[(i/8-1)*8+i%8] == 0.0)  {motorHamle.add(1*1000000+(i/8-2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}}
    					}    					
    					return 1;
    				}
    			}
	    		break;
	    	case 3:	    		
	    		for (int p = 1;p <= sahTaraf;p++) {    			
	    			if (tahta[(i/8)*8+i%8+p] != 0) {	    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8,x = i%8-1;x > -1;x--) {    				    				
    				if (tasDeger > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tasDeger < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) {    					
    					return 0;
    				}    				    				
    				if (tasDeger > 0 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tasDeger < 0 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {    					
    					return 1;
    				} 
    			}
	    		break;
	    	case 4:	  	    			    	
	    		for (int p = 1;p <= sahTaraf;p++) {    			
	    			if (tahta[(i/8)*8+i%8-p] != 0) {    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8,x = i%8+1;x < 8;x++) {    				
    				if ((tasDeger > 0 && (tahta[(y*8+x)] != -5 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) || tasDeger < 0 && (tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0))) {    					
    					return 0;
    				}    		    				    				
    				if (tasDeger > 0 && (tahta[(y*8+x)] == -5 || tahta[(y*8+x)] == -9) || tasDeger < 0 && (tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {    					    					
    					return 1;
    				}
    						    				
    			}
	    		break;	    		
	    	case 5:	 	    		
	    		for (int p = 1;p <= sahTaraf;p++) {    			
	    			if (tahta[(i/8+p)*8+i%8+p] != 0) {    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8-1,x = i%8-1;x > -1 && y > -1;y--,x--) {
	    			if (k.getBeyazSahCekis() == 0 && (y == i/8-1 && x == i%8-1 && i/8 > 1) && (tahta[y*8+x] == -9 || tahta[y*8+x] == -4)) {	    				
	    				arayuzCizilecekHamleAyarla(tasDeger,i,x,y,tahta);
	    				return 1;
	    			} else if (!(tahta[y*8+x] == -9 || tahta[y*8+x] == -4) && tahta[y*8+x] != 0.0) {	    				
	    				return 0;
	    			} else if ((tahta[y*8+x] == -9 || tahta[y*8+x] == -4) && i/8 > 1) {
	    				return 1;
	    			}    			
	    		}
	    		break;
	    	case 6:	 	    			    		
	    		for (int p = 1;p <= sahTaraf;p++) {    			
	    			if (tahta[(i/8-p)*8+i%8-p] != 0) {    				
	    				return 0;
	    			}
	    		}	    		
	    		for (int y = i/8+1,x = i%8+1;x < 8 && y < 8;y++,x++) {	    			
	    			if (k.getSiyahSahCekis() == 0 && (y == i/8+1 && x == i%8+1 && i/8 < 6) && (tahta[y*8+x] == 9 || tahta[y*8+x] == 4)) {
	    				arayuzCizilecekHamleAyarla(tasDeger,i,x,y,tahta);	    				
	    				return 1;
	    			} else if (!(tahta[y*8+x] == 9 || tahta[y*8+x] == 4) && tahta[y*8+x] != 0.0) {
	    				return 0;
	    			} else if ((tahta[y*8+x] == 9 || tahta[y*8+x] == 4) && i/8 < 6) {	    				
	    				return 1;
	    			}
	    		}
	    		break;    		
	    	case 7:	 	    			    		
	    		for (int p = 1;p <= sahTaraf;p++) {    			
	    			if (tahta[(i/8+p)*8+i%8-p] != 0) {	    					    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8-1,x = i%8+1;x < 8 && y > -1;y--,x++) {	    			
	    			if (k.getBeyazSahCekis() == 0 && ((y == i/8-1 && x == i%8+1)) && (tahta[y*8+x] == -9 || tahta[y*8+x] == -4)) {
	    				arayuzCizilecekHamleAyarla(tasDeger,i,x,y,tahta);
	    				return 1;
	    			} else if (!(tahta[y*8+x] == -9 || tahta[y*8+x] == -4) && tahta[y*8+x] != 0.0) {
	    				return 0;
	    			} else if ((tahta[y*8+x] == -9 || tahta[y*8+x] == -4) && i/8 > 1) {
	    				return 1;
	    			}    		
	    		}    		
	    		break;
	    	case 8:		
	    		for (int p = 1;p <= sahTaraf;p++) {	    			
	    			if (tahta[(i/8-p)*8+i%8+p] != 0) {    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8+1,x = i%8-1;x > -1 && y < 8;y++,x--) {	    			
	    			if (k.getSiyahSahCekis() == 0 && ((y == i/8+1 && x == i%8-1)) && (tahta[y*8+x] == 9 || tahta[y*8+x] == 4)) {	    				
	    				arayuzCizilecekHamleAyarla(tasDeger,i,x,y,tahta);
	    				return 1;
	    			} else if (!(tahta[y*8+x] == 9 || tahta[y*8+x] == 4) && tahta[y*8+x] != 0.0) {
	    				return 0;
	    				
	    			} else if ((tahta[y*8+x] == 9 || tahta[y*8+x] == 4) && i/8 < 6) {
	    				return 1;
	    			}
	    		}
	    		break;
	    	case 9:
	    		for (int p = 1;p <= sahTaraf;p++) {    			
	    			if (tahta[(i/8+p)*8+i%8-p] != 0) {	    					    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8-1,x = i%8+1;x < 8 && y > -1;y--,x++) {
	    			if (((y == i/8-1 && x == i%8+1)) && (tahta[y*8+x] == 9 || tahta[y*8+x] == 4)) {
	    				return 1;
	    			} else if (tahta[y*8+x] == 9 || tahta[y*8+x] == 4) {
	    				return 1;
	    			} else if (!(tahta[y*8+x] == 9 || tahta[y*8+x] == 4) && tahta[y*8+x] != 0.0) {
	    				return 0;
	    			}
	    		}
	    		break;
	    	case 10:		    		
	    		for (int p = 1;p <= sahTaraf;p++) {    			
	    			if ((i/8+p)*8+i%8+p > -1 && tahta[(i/8+p)*8+i%8+p] != 0) {	    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8-1,x = i%8-1;x < 8 && y > -1 && y*8+x > -1;y--,x--) {
	    			if (((y == i/8-1 && x == i%8-1)) && (tahta[y*8+x] == 9 || tahta[y*8+x] == 4)) {
	    				return 1;
	    			}
	    			else if (tahta[y*8+x] == 9 || tahta[y*8+x] == 4) {
	    				return 1;
	    			} else if (!(tahta[y*8+x] == 9 || tahta[y*8+x] == 4) && tahta[y*8+x] != 0.0) {
	    				return 0;
	    			}
	    		}
	    		break;
	    	case 11:	    		
	    		for (int p = 1;p <= sahTaraf;p++) {	    			
	    			if (tahta[(i/8-p)*8+i%8] != 0) {	    					    				
	    				return 0;
	    			}	    			
	    		}
	    		for (int y = i/8+1,x = i%8;y < 8;y++) {		    				
    				if ((tahta[(y*8+x)] != 5 && tahta[(y*8+x)] != 9 && tahta[(y*8+x)] != 0)) {    					
    					return 0;
    				}    				    				
    				if ((tahta[(y*8+x)] == 5 || tahta[(y*8+x)] == 9)) {
    					if (k.getSiyahSahCekis() == 0) {
    						if (i/8+1 < 7 && tahta[(i/8+1)*8+i%8] == 0.0) {motorHamle.add(1*100000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
    						if (i/8 == 1 && tahta[(i/8+2)*8+i%8] == 0.0 && tahta[(i/8+1)*8+i%8] == 0.0) {motorHamle.add(1*1000000+(i/8+2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
    					}    					
    					return 1;
    				}  				
    			}
	    		break;
	    	case 12:	    		
	    		for (int p = 1;p <= sahTaraf;p++) {	    			
	    			if (tahta[(i/8-p)*8+i%8-p] != 0) {	    					    				
	    				return 0;
	    			}	    			
	    		}
	    		for (int y = i/8+1,x = i%8+1;y < 8 && x < 8;y++,x++) {	    			
	    			if (tahta[(y*8+x)] != -4 && tahta[(y*8+x)] != -9 && tahta[(y*8+x)] != 0) {    					
    					return 0;
    				}    				    				
    				if (tahta[(y*8+x)] == -4 || tahta[(y*8+x)] == -9) {
    					if (k.getSiyahSahCekis() == 0) {
    						//if (i/8+1 < 7 && tahta[(i/8+1)*8+i%8] == 0.0) {motorHamle.add(1*100000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
    						//if (i/8 == 1 && tahta[(i/8+2)*8+i%8] == 0.0 && tahta[(i/8+1)*8+i%8] == 0.0) {motorHamle.add(1*1000000+(i/8+2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
    					}    					
    					return 1;
    				}
	    		}
	    		break;
	    	case 13:	    		
	    		for (int p = 1;p <= sahTaraf;p++) {	    			
	    			if (tahta[(i/8+p)*8+i%8] != 0) {	    				
	    				return 0;
	    			}
	    		}
	    		for (int y = i/8-1,x = i%8;y > -1;y--) {	    				
	    			if (tahta[y*8+x] == 9 || tahta[y*8+x] == 5) {
    					if (k.getSiyahSahCekis() == 0) {
    						if (i/8 == 1 && tahta[(i/8+2)*8+i%8] == 0.0 && tahta[(i/8+1)*8+i%8] == 0.0) {motorHamle.add(1*1000000+(i/8+2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
	    					if (i/8+1 < 7 && tahta[(i/8+1)*8+i%8] == 0.0) {motorHamle.add(1*100000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}	    					
    					}    				
	    				return 1;
	    			} else if (!(tahta[y*8+x] == 9 || tahta[y*8+x] == 5) && tahta[y*8+x] != 0.0) {		    				
	    				return 0;
	    			}
	    		}
	    		
	    		break;
	    	}	    	
	    	return 0;
	    }
	public void sahTehtid() {     	    	
   	 sahTehtid = new ArrayList<Integer>(
				  Arrays.asList(0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0,
						  		0,0,0,0,0,0,0,0
						   		));
   	
   }
	public void sahTehtid(boolean j, int[] tahta, PerformansTest p, boolean beyazMiSiyahMi) {		
    	sahTehtid();    	
    	for (byte i = 0;i < tahta.length;i++) {    		
    		if (Math.abs(tahta[i]) == 900) {     			    		
        		if (i/8-1 > -1) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8,i,tahta,p);}
        		if (i/8+1 <  8) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8,i,tahta,p);}
        		if (i%8+1 <  8) {sahTehtidAyarla(tahta[i], ((i/8))*8+i%8+1,i,tahta,p);}
        		if (i%8-1 > -1) {sahTehtidAyarla(tahta[i], ((i/8))*8+i%8-1,i,tahta,p);}
        		if (i%8-1 > -1 && i/8+1 < 8) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8-1,i,tahta,p);}
        		if (i%8+1 < 8 && i/8+1 < 8) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8+1,i,tahta,p);}
        		if (i%8+1 < 8 && i/8-1 > -1) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8+1,i,tahta,p);}
        		if (i%8-1 > -1 && i/8-1 > -1) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8-1,i,tahta,p);}
        	}
    		if (Math.abs(tahta[i]) == 3) {
    			if (i/8-2 > -1 && i%8-1 > -1) {sahTehtidAyarla(tahta[i], ((i/8)-2)*8+i%8-1,i,tahta,p);}	
				if (i/8-1 > -1 && i%8-2 > -1) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8-2,i,tahta,p);}
				if (i/8-1 > -1 && i%8+2 < 8) {sahTehtidAyarla(tahta[i], ((i/8)-1)*8+i%8+2,i,tahta,p);}
				if (i/8-2 > -1 && i%8+1 < 8) {sahTehtidAyarla(tahta[i], ((i/8)-2)*8+i%8+1,i,tahta,p);}
				if (i/8+2 < 8 && i%8+1 < 8) {sahTehtidAyarla(tahta[i], ((i/8)+2)*8+i%8+1,i,tahta,p);}
				if (i/8+2 < 8 && i%8-1 > -1) {sahTehtidAyarla(tahta[i], ((i/8)+2)*8+i%8-1,i,tahta,p);}
				if (i/8+1 < 8 && i%8-2 > -1) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8-2,i,tahta,p);}
				if (i/8+1 < 8 && i%8+2 < 8) {sahTehtidAyarla(tahta[i], ((i/8)+1)*8+i%8+2,i,tahta,p);}
    		}
    		if (Math.abs(tahta[i]) == 4) {
    			sahTehtidUzunTasHesap(true,i,tahta,p,beyazMiSiyahMi);
    		}
    		if (Math.abs(tahta[i]) == 5) {
    			sahTehtidUzunTasHesap(false,i,tahta,p,beyazMiSiyahMi);
    		}
    		if (Math.abs(tahta[i]) == 9) {
    			sahTehtidUzunTasHesap(true,i,tahta,p,beyazMiSiyahMi);
    			sahTehtidUzunTasHesap(false,i,tahta,p,beyazMiSiyahMi);
    		}
    		if (Math.abs(tahta[i]) == 1) {    			
    			if (i%8-1 > -1 && (beyazMiSiyahMi && i/8-1 > -1 || !beyazMiSiyahMi && i/8+1 < 8)) {if (tahta[i] == 1) {sahTehtidAyarla(tahta[i], (i/8-1)*8+i%8-1,i,tahta,p);}else{sahTehtidAyarla(tahta[i], (i/8+1)*8+i%8-1,i,tahta,p);}}    			
				if (i%8+1 < 8  && (beyazMiSiyahMi && i/8-1 > -1 || !beyazMiSiyahMi && i/8+1 < 8)) {if (tahta[i] == 1) {sahTehtidAyarla(tahta[i], (i/8-1)*8+i%8+1,i,tahta,p);}else{sahTehtidAyarla(tahta[i], (i/8+1)*8+i%8+1,i,tahta,p);}}
    		}    		
    	}    	
    }
	 public void sahCekisArama(double tas, int i, int[] tahta, PerformansTest k, boolean beyazMiSiyahMi) {		 
		 LinkedList<Integer> mudaheleKareleri = k.sahMudaheleKareAl();
		 for (int p = 0;p < mudaheleKareleri.size();p++) {			 
	    		if (Math.abs(tas) == 3) {	    			
	        		if (i/8-2 > -1 && i%8-1 > -1 && (i/8-2)*8+i%8-1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8-1,i/8-2,tahta);}	
	    			if (i/8-1 > -1 && i%8-2 > -1 && (i/8-1)*8+i%8-2 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8-2,i/8-1,tahta);}
	    			if (i/8-1 > -1 && i%8+2 < 8 && (i/8-1)*8+i%8+2 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8+2,i/8-1,tahta);}
	    			if (i/8-2 > -1 && i%8+1 < 8 && (i/8-2)*8+i%8+1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8+1,i/8-2,tahta);}
	    			if (i/8+2 < 8 && i%8+1 < 8 && (i/8+2)*8+i%8+1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8+1,i/8+2,tahta);}
	    			if (i/8+2 < 8 && i%8-1 > -1 && (i/8+2)*8+i%8-1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8-1,i/8+2,tahta);}
	    			if (i/8+1 < 8 && i%8-2 > -1 && (i/8+1)*8+i%8-2 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8-2,i/8+1,tahta);}
	    			if (i/8+1 < 8 && i%8+2 < 8 && (i/8+1)*8+i%8+2 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8+2,i/8+1,tahta);}
	        	}
	    		else if (tas == 1) {	    			
	    			if (i/8 == 6 && tahta[(i/8-2)*8+i%8] == 0 && tahta[(i/8-1)*8+i%8] == 0 && (i/8-2)*8+i%8 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(10,i,i%8,i/8-2,tahta);}
	    			if ((i/8-1 > 0) && tahta[(i/8-1)*8+i%8] == 0 && ((i/8)-1)*8+i%8 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8,i/8-1,tahta);}
	    			if ((i/8-1 > 0 && i%8-1 > -1) && tahta[(i/8-1)*8+i%8-1] < 0 && (i/8-1)*8+i%8-1== mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8-1,i/8-1,tahta);}
	    			if ((i/8-1 > 0 && i%8+1 < 8) && tahta[(i/8-1)*8+i%8+1] < 0 && (i/8-1)*8+i%8+1== mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8+1,i/8-1,tahta);}
	    			if (i/8 == 3 && k.getGecerkenAlma() > -1 && Math.abs(k.getGecerkenAlma()-i%8) == 1 && tahta[mudaheleKareleri.get(p)] == -1) {motorHamle.add(1*10000000+(i/8-1)*10000+(k.getGecerkenAlma())*1000+(i/8)*100+(i%8)*10);}
	    			if (i/8 == 1 && (tahta[mudaheleKareleri.get(p)] == 0) && (i/8-1)*8+i%8 == mudaheleKareleri.get(p)) {motorHamle.add(9*100000000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*10000000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*10000000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*10000000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
	    			if (i/8 == 1 && (tahta[mudaheleKareleri.get(p)] < 0) && (i/8-1)*8+i%8-1 == mudaheleKareleri.get(p)) {motorHamle.add(9*100000000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*10000000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*10000000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*10000000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}
	    			if (i/8 == 1 && (tahta[mudaheleKareleri.get(p)] < 0) && (i/8-1)*8+i%8+1 == mudaheleKareleri.get(p)) {motorHamle.add(9*100000000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*10000000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*10000000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*10000000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}
	    		}
	    		else if (tas == -1) {	    		
	    			if (i/8 == 1 && tahta[(i/8+2)*8+i%8] == 0 && tahta[(i/8+1)*8+i%8] == 0 && (i/8+2)*8+i%8 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(-10,i,i%8,i/8+2,tahta);}
	    			if (tahta[(i/8+1)*8+i%8] == 0 && (i/8+1)*8+i%8 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8,i/8+1,tahta);}	    			
	    			if ((i/8+1 < 7 && i%8-1 > -1) && tahta[(i/8+1)*8+i%8-1] > 0 && (i/8+1)*8+i%8-1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8-1,i/8+1,tahta);}
	    			if ((i/8+1 < 7 && i%8+1 < 8) && tahta[(i/8+1)*8+i%8+1] > 0 && (i/8+1)*8+i%8+1 == mudaheleKareleri.get(p)) {arayuzCizilecekHamleAyarla(tas,i,i%8+1,i/8+1,tahta);}
	    			if (i/8 == 4 && k.getGecerkenAlma() > -1 && Math.abs(k.getGecerkenAlma()-i%8) == 1 && tahta[mudaheleKareleri.get(p)] == 1) {motorHamle.add(1*10000000+(i/8+1)*10000+(k.getGecerkenAlma())*1000+(i/8)*100+(i%8)*10);}
	    			if (i/8 == 6 && (tahta[mudaheleKareleri.get(p)] == 0)  && (i/8+1)*8+i%8 == mudaheleKareleri.get(p)) {motorHamle.add(9*100000000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*10000000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*10000000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*10000000+(i/8+1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
	    			if (i/8 == 6 && (tahta[mudaheleKareleri.get(p)] > 0) && (i/8+1)*8+i%8-1 == mudaheleKareleri.get(p)) {motorHamle.add(9*100000000+(i/8+1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*10000000+(i/8+1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*10000000+(i/8+1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*10000000+(i/8+1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}
	    			if (i/8 == 6 && (tahta[mudaheleKareleri.get(p)] > 0) && (i/8+1)*8+i%8+1 == mudaheleKareleri.get(p)) {motorHamle.add(9*100000000+(i/8+1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*10000000+(i/8+1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*10000000+(i/8+1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*10000000+(i/8+1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}
	    		}
	    		else if (Math.abs(tas) == 9) {
	    			sahCekisUzunTasHesap(true,i,tahta,mudaheleKareleri.get(p));
	    			sahCekisUzunTasHesap(false,i,tahta,mudaheleKareleri.get(p));
	    		}
	    		else if (Math.abs(tas) == 5) {
	    			sahCekisUzunTasHesap(false,i,tahta,mudaheleKareleri.get(p));
	    		}
	    		else if (Math.abs(tas) == 4) {	    			
	    			sahCekisUzunTasHesap(true,i,tahta,mudaheleKareleri.get(p));
	    		}
	    	}	     	    		    
	 }	 
	 public void sahCekenTasNerede(int i, int j, int[] tahta, PerformansTest p) {		 
		 int x = i%8, y = i/8, sahX, sahY;
	    	LinkedList<Integer> sahMudaheleKare = new LinkedList<Integer>();
	    	if (Math.abs(j) == 3) {
	    		sahMudaheleKare.add((int)i);
	    	}	    	
	    	if (j > 0 && p.getSiyahSahKonum()%10 == i%8 && p.getSiyahSahKonum()/10%10 > i/8 || j < 0 && p.getBeyazSahKonum()%10 == i%8 && p.getBeyazSahKonum()/10%10 > i/8) {
	    		if (j > 0) {sahX = p.getSiyahSahKonum()%10;sahY = p.getSiyahSahKonum()/10%10-1;} else {sahX = p.getBeyazSahKonum()%10;sahY = p.getBeyazSahKonum()/10%10-1;}
	    		for (;sahY >= y;sahY--) {
	    			sahMudaheleKare.add(sahY*8+sahX);
	    		}    		
	    	}
	    	else if (j > 0 && p.getSiyahSahKonum()%10 == i%8 && p.getSiyahSahKonum()/10%10 < i/8 || j < 0 && p.getBeyazSahKonum()%10 == i%8 && p.getBeyazSahKonum()/10%10 < i/8) {    		
	    		if (j > 0) {sahX = p.getSiyahSahKonum()%10;sahY = p.getSiyahSahKonum()/10%10+1;} else {sahX = p.getBeyazSahKonum()%10;sahY = p.getBeyazSahKonum()/10%10+1;}
	    		for (;sahY <= y;sahY++) {    			
	    			sahMudaheleKare.add(sahY*8+sahX);
	    		}
	    	}
	    	else if (j > 0 && p.getSiyahSahKonum()/10%10 == i/8 && p.getSiyahSahKonum()%10 > i%8 || j < 0 && p.getBeyazSahKonum()/10%10 == i/8 && p.getBeyazSahKonum()%10 > i%8) {
	    		if (j > 0) {sahX = p.getSiyahSahKonum()%10-1;sahY = p.getSiyahSahKonum()/10%10;} else {sahX = p.getBeyazSahKonum()%10-1;sahY = p.getBeyazSahKonum()/10%10;}	    		
	    		for (;sahX >= x;sahX--) {	    			
	    			sahMudaheleKare.add(sahY*8+sahX);
	    		}    		
	    	}
	    	else if (j > 0 && p.getSiyahSahKonum()/10%10 == i/8 && p.getSiyahSahKonum()%10 < i%8 || j < 0 && p.getBeyazSahKonum()/10%10 == i/8 && p.getBeyazSahKonum()%10 < i%8) {    		
	    		if (j > 0) {sahX = p.getSiyahSahKonum()%10+1;sahY = p.getSiyahSahKonum()/10%10;} else {sahX = p.getBeyazSahKonum()%10+1;sahY = p.getBeyazSahKonum()/10%10;}
	    		for (;sahX <= x;sahX++) {	    			
	    			sahMudaheleKare.add(sahY*8+sahX);
	    		}
	    	}    	
	    	else if (j > 0 && ((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)) % 9 == i%9 && p.getSiyahSahKonum()/10%10 > i/8 || j < 0 && ((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10)) %9 == i%9 && p.getBeyazSahKonum()/10%10 > i/8) {
	    		if (j > 0) {sahX = p.getSiyahSahKonum()%10-1;sahY = p.getSiyahSahKonum()/10%10-1;} else {sahX = p.getBeyazSahKonum()%10-1;sahY = p.getBeyazSahKonum()/10%10-1;}
	    		for (;sahX >= x && sahY >= y;sahX--,sahY--) {    			
	    			sahMudaheleKare.add(sahY*8+sahX);
	    		}    		
	    	}    	
	    	else if (j > 0 && ((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)) % 9 == i%9 && p.getSiyahSahKonum()/10%10 < i/8 || j < 0 && ((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10)) %9 == i%9 && p.getBeyazSahKonum()/10%10 < i/8) {
	    		if (j > 0) {sahX = p.getSiyahSahKonum()%10+1;sahY = p.getSiyahSahKonum()/10%10+1;} else {sahX = p.getBeyazSahKonum()%10+1;sahY = p.getBeyazSahKonum()/10%10+1;}
	    		for (;sahX <= x && sahY <= y;sahX++,sahY++) {    			
	    			sahMudaheleKare.add(sahY*8+sahX);
	    		}
	    	}    	
	    	else if (j > 0 && ((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)) % 7 == i%7 && p.getSiyahSahKonum()/10%10 > i/8 || j < 0 && ((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10)) % 7 == i%7 && p.getBeyazSahKonum()/10%10 > i/8) {
	    		if (j > 0) {sahX = p.getSiyahSahKonum()%10+1;sahY = p.getSiyahSahKonum()/10%10-1;} else {sahX = p.getBeyazSahKonum()%10+1;sahY = p.getBeyazSahKonum()/10%10-1;}    		
	    		for (;sahX <= x && sahY >= y;sahX++,sahY--) {    			
	    			sahMudaheleKare.add(sahY*8+sahX);
	    		}
	    	}    	
	    	else if (j > 0 && ((p.getSiyahSahKonum()/10%10)*8+(p.getSiyahSahKonum()%10)) % 7 == i%7 && p.getSiyahSahKonum()/10%10 < i/8 || j < 0 && ((p.getBeyazSahKonum()/10%10)*8+(p.getBeyazSahKonum()%10)) % 7 == i%7 && p.getBeyazSahKonum()/10%10 < i/8) {
	    		if (j > 0) {sahX = p.getSiyahSahKonum()%10-1;sahY = p.getSiyahSahKonum()/10%10+1;} else {sahX = p.getBeyazSahKonum()%10-1;sahY = p.getBeyazSahKonum()/10%10+1;}
	    		for (;sahX >= x && sahY <= y;sahX--,sahY++) {    			
	    			sahMudaheleKare.add(sahY*8+sahX);
	    		}
	    	}		    	
	    	p.sahMudaheleKareAyarla(sahMudaheleKare);	    	    
	 }
	 public void sahCekisUzunTasHesap(boolean yon, int i, int[] tahta, int hedefKare) {		 
	    	int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1,ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1,m = 0,j = 0,k = 0, l = 0;	    	
	    	if (yon) {	    		
	    		if (hedefKare % 9 == i%9) {	    			
	        		if (i < hedefKare) {m = 7 - Math.max(i/8, i%8);} else {j = Math.min(i/8, i%8);}    	
	        	}
	    		else if (hedefKare % 7 == i%7) {	    			
	        		if (i < hedefKare) {k = 7 - Math.max(i/8, 7 - i%8);} else {l = Math.min(i/8, 7 - i%8);}    		
	        	}
	    		for (;sagAltKose <= m | solUstKose <= j || sagUstKose <= l || solAltKose <= k;sagAltKose++,solUstKose++,sagUstKose++,solAltKose++) {	    			
	    			if (sagAltKose <= m && i+sagAltKose*9 < 64 && i+sagAltKose*9 != hedefKare && tahta[i+sagAltKose*9] != 0) {sagAltKose = 8;}else if (i+sagAltKose*9 == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,(i+sagAltKose*9)%8,(i+sagAltKose*9)/8,tahta);}    			
	    			if (solUstKose <= j && i-solUstKose*9 > -1 && i-solUstKose*9 != hedefKare && tahta[i-solUstKose*9] != 0) {solUstKose = 8;}else if (i-solUstKose*9 == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,(i-solUstKose*9)%8,(i-solUstKose*9)/8,tahta);}
	    			if (sagUstKose <= l && i-sagUstKose*7 > -1 && i-sagUstKose*7 != hedefKare && tahta[i-sagUstKose*7] != 0) {sagUstKose = 8;}else if (i-sagUstKose*7 == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,(i-sagUstKose*7)%8,(i-sagUstKose*7)/8,tahta);}
	    			if (solAltKose <= k && i+solAltKose*7 < 64 && i+solAltKose*7 != hedefKare && tahta[i+solAltKose*7] != 0) {solAltKose = 8;}else if (i+solAltKose*7 == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,(i+solAltKose*7)%8,(i+solAltKose*7)/8,tahta);}	    				    				    			
	    		}
	    	} else {
	    		if (i%8 == hedefKare%8) {    			
	    			if (i < hedefKare) {m = hedefKare/8-i/8;}else {j = i/8-hedefKare/8;}
	    		}
	    		else if (i/8 == hedefKare/8) {
	    			if (i < hedefKare) {k = hedefKare%8-i%8;}else {l = i%8-hedefKare%8;}	    			
	    		}
	    		for (;ustDikey <= j || altDikey <= m || sagYatay <= k || solYatay <= l;ustDikey++,altDikey++,sagYatay++,solYatay++) {
	    			if (i-ustDikey*8 > -1 && i-ustDikey*8 != hedefKare && tahta[i-ustDikey*8] != 0) {ustDikey = 8;}else if (i-ustDikey*8 == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,(i-ustDikey*8)/8,tahta);}    				
	    			if (i+altDikey*8 < 64 && i+altDikey*8 != hedefKare && tahta[i+altDikey*8] != 0) {altDikey = 8;}else if (i+altDikey*8 == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,(i+altDikey*8)/8,tahta);}	    				    		
	    			if (i+sagYatay < 64 && i/8*8+i%8+sagYatay != hedefKare &&tahta[i/8*8+i%8+sagYatay] != 0) {sagYatay = 8;}else if (i/8*8+i%8+sagYatay == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+sagYatay,i/8,tahta);}	    				    				    		
	    			if (i-solYatay > -1 && i/8*8+i%8-solYatay != hedefKare && tahta[i/8*8+i%8-solYatay] != 0) {solYatay = 8;}else if (i/8*8+i%8-solYatay == hedefKare) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-solYatay,i/8,tahta);}
	    		}
	    	}	    	
	    }
	public void sahTehtidAyarla(double i, int kare, int sahCekenTas, int[] tahta, PerformansTest p) {    	
    	if (i > 0) {    	
    		sahTehtid.set(kare, sahTehtid.get(kare)+10);
    		if (tahta[kare] == -900) {    			
    			toplamSahSayisi++;    			
    			p.setSahCekenTas(sahCekenTas);
    			p.setSiyahSahCekis(p.getSiyahSahCekis()+1);    			
    			sahCekenTasNerede(p.getSahCekenTas(),(int) tahta[p.getSahCekenTas()],tahta,p);
    		}
    	}
    	else {
    		sahTehtid.set(kare, sahTehtid.get(kare)+1);
    		if (tahta[kare] == 900) {     			
    			toplamSahSayisi++;
    			p.setSahCekenTas(sahCekenTas);
    			p.setBeyazSahCekis(p.getBeyazSahCekis()+1);
    			sahCekenTasNerede(p.getSahCekenTas(),(int) tahta[p.getSahCekenTas()],tahta,p);
    		}
    	}
     }    	
	 public void uzuntasHesap(boolean yon, byte i, int[] tahta, boolean beyazMiSiyahMi) {
	    	if (yon) {    		
	    		for (int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1;solAltKose <= 7 || solUstKose <= 7 || sagUstKose <= 7 || sagAltKose <= 7;solAltKose++,solUstKose++,sagUstKose++,sagAltKose++) {    			
	    			if (i/8+solAltKose < 8 && i%8-solAltKose > -1 && (beyazMiSiyahMi && tahta[(i/8+solAltKose)*8+i%8-solAltKose] < 1 || !beyazMiSiyahMi && tahta[(i/8+solAltKose)*8+i%8-solAltKose] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-solAltKose,i/8+solAltKose,tahta);if (tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0) {solAltKose = 8;}} else {solAltKose = 8;}
	    			if (i/8-solUstKose > -1 && i%8-solUstKose > -1 && (beyazMiSiyahMi && tahta[(i/8-solUstKose)*8+i%8-solUstKose] < 1 || !beyazMiSiyahMi && tahta[(i/8-solUstKose)*8+i%8-solUstKose] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-solUstKose,i/8-solUstKose,tahta);if (tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0) {solUstKose = 8;}} else {solUstKose = 8;}
	    			if (i/8-sagUstKose > -1 && i%8+sagUstKose < 8 && (beyazMiSiyahMi &&tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] < 1 || !beyazMiSiyahMi && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+sagUstKose,i/8-sagUstKose,tahta);if (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0) {sagUstKose = 8;}} else {sagUstKose = 8;}
	    			if (i/8+sagAltKose < 8 && i%8+sagAltKose < 8 && (beyazMiSiyahMi &&tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] < 1 || !beyazMiSiyahMi && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+sagAltKose,i/8+sagAltKose,tahta);if (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0) {sagAltKose = 8;}} else {sagAltKose = 8;}    			
	    		}    		
	    	}
	    	else {
	    		for (int ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1;ustDikey <= i/8 || altDikey <= 7-i/8 || solYatay <= i%8 || sagYatay <= 7-i%8;solYatay++,sagYatay++,ustDikey++,altDikey++) {    			
	    			if ((ustDikey <= i/8) && (beyazMiSiyahMi && tahta[((i/8-ustDikey)*8)+i%8] < 1 || !beyazMiSiyahMi && tahta[((i/8-ustDikey)*8)+i%8] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8-ustDikey,tahta);if (tahta[(((i/8)-ustDikey)*8)+i%8] != 0.0) {ustDikey = i/8+1;}} else {ustDikey= i/8+1;}
	    			if ((altDikey <= (7-(i/8))) && (beyazMiSiyahMi && tahta[((i/8+altDikey)*8)+i%8] < 1 || !beyazMiSiyahMi && tahta[((i/8+altDikey)*8)+i%8] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8+altDikey,tahta);if (tahta[(((i/8)+altDikey)*8)+i%8] != 0.0) {altDikey = (8-(i/8));}} else {altDikey= (8-(i/8));}
	    			if ((sagYatay <= (7-(i%8))) && (beyazMiSiyahMi && tahta[((i/8)*8)+i%8+sagYatay] < 1 || !beyazMiSiyahMi && tahta[((i/8)*8)+i%8+sagYatay] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+sagYatay,i/8,tahta);if (tahta[(((i/8))*8)+i%8+sagYatay] != 0.0) {sagYatay = (8-(i%8));}} else {sagYatay= (8-(i%8));}
	    			if ((solYatay <= i%8) && (beyazMiSiyahMi && tahta[((i/8)*8)+i%8-solYatay] < 1 || !beyazMiSiyahMi && tahta[((i/8)*8)+i%8-solYatay] > -1)) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-solYatay,i/8,tahta);if (tahta[(((i/8))*8)+i%8-solYatay] != 0.0) {solYatay = i%8+1;}} else {solYatay= i%8+1;}    			
	    		}
	    	}
	}
	 public void sahTehtidUzunTasHesap(boolean yon, byte i, int[] tahta, PerformansTest p, boolean beyazMiSiyahMi) {
	    	if (yon) {
	    		for (int solAltKose = 1,solUstKose = 1,sagUstKose = 1,sagAltKose = 1;solAltKose <= 7 || solUstKose <= 7 || sagUstKose <= 7 || sagAltKose <= 7;solAltKose++,solUstKose++,sagUstKose++,sagAltKose++) {    			    			 	    			
	    			if ((i/8-solUstKose > -1 && i%8-solUstKose > -1) && (tahta[(i/8-solUstKose)*8+i%8-solUstKose] == 0 || !beyazMiSiyahMi && (tahta[(i/8-solUstKose)*8+i%8-solUstKose] == -900 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] > 0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] < 900 ) || beyazMiSiyahMi && (tahta[(i/8-solUstKose)*8+i%8-solUstKose] == 900 || tahta[(i/8-solUstKose)*8+i%8-solUstKose] > -900 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] < 0))) {sahTehtidAyarla(tahta[i], (i/8-solUstKose)*8+i%8-solUstKose,i,tahta,p);if (tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 900.0 && beyazMiSiyahMi || tahta[(i/8-solUstKose)*8+i%8-solUstKose] != 0.0 && tahta[(i/8-solUstKose)*8+i%8-solUstKose] != -900.0 && !beyazMiSiyahMi) {solUstKose = 8;}} else {solUstKose = 8;}	    				    		
	    			if ((i/8+solAltKose < 8 && i%8-solAltKose > -1)  && (tahta[(i/8+solAltKose)*8+i%8-solAltKose] == 0 || !beyazMiSiyahMi && (tahta[(i/8+solAltKose)*8+i%8-solAltKose] == -900 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] > 0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] < 900 ) || beyazMiSiyahMi && (tahta[(i/8+solAltKose)*8+i%8-solAltKose] == 900 || tahta[(i/8+solAltKose)*8+i%8-solAltKose] > -900 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] < 0))) {sahTehtidAyarla(tahta[i], (i/8+solAltKose)*8+i%8-solAltKose,i,tahta,p);if (tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 900.0 && beyazMiSiyahMi || tahta[(i/8+solAltKose)*8+i%8-solAltKose] != 0.0 && tahta[(i/8+solAltKose)*8+i%8-solAltKose] != -900.0 && !beyazMiSiyahMi) {solAltKose = 8;}} else {solAltKose = 8;}	    				    		
	    			if ((i/8-sagUstKose > -1 && i%8+sagUstKose < 8)  && (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == 0 || !beyazMiSiyahMi && (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == -900 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] > 0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] < 900 ) || beyazMiSiyahMi && (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] == 900 || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] > -900 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] < 0))) {sahTehtidAyarla(tahta[i], (i/8-sagUstKose)*8+i%8+sagUstKose,i,tahta,p);if (tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 900.0 && beyazMiSiyahMi || tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != 0.0 && tahta[(i/8-sagUstKose)*8+i%8+sagUstKose] != -900.0 && !beyazMiSiyahMi) {sagUstKose = 8;}} else {sagUstKose = 8;}	    				    		
	    			if ((i/8+sagAltKose < 8 && i%8+sagAltKose < 8)   && (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == 0 || !beyazMiSiyahMi && (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == -900 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] > 0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] < 900 ) || beyazMiSiyahMi && (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] == 900 || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] > -900 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] < 0))) {sahTehtidAyarla(tahta[i], (i/8+sagAltKose)*8+i%8+sagAltKose,i,tahta,p);if (tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 900.0 && beyazMiSiyahMi || tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != 0.0 && tahta[(i/8+sagAltKose)*8+i%8+sagAltKose] != -900.0 && !beyazMiSiyahMi) {sagAltKose = 8;}} else {sagAltKose = 8;}
	    			
	    		}    		
	    	}
	    	else {
	    		for (int ustDikey = 1,altDikey = 1,solYatay = 1,sagYatay = 1;ustDikey <= i/8 || altDikey <= 7-i/8 || solYatay <= i%8 || sagYatay <= 7-i%8;solYatay++,sagYatay++,ustDikey++,altDikey++) {    				    				    				    				    			    				    				    				    				    				    				    	    			
	    			if ((sagYatay <= 7-i%8) && (tahta[((i/8)*8)+i%8+sagYatay] == 0 || !beyazMiSiyahMi && (tahta[((i/8)*8)+i%8+sagYatay] == -900 || tahta[((i/8)*8)+i%8+sagYatay] > 0 && tahta[((i/8)*8)+i%8+sagYatay] < 900 ) || beyazMiSiyahMi && (tahta[((i/8)*8)+i%8+sagYatay] == 900 || tahta[((i/8)*8)+i%8+sagYatay] > -900 && tahta[((i/8)*8)+i%8+sagYatay] < 0))) {sahTehtidAyarla(tahta[i], ((i/8)*8)+i%8+sagYatay,i,tahta,p);if (tahta[((i/8)*8)+i%8+sagYatay] != 0.0 && tahta[((i/8)*8)+i%8+sagYatay] != 900.0 && beyazMiSiyahMi || tahta[((i/8)*8)+i%8+sagYatay] != 0.0 && tahta[(((i/8))*8)+i%8+sagYatay] != -900.0 && !beyazMiSiyahMi) {sagYatay = 8-i%8;}} else {sagYatay= 8-i%8;}	    				    		
	    			if ((solYatay <= i%8)   && (tahta[((i/8)*8)+i%8-solYatay] == 0 || !beyazMiSiyahMi && (tahta[((i/8)*8)+i%8-solYatay] == -900 || tahta[((i/8)*8)+i%8-solYatay] > 0 && tahta[((i/8)*8)+i%8-solYatay] < 900 ) || beyazMiSiyahMi && (tahta[((i/8)*8)+i%8-solYatay] == 900 || tahta[((i/8)*8)+i%8-solYatay] > -900 && tahta[((i/8)*8)+i%8-solYatay] < 0))) {sahTehtidAyarla(tahta[i], ((i/8)*8)+i%8-solYatay,i,tahta,p);if (tahta[((i/8)*8)+i%8-solYatay] != 0.0 && tahta[((i/8)*8)+i%8-solYatay] != 900.0 && beyazMiSiyahMi || tahta[((i/8)*8)+i%8-solYatay] != 0.0 && tahta[(((i/8))*8)+i%8-solYatay] != -900.0 && !beyazMiSiyahMi) {solYatay = i%8+1;}} else {solYatay= i%8+1;}	    				    				    				    	
	    			if ((ustDikey <= i/8)   && (tahta[((i/8-ustDikey)*8)+i%8] == 0 || !beyazMiSiyahMi && (tahta[((i/8-ustDikey)*8)+i%8] == -900 || tahta[((i/8-ustDikey)*8)+i%8] > 0 && tahta[((i/8-ustDikey)*8)+i%8] < 900) || beyazMiSiyahMi && (tahta[((i/8-ustDikey)*8)+i%8] == 900 || tahta[((i/8-ustDikey)*8)+i%8] > -900 && tahta[((i/8-ustDikey)*8)+i%8] < 0))) {sahTehtidAyarla(tahta[i], ((i/8-ustDikey)*8)+i%8,i,tahta,p); if (tahta[((i/8-ustDikey)*8)+i%8] != 0.0 && tahta[((i/8-ustDikey)*8)+i%8] != 900.0 && beyazMiSiyahMi || tahta[((i/8-ustDikey)*8)+i%8] != 0.0 && tahta[(((i/8)-ustDikey)*8)+i%8] != -900.0 && !beyazMiSiyahMi) {ustDikey = i/8+1;}} else {ustDikey= i/8+1;}	    				    		
	    			if ((altDikey <= 7-i/8) && (tahta[((i/8+altDikey)*8)+i%8] == 0 || !beyazMiSiyahMi && (tahta[((i/8+altDikey)*8)+i%8] == -900 || tahta[((i/8+altDikey)*8)+i%8] > 0 && tahta[((i/8+altDikey)*8)+i%8] < 900) || beyazMiSiyahMi && (tahta[((i/8+altDikey)*8)+i%8] == 900 || tahta[((i/8+altDikey)*8)+i%8] > -900 && tahta[((i/8+altDikey)*8)+i%8] < 0))) {sahTehtidAyarla(tahta[i], ((i/8+altDikey)*8)+i%8,i,tahta,p); if (tahta[((i/8+altDikey)*8)+i%8] != 0.0 && tahta[((i/8+altDikey)*8)+i%8] != 900.0 && beyazMiSiyahMi || tahta[((i/8+altDikey)*8)+i%8] != 0.0 && tahta[(((i/8)+altDikey)*8)+i%8] != -900.0 && !beyazMiSiyahMi) {altDikey = 8-i/8;}} else {altDikey= 8-i/8;}
	    			
	    		}
	    	}    	    	    	
	    }
	public void arayuzCizilecekHamleAyarla(double j, int i,int x, int y, int[] tahta) {
		if (tahta[y*8+x] != 0.0) {
			captures++;
		}		
    	if (j > 0) {
    		motorHamle.add((int) (j*100000+(y)*10000+(x)*1000+(i/8)*100+(i%8)*10));
    	}
    	else {
    		motorHamle.add((int) ((j*100000-(y)*10000-(x)*1000-(i/8)*100-(i%8)*10)*-1));
    	}    
    }	
	public int[] hamleleriAl(boolean siraKimde, int[] tahta, PerformansTest p) {		
		if (p.getBeyazSahCekis() > 0) {p.setBeyazSahCekis(0);}
		if (p.getSiyahSahCekis() > 0) {p.setSiyahSahCekis(0);}
		arama(siraKimde, tahta, p);
		if (motorHamle.size() == 0) {mat++;}
		int[] hamleler = new int[motorHamle.size()];
		hamleler = motorHamle.stream().mapToInt(i->i).toArray();	
		return hamleler;
	}	
}