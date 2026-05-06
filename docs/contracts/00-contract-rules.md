# Contract Rules v1

## Objetivo
Estos contratos definen el lenguaje común entre backend, frontend, Game Engine, WebSockets, persistencia y tests.

No son implementación. Son acuerdos de datos y comportamiento.

## Reglas generales
- Todo contrato debe incluir `schemaVersion`.
- Todo estado de partida debe incluir `stateVersion`.
- El backend es la única fuente de verdad.
- El frontend nunca modifica el estado directamente.
- El frontend solo envía `GameAction`.
- El backend responde con `GameActionResult`.
- Todo cambio relevante genera uno o más `GameEvent`.
- El estado interno completo se representa con `GameState`.
- El frontend recibe solo `GameView`, nunca `GameState`.
- La información oculta no debe enviarse al cliente incorrecto.

## Extensibilidad
Está permitido:
- Agregar campos opcionales.
- Agregar nuevos `ActionType`.
- Agregar nuevos `EventType`.
- Agregar nuevos `GameErrorCode`.
- Agregar nuevos valores de enums si son necesarios.

No está permitido sin crear `schemaVersion: 2`:
- Cambiar el significado de un campo existente.
- Cambiar el tipo de dato de un campo existente.
- Reutilizar un código de error con otro significado.
- Enviar datos ocultos dentro de `GameView`.

## Convenciones de nombres
- Los ids son `strings`.
- Los enums usan `UPPER_SNAKE_CASE`.
- Los campos usan `camelCase`.
- El daño se modela como contadores de daño con `damageCounters`.
- Si se necesita daño en puntos, se usa `damagePoints`.
- 1 contador de daño equivale a 10 puntos de daño.

## Versionado
Todos los contratos de esta carpeta pertenecen a:

```json
{
  "schemaVersion": 1
}
```
