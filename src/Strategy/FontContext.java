package Strategy;

public class FontContext {
    private FontStrategy fontstrategy;
    public FontContext(FontStrategy fs){
        this.fontstrategy=fs;
    }
    public void runStrategy(String fn){
        fontstrategy.setFont(fn);
    }
}
