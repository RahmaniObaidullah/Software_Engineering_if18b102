package at.technikum.rh.main_programs.Plugin;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Osm_handler extends DefaultHandler {

    private Map<String, SortedSet<String>> navi_data = new HashMap<String, SortedSet<String>>();
    private Map<String, String> map = new HashMap<String, String>();
    private SortedSet<String> streetSet = null;
    private boolean node = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if("node".equals(qName)) node = true;
        else if(("tag".equals(qName)) && (node == true)){
            map.put(attributes.getValue("k"), attributes.getValue("v"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        if("node".equals(qName)){
            if(map.containsKey("addr:city") && map.containsKey("addr:street")){
                if(navi_data.containsKey(map.get("addr:street").toLowerCase())){
                    if(navi_data.get(map.get("addr:street").toLowerCase()).contains(map.get("addr:city"))){
                        //do nothing
                    }
                    else {
                        navi_data.get(map.get("addr:street").toLowerCase()).add(map.get("addr:city"));
                    }
                }
                else{
                    streetSet = new TreeSet<String>();
                    streetSet.add(map.get("addr:city"));
                    navi_data.put(map.get("addr:street").toLowerCase(), streetSet);
                }
            }
            node = false;
            map.clear();
        }
    }
    /**
     * Returns parsed navi-data as a map
     * @return navi-data returns the parsed navi-data as a map
     */
    public Map<String, SortedSet<String>> return_navi_data(){
        return navi_data;
    }
}
