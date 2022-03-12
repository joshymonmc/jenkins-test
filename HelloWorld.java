package hello;

import java.io.FileInputStream;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.TopicManagementResponse;
import com.google.firebase.messaging.Notification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloWorld {

  public static void sendToToken() throws FirebaseMessagingException {
    // [START send_to_token]
    // This registration token comes from the client FCM SDKs.
 
     String registrationToken = "fwas-2J6PEJShv9NXNNyIS:APA91bGeb5E0ym2S9qkxwYf2PJoW1WQckJR8i48j0ZhFXvAdctQ5WtbRlFOYNmaqSw2BUAFWOs5AIIF9hM2e_4z_MU5g7xDEcMjiHBRfYrhdxHhgLPtlpiIwGlxbL4dTwQkA9d9HqqLv";
    

    Message message = Message.builder()
        .setNotification(Notification.builder()
            .setTitle("Hello")
            .setBody("from HR app")
            .build())
        .setToken(registrationToken)
        .build();

    // Send a message to the device corresponding to the provided
    // registration token.
    String response = FirebaseMessaging.getInstance().send(message);

    // Response is a message ID string.
    System.out.println("Successfully sent message: " + response);
  }


  public static void subscribeToTopic() throws FirebaseMessagingException {
    String topic = "Notify_All";
    // [START subscribe]
    // These registration tokens come from the client FCM SDKs.
    List<String> registrationTokens = Arrays.asList(
        "eDOpsEOvQ16a_FNaeu7-Pe:APA91bFoh5XUXJmntsX4hSJTNCq5fEd4aYzJakKeUxXDfaN0Sc9ow-rra9fWjVzRz8K8uEQQVzSgTMrpCXyTd3O9P63i7s_5Ydzak8PTFV7_M5V81nsm6Jg_-yLTdk8sNhWHPZWyleTY");

    // Subscribe the devices corresponding to the registration tokens to the
    // topic.
    TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(
        registrationTokens, topic);
 
    StringBuilder errors = new StringBuilder();
 
    if (response != null) {
        System.out.println("Received response for new registration : " +  " Success Count : " + response.getSuccessCount());

        if (response.getErrors() != null && !response.getErrors().isEmpty()) {
            for ( TopicManagementResponse.Error error : response.getErrors()) {
              errors.append(errors.length() > 0 ? "\n" : " ");
              errors.append(error.getReason());
            }
            System.out.println("Error : " + errors.toString());
        }
    }  
    else {
      System.out.println("Didn't receive a response for registration for topic " + topic);          
    }
  }


  public static void sendToTopic() throws FirebaseMessagingException {
    // [START send_to_topic]
    // The topic name can be optionally prefixed with "/topics/".
    String topic = "Notify_All";

    // See documentation on defining a message payload.

    Message message = Message.builder()
        .setNotification(Notification.builder()
            .setTitle("Hello 1 - All notification")
            .setBody("from HR app - tesst")
            .build())
        .setTopic(topic)
        .build();

    // Send a message to the devices subscribed to the provided topic.
    String response = FirebaseMessaging.getInstance().send(message);
    // Response is a message ID string.
    System.out.println("Successfully sent message: " + response);
  }




  public static void main(String[] args) {

	try {

                // Initialize the SDK
      //FileInputStream serviceAccount = new FileInputStream("C://fcmtest/src/main/java/hello/hrapp-nonprod-firebase-adminsdk-6oxjx-c313dabbd1.json");
    
      FileInputStream serviceAccount = new FileInputStream("C://fcmtest/src/main/java/hello/hrapp-test-b9b11-921928cf4ffc.json");
  		FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
		  FirebaseApp fa = FirebaseApp.initializeApp(options);

                /* Need to check these */
                //subscribeToTopic();
                // sendToToken();
                sendToTopic();

	}
	catch (Exception e) {
               e.printStackTrace(System.out);
    	}

  }
}
