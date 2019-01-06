package com.example.sara.vanroosbroeck;

public class MotorValues {
    private String name;
    private Integer year_build;
    private Float price_buy;
    private Float price_sale;
    private String descriptioin;
    private Integer waranty;
    private String motorsize;
    private String power;
    private Integer km;
    private Boolean first_owner;
    private Boolean sold;
    private Boolean website;
    private Boolean home_pagina;

    private String filename_main;
    private String filename_1;
    private String filename_2;
    private String filename_3;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (this.name != null)
        this.name = name;
        else
            this.name = "Onbekend";
    }


    public Integer getYear_build() {
        return year_build;
    }

    public void setYear_build(Integer year_build) {
       // if (year_build!= null)
        this.year_build = year_build;
        //else
          //  this.year_build=0;
    }

    public Float getPrice_buy() {
        return price_buy;
    }

    public void setPrice_buy(Float price_buy) {
        //if(this.price_buy!= null)
        this.price_buy = price_buy;
        //else
           // this.price_buy = 0.0f ;
    }

    //TODO: Floats omvormen naar doubles

    public Float getPrice_sale() {
        return price_sale;
    }

    public void setPrice_sale(Float price_sale) {
       // if(this.price_sale!= null)
            this.price_sale = price_sale;
       // else
          //  this.price_sale = 0.0f ;
    }

    public String getDescriptioin() {
        return descriptioin;
    }

    public void setDescriptioin(String descriptioin) {
      //  if (this.descriptioin!=null)
        this.descriptioin = descriptioin;
       // else
         //   this.descriptioin="Geen beschrijving";
    }

    public Integer getWaranty() {
        return waranty;
    }

    public void setWaranty(Integer waranty) {
      //  if (this.waranty!=null)
        this.waranty = waranty;
       // else
         //   this.waranty=0;
    }

    public String getMotorsize() {
        return motorsize;
    }

    public void setMotorsize(String motorsize) {
       // if(this.motorsize!= null)
        this.motorsize = motorsize;
        //else
          //  this.motorsize="";
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
       // if(this.power != null)
        this.power = power;
       // else
         //   this.power = "";
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
       // if(this.km!=null)
        this.km = km;
       // else
         //   this.km=0;
    }

    public Boolean getFirst_owner(){
        return first_owner;
    }

    public void setFirst_owner(Boolean first_owner) {
        this.first_owner = first_owner;
    }

    public Boolean getSold(){
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold   = sold;
    }

    public Boolean getWebsite(){
        return website;
    }

    public void setWebsite(Boolean website) {
        this.website = website;
    }

    public Boolean getHome_pagina(){
        return home_pagina;
    }

    public void setHome_pagina(Boolean home_pagina) {
        this.home_pagina = home_pagina;
    }



    public String getFilename_main() {
        return filename_main;
    }

    public void setFilename_main(String filename_main) {
        this.filename_main = filename_main;
    }

    public String getFilename_1() {
        return filename_1;
    }

    public void setFilename_1(String filename_1) {
        this.filename_1 = filename_1;
    }

    public String getFilename_2() {
        return filename_2;
    }

    public void setFilename_2(String filename_2) {
        this.filename_2 = filename_2;
    }

    public String getFilename_3() {
        return filename_3;
    }

    public void setFilename_3(String filename_3) {
        this.filename_3 = filename_3;
    }


}
