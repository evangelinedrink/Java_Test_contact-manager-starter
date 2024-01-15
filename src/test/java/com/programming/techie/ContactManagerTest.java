package com.programming.techie;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

//Creates one instance of the class
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {
    //Variable for contactManager that can be used in all the methods
    ContactManager contactManager;

    //@BeforeAll is executed once at the beginning of the test class
    //This method must be marked as static, otherwise JUnit wouldn't be able to run it
    //Since we are using @TestInstance(TestInstance.Lifecycle.PER_CLASS), we don't need the static keyword
    //public static void setupAll(){
    public void setupAll() {
        System.out.println("Should Print Before All Tests");
    }

    //@BeforeEach is executed before each test
    //In this method, we will initialize the ContactManager class
    @BeforeEach
    public void setup() {
        contactManager = new ContactManager();
    }

    //Test if contact was added successfully
    //This is testing a Happy Path Scenario
    @Test
    public void shouldCreateContact() {
        //ContactManager contactManager = new ContactManager();
        contactManager.addContact("John", "Doe", "0123456789");
        //Verifies if the contact is created or not and makes sure that the contact is in the list or not
        //The code below verifies if the list is not empty.
        //Assertions.assertFalse() takes a boolean parameter and if boolean is not false it will throw an exception and fail the test
        //Since we want the contact list to not be empty, we are using the assertFalse so that the test will not pass
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

        //We want to make sure that there is exactly one element in the Contact list
        //First parameter is the expected value and the second parameter is the actual value
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    //Testing a negative scenario where the test is failing by providing invalid input
    @Test
    //Instead of displaying a method name, @DisplayName would be placed in the test results
    @DisplayName("Should Not Create Contact When First Name is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        //ContactManager contactManager = new ContactManager();
        //When there is an invalid input, the test will throw a RuntimeException
        //Assertions.assertThrows(): First parameter is what we would like thrown in the test when the second parameter (the supplier) has been typed
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Doe", "0123456789");
        });
    }

    @Test
    @DisplayName("Should Not Create Contact When Last Name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
        //ContactManager contactManager = new ContactManager();
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John", null, "0123456789");
        });
    }

    @Test
    @DisplayName("Should Not Create Contact When Phone Number is Null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
        //ContactManager contactManager = new ContactManager();
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John", "Doe", null);
        });
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Should Execute After Each Test");
    }

    //Since we are using @TestInstance(TestInstance.Lifecycle.PER_CLASS), we don't need the static keyword
    //public static void tearDownAll(){
    @AfterAll
    public void tearDownAll() {
        System.out.println("Should be executed at the end of the test");
    }

    @Test
    @DisplayName("Should Create Contact Only on MAC OS")
    @EnabledOnOs(value = OS.MAC, disabledReason="Enabled only on MAC OS")
    public void shouldCreateContactOnlyOnMAC() {
        //ContactManager contactManager = new ContactManager();
        contactManager.addContact("John", "Doe", "0123456789");
        //Verifies if the contact is created or not and makes sure that the contact is in the list or not
        //The code below verifies if the list is not empty.
        //Assertions.assertFalse() takes a boolean parameter and if boolean is not false it will throw an exception and fail the test
        //Since we want the contact list to not be empty, we are using the assertFalse so that the test will not pass
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

        //We want to make sure that there is exactly one element in the Contact list
        //First parameter is the expected value and the second parameter is the actual value
        Assertions.assertEquals(1, contactManager.getAllContacts().size());

        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe") &&
                        contact.getPhoneNumber().equals("0123456789"))
                .findAny()
                .isPresent());
    }

    @Test
    @DisplayName("Should Create Contact Only on Windows OS")
    @EnabledOnOs(value = OS.WINDOWS, disabledReason="Enabled only on Windows OS")
    public void shouldCreateContactOnlyOnWindows() {
        //ContactManager contactManager = new ContactManager();
        contactManager.addContact("John", "Doe", "0123456789");
        //Verifies if the contact is created or not and makes sure that the contact is in the list or not
        //The code below verifies if the list is not empty.
        //Assertions.assertFalse() takes a boolean parameter and if boolean is not false it will throw an exception and fail the test
        //Since we want the contact list to not be empty, we are using the assertFalse so that the test will not pass
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

        //We want to make sure that there is exactly one element in the Contact list
        //First parameter is the expected value and the second parameter is the actual value
        Assertions.assertEquals(1, contactManager.getAllContacts().size());

        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe") &&
                        contact.getPhoneNumber().equals("0123456789"))
                .findAny()
                .isPresent());
    }

    @Test
    @DisplayName("Test Contact Creation on Developer Machine")
    public void shouldTestContactCreationOnDEV() {

        //Assumptions: The assertions will be inside of the test conditionally. THis means that if the assertion is not successful,
        //the test will not fail, it will only be aborted.
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));

        contactManager.addContact("John", "Doe", "0123456789");
        //Verifies if the contact is created or not and makes sure that the contact is in the list or not
        //The code below verifies if the list is not empty.
        //Assertions.assertFalse() takes a boolean parameter and if boolean is not false it will throw an exception and fail the test
        //Since we want the contact list to not be empty, we are using the assertFalse so that the test will not pass
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

        //We want to make sure that there is exactly one element in the Contact list
        //First parameter is the expected value and the second parameter is the actual value
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    //Repeated test with @RepeatedTest

    @DisplayName("Repeat Contact Creation Test 5 Times")
    @RepeatedTest(value = 5,
        name= "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
    public void shouldCreateContactRepeatedly() {
        contactManager.addContact("John", "Doe", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }


    //Parameterized Test: Use parameters within your test
    //Using @ValueSource

    @DisplayName("Value Source Case - Phone Number should match the required format")
    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0123456789", "0123456789"})
    public void shouldTestContactCreationUsingValueSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    //Using @MethodSource
    @DisplayName("Method Source Case - Phone Number should match the required format")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    public void shouldTestContactCreationUsingMethodSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    private static List<String> phoneNumberList() {
        return Arrays.asList("0123456789", "0123456789", "0123456789");
    }

    //Using @CsvSource
    @DisplayName("CSV Source Case - Phone Number should match the required format")
    @ParameterizedTest
    @CsvSource({"0123456789", "0123456789", "0123456789"})
    public void shouldTestContactCreationUsingCSVSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    //Using @CsvFileSource
    @DisplayName("CSV File Source Case - Phone Number should match the required format")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void shouldTestContactCreationUsingCSVFileSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }


    /*Nested Tests*/
    /*
    @Nested
    class RepeatedNestedTest{
        @DisplayName("Repeat Contact Creation Test 5 Times")
        @RepeatedTest(value = 5,
                name= "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
        public void shouldCreateContactRepeatedly() {
            contactManager.addContact("John", "Doe", "0123456789");
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }
    }

    @Nested
    class ParameterizedNestedTest{
        //Parameterized Test: Use parameters within your test
        //Using @ValueSource
        @DisplayName("Value Source Case - Phone Number should match the required format")
        @ParameterizedTest
        @ValueSource(strings = {"0123456789", "0123456789", "0123456789"})
        public void shouldTestContactCreationUsingValueSource(String phoneNumber) {
            contactManager.addContact("John", "Doe", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }

        //Using @MethodSource
        @DisplayName("Method Source Case - Phone Number should match the required format")
        @ParameterizedTest
        @MethodSource("phoneNumberList")
        public void shouldTestContactCreationUsingMethodSource(String phoneNumber) {
            contactManager.addContact("John", "Doe", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }

        private List<String> phoneNumberList() {
            return Arrays.asList("0123456789", "0123456789", "0123456789");
        }

        //Using @CsvSource
        @DisplayName("CSV Source Case - Phone Number should match the required format")
        @ParameterizedTest
        @CsvSource({"0123456789", "0123456789", "0123456789"})
        public void shouldTestContactCreationUsingCSVSource(String phoneNumber) {
            contactManager.addContact("John", "Doe", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }

        //Using @CsvFileSource
        @DisplayName("CSV File Source Case - Phone Number should match the required format")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        public void shouldTestContactCreationUsingCSVFileSource(String phoneNumber) {
            contactManager.addContact("John", "Doe", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }
    }
    */

}