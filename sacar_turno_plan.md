# Plan — Flujo 2 "Sacar Turno"

> Fuente de verdad de diseño: `docs/Saludify_Flujo_completo (standalone)_updated.html`
> Este doc es una referencia de trabajo — el HTML siempre prevalece si hay discrepancia.
> Working dir: `/Users/andrescalizaya/Downloads/DiseInter/TrabajoPracticoFinal/Saludify`

---

## Estado

| # | Pantalla | Estado |
|---|---|---|
| 10 | ForWhomScreen — ¿Para quién es el turno? | ✅ Implementada |
| 11 | SearchScreen — Buscador de especialidad | ✅ Implementada |
| 12 | ResultsScreen — Resultados | 🔴 Stub temporal |
| 13 | ConfirmScreen — Confirmar turno | 🔴 Stub temporal |
| 14 | ConfirmedScreen — Turno confirmado | 🔴 Stub temporal |

---

## Flujo de navegación

```
HomeScreen ──[tap "Sacar turno"]──▶ ForWhomScreen (10)
                                        │ tap "Continuar"
                                        ▼
                                   SearchScreen (11)
                                        │ tap "Continuar" (habilitado)
                                        ▼
                                   ResultsScreen (12)
                                        │ tap "Reservar"
                                        ▼
                                   ConfirmScreen (13)
                                        │ tap "Confirmar turno"
                                        ▼
                                   ConfirmedScreen (14)
                                        │ "Ver mis turnos" / "Volver al inicio"
                                        ▼
                                   MainScreen (popUpTo Main inclusive=false)
```

Las pantallas son **standalone** — no están dentro del Scaffold de `MainScreen`, no tienen BottomBar.
Cada screen recibe callbacks de navegación por parámetros; ninguna conoce el `navController`.

---

## Checklist de implementación

### Archivos modificados

- [x] **`domain/model/Medico.kt`** — Extendido con modalidad, lugar, diaSemana, dia, mes, hora
- [x] **`data/MockData.kt`** — Lista de 3 médicos de cardiología agregada
- [x] **`presentation/navigation/Route.kt`** — 5 rutas nuevas: ParaQuien, Buscar, Resultados, ConfirmarTurno, TurnoConfirmado
- [x] **`presentation/screens/home/HomeScreen.kt`** — `onSacarTurno` wired en `QuickAccessCard("Sacar turno")`
- [x] **`presentation/screens/main/MainScreen.kt`** — `onSacarTurno` recibido y pasado a HomeScreen
- [x] **`presentation/navigation/NavGraph.kt`** — Todos los composables del flujo 2 registrados; stubs para 12-14

### Archivos nuevos

- [x] **`presentation/screens/forwhom/ForWhomScreen.kt`** — Frame 10 completo
- [x] **`presentation/screens/search/SearchScreen.kt`** — Frame 11 completo
- [ ] **`presentation/screens/results/ResultsScreen.kt`** — Frame 12
- [ ] **`presentation/screens/confirm/ConfirmScreen.kt`** — Frame 13
- [ ] **`presentation/screens/confirmed/ConfirmedScreen.kt`** — Frame 14

---

## Notas de implementación — pantallas completadas

### Frame 10 — ForWhomScreen
- Stepper 1/4 lleno
- Header: "Sacar turno" centrado (sin nombre de usuario — todavía no hay selección)
- 3 cards de paciente interactivas con `remember { mutableIntStateOf(0) }` (María pre-seleccionada)
- Card dashed "Agregar integrante" (decorativa)
- CTA "Continuar" siempre habilitado (María viene pre-seleccionada)
- Pacientes hardcodeados: María González (Titular), Lucas González (Hijo 8 años), Roberto González (Cónyuge)

### Frame 11 — SearchScreen
- Stepper 2/4 lleno
- Header: "Sacar turno · María González" centrado
- **Exclusión mutua** entre text field y chips de especialidad:
  - Escribir texto → chips se ven grises (`dimmed`) pero siguen siendo clickeables; tap limpia el texto y selecciona
  - Seleccionar chip → field se ve gris pero sigue siendo focuseable; `onFocusChanged` limpia la selección
