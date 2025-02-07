package press;

import press.news.News;
import press.news.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un flux d'informations de type Breaking News.
 * Hérite de NewsReader et redistribue les nouvelles à d'autres lecteurs.
 */
public class BreakingNewsFeed extends NewsReader {
    /** Liste des lecteurs abonnés au flux */
    private List<NewsReader> readers;

    /**
     * Constructeur de BreakingNewsFeed.
     * @param name Nom du flux
     */
    public BreakingNewsFeed(String name) {
        super(name);
        this.readers = new ArrayList<>();
    }

    /**
     * Ajoute un lecteur à la liste des abonnés au flux.
     * @param reader Le lecteur à ajouter
     */
    public void addReader(NewsReader reader) {
        readers.add(reader);
    }

    /**
     * Reçoit une nouvelle et la redistribue aux lecteurs abonnés si elle est de type BREAKING.
     * @param news La nouvelle reçue
     */
    @Override
    public void receive(News news) {
        if (news.getStatus() == Status.BREAKING) {
            for (NewsReader reader : readers) {
                reader.receive(news);
            }
        }
    }
}