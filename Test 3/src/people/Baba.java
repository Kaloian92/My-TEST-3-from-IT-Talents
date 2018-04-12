package people;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import demo.Demo;

public class Baba extends Person implements Runnable {

	public Baba(String name) throws InvalidPersonException {
		super(name);
		Thread t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(15 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int broiBoqdisaniQica = Demo.hladilnik.broiBoqdisaniQica();
			int broiShareniQica = Demo.hladilnik.broiShareniQica();
			int broiGushiZeleniQica = Demo.hladilnik.broiGushiZeleniQica();
			String naiBoqdisvashtBurkan = Demo.hladilnik.naiBoqdisvashtBurkan();
			Dete naiBoqdisvashtoDete = Demo.hladilnik.naiBoqdisvashtoDete();

			System.out.println();
			System.out.println("====================BABA TIME====================");
			System.out.println("Vsichki boqdisani qica sa " + broiBoqdisaniQica + " na broi");
			System.out.println("Vsichki shareni qica sa " + broiShareniQica + " na broi");
			System.out.println("Vsichki gushi qica koito sa zeleni sa " + broiGushiZeleniQica + " na broi");
			System.out.println("Burkana boqdisal nai-mnogo qica e : " + naiBoqdisvashtBurkan);
			System.out.println("Deteto boqdisalo nai-mnogo qica e : " + naiBoqdisvashtoDete);
			System.out.println();
			
			try (FileWriter fw = new FileWriter("egg-report-" + LocalDate.now() + ".txt")) {
				fw.write('\n');
				fw.write("====================BABA TIME====================");
				fw.write('\n');
				fw.write("Vsichki boqdisani qica sa " + broiBoqdisaniQica + " na broi");
				fw.write('\n');
				fw.write("Vsichki shareni qica sa " + broiShareniQica + " na broi");
				fw.write('\n');
				fw.write("Vsichki gushi qica koito sa zeleni sa " + broiGushiZeleniQica + " na broi");
				fw.write('\n');
				fw.write("Burkana boqdisal nai-mnogo qica e : " + naiBoqdisvashtBurkan);
				fw.write('\n');
				fw.write("Deteto boqdisalo nai-mnogo qica e : " + naiBoqdisvashtoDete);
				fw.write('\n');
				fw.write('\n');
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
