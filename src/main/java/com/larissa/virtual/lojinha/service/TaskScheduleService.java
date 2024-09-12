package com.larissa.virtual.lojinha.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class TaskScheduleService {

    @Autowired
    private SendEmailService sendEmailService;

   // @Scheduled(initialDelay = 2000, fixedDelay = 86400000) // roda a cada 24h
   // @Scheduled(cron = "0 0 11 * * *", zone = "America/Sao_Paulo") // roda as 11h todo dia
    public void testSchedule() throws MessagingException, UnsupportedEncodingException, InterruptedException {
        StringBuilder msg = new StringBuilder();
        msg.append("Olá, testando schedule.");

        sendEmailService.sendEmailHtml("Teste Schedule", msg.toString(), "lari.f4ria@gmail.com" );
        Thread.sleep(3000); // caso for enviar vários emails libera memória, se não consome muito
    }
}
