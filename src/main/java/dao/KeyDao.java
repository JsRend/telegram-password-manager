package dao;

import entity.Key;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import tgbot.jdbc.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class KeyDao implements Dao<Long, Key>{

    private static final KeyDao INSTANCE = new KeyDao();

    private static final String SAVE_SQL = """
            INSERT INTO storage.keys (user_id, key_name, user_key, create_date, difficult)
            VALUES (?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE storage.keys
            SET user_id = ?,
                key_name = ?,
                user_key = ?,
                create_date = ?,
                difficult = ?
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE
            FROM storage.keys
            WHERE id = ?
            """;

    private static final String FIND_BY_KEY_ID_SQL = """
            SELECT
                id,
                user_id,
                key_name,
                user_key,
                create_date,
                difficult
            FROM storage.keys
            WHERE id = ?
            """;

    private static final String FIND_ALL_BY_ID_SQL = """
            SELECT
                id,
                user_id,
                key_name,
                user_key,
                create_date,
                difficult
            FROM storage.keys
            WHERE user_id = ?
            """;

    @SneakyThrows
    @Override
    public Key save(Key entity) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)){

            prepareStatement.setLong(1, entity.getUserId());
            prepareStatement.setString(2, entity.getKeyName());
            prepareStatement.setString(3, entity.getUserKey());
            prepareStatement.setObject(4, entity.getCreateDate());
            prepareStatement.setObject(5, entity.getDifficult());

            prepareStatement.executeUpdate();

            var generatedKeys = prepareStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong("id"));
            }
            return entity;
        }
    }

    @Override
    public void update(Key entity) {

    }

    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(DELETE_SQL)) {

            prepareStatement.setLong(1, id);

            var resultSet = prepareStatement.executeUpdate();

            return resultSet > 0;
        }
    }

    @SneakyThrows
    @Override
    public Optional<Key> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_BY_KEY_ID_SQL)) {

            prepareStatement.setLong(1, id);

            var resultSet = prepareStatement.executeQuery();
            System.out.println(resultSet.toString());
            Key keyNew = null;

            if (resultSet.next()) {
                keyNew = buildKey(resultSet);
            }

            return Optional.ofNullable(keyNew);
        }
    }

    @Override
    public List<Key> findAll() {
        return List.of();
    }

    @SneakyThrows
    public List<Key> findAll(Long userId) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_BY_ID_SQL)) {

            prepareStatement.setLong(1, userId);

            var resultSet = prepareStatement.executeQuery();
            List<Key> keys = new ArrayList<>();
            while(resultSet.next()) {
                keys.add(Key.builder()
                                .id(resultSet.getLong("id"))
                                .userId(resultSet.getLong("user_id"))
                                .keyName(resultSet.getString("key_name"))
                                .userKey(resultSet.getString("user_key"))
                                .createDate(resultSet.getObject("create_date", LocalDate.class))
                                .difficult(resultSet.getInt("difficult"))
                        .build());
            }

            return keys;
        }
    }

    public static KeyDao getInstance() {
        return INSTANCE;
    }

    private Key buildKey(ResultSet resultSet) throws SQLException {
        return Key.builder()
                .id(resultSet.getLong("id"))
                .userId(resultSet.getLong("user_id"))
                .keyName(resultSet.getString("key_name"))
                .userKey(resultSet.getString("user_key"))
                .createDate(resultSet.getObject("create_date", LocalDate.class))
                .difficult(resultSet.getInt("difficult"))
                .build();
    }
}
