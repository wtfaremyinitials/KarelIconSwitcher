import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import kareltherobot.RobotWorldWindow;
import kareltherobot.World;

public class KarelIconSwitcher {
    
    private static KarelIconSwitcher instance;
    private Map<String, Field> fields;
    
    public KarelIconSwitcher() {
        fields = new HashMap<String, Field>();
        
        for(KarelIcon icon : KarelIcon.values())
        	addField(icon.getFieldName());
        
    }
    
    public static KarelIconSwitcher getInstance() {
        if(instance != null)
            return instance;
        return (instance = new KarelIconSwitcher());
    }
    
    public void changeIcon(KarelIcon karelState, String icon) {
    	try {
    		
			changeIcon(karelState, ImageIO.read(new File(icon)));
			forceUpdate();
			
		} catch (IOException e) { e.printStackTrace(); }
    }
    
    public void changeIcon(KarelIcon karelState, Image icon) {  	
        try {
        	
        	fields.get(karelState.getFieldName()).set(null, icon);
        	
		} catch (IllegalArgumentException | IllegalAccessException e) { e.printStackTrace(); }        	
    }
    
    private void addField(String name) {
        try {
            Field field = RobotWorldWindow.class.getDeclaredField(name);
            field.setAccessible(true);
            
            fields.put(name, field);
        } catch(Exception e) {}
    }
    
    private void forceUpdate() {
    	try {
    		Field view = World.class.getDeclaredField("view");
    		view.setAccessible(true);
    		RobotWorldWindow window = (RobotWorldWindow) view.get(null);
        	
    		Method scaleAllImagesMethod = RobotWorldWindow.class.getMethod("scaleAllRobotImages");
    		scaleAllImagesMethod.setAccessible(true);
    		
    		Method repaint = RobotWorldWindow.class.getMethod("repaint");
    		repaint.setAccessible(true);
    		
    		Field oldScale = RobotWorldWindow.class.getDeclaredField("oldScale");
    		oldScale.setAccessible(true);

    		oldScale.set(null, 1);    		
    		
    		window.scaleAllRobotImages();
    		window.repaint();
    		        	
    	} catch(Exception e) { e.printStackTrace(); }
    }
    
    public enum KarelIcon {
    	
    	KAREL_NORTH_ON("karelImageNOnBase"),
    	KAREL_SOUTH_ON("karelImageSOnBase"),
    	KAREL_EAST_ON("karelImageEOnBase"),
    	KAREL_WEST_ON("karelImageWOnBase"),
    	
    	KAREL_NORTH_OFF("karelImageNOffBase"),
    	KAREL_SOUTH_OFF("karelImageSOffBase"),
    	KAREL_EAST_OFF("karelImageEOffBase"),
    	KAREL_WEST_OFF("karelImagWOffBase");
    	
    	private String fieldName;
    	
    	private KarelIcon(String fieldName) {
    		this.fieldName = fieldName;
    	}
    	
    	public String getFieldName() {
    		return this.fieldName;
    	}
    	
    }

}
