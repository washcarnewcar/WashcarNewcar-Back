package me.washcar.wcnc.form;

import lombok.Data;

import java.util.Collection;

@Data
public class NewStoreCreationForm {

  private String name;
  private String tel;
  private CoordinateForm coordinate;
  private String address;
  private String address_detail;
  private String slug;
  private String wayto;
  private String description;
  private String preview_image;
  private Collection<String> store_image;
}