- Placeholder: "Ej: Cardiología, Dr. Silva…"
- Chips de modalidad (Todos/Online/Presencial) son decorativos en esta pantalla
- CTA "Continuar" deshabilitado hasta que haya texto O chip seleccionado

---

## Datos MockData agregados

```kotlin
// domain/model/Medico.kt
data class Medico(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val modalidad: String,   // "Presencial" | "Online"
    val lugar: String,
    val diaSemana: String,
    val dia: String,
    val mes: String,
    val hora: String
)

// data/MockData.kt
val medicos = listOf(
    Medico(1, "Dr. Roberto Silva",  "Cardiología", "Presencial", "Hospital Central",  "Lun", "23", "Jun", "10:30 hs"),
    Medico(2, "Dra. Ana Martínez",  "Cardiología", "Presencial", "Sanatorio Güemes",  "Mar", "24", "Jun", "9:00 hs"),
    Medico(3, "Dr. Carlos López",   "Cardiología", "Online",     "Videoconsulta",     "Lun", "23", "Jun", "11:00 hs")
)
```

---

## Rutas (Route.kt)

```kotlin
object ParaQuien       : Routes("para_quien")
object Buscar          : Routes("buscar")
object Resultados      : Routes("resultados")
object ConfirmarTurno  : Routes("confirmar_turno")
object TurnoConfirmado : Routes("turno_confirmado")
```

---

## Spec pendiente — Frame 12 · ResultsScreen

**Archivo:** `presentation/screens/results/ResultsScreen.kt`

**Firma:**
```kotlin
@Composable
fun ResultsScreen(
    onBackClick: () -> Unit,
    onReservar: () -> Unit
)
```

**Header sticky** (`statusBarsPadding()`):
- Back + `"Resultados"` 15sp SemiBold centrado + `Icons.Filled.FilterList` `TextMuted` derecha
- Stepper: **3 de 4** llenos

**Segmented control** (padding 12/18dp, bg `BackgroundSegmented`, radius 12dp, padding interno 3dp):
- `"Turnos"` activo: bg white, shadow, 13sp SemiBold + badge `"12"` pill `BrandPrimary`
- `"Cartilla"` inactivo: `TextMuted` 13sp

**Controles** (padding 10/18dp): `"Ordenar"` `BrandPrimary` · `"12 disponibles"` `TextSecondary`

**MedicoCard** (radius 17dp, borde `BorderDefault`, bg white, shadow leve, mx 18dp, gap 10dp):
- Nombre 15sp Bold + especialidad 12sp `TextMuted` | badge modalidad (Presencial: `BrandPrimarySurface`/`BrandPrimary`; Online: `SemanticSuccessSurfaceStrong`/`SemanticSuccess`)
- `Icons.Outlined.LocationOn` + dirección 12sp `TextMuted`
- `HorizontalDivider`
- Footer: slot disponible (bg `SemanticSuccessSurface` si online, `BackgroundSubtle` si presencial) + botón `"Reservar"` `BrandPrimary` → `onReservar()`

---

## Spec pendiente — Frame 13 · ConfirmScreen

**Archivo:** `presentation/screens/confirm/ConfirmScreen.kt`

**Firma:**
```kotlin
@Composable
fun ConfirmScreen(
    onBackClick: () -> Unit,
    onConfirmar: () -> Unit
)
```

**Header sticky** (`statusBarsPadding()`): back + `"Confirmar turno"` centrado + stepper **4/4** + `HorizontalDivider`

