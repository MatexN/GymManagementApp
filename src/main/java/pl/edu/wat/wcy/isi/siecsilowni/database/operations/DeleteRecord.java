package pl.edu.wat.wcy.isi.siecsilowni.database.operations;

import pl.edu.wat.wcy.isi.siecsilowni.common.Execute;
import pl.edu.wat.wcy.isi.siecsilowni.database.operations.base.BaseOperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeleteRecord<T> extends BaseOperation {

    public void delete(T t, Execute success, Execute fail) {
        runTask(em -> em.remove(em.contains(t) ? t : em.merge(t)), success, fail);
    }
}
