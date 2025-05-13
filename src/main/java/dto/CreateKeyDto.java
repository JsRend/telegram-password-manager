package dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class CreateKeyDto {
    Long userId;
    String keyName;
    String userKey;
    LocalDate createDate;
    Integer difficult;
}
