package com.telkom.trial.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Argument {
    @Builder.Default
    private String type="plain";
    private String source;
    @Builder.Default
    private String output="console";

    public JSONObject toJSON(){
        return new JSONObject()
                .put("type",type)
                .put("source",source)
                .put("output",output);
    }
}
