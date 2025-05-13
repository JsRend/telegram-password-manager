package validator;

import dto.CreateUserDto;
import tgbot.jdbc.utils.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto>{

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();
        //if(LocalDateFormatter.isValid(object.getData())) {
        //      validationResult.add(Error.of("code", "value"));
        //}
        //if(object.getName().isEmpty()) {
        //    validationResult.add(Error.of("invalid.name", "Name not valid"));
        //}
        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
