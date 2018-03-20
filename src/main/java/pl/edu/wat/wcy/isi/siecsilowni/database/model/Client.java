package pl.edu.wat.wcy.isi.siecsilowni.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.wat.wcy.isi.siecsilowni.GymApp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String secondName;

    @Column(unique = true)
    private String pesel;

    @Column
    private LocalDate subscriptionCardStartDate;

    @Column
    private String amountOfDays;

    public String hasValidSubscriptionCard() {
        try {
            long amount = Long.parseLong(amountOfDays);
            LocalDate startDate = subscriptionCardStartDate;

            if (startDate.isBefore(LocalDate.now()) &&
                    startDate.plusDays(amount).isAfter(LocalDate.now())) {
                return GymApp.resource.getString("client.db.yes");

            }
            return GymApp.resource.getString("client.db.no");

        } catch (Exception e) {
            System.out.println("ERROR");
            return GymApp.resource.getString("client.db.error");
        }
    }
}
