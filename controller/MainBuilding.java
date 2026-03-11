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

public class MainBuilding {
    private static java.util.ArrayList<Contract> contracts = new java.util.ArrayList<>();
    private static java.util.ArrayList<Bill> bills = new java.util.ArrayList<>();

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
                    choice = sc.nextInt();
                    sc.nextLine();
                    loggedOut = handleLandlordChoice(choice, building, system, sc);
                    if (loggedOut || choice == 7) break;
                } else if (loggedIn instanceof Manager) {
                    showManagerMenu();
                    System.out.print("Choose: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    loggedOut = handleManagerChoice(choice, building, system, sc);
                    if (loggedOut || choice == 8) break;
                } else if (loggedIn instanceof TenantAcc) {
                    showTenantMenu();
                    System.out.print("Choose: ");
                    choice = sc.nextInt();
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
                            price = 200;
                            System.out.println("Single room detected.");
                        } else if (type.equals("double")) {
                            price = 480;
                            System.out.println("Double room detected.");
                        } else {
                            System.out.println("Invalid room type. Room not added.");
                            continue;
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
                            continue;
                        }
                        System.out.print("Enter new Room ID: ");
                        int newRoomId = sc.nextInt();
                        sc.nextLine();

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

                        System.out.print("Set room as available? (y/n): ");
                        boolean available = sc.nextLine().equalsIgnoreCase("y");
                        System.out.print("Enter new Rent Price: ");
                        double newPrice = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter new Floor Number: ");
                        int newFloor = sc.nextInt();
                        sc.nextLine();

                        building.getRooms().remove(roomToEdit);
                        Room updatedRoom = new Room(newRoomId, roomToEdit.getRoomType(), available, newPrice, newFloor);
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
                    int tenantChoice = sc.nextInt();
                    sc.nextLine();

                    if (tenantChoice == 1) {
                        System.out.print("Enter Tenant Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Tenant Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        if (age < 18 || age > 100) {
                            System.out.println("Tenant must be between 18 and 100 years old. Tenant not added.");
                            continue;
                        }
                        System.out.print("Enter Tenant Phone Number (+855...): ");
                        String phoneNumber = sc.nextLine();
                        System.out.print("Enter Tenant E-Mail: ");
                        String email = sc.nextLine();

                        Room assignedRoom = null;
                        while (true) {
                            System.out.print("Enter Room ID: ");
                            int roomId = sc.nextInt();
                            sc.nextLine();
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
                                    if (((TenantAcc) user).getTenant().getRoom().getRoomId() == assignedRoom.getRoomId()) {
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

                        System.out.print("Enter Tenant ID: ");
                        String tenantId = sc.nextLine();
                        System.out.print("Enter Tenant Password: ");
                        String tenantPassword = sc.nextLine();
                        Tenant tenant = new Tenant(name, phoneNumber, email, assignedRoom, tenantId, age, tenantPassword);
                        TenantAcc tenantAcc = new TenantAcc(tenant, name, tenantPassword);
                        system.addUser(tenantAcc);

                        System.out.print("Enter Contract Start Date (YYYY-MM-DD): ");
                        String startDate = sc.nextLine();
                        Contract contract = new Contract(assignedRoom, tenant, startDate, DEFAULT_WATER_RATE, DEFAULT_ELECTRICITY_RATE);
                        contracts.add(contract);
                        System.out.println("Tenant and contract added successfully!");

                    } else if (tenantChoice == 2) {
                        System.out.print("Enter Tenant ID to edit: ");
                        String editTenantId = sc.nextLine();
                        TenantAcc tenantAcc = null;
                        for (Iuser user : system.getUsers()) {
                            if (user instanceof TenantAcc && ((TenantAcc) user).hasLinkedTenant()) {
                                if (((TenantAcc) user).getTenant().getTenantId().equals(editTenantId)) {
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
                            tenant.setPhoneNumber(sc.nextLine());
                            System.out.print("Enter new email: ");
                            tenant.setEmail(sc.nextLine());
                            removeAllContractsForTenant(editTenantId);
                            System.out.print("Enter new contract start date (YYYY-MM-DD): ");
                            String newStartDate = sc.nextLine();
                            contracts.add(new Contract(tenant.getRoom(), tenant, newStartDate, DEFAULT_WATER_RATE, DEFAULT_ELECTRICITY_RATE));
                            System.out.println("Tenant updated and contract replaced!");
                        }

                    } else if (tenantChoice == 3) {
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
                System.out.println("--- Manager Menu ---");
                System.out.println("1) Add Manager");
                System.out.println("2) Remove Manager");
                System.out.println("3) View Managers");
                System.out.println("4) Back to Landlord Menu");
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
                    int contractChoice = sc.nextInt();
                    sc.nextLine();

                    if (contractChoice == 1) {
                        System.out.print("Enter Tenant ID: ");
                        String tenantId = sc.nextLine();
                        TenantAcc tenantAcc = findTenantAcc(system, tenantId);
                        if (tenantAcc == null) {
                            System.out.println("Tenant not found.");
                        } else {
                            Tenant tenant = tenantAcc.getTenant();
                            removeAllContractsForTenant(tenantId);
                            System.out.print("Enter Contract Start Date (YYYY-MM-DD): ");
                            String startDate = sc.nextLine();
                            contracts.add(new Contract(tenant.getRoom(), tenant, startDate, DEFAULT_WATER_RATE, DEFAULT_ELECTRICITY_RATE));
                            System.out.println("Contract replaced successfully!");
                        }

                    } else if (contractChoice == 2) {
                        System.out.print("Enter Contract Tenant ID to edit: ");
                        String editId = sc.nextLine();
                        TenantAcc tenantAcc = findTenantAcc(system, editId);
                        if (tenantAcc == null) {
                            System.out.println("Tenant not found.");
                            continue;
                        }
                        Contract contractToEdit = null;
                        for (Contract c : contracts) {
                            if (c.getTenant().getTenantId().equals(editId)) {
                                contractToEdit = c;
                                break;
                            }
                        }
                        if (contractToEdit == null) {
                            System.out.println("Contract not found for this tenant.");
                        } else {
                            System.out.print("Enter new Start Date (YYYY-MM-DD): ");
                            String newStartDate = sc.nextLine();
                            System.out.print("Enter new Water Rate: ");
                            double newWaterRate = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("Enter new Electricity Rate: ");
                            double newElectricityRate = sc.nextDouble();
                            sc.nextLine();
                            removeAllContractsForTenant(editId);
                            contracts.add(new Contract(contractToEdit.getRoom(), contractToEdit.getTenant(), newStartDate, newWaterRate, newElectricityRate));
                            System.out.println("Contract updated!");
                        }

                    } else if (contractChoice == 3) {
                        System.out.print("Enter Contract Tenant ID to delete: ");
                        String delId = sc.nextLine();
                        boolean removed = contracts.removeIf(c -> c.getTenant().getTenantId().equals(delId));
                        System.out.println(removed ? "Contract deleted successfully." : "Contract not found.");

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
                    int billChoice = sc.nextInt();
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
                        TenantAcc tenantAcc = findTenantAcc(system, billTenantId);
                        if (tenantAcc == null) {
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
                    boolean available = sc.nextLine().equalsIgnoreCase("y");
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
                System.out.print("Enter Tenant ID to update: ");
                String updateTenantId = sc.nextLine();
                TenantAcc tenantAcc = findTenantAcc(system, updateTenantId);
                if (tenantAcc == null) {
                    System.out.println("Tenant not found.");
                } else {
                    Tenant tenant = tenantAcc.getTenant();
                    System.out.print("Enter new phone number (+855...): ");
                    tenant.setPhoneNumber(sc.nextLine());
                    System.out.print("Enter new email: ");
                    tenant.setEmail(sc.nextLine());
                    removeAllContractsForTenant(updateTenantId);
                    System.out.print("Enter new contract start date (YYYY-MM-DD): ");
                    String newStartDate = sc.nextLine();
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
                    System.out.println("Availability: " + (room.isAvailable() ? "Available" : "Not Available"));
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
}

 