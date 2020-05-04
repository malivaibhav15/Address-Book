package com.BridgeLabz.InterFace;

import java.io.File;
import java.io.IOException;

public class AddressBookMenu
{
    @Override
    public boolean newAddressBook(String addressBookName) throws IOException {
        String filePath = "src/main/resources/NewFile.json";
        File newFile = new File(filePath);
        if (newFile.createNewFile())
            return true;
        return false;
    }

}
