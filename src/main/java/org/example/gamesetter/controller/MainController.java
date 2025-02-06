package org.example.gamesetter.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.gamesetter.model.Utente;
import org.example.gamesetter.service.GiocoService;
import org.example.gamesetter.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MainController {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private GiocoService giocoService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) String msgLogin) {
        model.addAttribute("msgLogin", msgLogin);
        return "index";
    }

    @PostMapping("/login")
    public String login(RedirectAttributes redirectAttributes, Model model, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        Utente utente = utenteService.getUtenteByUsernameAndPassword(username, password);
        if (utente != null) {
            session.setAttribute("utente", utente);
            return "redirect:/profile";
        }
        else{
            redirectAttributes.addAttribute("msgLogin", "Credenziali errate!");
            return "redirect:/";
        }
    }

    @GetMapping("/register")
    public String register(Model model, @RequestParam(required = false) String msgRegister) {
        model.addAttribute("utente", new Utente());
        model.addAttribute("msgRegister", msgRegister);
        return "register";
    }

    @PostMapping("/register")
    public String newUtente(RedirectAttributes redirectAttributes, @ModelAttribute("utente") Utente utente, Model model, HttpSession session) {

        String valido = controlli(utente);
        if(!valido.equals("valido")){
            redirectAttributes.addAttribute("msgRegister", valido);
            return "redirect:/register";
        }

        utenteService.addUtente(utente);
        model.addAttribute("utente", utente);
        session.setAttribute("utente", utente);
        return "redirect:/profile";
    }

    public String controlli(Utente utente){
        if(utenteService.findByUsername(utente.getUsername()) != null){
            return "Username già in uso!";
        }
        if(utenteService.findByEmail(utente.getEmail()) != null){
            return "Email già in uso!";
        }
        String email = utente.getEmail();
        if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            return "Email non valida!";
        }
        String password = utente.getPassword();
        if(password.length() < 8){
            return "La password deve essere lunga 8 caratteri!";
        }
        if(!password.matches(".*[A-Z].*")){
            return "La password deve contenere una lettera maiuscola!";
        }
        if(!password.matches(".*[0-9].*")){
            return "La password deve contenere un numero!";
        }
        if(!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")){
            return "La password deve contenere un carattere speciale!";
        }
        return "valido";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(RedirectAttributes redirectAttributes, HttpSession session, Model model) {
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente == null) {
            redirectAttributes.addAttribute("msgLogin", "Devi eseguire il login!");
            return "redirect:/";
        }
        return "profile";
    }
    @GetMapping("/userdata")
    public String userData(RedirectAttributes redirectAttributes, HttpSession session, Model model){
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente == null) {
            redirectAttributes.addAttribute("msgLogin", "Devi eseguire il login!");
            return "redirect:/";
        }
        return "userdata";
    }


}
