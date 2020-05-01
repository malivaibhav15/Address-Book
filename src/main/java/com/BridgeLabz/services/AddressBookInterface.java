package com.BridgeLabz.services;

import com.BridgeLabz.model.Person;

import java.io.IOException;

public interface AddressBookInterface
{
    void addPerson(Person person, String pathOfFile) throws IOException;
    void  updatePerson(Person person, String pathOfFile) throws IOException;
}
