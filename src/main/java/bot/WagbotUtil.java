package bot;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WagbotUtil {

    private static final String WEBHOOK_URL = "{discord webhook url}";

    public static final String FE_QR_IMAGE = "https://{feQrImage}";
    public static final String BE_QR_IMAGE = "https://{beQrImage}";
    public final String[] emojis = {
            "😃", "😁", "😎", "😍", "🥰",
            "🤗", "🤩", "😝", "😇", "🥳" };

    public String ampmString = "";


    String generateContentMessage(String str) {
        return "{\"content\":\"" + str + "\"}";
    }

    String post(String jsonbody) {

        String result = null;
        try {
            URL url = new URL(WEBHOOK_URL);

            HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
            https.setDefaultUseCaches(false);
            https.setDoInput(true);
            https.setDoOutput(true);
            https.setRequestMethod("POST");

            https.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(https.getOutputStream(), StandardCharsets.UTF_8);
            PrintWriter writer = new PrintWriter(outputStreamWriter);
            writer.write(jsonbody);
            writer.flush();
            writer.close();
            outputStreamWriter.close();

            InputStreamReader inputStreamReader = new InputStreamReader(https.getInputStream(), StandardCharsets.UTF_8);
//            BufferedReader reader = new BufferedReader(inputStreamReader);
//            StringBuilder builder = new StringBuilder();
//            String str;
//            while ((str = reader.readLine()) != null) {
//                builder.append(str + "\n");
//            }
//            result = builder.toString();
            inputStreamReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void alarmConditionCheck() {
        Calendar calendar = new GregorianCalendar();
        runOutWeekend(calendar);
        getAmpmString(calendar);
    }

    public String getRandomEmoji() {
        return emojis[getRandomInt()];
    }

    int getRandomInt() {
        return (int) ((Math.random() * 10000) % 10);
    }

    void sleep() throws InterruptedException {
        Thread.sleep(1000);
    }

    void runOutWeekend(Calendar calendar) {
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1 || day == 7) { // SUNDAY || SATURDAY
            System.exit(0);
        }
    }

    String getAmpmString(Calendar calendar) {
        int ampm = calendar.get(Calendar.AM_PM);
        if (ampm == 0) { // AM
            ampmString = " 잠시 후 9시는 TeamWAGMI의 출근 시간입니다! 미리 QR 인증을 준비해주세요. 오늘도 화이팅!";
        }
        else { // PM
            ampmString = " 벌써 6시! TeamWAGMI의 퇴근 시간입니다! QR 인증을 준비해주세요. 오늘 하루도 수고하셨어요!";
        }
        return ampmString;
    }
}
