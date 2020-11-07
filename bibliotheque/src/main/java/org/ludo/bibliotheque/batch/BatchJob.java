package org.ludo.bibliotheque.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ludo.bibliotheque.BibliothequeApplication;
import org.ludo.bibliotheque.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchJob {

    private static final Logger logger = LogManager.getLogger(BibliothequeApplication.class);

    @Autowired
    EmailService emailService ;

    @Scheduled(cron = "0 0 14 * * *")
    public void lendingRevival() throws Exception {
        logger.debug("Execution du batch");

        emailService.envoyerEmailRelance();

    }



}
