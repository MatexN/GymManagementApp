package pl.edu.wat.wcy.isi.siecsilowni.database.queries;

import pl.edu.wat.wcy.isi.siecsilowni.common.Handler;
import pl.edu.wat.wcy.isi.siecsilowni.database.model.User;
import pl.edu.wat.wcy.isi.siecsilowni.database.queries.base.BaseQuery;

import javax.persistence.EntityManager;
import java.util.List;

public class UserQueries {

    public static void getUser(String userName, Handler<User> handler) {
        new BaseQuery<User>() {
            @Override
            public User query(EntityManager em) {
                List<User> users = em.createQuery("select u from User u where u.userName = :name")
                        .setParameter("name", userName).getResultList();
                return users.stream().findFirst().orElse(null);
            }
        }.runTask(handler, () -> System.out.println("nie można z bazą"));
    }
}
