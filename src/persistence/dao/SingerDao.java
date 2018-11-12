package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.entitie.Singer;
import persistence.exception.DaoException;
import persistence.manager.JDBCManager;

public class SingerDao implements IDAO<Singer>{

	private final static String _INSERT = "INSERT INTO `singer` (`prenom`, `nom`, `salaire`, `age`) VALUES (?,?,?,?);";
	private final static String _SELECTBYID = "SELECT * FROM `singer` WHERE `id`=?;";
	private final static String _SELECT = "SELECT * FROM `singer`;";
	private final static String _UPDATE = "UPDATE `singer` SET `prenom`=?, `nom`=?, `salaire`=?, `age`=? WHERE `id`=?;";
	private final static String _DELETE = "DELETE FROM `singer` WHERE `id`=?;";

	@Override
	public Singer create(Singer pT) throws DaoException {
		if( pT == null) {
			return null;
		}
		Connection connection = null;
		try {
			connection = JDBCManager.getInstance().openConection();
			PreparedStatement preparedStatement = connection.prepareStatement(_INSERT, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, pT.getPrenom());
			preparedStatement.setString(2, pT.getNom());
			preparedStatement.setLong(3, pT.getSalaire());
			preparedStatement.setInt(4, pT.getAge());
			preparedStatement.execute();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			while(resultSet.next()) {
				pT.setId(resultSet.getLong("GENERATED_KEY"));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				connection.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pT;
	}

	@Override
	public Singer findById(long pId) throws DaoException {
		Singer singer = null;
		Connection connection = null;
		try {
			connection = JDBCManager.getInstance().openConection();
			PreparedStatement preparedStatement = connection.prepareStatement(_SELECTBYID);
			preparedStatement.setLong(1, pId);

			ResultSet resultSet = preparedStatement.executeQuery();	
			while(resultSet.next()) {
				long id = resultSet.getLong("id");
				String prenom = resultSet.getString("prenom");
				String nom = resultSet.getString("nom");
				int age = resultSet.getInt("age");
				long salaire = resultSet.getLong("salaire");
				singer = new Singer(id, prenom, nom, age, salaire);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				connection.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return singer;
	}

	@Override
	public List<Singer> findList() throws DaoException {
		List<Singer> listSinger = new ArrayList<>();
		Connection connection = null;
		try {
			connection = JDBCManager.getInstance().openConection();
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(_SELECT);	
			while(resultSet.next()) {
				long id = resultSet.getLong("id");
				String prenom = resultSet.getString("prenom");
				String nom = resultSet.getString("nom");
				int age = resultSet.getInt("age");
				long salaire = resultSet.getLong("salaire");
				listSinger.add(new Singer(id, prenom, nom, age, salaire));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				connection.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listSinger;
	}

	@Override
	public Singer updateById(Singer pT) throws DaoException {
		if( pT == null) {
			return null;
		}
		Connection connection = null;
		try {
			connection = JDBCManager.getInstance().openConection();
			PreparedStatement preparedStatement = connection.prepareStatement(_UPDATE);
			preparedStatement.setString(1, pT.getPrenom());
			preparedStatement.setString(2, pT.getNom());
			preparedStatement.setLong(3, pT.getSalaire());
			preparedStatement.setInt(4, pT.getAge());
			preparedStatement.setLong(5, pT.getId());
			preparedStatement.execute();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				connection.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pT;
	}

	@Override
	public void deleteById(long pId) throws DaoException {
		Connection connection = null;
		try {
			connection = JDBCManager.getInstance().openConection();
			PreparedStatement preparedStatement = connection.prepareStatement(_DELETE);
			preparedStatement.setLong(1, pId);
			preparedStatement.execute();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				connection.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
