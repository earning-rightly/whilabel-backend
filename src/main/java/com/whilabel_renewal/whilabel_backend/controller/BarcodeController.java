package com.whilabel_renewal.whilabel_backend.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.whilabel_renewal.whilabel_backend.dto.BarcodeScanResultDTO;
import com.whilabel_renewal.whilabel_backend.dto.BaseDTO;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/barcode/")
public class BarcodeController {

  // grayscale -> 침식 -> 팽창 -> 명암대비 (시간적 여유 확인 필요)
  // 명암대비 -> grayScale
  @PostMapping("/scan")
  public ResponseEntity<BaseDTO<BarcodeScanResultDTO>> scan(
      @RequestParam("file") MultipartFile file) {
    try {
      BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

      BufferedImage enhancedImage = enhanceContrast(bufferedImage);

      LuminanceSource source = new BufferedImageLuminanceSource(enhancedImage);
      BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

      Map<DecodeHintType, Object> hints = new HashMap<>();
      hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

      List<BarcodeFormat> possibleFormats =
          Arrays.asList(
              BarcodeFormat.QR_CODE,
              BarcodeFormat.CODE_39,
              BarcodeFormat.CODE_128,
              BarcodeFormat.EAN_13,
              BarcodeFormat.UPC_A);
      hints.put(DecodeHintType.POSSIBLE_FORMATS, possibleFormats);

      Result result = new MultiFormatReader().decode(bitmap, hints);

      BaseDTO<BarcodeScanResultDTO> response =
          BaseDTO.<BarcodeScanResultDTO>builder()
              .data(new BarcodeScanResultDTO(result.getText()))
              .build();
      return new ResponseEntity<>(response, HttpStatus.OK);

    } catch (IOException | NotFoundException e) {
      e.printStackTrace();
    }

    BaseDTO<BarcodeScanResultDTO> emptyResponse =
        BaseDTO.<BarcodeScanResultDTO>builder()
            .data(null)
            .code(1001)
            .message("no barcode found")
            .build();

    return new ResponseEntity<>(emptyResponse, HttpStatus.OK);
  }

  private BufferedImage enhanceContrast(BufferedImage original) {
    RescaleOp rescaleOp = new RescaleOp(1.5f, 15, null);
    rescaleOp.filter(original, original);
    return original;
  }
}
