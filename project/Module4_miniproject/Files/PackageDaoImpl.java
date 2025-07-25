import java.util.*;

public class PackageDaoImpl implements PackageDao {
    private List<Package> packageList = new ArrayList<>();

    @Override
    public void addPackage(Package pkg) {
        packageList.add(pkg);
    }

    @Override
    public List<Package> getAllPackages() {
        return packageList;
    }

    @Override
    public Package findPackageById(String packageId) {
        for (Package pkg : packageList) {
            if (pkg.getPackageId().equals(packageId)) {
                return pkg;
            }
        }
        return null;
    }

    @Override
    public void calculatePackageCost(Package pkg) {
        double basicFare = pkg.getBasicFare();
        int noOfDays = pkg.getNoOfDays();
        double totalFare = basicFare * noOfDays;
        double discount = 0;

        if (noOfDays > 5 && noOfDays <= 8) {
            discount = totalFare * 0.03;
        } else if (noOfDays > 8 && noOfDays <= 10) {
            discount = totalFare * 0.05;
        } else if (noOfDays > 10) {
            discount = totalFare * 0.07;
        }

        double costAfterDiscount = totalFare - discount;
        double gst = costAfterDiscount * 0.12;
        double finalCost = costAfterDiscount + gst;

        pkg.setPackageCost(finalCost);
    }
}
