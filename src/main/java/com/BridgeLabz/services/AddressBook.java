package com.BridgeLabz.services;

import com.BridgeLabz.InterFace.AddressBookInterface;
import com.BridgeLabz.model.Person;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

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
            if (person1.getFirstName().equalsIgnoreCase(person.getFirstName()) && person1.getLastName().equalsIgnoreCase(person.getLastName())) {
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
            if (person1.getFirstName().equalsIgnoreCase(person.getFirstName()) && person1.getLastName().equalsIgnoreCase(person.getLastName())) {
                deletePerson = person1;
                break;
            }
        }
        fileData.remove(deletePerson);
        writeFileData(fileData, pathOfFile);
    }

    @Override
    public void sortByName(String pathOfFile) throws IOException {
        ArrayList<Person> entries = readFileData(pathOfFile);
        entries.sort(Comparator.comparing(Person::getFirstName));
        writeFileData(entries, pathOfFile);
    }

    @Override
    public void sortPersonDataByZipCode(String pathOfFile) throws IOException {
        ArrayList<Person> data = readFileData(pathOfFile);
        data.sort(Comparator.comparing(Person::getZip));
        writeFileData(data, pathOfFile);
    }

    @Override
    public boolean printEntries(String filePath) {
        try {
            ArrayList<Person> data = readFileData(filePath);
            data.forEach(print -> System.out.println(print));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean newAddressBook(String addressBookName) throws IOException {
        String filePath = "src/main/resources/" + addressBookName + ".json";
        File newFile = new File(filePath);
        if (newFile.createNewFile())
            return true;
        return false;
    }

    @Override
    public boolean openExistingAddressBook(String addressBookName) {
        String filePath = "src/main/resources/" + addressBookName + ".json";
        File openFile = new File(filePath);
        if (openFile.exists())
            return true;
        return false;
    }

    @Override
    public boolean saveAddressBook(String filePath, ArrayList<Person> data) throws IOException {
        if (data.isEmpty())
            return false;
        writeFileData(data, filePath);
        return true;

    }

}