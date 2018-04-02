package io.github.miolivc.controller;

import io.github.miolivc.domain.Contact;
import io.github.miolivc.service.ContactService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("contacts")
@ViewScoped
public class ContactController implements Serializable {

    @Inject
    private ContactService service;
    private List<Contact> contacts = new ArrayList<>();
    private List<String> letters = new ArrayList<>();
    private Contact contact = new Contact();
    private String search = null;
    
    @PostConstruct
    public void init() {
        this.contacts = service.getAll();
        this.letters = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", 
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", 
                "T", "U", "V", "W", "X", "Y", "Z");
    }

    /**
     * List filter by contacts
     * @param event
     */
    public void contactsFilter(AjaxBehaviorEvent event) {
        this.contacts = contacts.stream()
                .filter(c -> c.getName()
                    .toLowerCase()
                    .startsWith(search.toLowerCase()))
                .collect(Collectors.toList());
        this.contacts.stream().forEach(c-> System.out.println(c.getName()));  
    }
    
    /**
     * Tab filter by letter
     * @param event
     */
    public void letterContactsFilter(AjaxBehaviorEvent event) {
        String letter = (String) event.getComponent().getAttributes().get("letter");
        this.contacts = contacts.stream()
                .filter(c -> c.getName()
                    .toLowerCase()
                    .startsWith(letter.toLowerCase()))
                .collect(Collectors.toList());
//        this.contacts = service.getByFirstLetter(letter);
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
    
    public List<String> getLetters() {
        return letters;
    }

}
