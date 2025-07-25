import java.util.List;

public class PackageServiceImpl implements PackageService {
    private PackageDao dao = new PackageDaoImpl();

    @Override
    public void addPackage(Package pkg) throws InvalidPackageIdException {
        if (pkg.getPackageId().length() != 7) {
            throw new InvalidPackageIdException("Invalid Package Id");
        }
        dao.addPackage(pkg);
    }

    @Override
    public List<Package> fetchAllPackages() {
        return dao.getAllPackages();
    }

    @Override
    public Package findPackageById(String packageId) {
        return dao.findPackageById(packageId);
    }

    @Override
    public void calculatePackageCost(String packageId) throws InvalidPackageIdException {
        Package pkg = dao.findPackageById(packageId);
        if (pkg == null) {
            throw new InvalidPackageIdException("Invalid Package Id");
        }
        dao.calculatePackageCost(pkg);
    }
}
