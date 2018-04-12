package drugo;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import drugo.Qice.Cvetove;

public class Masa {
	private Map<Qice.Cvetove, BlockingQueue<Qice>> burkani = new ConcurrentHashMap<Qice.Cvetove, BlockingQueue<Qice>>();
	private BlockingQueue<Qice> gotoviQica = new LinkedBlockingQueue<Qice>();

	public Masa() {
		for (Cvetove cvqt : Cvetove.values()) {
			burkani.put(cvqt, new ArrayBlockingQueue<Qice>(2));
		}
	}

	public void addQice(Qice qice) {
		synchronized (burkani) {
			while (imaLiMQstoZaQice() == null) {
				try {
					burkani.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Qice.Cvetove cvqt =imaLiMQstoZaQice();
			try {
				burkani.get(cvqt).put(qice);
				// kazvam na qiceto da zapochne da se boqdisva
				synchronized (qice) {
					qice.notifyAll();
				}
				qice.setCvqt(cvqt);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public Qice.Cvetove imaLiMQstoZaQice() {
		for (Qice.Cvetove cvqt : burkani.keySet()) {
			if (burkani.get(cvqt).size() < 2) {
				return cvqt;
			}
		}
		return null;
	}

	public void removeQiceFromBurkani(Qice qice) {
		synchronized (burkani) {
			Qice.Cvetove cvqt = qice.getCvqt();
			try {
				burkani.get(cvqt).remove(qice);
				gotoviQica.put(qice);
				System.out.println(qice+" e boqdisano v "+cvqt+" cvqt");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			burkani.notifyAll();
		}
	}

	public Qice takeGotovoQice() throws InterruptedException {
		return gotoviQica.poll(30,TimeUnit.SECONDS);
	}

}
