package com.BridgeLabz;

import com.BridgeLabz.model.Person;
import com.BridgeLabz.services.AddressBook;
import com.BridgeLabz.services.AddressBookInterface;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TestCaseForAddressBook {

    AddressBook addressBook;
    ObjectMapper objectMapper;
    Person person;
    public static String TestFilePath = "src/main/resources/TestAddressBook.json";

    @Before
    public void setUp() {
        addressBook = new AddressBook();
        objectMapper = new ObjectMapper();
        person = new Person();
    }

    @Test
    public void givenAddPerson_WhenProper_ShouldReturnTrue() throws IOException {
        Person person = new Person("Vaibhav", "Mali", "AB Colony", "Ambajogai", "Maharashtra", "1234567890", "431517");
        AddressBookInterface addressBookInterface = new AddressBook();
        addressBookInterface.addPerson(person, TestFilePath);
        ArrayList<Person> entries = objectMapper.readValue(new File(TestFilePath), new TypeReference<ArrayList<Person>>() {
        });
        Assert.assertEquals(person.getFirstName(), entries.get(1).getFirstName());
    }

    @Test
    public void givenPerson_WhenUpdate_ShouldReturnTrue() throws IOException {
        Person person = new Person("Vaibhav", "Mali", "vvv", "Amb", "Mah", "111", "4315");
        AddressBookInterface addressBookInterface = new AddressBook();
        addressBookInterface.updatePerson(person, TestFilePath);
        ArrayList<Person> entries = objectMapper.readValue(new File(TestFilePath), new TypeReference<ArrayList<Person>>() {
        });
        Assert.assertEquals(person.getFirstName(), entries.get(0).getFirstName());
    }

    @Test
    public void givenPersonObject_IfDeleted_ShouldReturnTrue() throws IOException {
        Person person = new Person("aaa", "bbb", "ccc", "ddd", "eee", "111", "222");
        AddressBookInterface addressBookInterface = new AddressBook();
        addressBookInterface.deletePerson(person, TestFilePath);
        ArrayList<Person> data = objectMapper.readValue(new File(TestFilePath), new TypeReference<ArrayList<Person>>() {
        });
        Assert.assertEquals(true, data.isEmpty());
    }
}
