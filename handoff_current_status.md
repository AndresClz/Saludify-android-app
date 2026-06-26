# Handoff — Estado actual del proyecto Saludify

> Objetivo: mock de alta fidelidad para demo en dispositivo real (Android Studio).
> No es una app de producción. Hardcodear datos está bien.
> **Fuente de verdad de diseño: `docs/saludify-standalone.html`** — prevalece sobre este doc y sobre README.md.

---

## Arquitectura actual (post-refactor)

Estructura simplificada para mock. Sin capas de dominio ni repositorios.

```
com.example.saludify/
├── domain/model/          Data classes (Turno, Usuario, Especialidad, Medico, ObraSocial)
├── data/
│   └── MockData.kt        Único punto de datos: usuarios, turnos, especialidades + autenticar()
├── presentation/
│   ├── components/        BottomBar.kt
│   ├── navigation/        NavGraph.kt, Route.kt
│   └── screens/
│       ├── onboarding/    OnboardingScreen.kt  ✅
│       ├── login/         LoginScreen.kt + LoginViewModel.kt  ✅
│       ├── home/          HomeScreen.kt  🟡 (datos ok, sin diseño visual)
│       ├── attention/     AttentionScreen.kt  🔴 stub
│       ├── procedures/    ProceduresScreen.kt  🔴 stub
│       ├── help/          HelpScreen.kt  🔴 stub
│       ├── profile/       ProfileScreen.kt  🔴 stub
│       └── main/          MainScreen.kt  (contenedor del tab bar)
└── ui/theme/              Color.kt, Theme.kt, Type.kt, Shape.kt
```

**Reglas:**
- ViewModel solo si hay estado interactivo (campos, errores). Actualmente solo `LoginViewModel`.
- Si una pantalla necesita ViewModel, va en la misma carpeta que su Screen.
- Datos hardcodeados siempre en `MockData.kt`, nunca inline en los Composables.

---

## Flujo de navegación definido

```
OnboardingScreen
  ├── "Soy afiliado"      → LoginScreen → MainScreen
  └── "Quiero afiliarme"  → no-op (onClick vacío)
```

**NavGraph:** `Onboarding` (startDestination) → `Login` → `Main`

**Transiciones:** slide horizontal estilo iOS implementado en `NavGraph.kt`:
- Push: nueva pantalla entra desde la derecha, la actual sale un tercio a la izquierda
- Pop: pantalla actual sale a la derecha, la anterior vuelve desde la izquierda

---

## Estado de pantallas

### Flujo 1 — Onboarding y navegación principal

| # | Pantalla | Estado | Notas |
|---|---|---|---|
| — | OnboardingScreen | ✅ Implementada | Cards "Soy afiliado" / "Quiero afiliarme", fondo plano, footer legal |
| — | LoginScreen | ✅ Implementada | DNI + contraseña, back arrow, toggle visibility, error state |
| 05 | HomeScreen | ✅ Implementada | Header sticky, credencial gradiente, token, próximo turno, accesos rápidos 2×2 |
| 06 | AttentionScreen | 🔴 Stub vacío | Lista de cards según HTML §06 |
| 07 | ProceduresScreen | 🔴 Stub vacío | Lista de cards según HTML §07 |
| 08 | ProfileScreen | 🔴 Stub vacío | Avatar hero, menú, cerrar sesión |
| 09 | HelpScreen | 🔴 Stub vacío | Chat IA hero, FAQ acordeón, segmented Teléfonos/Sucursales |
| 01 | SplashScreen | ❌ No existe | Gradiente + logo + tagline + barra de carga. Prioridad baja. |

### Flujo 2 — Sacar Turno (todo pendiente)

| # | Pantalla | Estado |
|---|---|---|
| 10 | ¿Para quién? | ❌ No existe |
| 11 | Buscador de especialidad | ❌ No existe |
| 12 | Resultados (Turnos / Cartilla) | ❌ No existe |
| 13 | Confirmar turno | ❌ No existe |
| 14 | Turno confirmado | ❌ No existe |

---

## Sistema de diseño — estado de implementación

| Archivo | Estado |
|---|---|
| `ui/theme/Color.kt` | ✅ Completo — 35 colores según tokens |
| `ui/theme/Theme.kt` | ✅ Completo — SaludifyTheme, light only, sin dynamic color |
| `ui/theme/Type.kt` | ✅ Completo — DM Sans variable font embebida, 13 estilos M3 |
| `ui/theme/Shape.kt` | ✅ Completo — SaludifyShapes (M3) + SaludifyRadius (tokens nombrados) |
| `res/font/dm_sans.ttf` | ✅ Variable font embebida (400–700) |

**Dependencias agregadas en esta sesión:**
- `androidx.compose.material:material-icons-extended` (versión via BOM, en `app/build.gradle.kts`)

---

## Spec de OnboardingScreen — extraída del HTML (frame 02 · Bienvenida)

- **Fondo:** `#f6f9ff` ≈ `BrandPrimarySurfaceStrong` — sin gradiente, pantalla plana
- **Padding:** top 30dp, horizontal 22dp, bottom 38dp
- **Logo row** (margin-bottom 48dp):
  - Badge 28×28dp, `BrandPrimary`, `RoundedCornerShape(8.dp)`, letra "S" blanca 12sp ExtraBold
  - Texto "Saludify" 17sp Bold, `BrandPrimary`, letterSpacing -0.3sp
