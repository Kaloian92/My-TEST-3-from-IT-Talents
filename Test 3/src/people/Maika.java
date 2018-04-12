package people;

import demo.Demo;
import drugo.Hladilnik;
import drugo.Masa;
import drugo.Qice;

public class Maika extends Person implements Runnable {
	public static final int ZABURSVNE_TIME = 2;
	public static final Hladilnik hladilnik = Demo.hladilnik;
	public static final Masa masa = Demo.masa;

	public Maika(String name) throws InvalidPersonException {
		super(name);
		new Thread(this).start();
	}

	@Override
	public String toString() {
		return "Maika [name=" + getName() + "]";
	}

	@Override
	public void run() {
		while (true) {
			Qice qice = null;
			try {
				qice = masa.takeGotovoQice();
				if(qice==null){
					System.out.println("Vsichki qica sa boqdisani");
					return;
				}
				System.out.println(this+" vze gotovoto boqdisano "+ qice);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (Demo.randomNumber(101) < 20) {
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				qice.setShareno(true);
				System.out.println(this + " napravi " + qice + " shareno ");
			}
			try {
				Thread.sleep(ZABURSVNE_TIME * 1000);
				hladilnik.addQice(qice);
				System.out.println(this+" dobavi "+qice+" v hladilnika");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
