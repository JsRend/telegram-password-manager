package mapper;

import dto.CreateUserDto;
import entity.User;

public class CreateUserMapper implements Mapper<CreateUserDto, User>{

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .names(object.getName())
                .code(object.getCode())
                .createDate(object.getCreateDate())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