- **Heading:** "Bienvenido/a" — 28sp Bold, `TextDefault`, lineHeight 1.2, letterSpacing -0.6sp, margin-bottom 10dp
- **Subtítulo:** "Gestioná tu cobertura médica desde el celular, sin filas ni llamados." — 14sp, `TextMuted`, lineHeight 1.6, margin-bottom 38dp
- **Dos cards** (gap 13dp): fondo blanco, `RoundedCornerShape(18.dp)`, borde 1.5dp `BorderDefault`, elevación 2dp
  - Card 1 "Soy afiliado": ícono círculo outline `BrandPrimary`, chevron `BrandPrimary`, subtítulo "Ingresá con tu DNI"
  - Card 2 "Quiero afiliarme": ícono `Icons.Filled.Add` `SemanticSuccess`, chevron `TextPlaceholder`, subtítulo "Conocé nuestros planes", onClick = no-op
- **Spacer(weight 1f)** + Footer legal con links `BrandPrimary` no-op

---

## Spec de LoginScreen — extraída del HTML (frame 03 · Login)

> Auth es por **DNI** (no email). `LoginViewModel` y `MockData.autenticar()` ya están correctos.

- **Fondo:** blanco. **Padding:** top 24dp, horizontal 22dp, bottom 38dp
- **Back arrow** arriba izquierda → `navController.popBackStack()`
- **Heading:** "Ingresá a\ntu cuenta" — 28sp Bold, letterSpacing -0.6sp
- **Subtítulo:** "Usá tu DNI como nombre de usuario." — 14sp `TextMuted`
- **Campos** con `BasicTextField` custom (label uppercase encima, borde reactivo al foco):
  - DNI: placeholder "12 345 678", `KeyboardType.Number`, máx 11 dígitos
  - Contraseña: `PasswordVisualTransformation`, toggle ojo derecha, link "¿Olvidaste tu contraseña?" abajo-derecha no-op
- **Error:** `SemanticDanger` 13sp, aparece entre campos y CTA
- **CTA "Ingresar":** full width 52dp, `SaludifyRadius.button`, `BrandPrimary`

---

## Credenciales mock (para demo)

| DNI | Contraseña |
|---|---|
| 12345678 | 1234 |

---

## Progreso general

```
Sistema de diseño    [✅][✅][✅][✅]        4/4
Flujo 1              [✅][✅][✅][ ][ ][ ][ ][ ]   3/8 pantallas con diseño
Flujo 2              [ ][ ][ ][ ][ ]            0/5
```

---

## Spec de HomeScreen — extraída del HTML (frame 05 · Home)

- **Fondo:** `BackgroundApp` (`#f0f4fb`)
- **HomeHeader (sticky):**
  - Fondo blanco, padding 14dp/18dp, `HorizontalDivider` `BorderLight` abajo
  - Avatar 38dp `BrandPrimary` circle, inicial del nombre blanca 15sp Bold
  - "Buenos días," 11sp `TextPlaceholder` / "Nombre Apellido" 15sp Bold `TextDefault` letterSpacing -0.2sp
  - Bell icon 38dp `BackgroundSubtle` circle, badge rojo 8dp con borde blanco 12dp

- **CredencialesSection** (padding 20dp top):
  - Label "Tus credenciales" 13sp SemiBold `TextSecondary` + "Ver todas" 12sp `BrandPrimary`
  - Tarjeta gradiente `linearGradient(0,0 → ∞,∞)`: `BrandPrimaryDark 0%` → `BrandPrimary 52%` → `BrandPrimaryLight 100%`
  - `SaludifyRadius.panel` (20dp), `shadow(10.dp)`, 2 círculos decorativos semitransparentes
  - "Saludify" 15sp Bold + chip rectangular (32×22dp blanco semitransparente)
  - "0000 · 0000 · 1234" 15sp letterSpacing 0.2em
  - TITULAR / nombre 14sp SemiBold — PLAN / "Premium" 13sp SemiBold
  - 2 dots de carrusel (20×5dp activo / 5dp inactivo)

- **TokenSection** (padding 18dp top):
  - Card blanca `SaludifyRadius.cardLg` + `BorderDefault`
  - "842 619" 30sp Bold letterSpacing 0.24em + "Válido por 23:47 hs · Tocá para copiar"
  - Ícono refresh 42dp `BrandPrimarySurface` `SaludifyRadius.iconLg`

- **ProximosTurnosSection** (padding 18dp top):
  - Card: badge fecha 46dp `BrandPrimarySurface` (mes 9sp + día 20sp) + info médico
  - Chips redondeados `BackgroundSubtle`: "Lun · 10:30 hs" y "Hospital Central"

- **AccesosRapidosSection** (padding 18dp top):
  - 2 filas × 2 columnas, gap 12dp, `weight(1f)` por columna
  - Sacar turno (`BrandPrimarySurface`/`Add`) · Mis turnos (`SemanticOrangeSurface`/`CalendarToday`)
  - Reintegros (`SemanticSuccessSurface`/`ArrowDownward`) · Cartilla (`SemanticPurpleSurface`/`Apps`)

- **BottomBar (custom):** Reemplaza `NavigationBar` con `Row` manual
  - Activo: pill 44×26dp `BrandPrimarySurface` badge radius, ícono 16dp `BrandPrimary`, label 10sp SemiBold `BrandPrimary`
  - Inactivo: ícono 16dp `TextDisabled`, label 10sp Normal `TextPlaceholder`
  - Ícono de cada tab: Home / Add / Menu / Help / Person
  - `navigationBarsPadding()` para sistema de navegación

- **Cambios en MockData:** usuario "María González", `Turno` con campos `mes`, `dia`, `diaSemana`, `hora`, `lugar`, `medico`

> Última actualización: 2026-06-24
