CREATE TABLE socket (
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uq_socket_name (name)
);

CREATE TABLE cpu (
    id              BIGINT         NOT NULL AUTO_INCREMENT,
    brand           VARCHAR(100)   NOT NULL,
    model           VARCHAR(100)   NOT NULL,
    socket_id       BIGINT         NOT NULL,
    clock_speed_ghz DECIMAL(5, 2)  NOT NULL,
    cores           INT            NOT NULL,
    threads         INT            NOT NULL,
    tdp_watt        INT,
    price_eur       DECIMAL(10, 2) NOT NULL,
    version         INT            NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    CONSTRAINT fk_cpu_socket FOREIGN KEY (socket_id) REFERENCES socket (id)
);
