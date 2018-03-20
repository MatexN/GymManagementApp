package pl.edu.wat.wcy.isi.siecsilowni.database.queries;

import pl.edu.wat.wcy.isi.siecsilowni.common.Handler;
import pl.edu.wat.wcy.isi.siecsilowni.database.model.Gym;
import pl.edu.wat.wcy.isi.siecsilowni.database.queries.base.BaseQuery;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GymQueries {

    public static void getGyms(Handler<List<Gym>> handler) {
        new BaseQuery<List<Gym>>() {
            @Override
            public List<Gym> query(EntityManager em) {
                return em.createQuery("select g from Gym g").getResultList();
            }
        }.runTask(handler, () -> System.out.println("nie można z bazą"));
    }
}
