import java.util.ArrayList;
import java.util.Scanner;

public class Contract {
    // global list of contracts for simple storage
    private static ArrayList<Contract> contracts = new ArrayList<>();

    // Contract signing details
    private Room room;
    private Tenant tenant;
    private String startDate;

    // water and electricity rates
    private double waterRate;
    private double electricityRate;

    public Contract(Room room, Tenant tenant, String startDate, double waterRate, double electricityRate) {
        this.room = room;
        this.tenant = tenant;
        this.startDate = startDate;

        setWaterRate(waterRate);
        setElectricityRate(electricityRate);
        if (room != null) {
            room.setAvailable(false); // mark room occupied
        }
        contracts.add(this);
    }

    public static Contract findContractByTenantID(String tenantID) {
        for (Contract c : contracts) {
            if (c.tenant != null && tenantID.equals(c.tenant.getTenantId())) {
                return c;
            }
        }
        return null;
    }

    public static void displayAllContracts() {
        if (contracts.isEmpty()) {
            System.out.println("No contracts have been created yet.");
            return;
        }
        for (Contract c : contracts) {
            System.out.println(c);
        }
    }

    public static void contractDisplayMenu(RentalSystem system) {
        Scanner sc = new Scanner(System.in);
        int contractChoice;
        do {
            System.out.println("1) Add Contract Details");
            System.out.println("2) View Contracts");
            System.out.println("3) Search Contract by Tenant ID");
            System.out.println("4) Edit/Delete Contracts");
            System.out.println("5) Exit");
            System.out.print("Choose: ");
            contractChoice = sc.nextInt();
            sc.nextLine();
            switch (contractChoice) {
                case 1: // only landlord can create contract
                    if (!system.requirePermission(RentalSystem.Create_Contract))
                        break;

                    System.out.print("Enter Tenant ID: ");
                    String tenantID = sc.nextLine();
                    System.out.print("Enter Tenant Full Name: ");
                    String tenantName = sc.nextLine();
                    System.out.print("Enter Tenant Age: ");
                    int tenantAge = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Tenant Phone Number: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Tenant E-Mail: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Tenant Password: ");
                    String password = sc.nextLine();
                    System.out.print("Enter Room ID: ");
                    int roomId = sc.nextInt();
                    sc.nextLine();
                    Room room = new Room(roomId, "Unknown", true, 0.0, 0);
                    Tenant tenant = new Tenant(tenantName, phone, email, room, tenantID, tenantAge, password);
                    System.out.print("Enter Start Date (YYYY-MM-DD): ");
                    String start = sc.nextLine();
                    System.out.print("Enter Water Rate: ");
                    double wRate = sc.nextDouble();
                    System.out.print("Enter Electricity Rate: ");
                    double eRate = sc.nextDouble();
                    new Contract(room, tenant, start, wRate, eRate);
                    System.out.println("Contract added successfully!");
                    break;
                case 2: // view contracts
                    if (!system.requirePermission(RentalSystem.View_Contract))
                        break;
                    displayAllContracts();
                    break;
                case 3: // search contract
                    if (!system.requirePermission(RentalSystem.View_Contract))
                        break;
                    System.out.print("Find Contract by Tenant ID: ");
                    String searchId = sc.nextLine();
                    Contract found = findContractByTenantID(searchId);
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Contract not found.");
                    }
                    break;
                case 4: // edit/delete submenu
                    if (!system.requirePermission(RentalSystem.Update_Contract) &&
                        !system.requirePermission(RentalSystem.Delete_Contract)) {
                        System.out.println("You do not have permission to modify contracts.");
                        break;
                    }
                    int subChoice;
                    do {
                        System.out.println(" 1) Edit Contract");
                        System.out.println(" 2) Delete Contract");
                        System.out.println(" 3) Back");
                        System.out.print("Choose: ");
                        subChoice = sc.nextInt();
                        sc.nextLine();
                        switch (subChoice) {
                            case 1:
                                if (!system.requirePermission(RentalSystem.Update_Contract))
                                    break;
                                System.out.print("Enter Tenant ID of contract to edit: ");
                                String editId = sc.nextLine();
                                Contract toEdit = findContractByTenantID(editId);
                                if (toEdit != null) {
                                    System.out.print("Enter new water rate: ");
                                    double newW = sc.nextDouble();
                                    sc.nextLine();
                                    toEdit.setWaterRate(newW);
                                    System.out.print("Enter new electricity rate: ");
                                    double newE = sc.nextDouble();
                                    sc.nextLine();
                                    toEdit.setElectricityRate(newE);
                                    System.out.println("Contract updated.");
                                } else {
                                    System.out.println("Contract not found.");
                                }
                                break;
                            case 2:
                                if (!system.requirePermission(RentalSystem.Delete_Contract))
                                    break;
                                System.out.print("Enter Tenant ID of contract to delete: ");
                                String delId = sc.nextLine();
                                Contract toDelete = findContractByTenantID(delId);
                                if (toDelete != null) {
                                    contracts.remove(toDelete);
                                    System.out.println("Contract deleted.");
                                } else {
                                    System.out.println("Contract not found.");
                                }
                                break;
                            case 3:
                                System.out.println("Returning to contract menu.");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    } while (subChoice != 3);
                    break;
                case 5:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (contractChoice != 5);
        sc.close();
    }

    public double getWaterRate() {
        return waterRate;
    }

    public double getElectricityRate() {
        return electricityRate;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public Room getRoom() {
        return room;
    }

    private void setWaterRate(double waterRate) {
        if (waterRate <= 0) {
            System.out.println("Water rate cannot be negative nor zero. ");
            return;
        }
        this.waterRate = waterRate;
    }

    private void setElectricityRate(double electricityRate) {
        if (electricityRate <= 0) {
            System.out.println("Electricity rate cannot be negative nor zero. ");
            return;
        }
        this.electricityRate = electricityRate;
    }

    @Override
    public String toString() {
        return "Tenant: " + (tenant != null ? tenant.getName() : "N/A") + "\n"
                + "Room: " + (room != null ? room.getRoomId() : "N/A") + "\n"
                + "Start Date: " + startDate + "\n"
                + "Water Rate: $" + waterRate + " Per m3\n"
                + "Electricity Rate: $" + electricityRate + " Per kWh\n"
                + "======================================";
    }
}