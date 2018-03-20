package pl.edu.wat.wcy.isi.siecsilowni.database.operations;

import pl.edu.wat.wcy.isi.siecsilowni.common.Execute;
import pl.edu.wat.wcy.isi.siecsilowni.database.operations.base.BaseOperation;

public class UpdateRecord<T> extends BaseOperation {

    public void update(T t, Execute success, Execute fail) {
        runTask(em -> em.merge(t), success, fail);
    }
}
