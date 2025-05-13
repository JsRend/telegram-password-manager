package dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Long id;
    Long names;
    Long code;
    LocalDate createDate;
}
