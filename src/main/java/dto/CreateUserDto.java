package dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class CreateUserDto {
    Long name;
    Long code;
    LocalDate createDate;
}
