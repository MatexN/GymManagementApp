package pl.edu.wat.wcy.isi.siecsilowni.database.queries.base;

import javafx.concurrent.Task;
import pl.edu.wat.wcy.isi.siecsilowni.common.Execute;
import pl.edu.wat.wcy.isi.siecsilowni.common.Handler;
import pl.edu.wat.wcy.isi.siecsilowni.database.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseQuery<T> {

    private static ExecutorService executor = Executors.newCachedThreadPool();

    public T selectQuery() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PZ");
        EntityManager em = emf.createEntityManager();

        T obj = query(em);

        em.close();
        emf.close();
        return obj;
    }

    public abstract T query(EntityManager em);

    public void runTask(Handler<T> success, Execute fail) {
        Task<T> task = new Task<T>() {
            @Override
            protected T call() {
                return selectQuery();
            }
        };

        task.setOnSucceeded(event -> success.handle(task.getValue()));
        task.setOnFailed((e) -> fail.execute());
        executor.submit(task);
    }

}
