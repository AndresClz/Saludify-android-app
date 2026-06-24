# Handoff — Estado actual del proyecto Saludify

> Objetivo: mock de alta fidelidad para demo en dispositivo real (Android Studio).
> No es una app de producción. Hardcodear datos está bien.
> Fuente de verdad de diseño: `README.md` (tokens, pantallas, interacciones).

---

## Arquitectura actual (post-refactor)

Estructura simplificada para mock. Sin capas de dominio ni repositorios — todo el dato fake está centralizado en `MockData.kt`.

```
com.example.saludify/
├── domain/model/          Data classes (Turno, Usuario, Especialidad, Medico, ObraSocial)
├── data/
│   └── MockData.kt        Único punto de datos: usuarios, turnos, especialidades + autenticar()
├── presentation/
│   ├── components/        BottomBar.kt
│   ├── navigation/        NavGraph.kt, Route.kt
│   └── screens/
│       ├── login/         LoginScreen.kt + LoginViewModel.kt
│       ├── home/          HomeScreen.kt  (datos desde MockData directo)
│       ├── attention/     AttentionScreen.kt
│       ├── procedures/    ProceduresScreen.kt
│       ├── help/          HelpScreen.kt
│       ├── profile/       ProfileScreen.kt
│       └── main/          MainScreen.kt
└── ui/theme/              Color.kt, Theme.kt, Type.kt
```

**Reglas:**
- ViewModel solo si hay estado interactivo (campos, errores). Actualmente solo `LoginViewModel`.
- Si una pantalla necesita ViewModel en el futuro, va en la misma carpeta que su Screen.
- Datos hardcodeados siempre en `MockData.kt`, nunca inline en los Composables.

---

## Estado de pantallas

### Flujo 1 — Onboarding y navegación principal

| # | Pantalla | Estado | Notas |
|---|---|---|---|
| 01 | Splash | ❌ No existe | Crear `SplashScreen.kt` |
| 02 | Login / Bienvenida | 🟡 Funcional, sin diseño | Rediseñar con brand colors, ilustración, layout del README |
| 03 | Nueva contraseña | ❌ No existe | Pantalla simple, baja prioridad |
| 04 | Onboarding (notificaciones) | ❌ No existe | Baja prioridad |
| 05 | Home | 🟡 Datos ok, sin diseño | Rediseñar: header, credencial, grid 2×2, próximo turno, tab bar custom |
| 06 | Atención (hub) | 🔴 Stub vacío | Crear lista con cards según README §06 |
| 07 | Trámites (hub) | 🔴 Stub vacío | Crear lista con cards según README §07 |
| 08 | Perfil | 🔴 Stub vacío | Crear con avatar hero, menú, cerrar sesión |
| 09 | Ayuda | 🔴 Stub vacío | Crear con chat, FAQ acordeón, segmented Teléfonos/Sucursales |
| 09b | Ayuda → Sucursales | ❌ No existe | Sub-vista de Ayuda |

### Flujo 2 — Sacar Turno

| # | Pantalla | Estado | Notas |
|---|---|---|---|
| 10 | ¿Para quién? | ❌ No existe | Primer paso del flujo |
| 11 | Buscador de especialidad | ❌ No existe | Segundo paso |
| 12 | Resultados (Turnos / Cartilla) | ❌ No existe | Tabs sticky, cards de turno |
| 12b | Resultados → Cartilla | ❌ No existe | Sub-tab de Resultados |
| 13 | Confirmar turno | ❌ No existe | Cuarto paso, step indicator 4/4 |
| 14 | Turno confirmado | ❌ No existe | Pantalla de éxito con animación |

---

## Sistema de diseño — estado de implementación

| Archivo | Estado | Tarea |
|---|---|---|
| `ui/theme/Color.kt` | 🔴 Colores del template (Purple) | Reescribir con todos los tokens del README |
| `ui/theme/Theme.kt` | 🔴 Dynamic Color activo | Deshabilitar dynamic color, aplicar SaludifyColorScheme |
| `ui/theme/Type.kt` | 🔴 Default Material 3 | Configurar DM Sans (Google Fonts) con escala del README |
| `ui/theme/Shape.kt` | ❌ No existe | Crear con radios: card-lg 17dp, card 14dp, button 14dp, badge 99dp |
| `ui/theme/Spacing.kt` | ❌ No existe | Opcional — constantes de padding frecuentes |

---

## Decisiones de arquitectura para el mock

- **Sin ViewModels para pantallas estáticas.** Datos hardcodeados desde `MockData`.
- **ViewModel solo si hay estado interactivo real** (ej: campo de búsqueda, accordion FAQ, tabs). Va en la misma carpeta que su Screen.
- **NavGraph se extiende** con las rutas del flujo Sacar Turno como nested navigation.

---

## Orden de implementación sugerido

1. **Sistema de diseño** (`Color.kt`, `Theme.kt`, `Type.kt`, `Shape.kt`) — base de todo
2. **SplashScreen** — fácil de hacer bien con un gradiente
3. **LoginScreen** — rediseño UI
4. **HomeScreen** — pantalla más compleja del flujo 1, pero la más visible
5. **BottomBar custom** — pill animado, íconos Material Symbols
6. **AttentionScreen, TrámitesScreen** — mismo componente reutilizable
7. **ProfileScreen** — avatar hero + menú
8. **HelpScreen** — acordeón + segmented
9. **Flujo Sacar Turno** (4 pantallas) — el más elaborado, dejarlo para el final

---

## Credenciales mock (para demo)

| DNI | Contraseña |
|---|---|
| 12345678 | 1234 |
| 87654321 | abcd |

---

## Progreso general

```
Sistema de diseño    [ ] [ ] [ ] [ ]   0/4 archivos
Flujo 1 (Onboarding) [x] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]   1/9 (solo nav funciona)
Flujo 2 (Turno)      [ ] [ ] [ ] [ ] [ ]   0/5
```

> Última actualización: 2026-06-23
