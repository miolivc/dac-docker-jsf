/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.miolivc.repository;

import io.github.miolivc.domain.Contact;
import java.util.List;

/**
 *
 * @author miolivc
 */
public interface Repository {

    void add(Contact contact);
    void edit(Contact contact);
    Contact get(int id);
    List<Contact> get();
    void remove(Contact contact);
    
}
