-- Datos iniciales: roles y usuario admin (contraseña en texto, igual que la app espera hoy)
INSERT INTO rol (nombre) VALUES ('ADMIN'), ('EMPLEADO');

-- Crear usuario admin usando el rol ADMIN
INSERT INTO usuario (nombre, email, password, rol_id)
VALUES ('Admin', 'admin@example.com', 'secreto123', (SELECT id FROM rol WHERE nombre='ADMIN' LIMIT 1));
