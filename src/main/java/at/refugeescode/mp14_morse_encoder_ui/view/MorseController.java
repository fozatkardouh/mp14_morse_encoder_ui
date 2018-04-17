package at.refugeescode.mp14_morse_encoder_ui.view;

import at.refugeescode.mp14_morse_encoder_ui.model.Form;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MorseController {

    private final RestTemplate restTemplate;
    private String morse;

    @Value("${encoder.url}")
    private String url;

    @ModelAttribute("morse")
    public String getMorse() {
        return morse;
    }

    @ModelAttribute("form")
    public Form form() {
        return new Form();
    }

    @GetMapping
    public String homePage() {
        return "home";
    }

    @PostMapping
    public String encode(Form form, Model model) {
        morse = restTemplate.postForObject(url, form, String.class);
        model.addAttribute("morse", morse);
        return "redirect:/";
    }

}
