package com.BridgeLabz.services;

import com.BridgeLabz.model.Person;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AddressBook implements AddressBookInterface {

    ObjectMapper objectMapper = new ObjectMapper();

    public void writeFileData(ArrayList<Person> list, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), list);
    }

    public ArrayList<Person> readFileData(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), new TypeReference<ArrayList<Person>>() {
        });
    }

    @Override
    public void addPerson(Person person, String pathOfFile) throws IOException {
        ArrayList<Person> data = readFileData(pathOfFile);
        data.add(person);
        writeFileData(data, pathOfFile);
    }

    @Override
    public void updatePerson(Person person, String pathOfFile) throws IOException {
        ArrayList<Person> data = readFileData(pathOfFile);
        for (Person person1 : data) {
            if (person1.getFirstName().equalsIgnoreCase(person.getFirstName()) && person1.getLastName().equalsIgnoreCase(person.getLastName()))
            {
                person1.setAddress(person.getAddress());
                person1.setCity(person.getCity());
                person1.setMobileNumber(person.getMobileNumber());
                person1.setState(person.getState());
                person1.setZip(person.getZip());
            }
        }
        writeFileData(data, pathOfFile);
    }

    @Override
    public void deletePerson(Person person, String pathOfFile) throws IOException {
        ArrayList<Person> fileData = readFileData(pathOfFile);
        Person deletePerson = null;
        for (Person person1 : fileData) {
            if (person1.getFirstName().equalsIgnoreCase(person.getFirstName()) && person1.getLastName().equalsIgnoreCase(person.getLastName())){
                deletePerson = person1;
                break;
            }
        }
        fileData.remove(deletePerson);
        writeFileData(fileData, pathOfFile);
    }
}