-- Crear tabla Persona
CREATE TABLE Persona (
                         id SERIAL PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL,
                         genero VARCHAR(50),
                         edad INT,
                         identificacion VARCHAR(50) UNIQUE NOT NULL,
                         direccion VARCHAR(255),
                         telefono VARCHAR(50)
);

-- Crear tabla Cliente que hereda de Persona
CREATE TABLE Cliente (
                         id INT PRIMARY KEY REFERENCES Persona(id),
                         cliente_id SERIAL UNIQUE NOT NULL,
                         contraseña VARCHAR(255) NOT NULL,
                         estado BOOLEAN NOT NULL
);

-- Crear tabla Cuenta
CREATE TABLE Cuenta (
                        id SERIAL PRIMARY KEY,
                        numero_cuenta VARCHAR(50) UNIQUE NOT NULL,
                        tipo_cuenta VARCHAR(50) NOT NULL,
                        saldo_inicial DECIMAL(15, 2) NOT NULL,
                        estado BOOLEAN NOT NULL,
                        cliente_id INT NOT NULL,
                        CONSTRAINT fk_cliente
                            FOREIGN KEY (cliente_id)
                                REFERENCES Cliente(id)
);

-- Crear tabla Movimiento
CREATE TABLE Movimiento (
                            id SERIAL PRIMARY KEY,
                            fecha TIMESTAMP NOT NULL,
                            tipo_movimiento VARCHAR(50) NOT NULL,
                            valor DECIMAL(15, 2) NOT NULL,
                            saldo DECIMAL(15, 2) NOT NULL,
                            cuenta_id INT NOT NULL,
                            CONSTRAINT fk_cuenta
                                FOREIGN KEY (cuenta_id)
                                    REFERENCES Cuenta(id)
);

-- Insertar datos de prueba en la tabla Persona
INSERT INTO Persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES
    ('Jose Lema', 'Masculino', 30, '1234567890', 'Otavalo sn y principal', '098254785'),
    ('Marianela Montalvo', 'Femenino', 25, '0987654321', 'Amazonas y NNUU', '097548965'),
    ('Juan Osorio', 'Masculino', 40, '1122334455', '13 junio y Equinoccial', '098874587');

-- Insertar datos de prueba en la tabla Cliente
INSERT INTO Cliente (id, cliente_id, contraseña, estado)
VALUES
    ((SELECT id FROM Persona WHERE identificacion = '1234567890'), 1, '1234', TRUE),
    ((SELECT id FROM Persona WHERE identificacion = '0987654321'), 2, '5678', TRUE),
    ((SELECT id FROM Persona WHERE identificacion = '1122334455'), 3, '1245', TRUE);

-- Insertar datos de prueba en la tabla Cuenta
INSERT INTO Cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES
    ('478758', 'Ahorros', 2000, TRUE, (SELECT id FROM Cliente WHERE cliente_id = 1)),
    ('225487', 'Corriente', 100, TRUE, (SELECT id FROM Cliente WHERE cliente_id = 2)),
    ('495878', 'Ahorros', 0, TRUE, (SELECT id FROM Cliente WHERE cliente_id = 3)),
    ('496825', 'Ahorros', 540, TRUE, (SELECT id FROM Cliente WHERE cliente_id = 2)),
    ('585545', 'Corriente', 1000, TRUE, (SELECT id FROM Cliente WHERE cliente_id = 1));

-- Insertar datos de prueba en la tabla Movimiento
INSERT INTO Movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id)
VALUES
    ('2024-02-10 10:00:00', 'Retiro', -575, 1425, (SELECT id FROM Cuenta WHERE numero_cuenta = '478758')),
    ('2024-02-08 12:00:00', 'Deposito', 600, 700, (SELECT id FROM Cuenta WHERE numero_cuenta = '225487')),
    ('2024-02-09 09:00:00', 'Deposito', 150, 150, (SELECT id FROM Cuenta WHERE numero_cuenta = '495878')),
    ('2024-02-08 15:00:00', 'Retiro', -540, 0, (SELECT id FROM Cuenta WHERE numero_cuenta = '496825'));