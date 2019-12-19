package at.technikum.rh.main_programs.PluginManager;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.Interfaces.PluginManager;
import at.technikum.rh.main_programs.Plugin.*;
import at.technikum.rh.main_programs.request.Request_Class;

import java.util.LinkedList;
import java.util.List;

public class PluginManager_CLass implements PluginManager{
    List<Plugin> my_plugins = new LinkedList<Plugin>();

    public PluginManager_CLass(){
        my_plugins.add(new NaviPlugin());
        my_plugins.add(new NewTemperaturPlugin());
        my_plugins.add(new ToLowerPlugin());
        my_plugins.add(new Myhomepage());
        my_plugins.add(new Plugin_Class());
        my_plugins.add(new StaticFilePlugin());
    }


    @Override
    public Iterable<Plugin> getPlugins() {
        return my_plugins;
    }

    public Plugin getPlugin(Request_Class request_class) throws Exception{
        float a = 0;
        Plugin plugin = null;
        for (Plugin _plugin : my_plugins) {
            if (_plugin.canHandle(request_class) > a){
                a = _plugin.canHandle(request_class);
                plugin = _plugin;
            }
        }
        return plugin;
    }

    @Override
    public void add(Plugin_Class plugin) {
        my_plugins.add(plugin);
    }

    @Override
    public void add(String plugin) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> classInt = Class.forName(plugin);

        if(Plugin_Class.class.isAssignableFrom(classInt)){
            try{
                Plugin_Class plugin_class_int = (Plugin_Class) classInt.getDeclaredConstructor().newInstance();
                my_plugins.add(plugin_class_int);
            }catch (Exception e){
                throw new ClassNotFoundException("Class wurde nicht gefunden");
            }
        } else {
            throw new ClassNotFoundException("Class wurde nicht gefunden");
        }
    }

    @Override
    public void clear() {
            my_plugins.clear();
    }
}






























































    /*


    public PluginManager_CLass(){
        Iplug.add(new TemperaturPlugin());
        Iplug.add(new StaticFilePlugin());
        Iplug.add(new ToLowerPlugin());
        Iplug.add(new StaticFilePlugin());
        Iplug.add(new Plugin_Class());
    }

    /*
    public PluginManager_CLass(){
        my_plugins.add(new Myhomepage());
        //my_plugins.add(new NaviPlugin());
        my_plugins.add(new TemperaturPlugin());
    }

    @Override
    public Iterable<Plugin> getPlugins() {
        if(my_plugins.isEmpty())
            my_plugins.add(new Myhomepage());
        return my_plugins;
    }

    @Override
    public void add(Plugin_Class plugin) {
        my_plugins.add(plugin);
    }

    @Override
    public void add(String plugin) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

    }

    @Override
    public void clear() {

    }
     */
    /**

     * implements a new pluginmanager with the plugins specified in the variable _plugin_file
    public PluginManager_CLass(){
        String _plug_name = "";
        try {
            BufferedReader _cfg = new BufferedReader(new FileReader(_plugin_file));
            while(true){
                _plug_name = _cfg.readLine();
                if(_plug_name == null) break;
                Class<?> c = Class.forName("mywebserver."+_plug_name);
                Iplug.add((Plugin) c.getDeclaredConstructor().newInstance());
            }
            _cfg.close();
        } catch (FileNotFoundException e) {
            System.out.println("Config not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




        @Override
    public Iterable<Plugin> getPlugins() {
        if(Iplug.isEmpty())
            Iplug.add(new Myhomepage());
        return Iplug;
    }

    @Override
    public void add(Plugin_Class plugin) {
        Iplug.add(plugin);
    }

    public Plugin_Class getPlugin(Request_Class request_class) throws Exception{
        float a = 0;
        Plugin_Class _plugin_class = null;
        for(Plugin plugin : Iplug){
            if(plugin.canHandle(request_class) > a){
                a = plugin.canHandle(request_class);
                _plugin_class = (Plugin_Class) plugin;
            }
        }
        return _plugin_class;
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
    //Hollt sich die Requests und gibt ein Plugin zurueck
    public Plugin_Class getBest(Request_Class req){
        Plugin_Class res = null;
        float highest = 0;
        for (Plugin p : Iplug) {
            float num = p.canHandle(req);
            if(num > highest){
                highest = num;
                res = (Plugin_Class) p;
            }
        }
        return res;
    }

}

     */
