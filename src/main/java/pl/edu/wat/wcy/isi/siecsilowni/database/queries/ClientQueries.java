package pl.edu.wat.wcy.isi.siecsilowni.database.queries;

import pl.edu.wat.wcy.isi.siecsilowni.common.Execute;
import pl.edu.wat.wcy.isi.siecsilowni.common.Handler;
import pl.edu.wat.wcy.isi.siecsilowni.database.model.Client;
import pl.edu.wat.wcy.isi.siecsilowni.database.queries.base.BaseQuery;

import javax.persistence.EntityManager;
import java.util.List;

public class ClientQueries {
    public static void getClients(Handler<List<Client>> handler) {
        new BaseQuery<List<Client>>() {
            @Override
            public List<Client> query(EntityManager em) {
                return em.createQuery("select c from Client c").getResultList();
            }
        }.runTask(handler, () -> System.out.println("nie można z bazą"));
    }

    public static void findClientByPesel(String pesel, Handler<Client> handler, Execute fail) {
        new BaseQuery<Client>() {
            @Override
            public Client query(EntityManager em) {
                return (Client) em.createQuery("select c from Client c where c.pesel = :pesel")
                        .setParameter("pesel", pesel).getResultList().stream().findFirst().orElse(null);
            }
        }.runTask(handler, fail);
    }
}
