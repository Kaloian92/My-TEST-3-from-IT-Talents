package drugo;

import java.util.ArrayList;
import java.util.List;

import demo.Demo;
import drugo.Qice.QicaType;

public class Tendjera {
	private List<Qice> qica = new ArrayList<Qice>();

	public Tendjera() {
		this.napulniSQica();
	}

	// Methods
	private void napulniSQica() {
		for (int i = 0; i < 50; i++) {
			Qice qice = new Qice(QicaType.getRandomQiceType());
			qica.add(qice);
		}
	}

	public Qice daiQice() {
		int nomer = Demo.randomNumber(qica.size());
		Qice qice = qica.get(nomer);
		this.qica.remove(nomer);
		return qice;
	}

	public boolean isEmpty() {
		return qica.isEmpty();
	}
}
