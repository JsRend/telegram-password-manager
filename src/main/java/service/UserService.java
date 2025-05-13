package service;

import dao.UserDao;
import dto.CreateUserDto;
import dto.UserDto;
import entity.User;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.CreateUserMapper;
import mapper.UserMapper;
import validator.CreateUserValidator;
import validator.ValidationResult;

import java.util.Optional;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    public Optional<UserDto> login(Long id, Long code) {
       return userDao.findByIdAndCode(id, code)
               .map(userMapper::mapFrom);
    }

    public Optional<UserDto> checkAvailability(Long id) {
        return userDao.findById(id)
                .map(userMapper::mapFrom);
    }

    public Long create(CreateUserDto userDto) {

        var userEntity = createUserMapper.mapFrom(userDto);

        userDao.save(userEntity);
        return userEntity.getId();
    }

    public Long changeCode(CreateUserDto userDto) {

        var userEntity = createUserMapper.mapFrom(userDto);

        userDao.update(userEntity);
        return userEntity.getId();
    }


    public static UserService getInstance() {
        return INSTANCE;
    }
}
