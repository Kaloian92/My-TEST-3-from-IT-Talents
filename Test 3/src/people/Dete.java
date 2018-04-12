package people;

import demo.Demo;
import drugo.Masa;
import drugo.Qice;
import drugo.Tendjera;

public class Dete extends Person implements Runnable {

	private static final Masa masa = Demo.masa;
	private static final Tendjera tendjera = Demo.tendjera;

	public Dete(String name) throws InvalidPersonException {
		super(name);
		new Thread(this).start();
	}

	@Override
	public String toString() {
		return "Dete [name=" + getName() + "]";
	}

	@Override
	public void run() {
		while (!tendjera.isEmpty()) {
			Qice qice = tendjera.daiQice();
			qice.setKoeDeteGoBoqdisa(this);
			System.out.printf("%s vze %s\n", this, qice);
			masa.addQice(qice);
			System.out.printf("%s sloji %s v burkan\n", this, qice);
		}
		System.out.println("Qicata svurshiha");
	}

}
