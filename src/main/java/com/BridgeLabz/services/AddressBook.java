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
}