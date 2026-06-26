# Handoff: Saludify — App Android

## Overview

Saludify es una app de obra social / salud para Android. El paquete @docs contiene los diseños de alta fidelidad de los flujos principales, junto con todos los tokens del sistema visual, listos para ser implementados en **Jetpack Compose**.

---

## Sobre los archivos de diseño

Los archivos HTML incluidos en este paquete son **prototipos de diseño de referencia**, creados para documentar look, layout y comportamiento. **No son código de producción ni deben copiarse directamente.** La tarea es recrear estos diseños en Jetpack Compose, respetando pixel a pixel colores, tipografía, espaciado e interacciones documentados aquí.

---

## Fidelidad

**Alta fidelidad (hifi).** Los prototipos incluyen colores exactos, tipografía final, espaciado preciso e interacciones detalladas. El desarrollador debe implementar el UI con fidelidad pixel-perfect usando Jetpack Compose + Material 3.

> **Stack recomendado:**
> - **UI:** Jetpack Compose (Material 3)
> - **Navegación:** Navigation Compose
> - **Tipografía:** DM Sans (Google Fonts)
> - **Tokens:** `saludify-tokens.json` → convertir a `Color.kt`, `Type.kt`, `Shape.kt`, `Spacing.kt`

---

## Archivos incluidos

| Archivo | Descripción |
|---|---|
| `saludify-prototype.html` | Prototipo completo (requiere servidor local o Designcompass) |
| `saludify-standalone.html` | Prototipo autocontenido — abrí directamente en el browser |
| `saludify-tokens.json` | Todos los tokens del sistema: colores, tipografía, radios, sombras, componentes |
| `README.md` | Este documento |

---

## Sistema de diseño

### Tipografía
- **Familia:** DM Sans (importar desde Google Fonts)
- Agregar en `res/font/` o via `downloadableFonts`

### Colores principales
```
brand.primary            #1a5fce   → Color de acción principal, botones CTA, tabs activos
brand.primary-dark       #0f3d8a   → Gradientes
brand.primary-surface    #e8effd   → Fondos de estado activo, badges
text.default             #111827   → Texto principal
text.muted               #6b7280   → Texto secundario
text.placeholder         #9ca3af   → Placeholders
background.app           #f0f4fb   → Fondo general de pantallas
background.surface       #ffffff   → Cards, headers
semantic.success         #16a34a   → Estado OK, turnos online
semantic.warning         #d97706   → Alertas, vencimientos
semantic.danger          #dc2626   → Errores, cerrar sesión
```

### Radios de borde
```
card-lg      17px   → Cards de lista principales (menú, médicos)
card         14px   → Cards de FAQ, teléfonos
button       14px   → Botones CTA primarios
badge        99px   → Badges pill
full         50%    → Avatares
```

### Sombras
```
card         0 1px 4px rgba(0,0,0,.04)         → Cards estándar
primary-md   0 8px 24px rgba(26,95,206,.26)    → Hero cards
```

> El archivo `saludify-tokens.json` contiene **todos** los valores con descripciones. Usarlo como fuente de verdad única.

---

## Pantallas / Vistas

### Flujo 1 — Onboarding y navegación principal

---

#### 01 · Splash
- Fondo: gradiente `brand.primary-dark → brand.primary → brand.primary-light` (138deg)
- Logo centrado: texto "Saludify" en blanco, `fontSize: 36sp`, `fontWeight: Bold`
- Tagline: "Tu salud, siempre cerca", blanco 70% opacidad, `fontSize: 14sp`
- Indicador de carga: barra horizontal blanca 30% opacidad, `width: 120dp`, `height: 3dp`, `radius: 99dp`

---

#### 02 · Bienvenida (Onboarding)
- Fondo: `#f6f9ff` (muy azul claro, similar a `brand.primary-surface-strong`)
- Logo row arriba: badge cuadrado 28dp "S" azul + "Saludify" texto azul
- Título: "Bienvenido/a", `fontSize: 28sp`, `fontWeight: Bold`, `letterSpacing: -0.6`
- Subtítulo: "Gestioná tu cobertura médica desde el celular, sin filas ni llamados.", `text.muted`, `fontSize: 14sp`
- Dos **cards** de opción (no botones): fondo blanco, `radius: 18dp`, borde 1.5dp `#e5e7eb`, sombra leve
  - "Soy afiliado" / "Ingresá con tu DNI" — ícono círculo outline azul, chevron azul
  - "Quiero afiliarme" / "Conocé nuestros planes" — ícono cruz verde, chevron gris (deshabilitado)
