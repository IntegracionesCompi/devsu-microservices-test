-- =========================
-- TABLA PERSONA
-- =========================
CREATE TABLE persona (
    id VARCHAR(36) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(10),
    edad INT,
    identificacion VARCHAR(50),
    direccion VARCHAR(200),
    telefono VARCHAR(20)
);

-- =========================
-- TABLA CLIENTE
-- =========================
CREATE TABLE cliente (
    id VARCHAR(36) PRIMARY KEY,
    persona_id VARCHAR(36) NOT NULL,
    contrasena VARCHAR(100),
    estado BOOLEAN,
    CONSTRAINT fk_cliente_persona
        FOREIGN KEY (persona_id) REFERENCES persona(id)
);

-- =========================
-- TABLA CUENTA
-- =========================
CREATE TABLE cuenta (
    id VARCHAR(36) PRIMARY KEY,
    cliente_id VARCHAR(36) NOT NULL,
    numero_cuenta VARCHAR(20) UNIQUE,
    tipo_cuenta VARCHAR(20),
    saldo_inicial DECIMAL(10,2),
    estado BOOLEAN,
    CONSTRAINT fk_cuenta_cliente
        FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- =========================
-- TABLA MOVIMIENTO
-- =========================
CREATE TABLE movimiento (
    id VARCHAR(36) PRIMARY KEY,
    cuenta_id VARCHAR(36) NOT NULL,
    fecha DATE,
    tipo_movimiento VARCHAR(20),
    valor DECIMAL(10,2),
    saldo_anterior DECIMAL(10,2),
    saldo DECIMAL(10,2),
    CONSTRAINT fk_movimiento_cuenta
        FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);

-- =========================
-- TABLA AUDITORIA CLIENTE (EVENTOS)
-- =========================
CREATE TABLE cliente_movimiento (
    movimiento_id VARCHAR(36) PRIMARY KEY,
    cliente_id VARCHAR(36) NOT NULL,
    cuenta_id VARCHAR(36) NOT NULL,
    fecha DATE,
    tipo_movimiento VARCHAR(20),
    valor DECIMAL(10,2),
    saldo_anterior DECIMAL(10,2),
    saldo DECIMAL(10,2),
    received_at TIMESTAMP
);
