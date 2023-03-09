//Erick Zagal (ezagal2@toromail.csudh.edu)
public class Person {
    //Fields
    private String firstName;
    private String lastName;
    private String SSN;
    //Constructors
    public Person(String fName, String lName, String SSN) {
        firstName=fName;
        lastName=lName;
        this.SSN=SSN;
    }

    @Override
    public String toString() {
        return firstName+" : "+lastName+" : "+SSN;
    }
}