- Footer legal: "Al continuar aceptás los Términos y condiciones y la Política de privacidad", `fontSize: 11sp`, links en `brand.primary`

---

#### 03 · Login
- Fondo: blanco
- Back arrow arriba izquierda
- Título: "Ingresá a tu cuenta", `fontSize: 28sp`, `fontWeight: Bold`, `letterSpacing: -0.6`
- Subtítulo: "Usá tu DNI como nombre de usuario.", `text.muted`, `fontSize: 14sp`
- **Campo DNI** (label uppercase encima): fondo `#f9fafb`, `radius: 13dp`, borde `border.default` idle / `brand.primary` foco
- **Campo Contraseña**: igual, con ojo toggle derecha + link "¿Olvidaste tu contraseña?" abajo derecha
- Auth: DNI `12345678` / contraseña `1234`
- CTA "Ingresar": `brand.primary`, `radius: button`, padding vertical 17dp

---

#### 04 · Nueva contraseña
- Similar al login
- Instrucción de validación: texto `text.muted`, `fontSize: 12sp`
- Campo activo muestra borde `brand.primary` de `2dp`

---

#### 05 · Home
**Layout:** `LazyColumn` o `Column` con scroll vertical

**Secciones:**
1. **Header**: Avatar del usuario (42dp, círculo, fondo `brand.primary`), nombre, badge de plan (`background.subtle`, `radius: badge`), ícono de notificaciones (38dp círculo `background.subtle`)
2. **Credencial digital**: Card `radius: 20dp`, gradiente `brand.primary-dark → brand.primary`, datos del afiliado en blanco, número de afiliado en `fontSize: 18sp fontWeight: Bold`
3. **Accesos rápidos**: Grid 2×2, cards blancas `radius: card-lg`, ícono coloreado 42dp + label `fontSize: 13sp fontWeight: SemiBold`
4. **Próximo turno**: Card destacada con badge "Próximo turno" en verde, datos del médico
5. **Tab Bar**: 5 tabs (Inicio, Atención, Trámites, Ayuda, Perfil). Tab activo: pill `44dp×26dp` fondo `brand.primary-surface`, label `brand.primary fontWeight: SemiBold`

---

#### 06 · Atención (hub)
**Layout:** `LazyColumn` con `padding: 20dp 18dp`, `gap: 10dp`

**Cada ítem de lista:**
- Card blanca, `radius: card-lg`, `padding: 16dp 18dp`
- Ícono 42dp con fondo color semántico, `radius: icon-lg`
- Título `fontSize: 14sp fontWeight: SemiBold color: text.default`
- Subtítulo `fontSize: 12sp color: text.placeholder`
- Chevron derecho `color: border.muted`
- Algunos ítems tienen badge numérico pill

**Ítems:**
| Label | Subtítulo | Color ícono |
|---|---|---|
| Cartilla médica | Buscar médico o especialidad | `brand.primary-surface` |
| Mi cobertura | Plan y prestaciones incluidas | `#fff7ed` (orange) |
| Sacar turno | Reservar una nueva consulta | `#f0fdf4` (green) |
| Mis turnos | Próximos · cancelar | `#f0f9ff` (blue) + badge amarillo "1" |
| Historial de consultas | Mis atenciones anteriores | `#faf5ff` (purple) |

---

#### 07 · Trámites (hub)
Mismo patrón que Atención.

**Ítems:**
| Label | Subtítulo | Color ícono |
|---|---|---|
| Autorizaciones | Solicitar o consultar estado | `brand.primary-surface` + badge amarillo "2" |
| Reintegros | Cargar gastos para reintegro | `#f0fdf4` |
| Presupuestos | Cotizaciones de prácticas | `#fff7ed` |
| Facturas | Comprobantes de pago | `#faf5ff` |
| Pagos | Cuotas y medios de pago | `#f0f9ff` + badge rojo "Vence hoy" |

---

#### 08 · Perfil
**Secciones:**
1. **Avatar hero**: círculo 80dp `brand.primary`, inicial en blanco `32sp Bold`, nombre `20sp Bold`, badge plan + badge DNI
2. **Lista de menú**: mismo patrón que Atención/Trámites
3. **Cerrar sesión**: card con fondo `#fff5f5`, borde `border.danger`, texto rojo

**Ítems de menú:**
| Label | Subtítulo |
|---|---|
| Mis datos | Información personal |
| Gestionar Plan | Cobertura y beneficios |
| Grupo familiar | Familiares afiliados (badge "3") |
| Historial de consultas | Mis atenciones anteriores |

