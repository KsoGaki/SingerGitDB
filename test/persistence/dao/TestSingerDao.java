package persistence.dao;

import java.util.List;

import Business.entitie.Cycliste;
import Business.entitie.Equipe;
import Persistence.dao.CyclisteDao;
import Persistence.dao.EquipeDao;
import persist.pere.TU_Pere;

public class TestSingerDao extends TU_Pere {

	private CyclisteDao cd;
	private EquipeDao ed;

	public void setUp() throws Exception {

		// TODO Auto-generated method stub
		super.setUp();
		cd = new CyclisteDao();
		ed = new EquipeDao();
	}

	// [1] ---------------- TestFINDLIST -------------------------------
	public void testFindList() throws Exception {

		// CAS NORMAUX
		List<Cycliste> list = cd.findList();
		int realNb = getInserter().select("select count(id) from cycliste").getDataAsInt();
		assertEquals(list.size(), realNb);

	}

	// [2] ------------------ TestFINDByID -----------------------------
	public void testFindById() throws Exception {

		// CAS NORMAUX
		Cycliste cycliste = cd.findById(1);
		assertEquals(cycliste.getId(), 1);
		// CAS LIMITES
		cycliste = cd.findById(0); // VALEUR N'EXISTANT PAS
		assertNull(cycliste);

	}

	// [3] ----------------------testCREATE-------------------------------------
	public void testCreate() throws Exception {

		// CAS NORMAL
		
		Equipe equipe = ed.findById(1);
		Cycliste cycliste = new Cycliste(0, "Virenque", equipe, 4); // construction cycliste
		int nbAvant = getInserter().select("select count(id) from cycliste").getDataAsInt();
		cd.create(cycliste); // création cycliste
		int nbApres = getInserter().select("select count(id) from cycliste").getDataAsInt();
		// test taille
		assertEquals(nbApres, nbAvant + 1);
		 // affectation sur objet cFind par la méthode FindById
		Cycliste cFind = cd.findById(cycliste.getId());

	// LES CHAMPS CYCLISTE
	 	assertEquals(cycliste.getId(), cFind.getId());
		assertEquals(cycliste.getName(), cFind.getName());
		assertEquals(cycliste.getNombre_velos(), cFind.getNombre_velos());

		// LES CHAMPS EQUIPE
		assertEquals(cycliste.getEquipe().getId(), cFind.getEquipe().getId());
		assertEquals(cycliste.getEquipe().getId(), cFind.getEquipe().getId());
		assertEquals(cycliste.getEquipe().getName(), cFind.getEquipe().getName());
		assertEquals(cycliste.getEquipe().getBugdet(), cFind.getEquipe().getBugdet());
		
		// CAS LIMITE
		Cycliste result = cd.create(null);
		assertNull(result);
}

	// [4] -------------------- testUPDATEByID --------------------------------
	public void testUpdateById() throws Exception {
		// CAS NORMAL
		
		Equipe equipe = ed.findById(1);

		Cycliste cycliste = new Cycliste(1, "Contador", equipe, 15000);
		cd.updateById(cycliste);
		Cycliste cFind = cd.findById(cycliste.getId()); // affectation sur objet eFind par la méthode FindById;

		// CHAMPS CYCLISTE
		assertEquals(cycliste.getId(), cFind.getId());
		assertEquals(cycliste.getName(), cFind.getName());
		assertEquals(cycliste.getNombre_velos(), cFind.getNombre_velos());

		

		// CHAMPS EQUIPE
		assertEquals(cycliste.getEquipe().getId(), cFind.getEquipe().getId());
		assertEquals(cycliste.getEquipe().getName(), cFind.getEquipe().getName());
		assertEquals(cycliste.getEquipe().getBugdet(), cFind.getEquipe().getBugdet());

		// # CAS LIMITE #
		Cycliste result = cd.updateById(null);
		assertNull(result);
	}

	// [5] ------------------ TestDELETEByID ------------------------------------
	public void testDeleteById() throws Exception {
		// # CAS NORMAUX #
		Equipe equipe = ed.findById(1);
		Cycliste cycliste = new Cycliste(0, "Virenque", equipe, 4);
		cd.create(cycliste); 
		// TEST NOMBRE
		List<Cycliste> list = cd.findList();
		int nbInit = list.size();
		cd.deleteById(cycliste.getId());
		list = cd.findList();
		assertEquals(nbInit - 1, list.size());
		// # CAS LIMITES #
		// ID N'EXISTE PAS
		ed.deleteById(0);
		list = cd.findList();
		assertEquals(list.size(), nbInit - 1);
		// ID EST NEGATIF
		cd.deleteById(-1);
		list = cd.findList();
		assertEquals(list.size(), nbInit - 1);

	}
}
