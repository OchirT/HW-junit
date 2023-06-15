import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.*;

class PhoneBookTest {
    PhoneBook phoneBook = new PhoneBook();
    private Contact contact;
    private Contact contact1;
    private Contact contact2;
    private Contact contact3;


    @BeforeEach
    void setUp() {
        phoneBook.addGroup("Friends");
        contact = new Contact("Vasya", "89241234567");
        contact1 = new Contact("Petya", "89641234567");
        contact2 = new Contact("Vanya", "88002000600");
        contact3 = null;

    }

    @Test
    void addGroupIsNotNull() {
        String[] groupName = {"Family", "Job", "Friends", null};
        HashMap<String,List<Contact>> expected = new HashMap<>();
        for (String name :groupName) {
            if(name != null ) {
                expected.put(name, new ArrayList<>());
            }
        }

        for (String input1 : groupName) {
            phoneBook.addGroup(input1);
        }
        Assertions.assertEquals(expected,phoneBook.phoneBook);
        assertThat(phoneBook.phoneBook,notNullValue());
    }

    @Test
    void addContactInGroupIsNotNull() {
        List<Contact> expect = new ArrayList<>();
        expect.add(contact);
        expect.add(contact1);
        expect.add(contact2);
        expect.add(contact3);
        expect.removeIf(Objects::isNull);


        phoneBook.addContactInGroup(new String[]{"Friends"}, contact);
        phoneBook.addContactInGroup(new String[]{"Friends"}, contact1);
        phoneBook.addContactInGroup(new String[]{"Friends"}, contact2);
        phoneBook.addContactInGroup(new String[]{"Friends"}, contact3);


        Assertions.assertEquals(expect, phoneBook.phoneBook.get("Friends"));
        assertThat(phoneBook.phoneBook, hasKey("Friends"));
    }

}



