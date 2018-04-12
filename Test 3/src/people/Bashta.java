package people;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import demo.Demo;
import drugo.Qice;

public class Bashta extends Person {

	public Bashta(String name) throws InvalidPersonException {
		super(name);
	}

	public void zapishi() {
		System.out.println();
		System.out.println("====================BASHTATA ZAPISVA====================");
		System.out.println();
		StringBuilder sb = new StringBuilder();
		for (Qice.QicaType type : Qice.QicaType.values()) {
			sb.append(type + " qica :");
			sb.append('\n');
			for (Qice qice : Demo.hladilnik.getKori(type)) {
				sb.append(qice);
				sb.append('\n');
			}
		}
		String vsichkoVHladilnika = sb.toString();
		try (FileWriter fw = new FileWriter(new File("zapiski_na_bashtata.txt"))) {
			fw.write(vsichkoVHladilnika);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
