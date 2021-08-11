package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admins;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminsDao {

    private static final String CREATE_ADMINS_QUERY = "INSERT INTO admins(first_name,last_name,email,password,superadmin,enable) VALUES (?,?,?,?,?,?);";
    private static final String DELETE_ADMINS_QUERY = "DELETE FROM admins where id = ?;";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMINS_QUERY = "SELECT * FROM admins where id = ?;";
    private static final String UPDATE_ADMINS_QUERY = "UPDATE admins SET first_name = ? , last_name = ?, email = ?, password = ?, superadmin = ?, enable = ? WHERE	id = ?;";
    private static final String GET_ADMIN_BY_EMAIl = "SELECT * from admins where email = ?;";

    public Admins readAdmins(Integer adminsid) {
        Admins admins = new Admins();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMINS_QUERY)
        ) {
            statement.setInt(1, adminsid);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admins.setId(resultSet.getInt("id"));
                    admins.setFirst_name(resultSet.getString("first_name"));
                    admins.setLast_name(resultSet.getString("last_name"));
                    admins.setEmail(resultSet.getString("email"));
                    admins.setPassword(resultSet.getString("password"));
                    admins.setSuperadmin(resultSet.getInt("superadmin"));
                    admins.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;

    }

    public List<Admins> findAll() {
        List<Admins> adminsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admins adminsToAdd = new Admins();
                adminsToAdd.setId(resultSet.getInt("id"));
                adminsToAdd.setFirst_name(resultSet.getString("first_name"));
                adminsToAdd.setLast_name(resultSet.getString("last_name"));
                adminsToAdd.setEmail(resultSet.getString("email"));
                adminsToAdd.setPassword(resultSet.getString("password"));
                adminsToAdd.setSuperadmin(resultSet.getInt("superadmin"));
                adminsToAdd.setEnable(resultSet.getInt("enable"));
                adminsList.add(adminsToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminsList;

    }
// TUTAJ HASH
    public Admins create(Admins admins) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_ADMINS_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, admins.getFirst_name());
            insertStm.setString(2, admins.getLast_name());
            insertStm.setString(3, admins.getEmail());
            insertStm.setString(4, BCrypt.hashpw(admins.getPassword(), BCrypt.gensalt()));
            insertStm.setInt(5, admins.getSuperadmin());
            insertStm.setInt(6, admins.getEnable());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    admins.setId(generatedKeys.getInt(1));
                    return admins;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer adminsId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMINS_QUERY)) {
            statement.setInt(1, adminsId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Admins admins) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMINS_QUERY)) {
            statement.setString(1, admins.getFirst_name());
            statement.setString(2, admins.getLast_name());
            statement.setString(3, admins.getEmail());
            statement.setString(4, BCrypt.hashpw(admins.getPassword(),BCrypt.gensalt()));
            statement.setInt(5, admins.getSuperadmin());
            statement.setInt(6, admins.getEnable());
            statement.setInt(7, admins.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Admins findAdminsByEmail(String email){
        try(Connection connection = DbUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement(GET_ADMIN_BY_EMAIl);
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Admins admins = new Admins();
                admins.setId(resultSet.getInt("id"));
                admins.setFirst_name(resultSet.getString("first_name"));
                admins.setLast_name(resultSet.getString("last_name"));
                admins.setEmail(resultSet.getString("email"));
                admins.setPassword(resultSet.getString("password"));
                admins.setSuperadmin(resultSet.getInt("superadmin"));
                admins.setEnable(resultSet.getInt("enable"));
                return admins;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }



}