import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueValues {
    public Set<String> getUniqueYears(List<RowData> rows) {
        Set<String> uniqueYears = new HashSet<>();
        for(RowData row: rows) {
            uniqueYears.add(row.getYear());
        }
        return uniqueYears;
    }

    public Set<String> getUniqueQuarters(List<RowData> rows) {
        Set<String> uniqueQuarters = new HashSet<>();
        for(RowData row: rows) {
            uniqueQuarters.add(row.getQuarter());
        }
        return uniqueQuarters;
    }

    public Set<String> getUniqueVendors(List<RowData> rows) {
        Set<String> uniqueVendors = new HashSet<>();
        for(RowData row: rows) {
            uniqueVendors.add(row.getVendor());
        }
        return uniqueVendors;
    }
}
