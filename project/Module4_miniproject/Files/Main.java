import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PackageService service = new PackageServiceImpl();

        while (true) {
            System.out.println("\n----- Holiday Package Management -----");
            System.out.println("1. Add Package Details");
            System.out.println("2. Display All Package Details");
            System.out.println("3. Search Package by ID");
            System.out.println("4. Calculate Package Cost by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Enter Package ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Source Place: ");
                    String source = sc.nextLine();
                    System.out.print("Enter Destination Place: ");
                    String dest = sc.nextLine();
                    System.out.print("Enter Number of Days: ");
                    int days = sc.nextInt();
                    System.out.print("Enter Basic Fare: ");
                    double fare = sc.nextDouble();

                    Package pkg = new Package(id, source, dest, days, fare);
                    try {
                        service.addPackage(pkg);
                        System.out.println("Package added successfully!");
                    } catch (InvalidPackageIdException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    List<Package> allPackages = service.fetchAllPackages();
                    if (allPackages.isEmpty()) {
                        System.out.println("No packages available.");
                    } else {
                        for (Package p : allPackages) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Package ID to search: ");
                    String searchId = sc.nextLine();
                    Package foundPkg = service.findPackageById(searchId);
                    if (foundPkg != null) {
                        System.out.println(foundPkg);
                    } else {
                        System.out.println("Package not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Package ID to calculate cost: ");
                    String calcId = sc.nextLine();
                    try {
                        service.calculatePackageCost(calcId);
                        System.out.println("Cost calculated successfully.");
                        Package updatedPkg = service.findPackageById(calcId);
                        System.out.println(updatedPkg);
                    } catch (InvalidPackageIdException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Exiting the application.");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
