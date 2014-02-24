package cz.cvut.bar.service.manager;

import java.io.Serializable;
import java.util.List;

public interface IGeneralManager<E> extends Serializable {
    public boolean add(E entity);
    public boolean edit(E entity);
    public boolean delete(E entity);
    public E findById(Long id);
    public List<E> findAll();
}
