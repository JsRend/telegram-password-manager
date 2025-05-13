package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Key {
    private Long id;
    private Long userId;
    private String keyName;
    private String userKey;
    private LocalDate createDate;
    private Integer difficult;
}
