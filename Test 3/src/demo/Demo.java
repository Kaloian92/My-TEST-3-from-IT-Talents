package demo;

import java.util.ArrayList;
import java.util.Random;

import drugo.Hladilnik;
import drugo.Masa;
import drugo.Tendjera;
import people.Baba;
import people.Bashta;
import people.Dete;
import people.InvalidPersonException;
import people.Maika;

public class Demo {

	public static final int randomNumber(int max) {
		Random r = new Random();
		return r.nextInt(max);
	}

	public static final int randomNumber(int min, int max) {
		Random r = new Random();
		return r.nextInt(max - min) + min;
	}

	public static final boolean isStringValid(String string) {
		return ((string != null) && (string.trim().length() > 0));
	}

	public static String giveRandomName() {
		String[] purviImena = { "Kaloian", "Miroslav", "Bogomil", "Georgi", "Ivan", "Martina", "Nikolai", "Aleksandur",
				"Simeon", "Mariq", "Iordan", "Vasil", "Bianka", "Spas" };
		String[] vtoriImena = { "Pavlov", "Markov", "Vladimirov", "Georgiev", "Ivanov", "Petrova", "Nikolov",
				"Aleksandrov", "Simeonov", "Zlatarski", "Iordanova", "Vasilev", "Karaivanov", "Spasov" };
		return purviImena[(int) (Math.random() * purviImena.length)] + " "
				+ vtoriImena[(int) (Math.random() * vtoriImena.length)];
	}

	public static Masa masa;
	public static Tendjera tendjera;
	public static Hladilnik hladilnik;
	public static Bashta bashta;
	public static Baba baba;
	public static ArrayList<Dete> deca;

	public static void main(String[] args) {
		Masa masa = new Masa();
		Demo.masa = masa;
		Tendjera tendjera = new Tendjera();
		Demo.tendjera = tendjera;
		Hladilnik hladilnik = new Hladilnik();
		Demo.hladilnik = hladilnik;
		try {
			Bashta bashta = new Bashta(giveRandomName());
			Demo.bashta = bashta;
		} catch (InvalidPersonException e) {
			System.out.println(e.getMessage());
		}
		try {
			Baba baba = new Baba(giveRandomName());
			Demo.baba = baba;
		} catch (InvalidPersonException e1) {
			e1.printStackTrace();
		}
		ArrayList<Dete> deca = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Dete dete;
			try {
				dete = new Dete(giveRandomName());
				deca.add(dete);
			} catch (InvalidPersonException e) {
				e.printStackTrace();
			}
		}
		Demo.deca = deca;
		Maika maika = null;
		try {
			maika = new Maika(giveRandomName());
		} catch (InvalidPersonException e) {
			e.printStackTrace();
		}

	}

}
