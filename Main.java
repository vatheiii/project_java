import java.util.ArrayList;//to store data 

public class Main {

    public static void main(String[] args) {
        ArrayList<Room> rooms = new ArrayList<>();  
        ArrayList<Tenant> tenants = new ArrayList<>();
        ArrayList<Contract> contracts = new ArrayList<>();
        ArrayList<Bill>bills=new ArrayList<>();

        Room room1 = new Room(101, "Single Bedroom", true, 500, 1);
        rooms.add(room1);
        
        Tenant t1 = new Tenant("Alice","0123456789","alice@gmail.com",room1,true,"T01",18);
        tenants.add(t1);

        Contract C1 = new Contract(room1, t1,"2023-01-01",0.5,0.5);
        contracts.add(C1);

        Bill b1 = new Bill(C1,10,50);
        bills.add(b1);

        //F1 : Primative Copy
        double a = 100;
        double b = a;
        b+=50;
        System.out.println("F1"+a+"vs"+b);

        //F2 : Reference Copy
        Room r2 = room1;
        r2.setRentPrice(900);
        System.out.println("F2: "+ room1.getRentPrice());

        //F3 : Array Reference
        Room[] arr = new Room[1];
        arr[0]=room1;
        arr[0].setRentPrice(1000);
        System.out.println("F3: " + room1.getRentPrice());
        
        //F4 : Snapshot
        System.out.println("F4 Snapshot Rent: " + C1.getRentAtContractTime());
        System.out.println("Current Rent: " + room1.getRentPrice());

        Choice.displayMenu(rooms, tenants, contracts, bills);
    }
}