---

#### 09 · Ayuda
**Secciones con divider label:**
Cada sección tiene un label `10sp fontWeight: SemiBold color: text.disabled letterSpacing: 0.1em UPPERCASE` + línea `height: 1dp color: border.strong` a su derecha.

1. **Atención inmediata**: Card hero gradiente azul horizontal. Ícono bot 46dp, título `15sp Bold` blanco, subtítulo blanco 65%, botón pill blanco con punto verde + "Iniciar"
2. **Preguntas frecuentes**: Acordeón. Item expandido muestra respuesta con `fontSize: 12sp color: text.muted lineHeight: 1.6`. Chevron apunta ↓ cuando abierto, → cuando cerrado. Link "Ver todas" en `brand.primary`
3. **Contacto**: Segmented control (Teléfonos / Sucursales) + lista de teléfonos con ícono, nombre, número `15sp Bold`, badge de horario/gratuito

---

#### 09b · Ayuda → Sucursales
- Mismo header + segmented con "Sucursales" activo
- Banner de ubicación: fondo `brand.primary-surface`, ícono pin, texto `brand.primary 12sp`
- **Card de sucursal más cercana**: borde `brand.primary 1.5dp`, header azul sólido con nombre "Más cercana · X km" + tiempo estimado
- **Cards restantes**: header gris con distancia
- Cada card: nombre, dirección, badge abierto/cerrado, horarios, botones "Cómo llegar" + "Llamar"

---

### Flujo 2 — Sacar Turno

---

#### 10 · ¿Para quién?
- Step indicator: 4 segmentos, 1 activo (`brand.primary`), resto `#e5e7eb`, `height: 3dp radius: 99dp gap: 4dp`
- Título `26sp Bold letterSpacing: -0.5`
- **Card seleccionada**: fondo `brand.primary-surface-strong`, borde `brand.primary 2dp`, radio 18dp. Checkmark circular `brand.primary`
- **Cards no seleccionadas**: borde `border.default 1.5dp`, sin fondo especial
- Card "Agregar integrante": borde dashed `border.muted 1.5dp`

---

#### 11 · Buscador
- Campo de búsqueda activo: fondo `background.input`, borde `brand.primary 2dp`, cursor visible
- Filtros: chips pill. Activo: fondo `brand.primary` texto blanco. Inactivo: fondo `background.subtle` texto `text.secondary`
- Filtro de ubicación: inline con texto + link "Cambiar" en `brand.primary`
- **Grid de especialidades**: 2 columnas, `gap: 9dp`. Card seleccionada: fondo `brand.primary-surface-strong` borde `brand.primary-border`. Card normal: blanca

---

#### 12 · Resultados → Tabs
**Tabs sticky** al tope (dentro del header):
- Segmented control: "Turnos (badge azul con count)" | "Cartilla"
- Tab activo: blanco con sombra. Tab inactivo: sin fondo

**Card de turno:**
- Header: nombre doctor `15sp Bold`, especialidad `12sp text.muted`, badge modalidad (azul presencial / verde online)
- Dirección con ícono de lista
- Footer separado con `border.light`: chip de fecha/hora + botón "Reservar" `brand.primary radius: button-sm`
- Turno online: chip fondo `semantic.success-surface-strong`, texto `semantic.success Bold`

**"Ver más médicos en cartilla"**: card con borde dashed `brand.primary-border`, texto + chevron `brand.primary`

---

#### 12b · Resultados → Cartilla
- Banner amarillo: `background: #fef9ee border: warning.border`, ícono `!` naranja, texto informativo
- **Card de médico de cartilla:**
  - Avatar circular con iniciales
  - Nombre `15sp Bold`, especialidad `12sp`, dirección `12sp`
  - Badges: horario (`background.subtle`) + estado abierto/cerrado
  - Dos botones: **Llamar** (`brand.primary` fondo blanco) + **WhatsApp** (fondo `semantic.success-surface` borde `success.surface-strong`)
  - WhatsApp debe abrir `https://wa.me/NUMERO?text=Hola%20Dr.%20X%2C%20quiero%20sacar%20un%20turno...`

---

#### 13 · Confirmar turno
- Step indicator: 4/4 activos
- **Card doctor**: fondo `brand.primary-surface-strong` borde `brand.primary-border`, avatar `brand.primary`, nombre, especialidad, estrellas de rating `#f59e0b`
- **Card de detalles**: 3 filas separadas por `border.light`:
  - Fecha/hora: ícono calendario `brand.primary-surface`
  - Lugar: ícono pin `semantic.success-surface`
  - Paciente: avatar circular con inicial
