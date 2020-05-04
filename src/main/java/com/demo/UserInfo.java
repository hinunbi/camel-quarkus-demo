package com.demo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@Data
@NoArgsConstructor
@CsvRecord(separator = ",")
public class UserInfo {

  @DataField(pos = 1)
  String firstName;
  @DataField(pos = 2)
  String lastName;
  @DataField(pos = 3)
  String inputDate;
  @DataField(pos = 4)
  Integer readCount;
}
