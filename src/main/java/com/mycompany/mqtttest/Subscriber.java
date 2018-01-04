/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqtttest;


import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author almis
 */

public class Subscriber implements MqttCallback {
    private final int qos = 1;
    private String topic = "sensor/data";
    private MqttClient client;
    MyServlet ms;
    

    public Subscriber(String uri, MyServlet aThis) throws MqttException, URISyntaxException {
        this(new URI(uri));
        this.ms = aThis;
    }
    
    public Subscriber(URI uri) throws MqttException {
        String MQTTHOST = "tcp://m23.cloudmqtt.com:15660";
        String USERNAME = "uzjzhqyg";
        String PASSWORD = "1TAG9wh0TTpa";
        //String MQTTHOST = "tcp://m14.cloudmqtt.com:12415";
        //String USERNAME = "jsjakozq";
        //String PASSWORD = "MJdmSC0hB9jy";
        String clientId = "MQTT-Java-Example";
        if (!uri.getPath().isEmpty()) {
            this.topic = uri.getPath().substring(1);
        }

        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);
        conOpt.setUserName(USERNAME);
        conOpt.setPassword(PASSWORD.toCharArray());

        this.client = new MqttClient(MQTTHOST, clientId, new MemoryPersistence());
        this.client.setCallback(this);
        this.client.connect(conOpt);

        this.client.subscribe(this.topic, qos);
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost because: " + cause);
        System.exit(1);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
    }
  
    @Override
    public void messageArrived(String topic, MqttMessage message) throws MqttException {
        String string = new String(message.getPayload());
        String string2 = string.split("/")[3];
        messageDB(topic,message);
        
        if (string2.equals ("2")){
            ms.setString2(string);
            ms.selectMaria("2");
            verifyMessage(string, 2);
        }
        else{
            ms.setString(string);
            ms.selectMaria("1");
            verifyMessage(string, 1);
        }
        System.out.println(String.format("[%s] %s", topic, string));
    }
    
    public void messageDB(String topic, MqttMessage message) throws MqttException {
        String string = new String(message.getPayload());
        String[] array = string.split("/");
        double x = Double.parseDouble(array[0]);
        double y = Double.parseDouble(array[1]);
        double z = Double.parseDouble(array[2]);
        double id = Double.parseDouble(array[3]);
        MariaDB db = new MariaDB();
        db.insertDB(x, y, z, id);
    }
    
    public void verifyMessage(String message, int number){
        String[] array = message.split("/");
        double x = Double.parseDouble(array[0]);
        double y = Double.parseDouble(array[1]);
        double z = Double.parseDouble(array[2]);
        if(y > -20 && y < 20){
            System.err.println("Alerta!!");
            ms.setAlert(true, number);
        }
        else{
            ms.setAlert(false, number);
        }
    }
        
    public static void main(String[] args) throws MqttException, URISyntaxException {
        
      
    }
}