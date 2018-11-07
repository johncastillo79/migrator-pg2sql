/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrator;

import org.json.JSONObject;

/**
 *
 * @author desktop
 */
public class JsonProcessor {

    private String source;
    private JSONObject json;

    public JsonProcessor(String source) {
        this.source = source;
        json = new JSONObject(source);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void printAttributes() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (String key : json.keySet()) {
            sb.append(key).append(", ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    public JSONObject getJson() {
        return json;
    }
    
}
