package pojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Arrays;
import java.util.List;

/**
 * POJO класс сущности Entity
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    @JsonIgnore
    private int id;

    @Builder.Default
    private Addition addition = Addition
            .builder()
            .additionalInfo("Какая-то дополнительная информация")
            .additionalNumber(576).build();

    @Builder.Default
    private List<Integer> importantNumbers = Arrays.asList(42, 87, 15);

    @Builder.Default
    private String title = "Очередная некая сущность";

    @Builder.Default
    private boolean verified = true;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Addition {
        @JsonIgnore
        private int id;

        @Builder.Default
        private String additionalInfo = "Какая-то дополнительная информация";

        @Builder.Default
        private int additionalNumber = 576;
    }
}
