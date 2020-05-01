package com.BridgeLabz;

import jdk.internal.org.objectweb.asm.TypeReference;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.util.List;

public class AddressBook {
    public static void main(String[] args) {
        try {
            System.out.println("Welcome to address book");
            System.out.println("Press 1.To add person");
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File("src/main/resources/AddressBook.json"));
            TypeReference   typeReference = new TypeReference<List<Person>>() {
            };
            List<Person> personList = objectMapper.readValues(inputStream, typeReference);
            for (Person p : personList) {
                System.out.println("name is" + p.getFirstName() + "lastname" + p.getLastName());
            }
            Person person = new Person();
            person.setFirstName("VAIBHAV");
            person.setLastName("MALI");
            objectMapper.writeValue(new File("src/main/resources/AddressBook.json"), person);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}