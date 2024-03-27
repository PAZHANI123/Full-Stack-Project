package com.example.partypro.partypro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "_Booking")

public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bid;
  private String bName;
  private String contact;
  private String dateOfParty;
  private String location;
  private String time;
  private String accomodation;
  private String cuisine;
  private String theme;
  private boolean phographer;
  private boolean djconcert;
  private String customizedService;

}
