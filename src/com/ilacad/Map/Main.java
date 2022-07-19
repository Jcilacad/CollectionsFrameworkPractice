package com.ilacad.Map;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private final static Map<Integer, Constituents> constituentsMap = new LinkedHashMap<>();
    private static int count = 1;
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("============VaxFo============");
        System.out.println("Vaccine Information of Constituents in Barangay 556, Zone 55\n");


        while (true) {

            System.out.println();
            System.out.println("\t(1) View Constituents");
            System.out.println("\t(2) Add Constituents");
            System.out.println("\t(3) Modify Constituents");
            System.out.println("\t(4) Find Constituents");
            System.out.println("\t(5) Delete Constituents");
            System.out.println("\t(6) Exit Program");
            System.out.println("\n");
            System.out.println("Enter Response: ");
            int response = scanner.nextInt();

            switch (response) {
                case 1:
                    viewConstituents();
                    break;
                case 2:
                    addConstituents();
                    break;
                case 3:
                    modifyConstituents();
                    break;
                case 4:
                    findConstituents();
                    break;
                case 5:
                    deleteConstituents();
                    break;
                case 6:
                    System.exit(0);
            }

        }


    }

    // Delete Constituents
    private static void deleteConstituents() {

        System.out.println("============Delete Constituents============");
        System.out.println("\tSelect an Option");
        System.out.println("\t(1) By ID");
        System.out.println("\t(2) By Name");
        int response = scanner.nextInt();

        switch (response) {
            case 1:
                System.out.println("Enter ID: ");
                deleteByID(scanner.nextInt());
                break;
            case 2:
                System.out.println("Enter Name: ");
                scanner.nextLine();
                deleteByName(scanner.nextLine());
                break;
        }

    }

    // Find Constituents
    private static void findConstituents() {

        System.out.println("============Find Constituents============");
        System.out.println("\tSelect an Option: ");
        System.out.println("\t(1) By ID");
        System.out.println("\t(2) By Name");

        int response = scanner.nextInt();
        switch (response) {
            case 1:
                System.out.println("Enter ID: ");
                findByID(scanner.nextInt());
                break;
            case 2:
                System.out.println("Enter Name:");
                scanner.nextLine();
                findByName(scanner.nextLine());
                break;

        }

    }

    // Modify Constituents
    private static void modifyConstituents() {

        System.out.println("============Modify Constituents============");
        System.out.println("\tEnter an Option: ");
        System.out.println("\t(1) Modify by ID");
        System.out.println("\t(2) Modify by Name");
        int response = scanner.nextInt();

        switch (response) {
            case 1:
                System.out.println("Enter ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                Constituents constituentsById = findByID(id);
                checkConstituent(constituentsById);

                break;
            case 2:
                System.out.println("Enter Name: ");
                scanner.nextLine();
                String name = scanner.nextLine();
                Constituents constituentsByName = findByName(name);
                checkConstituent(constituentsByName);

                break;

        }


    }

    // Add Constituents
    private static void addConstituents() {
        System.out.println("============Add Constituents============");
        System.out.print("\nEnter Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Vaccination Count: ");
        int vaccineCount = scanner.nextInt();

        Constituents newConstituents = new Constituents(name, address, contact, vaccineCount);

        constituentsMap.put(count++, newConstituents);
        System.out.println(name + " Successfully Added as a Constituents in Barangay 556, zone 55!\n");


    }

    // View Constituents
    private static void viewConstituents() {

        System.out.println("============Select an Option============");
        System.out.println("\t\t(1) View All");
        System.out.println("\t\t(2) View Protected");
        System.out.println("\t\t(3) View Unprotected\n");

        System.out.println("Enter your response: ");
        int response = scanner.nextInt();

        switch (response) {
            case 1:
                viewAll();
                break;
            case 2:
                viewProtected();
                break;
            case 3:
                viewUnprotected();
                break;
        }

    }

    // List of All Constituents
    private static void viewAll() {
        isRecordEmpty();

        System.out.println("============List of All Constituents============");
        for (Map.Entry<Integer, Constituents> entry : constituentsMap.entrySet()) {
            String name = entry.getValue().getName();
            String address = entry.getValue().getAddress();
            String contact = entry.getValue().getContactNumber();
            int vaccineCount = entry.getValue().getVaccineCount();
            boolean status = entry.getValue().status();

            System.out.println("ID: " + entry.getKey() + " Name: " + name + " Address: " + address + " Contact: " +
                    contact + " Vaccine Count: " + vaccineCount + " Status: " + ((status) ? "Protected" : "Unprotected"));

        }
    }

    // List of All Protected Constituents (Who has been vaccinated for their 2nd dose up to booster)
    private static void viewProtected() {
        isRecordEmpty();

        System.out.println("============List of All Protected Constituents============");
        for (Map.Entry<Integer, Constituents> entry : constituentsMap.entrySet()) {
            boolean isProtected = entry.getValue().status();
            String name = entry.getValue().getName();
            String address = entry.getValue().getAddress();
            String contact = entry.getValue().getContactNumber();
            int vaccineCount = entry.getValue().getVaccineCount();
            if (isProtected) {
                System.out.println("ID: " + entry.getKey() + " Name: " + name + " Address: " + address + " Contact: " +
                        contact + " Vaccine Count: " + vaccineCount);
            }
        }

    }

    // List of All Not Protected Constituents (Who has not yet vaccinated for their 2nd dose and booster)
    private static void viewUnprotected() {
        isRecordEmpty();
        System.out.println("============List of All Unprotected Constituents============");
        for (Map.Entry<Integer, Constituents> entry : constituentsMap.entrySet()) {
            boolean isProtected = entry.getValue().status();
            String name = entry.getValue().getName();
            String address = entry.getValue().getAddress();
            String contact = entry.getValue().getContactNumber();
            int vaccineCount = entry.getValue().getVaccineCount();
            if (!isProtected) {
                System.out.println("ID: " + entry.getKey() + " Name: " + name + " Address: " + address + " Contact: " +
                        contact + " Vaccine Count: " + vaccineCount);
            }
        }

    }

    // Check if the constituents record is empty
    private static void isRecordEmpty() {
        if (constituentsMap.isEmpty()) {
            System.out.println("There is no constituents in the record\n\n");
        }
    }

    // Delete Constituents by ID
    private static void deleteByID(int id) {
        constituentsMap.remove(id);
    }

    // Delete Constituents by Name
    private static void deleteByName(String name) {

        for (Map.Entry<Integer, Constituents> entry : constituentsMap.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                constituentsMap.remove(entry.getKey());
                break;
            }
        }
    }

    // Find Constituents by ID
    private static Constituents findByID(int id) {
        Constituents constituents = constituentsMap.getOrDefault(id, null);
        if (constituents != null) {
            String name = constituents.getName();
            String address = constituents.getAddress();
            String contact = constituents.getContactNumber();
            int vaccineCount = constituents.getVaccineCount();
            boolean status = constituents.status();
            System.out.println("ID: " + id + " Name: " + name + " Address: " + address + " Contact: " +
                    contact + " Vaccine Count: " + vaccineCount + " Status: " + ((status) ? "Protected" : "Unprotected"));
            return constituents;
        } else {
            System.out.println("There is no existing record in the id of " + id);
            return null;
        }
    }

    // Find Constituents by Name
    private static Constituents findByName(String name) {

        for (Map.Entry<Integer, Constituents> entry : constituentsMap.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                boolean status = entry.getValue().status();
                String address = entry.getValue().getAddress();
                String contact = entry.getValue().getContactNumber();
                int vaccineCount = entry.getValue().getVaccineCount();
                System.out.println("ID: " + entry.getKey() + " Name: " + name + " Address: " + address + " Contact: " +
                        contact + " Vaccine Count: " + vaccineCount + " Status: " + ((status) ? "Protected" : "Unprotected"));
                return entry.getValue();
            }
        }

        return null;
    }

    private static void checkConstituent (Constituents constituents) {
        if (constituents != null) {
            System.out.println("Enter New Name: ");
            String newName = scanner.nextLine();
            System.out.println("Enter New Address: ");
            String newAddress = scanner.nextLine();
            System.out.println("Enter New Contact: ");
            String newContact = scanner.nextLine();
            System.out.println("Enter New Vaccination Count: ");
            int newVaccineCount = scanner.nextInt();

            constituents.updateName(newName);
            constituents.updateAddress(newAddress);
            constituents.updateContact(newContact);
            constituents.updateVaccineCount(newVaccineCount);
        } else {
            System.out.println("Record Does not Exists");
        }

    }

}
