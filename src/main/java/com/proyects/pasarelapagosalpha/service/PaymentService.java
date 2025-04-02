package com.proyects.pasarelapagosalpha.service;

import com.proyects.pasarelapagosalpha.model.Payment;
import com.proyects.pasarelapagosalpha.model.request.QrRequest;
import com.proyects.pasarelapagosalpha.model.request.VpayRequest;
import com.proyects.pasarelapagosalpha.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private VpayService vpayService;

    public String createPayment(QrRequest qrRequest) {

        String qrFilePath = vpayService.generateQrCode(qrRequest);

        Payment payment = new Payment();
        payment.setQrCodeUrl(qrFilePath);
        payment.setStatus("PENDING");
        payment.setCreatedAt(LocalDateTime.now());
        payment.setAmount(String.valueOf(qrRequest.getAmount()));
        payment.setCurrency(qrRequest.getCurrency());
        payment.setDescription("NUEVA DESCRIPCION");
        paymentRepository.save(payment);
        return qrFilePath;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment updatePaymentStatus(Long id, String status) {
        return paymentRepository.findById(id).map(payment -> {
            payment.setStatus(status);
            return paymentRepository.save(payment);
        }).orElse(null);
    }
}
