package at.technikum.rh.main_programs.PluginManager;

import at.technikum.rh.Interfaces.PluginManager;
import at.technikum.rh.main_programs.Plugin.Plugin_Class;

import java.util.List;

public class PluginManager_CLass implements PluginManager{

    List<Plugin_Class> Iplug;
    /**
     * Returns a list of all plugins. Never returns null.
     * TODO: Refactor to List<Plugin>, Enumeration is deprecated
     * @return
     */
    //private final List<Plugin_Class> plugin_list = toList();
    @Override
    public List<Plugin_Class> getPlugins() {
        return Iplug;
    }

    @Override
    public void add(Plugin_Class plugin) {
        Iplug.add(plugin);
    }

    @Override
    public void add(String plugin) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> myclass = Class.forName(plugin);
        if(Plugin_Class.class.isAssignableFrom(myclass))
            try{
                Plugin_Class pluginClass = (Plugin_Class) myclass.getDeclaredConstructor().newInstance();
                Iplug.add(pluginClass);
                }catch (Exception e){
                    throw new ClassNotFoundException("Klasse wurde nicht gefunden");
        }
        else{
            throw new ClassNotFoundException("Klasse wurde nicht gefunden");
        }
    }

    @Override
    public void clear() {
        Iplug.clear();
    }
}
