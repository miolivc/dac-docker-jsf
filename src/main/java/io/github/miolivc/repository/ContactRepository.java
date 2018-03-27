
package io.github.miolivc.repository;

import io.github.miolivc.domain.Contact;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Local
@Stateless
public class ContactRepository implements Repository {
    
    @PersistenceContext
    private EntityManager manager;
    
    @Override
    public void add(Contact contact) {
        manager.persist(contact);
    }
    
    @Override
    public void remove(Contact contact) {
        manager.remove(manager.merge(contact));
    }
    
    @Override
    public void edit(Contact contact) {
        manager.merge(contact);
    }
    
    @Override
    public Contact get(int id) {
        return manager
                .createQuery("SELECT c FROM Contact c WHERE c.id = :id", Contact.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    @Override
    public List<Contact> get() {
        return manager
                .createQuery("SELECT c FROM Contact c ORDER BY c.name ASC", Contact.class)
                .getResultList();
    }
}
