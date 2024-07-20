package com.whilabel_renewal.whilabel_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BarcodeScanResultDTO {

  private String barcode;

  public BarcodeScanResultDTO(String barcode) {
    this.barcode = barcode;
  }
}
