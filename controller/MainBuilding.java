package controller;
import java.util.Scanner;

import Other.Bill;
import Other.Building;
import Other.Contract;
import Other.Room;
import Other.Tenant;
import user.Manager;
import user.TenantAcc;
import user.Iuser;
import user.LandLord;
import controller.RentalSystem;

public class MainBuilding {
    // Store contracts for all tenants
    private static java.util.ArrayList<Contract> contracts = new java.util.ArrayList<>();
    private static java.util.ArrayList<Bill> bills = new java.util.ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RentalSystem system = new RentalSystem();
       Building building = new Building("Sunrise Apartments", "123 Main St, Phnom Penh");
       LandLord landlord = null;
        Manager manager = null;
        for (Iuser user : system.getUsers()) {
            if (user instanceof LandLord) {
                landlord = (LandLord) user;
            } else if (user instanceof Manager) {
                manager = (Manager) user;
            }
        }
        while (true) {

            System.out.println("=== Welcome to Rental System ===");
            System.out.println("Landlord Contact: Phone=" + landlord.getPhone() + ", Email=" + landlord.getEmail());
            System.out.println("Manager Contact: Phone=" + manager.getPhone());
            System.out.println("=== Now login in Sunrise Apartments ===");
            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            if (!system.login(username, password)) {
                System.out.println("Login failed. Try again.\n");
                continue;
            }
            String role = system.getLoggedInUser().getRole();
            int choice;
            boolean loggedOut = false;
            do {
                if (role.equals("LandLord")) {
                    showLandlordMenu();
                    System.out.print("Choose: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    loggedOut = handleLandlordChoice(choice, building, system, sc);
                    if (loggedOut || choice == 7) break;
                } else if (role.equals("Manager")) {
                    showManagerMenu();
                    System.out.print("Choose: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    loggedOut = handleManagerChoice(choice, building, system, sc);
                    if (loggedOut || choice == 8) break;
                } else if (role.equals("Tenant")) {
                    showTenantMenu();
                    System.out.print("Choose: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    loggedOut = handleTenantChoice(choice, building, system, sc);
                    if (loggedOut || choice == 5) break;
                }
            } while (true);
            if (!loggedOut) break; // Exit if not a logout, e.g., user chose Exit
        }
        sc.close();

        }

        // Landlord menu and handler
        public static void showLandlordMenu() {
            System.out.println("\n=== Landlord Menu ===");
            System.out.println("1) Room");
            System.out.println("2) Tenant");
            System.out.println("3) Manager");
            System.out.println("4) Contract");
            System.out.println("5) Bill");
            System.out.println("6) Logout");
            System.out.println("7) Exit");
        }
        public static boolean handleLandlordChoice(int choice, Building building, RentalSystem system, Scanner sc) {
            final double DEFAULT_WATER_RATE = 0.55; // Example value, set as needed
            final double DEFAULT_ELECTRICITY_RATE = 0.20; // Example value, set as needed
            switch (choice) {
                case 1: // Room submenu
                    System.out.println("--- Room Menu ---");
                    System.out.println("1) Add Room");
                    System.out.println("2) Show Rooms");
                    System.out.println("3) Edit Room");
                    System.out.print("Choose: ");
                    int roomChoice = sc.nextInt();
                    sc.nextLine();
                    if (roomChoice == 1) {
                        System.out.print("Enter Room ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Room Type (single/double): ");
                        String type = sc.nextLine().trim().toLowerCase();
                        double price;
                        if (type.equals("single")) {
                            price = 200; // Default price for single room
                            System.out.println("Single room detected. ");
                        } else if (type.equals("double")) {
                            price = 480; // Default price for double room
                            System.out.println("Double room detected.");
                        } else {
                            System.out.println("Invalid room type. Room not added.");
                            return false;
                        }
                        System.out.print("Enter Floor Number: ");
                        int floor = sc.nextInt();
                        sc.nextLine();
                        Room room = new Room(id, type, true, price, floor);
                         building.addRoom(room);
                         System.out.println("Room added!");
                    } else if (roomChoice == 2) {
                        building.showRooms();
                    } else if (roomChoice == 3) {
                        System.out.print("Enter Room ID to edit: ");
                        int editId = sc.nextInt();
                        sc.nextLine();
                        Room roomToEdit = null;
                        for (Room r : building.getRooms()) {
                            if (r.getRoomId() == editId) {
                                roomToEdit = r;
                                break;
                            }
                        }
                        if (roomToEdit == null) {
                            System.out.println("Room not found.");
                        } else {
                            System.out.print("Enter new Room Number: ");
                            int newRoomId = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Set room as available? (y/n): ");
                            String availInput = sc.nextLine();
                            boolean available = availInput.equalsIgnoreCase("y");
                            System.out.print("Enter new Rent Price: ");
                            double newPrice = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("Enter new Floor Number: ");
                            int newFloor = sc.nextInt();
                            sc.nextLine();
                            building.getRooms().remove(roomToEdit);
                            roomToEdit = new Room(newRoomId, roomToEdit.getRoomType(), available, newPrice, newFloor);
                            building.addRoom(roomToEdit);
                            System.out.println("Room updated!");
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 2: // Tenant submenu
                    System.out.println("--- Tenant Menu ---");
                    System.out.println("1) Add Tenant");
                    System.out.println("2) Delete Tenant");
                    System.out.println("3) View Tenants");
                    System.out.print("Choose: ");
                    int tenantChoice = sc.nextInt();
                    sc.nextLine();
                    if (tenantChoice == 1) {
                        System.out.print("Enter Tenant Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Tenant Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Tenant Phone Number (+855...): ");
                        String phoneNumber = sc.nextLine();
                        System.out.print("Enter Tenant E-Mail: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Room ID: ");
                        int roomId = sc.nextInt();
                        sc.nextLine();
                        Room assignedRoom = null;
                        for (Room r : building.getRooms()) {
                            if (r.getRoomId() == roomId) {
                                assignedRoom = r;
                                break;
                            }
                        }
                        if (assignedRoom == null) {
                            System.out.println("Room not found. Tenant not added.");
                            break;
                        }
                        System.out.print("Enter Tenant ID: ");
                        String tenantId = sc.nextLine();
                        System.out.print("Enter Tenant Password: ");
                        String password = sc.nextLine();
                        Tenant tenant = new Tenant(name, phoneNumber, email, assignedRoom, tenantId, age, password);
                        TenantAcc tenantAcc = new TenantAcc(tenant, name, password);
                        system.addUser(tenantAcc);
                        // Create contract for tenant
                        System.out.print("Enter Contract Start Date (YYYY-MM-DD): ");
                        String startDate = sc.nextLine();
                        Contract contract = new Contract(assignedRoom, tenant, startDate, DEFAULT_WATER_RATE, DEFAULT_ELECTRICITY_RATE);
                        contracts.add(contract);
                        System.out.println("Tenant and contract added successfully!");
                    } else if (tenantChoice == 2) {
                        System.out.print("Enter Tenant ID to delete: ");
                        String delTenantId = sc.nextLine();
                        boolean removed = false;
                        java.util.Iterator<Iuser> it = system.getUsers().iterator();
                        while (it.hasNext()) {
                            Iuser user = it.next();
                            if (user instanceof TenantAcc && ((TenantAcc) user).hasLinkedTenant()) {
                                if (((TenantAcc) user).getTenant().getTenantId().equals(delTenantId)) {
                                    it.remove();
                                    removed = true;
                                }
                            }
                        }
                        if (removed) {
                            System.out.println("Tenant deleted successfully.");
                        } else {
                            System.out.println("Tenant not found.");
                        }
                    } else if (tenantChoice == 3) {
                        System.out.println("--- All Tenants ---");
                        boolean foundTenant = false;
                        for (Iuser user : system.getUsers()) {
                            if (user instanceof TenantAcc) {
                                TenantAcc ta = (TenantAcc) user;
                                if (ta.hasLinkedTenant()) {
                                    System.out.println(ta.getTenant());
                                    foundTenant = true;
                                }
                            }
                        }
                        if (!foundTenant) {
                            System.out.println("No tenants found.");
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 3: // Manager submenu
                    System.out.println("--- Manager Menu ---");
                    System.out.println("1) Add Manager");
                    System.out.println("2) Remove Manager");
                    System.out.print("Choose: ");
                    int managerChoice = sc.nextInt();
                    sc.nextLine();
                    if (managerChoice == 1) {
                        System.out.print("Enter Manager ID: ");
                        String managerId = sc.nextLine();
                        System.out.print("Enter Manager Username: ");
                        String managerUsername = sc.nextLine();
                        System.out.print("Enter Manager Password: ");
                        String managerPassword = sc.nextLine();
                        System.out.print("Enter Manager Phone: ");
                        String managerPhone = sc.nextLine();
                        Manager manager = new Manager(managerId, managerUsername, managerPassword, managerPhone);
                        system.addUser(manager);
                        System.out.println("Manager added successfully!");
                    } else if (managerChoice == 2) {
                        System.out.print("Enter Manager ID to remove: ");
                        String removeId = sc.nextLine();
                        boolean removed = false;
                        java.util.Iterator<Iuser> it = system.getUsers().iterator();
                        // Note: This will remove the first manager found with the matching ID
                        while (it.hasNext()) {
                            Iuser user = it.next();
                            if (user instanceof Manager && user.getID().equals(removeId)) {
                                it.remove();
                                removed = true;
                            }
                        }
                        if (removed) {
                            System.out.println("Manager removed successfully.");
                        } else {
                            System.out.println("Manager not found.");
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 4: // Contract submenu
                    System.out.println("--- Contract Menu ---");
                    System.out.println("1) Add Contract");
                    System.out.println("2) Edit Contract");
                    System.out.println("3) Delete Contract");
                    System.out.println("4) View Contracts");
                    System.out.print("Choose: ");
                    int contractChoice = sc.nextInt();
                    sc.nextLine();
                    if (contractChoice == 1) {
                        System.out.print("Enter Tenant ID: ");
                        String tenantId = sc.nextLine();
                        TenantAcc tenantAcc = null;
                        for (Iuser user : system.getUsers()) {
                            if (user instanceof TenantAcc && ((TenantAcc) user).hasLinkedTenant()) {
                                if (((TenantAcc) user).getTenant().getTenantId().equals(tenantId)) {
                                    tenantAcc = (TenantAcc) user;
                                    break;
                                }
                            }
                        }
                        if (tenantAcc == null) {
                            System.out.println("Tenant not found.");
                        } else {
                            Tenant tenant = tenantAcc.getTenant();
                            System.out.print("Enter Contract Start Date (YYYY-MM-DD): ");
                            String startDate = sc.nextLine();
                            Contract contract = new Contract(tenant.getRoom(), tenant, startDate, DEFAULT_WATER_RATE, DEFAULT_ELECTRICITY_RATE);
                            contracts.add(contract);
                            System.out.println("Contract added successfully!");
                        }
                    } else if (contractChoice == 2) {
                        System.out.print("Enter Contract Tenant ID to edit: ");
                        String editId = sc.nextLine();
                        Contract contractToEdit = null;
                        for (Contract c : contracts) {
                            if (c.getTenant().getTenantId().equals(editId)) {
                                contractToEdit = c;
                                break;
                            }
                        }
                        if (contractToEdit == null) {
                            System.out.println("Contract not found.");
                        } else {
                            System.out.print("Enter new Start Date (YYYY-MM-DD): ");
                            String newStartDate = sc.nextLine();
                            System.out.print("Enter new Water Rate: ");
                            double newWaterRate = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("Enter new Electricity Rate: ");
                            double newElectricityRate = sc.nextDouble();
                            sc.nextLine();
                            // Create new contract and replace
                            Contract updated = new Contract(contractToEdit.getRoom(), contractToEdit.getTenant(), newStartDate, newWaterRate, newElectricityRate);
                            contracts.remove(contractToEdit);
                            contracts.add(updated);
                            System.out.println("Contract updated!");
                        }
                    } else if (contractChoice == 3) {
                        System.out.print("Enter Contract Tenant ID to delete: ");
                        String delId = sc.nextLine();
                        boolean removed = false;
                        java.util.Iterator<Contract> it = contracts.iterator();
                        while (it.hasNext()) {
                            Contract c = it.next();
                            if (c.getTenant().getTenantId().equals(delId)) {
                                it.remove();
                                removed = true;
                            }
                        }
                        if (removed) {
                            System.out.println("Contract deleted successfully.");
                        } else {
                            System.out.println("Contract not found.");
                        }
                    } else if (contractChoice == 4) {
                        System.out.println("--- All Contracts ---");
                        if (contracts.isEmpty()) {
                            System.out.println("No contracts found.");
                        } else {
                            for (Contract c : contracts) {
                                System.out.println(c);
                            }
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 5: // Bill submenu
                    System.out.println("--- All Bills ---");
                    if (bills.isEmpty()) {
                        System.out.println("No bills found.");
                    } else {
                        for (Bill b : bills) {
                            System.out.println(b);
                        }
                    }
                    // Option to create a bill
                    System.out.print("Do you want to create a bill for a tenant? (y/n): ");
                    String createBill = sc.nextLine();
                    if (createBill.equalsIgnoreCase("y")) {
                        System.out.print("Enter Tenant ID: ");
                        String billTenantId = sc.nextLine();
                        Contract foundContract = null;
                        for (Contract c : contracts) {
                            if (c.getTenant().getTenantId().equals(billTenantId)) {
                                foundContract = c;
                                break;
                            }
                        }
                        if (foundContract == null) {
                            System.out.println("No contract found for this tenant.");
                        } else {
                            System.out.print("Enter water used (m3): ");
                            int waterUsed = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter electricity used (kWh): ");
                            int electricityUsed = sc.nextInt();
                            sc.nextLine();
                            Bill bill = new Bill(foundContract, waterUsed, electricityUsed, foundContract.getRoom());
                            bills.add(bill);
                            System.out.println("Bill created successfully!");
                            System.out.println(bill);
                        }
                    }
                    break;
                case 6:
                    system.logout();
                    return true;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    return true;
                default:
                    System.out.println("Invalid choice.");
            }
            return false;
        }

        // Manager menu and handler
        public static void showManagerMenu() {
            System.out.println("\n=== Manager Menu ===");
            System.out.println("1) Show Rooms");
            System.out.println("2) View Contracts");
            System.out.println("3) View Bills");
            System.out.println("4) Update Room");
            System.out.println("5) View Tenants");
            System.out.println("6) Update Tenant");
            System.out.println("7) Logout");
            System.out.println("8) Exit");
        }
        public static boolean handleManagerChoice(int choice, Building building, RentalSystem system, Scanner sc) {
            switch (choice) {
                case 1:
                    building.showRooms();
                    break;
                case 2:
                    System.out.println("--- All Contracts ---");
                    if (contracts.isEmpty()) {
                        System.out.println("No contracts found.");
                    } else {
                        for (Contract c : contracts) {
                            System.out.println(c);
                        }
                    }
                    break;
                case 3:
                    System.out.println("--- All Bills ---");
                    if (bills.isEmpty()) {
                        System.out.println("No bills found.");
                    } else {
                        for (Bill b : bills) {
                            System.out.println(b);
                        }
                    }
                    break;
                case 4:
                    System.out.println("--- Update Room Status ---");
                    System.out.print("Enter Room ID to update: ");
                    int updateRoomId = sc.nextInt();
                    sc.nextLine();
                    Room roomToUpdate = null;
                    for (Room r : building.getRooms()) {
                        if (r.getRoomId() == updateRoomId) {
                            roomToUpdate = r;
                            break;
                        }
                    }
                    if (roomToUpdate == null) {
                        System.out.println("Room not found.");
                    } else {
                        System.out.print("Set room as available? (y/n): ");
                        String availInput = sc.nextLine();
                        boolean available = availInput.equalsIgnoreCase("y");
                        roomToUpdate.setAvailable(available);
                        System.out.println("Room status updated. Now: " + (available ? "Available" : "Not Available"));
                    }
                    break;
                case 5:
                    System.out.println("--- All Tenants ---");
                    boolean foundTenant = false;
                    for (Iuser user : system.getUsers()) {
                        if (user instanceof TenantAcc) {
                            TenantAcc ta = (TenantAcc) user;
                            if (ta.hasLinkedTenant()) {
                                System.out.println(ta.getTenant());
                                foundTenant = true;
                            }
                        }
                    }
                    if (!foundTenant) {
                        System.out.println("No tenants found.");
                    }
                    break;
                case 6:
                    System.out.println("--- Update Tenant ---");
                    System.out.print("Enter Tenant ID to update: ");
                    String updateTenantId = sc.nextLine();
                    TenantAcc tenantAcc = null;
                    for (Iuser user : system.getUsers()) {
                        if (user instanceof TenantAcc && ((TenantAcc) user).hasLinkedTenant()) {
                            if (((TenantAcc) user).getTenant().getTenantId().equals(updateTenantId)) {
                                tenantAcc = (TenantAcc) user;
                                break;
                            }
                        }
                    }
                    if (tenantAcc == null) {
                        System.out.println("Tenant not found.");
                    } else {
                        Tenant tenant = tenantAcc.getTenant();
                        System.out.print("Enter new phone number (+855...): ");
                        String newPhone = sc.nextLine();
                        tenant.setPhoneNumber(newPhone);
                        System.out.print("Enter new email: ");
                        String newEmail = sc.nextLine();
                        tenant.setEmail(newEmail);
                        // Create new contract
                        System.out.print("Enter new contract start date (YYYY-MM-DD): ");
                        String newStartDate = sc.nextLine();
                        final double DEFAULT_WATER_RATE = 0.75;
                        final double DEFAULT_ELECTRICITY_RATE = 0.20;
                        Contract newContract = new Contract(tenant.getRoom(), tenant, newStartDate, DEFAULT_WATER_RATE, DEFAULT_ELECTRICITY_RATE);
                        contracts.add(newContract);
                        System.out.println("Tenant updated and new contract created!");
                    }
                    break;

                case 7:
                    system.logout();
                    return true;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                    return true;
                default:
                    System.out.println("Invalid choice.");
            }
            return false;
        }

        // Tenant menu and handler
        public static void showTenantMenu() {
            System.out.println("\n=== Tenant Menu ===");
            System.out.println("1) View My Room");
            System.out.println("2) View My Contract");
            System.out.println("3) View My Bills");
            System.out.println("4) Logout");
            System.out.println("5) Exit");
        }
        public static boolean handleTenantChoice(int choice, Building building, RentalSystem system, Scanner sc) {
            switch (choice) {
                case 1:
                    // View My Room
                    Iuser loggedUser = system.getLoggedInUser();
                    if (loggedUser instanceof TenantAcc && ((TenantAcc) loggedUser).hasLinkedTenant()) {
                        Tenant tenant = ((TenantAcc) loggedUser).getTenant();
                        System.out.println("--- My Room ---");
                        System.out.println(tenant.getRoom());
                    } else {
                        System.out.println("No room information found.");
                    }
                    break;
                case 2:
                    // View My Contract
                    loggedUser = system.getLoggedInUser();
                    if (loggedUser instanceof TenantAcc && ((TenantAcc) loggedUser).hasLinkedTenant()) {
                        Tenant tenant = ((TenantAcc) loggedUser).getTenant();
                        System.out.println("--- My Contract ---");
                        boolean found = false;
                        for (Contract c : contracts) {
                            if (c.getTenant().getTenantId().equals(tenant.getTenantId())) {
                                System.out.println(c);
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("No contract found.");
                        }
                    } else {
                        System.out.println("No contract information found.");
                    }
                    break;
                case 3:
                    // View My Bills
                    loggedUser = system.getLoggedInUser();
                    if (loggedUser instanceof TenantAcc && ((TenantAcc) loggedUser).hasLinkedTenant()) {
                        Tenant tenant = ((TenantAcc) loggedUser).getTenant();
                        System.out.println("--- My Bills ---");
                        boolean found = false;
                        for (Bill b : bills) {
                            if (b.getContract().getTenant().getTenantId().equals(tenant.getTenantId())) {
                                System.out.println(b);
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("No bills found.");
                        }
                    } else {
                        System.out.println("No bill information found.");
                    }
                    break;
                case 4:
                    system.logout();
                    return true;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    return true;
                default:
                    System.out.println("Invalid choice.");
            }
            return false;
        }
    }

 