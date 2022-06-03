package motor;

import java.util.LinkedList;

import tahta.Tahta;

public class Hamleler extends Tahta {
	public LinkedList<Integer> motorHamle = new LinkedList<Integer>();
	public String varyantlar = "";
	
	public void arama(boolean beyazMiSiyahMi) {    	
		double[] tahta = tahtaVeriAl();
		for (byte i = 0;i < tahta.length;i++) {									
			//King movements, �ah hareketleri
			if (beyazMiSiyahMi && tahta[i] == 900 || !beyazMiSiyahMi && tahta[i] == -900) {					
				if (i/8-1 > -1 && tahta[((i/8)-1)*8+i%8] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8)%10 < 1 || i/8-1 > -1 && tahta[((i/8)-1)*8+i%8] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8-1);}
				if (i/8+1 < 8 && tahta[((i/8)+1)*8+i%8] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8)%10 < 1 || i/8+1 < 8 && tahta[((i/8)+1)*8+i%8] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8,i/8+1);}
				if (i%8+1 < 8 && tahta[((i/8))*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8))*8+i%8+1)%10 < 1 || i%8+1 < 8 && tahta[((i/8))*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8))*8+i%8+1)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8);}
				if (i%8-1 > -1 && tahta[((i/8))*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8))*8+i%8-1)%10 < 1 || i%8-1 > -1 && tahta[((i/8))*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8))*8+i%8-1)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8);}
				if (i%8-1 > -1 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8-1)%10 < 1 || i%8-1 > -1 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8-1)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8+1);}
				if (i%8+1 < 8 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)+1)*8+i%8+1)%10 < 1 || i%8+1 < 8 && i/8+1 < 8 && tahta[((i/8)+1)*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)+1)*8+i%8+1)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8+1);}
				if (i%8+1 < 8 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8+1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8+1)%10 < 1 || i%8+1 < 8 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8+1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8+1)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8-1);}
				if (i%8-1 > -1 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8-1] < 1 && tahta[i] == 900 && sahTehtid.get(((i/8)-1)*8+i%8-1)%10 < 1 || i%8-1 > -1 && i/8-1 > -1 && tahta[((i/8)-1)*8+i%8-1] > -1 && tahta[i] == -900 && sahTehtid.get(((i/8)-1)*8+i%8-1)/10 < 1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8-1);}
				if (tahta[60] != 900) {if (beyazKisaRokAtilabilirMi()) {beyazKisaRokAyarla(false);}if (beyazUzunRokAtilabilirMi()) {beyazUzunRokAyarla(false);}}
				if (tahta[4] != -900) {if (siyahKisaRokAtilabilirMi()) {siyahKisaRokAyarla(false);}if (siyahUzunRokAtilabilirMi()) {siyahUzunRokAyarla(false);}}
				if (tahta[i] == -900 && siyahKisaRokAtilabilirMi() && tahta[7] == -5.0 && tahta[6] == 0.0 && tahta[5] == 0.0 || tahta[i] == 900 && beyazKisaRokAtilabilirMi() && tahta[63] == 5.0 && tahta[62] == 0.0 && tahta[61] == 0.0) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8);}
				if (tahta[i] == -900 && siyahUzunRokAtilabilirMi() && tahta[0] == -5.0 && tahta[1] == 0.0 && tahta[2] == 0.0 && tahta[3] == 0.0 || tahta[i] == 900 && beyazUzunRokAtilabilirMi() && tahta[56] == 5.0 && tahta[57] == 0.0 && tahta[58] == 0.0 && tahta[59] == 0.0) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8);}													
			}
			//Knight movements, At hareketleri
			if (beyazMiSiyahMi && tahta[i] == 3 || !beyazMiSiyahMi && tahta[i] == -3) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {
						if (tahta[i] == 3 && i/8-2 > -1 && i%8-1 > -1 && tahta[((i/8)-2)*8+i%8-1] < 1 || tahta[i] == -3 && i/8-2 > -1 && i%8-1 > -1 && tahta[((i/8)-2)*8+i%8-1] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8-2);}				
						if (tahta[i] == 3 && i/8-1 > -1 && i%8-2 > -1 && tahta[((i/8)-1)*8+i%8-2] < 1 || tahta[i] == -3 && i/8-1 > -1 && i%8-2 > -1 && tahta[((i/8)-1)*8+i%8-2] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8-1);}
						if (tahta[i] == 3 && i/8-1 > -1 && i%8+2 < 8 && tahta[((i/8)-1)*8+i%8+2] < 1 || tahta[i] == -3 && i/8-1 > -1 && i%8+2 < 8 && tahta[((i/8)-1)*8+i%8+2] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8-1);}
						if (tahta[i] == 3 && i/8-2 > -1 && i%8+1 < 8 && tahta[((i/8)-2)*8+i%8+1] < 1 || tahta[i] == -3 && i/8-2 > -1 && i%8+1 < 8 && tahta[((i/8)-2)*8+i%8+1] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8-2);}
						if (tahta[i] == 3 && i/8+2 < 8 && i%8+1 < 8 && tahta[((i/8)+2)*8+i%8+1] < 1 || tahta[i] == -3 && i/8+2 < 8 && i%8+1 < 8 && tahta[((i/8)+2)*8+i%8+1] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+1,i/8+2);}
						if (tahta[i] == 3 && i/8+2 < 8 && i%8-1 > -1 && tahta[((i/8)+2)*8+i%8-1] < 1 || tahta[i] == -3 && i/8+2 < 8 && i%8-1 > -1 && tahta[((i/8)+2)*8+i%8-1] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-1,i/8+2);}
						if (tahta[i] == 3 && i/8+1 < 8 && i%8-2 > -1 && tahta[((i/8)+1)*8+i%8-2] < 1 || tahta[i] == -3 && i/8+1 < 8 && i%8-2 > -1 && tahta[((i/8)+1)*8+i%8-2] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8-2,i/8+1);}
						if (tahta[i] == 3 && i/8+1 < 8 && i%8+2 < 8 && tahta[((i/8)+1)*8+i%8+2] < 1 || tahta[i] == -3 && i/8+1 < 8 && i%8+2 < 8 && tahta[((i/8)+1)*8+i%8+2] > -1) {arayuzCizilecekHamleAyarla(tahta[i],i,i%8+2,i/8+1);}
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i);
						}					
					}
				}				
			}
			//Bishop movements, Fil hareketleri
			if (beyazMiSiyahMi && tahta[i] == 4 || !beyazMiSiyahMi && tahta[i] == -4) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {
						uzuntasHesap(true,i,tahta);
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i);
						}					
					}
				}								
			}
			//Rook movements, Kale hareketleri
			if (beyazMiSiyahMi && tahta[i] == 5 || !beyazMiSiyahMi && tahta[i] == -5) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {
						uzuntasHesap(false,i,tahta);
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i);
						}					
					}
				}							
				if (beyazKisaRokAtilabilirMi() && tahta[63] != 5.0) {beyazKisaRokAyarla(false);} else if (siyahKisaRokAtilabilirMi() && tahta[7] != -5.0) {siyahKisaRokAyarla(false);}
                if (beyazUzunRokAtilabilirMi() && tahta[56] != 5.0) {beyazUzunRokAyarla(false);} else if (siyahUzunRokAtilabilirMi() && tahta[0] != -5.0) {siyahUzunRokAyarla(false);}
			}
			//Queen movements, Vezir hareketleri
			if (beyazMiSiyahMi && tahta[i] == 9 || !beyazMiSiyahMi && tahta[i] == -9) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {					
						uzuntasHesap(false,i,tahta);
						uzuntasHesap(true,i,tahta);
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i);
						}					
					}
				}						
			}
			//Pawn movements, Piyon hareketleri
			if (beyazMiSiyahMi && tahta[i] == 1 || !beyazMiSiyahMi && tahta[i] == -1) {
				if (acmazTest(i,(int)tahta[i],tahta) == 0) {
					if (tahta[i] < 0 && siyahSahCekisAl() < 1 || tahta[i] > 0 && beyazSahCekisAl() < 1) {
						if (i/8-1 > 0 && tahta[i] == 1 || i/8+1 < 7 && tahta[i] == -1) {if (tahta[i] == 1 && tahta[(i/8-1)*8+i%8] == 0.0 || tahta[i] == -1 && tahta[(i/8+1)*8+i%8] == 0.0) {if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else {motorHamle.add(-1*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}}}
						if (i/8 == 6 && tahta[i] == 1 || i/8 == 1 && tahta[i] == -1) {if (tahta[i] == 1 && tahta[(i/8-2)*8+i%8] == 0.0 || tahta[i] == -1 && tahta[(i/8+2)*8+i%8] == 0.0)  {if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-2)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}else {motorHamle.add(-1*100000-(i/8+2)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}}}
						if (tahta[i] == 1 && i/8-1 > 0 && i%8-1 > -1 && tahta[(i/8-1)*8+i%8-1] < 0 || tahta[i] == -1 && i/8+1 < 7 && i%8-1 > -1 && tahta[(i/8+1)*8+i%8-1] > 0) {if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);}else {motorHamle.add(-1*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
						if (tahta[i] == 1 && i/8-1 > 0 && i%8+1 < 8 && tahta[(i/8-1)*8+i%8+1] < 0 || tahta[i] == -1 && i/8+1 < 7 && i%8+1 < 8 && tahta[(i/8+1)*8+i%8+1] > 0) {if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);}else {motorHamle.add(-1*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
						if (tahta[i] > 0 && (hamleler.size() > 0) && hamleler.get(hamleler.size()-1)/100000 == -1 && i/8 == 3 && ((hamleler.get(hamleler.size()-1)*-1)/10000%10) == 3 && ((hamleler.get(hamleler.size()-1)*-1)/100%10) == 1 || tahta[i] < 0 && (hamleler.size() > 0) && hamleler.get(hamleler.size()-1)/100000 == 1 && i/8 == 4 && (hamleler.get(hamleler.size()-1)/10000%10) == 4 && (hamleler.get(hamleler.size()-1)/100%10) == 6) {if (tahta[i] > 0) {motorHamle.add(1*100000+(i/8-1)*10000+((hamleler.get(hamleler.size()-1)/1000%10)*-1)*1000+(i/8)*100+(i%8)*10);} else {motorHamle.add(1*100000+(i/8+1)*10000+((hamleler.get(hamleler.size()-1)/1000%10))*1000+(i/8)*100+(i%8)*10);}}
						if (i/8 == 1 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8] == 0.0) {motorHamle.add(9*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*100000+(i/8-1)*10000+(i%8)*1000+(i/8)*100+(i%8)*10);}
						if (i/8 == 6 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8] == 0.0) {motorHamle.add(-9*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-4*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-3*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-5*100000-(i/8+1)*10000-(i%8)*1000-(i/8)*100-(i%8)*10);}
						if (i/8 == 1 && i%8-1 > -1 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8-1] < 0 || i/8 == 6 && i%8-1 > -1 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8-1] > 0) {if (tahta[i] > 0) {motorHamle.add(9*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*100000+(i/8-1)*10000+(i%8-1)*1000+(i/8)*100+(i%8)*10);} else {motorHamle.add(-9*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-4*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-3*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-5*100000-(i/8+1)*10000-(i%8-1)*1000-(i/8)*100-(i%8)*10);}}
						if (i/8 == 1 && i%8+1 < 8 && tahta[i] == 1 && tahta[(i/8-1)*8+i%8+1] < 0 || i/8 == 6 && i%8+1 < 8 && tahta[i] == -1 && tahta[(i/8+1)*8+i%8+1] > 0) {if (tahta[i] > 0) {motorHamle.add(9*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(4*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(3*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);motorHamle.add(5*100000+(i/8-1)*10000+(i%8+1)*1000+(i/8)*100+(i%8)*10);} else {motorHamle.add(-9*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-4*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-3*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);motorHamle.add(-5*100000-(i/8+1)*10000-(i%8+1)*1000-(i/8)*100-(i%8)*10);}}
					} else {
						if (tahta[i] < 0 && siyahSahCekisAl() < 2 || tahta[i] > 0 && beyazSahCekisAl() < 2) {
							sahCekisArama(tahta[i],i);
						}					
					}		
				}					
				varyantlar+= motorHamle.getLast()+"|";
			}
		}		 
	}	
	public void arayuzCizilecekHamleAyarla(double j, int i,int x, int y) {    	
    	if (j > 0) {
    		motorHamle.add((int) (j*100000+(y)*10000+(x)*1000+(i/8)*100+(i%8)*10));
    	}
    	else {
    		motorHamle.add((int) (j*100000-(y)*10000-(x)*1000-(i/8)*100-(i%8)*10));
    	}
    	varyantlar+= motorHamle.getLast()+"|";
    }  

}