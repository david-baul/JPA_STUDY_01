package hellojpa;

public class ValueMain {
    public static void main(String[] args) {

        int a = 10;
        int b = 10;

        System.out.println("a == b : " + (a == b));

        Address Address1 = new Address();
        Address1.setCity("city_a");
        Address1.setStreet("street_a");
        Address1.setZipcode("zipcode_a");

        Address Address2 = new Address("city_a","street_a","zipcode_a");
        System.out.println("Address1 == Address2 : " + (Address1 == Address2));
        System.out.println("Address1.equatls(Address2) : " + (Address1.equals(Address2)));
    }
}

