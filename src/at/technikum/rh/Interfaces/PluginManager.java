package at.technikum.rh.Interfaces;

import at.technikum.rh.main_programs.Plugin.Plugin_Class;

public interface PluginManager {
    /**
     * Returns a list of all plugins. Never returns null.
     * TODO: Refactor to List<Plugin>, Enumeration is deprecated
     * @return
     */
    public Iterable<Plugin> getPlugins();
    /**
     * Adds a new plugin. If the plugin was already added, nothing will happen.
     * @param plugin
     */
    void add(Plugin_Class plugin);

    /**
     * Adds a new plugin by class name. If the plugin was already added, nothing will happen.
     * Throws an exeption, when the type cannot be resoled or the class does not implement Plugin.
     * @param plugin
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    void add(String plugin) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
    /**
     * Clears all plugins
     */
    void clear();
}