**Cuerpo** (padding 20/18dp, gap 14dp):
- `"Resumen del turno"` 22sp Bold, letterSpacing -0.4sp
- **DoctorCard** (radius 18dp, borde `BorderDefault`, shadow BrandPrimary 8%): avatar `"RS"` 52dp `BrandPrimary` + nombre 16sp Bold + especialidad 13sp `TextMuted` + rating ★★★★☆ 4.8 · 124 opiniones (`SemanticWarningIcon`)
- **DetallesCard** (radius 18dp, borde `BorderDefault`, divisores internos `BackgroundSubtle`):
  - Fecha: badge `"Jun/23"` `BrandPrimarySurface` + `"Lun 23 Jun · 10:30 hs"` 14sp SemiBold
  - Lugar: `Icons.Outlined.LocationOn` + `"Hospital Central · Av. Santa Fe 1234"` 13sp
  - Modalidad: `Icons.Filled.CheckCircle` `SemanticSuccess` + `"Presencial"`
  - Cobertura: `Icons.Outlined.Shield` `BrandPrimary` + `"Saludify · Plan Premium · Sin costo adicional"`
- CTA `"Confirmar turno"` full-width 52dp `BrandPrimary` radius `SaludifyRadius.button`

---

## Spec pendiente — Frame 14 · ConfirmedScreen

**Archivo:** `presentation/screens/confirmed/ConfirmedScreen.kt`

**Firma:**
```kotlin
@Composable
fun ConfirmedScreen(
    onVerTurnos: () -> Unit,
    onVolverInicio: () -> Unit
)
```

**Layout:** Column centrado verticalmente, padding horizontal 18dp, `BackgroundApp`

**SuccessIcon:** outer `SemanticSuccessSurface` → inner 88dp `SemanticSuccessSurfaceStrong` + `Icons.Filled.Check` 36dp `SemanticSuccess`

**Textos:** `"¡Turno confirmado!"` 26sp Bold / `"Te enviamos la confirmación\npor notificación y email"` 14sp `TextMuted`

**ResumenCard** (radius 20dp, borde `BorderDefault`, shadow BrandPrimary 8%): avatar "RS" + doctor + divider + fecha/hora 2 cols + lugar

**Acciones:**
- `"Agregar al calendario"` card blanca borde `BorderDefault` radius 14dp (decorativa)
- `"Ver mis turnos"` full-width `BrandPrimary` → `onVerTurnos()`
- `"Volver al inicio"` 13sp `TextMuted` centrado → `onVolverInicio()`

---

## Wiring NavGraph completo

```kotlin
composable(Routes.Main.route) {
    MainScreen(onSacarTurno = { navController.navigate(Routes.ParaQuien.route) })
}
composable(Routes.ParaQuien.route) {
    ForWhomScreen(
        onBackClick = { navController.popBackStack() },
        onContinuar = { navController.navigate(Routes.Buscar.route) }
    )
}
composable(Routes.Buscar.route) {
    SearchScreen(
        onBackClick = { navController.popBackStack() },
        onNext      = { navController.navigate(Routes.Resultados.route) }
    )
}
composable(Routes.Resultados.route) {
    ResultsScreen(
        onBackClick = { navController.popBackStack() },
        onReservar  = { navController.navigate(Routes.ConfirmarTurno.route) }
    )
}
composable(Routes.ConfirmarTurno.route) {
    ConfirmScreen(
        onBackClick = { navController.popBackStack() },
        onConfirmar = { navController.navigate(Routes.TurnoConfirmado.route) }
    )
}
composable(Routes.TurnoConfirmado.route) {
    ConfirmedScreen(
        onVerTurnos    = { navController.navigate(Routes.Main.route) { popUpTo(Routes.Main.route) } },
        onVolverInicio = { navController.navigate(Routes.Main.route) { popUpTo(Routes.Main.route) } }
    )
}
```

---

## Decisiones de arquitectura

- **Sin ViewModel** en las pantallas del flujo — data hardcodeada de `MockData`
- **Estado UI local** (`remember`) para selección de paciente, chip activo, tab segmented
- **Callbacks por parámetro** — ninguna screen conoce el `navController`
- **Sin BottomBar** — standalone en el NavHost, fuera del Scaffold de MainScreen
- **`statusBarsPadding()`** en el header de cada pantalla
