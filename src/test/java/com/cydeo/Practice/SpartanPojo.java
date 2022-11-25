package com.cydeo.Practice;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpartanPojo {

   private int id;
   private String name;
   private String gender;
   private long phone;

   public SpartanPojo(int id, String name, String gender, long phone) {
      this.id = id;
      this.name = name;
      this.gender = gender;
      this.phone = phone;
   }
}
