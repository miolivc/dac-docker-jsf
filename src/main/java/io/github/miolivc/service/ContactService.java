
package io.github.miolivc.service;

import io.github.miolivc.domain.Contact;
import io.github.miolivc.repository.Repository;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ContactService {
    
    @Inject
    private Repository repository;
    
    public void add(Contact contact) {
        repository.add(contact);
    }
    
    public void remove(Contact contact) {
        repository.remove(contact);
    }
    
    public void edit(Contact contact) {
        repository.edit(contact);
    }
    
    public Contact getOne(int id) {
        return repository.get(id);
    }
    
    public List<Contact> getAll() {
        return repository.get();
    }
    
}
