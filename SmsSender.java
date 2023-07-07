import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
    // Twilio account credentials
    private static final String ACCOUNT_SID = "your_account_sid";
    private static final String AUTH_TOKEN = "your_auth_token";

    public static void main(String[] args) {
        // Initialize the Twilio client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Sender and recipient phone numbers
        String senderNumber = "+1234567890"; // Your Twilio phone number
        String recipientNumber = "+9876543210"; // Destination phone number

        // Message content
        String message = "Hello, this is a test message.";

        // Send the message
        Message sms = Message.creator(new PhoneNumber(recipientNumber), new PhoneNumber(senderNumber), message).create();

        // Check if the message was sent successfully
        if (sms.getSid() != null) {
            System.out.println("SMS sent successfully. SID: " + sms.getSid());
        } else {
            System.out.println("Failed to send SMS.");
        }
    }
}
