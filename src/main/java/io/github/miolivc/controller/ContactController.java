
package io.github.miolivc.controller;

import io.github.miolivc.domain.Contact;
import io.github.miolivc.service.ContactService;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("contacts")
@RequestScoped
public class ContactController {
    
    @Inject
    private ContactService service;
    private Contact contact = new Contact(); 

    public String save() {
        if (contact.getId() != 0) {
            service.edit(contact);
        } else {
            service.add(contact);
        }
        contact = new Contact();
        return null;
    }
    
    public String remove(Contact contact) {
        service.remove(contact);
        return null;
    }
    
    public List<Contact> allContacts() {
        return service.getAll();
    }
    
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
}
