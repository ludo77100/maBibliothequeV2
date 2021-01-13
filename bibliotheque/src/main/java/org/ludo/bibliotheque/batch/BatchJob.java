package org.ludo.bibliotheque.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ludo.bibliotheque.BibliothequeApplication;
import org.ludo.bibliotheque.service.EmailService;
import org.ludo.bibliotheque.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchJob {

    private static final Logger logger = LogManager.getLogger(BibliothequeApplication.class);

    @Autowired
    EmailService emailService ;

    @Autowired
    ReservationService reservationService ;

    //@Scheduled(cron = "0 */1 * * * *")
    public void lendingRevival() throws Exception {
        emailService.envoyerEmailRelance();
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void verifReservationsAttentes() throws Exception {
        reservationService.verificationReservationAttente();
    }



}
