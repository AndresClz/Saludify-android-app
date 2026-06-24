# Flujos de Usuario — UNLaM Salud
**Desarrollo de Interfaces — Trabajo Práctico Final**
2do Cuatrimestre 2025

---

## Flujo 1 — Onboarding → Ver credencial y token

```
[Splash — logo UNLaM Salud]
→ ¿Ya sos afiliado? / ¿Querés afiliarte?
→ [Soy afiliado] → Login (DNI + contraseña enviada por email vía empleador)
     → ¿Primera vez?
          → SÍ → Forzar cambio de contraseña → Home
          → NO → Home
→ [Home]
     → Credencial visible en carrusel (tarjeta digital)
     → Botón "Ver token"
          → Muestra token + botón recargar
          → Válido 24hs o hasta que el médico lo utilice
```

---

## Flujo 2 — Buscar por especialidad → Seleccionar médico → Sacar turno

```
[Home] → Acceso rápido "Sacar turno" (cambia a tab Atención internamente)
→ ¿Para quién es el turno? (titular / integrante del grupo familiar)
→ Buscador (especialidad o nombre de médico)
   + Lista de especialidades frecuentes
   + Filtro: turno online / presencial
→ [Búsqueda por especialidad]
     → Grilla de turnos disponibles de todos los médicos de planta
          · Nombre del médico
          · Especialidad
          · Hospital de la obra social
          · Dirección
          · Fecha y hora
     → Botón "Ver más en cartilla"
          → Lista de médicos de cartilla de esa especialidad
               → Detalle del médico + dirección
               → Botón Llamar / WhatsApp (mensaje pre-completado)
→ [Búsqueda por nombre de médico]
     → Grilla de turnos disponibles de ese médico únicamente
→ [Seleccionar turno] → Confirmar
→ [Mis turnos] → Turno confirmado visible
```

---

## Flujo 3 — Solicitar un reintegro

```
[Home] → Tab Trámites → Reintegros
→ Lista de reintegros en curso
→ Botón "Solicitar reintegro"
→ Cargar comprobante (cámara o galería)
→ Formulario autocompletado con datos detectados del comprobante
   (monto, fecha, tipo de prestación, etc.)
   CBU ya cargado en el perfil → no se solicita
→ Botón "Solicitar"
→ [Reintegros en curso] → Nuevo reintegro visible con estado "En proceso"
```
