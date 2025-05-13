package dao;

import entity.User;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import tgbot.jdbc.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Long, User>{

    private static final UserDao INSTANCE = new UserDao();

    public static final String SAVE_SQL = """
            INSERT INTO storage.user (names, code, create_date)
            VALUES (?, ?, ?);
            """;

    public static final String UPDATE_SQL = """
            UPDATE storage.user
            SET code = ?,
                create_date = ?
            WHERE names = ?
            """;

    public static final String UPDATE_MASTER_SQL = """
            UPDATE storage.user
            SET names = ?,
                code = ?,
            WHERE id = ?
            """;

    public static final String DELETE_SQL = """
            """;

    public static final String FIND_BY_ID_SQL = """
            SELECT id, names, create_date
            FROM storage.user
            WHERE names = ?;
            """;

    public static final String FIND_BY_ID_AND_CODE_SQL = """
            SELECT id, names, code, create_date
            FROM storage.user
            WHERE names = ? AND code = ?;
            """;


    @Override
    @SneakyThrows
    public User save(User entity) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)){

            prepareStatement.setLong(1, entity.getNames());
            prepareStatement.setLong(2, entity.getCode());
            prepareStatement.setObject(3, entity.getCreateDate());

            prepareStatement.executeUpdate();

            var generatedKeys = prepareStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong("id"));
            }
            return entity;
        }
    }

    @Override
    @SneakyThrows
    public void update(User entity) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(UPDATE_SQL)) {


            prepareStatement.setLong(1, entity.getCode());
            prepareStatement.setObject(2, entity.getCreateDate());
            prepareStatement.setLong(3, entity.getNames());

            prepareStatement.executeUpdate();
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    @SneakyThrows
    public Optional<User> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            prepareStatement.setLong(1, id);

            var resultSet = prepareStatement.executeQuery();
            System.out.println(resultSet.toString());
            User userNew = null;

            if (resultSet.next()) {
                userNew = buildUser(resultSet);
            }

            return Optional.ofNullable(userNew);
        }
    }

    @SneakyThrows
    public Optional<User> findByIdAndCode(Long id, Long code) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_BY_ID_AND_CODE_SQL)) {

            prepareStatement.setLong(1, id);
            prepareStatement.setLong(2, code);

            var resultSet = prepareStatement.executeQuery();

            User user = null;

            if (resultSet.next()) {
                //user = buildUser(resultSet);
                user = User.builder()
                        .id(resultSet.getLong("id"))
                        .names(resultSet.getLong("names"))
                        .code(resultSet.getLong("code"))
                        .createDate(resultSet.getDate("create_date").toLocalDate())
                        .build();
            }

            return Optional.ofNullable(user);
        }
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .names(resultSet.getLong("names"))
                .createDate(resultSet.getDate("create_date").toLocalDate())
                .build();
    }
}
