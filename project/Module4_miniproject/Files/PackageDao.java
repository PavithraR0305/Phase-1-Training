import java.util.List;

public interface PackageDao {
    void addPackage(Package pkg);
    List<Package> getAllPackages();
    Package findPackageById(String packageId);
    void calculatePackageCost(Package pkg);
}
