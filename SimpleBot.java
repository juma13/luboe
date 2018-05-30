import java.util.*;
import java.util.regex.*;

public class SimpleBot {
    final String[] MANS_PHRASES = {
            "Это мне непонятно, простите.",
            "Я не могу ответить вам на это.",
            "Давайте не будем об этом говорить."};
    final String[] ELUSIVE_ANSWERS = {
            "Вопрос непростой я должен подумать.",
            "Не уверен, что располагаю такой информацией.",
            "Может лучше поговорим о чём-то другом?",
            "Не уверен, что Вам понравится ответ.",
            "Поверьте, я сам хотел бы это знать.",
            "Вы действительно хотите это знать?",
            "Зачем Вам такая информация?",
            "Бла бла бла бла бла бла бла"};
    final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {{
        put("ку","ku");
        put("хай", "hello");
        put("привет", "hello");
        put("здорово", "hello");
        put("здравствуй", "hello");
        put("кто\\s.*ты", "who");
        put("ты\\s.*кто", "who");
        put("как\\s.*зовут", "name");
        put("как\\s.*имя", "name");
        put("есть\\s.*имя", "name");
        put("какое\\s.*имя", "name");
        put("как\\s.*дела", "howareyou");
        put("как\\s.*жизнь", "howareyou");
        put("зачем\\s.*тут", "whatdoyoudoing");
        put("зачем\\s.*здесь", "whatdoyoudoing");
        put("что\\s.*делаешь", "whatdoyoudoing");
        put("чем\\s.*занимаешься", "whatdoyoudoingq");
        put("что\\s.*нравится", "whatdoyoulike");
        put("что\\s.*любишь", "whatdoyoulike");
        put("^да", "yes");
        put("согласен", "yes");
        put("который\\s.*час", "whattime");
        put("сколько\\s.*время", "whattime");
        put("прощай", "bye");
        put("увидимся", "bye");
        put("до\\s.*свидания", "bye");
        put("что\\s.*делаешь?","whatdoyoudo");
        put("чем\\s.*занят?","whatdoyoudo");
        put("откуда\\s.*будешь?","whereu");
        put("откуда\\s.*родом?","whereu");
        put("где\\s.*живешь?","wherelive");
        put("кто\\s.*по\\s.*жизни?","whojizn");
        put("беха\\s.*или\\s.*мерс?","mashina");
        put("в\\s.*какой\\s.*группировке\\s.*ты\\s.*состоишь?","grupa");
        put("какой\\s.*фильм\\s.*тебе\\s.*нравится?","film");
        put("почему","whai");

    }};
    final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {{
        put("ku","Ку");
        put("hello", "Здравствуйте, рад Вас видеть.");
        put("who", "Я обычный чат-бот.");
        put("name", "Мое полное имя Усама Бэн Ладен.");
        put("howareyou", "Спасибо, что интересуетесь. У меня всё хорошо.");
        put("whatdoyoudoingq", "В данный момент я взламваю Пентагон.");
        put("whatdoyoulike", "Мне нравиться думать что я не просто программа.");
        put("yes", "Хорошо...");
        put("bye", "До свидания. Надеюсь, ещё увидимся.");
        put("whatdoyoudo","Да так ничего особеного.");
        put("whereu","Я родом из Афганистана.");
        put("wherelive","В данный момент я живу в этом ноутбуке.");
        put("whojizn","Я сын своего отца.");
        put("mashina","Ну канешн Мерс.");
        put("grupa","Я лишь состою в Аль-Каиде.");
        put("film","Мне нравится фильм 'Не шутите с Зоханом'");
        put("whai","Потому-что!");
    }};
    Pattern pattern;
    Random random;
    Date date;

    public SimpleBot() {
        random = new Random();
        date = new Date();
    }

    public String sayInReturn(String msg, boolean ai) {
        String say = (msg.trim().endsWith("?"))?
                ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:
                MANS_PHRASES[random.nextInt(MANS_PHRASES.length)];
        if (ai) {
            String message =
                    String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
            for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
                pattern = Pattern.compile(o.getKey());
                if (pattern.matcher(message).find())
                    if (o.getValue().equals("whattime")) return date.toString();
                    else return ANSWERS_BY_PATTERNS.get(o.getValue());
            }
        }
        return say;
    }
}