# Handoff — Estado actual del proyecto Saludify

> Objetivo: mock de alta fidelidad para demo en dispositivo real (Android Studio).
> No es una app de producción. Hardcodear datos está bien.
> Fuente de verdad de diseño: `README.md` (tokens, pantallas, interacciones).

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
│       ├── onboarding/    OnboardingScreen.kt  ← PRÓXIMA A CREAR
│       ├── login/         LoginScreen.kt + LoginViewModel.kt
│       ├── home/          HomeScreen.kt
│       ├── attention/     AttentionScreen.kt
│       ├── procedures/    ProceduresScreen.kt
│       ├── help/          HelpScreen.kt
│       ├── profile/       ProfileScreen.kt
│       └── main/          MainScreen.kt
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
  └── "Quiero afiliarme"  → (sin diseño, SIN IMPLEMENTAR — botón deshabilitado o toast)
```

**Ruta en NavGraph:**  
`Onboarding` (startDestination) → `Login` → `Main`

---

## Estado de pantallas

### Flujo 1 — Onboarding y navegación principal

| # | Pantalla | Estado | Notas |
|---|---|---|---|
| — | OnboardingScreen | ❌ Por crear | Bienvenida con 2 CTAs: "Soy afiliado" → Login, "Quiero afiliarme" → deshabilitado |
| — | LoginScreen | 🟡 Funcional, sin diseño | Rediseñar: "Ingresa a tu cuenta", campo email + contraseña, CTA |
| 05 | HomeScreen | 🟡 Datos ok, sin diseño | Header, credencial, grid 2×2, próximo turno, tab bar custom |
| 06 | AttentionScreen | 🔴 Stub vacío | Lista de cards según README §06 |
| 07 | ProceduresScreen | 🔴 Stub vacío | Lista de cards según README §07 |
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

---

## Spec de OnboardingScreen (próxima pantalla)

Diseño libre (no está en el README), basado en el patrón de la app:

- **Fondo:** blanco (`BackgroundSurface`)
- **Hero superior (~40%):** `Box` con `Brush.linearGradient(BrandPrimaryDark → BrandPrimary)`, logo "Saludify" centrado en blanco, tagline abajo
- **Título:** "Bienvenido a Saludify" — `displayMedium` (26sp Bold)
- **Subtítulo:** "Tu obra social en tu bolsillo" — `bodyLarge` (14sp), `TextMuted`
- **Botón primario:** "Soy afiliado" — full width, 52dp, `SaludifyRadius.button`, `BrandPrimary`
- **Botón secundario:** "Quiero afiliarme" — `TextButton` o outlined, `BrandPrimary`, navega a nada (Toast "Próximamente" o no-op)
- **Navegación:** `onAfiliadoClick: () -> Unit` como parámetro del Composable

---

## Spec de LoginScreen (pantalla siguiente)

Del README §02 + decisiones tomadas:

- **Título:** "Ingresa a tu cuenta" — `displayMedium` (26sp Bold)
- **Subtítulo:** `bodyLarge` (14sp), `TextMuted`
- **Campo email:** label separado encima, `OutlinedTextField` con `shape = SaludifyRadius.card`, borde `BorderDefault` en idle, `BrandPrimary` en foco
- **Campo contraseña:** igual al de email, con `PasswordVisualTransformation`
- **Auth:** por email (`john.doe@email.com` / `1234`) — actualizar `MockData.autenticar()` y `LoginViewModel`
- **Botón CTA:** "Ingresar" — full width, 52dp, `SaludifyRadius.button`
- **Link:** "Olvidé mi contraseña" — `TextButton`, `BrandPrimary`, 13sp, no-op por ahora
- **Error:** texto rojo debajo del botón, ya manejado en `LoginViewModel`

---

## Credenciales mock (para demo)

| Email | Contraseña |
|---|---|
| john.doe@email.com | 1234 |

---

## Progreso general

```
Sistema de diseño    [✅][✅][✅][✅]   4/4
Flujo 1              [ ][ ][ ][ ][ ][ ][ ][ ]   0/8 pantallas con diseño
Flujo 2              [ ][ ][ ][ ][ ]   0/5
```

> Última actualización: 2026-06-24
