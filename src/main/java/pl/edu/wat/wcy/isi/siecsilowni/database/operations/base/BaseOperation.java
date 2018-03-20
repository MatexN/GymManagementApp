package pl.edu.wat.wcy.isi.siecsilowni.database.operations.base;

import javafx.concurrent.Task;
import pl.edu.wat.wcy.isi.siecsilowni.common.Execute;
import pl.edu.wat.wcy.isi.siecsilowni.common.Handler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseOperation {

    private static ExecutorService executor = Executors.newCachedThreadPool();

    private Void operation(Handler<EntityManager> handler) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PZ");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        handler.handle(em);

        em.getTransaction().commit();
        em.close();
        emf.close();

        return null;
    }

    protected final void runTask(Handler<EntityManager> handler, Execute success, Execute fail) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                return operation(handler);
            }
        };

        task.setOnSucceeded(event -> success.execute());
        task.setOnFailed((e) -> fail.execute());
        executor.submit(task);
    }
}
