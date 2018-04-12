package drugo;

import java.time.LocalDateTime;

import demo.Demo;
import people.Dete;

public class Qice implements Runnable {

	public enum Cvetove {
		CHERVEN, SIN, ZELEN, ORAJEV, JULT;
		public static Cvetove getRandomCvqt() {
			return Cvetove.values()[(int) (Demo.randomNumber(Cvetove.values().length))];
		}
	}

	public enum QicaType {
		KOKOSHI(10), GUSHI(5), PATESHKI(3);
		private int boqTime;

		private QicaType(int boqTime) {
			this.boqTime = boqTime;
		}

		public static QicaType getRandomQiceType() {
			return QicaType.values()[(int) (Demo.randomNumber(QicaType.values().length))];
		}

		public int getBoqTime() {
			return this.boqTime;
		}
	}

	private Cvetove cvqt;
	private QicaType type;
	private boolean isShareno;
	private boolean isPainted;
	private LocalDateTime kogaEBoqdisano;
	private Dete koeDeteGoBoqdisa;

	public Qice(QicaType type) {
		setType(type);
		new Thread(this).start();
	}

	// Setters
	public void setCvqt(Cvetove cvqt) {
		if (cvqt != null) {
			this.cvqt = cvqt;
		}
	}

	private void setType(QicaType type) {
		if (type != null) {
			this.type = type;
		}
	}

	@Override
	public String toString() {
		return "Qice [cvqt=" + cvqt + ", shareno e =" + isShareno + ", boqdisano e na =" + kogaEBoqdisano + ", boqdisa go:"
				+ koeDeteGoBoqdisa + "]";
	}

	public void setShareno(boolean isShareno) {
		this.isShareno = isShareno;
	}

	public void setPainted(boolean isPainted) {
		this.isPainted = isPainted;
	}

	public void setKoeDeteGoBoqdisa(Dete koeDeteGoBoqdisa) {
		this.koeDeteGoBoqdisa = koeDeteGoBoqdisa;
	}

	// Getters
	public Cvetove getCvqt() {
		return this.cvqt;
	}

	public QicaType getType() {
		return this.type;
	}

	public boolean isPainted() {
		return this.isPainted;
	}

	public boolean isShareno() {
		return this.isShareno;
	}

	public Dete getKoeDeteGoBoqdisa() {
		return this.koeDeteGoBoqdisa;
	}

	@Override
	public void run() {
		synchronized (this) {
			if (!isPainted) {
				// qiceto se chaka sebe si nqkoi da mu kaje notifi za da pochne
				// da se boqdisva
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			Thread.sleep(this.type.getBoqTime() * 1000);
			this.isPainted = true;
			this.kogaEBoqdisano = LocalDateTime.now();
			Demo.masa.removeQiceFromBurkani(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
