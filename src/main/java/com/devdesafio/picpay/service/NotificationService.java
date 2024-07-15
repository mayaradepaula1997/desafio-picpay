package com.devdesafio.picpay.service;


import com.devdesafio.picpay.client.NotificationClient;
import com.devdesafio.picpay.entites.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;




@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private NotificationClient notificationClient;


    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transfer transfer){

         try{                                               //try/cath: porque o seviço de notificação pode ser instavel
                                                            // se por acaso algum erro acontecer, não vai impedir a transferiencia
             logger.info("Sending notification...");

             var resp = notificationClient.sendNotification(transfer);

             if (resp.getStatusCode().isError()) {
                 logger.error("Error while sending notification, status code is not OK");
             }

         }catch (Exception e){
             logger.error("Error while sending notification", e);
         }
    }
}
