package AbstractFactory;

public final class GadgetFactoryProducer {

    private GadgetFactoryProducer(){
        throw new AssertionError();
    }

    public static GadgetFactory getFactory(String os){

        //根據Environment參數決定要Return哪個ConcreteFactory
        switch(os) {
            case "Win": return new WinGadgetFactory();
            case "Mac": return new MacGadgetFactory();
        }

        return null;
    }

}