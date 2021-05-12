package org.csystem.application.reversepalindrome.server.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
public class Client { //POJO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "host", nullable = false)
    public String host;

    @Column(name = "port", nullable = false)
    public int port;

    @Column(name = "text", nullable = false)
    public String text;

    @Column(name = "reverse", nullable = false)
    public boolean reverse;

    @Column(name = "date_time", nullable = false)
    public LocalDateTime dateTime;

}
