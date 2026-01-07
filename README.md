## Aquí tienes el contenido del archivo README.md en texto plano, sin negritas, listo para copiar:

## Sistema de Gestion de Entradas - Proyecto Ticketek
Este proyecto consiste en el modelado y desarrollo de un sistema de gestion de espectaculos y venta de entradas realizado en Java. 
La aplicacion permite administrar el ciclo de vida completo de un evento, desde el registro de la sede hasta la gestion de entradas por parte del usuario.

## Funcionalidades Principales
- Modelado de Sedes: Implementacion de diversos recintos como Estadios para campo general, Teatros para asientos numerados y MiniEstadios con servicios adicionales.
- Gestion de Funciones: Programacion de espectaculos en fechas especificas asociadas a una sede y un precio base.
- Sistema de Ventas Numeradas: Asignacion dinamica de ubicaciones con fila y asiento basada en la capacidad de la sede.
- Perfil de Usuario: Consulta de historial de entradas futuras y pasadas y validacion de autenticidad mediante contrasenia.
- Logica de Costos: Calculo automatico de precios incluyendo porcentajes adicionales por sector y cargos por consumicion.
- Seguridad y Anulacion: Soporte para anulacion de tickets en tiempo real y validacion de fechas para evitar cambios en eventos pasados.

## Arquitectura del Proyecto
El sistema utiliza una arquitectura orientada a objetos robusta:
- Interfaces: ITicketek e IEntrada definen el contrato de comportamiento del sistema.
- Jerarquia de Sedes: Uso de herencia y polimorfismo con una clase abstracta Sede y sus derivados.
- Manejo de Fechas: Clase Fecha personalizada para validaciones de cronogramas y anios bisiestos.

## Reportes Administrativos
- Recaudacion Total: Seguimiento de ingresos por espectaculo.
- Estadisticas por Sede: Desglose de recaudacion segun el establecimiento.
- Estado de Ocupacion: Visualizacion detallada de entradas vendidas frente a la capacidad disponible.

## Testing
Se incluye una suite de pruebas unitarias en TicketekTest.java utilizando JUnit que valida la integridad de los datos, los calculos de precios y los flujos de negocio.
