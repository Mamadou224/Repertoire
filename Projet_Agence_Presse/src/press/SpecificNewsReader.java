package press;

import press.news.News;

/**
 * La classe qui définit des lecteurs spécifiques.
 * Elle hérite de la classe NewsReader.
 */
public class SpecificNewsReader extends NewsReader {
    // Déclaration de l'attribut keyword
    private String keyword;

    /**
     * Constructeur de la classe SpecificNewsReader.
     * @param name Le nom du lecteur spécifique.
     * @param keyword Le mot-clé d'intérêt pour ce lecteur.
     */
    public SpecificNewsReader(String name, String keyword) {
        super(name);
        this.keyword = keyword;
    }

    /**
     * Retourne le mot-clé d'intérêt du lecteur spécifique.
     * @return Le mot-clé d'intérêt.
     */
    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Reçoit une news et la publie si elle contient le mot-clé d'intérêt.
     * @param news La news reçue.
     */
    @Override
    public void receive(News news) {
        this.numberOfReceivedNews++;
        // Vérifie si la nouvelle est pertinente avant de publier
        if (isRelevant(news)) {
            publish(news); // Publie seulement si c'est pertinent
        }
    }

    /**
     * Vérifie si la news est pertinente en fonction du mot-clé d'intérêt.
     * @param news La news à vérifier.
     * @return true si la news est pertinente, false sinon.
     */
    private boolean isRelevant(News news) {
        return news.getTitle().contains(keyword) || news.getText().contains(keyword);
    }

    /**
     * Publie une news en mettant en évidence le mot-clé d'intérêt.
     * @param news La news à publier.
     */
    protected void publish(News news) {
        String title = news.getTitle().replaceAll("(?i)" + keyword, "_" + keyword.toUpperCase() + "_");
        String text = news.getText().replaceAll("(?i)" + keyword, "_" + keyword.toUpperCase() + "_");
        News modifiedNews = new News(title, text, news.getStatus(), news.getDate());

        System.out.println(this + " -> " + modifiedNews);
    }
}