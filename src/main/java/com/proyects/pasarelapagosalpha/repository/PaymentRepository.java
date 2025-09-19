package com.proyects.pasarelapagosalpha.repository;

import com.proyects.pasarelapagosalpha.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByIdQr(String idQr);

}
