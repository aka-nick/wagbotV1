package bot;

public class WagbotRunner {

    public static void main(String[] args) throws InterruptedException {

        WagbotUtil botUtil = new WagbotUtil();

        botUtil.alarmConditionCheck();

        botUtil.post(botUtil.generateContentMessage(
                "안녕하세요! " + botUtil.getRandomEmoji() + botUtil.ampmString));
        botUtil.sleep();

        botUtil.post(botUtil.generateContentMessage(">>> FE는 아래의 이미지로 체크해주세요!" + botUtil.getRandomEmoji()));
        botUtil.post(botUtil.generateContentMessage(botUtil.FE_QR_IMAGE));
        botUtil.sleep();

        botUtil.post(botUtil.generateContentMessage("그리고..."));
        botUtil.post(botUtil.generateContentMessage(">>> BE는 아래의 이미지로 체크해주세요!" + botUtil.getRandomEmoji()));
        botUtil.post(botUtil.generateContentMessage(botUtil.BE_QR_IMAGE));
    }


}
