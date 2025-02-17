package lzpu.logistics.hq.websocket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String from;
    private String to;
    private String message;
    private Date time;
}
