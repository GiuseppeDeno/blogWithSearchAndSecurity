package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class BlogController {

    private BlogService b1;

    @Autowired
    public BlogController(BlogService b1) {
        this.b1 = b1;
    }

    // IMPORTANTE  https://stackoverflow.com/questions/64715520/trying-to-make-a-request-with-or-without-parameters
    // in  requestParam bisogna mettere    value = "categoria required = false   altrimenti richiederebbe la categoria
    // nella index prima di partire  e non partirebbe
    // Infatti  getArticoliPerCategoria richiede categoria come paramentro ed è usato come metodo all'interno di homePage
    
    // Home page con possibilità di filtrare articoli per categoria
    @GetMapping("/")
    public String homePage(@RequestParam(value = "categoria", required = false) String categoria, Model model) {
        ArrayList<String> categorie = b1.getCategorieDistinte();
        model.addAttribute("categorie", categorie);

        ArrayList<Articolo1> listaArticoli;
        if (categoria != null && !categoria.isEmpty()) {
            listaArticoli = b1.getArticoliPerCategoria(categoria);
        } else {
            listaArticoli = b1.getAllArticoli();
        }

        model.addAttribute("listaArticoli", listaArticoli);
        return "index";
    }

    // Ricerca articoli tramite parola chiave
    @GetMapping("/articoli")
    public String visualizzaArticoli(@RequestParam(value = "categoria", required = false) String categoria,
                                     @RequestParam(value = "parolaChiave", required = false) String parolaChiave,
                                     Model model) {
        ArrayList<String> categorie = b1.getCategorieDistinte();
        model.addAttribute("categorie", categorie);

        ArrayList<Articolo1> listaArticoli;
        // se non è null ovvero è stata creata e non  è vuota 
        if (parolaChiave != null && !parolaChiave.isEmpty()) {
            // su b1 chiamo il metodo per filtrare le categorie
            listaArticoli = b1.getArticoliPerParolaChiave(parolaChiave); // Usa il metodo che implementa la ricerca per parola chiave
        } else {
            listaArticoli = b1.getAllArticoli();
        }

        model.addAttribute("listaArticoli", listaArticoli);
        return "index";
    }

    // Gestione articoli, disponibile solo per utenti autorizzati
    @GetMapping("private/gestoreArticoli")
    public String gestioneArticoli(Model model) {
        ArrayList<Articolo1> listaArticoli = b1.getAllArticoli();
        model.addAttribute("lista", listaArticoli);
        return "gestoreBlog";
    }

    // Aggiungi un nuovo articolo
    @PostMapping("/aggiungiArticolo")
    public String addArticolo(@RequestParam("titolo") String titolo,
                              @RequestParam("contenuto") String contenuto,
                              @RequestParam("categoria") String categoria,
                              @RequestParam("url") String url) {
        b1.createArticle(titolo, contenuto, categoria, url);
        return "redirect:/private/gestoreArticoli"; // Cambiato per la security
    }

    // Rimuovi un articolo
    @PostMapping("/rimuoviArticolo")
    public String removeArticolo(@RequestParam("titolo") String titolo) {
        b1.deleteArticolo(titolo);
        return "redirect:/private/gestoreArticoli";
    }

    // Cambia la categoria di un articolo
    @PostMapping("/cambiaCategoria")
    public String cambiaGenere(@RequestParam("titolo") String titolo, 
                               @RequestParam("categoria") String nuovaCategoria, 
                               Model model) {
        boolean articoloAggiornato = b1.updateCategoria(titolo, nuovaCategoria);

        if (!articoloAggiornato) {
            model.addAttribute("messaggio", "Categoria non presente");
        } else {
            model.addAttribute("messaggio", "Categoria aggiornata con successo");
        }

        model.addAttribute("lista", b1.getAllArticoli());
        return "gestoreBlog";
    }

    // Visualizza articoli filtrati per categoria
    @GetMapping("/articoliPerCategoria")
    public String articoliPerCategoria(@RequestParam("categoria") String categoria, Model model) {
        ArrayList<Articolo1> articoliFiltrati = b1.getArticoliPerCategoria(categoria);
        model.addAttribute("lista", articoliFiltrati);
        return "index";
    }

    // Rotta per la pagina di login
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
