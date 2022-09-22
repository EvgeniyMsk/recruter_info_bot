package ou.info.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ou.info.entity.Person;
import ou.info.repository.PersonRepository;

@Component
public class RecruiterInfoBot extends TelegramLongPollingBot {
    private PersonRepository personRepository;

    @Autowired
    public RecruiterInfoBot(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public String getBotToken() {
        return "5490861064:AAGQurFLBhoOPo5B5QnklieFYMTuU5RDgMs";
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            SendMessage message = new SendMessage();
            String text = update.getMessage().getText();
            System.out.println(text);
            String []data = text.split(" ");
            if (data.length != 4)
            {
                message.setText("Неверный формат (Иванов Иван Иванович 1.1.1993) - без ведущих нулей");
                message.setChatId(Long.toString(update.getMessage().getChatId()));
                execute(message);
            } else
            {
                String name = data[0] + " " + data[1] + " " + data[2];
                name = name.toUpperCase();
                String birth = data[3];
                Person person = personRepository.findByNameAndBirth(name, birth);
                if (person != null)
                {
                    message.setText("Поздравляем! Вы идете служить!" + person.getName() + " " + person.getBirth() + " "
                            + person.getAddress() + " " + person.getPassportNumber() + " " + person.getPassport());
                } else
                    message.setText("Вам не повезло! Сидите дома.");
                message.setChatId(Long.toString(update.getMessage().getChatId()));
                execute(message);
            }

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "recruiter_info_bot";
    }
}
