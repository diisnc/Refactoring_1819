
import BusinessLayer.TradingCore;

public class APPMain{
    
    public static void main(String args[]) {
        TradingCore tc = new TradingCore();
        PresentationLayer.Principal princ = new PresentationLayer.Principal(tc);
        princ.setVisible(true);
    }
    
}