- **Chip tipo de turno**: fondo `brand.primary-surface`, texto `brand.primary 13sp`
- Botón CTA: "Confirmar turno" full width
- Link "Cancelar" centrado debajo

---

#### 14 · Turno confirmado
- **Ícono success**: círculo 88dp fondo `semantic.success-surface-strong`, doble anillo `semantic.success-surface` 12dp, checkmark `semantic.success 5dp stroke`
- Título `26sp Bold`, subtítulo `text.muted 14sp`
- **Card resumen**: fondo `#f8faff`, `radius: panel`, datos en grid 2col para fecha/hora
- Botón "Agregar al calendario": fondo `background.subtle`, ícono calendario `text.secondary`
- Botón CTA: "Ver mis turnos"
- Link "Volver al inicio"

---

## Interacciones y comportamiento

### Tab Bar
- Tab activo: pill animado que se desplaza al tab seleccionado (animación `spring`, 300ms)
- Íconos inactivos: `text.disabled`

### Step indicator (flujo Sacar turno)
- Actualizar en cada paso de avanzar/retroceder

### Segmented control
- Transición del pill activo: `animateContentSize` o `AnimatedVisibility`, 200ms ease

### Cards de lista (Perfil, Atención, Trámites)
- Estado pressed: `background.subtle` con `indication = rememberRipple`

### Acordeón FAQ
- Expandir/colapsar con `AnimatedVisibility`, `expandVertically` + `shrinkVertically`

### Chat IA (Ayuda)
- El punto verde del botón "Iniciar" puede tener animación de pulso (`pulse` keyframe)

### Turno confirmado
- El checkmark puede entrar con animación `scale` desde 0 a 1 (300ms, `EaseOutBack`)

---

## Gestión de estado

### Variables clave por flujo

**Sacar turno:**
```kotlin
var selectedPatient: Patient?       // paso 1
var searchQuery: String             // paso 2
var activeFilter: Filter            // "Todos" | "Online" | "Presencial"
var selectedSpecialty: Specialty?   // paso 2
var resultsTab: ResultsTab          // "TURNOS" | "CARTILLA"
var selectedSlot: AppointmentSlot?  // paso 3
```

**Ayuda:**
```kotlin
var helpTab: HelpTab               // "TELEFONOS" | "SUCURSALES"
var expandedFaqIndex: Int?         // null = todos cerrados
```

**Perfil:**
```kotlin
val user: User                     // datos del titular
val familyGroup: List<Member>      // integrantes
```

---

## Assets necesarios

- **DM Sans** (Google Fonts): Regular 400, Medium 500, SemiBold 600, Bold 700
- Íconos: Usar **Material Symbols** (Outlined) para todos los íconos del sistema. Los íconos de colores en las cards son ilustrativos — en producción implementar con Material Icons o assets SVG propios
- Avatares de médico: placeholder con iniciales mientras no haya foto

---

## Notas de implementación para Android

1. **Tokens → Kotlin**: Convertir `saludify-tokens.json` a archivos Compose:
   - `ui/theme/Color.kt` → todos los valores de `color.*`
   - `ui/theme/Type.kt` → escala tipográfica con DM Sans
   - `ui/theme/Shape.kt` → valores de `borderRadius.*`
   - `ui/theme/Spacing.kt` → objeto con constantes `val CardPaddingH = 18.dp`

2. **Material 3**: Mapear los colores de Saludify al esquema de M3:
   - `primary` → `brand.primary (#1a5fce)`
   - `primaryContainer` → `brand.primary-surface (#e8effd)`
   - `surface` → `background.surface (#ffffff)`
   - `background` → `background.app (#f0f4fb)`
   - `error` → `semantic.danger (#dc2626)`

3. **Tab Bar**: Implementar como `NavigationBar` de Material 3 con estilo customizado para el pill activo

4. **Gradiente de Splash/Hero**: Usar `Brush.linearGradient` en Compose

5. **Credencial digital**: Considerar `Card` con clip redondeado y `Box` con `Brush` background

6. **WhatsApp deep link**:
   ```kotlin
   val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$phone?text=$prefilledText"))
   context.startActivity(intent)
   ```

7. **Llamar**:
   ```kotlin
   val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
   context.startActivity(intent)
   ```
