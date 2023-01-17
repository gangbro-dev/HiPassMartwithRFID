package e101.hishop.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/iot")
public class IotController {

    @PostMapping("/rfid")
    public String rfidRead() {
        return "rfid";
    }

    @PostMapping("/barcode")
    public String barcodeRead() {
        return "barcode";
    }

    @PostMapping("/qr")
    public String qrCreate() {
        return "kioskQR";
    }

    @PostMapping("/init")
    public String init() {
        return "init";
    }
}
