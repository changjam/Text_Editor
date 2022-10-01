package builder;

public class Style {

    private String  bcg;
    private String  _color;
    private String cursor;
    private String margin;
    private String TextEffect;

    public String getBackgroundColor() {
        return bcg;
    }

    public void setBackgroundColor(String bgc) {
        this.bcg = bgc;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public String get_Color() {
        return _color;
    }

    public void setColor(String _color) {
        this._color = _color;
    }

    public String getMargin(){
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getTextEffect() {
        return TextEffect;
    }

    public void setTextEffect(String textEffect) {
        this.TextEffect = textEffect;
    }

   @Override
    public String toString() { //css屬性

       StringBuilder sb = new StringBuilder();
       sb.append("style=\"");
       sb.append(" background-color: ");
       sb.append(getBackgroundColor());
       sb.append(get_Color());
       sb.append("; cursor: ");
       sb.append(getCursor());
       sb.append("; margin:");
       sb.append(getMargin());
       sb.append(";");
       sb.append(getTextEffect());
       sb.append(" \"");
       //查看string
       //System.out.println(sb);
       return sb.toString();
    }
}
