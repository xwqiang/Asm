package com.sosnoski.dwct;

public class Run
{
    public static void main(String[] args) {
        Name name = new Name("Dennis", "Michael", "Sosnoski");
        Address address = new Address("1234 5th St.", "Redmond", "WA", "98052");
        Customer customer = new Customer(12345, name, address,
            "425 555-1212", "425 555-1213");
        System.out.println(customer);
    }
}