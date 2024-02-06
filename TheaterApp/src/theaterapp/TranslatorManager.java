package theaterapp;

import java.util.HashMap;
import java.util.Map;

public class TranslatorManager {
    private Map<String, Translator> translatorMap = new HashMap<>();
    private String activeIdiom;
    
    /**
     *
     * @param msg
     * @return
     */
    public String translate (String msg){        
        return this.translatorMap.get(this.activeIdiom).translate(msg);
    }

    /**
     *
     */
    public TranslatorManager() {     
        activeIdiom = "castellano";
        
        Translator castellano = new Translator("castellano.txt");
        translatorMap.put("castellano", castellano);
        Translator ingles  = new Translator("ingles.txt");
        translatorMap.put("ingles", ingles);
        Translator euskera = new Translator("euskera.txt");
        translatorMap.put("euskera", euskera);
        Translator catalan = new Translator("catalan.txt");
        translatorMap.put("catalan", catalan);
    }

    /**
     *
     * @return
     */
    public String getActiveIdiom() {
        return activeIdiom;
    }

    /**
     *
     * @param activeIdiom
     */
    public void setActiveIdiom(String activeIdiom) {
        this.activeIdiom = activeIdiom;
    }
    
    
}
