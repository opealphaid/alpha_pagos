package com.proyects.pasarelapagosalpha.controller;

import com.proyects.pasarelapagosalpha.model.Payment;
import com.proyects.pasarelapagosalpha.model.request.QrRequest;
import com.proyects.pasarelapagosalpha.model.response.QrResponse;
import com.proyects.pasarelapagosalpha.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

//    @PostMapping
//    public Payment createPayment(@RequestBody QrRequest qrRequest) {
//        return paymentService.createPayment(qrRequest);
//    }

//    @PostMapping
//    public ResponseEntity<byte[]> createPayment(@RequestBody QrRequest qrRequest) {
//        String imagePath = paymentService.createPayment(qrRequest);
//
//        try {
//            // Leer el archivo de imagen desde el sistema de archivos
//            File imageFile = new File(imagePath);
//            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
//
//            // Configurar los encabezados HTTP
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_JPEG);
//
//            // Retornar la imagen como respuesta
//            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error al procesar la imagen: " + e.getMessage());
//        }
//    }

    @PostMapping
    public ResponseEntity<QrResponse> createPayment(@RequestBody QrRequest qrRequest) {
        QrResponse qrResponse = paymentService.createPayment(qrRequest);

        return ResponseEntity.ok(qrResponse);
    }


    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Optional<Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @PutMapping("/{id}/status")
    public Payment updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
        return paymentService.updatePaymentStatus(id, status);
    }
}


