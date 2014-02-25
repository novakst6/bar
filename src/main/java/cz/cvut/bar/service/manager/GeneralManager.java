package cz.cvut.bar.service.manager;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GeneralManager<E> implements IGeneralManager<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6825484075860178838L;
	
	@Autowired
    private SessionFactory sf;
    private Class<? extends E> entityClass;

    public void setEntityClass(Class<? extends E> clazz) {
        entityClass = clazz;
    }

    protected Session getCurrentSession() {
        return sf.getCurrentSession();
    }

	@Override
	public boolean add(E entity) {
		Session s = getCurrentSession();
        try {
            s.save(entity);
            s.flush(); ///flush manual
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR ADD> " + e.getMessage());
        }
        return false;

	}

	@Override
	public boolean edit(E entity) {
        Session s = getCurrentSession();
        try {
            s.update(entity);
            s.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR EDIT> " + e.getMessage());
        }
        return false;
	}

	@Override
	public boolean delete(E entity) {
        Session s = getCurrentSession();
        try {
            s.delete(entity);
            s.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR DELETE> " + e.getMessage());
        }
        return false;
	}

	@Override
	public E findById(Long id) {
		Session s = getCurrentSession();
        try {
            @SuppressWarnings("unchecked")
			E load = (E) s.load(entityClass, id);
            s.flush();
            return load;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

	}

	@Override
	public List<E> findAll() {
        Session s = getCurrentSession();
        try {
            Query q = s.createQuery("SELECT e FROM " + entityClass.getName() + " as e");
            @SuppressWarnings("unchecked")
			List<E> list = q.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList<E>();
        }
	}

}
