
package io.github.miolivc.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "contact_seq", allocationSize = 1, sequenceName = "contact_seq")
public class Contact implements Serializable {
    
    @Id
    @GeneratedValue(generator = "contact_seq", strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(length = 30, nullable = false, unique = true)
    private String email;

    private Contact(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public static Contact of(String name, String email) {
        return new Contact(name, email);
    }
    
    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
