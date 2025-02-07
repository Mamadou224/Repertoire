package press;

import press.news.News;
import press.date.Date;

/**
 * Classe représentant un lecteur de nouvelles spécifiques récentes.
 * Hérite de SpecificNewsReader et ajoute un filtrage basé sur la durée de validité.
 */
public class RecentSpecificNewsReader extends SpecificNewsReader {
    /** Durée de validité des nouvelles en minutes */
    private int validityDuration;

    /**
     * Constructeur de RecentSpecificNewsReader.
     * @param name Nom du lecteur
     * @param keyword Mot-clé d'intérêt
     * @param validityDuration Durée de validité des nouvelles en minutes
     */
    public RecentSpecificNewsReader(String name, String keyword, int validityDuration) {
        super(name, keyword);
        this.validityDuration = validityDuration;
    }

    /**
     * Retourne la durée de validité des nouvelles.
     * @return Durée de validité en minutes
     */
    public int getValidityDuration() {
        return this.validityDuration;
    }

    /**
     * Reçoit une nouvelle et la publie si elle est pertinente et récente.
     * @param news La nouvelle reçue
     */
    @Override
    public void receive(News news) {
        super.receive(news);
        if (isRelevant(news) && isRecent(news)) {
            publish(news);
        }
    }

    /**
     * Vérifie si une nouvelle est récente selon la durée de validité.
     * @param news La nouvelle à vérifier
     * @return true si la nouvelle est récente, false sinon
     */
    private boolean isRecent(News news) {
        Date currentDate = Date.now();
        return !currentDate.isOlderThan(news.getDate(), validityDuration);
    }

    /**
     * Vérifie si une nouvelle est pertinente (contient le mot-clé).
     * @param news La nouvelle à vérifier
     * @return true si la nouvelle est pertinente, false sinon
     */
    private boolean isRelevant(News news) {
        return news.getTitle().contains(getKeyword()) || news.getText().contains(getKeyword());
    }
}