package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class BlogService {

    // Crea un'EntityManagerFactory utilizzando il nome dell'unità di persistenza
    private EntityManagerFactory entityManagerFactory;

    public BlogService() {
        // Crea l'EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
    }

    // Iniezione dell'EntityManager
    @PersistenceContext
    private EntityManager entityManager;

    // Metodo per creare un nuovo articolo e salvarlo nel database
    @Transactional
    public void createArticle(String titolo, String contenuto, String categoria, String url) {
        Articolo1 articolo = new Articolo1();
        articolo.setTitolo(titolo);
        articolo.setContenuto(contenuto);
        articolo.setCategoria(categoria);
        articolo.setUrl(url);

        // Salva l'articolo nel database
        entityManager.persist(articolo);
    }

    // Metodo per ottenere tutti gli articoli
    @Transactional
    public ArrayList<Articolo1> getAllArticoli() {
        // Seleziona tutti gli articoli dal database
        List<Articolo1> lista = entityManager.createQuery("SELECT a FROM Articolo1 a", Articolo1.class).getResultList();
        // Converte la lista in un ArrayList
        return new ArrayList<>(lista);
    }

    // Metodo per aggiornare la categoria di un articolo
    @Transactional
    public boolean updateCategoria(String titolo, String categoria) {
        List<Articolo1> lista = entityManager.createQuery("SELECT a FROM Articolo1 a", Articolo1.class).getResultList();
        ArrayList<Articolo1> listaArticoli = new ArrayList<>(lista);

        // Cerca l'articolo con il titolo fornito
        for (Articolo1 articolo : listaArticoli) {
            if (articolo.getTitolo().equalsIgnoreCase(titolo)) {
                articolo.setCategoria(categoria);
                entityManager.persist(articolo); // Salva le modifiche
                return true; // Categoria aggiornata
            }
        }
        return false; // Articolo non trovato
    }

    // Metodo per eliminare un articolo in base al titolo
    @Transactional
    public void deleteArticolo(String titolo) {
        entityManager.createQuery("DELETE FROM Articolo1 a WHERE a.titolo = ?1")
                .setParameter(1, titolo)
                .executeUpdate();
    }

    // Metodo per ottenere tutte le categorie distinte degli articoli
    public ArrayList<String> getCategorieDistinte() {
        List<String> listaCategorie = entityManager.createQuery(
                "SELECT DISTINCT a.categoria FROM Articolo1 a", String.class)
                .getResultList();
        return new ArrayList<>(listaCategorie);
    }

    // Metodo per ottenere gli articoli per categoria
    public ArrayList<Articolo1> getArticoliPerCategoria(String categoria) {
        List<Articolo1> lista = entityManager.createQuery(
                "SELECT a FROM Articolo1 a WHERE a.categoria = :categoria", Articolo1.class)
                .setParameter("categoria", categoria)
                .getResultList();
        return new ArrayList<>(lista); // Converte la lista in ArrayList
    }

    // Metodo per cercare articoli per parola chiave nel titolo o nel contenuto
    public ArrayList<Articolo1> getArticoliPerParolaChiave(String parolaChiave) {
        List<Articolo1> lista = entityManager.createQuery(
                "SELECT a FROM Articolo1 a WHERE a.titolo LIKE :parolaChiave OR a.contenuto LIKE :parolaChiave", Articolo1.class)
                .setParameter("parolaChiave", "%" + parolaChiave + "%") // '%' per la ricerca parziale
                .getResultList();
        return new ArrayList<>(lista); // Converte la lista in ArrayList
    }

    // Metodo per ottenere l'articolo con il maggior numero di like
    // (decommenta se necessario)
    /*
    public Articolo getArticoloPiuLike() {
        return entityManager.createQuery("SELECT a FROM Articolo a ORDER BY a.likeCount DESC", Articolo.class)
                            .setMaxResults(1)
                            .getSingleResult();
    }
    */

}
