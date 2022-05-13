package evrentan.qrcodegenerator.qrcodegeneratorbackend.controller;

import evrentan.qrcodegenerator.qrcodegeneratorbackend.dto.GenerateQrCodeRequest;
import evrentan.qrcodegenerator.qrcodegeneratorbackend.service.QrCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.net.URI;

/**
 * REST Controller for QR Code related API end-points.
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/qrCode", consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "QR Code Related APIs")
public class QrCodeController {

  private final QrCodeService qrCodeService;

  public QrCodeController(QrCodeService qrCodeService) {
    this.qrCodeService = qrCodeService;
  }

  /**
   * REST end-point in order to generate QR code.
   * Details related to API specs can be found in the API Documentation which can be reached as described in README file.
   *
   * @param qrCodeGenerateRequest object that is used to generate QR Code. Please, see the {@link GenerateQrCodeRequest} class for details.
   * @return String within ResponseEntity which corresponds to generated QR Code in base64 String.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @PostMapping(value = "/generate/qrCodeString", produces = MediaType.IMAGE_PNG_VALUE)
  @Operation(summary = "Generate QR Code", description = "Generate QR Code")
  @ApiResponses(value = {
      @ApiResponse(responseCode  = "201", description  = "Successfully Generate QR Code"),
      @ApiResponse(responseCode  = "400", description  = "Bad Request"),
      @ApiResponse(responseCode  = "404", description  = "Not Found"),
      @ApiResponse(responseCode  = "500", description  = "Internal Server Error")
  })
  public ResponseEntity<String> generateQrCodeString(@RequestBody @NotNull GenerateQrCodeRequest qrCodeGenerateRequest) {
    return ResponseEntity.created(URI.create(qrCodeGenerateRequest.getBarcodeText()))
        .body(qrCodeService.generateQrCodeString(qrCodeGenerateRequest));
  }

  /**
   * REST end-point in order to generate QR code.
   * Details related to API specs can be found in the API Documentation which can be reached as described in README file.
   *
   * @param qrCodeGenerateRequest object that is used to generate QR Code. Please, see the {@link GenerateQrCodeRequest} class for details.
   * @return byte[] within ResponseEntity which corresponds to generated QR Code.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @PostMapping(value = "/generate/qrCodeByteArray", produces = MediaType.IMAGE_PNG_VALUE)
  @Operation(summary = "Generate QR Code", description = "Generate QR Code")
  @ApiResponses(value = {
      @ApiResponse(responseCode  = "201", description  = "Successfully Generate QR Code"),
      @ApiResponse(responseCode  = "400", description  = "Bad Request"),
      @ApiResponse(responseCode  = "404", description  = "Not Found"),
      @ApiResponse(responseCode  = "500", description  = "Internal Server Error")
  })
  public ResponseEntity<byte[]> generateQrCodeByteArray(@RequestBody @NotNull GenerateQrCodeRequest qrCodeGenerateRequest) {
    return ResponseEntity.created(URI.create(qrCodeGenerateRequest.getBarcodeText()))
        .body(qrCodeService.generateQrCodeByteArray(qrCodeGenerateRequest));
  }
}
