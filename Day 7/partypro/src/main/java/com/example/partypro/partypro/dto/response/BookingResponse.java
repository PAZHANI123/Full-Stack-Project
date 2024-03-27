package com.example.partypro.partypro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
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
