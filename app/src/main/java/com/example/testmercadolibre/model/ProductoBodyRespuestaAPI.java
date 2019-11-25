package com.example.testmercadolibre.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductoBodyRespuestaAPI {

    public String query = "";
    public Paging paging = new Paging();
    public List<Results> results = new ArrayList<>();

    public class Paging {

        public int total = 0;
        public int offset = 0;
        public int limit = 0;
        public int primary_results = 0;
    }

    public class Results implements Serializable {

        public String id = "";
        public String title = "";
        public float price = 0;
        public String permalink = "";
        public String thumbnail = "";
        public Address address = new Address();
        public List<Attributes> attributes = new ArrayList<>();
    }

    public class Address implements Serializable {

        public String state_name = "";
        public String city_name = "";
    }

    public class Attributes implements Serializable{

        public String name = "";
        public String value_name = "";

    }

}