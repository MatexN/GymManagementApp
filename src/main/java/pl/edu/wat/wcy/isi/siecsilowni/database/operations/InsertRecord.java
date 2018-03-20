package pl.edu.wat.wcy.isi.siecsilowni.database.operations;

import pl.edu.wat.wcy.isi.siecsilowni.common.Execute;
import pl.edu.wat.wcy.isi.siecsilowni.database.operations.base.BaseOperation;

public class InsertRecord<T> extends BaseOperation {

    public void insert(T t, Execute success, Execute fail) {
        runTask(em -> em.persist(t), success, fail);
    }
}