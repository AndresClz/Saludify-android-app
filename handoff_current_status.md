# Handoff — Estado actual del proyecto Saludify

> Objetivo: mock de alta fidelidad para demo en dispositivo real (Android Studio).
> No es una app de producción. Hardcodear datos está bien.
> **Fuente de verdad de diseño: `docs/Saludify_Flujo_completo (standalone)_updated.html`** — prevalece sobre este doc y sobre README.md.
> El HTML es un bundle gzip+base64. Para extraer un frame: decodificar el manifest con Python, buscar por label uppercase (ej. `"06 · ATENCIÓN"`).

---

## Arquitectura actual

```
com.example.saludify/
├── domain/model/          Turno, Usuario, Especialidad, Medico, ObraSocial
├── data/
│   └── MockData.kt        Único punto de datos + autenticar()
├── presentation/
│   ├── components/        BottomBar.kt  ✅ custom (pill activo)
│   ├── navigation/        NavGraph.kt, Route.kt
│   └── screens/
│       ├── onboarding/    OnboardingScreen.kt  ✅
│       ├── login/         LoginScreen.kt + LoginViewModel.kt  ✅
│       ├── home/          HomeScreen.kt  ✅
│       ├── attention/     AttentionScreen.kt  ✅
│       ├── procedures/    ProceduresScreen.kt  ✅
│       ├── help/          HelpScreen.kt  ✅
│       ├── profile/       ProfileScreen.kt  ✅
│       ├── main/          MainScreen.kt  (Scaffold + BottomBar)
│       ├── sacaturno/     SacarTurnoScreen.kt  ✅ (scaffold compartido del flujo)
│       ├── forwhom/       ForWhomScreen.kt  ✅
│       ├── search/        SearchScreen.kt  ✅
│       ├── results/       ResultsScreen.kt  ✅
│       ├── confirm/       ConfirmScreen.kt  ✅
│       └── confirmed/     ConfirmedScreen.kt  ✅
└── ui/theme/              Color.kt, Theme.kt, Type.kt, Shape.kt
```

**Reglas:**
- ViewModel solo si hay estado interactivo. Actualmente solo `LoginViewModel`.
- Datos hardcodeados siempre en `MockData.kt`, nunca inline.
- ViewModel va en la misma carpeta que su Screen.

---

## Flujo de navegación

```
OnboardingScreen → LoginScreen → MainScreen (Scaffold con BottomBar)
                                     ├── HomeScreen        (tab: Inicio)
                                     ├── AttentionScreen   (tab: Atención)
                                     ├── ProceduresScreen  (tab: Trámites)
                                     ├── HelpScreen        (tab: Ayuda)
                                     └── ProfileScreen     (tab: Perfil)

MainScreen → SacarTurnoScreen (scaffold con header+stepper fijos)
                 ├── ForWhomScreen   (step 0)
                 ├── SearchScreen    (step 1)
                 ├── ResultsScreen   (step 2)
                 └── ConfirmScreen   (step 3)
             → ConfirmedScreen       (ruta NavGraph independiente)
```

**Transiciones NavGraph:** slide horizontal estilo iOS (push entra por derecha, pop sale por derecha).
**Tabs del BottomBar:** `AnimatedContent` con slide direccional (izquierda/derecha según índice del tab), 280ms.
**Flujo Sacar Turno:** `SacarTurnoScreen` maneja pasos 0–3 con `AnimatedContent` (mismo slide direccional). Header y stepper son fijos (fuera del AnimatedContent). El stepper anima el color de cada segmento con `tween(300)` al avanzar/retroceder.
**ConfirmedScreen:** ruta NavGraph separada con transición `fadeIn/fadeOut` (no slide), apropiada para pantalla de éxito.
**Segmented controls:** `AnimatedContent` con slide direccional en HelpScreen (Teléfonos↔Sucursales) y ResultsScreen (Turnos↔Cartilla), 260ms.

---

## Estado de pantallas

### Flujo 1 — Navegación principal

