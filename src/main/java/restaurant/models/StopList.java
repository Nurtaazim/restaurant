package restaurant.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StopList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stopList_gen")
    @SequenceGenerator(name = "stopList_gen", sequenceName = "stopList_seq", allocationSize = 1)
    private long id;
    private String reason;
    private LocalDate date;
    @OneToOne()
    private MenuItem menuItem;

    @PrePersist
    private void prePersist (){
        date = LocalDate.now();
    }
}
