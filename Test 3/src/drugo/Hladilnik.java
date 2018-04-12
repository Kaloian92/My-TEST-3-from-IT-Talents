package drugo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import demo.Demo;
import people.Dete;

public class Hladilnik {
	private Map<Qice.QicaType, Set<Qice>> kori = new ConcurrentHashMap<Qice.QicaType, Set<Qice>>();

	public Hladilnik() {
		for (Qice.QicaType type : Qice.QicaType.values()) {
			kori.put(type, new HashSet<Qice>());
		}
	}

	public void addQice(Qice qice) {
		Qice.QicaType type = qice.getType();
		synchronized (kori.get(type)) {
			kori.get(type).add(qice);
		}
		Demo.bashta.zapishi();
	}

	public Set<Qice> getKori(Qice.QicaType type) {
		return Collections.unmodifiableSet(kori.get(type));
	}

	public int broiBoqdisaniQica() {
		int broi = 0;
		for (Qice.QicaType type : Qice.QicaType.values()) {
			broi += kori.get(type).size();
		}
		return broi;
	}

	public int broiShareniQica() {
		int broi = 0;
		for (Qice.QicaType type : Qice.QicaType.values()) {
			for (Qice qice : kori.get(type)) {
				if (qice.isShareno()) {
					broi++;
				}
			}
		}
		return broi;
	}

	public int broiGushiZeleniQica() {
		int broi = 0;
		for (Qice qice : kori.get(Qice.QicaType.GUSHI)) {
			if (qice.getCvqt() == Qice.Cvetove.ZELEN) {
				broi++;
			}
		}
		return broi;
	}

	public String naiBoqdisvashtBurkan() {
		Qice.Cvetove burkan = null;
		int maxBroiOtEdinCvqt = 0;
		for (Qice.Cvetove cvqt : Qice.Cvetove.values()) {
			int broi = 0;
			for (Qice.QicaType type : Qice.QicaType.values()) {
				for (Qice qice : kori.get(type)) {
					if (qice.getCvqt() == cvqt) {
						broi++;
					}
				}
			}
			if (broi > maxBroiOtEdinCvqt) {
				maxBroiOtEdinCvqt = broi;
				burkan = cvqt;
			}
		}
		return burkan.toString();
	}

	public Dete naiBoqdisvashtoDete() {
		Dete naiDete = null;
		int maxBroiOtEdinoDete = 0;
		for (Dete dete : Demo.deca) {
			int broi = 0;
			for (Qice.QicaType type : Qice.QicaType.values()) {
				for (Qice qice : kori.get(type)) {
					if (qice.getKoeDeteGoBoqdisa() == dete) {
						broi++;
					}
				}
			}
			if (broi > maxBroiOtEdinoDete) {
				maxBroiOtEdinoDete = broi;
				naiDete = dete;
			}
		}
		return naiDete;
	}

}
