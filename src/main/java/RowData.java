public class RowData {
    private final String country;
    private final String year;
    private final String quarter;
    private final String vendor;
    private final String units;

    public String getCountry() {
        return country;
    }

    public String getYear() {
        return year;
    }

    public String getQuarter() {
        return quarter;
    }

    public String getVendor() {
        return vendor;
    }

    public String getUnits() {
        return units;
    }

    public RowData(String country, String year, String quarter, String vendor, String units) {
        this.country = country;
        this.year = year;
        this.quarter = quarter;
        this.vendor = vendor;
        this.units = units;
    }

    @Override
    public String toString() {
        return  "Country: " + country +
                " | Year: " + year +
                " | Quarter: " + quarter +
                " | Vendor: " + vendor +
                " | Units: " + units;
    }
}
