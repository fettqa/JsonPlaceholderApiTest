package pojo;

import lombok.Data;

@Data
public class Users {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private Company.Address address;
    private String phone;
    private String website;
    private Company company;

    @Data
    static class Company {
        private String name;
        private String catchPhrase;
        private String bs;

        @Data
        static class Address {
            private String street;
            private String suite;
            private String city;
            private String zipcode;
            private Geo geo;
        }

        @Data
        static class Geo {
            private String lat;
            private String lng;
        }
    }
}
