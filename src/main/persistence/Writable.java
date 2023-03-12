package persistence;

import org.json.JSONObject;

// interface
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
