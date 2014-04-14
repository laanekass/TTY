package ee.tty.harjutus10;

public class TestSauna {

	public static void main(String[] args) {
		Sauna sauna = new Sauna();
		new Thread(new FemaleBather(sauna, "Mari")).start();
		new Thread(new FemaleBather(sauna, "Mann")).start();
		new Thread(new FemaleBather(sauna, "Ann")).start();
		new Thread(new FemaleBather(sauna, "Mirru")).start();
		new Thread(new FemaleBather(sauna, "Ronja")).start();
		new Thread(new FemaleBather(sauna, "kairi")).start();

		new Thread(new MaleBather(sauna, "Jüri")).start();
		new Thread(new MaleBather(sauna, "Ants")).start();
		new Thread(new MaleBather(sauna, "Ats")).start();
		new Thread(new MaleBather(sauna, "Tom")).start();
		new Thread(new MaleBather(sauna, "Toomas")).start();
		new Thread(new MaleBather(sauna, "Ülar")).start();

	}

}
