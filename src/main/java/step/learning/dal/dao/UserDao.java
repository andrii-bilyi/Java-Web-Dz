package step.learning.dal.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.dal.dto.User;
import step.learning.services.db.DbService;

import java.sql.*;
import java.util.UUID;

@Singleton
public class UserDao {
    private final DbService dbService;
    @Inject
    public UserDao(DbService dbService) {
        this.dbService = dbService;
    }

    public  User getUserByToken(String token){
        String sql = "SELECT t.*, u.* " +
                "FROM Tokens t JOIN Users u ON t.user_id = u.user_id " +
                "WHERE t.token_id = ? AND t.token_expires > CURRENT_TIMESTAMP " +
                "LIMIT 1";
        try(PreparedStatement prep = dbService.getConnection().prepareStatement(sql)){
            prep.setString(1, token);
            ResultSet res = prep.executeQuery();
            if(res.next()){ //якщо є дані
                return User.fromResultSet(res);
            }
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
            System.out.println(sql);
        }
        return null;
    }
    public String generateToken(User user){
        String existingToken = getValidTokenForUser(user);
        if (existingToken != null) {
            // Повертаємо існуючий токен з подовженням терміну дії
            extendTokenExpiration(existingToken);
            return existingToken;
        } else {
            // Генеруємо новий токен
            String sql = "INSERT INTO Tokens(token_id, user_id, token_expires) VALUES(?,?,?)";
            try(PreparedStatement prep = dbService.getConnection().prepareStatement(sql)){
                String token = UUID.randomUUID().toString();
                prep.setString(1, token);
                prep.setString(2, user.getId().toString());
                prep.setTimestamp(3, new Timestamp(new java.util.Date().getTime() + 60 * 5 * 1000)); // + 5 хв
                prep.executeUpdate();
                return token;
            }
            catch (SQLException ex){
                System.err.println(ex.getMessage());
                System.out.println(sql);
            }
        }
        return null;

        /*
        *
        String sql = "INSERT INTO Tokens(token_id, user_id, token_expires) VALUES(?,?,?)";
        try(PreparedStatement prep = dbService.getConnection().prepareStatement(sql)){
            String token = UUID.randomUUID().toString();
            prep.setString(1, token);
            prep.setString(2, user.getId().toString());
            prep.setTimestamp(3, new Timestamp(new java.util.Date().getTime() + 60 * 5 * 1000)); // + 5 хв
            prep.executeUpdate();
            return token;
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
            System.out.println(sql);
        }
        return null;
        */
    }

    private String getValidTokenForUser(User user) {
        String sql = "SELECT token_id FROM Tokens WHERE user_id = ? AND token_expires > NOW()";
        try (PreparedStatement prep = dbService.getConnection().prepareStatement(sql)) {
            prep.setString(1, user.getId().toString());
            try (ResultSet resultSet = prep.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("token_id");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println(sql);
        }
        return null;
    }

    private void extendTokenExpiration(String token) {
        String sql = "SELECT token_expires FROM Tokens WHERE token_id = ?";
        try (PreparedStatement prep = dbService.getConnection().prepareStatement(sql)) {
            prep.setString(1, token);
            try (ResultSet resultSet = prep.executeQuery()) {
                if (resultSet.next()) {
                    Timestamp currentExpiration = resultSet.getTimestamp("token_expires");
                    long currentExpirationTime = currentExpiration.getTime();
                    // Передбачаємо, що стандартний термін дії - 5 хвилин
                    long extendedTime = currentExpirationTime + (5 * 60 * 1000/2); // Подовження на половину терміну
                    Timestamp newExpirationTime = new Timestamp(extendedTime);
                    String updateSql = "UPDATE Tokens SET token_expires = ? WHERE token_id = ?";
                    try (PreparedStatement updatePrep = dbService.getConnection().prepareStatement(updateSql)) {
                        updatePrep.setTimestamp(1, newExpirationTime);
                        updatePrep.setString(2, token);
                        updatePrep.executeUpdate();
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println(sql);
        }
    }

    public User getUserByEmail(String email){
        String sql = "SELECT u.* FROM Users u WHERE u.user_email = ?";
        try(PreparedStatement prep = dbService.getConnection().prepareStatement(sql)){
            prep.setString(1, email);
            ResultSet res = prep.executeQuery();
            if(res.next()){ //якщо є дані (значить коривтувача знайдено)
                return User.fromResultSet(res);
            }
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
            System.out.println(sql);
        }
        return null;
    }
    public boolean registerUser(User user){
        if(user == null) return false;
        if(user.getId() == null) user.setId(UUID.randomUUID());

        String sql = "INSERT INTO Users" +
                "(user_id, user_name, user_email, user_avatar, user_salt, user_dk)" +
                "VALUES(?,?,?,?,?,?)";
        try(PreparedStatement prep = dbService.getConnection().prepareStatement(sql)){
            prep.setString(1, user.getId().toString());
            prep.setString(2, user.getName());
            prep.setString(3, user.getEmail());
            prep.setString(4, user.getAvatar());
            prep.setString(5, user.getSalt());
            prep.setString(6, user.getDerivedKey());
            prep.executeUpdate();
            return true;
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
            System.out.println(sql);
            return false;
        }
    }

    public boolean installTable(){
        String sql = "CREATE TABLE Users (" +
                "user_id CHAR(36) PRIMARY KEY DEFAULT(UUID())," +
                "user_name VARCHAR(64) NOT NULL," +
                "user_email VARCHAR(128) NOT NULL," +
                "user_avatar VARCHAR(64) NULL," +
                "user_salt CHAR(32) NOT NULL," +
                "user_dk CHAR(32) NOT NULL," +
                "user_created DATETIME  NOT NULL  DEFAULT CURRENT_TIMESTAMP," +
                "user_deleted DATETIME NULL" +
                ") ENGINE = INNODB, DEFAULT CHARSET = utf8mb4";
        try(Statement statement = dbService.getConnection().createStatement())  {
            statement.executeUpdate(sql);
            return true;
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
            System.out.println(sql);
            return false;
        }
    }
}
