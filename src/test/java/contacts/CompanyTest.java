package contacts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    Company companyToTest;

    CompanyTest() {
        companyToTest = new Company();
        companyToTest.setName("Company");
        companyToTest.setAddress("In this house");
        companyToTest.setNumber("777888111");
        companyToTest.setTimeModified();
        companyToTest.setTimeCreated();
    }

    @Test
    void setCompanyFieldByName() {
        String expectedBefore = """
                Organization name: Company
                Address: In this house
                Number: 777888111
                Time created:""" + " " + companyToTest.getTimeCreated()
                + "\nTime last edit: " + companyToTest.getTimeModified() + "\n";

        assertEquals(expectedBefore, companyToTest.toString());

        companyToTest.setFieldByName("NAME", "An actual company");
        companyToTest.setFieldByName("ADDRESS", "At the mall");
        companyToTest.setFieldByName("NUMBER", "444444444");
        companyToTest.setTimeModified();

        String expectedAfter = """
                Organization name: An actual company
                Address: At the mall
                Number: 444444444
                Time created:""" + " " + companyToTest.getTimeCreated()
                + "\nTime last edit: " + companyToTest.getTimeModified() + "\n";

        assertEquals(expectedAfter, companyToTest.toString());

    }
}