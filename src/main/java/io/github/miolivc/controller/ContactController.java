
package io.github.miolivc.controller;

import io.github.miolivc.domain.Contact;
import io.github.miolivc.service.ContactService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

@Named("contacts")
@RequestScoped
public class ContactController {
    
    @Inject
    private ContactService service;
    private List<Contact> contacts = new ArrayList<>();
    private Contact contact = new Contact(); 
    private String search = null;
    
    @PostConstruct
    public void init() {
        this.contacts = service.getAll();
    }
    
    public void contactsFilter(AjaxBehaviorEvent event) {
//        System.out.println("changed value: " + search);
        this.contacts = contacts.stream()
                .filter(c -> c.getName().toLowerCase().startsWith(search.toLowerCase()))
                .collect(Collectors.toList());
        this.contacts.stream().forEach(c -> System.out.println(c.getName()));
    }
    
    public String save() {
        if (contact.getId() != 0) {
            service.edit(contact);
        } else {
            service.add(contact);
        }
        contact = new Contact();
        return "index.xhtml?faces-redirect=true";
    }
    
    public String remove(Contact contact) {
        service.remove(contact);
        return null;
    }
    
    public List<Contact> allContacts() {
        return contacts;
    }
    
    /*
     * Getters ans Setters
     */
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}
