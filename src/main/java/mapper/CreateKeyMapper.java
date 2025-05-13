package mapper;

import dto.CreateKeyDto;
import dto.CreateUserDto;
import entity.Key;
import entity.User;

public class CreateKeyMapper implements Mapper<CreateKeyDto, Key>{

    private static final CreateKeyMapper INSTANCE = new CreateKeyMapper();

    @Override
    public Key mapFrom(CreateKeyDto object) {
        return Key.builder()
                .userId(object.getUserId())
                .keyName(object.getKeyName())
                .userKey(object.getUserKey())
                .createDate(object.getCreateDate())
                .difficult(object.getDifficult())
                .build();
    }

    public static mapper.CreateKeyMapper getInstance() {
        return INSTANCE;
    }
}
