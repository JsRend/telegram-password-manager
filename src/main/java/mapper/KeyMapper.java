package mapper;

import dto.KeyDto;
import entity.Key;

import java.time.LocalDate;

public class KeyMapper implements Mapper<Key, KeyDto>{

    private static final KeyMapper INSTANCE = new KeyMapper();

    @Override
    public KeyDto mapFrom(Key object) {
        return KeyDto.builder()
                .id(object.getId())
                .userId(object.getUserId())
                .keyName(object.getKeyName())
                .userKey(object.getUserKey())
                .createDate(object.getCreateDate())
                .difficult(object.getDifficult())
                .build();
    }

    public static KeyMapper getInstance() {
        return INSTANCE;
    }
}
