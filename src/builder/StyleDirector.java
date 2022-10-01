package builder;

public class StyleDirector {

    private  Builder style_build;

    public StyleDirector(Builder style_build){
        this.style_build =style_build;
    }
    public void build(){
        style_build.build_bcg();
        style_build.build_color();
        style_build.build_cursor();
        style_build.build_margin();

    }
}
