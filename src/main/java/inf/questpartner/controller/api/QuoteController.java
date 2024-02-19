package inf.questpartner.controller.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class QuoteController {

    @Getter
    public enum Quote {
        QUOTE_1("행복은 습관이다. 그것을 몸에 지니라. -허버드-"),
        QUOTE_2("고개 숙이지 마십시오. 세상을 똑바로 정면으로 바라보십시오. -헬렌 켈러-"),
        QUOTE_3("고난의 시기에 동요하지 않는 것, 이것은 진정 칭찬 받을 만한 뛰어난 인물의 증거다. -베토벤-"),
        QUOTE_4("당신이 할 수 있다고 믿든 할 수 없다고 믿는 믿는 대로 될 것이다. -헨리포드-"),
        QUOTE_5("작은 기회로부터 종종 위대한 업적이 시작된다. -데모스테네스-");

        private final String quote;

        Quote(String quote) {
            this.quote = quote;
        }
    }

    @GetMapping("/randomQuote")
    public String randomQuote(Model model) {
        int randInt = new Random().nextInt(Quote.values().length);
        model.addAttribute("randomQuote", Quote.values()[randInt].getQuote());
        return "randomQuote";
    }
}
