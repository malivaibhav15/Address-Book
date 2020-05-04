package com.BridgeLabz.services;

import com.BridgeLabz.InterFace.AddressBookMenuInterface;

import java.io.File;
import java.io.IOException;

public class AddressBookMenu implements AddressBookMenuInterface {
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
}
