package com.proyects.pasarelapagosalpha.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyects.pasarelapagosalpha.config.VpayConfig;
import com.proyects.pasarelapagosalpha.model.request.*;
import com.proyects.pasarelapagosalpha.model.response.QrVpayServiceResponse;
import com.proyects.pasarelapagosalpha.model.response.VpayResponse;
import com.proyects.pasarelapagosalpha.model.response.VpayResponseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.List;

@Service
public class VpayService {

    @Autowired
    private VpayConfig vpayConfig;

    private final RestTemplate restTemplate = new RestTemplate();

    public QrVpayServiceResponse generateQrCode(QrRequest qrRequest) {
        try {

            VpayRequest vpayRequest = new VpayRequest();
            vpayRequest.setOperation(vpayConfig.getApiOperationQr());

            List<VpayAttribute> vpayAttributes = new ArrayList<>();
            vpayAttributes.add(new VpayAttribute("currency", qrRequest.getCurrency()));
            vpayAttributes.add(new VpayAttribute("gloss", qrRequest.getGloss()));
            vpayAttributes.add(new VpayAttribute("amount", Double.toString(qrRequest.getAmount())));
            vpayAttributes.add(new VpayAttribute("expirationDate", qrRequest.getExpirationDate()));
            vpayAttributes.add(new VpayAttribute("additionalData", "FACTURACION"));
            vpayAttributes.add(new VpayAttribute("destinationAccountId", vpayConfig.getApiDestinationAccountId()));
            vpayAttributes.add(new VpayAttribute("transactionIdentifier", vpayConfig.getApiTransactionIdentifier()));
            vpayAttributes.add(new VpayAttribute("user", vpayConfig.getApiUser()));
            vpayAttributes.add(new VpayAttribute("company", vpayConfig.getApiCompany()));
            vpayAttributes.add(new VpayAttribute("originCollection", "PWEB/APPM"));
            vpayAttributes.add(new VpayAttribute("callback", "http://.../callback/vpay"));

            vpayRequest.setHeader(vpayAttributes);

            //se puede poner la lista de productos si es necesaria o solo el servicio el cual va a consumir
            List<VpayItem> vpayItems = new ArrayList<>();
            vpayItems.add(new VpayItem("fgdgdfgd", "45"));

            VpayDetail vpayDetail = new VpayDetail();
            vpayDetail.setItems(vpayItems);

            List<VpayDetail> vpayDetails = new ArrayList<>();
            vpayDetails.add(vpayDetail);

            vpayRequest.setDetail(vpayDetails);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", vpayConfig.getApiToken());
            //headers.set("API-SECRET", vpayConfig.getApiSecret());

            System.out.println(vpayRequest);

            System.out.println("URL: " + vpayConfig.getApiUrl());
            System.out.println("Headers: " + headers);
            System.out.println("Request Body: " + new ObjectMapper().writeValueAsString(vpayRequest));


            HttpEntity<VpayRequest> requestEntity = new HttpEntity<>(vpayRequest, headers);

            ResponseEntity response = restTemplate.exchange(
                    vpayConfig.getApiUrl(),
                    HttpMethod.PUT,
                    requestEntity,
                    String.class
            );

            ObjectMapper objectMapper = new ObjectMapper();

            VpayResponse vpayResponse = objectMapper.readValue((String) response.getBody(), VpayResponse.class);

            List<VpayResponseItem> responseItems = vpayResponse.getResponseList().get(0).getResponse();

            String qrBase64 = null;
            String qrId = null;
            for (VpayResponseItem item : responseItems) {
                if ("QR".equals(item.getCode())) {
                    qrBase64 = item.getIdentificator();
                }
                if ("idQr".equals(item.getCode())) {
                    qrId = item.getIdentificator();
                }
            }

            System.out.println(qrId);

            if (qrBase64 != null) {
                String directoryPath = "C:\\Users\\XCruz\\Desktop\\PAGOS";

                File directory = new File(directoryPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String fileName = "qr_" + qrId + ".jpg";
                String fullPath = directoryPath + "/" + fileName;

                saveBase64AsImage(qrBase64, fullPath);

                String logoPath = "C:\\Users\\XCruz\\Desktop\\ALPHA\\FACTURACION\\DEMO_FACTURACION\\COMPUTARIZADA\\api-factu\\target\\Logo.png";

                String outputPath = directoryPath + File.separator + "qr_with_logo_" + qrId + ".jpg";
                addLogoToQr(fullPath, logoPath, outputPath);

                //return qrBase64;
                QrVpayServiceResponse qrVpayServiceResponse = new QrVpayServiceResponse();
                qrVpayServiceResponse.setIdQr(qrId);
                qrVpayServiceResponse.setBase64Image(outputPath);
                return qrVpayServiceResponse;
            } else {
                throw new RuntimeException("No se encontró el QR en la respuesta");
            }



        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con VPay: " + e.getMessage());
        }
    }

    private void saveBase64AsImage(String base64, String fullPath) {
        try (OutputStream stream = new FileOutputStream(fullPath)) {
            byte[] imageBytes = Base64.getDecoder().decode(base64);
            stream.write(imageBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la imagen QR: " + e.getMessage());
        }
    }

//    private void addLogoToQr(String qrPath, String logoPath, String outputPath) {
//        try {
//            BufferedImage qrImage = ImageIO.read(new File(qrPath));
//
//            BufferedImage logoImage = ImageIO.read(new File(logoPath));
//
//            int canvasWidth = 600;
//            int canvasHeight = 800;
//
//            BufferedImage canvas = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
//
//            Graphics2D graphics = canvas.createGraphics();
//
//            graphics.setColor(new Color(0, 150, 0));
//            graphics.fillRect(0, 0, canvasWidth, canvasHeight);
//
//            int logoWidth = 390;
//            int logoHeight = 300;
//            int logoX = (canvasWidth - logoWidth) / 2;
//            int logoY = canvasHeight - logoHeight - 50;
//            graphics.drawImage(logoImage, logoX, logoY, logoWidth, logoHeight, null);
//
//            graphics.setColor(Color.WHITE);
//            graphics.setFont(new Font("Arial", Font.BOLD, 40));
//            String title = "¡Apunta, escanea y paga!";
//            int titleWidth = graphics.getFontMetrics().stringWidth(title);
//            graphics.drawString(title, (canvasWidth - titleWidth) / 2, 80);
//
//            int qrSize = 300;
//            int qrX = (canvasWidth - qrSize) / 2;
//            int qrY = 150;
//            graphics.drawImage(qrImage, qrX, qrY, qrSize, qrSize, null);
//
//            graphics.setFont(new Font("Arial", Font.PLAIN, 24));
//            String subtitle = "Este comercio acepta pagos con QR";
//            int subtitleWidth = graphics.getFontMetrics().stringWidth(subtitle);
//            graphics.drawString(subtitle, (canvasWidth - subtitleWidth) / 2, qrY + qrSize + 40);
//
//            graphics.dispose();
//
//            ImageIO.write(canvas, "png", new File(outputPath));
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error al crear el diseño del QR: " + e.getMessage());
//        }
//    }


    private void addLogoToQr(String qrPath, String logoPath, String outputPath) {
        try {
            // Cargar la imagen QR
            BufferedImage qrImage = ImageIO.read(new File(qrPath));

            // Cargar el logo
            BufferedImage logoImage = ImageIO.read(new File(logoPath));

            // Dimensiones finales de la imagen
            int canvasWidth = 600;  // Ancho total
            int canvasHeight = 650; // Altura total

            // Crear un canvas (imagen en blanco) para el diseño
            BufferedImage canvas = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);

            // Obtener el contexto gráfico
            Graphics2D graphics = canvas.createGraphics();

            // Dibujar el fondo (color verde)
            graphics.setColor(new Color(0, 150, 0)); // Verde
            graphics.fillRect(0, 0, canvasWidth, canvasHeight);

            // Configurar el texto superior
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 40));
            String title = "¡Apunta, escanea y paga!";
            int titleWidth = graphics.getFontMetrics().stringWidth(title);
            //graphics.drawString(title, (canvasWidth - titleWidth) / 2, 80);

            int logoWidth = 390;
            int logoHeight = 220;
            int logoX = (canvasWidth - logoWidth) / 2;
            int logoY = 0;
            graphics.drawImage(logoImage, logoX, logoY, logoWidth, logoHeight, null);
            graphics.drawString(title, (canvasWidth - titleWidth) / 2, 220);

            int qrSize = 300;
            int qrX = (canvasWidth - qrSize) / 2;
            int qrY = logoY + logoHeight + 30;
            graphics.drawImage(qrImage, qrX, qrY, qrSize, qrSize, null);

            graphics.setFont(new Font("Arial", Font.PLAIN, 24));
            String subtitle = "Este comercio acepta pagos con QR";
            int subtitleWidth = graphics.getFontMetrics().stringWidth(subtitle);
            graphics.drawString(subtitle, (canvasWidth - subtitleWidth) / 2, qrY + qrSize + 40);

            graphics.dispose();

            ImageIO.write(canvas, "png", new File(outputPath));

        } catch (Exception e) {
            throw new RuntimeException("Error al crear el diseño del QR: " + e.getMessage());
        }
    }




}