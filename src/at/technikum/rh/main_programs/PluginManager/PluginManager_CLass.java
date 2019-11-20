package at.technikum.rh.main_programs.PluginManager;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.Interfaces.PluginManager;

public class PluginManager_CLass implements PluginManager{
    @Override
    public Iterable<Plugin> getPlugins() {
        return null;
    }

    @Override
    public void add(Plugin plugin) {

    }

    @Override
    public void add(String plugin) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

    }

    @Override
    public void clear() {

    }
}
