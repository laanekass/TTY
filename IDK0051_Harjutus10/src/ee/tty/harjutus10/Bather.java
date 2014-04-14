package ee.tty.harjutus10;

/**
 * person who can visit sauna
 * 
 * @author heleen
 * 
 */
abstract class Bather implements Runnable {

	protected Sauna sauna;
	private String name;

	/**
	 * 
	 * @param sauna
	 *            - sauna that bather is going to visit
	 * @param name
	 *            - name of bather
	 */
	public Bather(Sauna sauna, String name) {
		this.sauna = sauna;
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			try {
				sauna.enter(this);
				sauna.leave(this);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @return name - name of bather
	 */
	public String getName() {
		return this.name;
	}
}
