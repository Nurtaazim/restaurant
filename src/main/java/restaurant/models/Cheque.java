package restaurant.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_gen")
    @SequenceGenerator(name = "cheque_gen", sequenceName = "cheque_seq", allocationSize = 1)
    private long id;
    private BigDecimal price;
    private LocalDate date;
    @ManyToMany
    private List<MenuItem> menuItemList;
    @ManyToOne
    private User user;
    @PrePersist
    private void prePersist (){
        date = LocalDate.now();
    }
}
