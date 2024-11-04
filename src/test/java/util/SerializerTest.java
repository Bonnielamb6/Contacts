package util;

import contacts.Contact;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SerializerTest {

    String fileName = "contactsTest.txt";
    List<Contact> contactsToSerialize;

    SerializerTest() {
        contactsToSerialize = new ArrayList<>();
    }

    @Test
    void serializeShouldNotThrowException(){
        assertDoesNotThrow(() -> Serializer.serialize((Serializable) contactsToSerialize, fileName));
    }

    @Test
    void deserialize() {
        assertDoesNotThrow(() -> Serializer.deserialize(fileName));
    }
}