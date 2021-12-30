package com.telkom.trial.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorLog {
    private LocalDateTime localDateTime;
    private String title;
    private String message;
    private String location;
    private String line;

    public JSONObject toJSON(){
        return new JSONObject()
                .put("tanggal",localDateTime)
                .put("title",title)
                .put("location",location)
                .put("message",message)
                .put("line",line);
    }
    public String toString(){
        return new StringBuilder().append("[ ")
                .append(localDateTime).append(" ")
                .append(title).append(" ")
                .append(location).append(" ")
                .append(message).append(" ")
                .append(line).append(" ]").toString();
    }
}
