# Visibility Rules v1

## Objetivo

Definir qué información puede recibir cada jugador.

Estas reglas son obligatorias para evitar filtrar información oculta.

## Visible para ambos jugadores

- Pokémon Activo de ambos jugadores.
- Pokémon en Banca de ambos jugadores.
- Daño sobre Pokémon en juego.
- Condiciones especiales sobre Pokémon en juego.
- Energías unidas.
- Herramientas unidas.
- Evoluciones visibles.
- Estadio activo.
- Pilas de descarte.
- Cantidad de cartas en cada mano.
- Cantidad de cartas en cada mazo.
- Cantidad de cartas de Premio restantes.
- Log público de eventos.

## Visible solo para el jugador dueño

- Su mano completa.
- Cartas robadas por él.
- Cartas de Premio cuando las toma.
- Decisiones privadas pendientes.

## Nunca visible para el rival

- Contenido de la mano.
- Orden del mazo.
- Identidad de cartas de Premio boca abajo.
- Cartas privadas elegidas durante búsquedas hasta que una regla indique revelarlas.

## GameState vs GameView

`GameState` puede contener toda la información.

`GameView` debe filtrar información según `viewerPlayerId`.

## Reglas de seguridad

- El frontend no debe recibir `GameState`.
- El filtrado se hace en backend.
- No alcanza con ocultar datos por CSS.
- Si una carta está boca abajo, debe representarse como `HiddenCardView`.
