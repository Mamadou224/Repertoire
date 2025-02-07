package press;


import press.news.News;

/**
 * la classe NewsReader qui permet aux lecteurs de s'enregistrer à une agence
 * de presse et de lire les articles.
 */

 public class NewsReader {
    // Déclaration de l'attribut name du lecteur avec l'option protected
    protected String name;
    // Déclaration de l'attribut numberOfReceivedNews avec l'option protected
    protected int numberOfReceivedNews;

    /**
     * Le constructeur de la classe NewsReader
     * @param name ,le nom du lecteur
     */

    public NewsReader(String name){
        this.name = name;
    }

    /**
     * la méthode du getteur pour obtenir le nom du lecteur
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * la méthode du getteur pour obtenir le nombre de fois qu'un lecteur a reçu un News
     * @return this.numberOfReceivedNews
     */
    public int getNumberOfReceivedNews() {
        return this.numberOfReceivedNews;
    }

    /**
     * la méthode ToString pour afficher le name du lecteur
     * @return  Retourne seulement le nom du lecteur
     */
    public String toString() {
        return this.name; 
    }
    
    /**
     * implémentation de la méthode receive
     * @param News de type news
     * Publie la nouvelle reçue
     */
    public void receive(News news) {
        this.numberOfReceivedNews++;
        publish(news); 
    }

    /**
     * implémentation de la méthode publish avec l'option protected
     * @param news de type news
     */
    protected void publish(News news) {
        System.out.println(this + " -> " + news);
    }
    
 }