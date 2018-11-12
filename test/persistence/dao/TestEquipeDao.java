package persistence.dao;

import java.util.List;

import Business.entitie.Singer;
import Persistence.dao.SingerDao;
import persist.pere.TU_Pere;

public class TestEquipeDao extends TU_Pere {

	private SingerDao sd;

	public void setUp() throws Exception {

		// TODO Auto-generated method stub
		super.setUp();
		sd = new SingerDao();
	}

	// [1] ---------------- TestFINDLIST -------------------------------
	public void testFindList() throws Exception {

		// # CAS NORMAUX #
		// TEST TAILLE
		List<Singer> list = sd.findList();
		int realNb = getInserter().select("select count(id) from singer").getDataAsInt();
		assertEquals(list.size(), realNb);

	}

	// [2] ------------------ TestFINDByID -----------------------------
	public void testFindById() throws Exception {

		// # CAS NORMAUX #
		// TEST ID
		Singer singer = sd.findById(3);
		assertEquals(singer.getId(), 3);

		// # CAS LIMITES #
		singer = sd.findById(0); // VALEUR N'EXISTANT PAS
		assertNull(singer);

	}

	// [3] ----------------------testCREATE-------------------------------------
	public void testCreate() throws Exception {

		// # CAS NORMAUX #
		// TEST TAILLE
		Singer singer = new Singer(0, "James", "Brown", 15000, 82);
		int nbAvant = getInserter().select("select count(id) from singer").getDataAsInt();
		sd.create(singer);
		int nbApres = getInserter().select("select count(id) from singer").getDataAsInt();
		assertEquals(nbApres, nbAvant + 1);

		// TEST CHAMPS
		Singer eFind = sd.findById(singer.getId());
		assertEquals(singer.getId(), eFind.getId());
		assertEquals(singer.getprenom(), eFind.getprenom());
		assertEquals(singer.getnom(), eFind.getnom());
		assertEquals(singer.getsalaire(), eFind.getsalaire());
		assertEquals(singer.getage(), eFind.getage());

		// # CAS LIMITES #
		Singer result = sd.create(null);
		assertNull(result);

	}

	// [4] -------------------- testUPDATEByID --------------------------------
	public void testUpdateById() throws Exception {

		// # CAS NORMAUX #
		// TEST CHAMPS
		Singer singer = new Singer(0, "Rocky", "Balboa", 11000, 34);
		sd.updateById(singer);
		Singer aFind = sd.findById(singer.getId()); // affectation sur objet aFind par la méthode FindById;
		assertEquals(singer.getId(), eFind.getId());
		assertEquals(singer.getprenom(), eFind.getprenom());
		assertEquals(singer.getnom(), eFind.getnom());
		assertEquals(singer.getsalaire(), eFind.getsalaire());
		assertEquals(singer.getage(), eFind.getage());


		// # CAS LIMITE #
		Singer result = sd.updateById(null);
		assertNull(result);
	}

	// [5] ------------------ TestDELETEByID ------------------------------------

	public void testDeleteById() throws Exception {

		// # CAS NORMAUX #
		Singer singer = new Singer(0, "James", "Brown", 15000, 82);
		sd.create(singer);
		// TEST NOMBRE
		List<Singer> list = sd.findList();
		int nbInit = list.size();
		sd.deleteById(singer.getId());
		list = sd.findList();
		assertEquals(nbInit - 1, list.size());

		// # CAS LIMITES #
		// ID N'EXISTE PAS
		sd.deleteById(0);
		list = sd.findList();
		assertEquals(list.size(), nbInit - 1);
		// ID EST NEGATIF
		sd.deleteById(-1);
		list = sd.findList();
		assertEquals(list.size(), nbInit - 1);
	}
}