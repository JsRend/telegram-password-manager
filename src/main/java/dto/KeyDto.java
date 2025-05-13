package dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class KeyDto {
    Long id;
    Long userId;
    String keyName;
    String userKey;
    LocalDate createDate;
    Integer difficult;
}
