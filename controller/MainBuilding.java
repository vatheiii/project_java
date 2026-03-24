import java.util.Scanner;
import java.time.LocalDate;


import Other.Bill;
import Other.Building;
import Other.Contract;
import Other.Room;
import Other.Tenant;
import controller.RentalSystem;
import user.Manager;
import user.TenantAcc;
import user.Iuser;
import user.LandLord;

public class MainBuilding {
    private static java.util.ArrayList<Contract> contracts = new java.util.ArrayList<>();
    private static java.util.ArrayList<Bill> bills = new java.util.ArrayList<>();
    private static final LocalDate MIN_CONTRACT_DATE = LocalDate.of(2026, 1, 1);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RentalSystem system = new RentalSystem();
        Building building = new Building("Sunrise Apartments", "123 Main St, Phnom Penh");
        system.polymorphismDemo();

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

            Iuser loggedIn = system.getLoggedInUser();
            int choice;
            boolean loggedOut = false;

            do {
                if (loggedIn instanceof LandLord) {
                    showLandlordMenu();
                    System.out.print("Choose: ");
                    Integer landlordChoice = ExceptionRentalSystem.readMenuIntOrNull(sc,
                            "Invalid input. Please enter a whole number.");
                    if (landlordChoice == null) {
                        continue;
                    }
                    choice = landlordChoice;
                    sc.nextLine();
                    loggedOut = handleLandlordChoice(choice, building, system, sc);
                    if (loggedOut || choice == 7) break;
                } else if (loggedIn instanceof Manager) {
                    showManagerMenu();
                    System.out.print("Choose: ");
                    Integer managerChoice = ExceptionRentalSystem.readMenuIntOrNull(sc,
                            "Invalid input. Please enter a whole number.");
                    if (managerChoice == null) {
                        continue;
                    }
                    choice = managerChoice;
                    sc.nextLine();
                    loggedOut = handleManagerChoice(choice, building, system, sc);
                    if (loggedOut || choice == 8) break;
                } else if (loggedIn instanceof TenantAcc) {
                    showTenantMenu();
                    System.out.print("Choose: ");
                    Integer tenantChoice = ExceptionRentalSystem.readMenuIntOrNull(sc,
                            "Invalid input. Please enter a whole number.");
                    if (tenantChoice == null) {
                        continue;
                    }
                    choice = tenantChoice;
                    sc.nextLine();
                    loggedOut = handleTenantChoice(choice, building, system, sc);
                    if (loggedOut || choice == 5) break;
                }
            } while (true);

