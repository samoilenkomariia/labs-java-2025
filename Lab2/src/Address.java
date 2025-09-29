public class Address {
    private String region;
    private String locality;
    private String district;
    private String street;
    private String house;
    private String apartment;

    public void setRegion(String region) {
        String[] parts = region.trim().split(" ", 2);
        if (!parts[0].equalsIgnoreCase("АР")) this.region = capitalize(parts[0]) + " " + parts[1];
        else this.region = parts[0].toUpperCase() + " " + capitalize(parts[1]);
    }

    public void setDistrict(String district) {
        String[] parts = district.trim().split(" ", 2);
        this.district = capitalize(parts[0]) + " " + parts[1];
    }

    public void setLocality(String locality) {
        String[] parts = locality.trim().split(" ", 2);
        this.locality = parts[0] + " " + capitalize(parts[1]);
    }

    public void setStreet(String street) {
        String[] parts = street.trim().split(" ", 2);
        this.street = parts[0] + " " + capitalize(parts[1]);
    }

    public void setHouse(String house) {
        this.house = house.trim();
    }

    public void setApartment(String apartment) {
        this.apartment = apartment != null ? apartment.trim() : null;
    }

    private String capitalize(String s) {
        s = s.trim();
        s = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        String[] dividers = {" ", "-"};
        for (var d : dividers) {
            if (s.contains(d)) {
                int dividerIndex = s.indexOf(d);
                s = s.substring(0, dividerIndex + 1) +
                        s.substring(dividerIndex + 1, dividerIndex + 2).toUpperCase() +
                        s.substring(dividerIndex + 2);
            }
        }
        return s;
    }

    @Override public String toString() {
        if (apartment != null) {
            return "%s, %s, %s, %s, %s, %s".formatted(region, district, locality, street, house, apartment);
        }
        return "%s, %s, %s, %s, %s".formatted(region, district, locality, street, house);
    }
}