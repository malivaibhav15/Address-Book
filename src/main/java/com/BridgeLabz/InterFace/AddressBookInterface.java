package com.BridgeLabz.InterFace;

import com.BridgeLabz.model.Person;

import java.io.IOException;
import java.util.ArrayList;

public interface AddressBookInterface {
    void addPerson(Person person, String pathOfFile) throws IOException;

    void updatePerson(Person person, String pathOfFile) throws IOException;

    void deletePerson(Person person, String pathOfFile) throws IOException;

    void sortByName(String pathOfFile) throws IOException;

    void sortPersonDataByZipCode(String pathOfFile) throws IOException;

    boolean printEntries(String filePath);

    boolean saveAddressBook(String filePath, ArrayList<Person> data) throws IOException;
}
