package com.exemplu.controller;

import com.exemplu.entity.Carte;
import org.springframework.ui.Model;
import com.exemplu.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CarteWebController {

    @Autowired
    private CarteRepository repository;

    @GetMapping("/lista-carti")
    public String getListaCarti(Model model) {
        String s = "Lista cărților preluate prin repository";
        model.addAttribute("str", s);
        model.addAttribute("listaCarti", repository.findAll());
        return "carti";
    }

    @PostMapping("/carti/operatii")
    public String operatiiCarti(
            @RequestParam(required = false) String adauga,
            @RequestParam(required = false) String modifica,
            @RequestParam(required = false) String sterge,
            @RequestParam(required = false) String filtru,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String titlul,
            @RequestParam(required = false) String autorul,
            Model model) {

        String mesaj = "";

        // Logica pentru butonul "Adauga"
        if (adauga != null) {
            if (titlul != null && autorul != null) {
                if (!repository.existsById(Integer.parseInt(isbn))) {
                    Carte carteNoua = new Carte(titlul, autorul);
                    repository.save(carteNoua);
                    mesaj = "Cartea a fost adăugată cu succes!";
                } else {
                    mesaj = "Eroare: Cartea cu acest ISBN există deja!";
                }
            }
            else {
                mesaj = "Toate câmpurile trebuie completate pentru a adăuga o carte!";
            }
        }

        // Logica pentru butonul "Modifica"
        else if (modifica != null) {
            if (isbn != null && !isbn.isEmpty()) {
                Optional<Carte> carteExistenta = repository.findById(Integer.parseInt(isbn));
                if (carteExistenta.isPresent()) {
                    Carte carte = carteExistenta.get();
                    carte.setTitlul(titlul);
                    carte.setAutorul(autorul);
                    repository.save(carte);
                    mesaj = "Cartea cu ISBN " + isbn + " a fost modificată!";
                } else {
                    mesaj = "Cartea cu ISBN " + isbn + " nu există!";
                }
            } else {
                mesaj = "Introduceți un ISBN valid pentru modificare!";
            }
        }

        // Logica pentru butonul "Sterge"
        else if (sterge != null) {
            if (isbn != null && !isbn.isEmpty()) {
                repository.deleteById(Integer.parseInt(isbn));
                mesaj = "Cartea cu ISBN " + isbn + " a fost ștearsă!";
            } else {
                mesaj = "Introduceți un ISBN valid pentru ștergere!";
            }
        }

        // Logica pentru butonul "Filtreaza dupa autor"
        else if (filtru != null) {
            if (autorul != null && !autorul.isEmpty()) {
                List<Carte> cartiFiltrate = (List<Carte>) repository.findByAutorul(autorul);
                mesaj = "Cărțile filtrate după autorul " + autorul;
                model.addAttribute("listaCarti", cartiFiltrate);
            } else {
                mesaj = "Introduceți un nume de autor pentru filtrare!";
            }
        }

        // Încarcă datele actualizate și mesajele
        if (filtru == null) { // Pentru filtrare, lista este deja actualizată
            model.addAttribute("listaCarti", repository.findAll());
        }
        model.addAttribute("str", mesaj);

        return "carti";
    }
}


