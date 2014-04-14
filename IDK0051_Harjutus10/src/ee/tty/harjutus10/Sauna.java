package ee.tty.harjutus10;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * Sauna is a limited resource, there can be up to 4 bathers in same gender
 * @author heleen
 * 
 */
public class Sauna {
	private int maxNoPeople = 4;
	private List<Bather> saunaQueue = new LinkedList<Bather>();
	private List<Bather> saunaUsers = new LinkedList<Bather>();

	/**
	 * 
	 * @param bather
	 * @throws InterruptedException
	 */
	public void enter(Bather bather) throws InterruptedException {
		System.out.println(bather.getName() + " is trying to enter to sauna");
		Bather batherEntering;
		synchronized (this) {
			saunaQueue.add(bather);
			batherEntering = leave(null);
		}
		if (bather != batherEntering) {
			synchronized (bather) {
				bather.wait();
			}
		}
	}

	/**
	 * 
	 * @param enterer
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized Bather leave(Bather enterer)
			throws InterruptedException {
		if (enterer != null) {
			removeBather(enterer, saunaUsers);
		}

		Bather bather = null;

		if (saunaQueue.size() == 0 || saunaUsers.size() >= maxNoPeople) {
			System.out
					.println("Sauna is full or there is no queue; number of sauna users "
							+ saunaUsers.size()
							+ "; number of people in queue: "
							+ saunaQueue.size());
			bather = null;
		} else {
			Bather occupiersGender = ((LinkedList<Bather>) saunaUsers).peek();
			Bather nextPerson = ((LinkedList<Bather>) saunaQueue).peek();
			boolean isMaleInSauna = false;
			if (occupiersGender != null
					&& occupiersGender.getClass() == MaleBather.class) {
				isMaleInSauna = true;
			} else {
				if (nextPerson.getClass() == MaleBather.class)
					isMaleInSauna = true;
			}
			if (isMaleInSauna) {
				System.out.println("There is " + saunaUsers.size()
						+ " men in sauna");
			} else {
				System.out.println("There is " + saunaUsers.size()
						+ " women in sauna");
			}
			if (nextPerson != null) {
				if (nextPerson.getClass() == MaleBather.class
						&& (isMaleInSauna || saunaUsers.size() == 0)) {

					bather = nextPerson;
				} else if (nextPerson.getClass() != MaleBather.class
						&& (!isMaleInSauna || saunaUsers.size() == 0)) {
					isMaleInSauna = false;
					bather = nextPerson;
				} else {
					bather = null;
				}
			}

		}

		if (bather != null) {
			System.out.println(bather.getName() + " enters to sauna");
			if (bather.getClass() == MaleBather.class) {
				removeBather(bather, saunaQueue);
			} else {
				removeBather(bather, saunaQueue);
			}
			saunaUsers.add(bather);
			System.out.println(bather.getName() + " is enjoing sauna");
			Thread.sleep(10);
			synchronized (bather) {
				bather.notify();
			}
		}

		return bather;

	}

	/**
	 * 
	 * @param bather
	 * @param saunaUsers
	 * @throws InterruptedException
	 */
	private void removeBather(Bather bather, List<Bather> saunaUsers)
			throws InterruptedException {
		System.out.println(bather.getName() + " is leaving sauna");
		Iterator<Bather> i = saunaUsers.iterator();
		while (i.hasNext()) {
			if (i.next() == bather) {
				i.remove();
				System.out.println(bather.getName() + " is cooling down");
			}
		}

	}

}