| # | Pantalla | Estado | Notas |
|---|---|---|---|
| 01 | SplashScreen | ❌ No existe | Prioridad baja |
| 02 | OnboardingScreen | ✅ Implementada | Ícono "Soy afiliado" → `Icons.Outlined.Person`; ambas cards usan `BrandPrimarySurface`; `statusBarsPadding()` aplicado |
| 03 | LoginScreen | ✅ Implementada | DNI + contraseña; `statusBarsPadding()` aplicado |
| 05 | HomeScreen | ✅ Implementada (rediseño v2) | Ver detalle de secciones abajo |
| 06 | AttentionScreen | ✅ Implementada (mergeada PR #5, #7) | Hero gradiente + 5 items con highlight al presionar; FAB movido a MainScreen |
| 07 | ProceduresScreen | ✅ Implementada (mergeada PR #8, fix PR #9) | Hero blanco con 3 chips de estado + 5 items con highlight al presionar; FAB en MainScreen |
| 08 | ProfileScreen | ✅ Implementada (mergeada PR #6, #7) | Hero gradiente + avatar 64dp + LogoutCard con highlight; FAB movido a MainScreen |
| 09 | HelpScreen | ✅ Implementada | Chat IA + buscador + FAQ accordion + segmented Teléfonos/Sucursales animado; botones "Llamar" y "Cómo llegar" funcionales |

### Flujo 2 — Sacar Turno

| # | Pantalla | Estado |
|---|---|---|
| 10 | ForWhomScreen — ¿Para quién es el turno? | ✅ Implementada |
| 11 | SearchScreen — Buscador de especialidad | ✅ Implementada |
| 12 | ResultsScreen — Resultados (tab Turnos + tab Cartilla) | ✅ Implementada |
| 13 | ConfirmScreen — Confirmar turno | ✅ Implementada |
| 14 | ConfirmedScreen — Turno confirmado | ✅ Implementada |

---

## Sistema de diseño

| Archivo | Estado |
|---|---|
| `ui/theme/Color.kt` | ✅ 35 tokens |
| `ui/theme/Theme.kt` | ✅ SaludifyTheme, light only |
| `ui/theme/Type.kt` | ✅ DM Sans variable, 13 estilos M3, pesos via `FontVariation.Settings` |
| `ui/theme/Shape.kt` | ✅ SaludifyShapes + SaludifyRadius |
| `res/font/dm_sans.ttf` | ✅ Variable font (eje `wght` 100–1000) |
| `res/drawable/ic_logo.xml` | ✅ Vector Drawable — logo real de Saludify, tintado con `BrandPrimary` |
| `material-icons-extended` | ✅ En build.gradle.kts vía BOM |

**Nota `Type.kt`:** requiere `@file:OptIn(ExperimentalTextApi::class)`. Cada peso usa `FontVariation.Settings(FontVariation.weight(N))` para forzar el eje `wght` explícitamente — sin esto el OS renderiza todo en Regular (defecto del archivo).

**Credenciales mock:** DNI `12345678` / contraseña `1234` → usuario "María González".

---

## HomeScreen v2 — detalle de cambios respecto al HTML actualizado

| Sección | Implementación |
|---|---|
| Header | `ic_logo` 24dp tintado `BrandPrimary` + "Saludify" 14sp Bold · derecha: pill "Ver token" + bell 34dp + avatar 34dp · `statusBarsPadding()` en el Row |
| Credenciales | `HorizontalPager` (2 páginas) con `contentPadding(start=18,end=44)` y `pageSpacing=12` — peek nativo · dots reactivos a `pagerState.currentPage` |
| Próximo turno | Barra header `BrandPrimary` + dot verde `#4ADE80` + "en 2 días" · body: avatar "RS" 42dp + doctor info + pill "Detalle" · borde `BrandPrimaryBorder` |
| Accesos rápidos | Cards horizontales (Row), todos `BrandPrimarySurface`/`BrandPrimary`, gap 8dp, radius `SaludifyRadius.card` (14dp) |
| Emergencias | Sección nueva: card `SemanticDangerSurface` + "0800 333 4444" + botón "Llamar" |
| FAB Gemini | `GeminiFab` overlay `Alignment.BottomEnd`, gradiente `#4285F4→#7C3AED→#DB2777`, `Icons.Filled.AutoAwesome` |
| Token | Eliminado del body; accesible vía pill "Ver token" en header |
| Safe area | `MainScreen` aplica solo `calculateBottomPadding()` del Scaffold; cada screen maneja su propio top inset con `statusBarsPadding()` |

**Convención para tabs futuras (06, 07, 08, 09):** el header sticky de cada pantalla debe incluir `.statusBarsPadding()` antes del padding de contenido, igual que `HomeHeader`.

---

## Patrón de pantallas con tab bar (06, 07, 08, 09)

Todas las pantallas del tab bar comparten la misma estructura:

```
Column {
    Header (sticky)           // Logo badge "S" 24dp + título 17sp Bold + acción derecha
    LazyColumn / Column {     // contenido scrolleable
        HeroSection           // card destacada (gradiente o blanca)
        ListItems (gap 10dp)  // cards blancas radius 17dp, padding 16/18dp
        Spacer(weight 1f)
    }
    // FAB Gemini posicionado absolute bottom 74dp right 16dp
}
```

**Header común:** `Image(painterResource(R.drawable.ic_logo), Modifier.size(24.dp), colorFilter = ColorFilter.tint(BrandPrimary))` + título 17sp Bold `TextDefault` + placeholder 24dp derecha (o ícono).

**FAB Gemini (aparece en 06, 07, 08):** 52dp circle, gradient `#4285f4 → #7c3aed → #db2777`, estrella 4 puntas blanca, shadow `0 4px 18px rgba(124,58,237,.45)`, `position: absolute; bottom: 74dp; right: 16dp; zIndex: 20`.

**Card de item tipo menú:** `Row`, card blanca radius 17dp, padding 16/18dp, `BorderDefault`, shadow `0 1px 4px rgba(0,0,0,.04)`. Estructura: ícono 42×42dp (radius 13dp) + texto Column (título 14–15sp SemiBold + subtítulo 12sp `TextPlaceholder`) + badge opcional + chevron derecho.

---

## Spec: Frame 06 · Atención

**Fondo:** `BackgroundApp`. **Header:** título "Atención", sin acción derecha.

**Hero card** (gradiente `135deg, #0f3d8a → #1a5fce → #2e7de0`, radius 20dp, padding 18/20dp, overflow hidden):
- 2 círculos decorativos semitransparentes (igual patrón HomeScreen)
- "Buenos días, María" 12sp `rgba(255,255,255,.65)`
- "¿Qué necesitás hoy?" 18sp Bold white, letterSpacing -0.3sp
- 2 chips `rgba(255,255,255,.18)` radius 10dp, gap 8dp:
  - "● Turno online" — dot `#4ade80`
  - "● Urgencias" — dot `#f87171`

**Lista de items** (padding 20/18dp, gap 10dp):

| Ícono bg | Label | Subtítulo | Badge |
|---|---|---|---|
| `BrandPrimarySurface` | Cartilla médica | Buscar médico o especialidad | — |
| `BrandPrimarySurface` | Mi cobertura | Plan y prestaciones incluidas | "Premium" `BrandPrimarySurface` |
| `SemanticSuccessSurface` | Sacar turno | Reservar una nueva consulta | — |
| `SemanticInfoSurface` (`#f0f9ff`) | Mis turnos | Próximos · cancelar | "1" `SemanticWarningSurfaceStrong` |
| `SemanticPurpleSurface` | Historial de consultas | Mis atenciones anteriores | — |

---

## Spec: Frame 07 · Trámites

**Header:** título "Trámites", sin acción derecha.

**Hero card** (blanca, radius 20dp, borde `BorderDefault`, shadow `0 2px 10px rgba(0,0,0,.05)`):
- "Estado de tus trámites" 12sp `TextPlaceholder` Medium
- 3 chips lado a lado (gap 10dp, cada uno `flex: 1`):
  - "2 / En proceso" — bg `BrandPrimarySurfaceStrong` (`#f0f5ff`), borde `BrandPrimaryBorder`, número 22sp ExtraBold `BrandPrimary`
  - "1 / Por vencer" — bg `SemanticWarningSurface`, borde `SemanticWarningBorder`, número `SemanticWarning`
  - "5 / Resueltos" — bg `SemanticSuccessSurface`, borde `#bbf7d0`, número `SemanticSuccess`

**Lista de items** (padding 20/18dp, gap 10dp):

| Ícono bg | Label | Subtítulo | Badge |
|---|---|---|---|
| `BrandPrimarySurface` | Autorizaciones | Solicitar o consultar estado | "2" `SemanticWarningSurfaceStrong` naranja |
| `BrandPrimarySurface` | Reintegros | Cargar gastos para reintegro | — |
| `BrandPrimarySurface` | Presupuestos | Cotizaciones de prácticas | — |
| `BrandPrimarySurface` | Facturas | Comprobantes de pago | — |
| `BrandPrimarySurface` | Pagos | Cuotas y medios de pago | "Vence hoy" `SemanticDangerSurfaceStrong` rojo |

---

## Spec: Frame 08 · Perfil

**Header:** "Mi perfil" + ícono settings 34dp `BackgroundSubtle` circle (derecha).

**Hero card** (gradiente `135deg #0f3d8a → #1a5fce → #2e7de0`, radius 20dp, padding 20dp, overflow hidden, margin 14/18dp):
- Avatar 64dp circle `rgba(255,255,255,.22)`, borde `rgba(255,255,255,.4)`, "M" 26sp Bold white
- "María González" 18sp Bold white, letterSpacing -0.3sp
- Badges: "Plan Premium" `rgba(255,255,255,.22)` + "DNI 12.345.678" `rgba(255,255,255,.12)`
- Footer row (border-top `rgba(255,255,255,.15)`, margin-top 14dp, gap 10dp):
  - "AFILIADO DESDE / Enero 2018" — 10sp label + 13sp SemiBold white
  - "PRÓXIMA CUOTA / Vence 30 Jun" — valor en `#fde68a` (amarillo)
  - "GRUPO / 3 integrantes" — white

**Lista items** (padding 14/18dp, gap 10dp):

| Ícono bg | Label | Subtítulo | Badge |
|---|---|---|---|
| `BrandPrimarySurface` | Mis datos | Información personal | — |
| `BrandPrimarySurface` | Gestionar Plan | Cobertura y beneficios | — |
| `BrandPrimarySurface` | Grupo familiar | Familiares afiliados | "3" `BrandPrimarySurface` azul |
| `BrandPrimarySurface` | Historial de consultas | Mis atenciones anteriores | — |

**Cerrar sesión** (padding 18/18dp, separado):
Card `#fff5f5`, borde `#fecaca`, ícono bg `#fee2e2` (salida derecha en rojo), "Cerrar sesión" 14sp SemiBold `SemanticDanger`. No tiene chevron ni subtítulo.

---

## Spec: Frame 09 · Ayuda

**Header:** "Ayuda", sin acción. Debajo del header, buscador sticky:
- Card blanca, borde 1.5dp `BorderDefault`, radius 14dp, padding 12/16dp, shadow leve
- Ícono lupa `TextPlaceholder` + placeholder "Buscar en ayuda…" 14sp `TextPlaceholder`

**Secciones** (padding 20/18dp, gap 26dp entre secciones):

**§ Atención inmediata** — header de sección: label 10sp uppercase `#8896aa` + línea `BorderStrong`:
- Chat IA card (gradiente horizontal `138deg #0f3d8a → #1a5fce → #2e7de0`, radius 18dp, padding 18/20dp, layout horizontal):
  - Ícono chat 46×46dp `rgba(255,255,255,.16)` radius 14dp + íconos chat bubble blancos
  - Texto: título + subtítulo + CTA "Consultar ahora"
  - Shadow `0 8px 24px rgba(26,95,206,.26)`

**§ Preguntas frecuentes** — header de sección + lista de items acordeón:
- Cada FAQ: card blanca, radio 14dp, padding 16/18dp, "pregunta" 14sp SemiBold + chevron. Al expandir muestra respuesta 13sp `TextMuted`.

**§ Contacto / Teléfonos / Sucursales** — segmented control:
- Container `BackgroundSegmented` radius 12dp, 2 opciones: "Teléfonos" (activo) / "Sucursales"
- Contenido del tab activo: lista de cards con íconos y datos de contacto.

---

## Spec: Frame 11 · Buscador de especialidad

**Sin bottom tab bar** (es parte del flujo Sacar Turno, no del MainScreen).

**Header sticky** (bg white, padding 12/18dp):
- Back arrow izquierda (chevron 9dp) + "Sacar turno · María González" 15sp SemiBold `TextSecondary` centrado + placeholder 28dp derecha
- Progress stepper: 4 segmentos height 3dp radius 99dp, gap 4dp — **2 llenos** `BrandPrimary` / 2 vacíos `#e5e7eb`

**Cuerpo** (padding 22/18dp):
- "¿Qué especialidad\no médico buscás?" 22sp Bold letterSpacing -0.4sp
- Search bar activo (borde 2dp `BrandPrimary`, shadow `0 4px 14px rgba(26,95,206,.1)`): ícono lupa `BrandPrimary` + texto "Cardiología" 14sp SemiBold `BrandPrimary` + chevron down `BrandPrimaryBorder`
- Filtros (chips, gap 8dp): "Todos" (activo, bg `BrandPrimary`, blanco) · "Online" · "Presencial" (bg `BackgroundSubtle`)
- Filtro ubicación: dot `TextPlaceholder` + "Buenos Aires, CABA" 13sp Medium + "Cambiar" `BrandPrimary`
- **Especialidades frecuentes** (label 11sp uppercase + grid 2×2, gap 9dp):
  - Chip activo: bg `BrandPrimary`, texto blanco 14sp SemiBold
  - Chips inactivos: bg white, borde 1dp `BorderDefault`, texto 14sp `TextSecondary`
  - Items: Cardiología (activo) · Clínica Médica · Dermatología · Pediatría · …

---

## Spec: Frame 12 · Resultados

**Header sticky:**
- Back arrow + "Resultados" 15sp SemiBold centrado + filter icon derecha (3 líneas de distinto ancho)
- Progress stepper: **3 de 4 llenos** `BrandPrimary`

**Segmented control** (padding 12/18dp):
- Container `BackgroundSegmented` (`#e8edf5`) radius 12dp, padding 3dp
- "Turnos" (activo): bg white, shadow, + badge counter "12" `BrandPrimary` pill
- "Cartilla": sin bg, texto `TextMuted`

**Controles** (padding 10/18dp): "Ordenar" `BrandPrimary` izquierda + "12 disponibles" 13sp SemiBold `TextSecondary` derecha

**Cards de resultado** (gap 10dp, radius 17dp, borde `BorderDefault`, overflow hidden, shadow leve). Cada card:
- Fila superior: nombre 15sp Bold `TextDefault` + especialidad 12sp `TextMuted` | badge modalidad:
  - Presencial: bg `BrandPrimarySurface`, texto `BrandPrimary`
  - Online: bg `SemanticSuccessSurfaceStrong`, texto `SemanticSuccess`
- Fila info: ícono location + dirección 12sp `TextMuted`
- Footer (border-top `BackgroundSubtle`, padding-top 12dp): disponibilidad card + botón "Reservar":
  - Slot disponible (bg `SemanticSuccessSurface` si es online, `BackgroundSubtle` si presencial): "Próximo turno / Lun 23 Jun · 10:30 hs" 13sp Bold
  - "Reservar": bg `BrandPrimary`, radius 11dp, padding 10/18dp, blanco 13sp SemiBold

**Médicos de ejemplo:**
1. Dr. Roberto Silva · Presencial · Hospital Central · Lun 23 Jun 10:30 hs
2. Dra. Ana Martínez · Presencial · Sanatorio Güemes · Mar 24 Jun 9:00 hs
3. Dr. Carlos López · Online · Videoconsulta

---

## Spec: Frame 13 · Confirmar turno

**Header sticky:**
- Back + "Confirmar turno" 15sp SemiBold centrado
- Progress stepper: **4 de 4 llenos** + `HorizontalDivider` abajo

**Cuerpo** (padding 20/18dp, gap 14dp):

"Resumen del turno" 22sp Bold letterSpacing -0.4sp

**Doctor card** (radius 18dp, borde `BorderDefault`, shadow `0 6px 20px rgba(26,95,206,.08)`, padding 18dp, layout horizontal):
- Avatar 52dp `BrandPrimary` circle, iniciales "RS" 20sp Bold white
- Dr. Roberto Silva 16sp Bold + Cardiología 13sp `TextMuted` + rating ★★★★☆ 4.8 · 124 opiniones (estrellas `SemanticWarningIcon`, vacía `BorderStrong`)

**Detalles card** (radius 18dp, borde `BorderDefault`, shadow igual, sin padding externo — divisores internos `BackgroundSubtle`):
- **Fecha y hora:** badge Jun/23 `BrandPrimarySurface` radius 11dp + "Fecha y hora" 11sp `TextPlaceholder` + "Lun 23 Jun · 10:30 hs" 14sp SemiBold `TextDefault`
- **Lugar:** ícono location + "Hospital Central · Av. Santa Fe 1234" 13sp `TextSecondary`
- **Modalidad:** check verde + "Presencial" 13sp `TextSecondary`
- **Cobertura:** shield + "Saludify · Plan Premium · Sin costo adicional" 13sp `TextSecondary`

**CTA:** "Confirmar turno" full width 52dp `BrandPrimary` radius `SaludifyRadius.button`

---

## Spec: Frame 14 · Turno confirmado

**Layout:** Column centrado verticalmente, padding horizontal 18dp, fondo `BackgroundApp`.

**Success icon:**
- Outer ring: 12dp padding bg `SemanticSuccessSurface` (`#f0fdf4`)
- Inner circle: 88dp `SemanticSuccessSurfaceStrong` (`#dcfce7`), shadow `0 0 0 12px #f0fdf4`
- Checkmark: `border-left + border-bottom` 5dp `SemanticSuccess`, rotado -45deg

**Textos:**
- "¡Turno confirmado!" 26sp Bold `TextDefault` letterSpacing -0.5sp, text-center, lineHeight 1.2
- "Te enviamos la confirmación\npor notificación y email" 14sp `TextMuted` centrado, lineHeight 1.6

**Resumen card** (radius 20dp, borde `BorderDefault`, shadow `0 6px 20px rgba(26,95,206,.08)`, padding 20dp, gap 14dp):
- Row: avatar 42dp `BrandPrimary` "RS" + "Dr. Roberto Silva" 15sp Bold / "Cardiología" 12sp `TextMuted`
- `HorizontalDivider` `BorderDefault`
- Row 2 columnas: "FECHA / Lun 23 Jun" | "HORA / 10:30 hs" — label 10sp uppercase `TextPlaceholder` + valor 13sp SemiBold `TextDefault`
- "LUGAR / Hospital Central · Av. Santa Fe 1234" — mismo estilo

**Acciones:**
- "Agregar al calendario": card blanca, borde `BorderDefault`, radius 14dp, padding 14dp, ícono calendario `TextSecondary` + texto 14sp SemiBold `TextSecondary`
- "Ver mis turnos": full width `BrandPrimary`, radius 14dp, padding 17dp, 15sp SemiBold white
- "Volver al inicio": 13sp Medium `TextMuted` centrado

---

## Branch activo: `main` (PR #9 pendiente de merge)

PR #8 `feature/procedure-screen` mergeado a `main` el 2026-07-01. Implementa `ProceduresScreen`.

PR #9 `fix/procedures-screen` abierto — corrige bugs y desviaciones de spec del PR #8:
1. `collectIsPressedAsState` → `pointerInput + detectTapGestures` en `ProceduresMenuCard`
2. Elimina `GeminiFab` local duplicado
3. Hero card: título 12sp, sombra 2dp, altura dinámica, borde `BorderDefault`
4. Chips de estado: colores correctos por spec; número 22sp ExtraBold; sin `HorizontalDivider` interno
5. Íconos semánticos por item (`AssignmentTurnedIn`, `AccountBalance`, `Calculate`, `Receipt`, `CreditCard`)
6. Subtítulos y badges corregidos al texto exacto de la spec
7. `StatusChip`, `ProceduresMenuCard`, `ProceduresHeader` pasados a `private`
8. `.gitignore`: agrega `/.idea/inspectionProfiles` y `/.idea/misc.xml`; destrackea `misc.xml`

PR #7 `feature/animations` mergeado a `main` el 2026-07-01. Incluye:
1. Slide horizontal estilo iOS en todas las rutas del NavGraph
2. `ConfirmedScreen` con transición `fadeIn/fadeOut` propia
3. `AnimatedContent` con slide direccional en tabs del BottomBar (280ms)
4. Header y stepper fijos en flujo Sacar Turno; slide direccional en pasos
5. Slide direccional en segmented controls de HelpScreen y ResultsScreen
6. `DraggableGeminiFab` en `components/GeminiFab.kt` — centralizado en MainScreen, snap al borde con spring, ausente en flujo Sacar Turno
7. Highlight al presionar con `pointerInput + detectTapGestures + animateColorAsState`:
   - `QuickAccessCard` (HomeScreen) — fondo anima a `BrandPrimarySurface`
   - `AttentionMenuCard` (AttentionScreen) — fondo + borde animados
   - `ProfileMenuCard` + `LogoutCard` (ProfileScreen) — fondo + borde animados
   - `FaqCard` (HelpScreen) — `expandVertically/shrinkVertically` + rotación del chevron
   - `ProceduresMenuCard` (ProceduresScreen) — fondo + borde animados
8. Patrón consolidado: `collectIsPressedAsState` NO funciona en `LazyColumn` ni `verticalScroll`; usar siempre `pointerInput + detectTapGestures` con `try/finally`

**Próximo trabajo:** implementar SplashScreen (Frame 01) y preparar demo.

---

## FAB Gemini — arquitectura actual

- Vive en `presentation/components/GeminiFab.kt` como `DraggableGeminiFab()`
- Se instancia una sola vez en `MainScreen`, fuera del `AnimatedContent` de tabs
- Posición inicial: bottom-end con margen 16dp
- Al soltar: snap animado con spring al borde izquierdo o derecho más cercano
- Ausente automáticamente en el flujo Sacar Turno (rutas separadas del NavGraph)

---

## Patrón de highlight al presionar (consolidado)

**No usar** `collectIsPressedAsState` + `MutableInteractionSource` — no cancela correctamente en `LazyColumn` ni `verticalScroll`, dejando el highlight pegado.

**Usar siempre:**
```kotlin
var pressed by remember { mutableStateOf(false) }
val bgColor by animateColorAsState(
    targetValue = if (pressed) BrandPrimarySurface else BackgroundSurface,
    animationSpec = tween(durationMillis = if (pressed) 40 else 200),
    label = ""
)
Modifier.pointerInput(onClick) {
    detectTapGestures(
        onPress = {
            pressed = true
            try { tryAwaitRelease() } finally { pressed = false }
        },
        onTap = { onClick() }
    )
}
```
El `try/finally` garantiza que `pressed` siempre vuelve a `false` aunque el gesto sea cancelado por scroll.

---

## Progreso general

```
Sistema de diseño    [✅][✅][✅][✅]              4/4
Flujo 1              [✅][✅][✅][✅][✅][✅][✅]   7/7 mergeadas
Flujo 2              [✅][✅][✅][✅][✅]           5/5
Animaciones + UI     [✅][✅][✅][✅][✅][✅][✅]   mergeado en main (PR #7)
```

> Última actualización: 2026-07-01 (PR #9 mergeado · logo SVG importado como `ic_logo.xml` · badge "S" reemplazado por logo real en las 6 pantallas · **próximo: SplashScreen**)
