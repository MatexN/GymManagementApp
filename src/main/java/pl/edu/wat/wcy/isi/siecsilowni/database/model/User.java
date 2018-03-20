package pl.edu.wat.wcy.isi.siecsilowni.database.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String userName;

    @Column
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean isCorrect(String repeatPassword) {
        return !userName.isEmpty() && !password.isEmpty() && password.equals(repeatPassword);
    }
}