            if (!loggedOut) break;
        }
        sc.close();
    }

    // ==================== LANDLORD ====================

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
        final double DEFAULT_WATER_RATE = 0.55;
        final double DEFAULT_ELECTRICITY_RATE = 0.20;

        switch (choice) {
            case 1: // Room submenu
                while (true) {
                    System.out.println("--- Room Menu ---");
                    System.out.println("1) Add Room");
                    System.out.println("2) Show Rooms");
                    System.out.println("3) Edit Room");
                    System.out.println("4) Back to Landlord Menu");
                    System.out.print("Choose: ");
                    Integer roomChoiceValue = ExceptionRentalSystem.readMenuIntOrNull(sc,
                            "Invalid input. Please enter a whole number.");
                    if (roomChoiceValue == null) {
                        continue;
                    }
                    int roomChoice = roomChoiceValue;
                    sc.nextLine();

                    if (roomChoice == 1) {
                        Integer addRoomId;
                        while (true) {
                            System.out.print("Enter Room ID: ");
                            addRoomId = ExceptionRentalSystem.readMenuIntOrNull(sc,
                                    "Invalid Room ID. Please enter a whole number.");
                            if (addRoomId != null) {
                                break;
                            }
                        }
                        int id = addRoomId;
                        sc.nextLine();
                        String type;
                        while (true) {
                            System.out.print("Enter Room Type (single/double): ");
                            type = sc.nextLine().trim().toLowerCase();
                            if (type.equals("single") || type.equals("double")) {
                                break;
                            }
                            System.out.println("Invalid room type. Please enter 'single' or 'double'.");
                        }
                        double price;
                        if (type.equals("single")) {
                            price = 200;
                            System.out.println("Single room detected.");
                        } else {
                            price = 480;
                            System.out.println("Double room detected.");
                        }
                        Integer enteredFloorValue;
                        while (true) {
                            System.out.print("Enter Floor Number (1-4): ");
                            enteredFloorValue = ExceptionRentalSystem.readMenuIntOrNull(sc,
                                    "Invalid floor number. Please enter a whole number between 1 and 4.");
                            if (enteredFloorValue == null) {
                                continue;
                            }
                            if (enteredFloorValue < 1 || enteredFloorValue > 4) {
                                System.out.println("Invalid floor number. Please enter a whole number between 1 and 4.");
                                continue;
                            }
                            break;
                        }
                        int enteredFloor = enteredFloorValue;
                        sc.nextLine();
                        boolean available;
                        while (true) {
                            System.out.print("Set room as available? (yes/no): ");
                            String availableInput = sc.nextLine().trim().toLowerCase();
                            if (availableInput.equals("y") || availableInput.equals("yes")) {
                                available = true;
                                break;
                            }
                            if (availableInput.equals("n") || availableInput.equals("no")) {
                                available = false;
                                break;
                            }
                            System.out.println("Invalid input. Please enter yes/no or y/n.");
                        }
                        Room room = new Room(id, type, true, price, 1
                        );
                        if (!room.setFloor(enteredFloor)) {
                            sc.nextLine();
                            System.out.println("Room not added.");
                            continue;
                        }
                        room.setAvailable(available);
                        building.addRoom(room);
                        System.out.println("Room added!");

                    } else if (roomChoice == 2) {
                        building.showRooms();

                    } else if (roomChoice == 3) {
                        Integer editRoomId;
                        while (true) {
                            System.out.print("Enter Room ID to edit (0 to cancel): ");
                            editRoomId = ExceptionRentalSystem.readMenuIntOrNull(sc,
                                    "Invalid Room ID. Please enter a whole number.");
                            if (editRoomId != null) {
                                break;
                            }
                        }
                        int editId = editRoomId;
                        sc.nextLine();
                        if (editId == 0) {
                            System.out.println("Edit cancelled.");
                            continue;
                        }
                        Room roomToEdit = null;
                        for (Room r : building.getRooms()) {
                            if (r.getRoomId() == editId) {
                                roomToEdit = r;
                                break;
                            }
                        }
                        if (roomToEdit == null) {
                            System.out.println("Room not found.");
                            continue;
                        }
                        Integer newRoomIdValue;
                        while (true) {
                            System.out.print("Enter new Room ID (0 to cancel): ");
                            newRoomIdValue = ExceptionRentalSystem.readMenuIntOrNull(sc,
                                    "Invalid Room ID. Please enter a whole number.");
                            if (newRoomIdValue != null) {
                                break;
                            }
                        }
                        int newRoomId = newRoomIdValue;
                        sc.nextLine();
                        if (newRoomId == 0) {
                            System.out.println("Edit cancelled.");
                            continue;
                        }

                        boolean sameRoomNumber = newRoomId == roomToEdit.getRoomId();
                        if (sameRoomNumber) {
                            System.out.println("Room number unchanged. You can still update other fields.");
                        }

                        boolean idConflict = false;
                        if (!sameRoomNumber) {
                            for (Room r : building.getRooms()) {
                                if (r.getRoomId() == newRoomId && r != roomToEdit) {
                                    idConflict = true;
                                    break;
                                }
                            }
                        }
                        if (idConflict) {
                            System.out.println("Room number already in use. Update cancelled.");
                            continue;
                        }

                        String newRoomType;
                        while (true) {
                            System.out.print("Enter new Room Type (single/double): ");
                            newRoomType = sc.nextLine().trim().toLowerCase();
                            if (newRoomType.equals("single") || newRoomType.equals("double")) {
                                break;
                            }
                            System.out.println("Invalid room type. Please enter 'single' or 'double'.");
                        }

                        boolean available;
                        while (true) {
                            System.out.print("Set room as available? (yes/no): ");
                            String availableInput = sc.nextLine().trim().toLowerCase();
                            if (availableInput.equals("y") || availableInput.equals("yes")) {
                                available = true;
                                break;
                            }
                            if (availableInput.equals("n") || availableInput.equals("no")) {
                                available = false;
                                break;
                            }
                            System.out.println("Invalid input. Please enter yes/no or y/n.");
                        }

                        Integer enteredRentPriceValue;
                        while (true) {
                            System.out.print("Enter new Rent Price (whole number only): ");
                            enteredRentPriceValue = ExceptionRentalSystem.readMenuIntOrNull(sc,
                                    "Invalid rent price. Please enter a whole number.");
                            if (enteredRentPriceValue == null) {
                                continue;
                            }
                            if (enteredRentPriceValue <= 0) {
                                System.out.println("Invalid rent price. Please enter a positive whole number.");
                                continue;
                            }
                            break;
                        }
                        roomToEdit.setRentPrice(enteredRentPriceValue);
                        Integer newFloorValue;
                        while (true) {
                            System.out.print("Enter new Floor Number (1-4): ");
                            newFloorValue = ExceptionRentalSystem.readMenuIntOrNull(sc,
                                    "Invalid floor number. Please enter a whole number between 1 and 4.");
                            if (newFloorValue == null) {
                                continue;
                            }
                            if (newFloorValue < 1 || newFloorValue > 4) {
                                System.out.println("Invalid floor number. Please enter a whole number between 1 and 4.");
                                continue;
                            }
                            break;
                        }
                        if (!roomToEdit.setFloor(newFloorValue)) {
                            System.out.println("Invalid floor number. Update cancelled.");
                            continue;
                        }

                        roomToEdit.setAvailable(available);

                        building.getRooms().remove(roomToEdit);
                        Room updatedRoom = new Room(newRoomId, newRoomType, roomToEdit.isAvailable(), roomToEdit.getRentPrice(), roomToEdit.getFloor());
                        building.addRoom(updatedRoom);

                        for (Iuser user : system.getUsers()) {
                            if (user instanceof TenantAcc && ((TenantAcc) user).hasLinkedTenant()) {
                                Tenant t = ((TenantAcc) user).getTenant();
                                if (t.getRoom() != null && t.getRoom().getRoomId() == editId) {
                                    t.setRoom(updatedRoom);
                                }
                            }
                        }
                        for (Contract c : contracts) {
                            if (c.getRoom() != null && c.getRoom().getRoomId() == editId) {
                                c.setRoom(updatedRoom);
                            }
                        }
                        System.out.println("Room updated!");

                    } else if (roomChoice == 4) {
                        break;
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
                break;

            case 2: // Tenant submenu
                while (true) {
                    System.out.println("--- Tenant Menu ---");
                    System.out.println("1) Add Tenant");
                    System.out.println("2) Edit Tenant");
                    System.out.println("3) Delete Tenant");
                    System.out.println("4) View Tenants");
                    System.out.println("5) Back to Landlord Menu");
                    System.out.print("Choose: ");
                    Integer tenantChoiceValue = ExceptionRentalSystem.readMenuIntOrNull(sc,
                            "Invalid input. Please enter a whole number.");
                    if (tenantChoiceValue == null) {
                        continue;
                    }
                    int tenantChoice = tenantChoiceValue;
                    sc.nextLine();

                    if (tenantChoice == 1) {
                        Tenant draftTenant = new Tenant();
                        String name;
                        while (true) {
                            System.out.print("Enter Tenant Name: ");
                            name = sc.nextLine();
                            if (draftTenant.setName(name)) break;
                        }

                        int age;
                        while (true) {
                            System.out.print("Enter Tenant Age: ");
                            if (!sc.hasNextInt()) {
                                System.out.println("Age must be a number.");
                                sc.nextLine();
                                continue;
                            }
                            age = ExceptionRentalSystem.readInt(sc);
                            sc.nextLine();
                            if (draftTenant.setAge(age)) break;
                        }

                        String phoneNumber;
                        while (true) {
                            System.out.print("Enter Tenant Phone Number (+855...): ");
                            phoneNumber = sc.nextLine();
                            if (draftTenant.setPhoneNumber(phoneNumber)) break;
                        }

                        String email;
                        while (true) {
                            System.out.print("Enter Tenant E-Mail: ");
                            email = sc.nextLine();
                            if (draftTenant.setEmail(email)) break;
                        }

                        Room assignedRoom = null;
                        while (true) {
                            System.out.print("Enter Room ID (0 to cancel): ");
                            if (!sc.hasNextInt()) {
                                System.out.println("Room ID must be a number.");
                                sc.nextLine();
                                continue;
                            }
                            int roomId = ExceptionRentalSystem.readInt(sc);
                            sc.nextLine();
                            if (roomId == 0) {
                                System.out.println("Tenant creation cancelled.");
                                break;
                            }
                            for (Room r : building.getRooms()) {
                                if (r.getRoomId() == roomId) {
                                    assignedRoom = r;
                                    break;
                                }
                            }
                            if (assignedRoom == null) {
                                System.out.println("Room not found. Please enter a valid room ID.");
                                continue;
                            }
                            boolean roomTaken = false;
                            for (Iuser user : system.getUsers()) {
                                if (user instanceof TenantAcc && ((TenantAcc) user).hasLinkedTenant()) {
                                    Tenant existingTenant = ((TenantAcc) user).getTenant();
                                    if (existingTenant != null && existingTenant.getRoom() != null
                                            && existingTenant.getRoom().getRoomId() == assignedRoom.getRoomId()) {
                                        roomTaken = true;
                                        break;
                                    }
                                }
                            }
                            if (roomTaken) {
                                System.out.println("Room is already occupied. Please choose a different room.");
                                assignedRoom = null;
                                continue;
                            }
                            break;
                        }
                        if (assignedRoom == null) continue;
                        draftTenant.setRoom(assignedRoom);

                        String tenantId;
                        while (true) {
                            System.out.print("Enter Tenant ID (numbers only): ");
                            tenantId = sc.nextLine().trim();
                            if (tenantId.matches("^\\d+$")) {
                                break;
                            }
                            System.out.println("Invalid Tenant ID. Please enter numbers only.");
                        }
                        String tenantPassword;
                        while (true) {
                            System.out.print("Enter Tenant Password (numbers only): ");
                            tenantPassword = sc.nextLine().trim();
                            if (tenantPassword.matches("^\\d+$")) {
                                break;
                            }
                            System.out.println("Invalid Tenant Password. Please enter numbers only.");
                        }
                        Tenant tenant = new Tenant(name, phoneNumber, email, assignedRoom, tenantId, age, tenantPassword);
                        TenantAcc tenantAcc = new TenantAcc(tenant, name, tenantPassword);
                        system.addUser(tenantAcc);

                        String startDate;
                        while (true) {
                            System.out.print("Enter Contract Start Date (YYYY-MM-DD): ");
                            startDate = sc.nextLine().trim();
                            if (isValidContractDate(startDate)) break;
                        }
                        Contract contract = new Contract(assignedRoom, tenant, startDate, DEFAULT_WATER_RATE, DEFAULT_ELECTRICITY_RATE);
                        contracts.add(contract);
                        System.out.println("Tenant and contract added successfully!");

                    } else if (tenantChoice == 2) {
                        String editTenantId;
                        while (true) {
                            System.out.print("Enter Tenant ID to edit (numbers only, 0 to cancel): ");
                            editTenantId = sc.nextLine().trim();
                            if ("0".equals(editTenantId) || editTenantId.matches("^\\d+$")) {
                                break;
                            }
                            System.out.println("Invalid Tenant ID. Please enter numbers only.");
                        }
                        if ("0".equals(editTenantId)) {
                            System.out.println("Edit cancelled.");
                            continue;
                        }
                        TenantAcc tenantAccEdit = null;
                        for (Iuser user : system.getUsers()) {
                            if (user instanceof TenantAcc && ((TenantAcc) user).hasLinkedTenant()) {
                                if (((TenantAcc) user).getTenant().getTenantId().equals(editTenantId)) {
                                    tenantAccEdit = (TenantAcc) user;
                                    break;
                                }
                            }
                        }
                        if (tenantAccEdit == null) {
                            System.out.println("Tenant not found.");
                        } else {
                            Tenant tenant = tenantAccEdit.getTenant();
                            String newTenantPassword;
                            while (true) {
                                System.out.print("Enter new tenant password (numbers only): ");
                                newTenantPassword = sc.nextLine().trim();
                                if (newTenantPassword.matches("^\\d+$")) {
                                    break;
                                }
                                System.out.println("Invalid Tenant Password. Please enter numbers only.");
                            }
                            tenantAccEdit.setPassword(newTenantPassword);
                            while (true) {
                                System.out.print("Enter new phone number (+855...): ");
                                String newPhone = sc.nextLine();
                                if (tenant.setPhoneNumber(newPhone)) break;
                            }
                            while (true) {
                                System.out.print("Enter new email: ");
                                String newEmail = sc.nextLine();
                                if (tenant.setEmail(newEmail)) break;
                            }
                            removeAllContractsForTenant(editTenantId);
                            String newStartDate;
                            while (true) {
                                System.out.print("Enter new contract start date (YYYY-MM-DD): ");
                                newStartDate = sc.nextLine().trim();
                                if (isValidContractDate(newStartDate)) break;
                            }
                            contracts.add(new Contract(tenant.getRoom(), tenant, newStartDate, DEFAULT_WATER_RATE, DEFAULT_ELECTRICITY_RATE));
                            System.out.println("Tenant updated and contract replaced!");
                        }

                    } else if (tenantChoice == 3) {
                        String delTenantId;
                        while (true) {
                            System.out.print("Enter Tenant ID to delete (numbers only): ");
                            delTenantId = sc.nextLine().trim();
                            if (delTenantId.matches("^\\d+$")) {
                                break;
                            }
                            System.out.println("Invalid Tenant ID. Please enter numbers only.");
                        }
                        boolean removed = false;
                        java.util.Iterator<Iuser> it = system.getUsers().iterator();
                        while (it.hasNext()) {
                            Iuser user = it.next();
                            if (user instanceof TenantAcc && ((TenantAcc) user).hasLinkedTenant()) {
                                Tenant tenantToDelete = ((TenantAcc) user).getTenant();
                                if (tenantToDelete.getTenantId().equals(delTenantId)) {
                                    if (tenantToDelete.getRoom() != null) {
                                        tenantToDelete.getRoom().setAvailable(true);
                                    }
                                    removeAllContractsForTenant(delTenantId);
                                    it.remove();
                                    removed = true;
                                }
                            }
                        }
                        System.out.println(removed ? "Tenant deleted successfully." : "Tenant not found.");

                    } else if (tenantChoice == 4) {
                        System.out.println("--- All Tenants ---");
                        boolean foundTenant = false;
                        for (Iuser user : system.getUsers()) {
                            if (user instanceof TenantAcc && ((TenantAcc) user).hasLinkedTenant()) {
                                System.out.println(((TenantAcc) user).getTenant());
                                foundTenant = true;
                            }
                        }
                        if (!foundTenant) System.out.println("No tenants found.");

                    } else if (tenantChoice == 5) {
                        break;
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
                break;

            case 3: // Manager submenu
                while (true) {
                    System.out.println("--- Manager Menu ---");
                    System.out.println("1) Add Manager");
                    System.out.println("2) Remove Manager");
                    System.out.println("3) View Managers");
                    System.out.println("4) Back to Landlord Menu");
                    System.out.print("Choose: ");
                    Integer managerChoiceValue = ExceptionRentalSystem.readMenuIntOrNull(sc,
                            "Invalid input. Please enter a whole number.");
                    if (managerChoiceValue == null) {
                        continue;
                    }
                    int managerChoice = managerChoiceValue;
                    sc.nextLine();

                    if (managerChoice == 1) {
                        String managerId;
                        while (true) {
                            System.out.print("Enter Manager ID (numbers only): ");
                            managerId = sc.nextLine().trim();
                            if (managerId.matches("^\\d+$")) {
                                break;
                            }
                            System.out.println("Invalid Manager ID. Please enter numbers only.");
                        }
                        String managerUsername;
                        while (true) {
                            System.out.print("Enter Manager Username (letters only): ");
                            managerUsername = sc.nextLine().trim();
                            if (managerUsername.matches("^[A-Za-z ]+$")) {
                                break;
                            }
                            System.out.println("Invalid Manager Username. Please enter letters only.");
                        }
                        String managerPassword;
                        while (true) {
                            System.out.print("Enter Manager Password (numbers only): ");
                            managerPassword = sc.nextLine().trim();
                            if (managerPassword.matches("^\\d+$")) {
                                break;
                            }
                            System.out.println("Invalid Manager Password. Please enter numbers only.");
                        }
                        String managerPhone;
                        while (true) {
                            System.out.print("Enter Manager Phone (+855...): ");
                            managerPhone = sc.nextLine().trim();
                            if (managerPhone.matches("^\\+855[0-9]{8,9}$")) {
                                break;
                            }
                            System.out.println("Invalid phone number format. Please use +855 followed by 8 or 9 digits.");
                        }
                        system.addUser(new Manager(managerId, managerUsername, managerPassword, managerPhone));
                        System.out.println("Manager added successfully!");

                    } else if (managerChoice == 2) {
                        System.out.print("Enter Manager ID to remove: ");
                        String removeId = sc.nextLine();
                        boolean removed = false;
                        java.util.Iterator<Iuser> it = system.getUsers().iterator();
                        while (it.hasNext()) {
                            Iuser user = it.next();
                            if (user instanceof Manager && user.getID().equals(removeId)) {
                                it.remove();
                                removed = true;
                            }
                        }
                        System.out.println(removed ? "Manager removed successfully." : "Manager not found.");

                    } else if (managerChoice == 3) {
                        System.out.println("--- All Managers ---");
                        boolean foundManager = false;
                        for (Iuser user : system.getUsers()) {
                            if (user instanceof Manager) {
                                System.out.println("Manager ID: " + user.getID() + ", Phone: " + ((Manager) user).getPhone());
                                foundManager = true;
                            }
                        }
                        if (!foundManager) System.out.println("No managers found.");
                    } else if (managerChoice == 4) {
                        break;
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
                break;

            case 4: // Contract submenu
                while (true) {
                    System.out.println("--- Contract Menu ---");
                    System.out.println("1) Add Contract");
                    System.out.println("2) Edit Contract");
                    System.out.println("3) Delete Contract");
                    System.out.println("4) View Contracts");
                    System.out.println("5) Back to Landlord Menu");
                    System.out.print("Choose: ");
                    Integer contractChoiceValue = ExceptionRentalSystem.readMenuIntOrNull(sc,
                            "Invalid input. Please enter a whole number.");
                    if (contractChoiceValue == null) {
                        continue;
                    }
                    int contractChoice = contractChoiceValue;
                    sc.nextLine();

                    if (contractChoice == 1) {
                        TenantAcc tenantAcc = null;
                        String tenantId;
                        while (true) {
                            System.out.print("Enter Tenant ID (0 to cancel): ");
                            tenantId = sc.nextLine().trim();
                            if ("0".equals(tenantId)) {
                                System.out.println("Add contract cancelled.");
                                break;
                            }
                            if (!tenantId.matches("^\\d+$")) {
                                System.out.println("Invalid Tenant ID. Please enter numbers only.");
                                continue;
                            }
                            tenantAcc = findTenantAcc(system, tenantId);
                            if (tenantAcc != null) break;
                            System.out.println("Tenant not found.");
                        }
                        if (tenantAcc == null) {
                            continue;
                        }

                        Tenant tenant = tenantAcc.getTenant();
                        removeAllContractsForTenant(tenantId);

                        String startDate;
                        while (true) {
                            System.out.print("Enter Contract Start Date (YYYY-MM-DD, 0 to cancel): ");
                            startDate = sc.nextLine().trim();
                            if ("0".equals(startDate)) {
                                System.out.println("Add contract cancelled.");
                                break;
                            }
                            if (isValidContractDate(startDate)) break;
                        }
                        if ("0".equals(startDate)) {
                            continue;
                        }

                        contracts.add(new Contract(tenant.getRoom(), tenant, startDate, DEFAULT_WATER_RATE, DEFAULT_ELECTRICITY_RATE));
                        System.out.println("Contract replaced successfully!");

                    } else if (contractChoice == 2) {
                        String editId;
                        Contract contractToEdit = null;
                        while (true) {
                            System.out.print("Enter Contract Tenant ID to edit (0 to cancel): ");
                            editId = sc.nextLine();
                            if ("0".equals(editId)) {
                                System.out.println("Edit contract cancelled.");
                                break;
                            }
                            TenantAcc tenantAcc = findTenantAcc(system, editId);
                            if (tenantAcc == null) {
                                System.out.println("Tenant not found.");
                                continue;
                            }
                            for (Contract c : contracts) {
                                if (c.getTenant().getTenantId().equals(editId)) {
                                    contractToEdit = c;
                                    break;
                                }
                            }
                            if (contractToEdit != null) break;
                            System.out.println("Contract not found for this tenant.");
                        }
                        if (contractToEdit == null) {
                            continue;
                        }

                        String newStartDate;
                        while (true) {
                            System.out.print("Enter new Start Date (YYYY-MM-DD, 0 to cancel): ");
                            newStartDate = sc.nextLine().trim();
                            if ("0".equals(newStartDate)) {
                                System.out.println("Edit contract cancelled.");
                                break;
                            }
                            if (isValidContractDate(newStartDate)) break;
                        }
                        if ("0".equals(newStartDate)) {
                            continue;
                        }

                        double newWaterRate;
                        while (true) {
                            System.out.print("Enter new Water Rate (>0, 0 to cancel): ");
                            String input = sc.nextLine();
                            if ("0".equals(input)) {
                                System.out.println("Edit contract cancelled.");
                                newWaterRate = -1;
                                break;
                            }
                            try {
                                newWaterRate = Double.parseDouble(input);
                            } catch (NumberFormatException ex) {
                                System.out.println("Water rate must be a number.");
                                continue;
                            }
                            if (newWaterRate > 0) break;
                            System.out.println("Water rate must be greater than 0.");
                        }
                        if (newWaterRate <= 0) {
                            continue;
                        }

                        double newElectricityRate;
                        while (true) {
                            System.out.print("Enter new Electricity Rate (>0, 0 to cancel): ");
                            String input = sc.nextLine();
                            if ("0".equals(input)) {
                                System.out.println("Edit contract cancelled.");
                                newElectricityRate = -1;
                                break;
                            }
                            try {
                                newElectricityRate = Double.parseDouble(input);
                            } catch (NumberFormatException ex) {
                                System.out.println("Electricity rate must be a number.");
                                continue;
                            }
                            if (newElectricityRate > 0) break;
                            System.out.println("Electricity rate must be greater than 0.");
                        }
                        if (newElectricityRate <= 0) {
                            continue;
                        }

                        removeAllContractsForTenant(editId);
                        contracts.add(new Contract(contractToEdit.getRoom(), contractToEdit.getTenant(), newStartDate, newWaterRate, newElectricityRate));
                        System.out.println("Contract updated!");

                    } else if (contractChoice == 3) {
                        String delId;
                        while (true) {
                            System.out.print("Enter Contract Tenant ID to delete (0 to cancel): ");
                            delId = sc.nextLine();
                            if ("0".equals(delId)) {
                                System.out.println("Delete contract cancelled.");
                                break;
                            }
                            boolean exists = false;
                            for (Contract c : contracts) {
                                if (c.getTenant() != null && delId.equals(c.getTenant().getTenantId())) {
                                    exists = true;
                                    break;
                                }
                            }
                            if (!exists) {
                                System.out.println("Contract not found.");
                                continue;
                            }
                            final String targetDelId = delId;
                            boolean removed = contracts.removeIf(c -> c.getTenant() != null && targetDelId.equals(c.getTenant().getTenantId()));
                            System.out.println(removed ? "Contract deleted successfully." : "Contract not found.");
                            break;
                        }

                    } else if (contractChoice == 4) {
                        System.out.println("--- All Contracts ---");
                        if (contracts.isEmpty()) {
                            System.out.println("No contracts found.");
                        } else {
                            for (Contract c : contracts) System.out.println(c);
                        }

                    } else if (contractChoice == 5) {
                        break;
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
                break;

            case 5: // Bill submenu
                while (true) {
                    System.out.println("--- Bill Menu ---");
                    System.out.println("1) View Bills");
                    System.out.println("2) Create Bill");
                    System.out.println("3) Back to Landlord Menu");
                    System.out.print("Choose: ");
                    Integer billChoiceValue = ExceptionRentalSystem.readMenuIntOrNull(sc,
                            "Invalid input. Please enter a whole number.");
                    if (billChoiceValue == null) {
                        continue;
                    }
                    int billChoice = billChoiceValue;
                    sc.nextLine();

                    if (billChoice == 1) {
                        System.out.println("--- All Bills ---");
                        if (bills.isEmpty()) {
                            System.out.println("No bills found.");
                        } else {
                            for (Bill b : bills) System.out.println(b);
                        }

                    } else if (billChoice == 2) {
                        System.out.print("Enter Tenant ID: ");
                        String billTenantId = sc.nextLine();
                        TenantAcc tenantAccEdit = findTenantAcc(system, billTenantId);
                        if (tenantAccEdit == null) {
                            System.out.println("Tenant not found.");
                            continue;
                        }
                        Contract foundContract = null;
                        for (Contract c : contracts) {
                            if (c.getTenant().getTenantId().equals(billTenantId)) {
                                foundContract = c;
                                break;
                            }
                        }
                        if (foundContract == null) {
                            System.out.println("No contract found for this tenant.");
                            continue;
                        }
                        double waterUsedInput;
                        while (true) {
                            System.out.print("Enter water used (m3, number or decimal): ");
                            String waterInput = sc.nextLine().trim();
                            if (!waterInput.matches("^\\d+(\\.\\d{1,2})?$")) {
                                System.out.println("Invalid water used value. Please enter a number with up to 2 decimal places.");
                                continue;
                            }
                            try {
                                waterUsedInput = Double.parseDouble(waterInput);
                            } catch (NumberFormatException ex) {
                                System.out.println("Invalid water used value. Please enter numbers only.");
                                continue;
                            }
                            if (waterUsedInput < 0) {
                                System.out.println("Water used must be 0 or greater.");
                                continue;
                            }
                            break;
                        }
                        int waterUsed = (int) Math.round(waterUsedInput);

                        double electricityUsedInput;
                        while (true) {
                            System.out.print("Enter electricity used (kWh, number or decimal): ");
                            String electricityInput = sc.nextLine().trim();
                            if (!electricityInput.matches("^\\d+(\\.\\d{1,2})?$")) {
                                System.out.println("Invalid electricity used value. Please enter a number with up to 2 decimal places.");
                                continue;
                            }
                            try {
                                electricityUsedInput = Double.parseDouble(electricityInput);
                            } catch (NumberFormatException ex) {
                                System.out.println("Invalid electricity used value. Please enter numbers only.");
                                continue;
                            }
                            if (electricityUsedInput < 0) {
                                System.out.println("Electricity used must be 0 or greater.");
                                continue;
                            }
                            break;
                        }
                        int electricityUsed = (int) Math.round(electricityUsedInput);
                        Bill bill = new Bill(foundContract, waterUsed, electricityUsed, foundContract.getRoom());
                        bills.add(bill);
                        System.out.println("Bill created successfully!");
                        System.out.println(bill);

                    } else if (billChoice == 3) {
                        break;
                    } else {
                        System.out.println("Invalid choice.");
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

    // ==================== MANAGER ====================

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
        final double DEFAULT_WATER_RATE = 0.75;
        final double DEFAULT_ELECTRICITY_RATE = 0.20;

        switch (choice) {
            case 1:
                building.showRooms();
                break;
            case 2:
                System.out.println("--- All Contracts ---");
                if (contracts.isEmpty()) {
                    System.out.println("No contracts found.");
                } else {
                    for (Contract c : contracts) System.out.println(c);
                }
                break;
            case 3:
                System.out.println("--- All Bills ---");
                if (bills.isEmpty()) {
                    System.out.println("No bills found.");
                } else {
                    for (Bill b : bills) System.out.println(b);
                }
                break;
            case 4:
                Integer updateRoomIdValue;
                while (true) {
                    System.out.print("Enter Room ID to update (0 to cancel): ");
                    updateRoomIdValue = ExceptionRentalSystem.readMenuIntOrNull(sc,
                            "Invalid Room ID. Please enter a whole number.");
                    if (updateRoomIdValue != null) {
                        break;
                    }
                }
                int updateRoomId = updateRoomIdValue;
                sc.nextLine();
                if (updateRoomId == 0) {
                    System.out.println("Update cancelled.");
                    break;
                }
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
                    boolean available;
                    while (true) {
                        System.out.print("Set room as available? (yes/no): ");
                        String availableInput = sc.nextLine().trim().toLowerCase();
                        if (availableInput.equals("y") || availableInput.equals("yes")) {
                            available = true;
                            break;
                        }
                        if (availableInput.equals("n") || availableInput.equals("no")) {
                            available = false;
                            break;
                        }
                        System.out.println("Invalid input. Please enter yes/no or y/n.");
                    }
                    roomToUpdate.setAvailable(available);
                    System.out.println("Room status updated. Now: " + (available ? "Available" : "Not Available"));
                }
                break;
            case 5:
                System.out.println("--- All Tenants ---");
                boolean foundTenant = false;
                for (Iuser user : system.getUsers()) {
                    if (user instanceof TenantAcc && ((TenantAcc) user).hasLinkedTenant()) {
                        System.out.println(((TenantAcc) user).getTenant());
                        foundTenant = true;
                    }
                }
                if (!foundTenant) System.out.println("No tenants found.");
                break;
            case 6:
                String updateTenantId;
                while (true) {
                    System.out.print("Enter Tenant ID to update (numbers only, 0 to cancel): ");
                    updateTenantId = sc.nextLine().trim();
                    if ("0".equals(updateTenantId) || updateTenantId.matches("^\\d+$")) {
                        break;
                    }
                    System.out.println("Invalid Tenant ID. Please enter numbers only.");
                }
                if ("0".equals(updateTenantId)) {
                    System.out.println("Update cancelled.");
                    break;
                }
                TenantAcc tenantAcc = findTenantAcc(system, updateTenantId);
                if (tenantAcc == null) {
                    System.out.println("Tenant not found.");
                } else {
                    Tenant tenant = tenantAcc.getTenant();
                    while (true) {
                        System.out.print("Enter new phone number (+855...): ");
                        String newPhone = sc.nextLine().trim();
                        if (!newPhone.matches("^\\+855[0-9]{8,9}$")) {
                            System.out.println("Invalid phone number format. Please use +855 followed by 8 or 9 digits.");
                            continue;
                        }
                        if (tenant.setPhoneNumber(newPhone)) {
                            break;
                        }
                    }
                    while (true) {
                        System.out.print("Enter new email (gmail only): ");
                        String newEmail = sc.nextLine().trim();
                        if (!newEmail.matches("^[A-Za-z0-9+_.-]+@gmail\\.com$")) {
                            System.out.println("Invalid email format. Please enter a Gmail address (example@gmail.com).");
                            continue;
                        }
                        if (tenant.setEmail(newEmail)) {
                            break;
                        }
                    }
                    removeAllContractsForTenant(updateTenantId);
                    String newStartDate;
                    while (true) {
                        System.out.print("Enter new contract start date (YYYY-MM-DD): ");
                        newStartDate = sc.nextLine().trim();
                        if (isValidContractDate(newStartDate)) break;
                    }
                    contracts.add(new Contract(tenant.getRoom(), tenant, newStartDate, DEFAULT_WATER_RATE, DEFAULT_ELECTRICITY_RATE));
                    System.out.println("Tenant updated and contract replaced!");
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

    // ==================== TENANT ====================

    public static void showTenantMenu() {
        System.out.println("\n=== Tenant Menu ===");
        System.out.println("1) View My Room");
        System.out.println("2) View My Contract");
        System.out.println("3) View My Bills");
        System.out.println("4) Logout");
        System.out.println("5) Exit");
    }

    public static boolean handleTenantChoice(int choice, Building building, RentalSystem system, Scanner sc) {
        Tenant tenant = getLoggedInTenant(system);

        switch (choice) {
            case 1:
                if (tenant != null && tenant.getRoom() != null) {
                    Room room = tenant.getRoom();
                    System.out.println("--- My Room ---");
                    System.out.println("Room Number: " + room.getRoomId());
                    System.out.println("Room Type: " + room.getRoomType());
                    System.out.println("Floor: " + room.getFloor());
                    System.out.println("Rent: $" + room.getRentPrice());
                    System.out.println("=======================================");
                } else {
                    System.out.println("No room information found.");
                }
                break;
            case 2:
                if (tenant != null) {
                    System.out.println("--- My Contract ---");
                    Contract myContract = getLatestContractForTenant(tenant.getTenantId());
                    System.out.println(myContract != null ? myContract : "No contract found.");
                } else {
                    System.out.println("No contract information found.");
                }
                break;
            case 3:
                if (tenant != null) {
                    System.out.println("--- My Bills ---");
                    boolean found = false;
                    for (Bill b : bills) {
                        if (b != null && b.getContract() != null && b.getContract().getTenant() != null
                                && tenant.getTenantId().equals(b.getContract().getTenant().getTenantId())) {
                            System.out.println(b);
                            found = true;
                        }
                    }
                    if (!found) System.out.println("No bills found.");
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

    // ==================== HELPERS ====================

    private static Tenant getLoggedInTenant(RentalSystem system) {
        Iuser loggedUser = system.getLoggedInUser();
        if (loggedUser instanceof TenantAcc && ((TenantAcc) loggedUser).hasLinkedTenant()) {
            return ((TenantAcc) loggedUser).getTenant();
        }
        return null;
    }

    private static TenantAcc findTenantAcc(RentalSystem system, String tenantId) {
        for (Iuser user : system.getUsers()) {
            if (user instanceof TenantAcc && ((TenantAcc) user).hasLinkedTenant()) {
                if (((TenantAcc) user).getTenant().getTenantId().equals(tenantId)) {
                    return (TenantAcc) user;
                }
            }
        }
        return null;
    }

    private static Contract getLatestContractForTenant(String tenantId) {
        if (tenantId == null) return null;
        for (int i = contracts.size() - 1; i >= 0; i--) {
            Contract c = contracts.get(i);
            if (c != null && c.getTenant() != null && tenantId.equals(c.getTenant().getTenantId())) {
                return c;
            }
        }
        return null;
    }

    private static void removeAllContractsForTenant(String tenantId) {
        if (tenantId == null) return;
        contracts.removeIf(c -> c != null && c.getTenant() != null && tenantId.equals(c.getTenant().getTenantId()));
    }

    private static boolean isValidContractDate(String dateInput) {
        if (!dateInput.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return false;
        }
        try {
            LocalDate parsedDate = LocalDate.parse(dateInput);
            if (parsedDate.isBefore(MIN_CONTRACT_DATE)) {
                System.out.println("Date must be 2026-01-01 or later.");
                return false;
            }
            return true;
        } catch (java.time.format.DateTimeParseException ex) {
            System.out.println("Invalid date value. Please enter a real date in YYYY-MM-DD format.");
            return false;
        }
    }
}

 