package service;

import dao.KeyDao;
import dto.CreateKeyDto;
import dto.KeyDto;
import lombok.NoArgsConstructor;
import mapper.CreateKeyMapper;
import mapper.KeyMapper;
import tgbot.jdbc.utils.PasswordGenerator;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class KeyService {
    private static final KeyService INSTANCE = new KeyService();
    private final KeyDao keyDao = KeyDao.getInstance();
    private final KeyMapper mapper = KeyMapper.getInstance();
    private final CreateKeyMapper keyMapper = CreateKeyMapper.getInstance();

    public Long getPasswordCount(Long id) {
        return keyDao.findAll(id).stream().count();
    }

    public List<KeyDto> getAllKey(Long id) {
        return keyDao.findAll(id).stream().map(mapper::mapFrom).collect(toList());
    }

    public String generatePassword(Integer length, Boolean useNumbers, Boolean useSpecialChar, Boolean useUppercase) {
        return PasswordGenerator.generatePassword(length, useNumbers, useSpecialChar, useUppercase);
    }

    public Optional<KeyDto> showKeyById(Long id) {
        return keyDao.findById(id)
                .map(mapper::mapFrom);
    }

    public boolean delete(Long id) {
        return keyDao.delete(id);
    }

    public Long create(CreateKeyDto keyDto) {
        var keyEntity = keyMapper.mapFrom(keyDto);
        keyDao.save(keyEntity);
        return keyEntity.getId();
    }

    public static KeyService getInstance() {
        return INSTANCE;
    }
}
